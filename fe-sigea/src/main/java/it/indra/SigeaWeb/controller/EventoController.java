package it.indra.SigeaWeb.controller;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.indra.es.utils.LogUtility;
import com.indra.es.utils.exceptions.RestException;
import com.indra.es.utils.model.WSO2Token;

import it.indra.SigeaCommons.enumeration.StatoPubblicazione;
import it.indra.SigeaCommons.model.AttivitaModel;
import it.indra.SigeaCommons.model.AttrattoreModel;
import it.indra.SigeaCommons.model.EventoFilter;
import it.indra.SigeaCommons.model.EventoModel;
import it.indra.SigeaCommons.model.EventoModelList;
import it.indra.SigeaCommons.model.EventoTitoloModel;
import it.indra.SigeaCommons.model.LogEventoModel;
import it.indra.SigeaCommons.model.PubblicazioneModel;
import it.indra.SigeaCommons.model.SmartModelList;
import it.indra.SigeaCommons.model.StatoModel;
import it.indra.SigeaWeb.service.EventoService;
import it.indra.SigeaWeb.service.VIPService;
import it.indra.SigeaWeb.utilities.WrapperFilterRequest;
import it.indra.SigeaWeb.utilities.WrapperFilterResponse;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class EventoController {

	@Autowired
	private EventoService eventoService;

	@Autowired
	private VIPService vipService;

	@RequestMapping(value = "/eventPageList", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> getListaPaginataEventi(HttpServletRequest request, HttpServletResponse response,
			@RequestBody WrapperFilterRequest<EventoFilter> dataTableRequest) {
		log.info("Invocato metodo getListaPaginataEventi");
		try {

			WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");

			WrapperFilterResponse<EventoModelList> result = eventoService.getListaPaginataEventi(token,
					dataTableRequest);

			log.info("Return getListaPaginataEventi");
			return new ResponseEntity<WrapperFilterResponse<EventoModelList>>(result, HttpStatus.OK);
		} catch (HttpClientErrorException e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(e.getStatusCode());
		} catch (Exception e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/stati", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getListaStatiEvento(HttpServletRequest request, HttpServletResponse response) {

		try {

			WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");

			Set<StatoModel> result = eventoService.getStatiEvento(token);

			return new ResponseEntity<Set<StatoModel>>(result, HttpStatus.OK);
		} catch (HttpClientErrorException e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(e.getStatusCode());
		} catch (Exception e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getEvento", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getEvento(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Long idEvento) {

		try {

			WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");

			EventoModel result = eventoService.getEvento(token, idEvento).getBody();

			
			return new ResponseEntity<EventoModel>(result, HttpStatus.OK);
		} catch (HttpClientErrorException e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(e.getStatusCode());
		} catch (Exception e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/saveEvento/{stato}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<?> saveEvento(HttpServletRequest request, HttpServletResponse response,
			@RequestBody EventoModel model, @PathVariable String stato) {
		try {

			WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");

			Long result = eventoService.saveEvento(token, model, stato);

			return new ResponseEntity<Long>(result, HttpStatus.OK);
		} catch (HttpClientErrorException e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(e.getStatusCode());
		}  catch (Exception e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/saveEventoInLavorazione", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<?> saveEventoInLavorazione(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Long idEvento) {
		
		log.info("Invocato metodo saveEventoInLavorazione con idevento: " + idEvento);
		try {

			WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");

			/* SEMBRA CHE IL getBody finale gestisca in malomodo i json.
			 * La valutazione viene fatta nel saveEvento "if (stato.equalsIgnoreCase("LAVORAZIONE"))"
			
			EventoModel model = eventoService.getEvento(token, idEvento).getBody();
			String stato="LAVORAZIONE";
			
			Long result = eventoService.saveEvento(token, model, stato);
			*/
			
			EventoModel model = new EventoModel();
			model.setEventoId(idEvento);
			String stato="PASSAGGIO_AUTOMATICO_LAVORAZIONE";
			
			Long result = eventoService.saveEvento(token, model, stato);

			return new ResponseEntity<Long>(result, HttpStatus.OK);
		} catch (HttpClientErrorException e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(e.getStatusCode());
		}  catch (Exception e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	@RequestMapping(value = "/saveEventoInRilavorazione", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<?> saveEventoInRilavorazione(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Long idEvento) {
		
		log.info("Invocato metodo saveEventoInLavorazione con idevento: " + idEvento);
		try {

			WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");

			/* SEMBRA CHE IL getBody finale gestisca in malomodo i json.
			 * La valutazione viene fatta nel saveEvento "if (stato.equalsIgnoreCase("LAVORAZIONE"))"
			
			EventoModel model = eventoService.getEvento(token, idEvento).getBody();
			String stato="LAVORAZIONE";
			
			Long result = eventoService.saveEvento(token, model, stato);
			*/
			
			EventoModel model = new EventoModel();
			model.setEventoId(idEvento);
			String stato="PASSAGGIO_AUTOMATICO_RILAVORAZIONE";
			
			Long result = eventoService.saveEvento(token, model, stato);

			return new ResponseEntity<Long>(result, HttpStatus.OK);
		} catch (HttpClientErrorException e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(e.getStatusCode());
		}  catch (Exception e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	

	@RequestMapping(value = "/duplicaOld", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> duplicaEventoOld(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Long idEvento) {
		try {
			WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");

			String result = eventoService.duplicaEvento(token, idEvento);

			return new ResponseEntity<String>(result, HttpStatus.OK);
		} catch (HttpClientErrorException e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(e.getStatusCode());
		} catch (Exception e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@RequestMapping(value = "/duplica", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> duplicaEvento(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Long idEvento , @RequestBody AttivitaModel attivita) {
		try {
			WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");

			String result = eventoService.duplicaEvento(token, idEvento , attivita);

			return new ResponseEntity<String>(result, HttpStatus.OK);
		} catch (HttpClientErrorException e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(e.getStatusCode());
		} catch (Exception e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	@RequestMapping(value = "/lockEvento", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> lockEvento(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Long idEvento) {
		try {
			WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");

			String result = eventoService.lockEvento(token, idEvento);

			return new ResponseEntity<String>(result, HttpStatus.OK);
		} catch (HttpClientErrorException e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(e.getStatusCode());
		} catch (Exception e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// TODO gestione responceentity
	@RequestMapping(value = "/exportEventi/fetch", method = RequestMethod.GET)
	@ResponseBody
	public void exportEventi(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String ruolo , @RequestParam(required=false) String uuid) {
		try {
			
			WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");
			if(uuid!=null) 
			{
				List<EventoModelList> list =eventoService.fetchListaPaginataEventi(token, uuid);
				if(list!=null) {
				HSSFWorkbook workbook = eventoService.exportExcel(token, ruolo, list);
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Transfer-Encoding", "binary");
				response.setHeader("Content-Disposition", "attachment; filename=Eventi.xls");
				ServletOutputStream file = response.getOutputStream();
				workbook.write(file);
				file.close();
				}
				else 
				{
					response.setStatus(HttpServletResponse.SC_ACCEPTED);
				}
			}
			else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);				
			}
		} catch (Exception e) {
			if(!e.getMessage().contains("423")) {
			log.error(LogUtility.exceptionToLog(e));
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
			else {
				response.setStatus(HttpServletResponse.SC_ACCEPTED);
			}
		}
	}
	
	@RequestMapping(value = "/exportRevisioni", method = RequestMethod.GET)
	@ResponseBody
	public void exportRevisioni(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required=true) Long idEvento) {
		try {
			
			WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");
			if(idEvento!=null) 
			{				
				Set<LogEventoModel> result = eventoService.getLogEventiList(token, idEvento);				
				
				if(result!=null) {
				HSSFWorkbook workbook = eventoService.exportRevisioni(token, result);
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Transfer-Encoding", "binary");
				response.setHeader("Content-Disposition", "attachment; filename=Revisione_evento.xls");
				ServletOutputStream file = response.getOutputStream();
				workbook.write(file);
				file.close();
				}
				else 
				{
					response.setStatus(HttpServletResponse.SC_ACCEPTED);
				}
			}
			else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);				
			}
		} catch (Exception e) {
			if(!e.getMessage().contains("423")) {
			log.error(LogUtility.exceptionToLog(e));
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
			else {
				response.setStatus(HttpServletResponse.SC_ACCEPTED);
			}
		}
	}	
	
	
	@RequestMapping(value = "/exportEventi/start", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> exportEventiStart(HttpServletRequest request, HttpServletResponse response,
			@RequestBody WrapperFilterRequest<EventoFilter> dataTableRequest, @RequestParam String ruolo ) {
		try {

			
			
			WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");
			String uuid = eventoService.startPollingListaPaginataEventi(token, dataTableRequest);

			if(uuid!= null) {
				return new ResponseEntity<>(uuid, HttpStatus.OK);
			}

			return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (Exception e) {
			log.error(LogUtility.exceptionToLog(e));

			return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	

	@RequestMapping(value = "/raggruppamentopercomune", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getEventoPerComune(HttpServletRequest request, HttpServletResponse response, @RequestParam(name ="redazione" , required = false) Boolean isRedazione) {

		log.info("Entrato raggruppamentopercomune");
		
		try {
			
			
			if(isRedazione==null)
				isRedazione=false;
			WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");

			List<SmartModelList> result = eventoService.getEventoPerComune(token , isRedazione);

			log.info("Return raggruppamentopercomune");
			
			
			return new ResponseEntity<List<SmartModelList>>(result, HttpStatus.OK);
		} catch (HttpClientErrorException e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(e.getStatusCode());
		} catch (Exception e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// TODO gestione di variabili di classe
	@RequestMapping(value = "/attrattori", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getAttrattoreByTitle(HttpServletRequest request, @RequestParam String s) {

		try {
			WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");

			List<AttrattoreModel> result = vipService.getAttrattoreByTitle(s);

			return new ResponseEntity<List<AttrattoreModel>>(result, HttpStatus.OK);
		} catch (HttpClientErrorException e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(e.getStatusCode());
		} catch (Exception e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// TODO gestione di variabili di classe
/*	@RequestMapping(value = "/infopoint", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getAllInfoPoint(HttpServletRequest request) {

		try {
			WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");

			List<InfoPointModel> result = vipService.getAllInfoPoint();

			return new ResponseEntity<List<InfoPointModel>>(result, HttpStatus.OK);
		} catch (Exception e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
*/
	@RequestMapping(value = "/titoliEventi/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getTitoliEventi(HttpServletRequest request, @PathVariable Long id,
			@RequestParam(required = false) String tipoEvento, @RequestParam String q) {

		try {
			WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");

			Set<EventoTitoloModel> result = eventoService.getEventoTitoli(token, id, tipoEvento, q);

			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (HttpClientErrorException e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(e.getStatusCode());
		} catch (RestException e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// TODO gestione con base64?
	@RequestMapping(value = "/submitFile/{tipo}/{idEvento}", method = RequestMethod.POST )
	@ResponseBody
	public ResponseEntity<?> submitFile(MultipartHttpServletRequest request, @PathVariable String tipo,
			@PathVariable String idEvento,
			@RequestParam(name = "idPubblicazione", required = false) Long idPubblicazione,
			@RequestParam(name = "w", required = false) Integer w,
			@RequestParam(name = "h", required = false) Integer h,
			@RequestParam(name = "ordine", required = false) Long ordine,
			@RequestParam(name = "avoidTypeCheck", required = false) Boolean avoidTypeCheck,
			@RequestParam MultiValueMap<String, String> formData) {
		
		
		try {
			
			
			
			WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");
			MultipartFile file = request.getFile(tipo);
			
			String jsonEventoAggiornato = "";

			try {
				if (formData != null && formData.containsKey("json")) 
				{
					jsonEventoAggiornato = formData.get("json").get(0);
				}

			} catch (Exception e)
			{
				jsonEventoAggiornato = "";
			}
			log.info("Invocato metodo submitFile con file di dimensioni: " + file.getSize());
			
			String result = eventoService.submitFile(token, file, tipo, idEvento, idPubblicazione, w, h, avoidTypeCheck , ordine , jsonEventoAggiornato);
			
			return new ResponseEntity<String>(result, HttpStatus.OK);
		} catch (HttpServerErrorException e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(e.getStatusCode());
		} catch (HttpClientErrorException e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(e.getStatusCode());
		} catch (Exception e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// TODO gestione con base64?
	@RequestMapping(value = "/getFile/{idEvento}/{tipo}/{fileName}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> getFile(HttpServletRequest request, @PathVariable String idEvento,
			@PathVariable String tipo, @PathVariable String fileName,
			@RequestParam(name = "idPubblicazione", required = false) Long idPubblicazione) {

		try {
			WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");
			byte[] result = eventoService.getFile(token, "eventi/" + idEvento + "/" + tipo + "/" + fileName,
					idPubblicazione);
			return new ResponseEntity<byte[]>(result, HttpStatus.OK);
		} catch (HttpClientErrorException e) {

			
			if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
				log.error("File non trovato.");
			} else {
				log.error(LogUtility.exceptionToLog(e));
			}
			
			return new ResponseEntity<>(e.getStatusCode());
		}catch (Exception e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getPubblicazioni/{idEvento}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getPubblicazioniList(HttpServletRequest request, @PathVariable Long idEvento) {

		try {
			WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");
			Set<PubblicazioneModel> result = eventoService.getPubblicazioniList(token, idEvento);

			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (HttpClientErrorException e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(e.getStatusCode());
		} catch (Exception e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	
	@RequestMapping(value = "/getLogEventi/{idEvento}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getLogEventiList(HttpServletRequest request, @PathVariable Long idEvento) {

		try {
			WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");
			Set<LogEventoModel> result = eventoService.getLogEventiList(token, idEvento);

			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (HttpClientErrorException e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(e.getStatusCode());
		} catch (Exception e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// TODO gestione della return di una pagina da un servzio REST
	@RequestMapping(value = "/getRiassunto/{idEvento}", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView getRiassunto(HttpServletRequest request, @PathVariable Long idEvento,@RequestParam(required = false) String tipoScheda,  Model model)
			throws RestException {

		WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");

		EventoModel e = eventoService.getEvento(token, idEvento).getBody();
		
		if(e.getRelazioneSet() != null) { // rimuovo relazioni delle schede
			e.getRelazioneSet().removeIf(x -> StringUtils.isNotEmpty(x.getRedazioneId()));
		}
		
		Set<PubblicazioneModel> p = eventoService.getPubblicazioniList(token, idEvento);

		String dataPrimaRichiestaValidazione="";
		
		List<LogEventoModel> listLogSorted = new ArrayList();
		listLogSorted.addAll(e.getLogSet());


		Comparator<LogEventoModel> comparator = new Comparator<LogEventoModel>() 
		{

			@Override
			public int compare(LogEventoModel o1, LogEventoModel o2) {

				return (o2.getLogId().longValue()>o1.getLogId().longValue())?0:1;
			}

		};
		Collections.sort(listLogSorted, comparator);

		LogEventoModel primaValutazione = listLogSorted.stream().filter(x-> x.getDescrizioneStato().equalsIgnoreCase("In attesa di valutazione")).findFirst().orElse(null);
		LogEventoModel primaValidazione= listLogSorted.stream().filter(x-> x.getDescrizioneStato().equalsIgnoreCase("Validato")).findFirst().orElse(null);

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		PubblicazioneModel pubblicazioneVip =null;
		
		String redazioneVIP="NOVIP";
		
		if(tipoScheda!= null && tipoScheda.equalsIgnoreCase("redattore"))
		{
		try {
			pubblicazioneVip =  eventoService.getVipScheda(token, idEvento);
			redazioneVIP = "VIP";

		} catch (Exception e2) {
			pubblicazioneVip=null;
		}
		
		}

		if(primaValutazione==null)
			primaValutazione=primaValidazione;


		if(primaValutazione!=null)
		{
			try {
				Date date = new Date(primaValutazione.getDataModifica().getTime());
				dataPrimaRichiestaValidazione = formatter.format(date);
			}catch (Exception e1) {
				dataPrimaRichiestaValidazione="";
			}
		}

		e.getDatiGenerali().getDescrizioneMulti().put("IT", e.getDatiGenerali().getDescrizioneMulti().get("IT").replaceAll("\r\n", "<br>"));
		
		e.getDatiGenerali().getAbstractDescrMulti().put("IT", e.getDatiGenerali().getAbstractDescrMulti().get("IT").replaceAll("\r\n", "<br>"));
		e.getDatiGenerali().getTitoloMulti().put("IT", e.getDatiGenerali().getTitoloMulti().get("IT").replaceAll("\r\n", "<br>"));
		
		
		
		model.addAttribute("dataPrimaRichiestaValidazione" , dataPrimaRichiestaValidazione);
		model.addAttribute("evento", e);
		model.addAttribute("pubblicazioni", p);
		
		String statoPubblicazione = "";
try {
		if(pubblicazioneVip!=null) 
		{

			
			
			if(pubblicazioneVip.getStatoPubblicazione() == StatoPubblicazione.BOZZA) {
				statoPubblicazione = "In redazione";
			}
			else {
				statoPubblicazione = pubblicazioneVip.getStatoPubblicazione().getStatoEvento();
			}

		}
		model.addAttribute("statoPubblicazione", statoPubblicazione);
	
}catch(Exception e2) {
	LogUtility.exceptionToLog(e2);
}
		model.addAttribute("pubblicazioneVip", pubblicazioneVip);
		model.addAttribute("RedazioneVIP", redazioneVIP);

		return new ModelAndView("riassuntoEvento");
	}

		

	@RequestMapping(value = "/deleteEvento/{idEvento}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<?> deleteEvento(HttpServletRequest request, @PathVariable Long idEvento) {

		try {
			WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");

			eventoService.deleteEvento(token, idEvento);

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (HttpClientErrorException e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(e.getStatusCode());
		} catch (Exception e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@RequestMapping(value = "/ricevute/{idEvento}", method = RequestMethod.GET)
	@ResponseBody
	public void  exportRicevuta(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String stato , @PathVariable Long idEvento) {
		try {
			
			
		
			
				WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");
				if(idEvento!=null) 
				{				
					/*			String result = eventoService.exportRicevute(token, idEvento, stato);				
					
					if(result!=null) {
					
					response.setContentType("application/octet-stream");
					response.setHeader("Content-Transfer-Encoding", "binary");
					response.setHeader("Content-Disposition", "attachment; filename=Ricevuta.pdf");
					
					}
					else 
					{
						response.setStatus(HttpServletResponse.SC_ACCEPTED);
					}
				}
				else {
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);				
				}*/
			
			

			
				String result = eventoService.exportRicevute(token, idEvento, stato);	
				

				
				byte[] base64Decoded = DatatypeConverter.parseBase64Binary(result);
								log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " >>> END");

				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "attachment; filename=Ricevuta.pdf");

				OutputStream out = response.getOutputStream();
				
				out.write(base64Decoded);
				out.flush();
				out.close();
				}
				
				
				
			} catch (Exception e) {
				log.error(LogUtility.exceptionToLog(e));
				response.setStatus(500);
				
			}
			
		

	}
		
	
	
}
