package it.indra.SigecAPI.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.indra.SigeaCommons.model.EventoModel;
import it.indra.SigecAPI.exception.LockedResourceException;
import it.indra.SigecAPI.repository.LogEventoRepository;
import it.indra.SigecAPI.service.EventoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "")
public class CulturaController {

	
	
	@Value("${filesystem.tmpDirectory}")
	private String tmpDirectory;
	
	
	@Autowired
	private EventoService eventoService;

	@Autowired
	private LogEventoRepository logEventoRepository;
	
	

	@RequestMapping(value = "/eventi/external/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getEvento(@PathVariable Long id, HttpServletRequest request)
			throws LockedResourceException, Exception {
		log.info("Invocato metodo getEvento con id: " + id);
		
		EventoModel eventomodel = eventoService.getEvento(id);
		return new ResponseEntity<EventoModel>(eventomodel, HttpStatus.OK);
	}


	/**
	 * Servizio per ricevere gli id e lo stato degli eventi, validati almeno una volta, modificati nell'arco temporale indicato dai parametri in input
	 * N.B. Se le date non sono presenti verranno restituiti tutti gli eventi che sono stati validati almeno una volta
	 * 
	 * @param dataDa data iniziale
	 * @param dataA data finale
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/eventi-validati/id/list", method = RequestMethod.GET)
	public ResponseEntity<?> getListaPaginataEventi(
			@RequestParam(value = "dataDa", required=false) String dataDa , @RequestParam(value = "dataA", required=false) String dataA)
					throws Exception {
		List<Long> eventiModificati = eventoService.getListaEventiModificatiPerData(dataDa , dataA);

		log.info("Elementi trovati = " + eventiModificati.size());
		return new ResponseEntity<List<Long>>(eventiModificati, HttpStatus.OK);
		
		
/*		List<EventoModificatoModel> eventiModificati = eventoService.getListaEventiModificatiPerData(dataDa , dataA);

		log.info("Elementi trovati = " + eventiModificati.size());
		return new ResponseEntity<List<EventoModificatoModel>>(eventiModificati, HttpStatus.OK);
*/
	}


	@RequestMapping(value = "/eventi/external/{id}/{fileType}/{fileName}", method = RequestMethod.GET)
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

	
}
