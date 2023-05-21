package it.indra.SigeaWeb.controller;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpServerErrorException;

import com.indra.es.utils.LogUtility;
import com.indra.es.utils.auth.custom.DMSCustomAuthentication;
import com.indra.es.utils.exceptions.RestException;
import com.indra.es.utils.model.WSO2Token;
import com.indra.es.utils.model.security.RuoliAssociati;

import it.indra.SigeaWeb.service.AuthService;
import it.indra.SigeaWeb.service.DMSService;
import it.indra.SigeaWeb.service.TerritorialService;
import it.puglia.spc.ect.commons.dms.dto.utenti.UserRolesDTO;
import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class LoginController {

	@Autowired
	private AuthService authService;

	@Autowired
	private TerritorialService territorialService;

	@Autowired
	private DMSService dmsService;

	/**
	 * Metodo per effettuare la login
	 * @param request
	 * @param response
	 * @param serviceCode uno tra segnalazione(Cittadino), promozione(Impresa o registrazione del DMS), gestione(Ente pubblico abilitato)
	 * @param idEntita
	 * @param idRichiesta
	 * @param idEvento
	 * @return
	 */
	@RequestMapping(value = "/{serviceCode}/accedi", method = RequestMethod.GET)
	public ResponseEntity<String> getAccess(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String serviceCode, @RequestParam(required = false) Long idEntita,
			@RequestParam(required = false) Long idRichiesta, @RequestParam(required = false) Long idEvento) {

		HttpHeaders headers = new HttpHeaders();

		request.getSession().setAttribute("serviceCode", serviceCode);
		request.getSession().setAttribute("idEntita", idEntita);
		request.getSession().setAttribute("idRichiesta", idRichiesta);
		request.getSession().setAttribute("idEvento", idEvento);

		headers.add("Location", "../login");
		return new ResponseEntity<String>(headers, HttpStatus.FOUND);

	}

	@RequestMapping(value = "/oidc/callback", method = RequestMethod.GET)
	public ResponseEntity<String> auth(HttpServletRequest request) throws URISyntaxException, RestException {

		try {

			authService.acquireToken(request);

			HttpHeaders headers = new HttpHeaders();
			headers.add("Location", "../doLogin");

			return new ResponseEntity<String>(headers, HttpStatus.FOUND);
		} catch (Exception e) {

			log.error(LogUtility.exceptionToLog(e));

			HttpHeaders headers = new HttpHeaders();
			headers.add("Location", "../accessonegato");

			return new ResponseEntity<String>(headers, HttpStatus.FOUND);
		}
	}

	@RequestMapping(value = "/doLogin", method = RequestMethod.GET)
	public ResponseEntity<String> doLogin(HttpServletRequest request) throws Exception {

		SecurityContext sc = SecurityContextHolder.getContext();
		boolean isAuth = (sc != null && sc.getAuthentication() != null && sc.getAuthentication().isAuthenticated())
				&& sc.getAuthentication().isAuthenticated();

		String serviceCode = (String) request.getSession().getAttribute("serviceCode");
		Long idEntita = (Long) request.getSession().getAttribute("idEntita");
		Long idRichiesta = (Long) request.getSession().getAttribute("idRichiesta");
//		Long idEvento = (Long) request.getSession().getAttribute("idEvento");

		if (isAuth) {
			
			DMSCustomAuthentication auth = new DMSCustomAuthentication();
			HttpServletRequest servletRequest = (HttpServletRequest) request;
			WSO2Token wso2token = null;

			try {
				wso2token = (WSO2Token) servletRequest.getSession().getAttribute("WSO2Token");
			} catch (Exception e) {
				log.error(LogUtility.exceptionToLog(e));
			}

			if (wso2token == null) {
				log.error("Token utente non presente in sessione");
				HttpHeaders headers = new HttpHeaders();
				headers.add("Location", "./accessonegato");
				return new ResponseEntity<String>(headers, HttpStatus.FOUND);
			}else {
				log.info("Loggando utente [token:"+wso2token.getAccessToken()+", idEntita:"+idEntita+", idRichiesta:"+idRichiesta+", serviceCode:"+serviceCode+"]");
			}

			String accessToken = wso2token != null && wso2token.getAccessToken() != null ? wso2token.getAccessToken()
					: "";

			// CONTROLLI SIGEA
			boolean allow = true;

			try {

				String denominazione = null;
				String entityType = null;
				if (idEntita != null) {
					if (serviceCode.equals("promozione")) {
						try {
							denominazione = dmsService.getDenominazioneAttivita(wso2token, idEntita);
							entityType = "impresa";
						} catch (Exception e) {
							denominazione = dmsService.getDenominazioneEntita(wso2token, idEntita);
							entityType = "ente";
						}
						
					} else {
						denominazione = dmsService.getDenominazioneEntita(wso2token, idEntita);
						entityType = "ente";
					}
				} else if (idRichiesta != null) {
					denominazione = dmsService.getDenominazioneAttivitaRichiesta(wso2token, idRichiesta);
					//TODO settare entityType per ritorno diretto a richiesta registrazione
				}
				
				request.getSession().setAttribute("denominazione", denominazione);
				request.getSession().setAttribute("entityType", entityType);

			} catch (Exception e) {
				allow = false;
				log.error("IMPOSSIBILE ricavare la denominazione dell'entita/attivita/richiesta dal DMS");
			}

			// Recupero dati territoriali
			// TODO spostare?
			try {
				territorialService.getTerritorialData(wso2token);
			} catch (Exception e) {
				allow = false;
				log.error("IMPOSSIBILE recuperare i dati territoriali");
			}

			if (allow) {

				List<String> roles = null;
				UserRolesDTO userPermissions = null;
				com.indra.es.utils.model.security.SecurityContext userEntityPermissions = null;

				// TODO controllo su associazione richiesta
				if (idEntita != null) {
					userEntityPermissions = authService.getUserEntityPermissions(wso2token, idEntita);
					String idEntitaString = "" + idEntita;

					RuoliAssociati ruoliAssociati = userEntityPermissions.getRuoliAssociati().stream()
							.filter(x -> x.getAssociazione().equals(idEntitaString)).findFirst().orElse(null);

					roles = ruoliAssociati == null ? null : ruoliAssociati.getRuoli();
					
					log.info("Dati utente[idEntita:"+idEntita+", token:"+wso2token.getAccessToken()+", ruoli:"+roles.toString()+"]");
					
					if (roles == null || !roles.contains("ROLE_SIGEA_ACCESSO")) {
						throw new HttpServerErrorException(HttpStatus.UNAUTHORIZED);
					}
				} else {
					userPermissions = authService.getUserPermissions(wso2token);
					roles = userPermissions.getRoles();

					log.info("Dati utente[idEntita:null, token:"+wso2token.getAccessToken()+", ruoli:"+roles.toString()+"]");
					
					if (roles == null || !roles.contains("ROLE_SIGEA_ACCESSO")) {
						throw new HttpServerErrorException(HttpStatus.UNAUTHORIZED);
					}					
				} 

				List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

				// Add roles to the authentication
				for (int i = 0; i < roles.size(); i++) {
					grantedAuthorities.add(new SimpleGrantedAuthority(roles.get(i)));
				}

				auth.setRoles(grantedAuthorities);
				auth.setWso2AccessToken(accessToken);

				SecurityContextHolder.getContext().setAuthentication(auth);
			} else {
				HttpHeaders headers = new HttpHeaders();
				headers.add("Location", "./accessonegato");
				return new ResponseEntity<String>(headers, HttpStatus.FOUND);
			}

		} else {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Location", "./accessonegato");
			return new ResponseEntity<String>(headers, HttpStatus.FOUND);
		}

		HttpHeaders headers = new HttpHeaders();

		if (serviceCode.equals("promozione")) {
			headers.add("Location", "./promozione");
			return new ResponseEntity<String>(headers, HttpStatus.FOUND);
		} else if (serviceCode.equals("gestione")) {
			headers.add("Location", "./gestione/home");
			return new ResponseEntity<String>(headers, HttpStatus.FOUND);
		} else if (serviceCode.equals("segnalazione")) {
			headers.add("Location", "./segnalazione");
			return new ResponseEntity<String>(headers, HttpStatus.FOUND);
		} else {
			headers.add("Location", "./accessonegato");
			return new ResponseEntity<String>(headers, HttpStatus.FOUND);
		}

	}
}
