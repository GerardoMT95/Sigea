package it.indra.SigecAPI.rest;

import java.util.Set;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.indra.SigeaCommons.model.DettaglioUtenteModel;
import it.indra.SigeaCommons.model.RedazioneModel;
import it.indra.SigeaCommons.model.TipologiaModel;
import it.indra.SigecAPI.clientstub.DMSClientStub;
import it.indra.SigecAPI.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UtenteController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	private DMSClientStub dmsClientStub;
	
	@RequestMapping(value = "/utenti", method = RequestMethod.PUT)
	public ResponseEntity<?> saveUtente(HttpServletRequest request, @RequestParam String tipologia, @RequestParam(required = false) Long entitaId) throws AuthenticationException{
		log.info("Invocato metodo saveUtente");
		DettaglioUtenteModel user  = dmsClientStub.getUserFromRequest(request);
		user.setEntitaId(entitaId);
		return new ResponseEntity<DettaglioUtenteModel>(userService.profilaUtente(user,tipologia), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/tipologie", method = RequestMethod.GET)
	public ResponseEntity<?> getTipologie() throws Exception {
		log.info("Invocato metodo getTipologie");
		Set<TipologiaModel> tipologieModelSet = userService.getTipologie();
		return new ResponseEntity<Set<TipologiaModel>>(tipologieModelSet, HttpStatus.OK);
	}

	@RequestMapping(value = "/redazioni", method = RequestMethod.GET)
	public ResponseEntity<?> getRedazioni() throws Exception {
		log.info("Invocato metodo getRedazioni");
		Set<RedazioneModel> redazioniModelSet = userService.getRedazioni();
		return new ResponseEntity<Set<RedazioneModel>>(redazioniModelSet, HttpStatus.OK);
	}
	

}
