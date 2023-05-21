package it.indra.SigecAPI.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.indra.SigeaCommons.model.ProgettoModel;
import it.indra.SigecAPI.exception.LockedResourceException;
import it.indra.SigecAPI.service.ProgettoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ProgettoController {
	
	@Autowired
	private ProgettoService progettoService;
	
/*	@RequestMapping(value = "/progetti", method = RequestMethod.GET)
	public ResponseEntity<?> getProgettiByPIVA(@RequestParam(required = false) String partitaIva)
			throws LockedResourceException, Exception {
		log.info("Invocato metodo getProgettiByPIVA con partitaIva: " + partitaIva);
		return new ResponseEntity<List<ProgettoModel>>(progettoService.getProgettobyPIVA(partitaIva), HttpStatus.OK);
	}
	*/

}
