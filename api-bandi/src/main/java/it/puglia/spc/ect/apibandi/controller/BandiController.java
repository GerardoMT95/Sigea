package it.puglia.spc.ect.apibandi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zalando.problem.Status;

import com.indra.es.utils.RestUtility;

import it.puglia.spc.ect.apibandi.service.BandiService;
import it.puglia.spc.ect.commonsbandi.BandiDTO;
import it.puglia.spc.ect.commonsbandi.ProgettiDTO;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping
@Slf4j
public class BandiController {

	@Autowired
	@Qualifier(value = "bandiService")
	BandiService bandiService;

	@RequestMapping(value = "/bandi")
	public ResponseEntity<?> recuperaBandi() {

		try {
			log.info("Invocato servizio /bandi");
			BandiDTO bandi = bandiService.findBandi();

			return new ResponseEntity<BandiDTO>(bandi, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Errore durante il recupero dei bandi", e);
			return RestUtility.errorResponse(Status.INTERNAL_SERVER_ERROR, e.getMessage());
		}

	}

	@RequestMapping(value = "/progetti")
	public ResponseEntity<?> recuperaProgetti(
			@RequestParam(required = true, name = "codice_fiscale") String codiceFiscale) {

		try {
			log.info("Invocato servizio /progetti con codice_fiscale: " + codiceFiscale);
			ProgettiDTO progetti = bandiService.findProgetti(codiceFiscale);

			return new ResponseEntity<ProgettiDTO>(progetti, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Errore durante il recupero dei progetti", e);
			return RestUtility.errorResponse(Status.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

}
