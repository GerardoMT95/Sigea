package it.indra.SigeaWeb.controller;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.indra.es.utils.exceptions.RestException;
import com.indra.es.utils.model.WSO2Token;

import it.indra.SigeaCommons.model.AttivitaModel;
import it.indra.SigeaCommons.model.DettaglioUtenteModel;
import it.indra.SigeaCommons.model.EventoModel;
import it.indra.SigeaCommons.model.RichiestaAttivitaModel;
import it.indra.SigeaCommons.model.TipologiaModel;
import it.indra.SigeaWeb.service.DMSService;
import it.indra.SigeaWeb.service.DecodificaService;
import it.indra.SigeaWeb.service.EventoService;
import it.indra.SigeaWeb.service.OntologiaService;
import it.indra.SigeaWeb.service.TerritorialService;
import it.indra.SigeaWeb.service.UserService;
import it.indra.SigeaWeb.service.VIPService;
import it.indra.SigeaWeb.utilities.GeneralProperties;
import it.indra.SigeaWeb.utilities.MessagePropertiesUtility;
import lombok.extern.slf4j.Slf4j;
@Controller
@Slf4j
public class HomeController {
	
	@Autowired
	GeneralProperties properties;
	
	@Autowired
	MessageSource messageSource; 
	
	@Autowired
	EventoService eventoService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	TerritorialService territorialService;
	
	@Autowired
	DecodificaService decodificaService;
	
	@Autowired
	OntologiaService ontologiaService;
	
	@Autowired
	DMSService dmsService;
	
