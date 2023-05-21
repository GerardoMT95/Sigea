package it.indra.SigecAPI.clientstub;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.indra.es.builder.RestTemplateBuilder;
import com.indra.es.utils.exceptions.RestException;

import it.indra.SigeaCommons.model.DettaglioUtenteModel;
import it.indra.SigecAPI.util.CommonUtility;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DMSClientStub {

	@Value("${DIRECTURL.SERVICEDMS.BASEPATH}")
	String clientPath;

	@Value("${DIRECTURL.UTENTISERVICE.BASEPATH}")
	String utentiServicePath;

	public DettaglioUtenteModel getUserFromRequest(HttpServletRequest request) throws AuthenticationException {
		String accessToken = CommonUtility.getTokenFromRequest(request);
		UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(utentiServicePath + "users/user");
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + accessToken);

		RestTemplate restTemplate = new RestTemplate();
		//log.info("Inizio chiamata a DMS per prelevare informazioni utente");
		ResponseEntity<String> response = restTemplate.exchange(url.build().toUriString(), HttpMethod.GET,
				new HttpEntity<String>(headers), String.class);
		JSONObject user = new JSONObject(response.getBody());

		DettaglioUtenteModel dettaglioUtenteModel = new DettaglioUtenteModel();
		dettaglioUtenteModel.setUtenteId(user.getLong("idUtente"));
		dettaglioUtenteModel.setUsername(user.getString("username"));
		if (!user.isNull("nome")) {
			dettaglioUtenteModel.setNome(user.getString("nome"));
		}
		if (!user.isNull("cognome")) {
			dettaglioUtenteModel.setCognome(user.getString("cognome"));
		}
		if (!user.isNull("codicefiscale")) {
			dettaglioUtenteModel.setCodFiscale(user.getString("codicefiscale"));
		}
		if (!user.isNull("mailNotifiche") && user.getString("mailNotifiche").trim() != "") {
			dettaglioUtenteModel.setEmail(user.getString("mailNotifiche"));
		} else if (!user.isNull("emailaddress") && user.getString("emailaddress").trim() != "") {
			dettaglioUtenteModel.setEmail(user.getString("emailaddress"));
		}
		if (!user.isNull("telefono") && user.getString("telefono").trim() != "") {
			dettaglioUtenteModel.setTel(user.getString("telefono"));
		}
		if (!user.isNull("cellulare") && user.getString("cellulare").trim() != "") {
			dettaglioUtenteModel.setCel(user.getString("cellulare"));
		}
	//	log.info("Fine chiamata a DMS per prelevare informazioni utente");
		return dettaglioUtenteModel;
	}

	public Set<String> getEmailRedattori(String jwt, Set<String> tipiUtente) {
		UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(clientPath + "service/mail/list")
				.queryParam("serviceslist", String.join(",", tipiUtente));
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + jwt);
		RestTemplate restTemplate = new RestTemplate();
		try {
			log.info("Inizio chiamata a DMS per prelevare elenco mail redattori con tipiUtente: "
					+ String.join(",", tipiUtente));
			log.info("Invocando url " + url.build().toUriString() + "...");
			ResponseEntity<Set<String>> response = restTemplate.exchange(url.build().toUriString(), HttpMethod.GET,
					new HttpEntity<String>(headers), new ParameterizedTypeReference<Set<String>>() {
					});
			log.info("Email redattori ricevute da DMS: " + String.join(",", response.getBody()));
			return response.getBody();
		} catch (Exception e) {
			log.info("Chiamata al DMS fallita. elenco email vuoto: ", e);
			return new HashSet<String>();
		}
	}

	public String getActivityDTO(String token, Long id) throws RestException {
		UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(clientPath + "companies/getActivityById/" + id);

		@SuppressWarnings({ "unchecked" })
		ResponseEntity<String> res = (ResponseEntity<String>) new RestTemplateBuilder().url(url.build().toUriString())
				.returnClass(String.class).bearerToken(token).httpMethod(HttpMethod.GET).build().executeRequest(false);

		String string = res.getBody();

		return string;
	}

	public String getMailImpresa(String token, Long id) throws RestException {
		
		log.info("Recuperando mailSedeOperativa per impresa: "+id);
		String mailSedeOperativa = "";
		try {
			UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(clientPath + "companies/getActivityById/" + id);

			@SuppressWarnings({ "unchecked" })
			ResponseEntity<String> res = (ResponseEntity<String>) new RestTemplateBuilder()
					.url(url.build().toUriString()).returnClass(String.class).bearerToken(token)
					.httpMethod(HttpMethod.GET).build().executeRequest(false);

			String response = res.getBody();
			JSONObject obj = new JSONObject(response);
			mailSedeOperativa = obj.getString("mailSedeOperativa");
			
			log.info("Recuperata mailSedeOperativa="+mailSedeOperativa+" per impresa: "+id);
			
		} catch (Exception e) {
			log.error("Errore durante il recupero dell'impresa dal DMS", e.getMessage());
		}

		return mailSedeOperativa;
	}

	public String getMailRegistrazioneImpresa(String token, Long id) throws RestException {
		
		log.info("Recuperando mailSedeOperativa per registrazione impresa: "+id);
		
		String mailSedeOperativa = "";
		try {
			UriComponentsBuilder url = UriComponentsBuilder
					.fromHttpUrl(clientPath + "companies/searchregisteredcompany/" + id);

			@SuppressWarnings({ "unchecked" })
			ResponseEntity<String> res = (ResponseEntity<String>) new RestTemplateBuilder()
					.url(url.build().toUriString()).returnClass(String.class).bearerToken(token)
					.httpMethod(HttpMethod.GET).build().executeRequest(false);

			String response = res.getBody();
			JSONObject obj = new JSONObject(response);
			JSONObject datiRegistrazione = obj.getJSONObject("datiAttivita");
			mailSedeOperativa = datiRegistrazione.getString("mailSedeOperativa");

			log.info("Recuperata mailSedeOperativa="+mailSedeOperativa+" per registrazione impresa: "+id);
			
		} catch (Exception e) {
			log.error("Errore durante il recupero dell'impresa dal DMS", e.getMessage());
		}

		return mailSedeOperativa;
	}
	
	

	public String getRegistrazioneImpresa(String token, Long id) throws RestException {
		
		log.info("Recuperando mailSedeOperativa per registrazione impresa: "+id);
		
		String response="";
		try {
			UriComponentsBuilder url = UriComponentsBuilder
					.fromHttpUrl(clientPath + "companies/searchregisteredcompany/" + id);

			@SuppressWarnings({ "unchecked" })
			ResponseEntity<String> res = (ResponseEntity<String>) new RestTemplateBuilder()
					.url(url.build().toUriString()).returnClass(String.class).bearerToken(token)
					.httpMethod(HttpMethod.GET).build().executeRequest(false);

			 response = res.getBody();
			
		

			
			
		} catch (Exception e) {
			log.error("Errore durante il recupero dell'impresa dal DMS", e.getMessage());
		}

		return response;
	}

}
