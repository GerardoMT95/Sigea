package it.indra.SigecAPI.rest;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zalando.problem.Status;

import com.indra.es.utils.RestUtility;

import it.indra.SigeaCommons.model.BandoEventoDTO;
import it.indra.SigeaCommons.model.BandoModelList;
import it.indra.SigecAPI.service.BandoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class BandoController {

	@Autowired
	BandoService bandoService;

	@RequestMapping(value = "/bandiProgetti", method = RequestMethod.GET)
	public ResponseEntity<?> getBandiProgettiSelect(
			@RequestParam(value = "idAttivita", required = false) Long idAttivita) {
		try {

			log.info("Invocato metodo getBandiProgetti");

			return new ResponseEntity<Set<BandoModelList>>(bandoService.getBandiProgetti(idAttivita), HttpStatus.OK);

		} catch (Exception e) {
			log.error("Errore durante il recupero dei bandi", e);
			return RestUtility.errorResponse(Status.INTERNAL_SERVER_ERROR, e.getMessage());
		}

	}

	@RequestMapping(value = "/eventi-finanziati/eventiByBando", method = RequestMethod.GET)
	public ResponseEntity<?> getEventiByBando(@RequestParam(value = "idBando", required = true) String idBando) {

		log.info("Invocato metodo getBando con id"+idBando);
		
		List<BandoEventoDTO> bandoEventoDTOList = bandoService.getEventiByBando(idBando);
		
		return new ResponseEntity<List<BandoEventoDTO>>(bandoEventoDTOList, HttpStatus.OK);
	
	}

}