	@Autowired
	VIPService vipService;
	
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView startIndexOld(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes) throws RestException {

		log.info("Entrato metodo startIndex");
		
		WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");
		
//		DatabaseMessageSource messageData = (DatabaseMessageSource) messageSource;
//		messageData.setActivate(false);
//		messageData.clearCache();
		
		//prelevo dalla sessione le informazioni ottenute in ingresso
		String tipo =  (String) request.getSession().getAttribute("userType");
		Long idEntita =  (Long) request.getSession().getAttribute("idEntita");
		Long idRichiesta =  (Long) request.getSession().getAttribute("idRichiesta");
		Long idEvento =  (Long) request.getSession().getAttribute("idEvento");
		String denominazione =  (String) request.getSession().getAttribute("denominazione");
		
		//profilo immediatamente l'utente
		DettaglioUtenteModel user = userService.saveUtente(token, tipo, idEntita);
		TipologiaModel tipologiaModel = user.getTipologia();
		
		//Check su id evento
		if(idEvento!=null && idEvento != -1L && tipo.equals("PROMUOVI_EVENTO")) {
			try {
				EventoModel eventoIngresso = eventoService.getEvento(token, idEvento).getBody();
				if(eventoIngresso.getOwnerId().longValue()!=user.getUtenteId().longValue()) {
					log.error("L'EVENTO NON APPARTIENE AL CHIAMANTE");
					return new ModelAndView("accessonegato", new HashMap<String,String>(), HttpStatus.FORBIDDEN);
				}
			}catch(Exception e) {
				log.error("IMPOSSIBILE RECUPERARE EVENTO " + idEvento);
				return new ModelAndView("accessonegato", new HashMap<String,String>(), HttpStatus.NOT_FOUND);
			}
		}
		
		//creo attivita
		AttivitaModel attivitaModel = new AttivitaModel();
		RichiestaAttivitaModel richiestaAttivitaModel = new RichiestaAttivitaModel();
		if(idEntita!=null) {
			attivitaModel.setAttivitaId(idEntita);
			attivitaModel.setDenominazione(denominazione);
		}else if(idRichiesta!=null){
			richiestaAttivitaModel.setRichiestaAttivitaId(new Long(idRichiesta));
			richiestaAttivitaModel.setDenominazione(denominazione);
		}

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			model.addAttribute("flagPrm",objectMapper.writeValueAsString(tipologiaModel.getPermessiSommati()));
		} catch (IOException e) {
			log.error(e.getMessage());
			response.setStatus( HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} 
		model.addAttribute("mexMap", MessagePropertiesUtility.getPropertiesMap());
		model.addAttribute("areaRiservata", properties.getLogin());
		model.addAttribute("linkDms", properties.getUrldms());
		
		try {
			model.addAttribute("tipoUtente", tipologiaModel.getTipologiaId());
			model.addAttribute("titoloUtente", tipologiaModel.getDescrizione());
			model.addAttribute("attivita",objectMapper.writeValueAsString(attivitaModel)); 
			model.addAttribute("attivitaRichiesta",objectMapper.writeValueAsString(richiestaAttivitaModel));
			model.addAttribute("statiEvento", objectMapper.writeValueAsString(eventoService.getStatiEvento(token)));
			model.addAttribute("mezzi", objectMapper.writeValueAsString(ontologiaService.getMezzi(token)));
			model.addAttribute("prodotti", objectMapper.writeValueAsString(ontologiaService.getProdotti(token)));
			model.addAttribute("tipologieMIBACT", objectMapper.writeValueAsString(ontologiaService.getTipologieMIBACT(token)));
			model.addAttribute("tipologieAttivita", objectMapper.writeValueAsString(ontologiaService.getTipologieAttivita(token)));
			model.addAttribute("valoriAttrattivita", objectMapper.writeValueAsString(ontologiaService.getValoriAttrattivita(token)));
			model.addAttribute("infopoint", objectMapper.writeValueAsString(vipService.getAllInfoPoint()).replace("'", "\\'"));
			model.addAttribute("idUtenteCorrente", user.getUtenteId());
			model.addAttribute("nome", user.getNome());
			model.addAttribute("cognome", user.getCognome());
			model.addAttribute("username", user.getUsername());
			model.addAttribute("redazioni", objectMapper.writeValueAsString(userService.getRedazioni(token)));
			model.addAttribute("redazioniUtente", objectMapper.writeValueAsString(user.getTipologia().getRedazioniSet()));
		} catch (JsonProcessingException e) {
			log.error(e.getMessage());
			response.setStatus( HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		if(tipo.equals("SEGNALA_EVENTO")) {
			model.addAttribute("myVariable","segnalazione.jsp");
		}else{
			if (idEvento!=null) {
				model.addAttribute("myVariable",idEvento);
			}else {
				model.addAttribute("myVariable","filtriEvento.jsp");
			}
		}

		model.addAttribute("italia",territorialService.getItalia());
		model.addAttribute("puglia",territorialService.getPuglia());
		model.addAttribute("nazioniList",territorialService.getNazioniList().toString().replace("'", "\\'"));
		model.addAttribute("regioniList",territorialService.getRegioniList().toString().replace("'", "\\'"));
		model.addAttribute("areeList",territorialService.getAreeList().toString().replace("'", "\\'"));
		model.addAttribute("provinceList",territorialService.getProvinceList().toString().replace("'", "\\'"));
		model.addAttribute("comuniList",territorialService.getComuniList().toString().replace("'", "\\'"));
		model.addAttribute("provincePerRegione",territorialService.getProvincePerRegione().toString().replace("'", "\\'"));
		model.addAttribute("provincePerArea",territorialService.getProvincePerArea().toString().replace("'", "\\'"));
		model.addAttribute("comuniPerRegione",territorialService.getComuniPerRegione().toString().replace("'", "\\'"));
		model.addAttribute("comuniPerArea",territorialService.getComuniPerArea().toString().replace("'", "\\'"));
		model.addAttribute("comuniPerProvincia",territorialService.getComuniPerProvincia().toString().replace("'", "\\'"));

		decodificaService.cleanDecodifiche();
		
		return new ModelAndView( "main" );
	}
	
	@RequestMapping(value = "/filtriEvento", method = RequestMethod.GET)
	public ModelAndView filtriEvento(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes) {
		return new ModelAndView( "filtriEvento" );
	}
	
	@RequestMapping(value = "/creazioneEvento", method = RequestMethod.GET)
	public ModelAndView creazioneEvento(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes/*, @RequestParam(value = "serviceCode", required = false) String serviceCode*/) throws RestException {
		WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");
		
		model.addAttribute("redazioniSet", userService.getRedazioni(token));
		model.addAttribute("statiSet", eventoService.getStatiEvento(token));
		
//		if(StringUtils.equals("PROM", serviceCode)) {
//			return new ModelAndView( "creazioneEventoPromozione" );
//		}else {
		return new ModelAndView( "creazioneEvento" );
//		}
		
	}

	@RequestMapping(value = "/vipScheda/{eventoId}", method = RequestMethod.GET)
	public ModelAndView vipScheda(@PathVariable Long eventoId, Locale locale, Model model, HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes, @RequestParam (required = false) String statoNucleo) {
		ModelAndView MV = new ModelAndView( "vipScheda" );
		MV.getModel().put("eventoId", eventoId);
		if(statoNucleo==null)
		{
			statoNucleo ="";
		}
		MV.getModel().put("statoNucleo", statoNucleo);
		return MV;
	}
	
	@RequestMapping(value = "/b2bScheda/{eventoId}", method = RequestMethod.GET)
	public ModelAndView b2bScheda(@PathVariable Long eventoId, Locale locale, Model model, HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes) {
		ModelAndView MV = new ModelAndView( "b2bScheda" );
		MV.getModel().put("eventoId", eventoId);
		return MV;
	}
	
	@RequestMapping(value = "/b2bdmsScheda/{eventoId}", method = RequestMethod.GET)
	public ModelAndView b2bdmsScheda(@PathVariable Long eventoId, Locale locale, Model model, HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes) {
		ModelAndView MV = new ModelAndView( "b2bdmsScheda" );
		MV.getModel().put("eventoId", eventoId);
		return MV;
	}
	
	@RequestMapping(value = "/accessonegato", method = RequestMethod.GET)
	public ModelAndView accessoNegato(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes) throws RestException {
		
		model.addAttribute("mexMap", MessagePropertiesUtility.getPropertiesMap());
		model.addAttribute("linkDms", properties.getUrldms());
		
		return new ModelAndView( "base/accessonegato" );
	}
	
}
