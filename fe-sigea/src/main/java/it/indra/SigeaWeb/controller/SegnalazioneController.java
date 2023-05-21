package it.indra.SigeaWeb.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.indra.es.utils.LogUtility;
import com.indra.es.utils.exceptions.RestException;
import com.indra.es.utils.model.WSO2Token;

import it.indra.SigeaCommons.model.AttivitaModel;
import it.indra.SigeaCommons.model.DettaglioUtenteModel;
import it.indra.SigeaCommons.model.EventoModel;
import it.indra.SigeaCommons.model.RichiestaAttivitaModel;
import it.indra.SigeaCommons.model.SegnalazioneFilter;
import it.indra.SigeaCommons.model.SegnalazioneModel;
import it.indra.SigeaCommons.model.SegnalazioneModelList;
import it.indra.SigeaCommons.model.SegnalazioneStatoPatchModel;
import it.indra.SigeaCommons.model.TipologiaModel;
import it.indra.SigeaWeb.service.DecodificaService;
import it.indra.SigeaWeb.service.EventoService;
import it.indra.SigeaWeb.service.OntologiaService;
import it.indra.SigeaWeb.service.SegnalazioneService;
import it.indra.SigeaWeb.service.TerritorialService;
import it.indra.SigeaWeb.service.UserService;
import it.indra.SigeaWeb.service.VIPService;
import it.indra.SigeaWeb.utilities.MessagePropertiesUtility;
import it.indra.SigeaWeb.utilities.SigeaPropertiesSettings;
import it.indra.SigeaWeb.utilities.WrapperFilterRequest;
import it.indra.SigeaWeb.utilities.WrapperFilterResponse;
import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class SegnalazioneController {
	
	@Autowired
	private SigeaPropertiesSettings sigeaPropertiesSettings;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private EventoService eventoService;

	@Autowired
	private UserService userService;

	@Autowired
	private TerritorialService territorialService;

	@Autowired
	private DecodificaService decodificaService;

	@Autowired
	private OntologiaService ontologiaService;

	@Autowired
	private VIPService vipService;
	
	@Autowired
	private SegnalazioneService segnalazioneService;

	@RequestMapping(value = "/segnalazione", method = RequestMethod.GET)
	public ModelAndView segnalazionePage(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes attributes) throws RestException {

		log.info("Entrato metodo startIndex");

		WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");

//		DatabaseMessageSource messageData = (DatabaseMessageSource) messageSource;
//		messageData.setActivate(false);
//		messageData.clearCache();

		// prelevo dalla sessione le informazioni ottenute in ingresso
		String tipo = "SEGNALA_EVENTO";
		Long idEntita = (Long) request.getSession().getAttribute("idEntita");
		Long idRichiesta = (Long) request.getSession().getAttribute("idRichiesta");
		Long idEvento = (Long) request.getSession().getAttribute("idEvento");
		String denominazione = "AREA PERSONALE";
		
		java.util.Date date = new java.util.Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("datadioggi", formattedDate);

		// profilo immediatamente l'utente
		DettaglioUtenteModel user = userService.saveUtente(token, tipo, null);
		TipologiaModel tipologiaModel = user.getTipologia();

		// Check su id evento
		if (idEvento != null && idEvento != -1L && tipo.equals("PROMUOVI_EVENTO")) {
			try {
				EventoModel eventoIngresso = eventoService.getEvento(token, idEvento).getBody();
				if (eventoIngresso.getOwnerId().longValue() != user.getUtenteId().longValue()) {
					log.error("L'EVENTO NON APPARTIENE AL CHIAMANTE");
					return new ModelAndView("accessonegato", new HashMap<String, String>(), HttpStatus.FORBIDDEN);
				}
			} catch (Exception e) {
				log.error("IMPOSSIBILE RECUPERARE EVENTO " + idEvento);
				return new ModelAndView("accessonegato", new HashMap<String, String>(), HttpStatus.NOT_FOUND);
			}
		}

		// creo attivita
		AttivitaModel attivitaModel = new AttivitaModel();
		RichiestaAttivitaModel richiestaAttivitaModel = new RichiestaAttivitaModel();
		if (idEntita != null) {
			attivitaModel.setAttivitaId(idEntita);
			attivitaModel.setDenominazione(denominazione);
		} else if (idRichiesta != null) {
			richiestaAttivitaModel.setRichiestaAttivitaId(new Long(idRichiesta));
			richiestaAttivitaModel.setDenominazione(denominazione);
		}

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			model.addAttribute("flagPrm", objectMapper.writeValueAsString(tipologiaModel.getPermessiSommati()));
		} catch (IOException e) {
			log.error(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		model.addAttribute("mexMap", MessagePropertiesUtility.getPropertiesMap());
		model.addAttribute("areaRiservata", sigeaPropertiesSettings.getLogin());
		model.addAttribute("linkDms", sigeaPropertiesSettings.getUrlDms());
		model.addAttribute("denominazione", denominazione);

		try {
			model.addAttribute("tipoUtente", tipologiaModel.getTipologiaId());
			model.addAttribute("titoloUtente", tipologiaModel.getDescrizione());
			model.addAttribute("attivita", objectMapper.writeValueAsString(attivitaModel));
			model.addAttribute("attivitaRichiesta", objectMapper.writeValueAsString(richiestaAttivitaModel));
			model.addAttribute("statiEvento", objectMapper.writeValueAsString(eventoService.getStatiEvento(token)));
			model.addAttribute("mezzi", objectMapper.writeValueAsString(ontologiaService.getMezzi(token)));
			model.addAttribute("prodotti", objectMapper.writeValueAsString(ontologiaService.getProdotti(token)));
			model.addAttribute("tipologieMIBACT",
					objectMapper.writeValueAsString(ontologiaService.getTipologieMIBACT(token)));
			model.addAttribute("tipologieAttivita",
					objectMapper.writeValueAsString(ontologiaService.getTipologieAttivita(token)));
			model.addAttribute("valoriAttrattivita",
					objectMapper.writeValueAsString(ontologiaService.getValoriAttrattivita(token)));
			model.addAttribute("infopoint",
					objectMapper.writeValueAsString(vipService.getAllInfoPoint()).replace("'", "\\'"));
			model.addAttribute("idUtenteCorrente", user.getUtenteId());
			model.addAttribute("nome", user.getNome());
			model.addAttribute("cognome", user.getCognome());
			model.addAttribute("username", user.getUsername());
			model.addAttribute("redazioni", objectMapper.writeValueAsString(userService.getRedazioni(token)));
			model.addAttribute("redazioniUtente",
					objectMapper.writeValueAsString(user.getTipologia().getRedazioniSet()));
		} catch (JsonProcessingException e) {
			log.error(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
//		if (tipo.equals("SEGNALA_EVENTO")) {
//			model.addAttribute("myVariable", "segnalazione.jsp");
//		} else {
//			if (idEvento != null) {
//				model.addAttribute("myVariable", idEvento);
//			} else {
//				model.addAttribute("myVariable", "filtriEvento.jsp");
//			}
//		}

		model.addAttribute("myVariable", "segnalazione.jsp");
		
		model.addAttribute("italia", territorialService.getItalia());
		model.addAttribute("puglia", territorialService.getPuglia());
		model.addAttribute("nazioniList", territorialService.getNazioniList().toString().replace("'", "\\'"));
		model.addAttribute("regioniList", territorialService.getRegioniList().toString().replace("'", "\\'"));
		model.addAttribute("areeList", territorialService.getAreeList().toString().replace("'", "\\'"));
		model.addAttribute("provinceList", territorialService.getProvinceList().toString().replace("'", "\\'"));
		model.addAttribute("comuniList", territorialService.getComuniList().toString().replace("'", "\\'"));
		model.addAttribute("provincePerRegione",
				territorialService.getProvincePerRegione().toString().replace("'", "\\'"));
		model.addAttribute("provincePerArea", territorialService.getProvincePerArea().toString().replace("'", "\\'"));
		model.addAttribute("comuniPerRegione", territorialService.getComuniPerRegione().toString().replace("'", "\\'"));
		model.addAttribute("comuniPerArea", territorialService.getComuniPerArea().toString().replace("'", "\\'"));
		model.addAttribute("comuniPerProvincia",
				territorialService.getComuniPerProvincia().toString().replace("'", "\\'"));

		decodificaService.cleanDecodifiche();

		return new ModelAndView("main");
	}
	
	@RequestMapping(value = "/segnPageList", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> getListaPaginataSegnalazioni(HttpServletRequest request, HttpServletResponse response,
			@RequestBody WrapperFilterRequest<SegnalazioneFilter> dataTableRequest) {

		try {
			WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");

			WrapperFilterResponse<SegnalazioneModelList> result = segnalazioneService
					.getListaPaginataSegnalazioni(token, dataTableRequest);

			return new ResponseEntity<WrapperFilterResponse<SegnalazioneModelList>>(result, HttpStatus.OK);
		} catch (Exception e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/aggiungiSegnalazione", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> postSegnalazione(HttpServletRequest request, HttpServletResponse response,
			@RequestBody SegnalazioneModel model) {

		try {
			WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");

			String result = segnalazioneService.postSegnalazione(token, model);

			return new ResponseEntity<String>(result, HttpStatus.OK);
		} catch (Exception e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/listSegnalazione", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getListaTitoliSegnalazioniByDataComune(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Date dataDa, @RequestParam String codIstat) {

		try {
			WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");

			List<String> result = segnalazioneService.getListaTitoliSegnalazioniByDataComune(token, dataDa, codIstat);

			return new ResponseEntity<List<String>>(result, HttpStatus.OK);
		} catch (Exception e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/cambiaStato", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<?> patchStato(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Long segnalazioneId, @RequestBody SegnalazioneStatoPatchModel segnalazioneStatusPatchModel) {

		try {
			WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");

			String result = segnalazioneService.patchStato(token, segnalazioneId, segnalazioneStatusPatchModel);

			return new ResponseEntity<String>(result, HttpStatus.OK);
		} catch (Exception e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getSegnalazione", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getSegnalazione(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Long segnalazioneId) {

		try {
			WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");

			String result = segnalazioneService.getSegnalazione(token, segnalazioneId);

			return new ResponseEntity<String>(result , HttpStatus.OK);
		} catch (Exception e) {

			log.error(LogUtility.exceptionToLog(e));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
