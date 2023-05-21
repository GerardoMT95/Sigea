package it.indra.SigecAPI.rest;

import java.util.List;
import java.util.Map;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.indra.SigeaCommons.model.ElencoRiconciliazioni;
import it.indra.SigecAPI.clientstub.DMSClientStub;
import it.indra.SigecAPI.service.RiconciliazioneService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/riconciliazioni")
public class RiconciliazioneController {

	@Autowired
	RiconciliazioneService riconciliazioneService;
	
	@Autowired
	private DMSClientStub dmsClientStub;
	
	@GetMapping
	public ResponseEntity<?> riconciliazioneElenco(@RequestParam(value = "elencomail", required = true) List<String> elencoEmail) {
		log.info("Invocato metodo riconciliazioneElenco()");
		return new ResponseEntity<Object>(riconciliazioneService.elaboraEmail(elencoEmail), HttpStatus.OK);
	}
	
	@PostMapping(value = "/associazioni")
	public ResponseEntity<?> riconciliazioneAssociazione(@RequestBody List<ElencoRiconciliazioni> elencoRiconciliazioni, HttpServletRequest request) throws AuthenticationException{
		log.info("Invocato metodo riconciliazioneAssociazione()");	
		Map<Long, String> elenco = riconciliazioneService.verificaRiconciliazioni(elencoRiconciliazioni, dmsClientStub.getUserFromRequest(request));
		HttpStatus status = elenco.isEmpty() ? HttpStatus.OK : HttpStatus.CONFLICT;
		return new ResponseEntity<Object>(elenco, status);
	}
	
}
