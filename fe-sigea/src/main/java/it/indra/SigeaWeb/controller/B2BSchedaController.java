package it.indra.SigeaWeb.controller;

import javax.servlet.http.HttpServletRequest;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.indra.es.utils.LogUtility;
import com.indra.es.utils.exceptions.RestException;
import com.indra.es.utils.model.WSO2Token;

import it.indra.SigeaCommons.model.PubblicazioneModel;
import it.indra.SigeaCommons.model.redazioni.B2BSchedaModel;
import it.indra.SigeaWeb.service.EventoService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class B2BSchedaController {

	@Autowired
	private EventoService eventoService;

	@RequestMapping(value = "/saveB2BScheda/{idEvento}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> saveB2BScheda(HttpServletRequest request, @PathVariable Long idEvento,
			@RequestBody B2BSchedaModel b2bSchedaModel, @RequestParam String noteAggiuntive,
			@RequestParam String statoPubblicazione) {

		try {
			WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");

			Long result = eventoService.saveB2BScheda(token, (String) request.getSession().getAttribute("userType"),
					idEvento, b2bSchedaModel, noteAggiuntive, statoPubblicazione);

			return new ResponseEntity<Long>(result, HttpStatus.OK);
		} catch (RestException e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/recoverB2BScheda/{idEvento}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<?> recoverB2BScheda(HttpServletRequest request, @PathVariable Long idEvento) {

		try {
			WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");

			Long result = eventoService.recoverB2BScheda(token, (String) request.getSession().getAttribute("userType"),
					idEvento);

			return new ResponseEntity<Long>(result, HttpStatus.OK);
		} catch (RestException e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/deleteB2BScheda/{idEvento}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<?> deleteB2BScheda(HttpServletRequest request, @PathVariable Long idEvento) {

		try {

			WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");

			Long result = eventoService.deleteB2BScheda(token, (String) request.getSession().getAttribute("userType"),
					idEvento);

			return new ResponseEntity<Long>(result, HttpStatus.OK);
		} catch (RestException e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getB2BScheda/{idEvento}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getB2BSchedaModel(HttpServletRequest request, @PathVariable Long idEvento)
			throws JsonProcessingException {

		try {
			WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");

			PubblicazioneModel result = eventoService.getB2BScheda(token, idEvento);

			return new ResponseEntity<PubblicazioneModel>(result, HttpStatus.OK);
		} catch (JsonProcessingException e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (RestException e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
