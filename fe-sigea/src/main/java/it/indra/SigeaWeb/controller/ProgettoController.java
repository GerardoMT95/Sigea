package it.indra.SigeaWeb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

import com.indra.es.utils.LogUtility;
import com.indra.es.utils.exceptions.RestException;
import com.indra.es.utils.model.WSO2Token;

import it.indra.SigeaCommons.model.ProgettoModel;
import it.indra.SigeaWeb.service.DMSService;
import it.indra.SigeaWeb.service.ProgettoService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ProgettoController {

	@Autowired
	private ProgettoService progettoService;

	@Autowired
	private DMSService dmsService;

	@RequestMapping(value = "/getProgetti", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getProgettiByPIVA(HttpServletRequest request,
			@RequestParam(required = false) String piva) {

		try {
log.info("Entrato getProgetti");
			
			WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");
			List<ProgettoModel> result = progettoService.getProgettiByPIVA(token, piva);

			log.info("Return getProgetti");
			
			return new ResponseEntity<List<ProgettoModel>>(result, HttpStatus.OK);
		} catch (HttpClientErrorException e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(e.getStatusCode());
		} catch (RestException e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getpiva", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> getPiva(HttpServletRequest request, @RequestParam String id,
			@RequestParam Boolean approvata) {
		try {
			WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");

			String piva = dmsService.getPIVA(token, id, approvata);

			return new ResponseEntity<String>(piva, HttpStatus.OK);
		} catch (HttpClientErrorException e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(e.getStatusCode());
		} catch (Exception e) {
			log.error("PARTITA IVA NON TROVATA PER ATTIVITA " + id + " approvata " + approvata.booleanValue());
			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
