package it.indra.SigeaWeb.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.indra.es.builder.RestTemplateBuilder;
import com.indra.es.utils.exceptions.RestException;
import com.indra.es.utils.model.WSO2Token;

import it.indra.SigeaCommons.model.DettaglioUtenteModel;
import it.indra.SigeaCommons.model.RedazioneModel;
import it.indra.SigeaCommons.model.TipologiaModel;
import it.indra.SigeaWeb.utilities.SigeaPropertiesSettings;

@Service
public class UserService {

	@Autowired
	private SigeaPropertiesSettings sigeaPropertiesSettings;

//	@Autowired
//	@Qualifier("genericRestTemplate")	
//	private RestTemplate restTemplate;

	public DettaglioUtenteModel saveUtente(WSO2Token token, String tipologia, Long entitaId) throws RestException {
		UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + "utenti")
				.queryParam("tipologia", tipologia).queryParam("entitaId", entitaId);
//		return restTemplate.exchange(url.build().toUriString(), HttpMethod.PUT, new HttpEntity<String>(new HttpHeaders()), DettaglioUtenteModel.class).getBody();

		@SuppressWarnings({ "unchecked" })
		ResponseEntity<DettaglioUtenteModel> res = (ResponseEntity<DettaglioUtenteModel>) new RestTemplateBuilder()
				.url(url.build().toUriString()).returnClass(DettaglioUtenteModel.class).oauth2Token(token)
				.httpMethod(HttpMethod.PUT).build().executeRequest(false);

		DettaglioUtenteModel result = res.getBody();

		return result;
	}

	public Set<RedazioneModel> getRedazioni(WSO2Token token) throws RestException {
		UriComponentsBuilder url = UriComponentsBuilder
				.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + "redazioni");
//		return restTemplate.exchange(url.build().toUriString(), HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), new ParameterizedTypeReference<Set<RedazioneModel>>() {}).getBody();

		@SuppressWarnings({ "unchecked" })
		ResponseEntity<RedazioneModel[]> res = (ResponseEntity<RedazioneModel[]>) new RestTemplateBuilder()
				.url(url.build().toUriString()).returnClass(RedazioneModel[].class).oauth2Token(token)
				.httpMethod(HttpMethod.GET).build().executeRequest(false);

		Set<RedazioneModel> result = new HashSet<RedazioneModel>(Arrays.asList(res.getBody()));

		return result;
	}

	public boolean verifyTipologiaUtente(WSO2Token token, String tipologia) throws RestException {
		UriComponentsBuilder url = UriComponentsBuilder
				.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + "tipologie");
//		Set<TipologiaModel> tipologie = restTemplate.exchange(url.build().toUriString(), HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), new ParameterizedTypeReference<Set<TipologiaModel>>() {}).getBody();

		@SuppressWarnings({ "unchecked" })
		ResponseEntity<TipologiaModel[]> res = (ResponseEntity<TipologiaModel[]>) new RestTemplateBuilder()
				.url(url.build().toUriString()).returnClass(TipologiaModel[].class).oauth2Token(token)
				.httpMethod(HttpMethod.GET).build().executeRequest(false);

		Set<TipologiaModel> tipologie = new HashSet<TipologiaModel>(Arrays.asList(res.getBody()));

		boolean find = false;

		for (TipologiaModel tipologiaModel : tipologie) {
			if (tipologiaModel.getTipologiaId().equals(tipologia)) {
				find = true;
				break;
			}
		}

		return find;
	}
	
	/**
	 * Metodo per rilevare il ruolo pi- alto in riferimento ai ruoli impostati in SIGEA (Tabella t_sigea_tipologia_utente)
	 * @param request
	 * @return
	 */
	public String findHighestRole(HttpServletRequest request) {
		
		String userType = "SEGNALA_EVENTO";
		if(request.isUserInRole("ROLE_SIGEA_PUBBLICAZIONE_VIP")) {
			userType = "REDCAP-PP";
		} else if (request.isUserInRole("ROLE_SIGEA_REDAZIONE_VIP")) {
			userType = "RED-PP";
		} else if (request.isUserInRole("ROLE_SIGEA_VALIDAZIONE")) {
			userType = "REDVAL-PP";
		} else if (request.isUserInRole("ROLE_SIGEA_PROMOZIONE")) {
			userType = "PROMUOVI_EVENTO";
		}
		
		return userType;
	}
	
	/**
	 * Metodo per rilevare il ruolo pi- alto in riferimento ai ruoli impostati in SIGEA (Tabella t_sigea_tipologia_utente).
	 * Nel particolare entrando sul servizio promozione se si - validatore la pagina avr- alcune funzionalit- aggiuntive prese dal servizio validazione
	 * @param request
	 * @return
	 */
	public String setRolePromozione(HttpServletRequest request) {
		
		String userType = "SEGNALA_EVENTO";
		if (request.isUserInRole("ROLE_SIGEA_VALIDAZIONE")) {
			userType = "REDVAL-PP";
		} else if (request.isUserInRole("ROLE_SIGEA_PROMOZIONE")) {
			userType = "PROMUOVI_EVENTO";
		}
		
		return userType;
	}
	
	public String setRoleRicevute(HttpServletRequest request)
	{
		String userType = "SEGNALA_EVENTO";
		if (request.isUserInRole("ROLE_SIGEA_RICEVUTE")) {
			userType = "RIC-PP";
		} else if (request.isUserInRole("ROLE_SIGEA_PROMOZIONE")) {
			userType = "PROMUOVI_EVENTO";
		}
		
		return userType;
	}
	

	/**
	 * Metodo per rilevare il ruolo pi- alto in riferimento ai ruoli impostati in SIGEA (Tabella t_sigea_tipologia_utente).
	 * Nel particolare entrando sul servizio redazione se si ha il role "ROLE_SIGEA_PUBBLICAZIONE_VIP" la pagina avr- alcune funzionalit- aggiuntive della tipologia utente REDCAP-VIP
	 * @param request
	 * @return
	 */
	public String setRoleRedazione(HttpServletRequest request) {
		
		String userType = "SEGNALA_EVENTO";
		if(request.isUserInRole("ROLE_SIGEA_PUBBLICAZIONE_VIP")) {
			userType = "REDCAP-PP";
		} else if (request.isUserInRole("ROLE_SIGEA_REDAZIONE_VIP")) {
			userType = "RED-PP";
		}
		
		return userType;
	}
}
