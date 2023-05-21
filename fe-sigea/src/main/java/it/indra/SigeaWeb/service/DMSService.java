package it.indra.SigeaWeb.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.indra.es.builder.RestTemplateBuilder;
import com.indra.es.utils.exceptions.RestException;
import com.indra.es.utils.model.WSO2Token;

import it.indra.SigeaWeb.utilities.SigeaPropertiesSettings;

@Service
public class DMSService {

//	@Autowired
//	GeneralProperties properties;

	@Autowired
	private SigeaPropertiesSettings sigeaPropertiesSettings;

//	@Autowired
//	@Qualifier("genericRestTemplate")
//	RestTemplate restTemplate;

	public String getDenominazioneAttivita(WSO2Token token, Long id) throws RestException {
		UriComponentsBuilder url = UriComponentsBuilder
				.fromHttpUrl(sigeaPropertiesSettings.getUrlDmsServices() + "companies/activitytourbyidactivity/" + id);
//		String string = restTemplate.exchange(url.build().toUriString(), HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), String.class).getBody();

		@SuppressWarnings({ "unchecked" })
		ResponseEntity<String> res = (ResponseEntity<String>) new RestTemplateBuilder().url(url.build().toUriString())
				.returnClass(String.class).oauth2Token(token).httpMethod(HttpMethod.GET).build().executeRequest(false);

		String string = res.getBody();

		JSONObject obj = new JSONObject(string);
		JSONObject attivita = obj.getJSONObject("datiAttivita");

		return attivita.getString("denominazione");
	}
	
	public String getActivityDTO(WSO2Token token, Long id) throws RestException {
		UriComponentsBuilder url = UriComponentsBuilder
				.fromHttpUrl(sigeaPropertiesSettings.getUrlDmsServices() + "companies/activitytourbyidactivity/" + id);

		@SuppressWarnings({ "unchecked" })
		ResponseEntity<String> res = (ResponseEntity<String>) new RestTemplateBuilder().url(url.build().toUriString())
				.returnClass(String.class).oauth2Token(token).httpMethod(HttpMethod.GET).build().executeRequest(false);

		String string = res.getBody();

		return string;
	}


	public String getDenominazioneAttivitaRichiesta(WSO2Token token, Long id) throws RestException {
		UriComponentsBuilder url = UriComponentsBuilder
				.fromHttpUrl(sigeaPropertiesSettings.getUrlDmsServices() + "companies/searchregisteredcompany/" + id);
//		String string = restTemplate.exchange(url.build().toUriString(), HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), String.class).getBody();

		@SuppressWarnings({ "unchecked" })
		ResponseEntity<String> res = (ResponseEntity<String>) new RestTemplateBuilder().url(url.build().toUriString())
				.returnClass(String.class).oauth2Token(token).httpMethod(HttpMethod.GET).build().executeRequest(false);

		String string = res.getBody();

		JSONObject obj = new JSONObject(string);
		JSONObject attivita = obj.getJSONObject("datiAttivita");

		return attivita.getString("denominazione");
	}

	public String getDenominazioneEntita(WSO2Token token, Long id) throws RestException {
		UriComponentsBuilder url = UriComponentsBuilder
				.fromHttpUrl(sigeaPropertiesSettings.getUrlDmsServices() + "entities/entepubblico")
				.queryParam("idEntita", id);
//		String string = restTemplate.exchange(url.build().toUriString(), HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), String.class).getBody();

		@SuppressWarnings({ "unchecked" })
		ResponseEntity<String> res = (ResponseEntity<String>) new RestTemplateBuilder().url(url.build().toUriString())
				.returnClass(String.class).oauth2Token(token).httpMethod(HttpMethod.GET).build().executeRequest(false);

		String string = res.getBody();

		JSONObject obj = new JSONObject(string);

		return obj.getString("denominazione");
	}

	public String getPIVA(WSO2Token token, String id, boolean approvata) throws RestException {
		if (approvata) {
			try {
				UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(
						sigeaPropertiesSettings.getUrlDmsServices() + "companies/activitytourbyidactivity/" + id);
//				String string = restTemplate.exchange(url.build().toUriString(), HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), String.class).getBody();

				@SuppressWarnings({ "unchecked" })
				ResponseEntity<String> res = (ResponseEntity<String>) new RestTemplateBuilder().url(url.build().toUriString())
						.returnClass(String.class).oauth2Token(token).httpMethod(HttpMethod.GET).build()
						.executeRequest(false);

				String string = res.getBody();

				JSONObject obj = new JSONObject(string);
				JSONObject attivita = obj.getJSONObject("datiAttivita");
				String piva = attivita.getString("cfPiva");
				if (piva == null || piva.trim().isEmpty()) {
					throw new Exception();
				} else {
					return attivita.getString("cfPiva");
				}
			} catch (Exception e) {
				UriComponentsBuilder url = UriComponentsBuilder
						.fromHttpUrl(sigeaPropertiesSettings.getUrlDmsServices() + "entities/entepubblico")
						.queryParam("idEntita", id);
//				String string = restTemplate.exchange(url.build().toUriString(), HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), String.class).getBody();

				@SuppressWarnings({ "unchecked" })
				ResponseEntity<String> res = (ResponseEntity<String>) new RestTemplateBuilder().url(url.build().toUriString())
						.returnClass(String.class).oauth2Token(token).httpMethod(HttpMethod.GET).build()
						.executeRequest(false);

				String string = res.getBody();

				JSONObject obj = new JSONObject(string);

				//TODO un ente non ha partita IVA
				return "";
			}
		} else {
			UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(
					sigeaPropertiesSettings.getUrlDmsServices() + "companies/searchregisteredcompany/" + id);
//			String string = restTemplate.exchange(url.build().toUriString(), HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), String.class).getBody();

			@SuppressWarnings({ "unchecked" })
			ResponseEntity<String> res = (ResponseEntity<String>) new RestTemplateBuilder().url(url.build().toUriString())
					.returnClass(String.class).oauth2Token(token).httpMethod(HttpMethod.GET).build()
					.executeRequest(false);

			String string = res.getBody();

			JSONObject obj = new JSONObject(string);
			JSONObject attivita = obj.getJSONObject("datiAttivita");

			return attivita.getString("cfPiva");
		}
	}

}
