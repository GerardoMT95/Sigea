package it.indra.SigeaWeb.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.indra.es.utils.exceptions.RestException;
import com.indra.es.utils.model.WSO2Token;

import it.indra.SigeaCommons.model.AttivitaModel;
import it.indra.SigeaCommons.model.BandoModel;
import it.indra.SigeaCommons.model.BandoModelList;
import it.indra.SigeaCommons.model.DettaglioUtenteModel;
import it.indra.SigeaCommons.model.EventoModel;
import it.indra.SigeaCommons.model.ProgettoModel;
import it.indra.SigeaCommons.model.RichiestaAttivitaModel;
import it.indra.SigeaCommons.model.TipologiaModel;
import it.indra.SigeaWeb.service.BandiService;
import it.indra.SigeaWeb.service.DMSService;
import it.indra.SigeaWeb.service.DecodificaService;
import it.indra.SigeaWeb.service.EventoService;
import it.indra.SigeaWeb.service.OntologiaService;
import it.indra.SigeaWeb.service.TerritorialService;
import it.indra.SigeaWeb.service.UserService;
import it.indra.SigeaWeb.service.VIPService;
import it.indra.SigeaWeb.utilities.MessagePropertiesUtility;
import it.indra.SigeaWeb.utilities.SigeaPropertiesSettings;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping(value = "/gestione")
public class GestioneController {

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
	private BandiService bandoService;
	

	@Autowired
	private VIPService vipService;
	
