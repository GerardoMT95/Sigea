package it.indra.SigeaWeb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.indra.es.utils.LogUtility;
import com.indra.es.utils.exceptions.RestException;
import com.indra.es.utils.model.WSO2Token;

import it.indra.SigeaCommons.model.EventoModel;
import it.indra.SigeaCommons.model.PubblicazioneModel;
import it.indra.SigeaCommons.model.redazioni.VIPSchedaModel;
import it.indra.SigeaWeb.service.EventoService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class VIPSchedaController {

	@Autowired
	private EventoService eventoService;

	@RequestMapping(value = "/saveVipScheda/{idEvento}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> saveVipScheda(HttpServletRequest request, @PathVariable Long idEvento,
			@RequestBody VIPSchedaModel vipSchedaModel, @RequestParam String noteAggiuntive,
			@RequestParam String statoPubblicazione) {

		try {

			WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");

			String userType = (String) request.getSession().getAttribute("userType");

			if (request.isUserInRole("ROLE_SIGEA_PUBBLICAZIONE_VIP")) {
				userType = "REDCAP-PP";
			} else if (request.isUserInRole("ROLE_SIGEA_REDAZIONE_VIP")) {
				userType = "RED-PP";
			} else {
				log.error("Utente non autorizzato");
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}

			
			Long result = eventoService.saveVipScheda(token, userType, idEvento, vipSchedaModel, noteAggiuntive,
					statoPubblicazione);
			return new ResponseEntity<Long>(result, HttpStatus.OK);
		} catch (RestException e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/recoverVipScheda/{idEvento}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<?> recoverVipScheda(HttpServletRequest request, @PathVariable Long idEvento) {

		try {

			WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");

			String userType = (String) request.getSession().getAttribute("userType");

			if (request.isUserInRole("ROLE_SIGEA_PUBBLICAZIONE_VIP")) {
				userType = "REDCAP-PP";
			} else if (request.isUserInRole("ROLE_SIGEA_REDAZIONE_VIP")) {
				userType = "RED-PP";
			} else {
				log.error("Utente non autorizzato");
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}

			Long result = eventoService.recoverVipScheda(token, userType, idEvento);

			return new ResponseEntity<Long>(result, HttpStatus.OK);
		} catch (RestException e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/deleteVipScheda/{idEvento}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<?> deleteVipScheda(HttpServletRequest request, @PathVariable Long idEvento) {

		try {

			WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");

			String userType = (String) request.getSession().getAttribute("userType");

			if (request.isUserInRole("ROLE_SIGEA_PUBBLICAZIONE_VIP")) {
				userType = "REDCAP-PP";
			} else if (request.isUserInRole("ROLE_SIGEA_REDAZIONE_VIP")) {
				userType = "RED-PP";
			} else {
				log.error("Utente non autorizzato");
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}

			Long result = eventoService.deleteVipScheda(token, userType, idEvento);

			return new ResponseEntity<Long>(result, HttpStatus.OK);
		} catch (RestException e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getVipScheda/{idEvento}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getVIPSchedaModel(HttpServletRequest request, @PathVariable Long idEvento)
			throws JsonProcessingException {

		try {

			WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");

			
			PubblicazioneModel result = eventoService.getVipScheda(token, idEvento);

			
			return new ResponseEntity<PubblicazioneModel>(result, HttpStatus.OK);
		} catch (JsonProcessingException e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (RestException e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/saveEventoInRedazione", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<?> saveEventoInLavorazione(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Long idEvento) {
		log.info("Invocato metodo saveEventoInRedazione con idevento: " + idEvento);
		try {

			ObjectMapper jsonObjectMapper = new ObjectMapper();
		
			WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");

			String userType = (String) request.getSession().getAttribute("userType");

			if (request.isUserInRole("ROLE_SIGEA_PUBBLICAZIONE_VIP")) {
				userType = "REDCAP-PP";
			} else if (request.isUserInRole("ROLE_SIGEA_REDAZIONE_VIP")) {
				userType = "RED-PP";
			} else {
				log.error("Utente non autorizzato");
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
			
			PubblicazioneModel pubblicazioneModel = eventoService.getVipScheda(token, idEvento);
			VIPSchedaModel vipSchedaModel = jsonObjectMapper.treeToValue(pubblicazioneModel.getGenericMetadata(), VIPSchedaModel.class);
			vipSchedaModel.setTipoScheda("evento");
			Long result = eventoService.saveVipScheda(token, userType, idEvento, vipSchedaModel, "Cambio stato in Redazione Automatizzato",
					"BOZZA");

			
			return new ResponseEntity<Long>(result, HttpStatus.OK);
		} catch (HttpClientErrorException e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(e.getStatusCode());
		}  catch (Exception e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
