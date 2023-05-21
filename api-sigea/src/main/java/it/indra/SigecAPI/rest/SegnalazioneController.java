package it.indra.SigecAPI.rest;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.util.Set;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.DecoderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.indra.SigeaCommons.model.SegnalazioneFilter;
import it.indra.SigeaCommons.model.SegnalazioneModel;
import it.indra.SigeaCommons.model.SegnalazioneStatoPatchModel;
import it.indra.SigecAPI.clientstub.DMSClientStub;
import it.indra.SigecAPI.entity.Segnalazione;
import it.indra.SigecAPI.service.SegnalazioneService;
import it.indra.SigecAPI.util.CommonUtility;
import it.indra.SigecAPI.util.WrapperFilterRequest;
import it.indra.SigecAPI.util.WrapperFilterResponse;
import it.indra.SigecAPI.util.WrapperFilterUtility;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SegnalazioneController {
	
	@Autowired
	private SegnalazioneService segnalazioniService;
	
	@Autowired
	private DMSClientStub dmsClientStub;
	
	@RequestMapping(value = "/segnalazioni/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getSegnalazione(@PathVariable Long id) {
		log.info("Invocato metodo getSegnalazione con id: " + id);
		SegnalazioneModel segnalazione = segnalazioniService.getSegnalazione(id);
		return new ResponseEntity<SegnalazioneModel>(segnalazione, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/segnalazioni", method = RequestMethod.POST)
	public ResponseEntity<?> insertSegnalazione(@RequestBody SegnalazioneModel model, HttpServletRequest request) throws IllegalAccessException, InvocationTargetException, AuthenticationException {
		log.info("Invocato metodo insertSegnalazione con model: " + model.toString());
		Segnalazione segnalazione = segnalazioniService.insertSegnalazione(model, dmsClientStub.getUserFromRequest(request).getUtenteId());
		return new ResponseEntity<Long>(segnalazione.getSegnalazioneId(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/segnalazioni/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> cambiaStato(@PathVariable Long id, @RequestBody SegnalazioneStatoPatchModel statusPatchModel) {
		log.info("Invocato metodo cambiaStato con id: " + id + " e stato: " + statusPatchModel.toString());
		segnalazioniService.cambiaStato(id, statusPatchModel.getStato());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/segnalazioni", method = RequestMethod.GET)
	public ResponseEntity<?> getListaSegnalazioni(@RequestParam Date dataDa,@RequestParam String codIstat) {
		log.info("Invocato metodo getListaSegnalazioni con data da: " + dataDa + " e codice istat: " + codIstat);
		Set<SegnalazioneModel> lista = segnalazioniService.getListaSegnalazioniPerDataLuogo(dataDa, codIstat);
		return new ResponseEntity<Set<SegnalazioneModel>>(lista, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/segnalazioni/listapaginata", method = RequestMethod.GET)
	public ResponseEntity<?> getListaPaginataSegnalazioni(@RequestParam(value = "wrappedFilter") String requestFilterStringCoded , HttpServletRequest request) throws DecoderException, InstantiationException, IllegalAccessException, IOException {
		WrapperFilterRequest<SegnalazioneFilter> wrappedFilter = WrapperFilterUtility.extractWrapperFromCodedString(requestFilterStringCoded, SegnalazioneFilter.class);
		log.info("Invocato metodo getListaPaginataSegnalazioni con filtro: " + wrappedFilter.getFilter().toString());
		WrapperFilterResponse<?> response = segnalazioniService.getListaPaginata(wrappedFilter, dmsClientStub.getUserFromRequest(request).getUtenteId());
		return new ResponseEntity<WrapperFilterResponse<?>>(response, HttpStatus.OK);
	}
}