	@Autowired
	private DMSService dmsService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView homePage(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes attributes) throws RestException {

		log.info("Entrato metodo homePage");

		WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");

//		DatabaseMessageSource messageData = (DatabaseMessageSource) messageSource;
//		messageData.setActivate(false);
//		messageData.clearCache();

		// prelevo dalla sessione le informazioni ottenute in ingresso

		Long idEntita = (Long) request.getSession().getAttribute("idEntita");
		Long idRichiesta = (Long) request.getSession().getAttribute("idRichiesta");
		Long idEvento = (Long) request.getSession().getAttribute("idEvento");
		String denominazione = (String) request.getSession().getAttribute("denominazione");
		String entityType = (String) request.getSession().getAttribute("entityType");

		String tipo = userService.findHighestRole(request);
		
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = dateFormat.format(date);
		model.addAttribute("datadioggi", formattedDate);

		// profilo immediatamente l'utente
		DettaglioUtenteModel user = userService.saveUtente(token, tipo, idEntita);
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
		model.addAttribute("areaRiservata",
				sigeaPropertiesSettings.getUrlDms() + entityType + "/accedi?idEntita=" + idEntita);
		model.addAttribute("linkDms", sigeaPropertiesSettings.getUrlDms());
		model.addAttribute("denominazione", denominazione);

		try {
			model.addAttribute("idEntita", idEntita);
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
			model.addAttribute("codicefiscale", user.getCodFiscale());
			model.addAttribute("username", user.getUsername());
			model.addAttribute("redazioni", objectMapper.writeValueAsString(userService.getRedazioni(token)));
			model.addAttribute("redazioniUtente",
					objectMapper.writeValueAsString(user.getTipologia().getRedazioniSet()));
		} catch (JsonProcessingException e) {
			log.error(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		model.addAttribute("myVariable", "home.jsp");

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

	@RequestMapping(value = "/validazione", method = RequestMethod.GET)
	public ModelAndView validazionePage(Locale locale, Model model, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes attributes) throws RestException {

		log.info("Entrato metodo validazionePage");

		WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");

//		DatabaseMessageSource messageData = (DatabaseMessageSource) messageSource;
//		messageData.setActivate(false);
//		messageData.clearCache();

		// prelevo dalla sessione le informazioni ottenute in ingresso
		String tipo = "REDVAL-PP";
		Long idEntita = (Long) request.getSession().getAttribute("idEntita");
		Long idRichiesta = (Long) request.getSession().getAttribute("idRichiesta");
		Long idEvento = (Long) request.getSession().getAttribute("idEvento");
		String denominazione = (String) request.getSession().getAttribute("denominazione");
		String entityType = (String) request.getSession().getAttribute("entityType");

		// profilo immediatamente l'utente
		DettaglioUtenteModel user = userService.saveUtente(token, tipo, idEntita);
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
		model.addAttribute("areaRiservata",
				sigeaPropertiesSettings.getUrlDms() + entityType + "/accedi?idEntita=" + idEntita);
		model.addAttribute("linkDms", sigeaPropertiesSettings.getUrlDms());	
		model.addAttribute("denominazione", denominazione);
		
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = dateFormat.format(date);
		model.addAttribute("datadioggi", formattedDate);

		try {
			model.addAttribute("idEntita", idEntita);
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
			model.addAttribute("codicefiscale", user.getCodFiscale());
			model.addAttribute("username", user.getUsername());
			model.addAttribute("redazioni", objectMapper.writeValueAsString(userService.getRedazioni(token)));
			model.addAttribute("redazioniUtente",
					objectMapper.writeValueAsString(user.getTipologia().getRedazioniSet()));
		} catch (JsonProcessingException e) {
			log.error(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		model.addAttribute("myVariable", "filtriGestioneValidazione.jsp");

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

	@RequestMapping(value = "/promozione", method = RequestMethod.GET)
	public ModelAndView promozionePage(Locale locale, Model model, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes attributes) throws RestException {

		log.info("Entrato metodo promozionePage per ente");

		WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");

//		DatabaseMessageSource messageData = (DatabaseMessageSource) messageSource;
//		messageData.setActivate(false);
//		messageData.clearCache();
		String statoStruttura="";
		String activityObj="";
		String cfTitolare="";
		String cfPiva="";

		// prelevo dalla sessione le informazioni ottenute in ingresso
		String tipo = userService.setRolePromozione(request);
		Long idEntita = (Long) request.getSession().getAttribute("idEntita");
		Long idRichiesta = (Long) request.getSession().getAttribute("idRichiesta");
		Long idEvento = (Long) request.getSession().getAttribute("idEvento");
		String denominazione = (String) request.getSession().getAttribute("denominazione");
		String entityType = (String) request.getSession().getAttribute("entityType");
		
		if (idEntita!=null) {
			statoStruttura="VALIDATO";
		}else {
			statoStruttura="IN VALIDAZIONE";
		}
		
		Long idEntitaCorrente = idEntita != null ? idEntita : idRichiesta;
		

		try {
			if (!entityType.equalsIgnoreCase("ente")){
				activityObj = dmsService.getActivityDTO(token, idEntitaCorrente);
				JSONObject obj = new JSONObject(activityObj);
				if(obj.getJSONObject("datiAttivita").has("cfPiva")) {
					cfPiva = obj.getJSONObject("datiAttivita").getString("cfPiva");
				}
				cfTitolare = obj.getJSONObject("datititolare").getString("cfTitolare");
			
			}
		} catch (Exception e) {
			log.error("IMPOSSIBILE RECUPERARE DATI SU CF E/O PI, potrebbe trattarsi di un ente con idAttivita " + idEntitaCorrente);
		}


		// profilo immediatamente l'utente
		DettaglioUtenteModel user = userService.saveUtente(token, tipo, idEntita);
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
		model.addAttribute("areaRiservata",
				sigeaPropertiesSettings.getUrlDms() + entityType + "/accedi?idEntita=" + idEntita);
		model.addAttribute("linkDms", sigeaPropertiesSettings.getUrlDms());	
		model.addAttribute("denominazione", denominazione);
		model.addAttribute("entityType", entityType);
		
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = dateFormat.format(date);
		model.addAttribute("datadioggi", formattedDate);

		try {
			model.addAttribute("idEntita", idEntita);
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
			model.addAttribute("statoStruttura", statoStruttura);
			model.addAttribute("cognome", user.getCognome());

			model.addAttribute("username", user.getUsername());
			model.addAttribute("redazioni", objectMapper.writeValueAsString(userService.getRedazioni(token)));
			model.addAttribute("redazioniUtente",
					objectMapper.writeValueAsString(user.getTipologia().getRedazioniSet()));
			
			model.addAttribute("cfPiva", cfPiva);
			model.addAttribute("cfTitolare", cfTitolare);
			
		} catch (JsonProcessingException e) {
			log.error(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		model.addAttribute("myVariable", "filtriGestionePromozione.jsp");

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
	
	@RequestMapping(value = "/ricevute", method = RequestMethod.GET)
	public ModelAndView ricevutePage(Locale locale, Model model, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes attributes) throws RestException {

		log.info("Entrato metodo ricevutePage");

		WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");

//		DatabaseMessageSource messageData = (DatabaseMessageSource) messageSource;
//		messageData.setActivate(false);
//		messageData.clearCache();


		// prelevo dalla sessione le informazioni ottenute in ingresso
		String tipo = userService.setRoleRicevute(request);
		
		Long idEntita = (Long) request.getSession().getAttribute("idEntita");
		Long idRichiesta = (Long) request.getSession().getAttribute("idRichiesta");
		Long idEvento = (Long) request.getSession().getAttribute("idEvento");
		String denominazione = (String) request.getSession().getAttribute("denominazione");
		String entityType = (String) request.getSession().getAttribute("entityType");

		// profilo immediatamente l'utente
		DettaglioUtenteModel user = userService.saveUtente(token, tipo, idEntita);
		TipologiaModel tipologiaModel = user.getTipologia();

		Long idAttivita = (tipo.equalsIgnoreCase("RIC-PP")) ? null : (idEntita!=null) ? idEntita : idRichiesta;
		
		
		//START LOGIC BANDI E PROGETTI SELECT
		Set<BandoModelList> bandoModelListSet = bandoService.getBandiProgetti(token,idAttivita);
		Set<BandoModel> bandoModelSet=new HashSet<BandoModel>();
		Set<ProgettoModel> progettoModelSet=new HashSet<ProgettoModel>();
		Iterator<BandoModelList> value = bandoModelListSet.iterator();

		while (value.hasNext()) {
			BandoModelList bandoModelList =value.next();
			BandoModel bandoModel = new BandoModel();
			ProgettoModel progettoModel = new ProgettoModel();

			bandoModel.setBandoId(bandoModelList.getBandoId());
			bandoModel.setTitoloBando(bandoModelList.getTitoloBando());
			bandoModelSet.add(bandoModel);
			progettoModel.setProgettoId(bandoModelList.getProgettoId());
			progettoModel.setTitoloProgetto(bandoModelList.getTitoloProgetto());
			progettoModel.setBandoId(bandoModelList.getBandoId());
			progettoModelSet.add(progettoModel);
		}
		//END LOGIC BANDI E PROGETTI SELECT
		
		
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
		model.addAttribute("areaRiservata",
				sigeaPropertiesSettings.getUrlDms() + entityType + "/accedi?idEntita=" + idEntita);
		model.addAttribute("linkDms", sigeaPropertiesSettings.getUrlDms());	
		model.addAttribute("denominazione", denominazione);

		try {
			model.addAttribute("idEntita", idEntita);
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
			model.addAttribute("bandiList",objectMapper.writeValueAsString(bandoModelSet).replace("'", "\\'"));
			model.addAttribute("progettiList",objectMapper.writeValueAsString( progettoModelSet).replace("'", "\\'"));
		} catch (JsonProcessingException e) {
			log.error(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		model.addAttribute("myVariable", "ricevute.jsp");

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
	
}
