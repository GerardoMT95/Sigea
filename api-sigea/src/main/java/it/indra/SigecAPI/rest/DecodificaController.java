package it.indra.SigecAPI.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.indra.SigeaCommons.model.DecodificaModel;
import it.indra.SigecAPI.service.DecodificaService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class DecodificaController {
	
	@Autowired
	DecodificaService decodificaService;
	
	@RequestMapping(value = "/decodifiche", method = RequestMethod.GET)
	public ResponseEntity<?> getDecodifiche(){
		log.info("Invocato metodo getDecodifiche");
		return new ResponseEntity<List<DecodificaModel>>(decodificaService.getDecodifiche(), HttpStatus.OK);
	}
	
	
}
