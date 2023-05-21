package it.indra.SigecAPI.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.zalando.problem.Status;

import com.indra.es.utils.LogUtility;
import com.indra.es.utils.RestUtility;

import it.indra.SigeaCommons.model.AttivitaModel;
import it.indra.SigeaCommons.model.EventoFilter;
import it.indra.SigeaCommons.model.EventoModel;
import it.indra.SigeaCommons.model.EventoModelList;
import it.indra.SigeaCommons.model.EventoTitoloModel;
import it.indra.SigeaCommons.model.LogEventoModel;
import it.indra.SigeaCommons.model.PubblicazioneModel;
import it.indra.SigeaCommons.model.SmartModelList;
import it.indra.SigeaCommons.model.StatoModel;
import it.indra.SigecAPI.clientstub.DMSClientStub;
import it.indra.SigecAPI.entity.Attivita;
import it.indra.SigecAPI.exception.LockedResourceException;
import it.indra.SigecAPI.service.EventoService;
import it.indra.SigecAPI.util.CommonUtility;
import it.indra.SigecAPI.util.ExportEventiThreadUtility;
import it.indra.SigecAPI.util.WrapperFilterRequest;
import it.indra.SigecAPI.util.WrapperFilterResponse;
import it.indra.SigecAPI.util.WrapperFilterUtility;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class EventoController {

	
	
	@Value("${filesystem.tmpDirectory}")
	private String tmpDirectory;
	
	
	@Autowired
	private EventoService eventoService;

	@Autowired
	private DMSClientStub dmsClientStub;
	
	
	@RequestMapping(value = "/eventi/{stato}", method = RequestMethod.PUT)
	public ResponseEntity<?> saveEvento(@RequestBody EventoModel model, @PathVariable String stato,
			HttpServletRequest request) throws LockedResourceException, IOException, Exception {
		log.info("Invocato metodo saveEvento con model: " + model.toString() + " e stato: " + stato);
		Long eventoId = eventoService.saveEvento(model, dmsClientStub.getUserFromRequest(request).getUtenteId(), stato);
		return new ResponseEntity<Long>(eventoId, HttpStatus.OK);
	}

	@RequestMapping(value = "/eventi/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getEvento(@PathVariable Long id, HttpServletRequest request)
			throws LockedResourceException, Exception {
		log.info("Invocato metodo getEvento con id: " + id);
		
		long idUtente=dmsClientStub.getUserFromRequest(request).getUtenteId();
		
		EventoModel eventomodel = eventoService.getEvento(id, idUtente);
		return new ResponseEntity<EventoModel>(eventomodel, HttpStatus.OK);
	}

	@RequestMapping(value = "/eventi/listapaginata", method = RequestMethod.GET)
	public ResponseEntity<?> getListaPaginataEventi(
			@RequestParam(value = "wrappedFilter") String requestFilterStringCoded, HttpServletRequest request)
			throws Exception {
		WrapperFilterRequest<EventoFilter> wrappedFilter = WrapperFilterUtility
				.extractWrapperFromCodedString(requestFilterStringCoded, EventoFilter.class);
		log.info("Invocato metodo getListaPaginataEventi con filtro: " + wrappedFilter.getFilter().toString());
		WrapperFilterResponse<?> response = eventoService.getListaPaginata(wrappedFilter,
				dmsClientStub.getUserFromRequest(request).getUtenteId());
		return new ResponseEntity<WrapperFilterResponse<?>>(response, HttpStatus.OK);
	}
	
	
	
	
	@RequestMapping(value = "/eventi/export/start", method = RequestMethod.GET)
	public ResponseEntity<String> getListaPaginataEventiThread(
			@RequestParam(value = "wrappedFilter") String requestFilterStringCoded, HttpServletRequest request)
			throws Exception {
		
		
		Long idUser = dmsClientStub.getUserFromRequest(request).getUtenteId();
		
		
		try {
		
			String uuid = UUID.randomUUID().toString();
			String prefix=idUser+"_"+"ricercaexporteventi_";
			log.info("Cancellando file dell'utente "+idUser+" da tmp...");
			final File downloadDirectory = new File(tmpDirectory);   
			final File[] files = downloadDirectory.listFiles( (dir,name) -> name.startsWith(prefix));    
			if(files!=null && files.length>0) {
		    Arrays.asList(files).stream().forEach(File::delete);
		    log.info("Cancellazione da tmp effettuata...");
		   }else {
			   log.info("Nessun file utente presente in tmp...");
		   }
		
		
			Thread t = new Thread(new ExportEventiThreadUtility(eventoService, requestFilterStringCoded, idUser, uuid, tmpDirectory));
			t.start();

	
		return new ResponseEntity<String>(uuid, HttpStatus.OK);
		}catch (Exception e) {
			
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	
	@RequestMapping(value = "/eventi/export/fetch", method = RequestMethod.GET)
	public ResponseEntity<?> getEventiExport(@RequestParam(value = "uuid") String uuid,
			HttpServletResponse httpServletResponse,HttpServletRequest httpServletRequest) {

		try {
			
			Long idUser=dmsClientStub.getUserFromRequest(httpServletRequest).getUtenteId();
			
			
			String prefix=idUser+"_"+"ricercaexporteventi_";
			String nameFile = prefix + uuid;
			String pathFile=tmpDirectory+nameFile;
			
			File file = new File(pathFile);
			List<EventoModelList> strutture;
			if (file.exists()) {
				strutture = new ArrayList<EventoModelList>();
				log.info("Ricerca " + uuid + " terminata e pronta per la visualizzazione in pagina");
				FileInputStream fileIn = null;
				ObjectInputStream objectIn = null;
				try {
					fileIn = new FileInputStream(pathFile);
					objectIn = new ObjectInputStream(fileIn);
					Object obj = objectIn.readObject();
					strutture = (List<EventoModelList>) obj;
				} catch (ClassCastException e) {
					
					log.error(LogUtility.exceptionToLog(e));
					return new ResponseEntity<List<EventoModelList>>( HttpStatus.INTERNAL_SERVER_ERROR);
				} finally {
					if (objectIn != null) {
						objectIn.close();
					}
					if (fileIn != null) {
						fileIn.close();
					}
				}
				log.info("Cancellando ricerca dalla cartella tmp dell'utente "+idUser);
				file.delete();
				log.info("Cancellazione da tmp effettuata...");
				return new ResponseEntity<List<EventoModelList>>(strutture, HttpStatus.FOUND);
			} else {
				log.info("Export eventi " + uuid + " NON terminata");
				return RestUtility.errorResponse(Status.LOCKED, "Export eventi" + uuid + " NON terminata");
			}

		} catch (Exception e) {
			log.error("Errore durante il check sul file", e);
			return RestUtility.errorResponse(Status.INTERNAL_SERVER_ERROR, e.getMessage());
		}

	}
	
	
	

	@RequestMapping(value = "/eventi/lock/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> lockEvento(@PathVariable Long id, HttpServletRequest request)
			throws LockedResourceException, Exception {
	//log.info("Invocato metodo lockEvento con id: " + id);
		eventoService.lockEvento(id, dmsClientStub.getUserFromRequest(request).getUtenteId());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "eventi/stati", method = RequestMethod.GET)
	public ResponseEntity<?> getStatiEvento() throws Exception {
		log.info("Invocato metodo getStatiEvento");
		Set<StatoModel> statomodel = eventoService.getStatiEvento();
		return new ResponseEntity<Set<StatoModel>>(statomodel, HttpStatus.OK);
	}

	@RequestMapping(value = "eventi/{id}/{fileType}/{fileName}", method = RequestMethod.GET)
	public ResponseEntity<?> getFile(@PathVariable Long id, @PathVariable String fileType,
			@PathVariable String fileName,  @RequestParam(required = false) Long idPubblicazione) {
		log.info("Invocato metodo getFile di tipo: " + fileType + " con id: " + id + " e nome: " + fileName);
		try {
			return new ResponseEntity<byte[]>(eventoService.getFile(id, fileName, fileType, idPubblicazione), HttpStatus.OK);
		} catch (IOException e) {
			log.error("Risorsa non presente o cancellata in modo permanente: "+e.getMessage());
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "eventi/{id}/file", method = RequestMethod.POST)
	public ResponseEntity<?> saveFile(@PathVariable Long id, HttpServletRequest request,
			@RequestParam("file") MultipartFile file) throws LockedResourceException, Exception {
		log.info("Invocato metodo saveFile con id: " + id);
		Long ordine=0L;
		String tipo="";
		try {
			ordine= Long.parseLong(request.getParameter("ordine"));
			tipo= request.getParameter("tipo");
		}catch (Exception e) {
			log.error(LogUtility.exceptionToLog(e));
		}
		
		
		
		return new ResponseEntity<>(
				eventoService.saveFile(id, file, dmsClientStub.getUserFromRequest(request).getUtenteId(), null, null, null , ordine, tipo),
				HttpStatus.OK);
	}

	@RequestMapping(value = "eventi/{id}/pubblicazione/{idPubblicazione}/file", method = RequestMethod.POST)
	public ResponseEntity<?> saveFilePubblicato(@PathVariable Long id, HttpServletRequest request,
			@RequestParam("file") MultipartFile file, @PathVariable Long idPubblicazione, @RequestParam(required = false) Integer resizeWidth, @RequestParam(required = false) Integer resizeHeight)
			throws LockedResourceException, Exception {
		log.info("Invocato metodo saveFilePubblicato con id: " + id + "e pubblicazione: " + idPubblicazione);
		Long ordine=0L;
		String tipo="";
		try {
			if(request.getParameter("ordine") != null) {
				ordine= Long.parseLong(request.getParameter("ordine"));
			}
		
		}catch (Exception e) {
			log.error(LogUtility.exceptionToLog(e));
		}
		
		try {
			if(request.getParameter("tipo")!= null) {
				tipo= request.getParameter("tipo");
			}
		}catch (Exception e) {
			log.error(LogUtility.exceptionToLog(e));
		}
		
		return new ResponseEntity<>(eventoService.saveFile(id, file,
				dmsClientStub.getUserFromRequest(request).getUtenteId(), idPubblicazione, resizeWidth, resizeHeight, ordine,tipo), HttpStatus.OK);
	}

	@RequestMapping(value = "eventi/{id}/titoli", method = RequestMethod.GET)
	public ResponseEntity<?> getEventoTitoli(@PathVariable Long id, @RequestParam(required = false) String tipoEvento,
			@RequestParam String q, HttpServletRequest request) throws AuthenticationException {
		log.info("Invocato metodo getEventoTitoli di tipo: " + tipoEvento + " con titolo: " + q);
		Set<EventoTitoloModel> eventoTitoloModelSet = eventoService.getTitoliEvento(id, tipoEvento, q,
				dmsClientStub.getUserFromRequest(request).getUtenteId());
		return new ResponseEntity<Set<EventoTitoloModel>>(eventoTitoloModelSet, HttpStatus.OK);
	}

	@RequestMapping(value = "eventi/attivita/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> cambiaAttivita(@RequestBody Attivita attivita, @PathVariable Long id, HttpServletRequest request) {
		log.info("Invocato metodo cambiaAttivita con attivita': " + attivita + " e id: " + id);
		eventoService.cambiaAttivita(attivita, id, CommonUtility.getTokenFromRequest(request));
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "eventi/duplicaOld/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> duplicaEventoOld(@PathVariable Long id, HttpServletRequest request) throws Exception {
		log.info("Invocato metodo duplicaEvento con id: " + id);
		eventoService.duplicaEvento(id, dmsClientStub.getUserFromRequest(request).getUtenteId());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	

	@RequestMapping(value = "eventi/duplica/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> duplicaEvento(@PathVariable Long id, HttpServletRequest request, @RequestBody AttivitaModel attivita) throws Exception {
		log.info("Invocato metodo duplicaEvento con id: " + id);
		eventoService.duplicaEvento(id, dmsClientStub.getUserFromRequest(request).getUtenteId() , attivita);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "eventi/raggruppamentopercomune", method = RequestMethod.GET)
	public ResponseEntity<?> getEventoPerComune(@RequestParam (name="redazione" , required = false ) Boolean isRedazione) throws Exception {
		log.info("Invocato metodo getEventoPerComune");
		if(isRedazione==null) 
		{
			isRedazione = false;
		}
		List<SmartModelList> smartModel = eventoService.getEventoPerComune(isRedazione); 
		return new ResponseEntity<List<SmartModelList>>(smartModel, HttpStatus.OK);
	}

	@RequestMapping(value = "eventi/{idEvento}/redazione/{idRedazione}/{tipoUtente}", method = RequestMethod.PUT)
	public ResponseEntity<?> addMetadataToEvento(@RequestParam String noteAggiuntive,
			@RequestParam String statoPubblicazione, @PathVariable Long idEvento, @PathVariable String tipoUtente,
			@PathVariable String idRedazione, @RequestBody String json, HttpServletRequest request)
			throws IllegalAccessException, Exception {
		log.info("Invocato metodo addMetadataToEvento con id Redazione: " + idRedazione + " dettagli aggiuntivi: "
				+ noteAggiuntive + " e id: " + idEvento);
		if(idRedazione.equals("GENERAL")) {
			throw new IllegalAccessException();
		}
		log.info(json);
		
		
		Long idPubblicazione = eventoService.addMetadata(idEvento, dmsClientStub.getUserFromRequest(request),
				tipoUtente, idRedazione, json, noteAggiuntive, statoPubblicazione,
				CommonUtility.getTokenFromRequest(request));
		return new ResponseEntity<>(idPubblicazione, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "eventi/{idEvento}/redazione/{idRedazione}/{tipoUtente}/recupero", method = RequestMethod.PUT)
	public ResponseEntity<?> recoverLastPubblicazione(@PathVariable Long idEvento, @PathVariable String tipoUtente,
			@PathVariable String idRedazione, HttpServletRequest request) throws IllegalAccessException, Exception {
		log.info("Invocato metodo recoverLastPubblicazione con id Redazione: " + idRedazione + " e id: " + idEvento);
		if(idRedazione.equals("GENERAL")) {
			throw new IllegalAccessException();
		}
		Long idPubblicazione = eventoService.recoverMetadata(idEvento, dmsClientStub.getUserFromRequest(request),
				tipoUtente, idRedazione);
		return new ResponseEntity<>(idPubblicazione, HttpStatus.OK);
	}

	@RequestMapping(value = "eventi/{idEvento}/redazione/{idRedazione}/{tipoUtente}/revocapubblicazione", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteLastPubblicazione(@PathVariable Long idEvento, @PathVariable String tipoUtente,
			@PathVariable String idRedazione, HttpServletRequest request) throws IllegalAccessException, Exception {
		log.info("Invocato metodo deleteLastPubblicazione con id Redazione: " + idRedazione + " e id: " + idEvento);
		if(idRedazione.equals("GENERAL")) {
			throw new IllegalAccessException();
		}
		Long idPubblicazione = eventoService.deleteMetadata(idEvento, dmsClientStub.getUserFromRequest(request),
				tipoUtente, idRedazione, CommonUtility.getTokenFromRequest(request));
		return new ResponseEntity<>(idPubblicazione, HttpStatus.OK);
	}

	@RequestMapping(value = "eventi/{idEvento}/redazione/{idRedazione}", method = RequestMethod.GET)
	public ResponseEntity<?> getMetadataFromEvento(@PathVariable Long idEvento, @PathVariable String idRedazione,
			@RequestParam(required = false) Long idPubblicazione) throws Exception {
		log.info("Invocato metodo getMetadataFromEvento con id Redazione: " + idRedazione + " id pubblicazione: "
				+ idPubblicazione + " e id evento: " + idEvento);
		PubblicazioneModel model = eventoService.getMetadata(idEvento, idRedazione, idPubblicazione);
		if (model == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(model, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "eventi/{id}/pubblicazioni", method = RequestMethod.GET)
	public ResponseEntity<?> getPubblicazioniList(@PathVariable Long id) {
		log.info("Invocato metodo getPubblicazioniList con id: " + id);
		Set<PubblicazioneModel> modelSet = eventoService.getPubblicazioneList(id);
		return new ResponseEntity<>(modelSet, HttpStatus.OK);
	}
	
	@RequestMapping(value = "eventi/{id}/logEventi", method = RequestMethod.GET)
	public ResponseEntity<?> getLogEventiList(@PathVariable Long id) {
		log.info("Invocato metodo getLogEventiList con id: " + id);
		Set<LogEventoModel> modelSet = eventoService.getLogEventiList(id);
		return new ResponseEntity<>(modelSet, HttpStatus.OK);
	}

	@RequestMapping(value = "eventi/redazione/{idRedazione}", method = RequestMethod.GET)
	public ResponseEntity<?> getPubblicazioniListByMetadata(@PathVariable String idRedazione,
			@RequestParam(required = false) String search) {
		log.info("Invocato metodo getPubblicazioniListByMetadata con id Redazione: " + idRedazione);
		Set<PubblicazioneModel> modelSet = eventoService.getPubblicazioneListByMetadata(idRedazione, search);
		return new ResponseEntity<>(modelSet, HttpStatus.OK);
	}

	@RequestMapping(value = "eventi/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteEvento(@PathVariable Long id, HttpServletRequest request)
			throws LockedResourceException, Exception {
		log.info("Invocato metodo deleteEvento con id: " + id);
		eventoService.cancellaEvento(id, dmsClientStub.getUserFromRequest(request).getUtenteId());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/eventi/{id}/validato", method = RequestMethod.GET)
	public ResponseEntity<?> getEventoValidato(@PathVariable Long id, HttpServletRequest request)
			throws LockedResourceException, Exception {
		log.info("Invocato metodo getEventoValidato con id: " + id);
		EventoModel eventomodel = eventoService.getEvento(id, dmsClientStub.getUserFromRequest(request).getUtenteId());
		if (eventomodel.getStatoId().equals("VALIDATO")) {
			return new ResponseEntity<EventoModel>(eventomodel, HttpStatus.OK);
		} else {
			return new ResponseEntity<EventoModel>(HttpStatus.NOT_FOUND);
		}
	}
}
