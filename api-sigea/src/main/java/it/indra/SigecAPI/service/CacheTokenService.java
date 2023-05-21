package it.indra.SigecAPI.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import io.micrometer.core.instrument.util.StringUtils;
import it.indra.SigecAPI.entity.CacheToken;
import it.indra.SigecAPI.repository.CacheTokenRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CacheTokenService {

	@Value("${jms.token.enabled}")
	private boolean tokenEnabled;

	@Value("${jms.token.url}")
	private String url;

	@Value("${jms.token.clientId}")
	private String clientId;

	@Value("${jms.token.clientSecret}")
	private String clientSecret;

	@Autowired
	private CacheTokenRepository cacheTokenRepository;

	public String getToken() {

		Optional<CacheToken> cacheToken = cacheTokenRepository.findById(clientId);

		if (tokenEnabled) {
			if (cacheToken.isPresent() && cacheToken.get().isValid()) {
				log.info("Token in cache valido");
				return cacheToken.get().getAccessToken();
			} else {
				String token = generateAndPersist();
				log.info("Token in cache valido");
				return token;
			}
		} else {
			return "";
		}
	}

	private String generateAndPersist() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();

		body.add("grant_type", "client_credentials");
		body.add("client_id", clientId);
		body.add("client_secret", clientSecret);

		HttpEntity<?> requestEntity = new HttpEntity<Object>(body, headers);
		ResponseEntity<String> stringJson = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
		JSONObject json = new JSONObject(stringJson);
		String bodyString = json.getString("body");
		json = new JSONObject(bodyString);
		String accessToken = json.getString("access_token");
		int expiresIn = json.getInt("expires_in");
		CacheToken token = new CacheToken();
		Optional<CacheToken> cacheToken = cacheTokenRepository.findById(clientId);
		if (cacheToken.isPresent()) {
			log.info("Token in cache scaduto..aggiornamento in corso");
			token = cacheToken.get();
		} else {
			log.info("Token in cache non presente..inserimento in corso");
			token.setClientId(clientId);
		}
		token.setAccessToken(accessToken);
		token.setCreationDate(LocalDateTime.now());
		token.setExpiresIn(expiresIn);

		cacheTokenRepository.save(token);
		
		return accessToken;
	}
}
