package it.indra.SigeaWeb.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.indra.es.builder.RestTemplateBuilder;
import com.indra.es.utils.JSONUtility;
import com.indra.es.utils.LogUtility;
import com.indra.es.utils.RestTemplate;
import com.indra.es.utils.RestUtility;
import com.indra.es.utils.constants.Oauth2RefreshTokenType;
import com.indra.es.utils.constants.SharedUrl;
import com.indra.es.utils.exceptions.RestException;
import com.indra.es.utils.model.RefreshTokenType;
import com.indra.es.utils.model.WSO2Token;
import com.indra.es.utils.model.security.SecurityContext;

import it.indra.SigeaWeb.utilities.SigeaPropertiesSettings;
import it.puglia.spc.ect.commons.dms.dto.utenti.UserRolesDTO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthService {

	@Autowired
	private SigeaPropertiesSettings sigeaPropertiesSettings;

	@SuppressWarnings("unchecked")
	public WSO2Token acquireToken(HttpServletRequest request) throws RestException {

		WSO2Token token = null;
		WSO2Token oidcToken = null;
		
		String authorizationCode = request.getParameter("code");

		Boolean returnBodyInSteadHttpResponseEntity = false;
		String body = "grant_type=authorization_code&scope=openid&code=" + authorizationCode + "&redirect_uri="
				+ sigeaPropertiesSettings.getOidcRedirectUri();
		
		log.info("BODY:" + body);
		
		ResponseEntity<Object> res = (ResponseEntity<Object>) RestUtility.builder()
				.url(sigeaPropertiesSettings.getOidcAccessTokenUri())
				.username(sigeaPropertiesSettings.getOidcClientId())
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.password(sigeaPropertiesSettings.getOidcClientSecret())
				.httpMethod(HttpMethod.POST).body(body).build()
				.executeRequest(returnBodyInSteadHttpResponseEntity);

		String tmpTokenMan = (String) res.getBody();
		JSONObject tmpToken = new JSONObject(tmpTokenMan);
		String accessToken = null;
		String scope = null;
		long expiresIn = 0;
		String refreshToken = null;
		String tokenType = null;
		String idToken = tmpToken.getString("id_token");
		
		accessToken = tmpToken.getString("access_token");
		scope = tmpToken.getString("scope");
		expiresIn = tmpToken.getLong("expires_in");
		refreshToken = tmpToken.getString("refresh_token");
		tokenType = tmpToken.getString("token_type");

		oidcToken = new WSO2Token(idToken, accessToken, expiresIn, refreshToken, scope, tokenType,
				System.currentTimeMillis());

		ResponseEntity<String> response = null;
		
		try {
			response = (ResponseEntity<String>) RestUtility.builder().contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.queryParam("assertion", idToken)
					.queryParam("scope", "openid")
					.queryParam("grant_type", "urn:ietf:params:oauth:grant-type:jwt-bearer")
					.username(sigeaPropertiesSettings.getWso2ClientId())
					.password(sigeaPropertiesSettings.getWso2ClientSecret())
					.url(sigeaPropertiesSettings.getOidcAccessTokenUri())
					.httpMethod(HttpMethod.POST).build()
					.executeRequest(false);
		} catch (RestException e) {
			log.error(LogUtility.exceptionToLog(e));
			throw e;
		} catch (Exception e) {
			log.error(LogUtility.exceptionToLog(e));
			throw e;
		}

		tmpTokenMan = (String) response.getBody();
		tmpToken = new JSONObject(tmpTokenMan);
		accessToken = tmpToken.getString("access_token");
		scope = tmpToken.getString("scope");
		expiresIn = tmpToken.getLong("expires_in");
		refreshToken = tmpToken.getString("refresh_token");
		tokenType = tmpToken.getString("token_type");

		token = new WSO2Token(accessToken, expiresIn, refreshToken, scope, tokenType,
				System.currentTimeMillis());

		RefreshTokenType refreshMode = new RefreshTokenType();
		refreshMode.setClientId(sigeaPropertiesSettings.getWso2ClientId());
		refreshMode.setClientSecret(sigeaPropertiesSettings.getWso2ClientSecret());
		refreshMode.setNameAttributeInSession("WSO2Token");
		refreshMode.setSession(request.getSession());
		refreshMode.setTypeRefresh(Oauth2RefreshTokenType.REFRESH_TOKEN_WITH_CLIENT_VALIDATION);
		token.setRefreshModeType(refreshMode);
		request.getSession().setAttribute("WSO2Token", token);
		request.getSession().setAttribute("OIDCToken", oidcToken);

		return token;
	}

	public SecurityContext getUserEntityPermissions(WSO2Token token, Long idEntita) throws JsonParseException,
			JsonMappingException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {

		String string = null;
		try {

			string = (String) RestUtility.builder()
					.url(SharedUrl.URL_API_USER + "users/entita/permessi?entita=" + idEntita).oauth2Token(token)
					.httpMethod(HttpMethod.GET).build().executeRequest(true);
		} catch (RestException e) {
			log.error(LogUtility.exceptionToLog(e));
		}

		@SuppressWarnings("rawtypes")
		JSONUtility jsonUtility = new JSONUtility();
		SecurityContext securityContext = (SecurityContext) jsonUtility.convertToObject(string, SecurityContext.class);

		return securityContext;
	}

	public UserRolesDTO getUserPermissions(WSO2Token token) throws RestException, JsonParseException,
			JsonMappingException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {

		UserRolesDTO userRoles = null;
		try {
			userRoles = (UserRolesDTO) new RestTemplateBuilder().returnClass(UserRolesDTO.class)
					.url(SharedUrl.URL_API_USER + "users/user/roles").oauth2Token(token).httpMethod(HttpMethod.GET)
					.build().executeRequest(true);
		} catch (RestException e) {
			log.error(LogUtility.exceptionToLog(e));
		}

		return userRoles;
	}
	
	/**
	 * Recupero application token
	 * @return
	 */
	public WSO2Token getWso2ApplicationToken() {
		
		RestTemplate restTemplate = new RestTemplate();

		String url = sigeaPropertiesSettings.getOidcAccessTokenUri();
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();

		String clientId = sigeaPropertiesSettings.getWso2ClientId();
		String clientSecret = sigeaPropertiesSettings.getWso2ClientSecret();
		
		body.add("grant_type", "client_credentials");
		body.add("client_id", clientId);
		body.add("client_secret", clientSecret);

		HttpEntity<?> requestEntity = new HttpEntity<Object>(body, headers);

		ResponseEntity<String> stringJson = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

		JSONObject json = new JSONObject(stringJson);

		String bodyString = json.getString("body");
		json = new JSONObject(bodyString);

		long expiresIn = json.getLong("expires_in");
		String accessToken = json.getString("access_token");
		String scope = json.getString("scope");
		String tokenType = json.getString("token_type");
		
		WSO2Token token = new WSO2Token(accessToken, expiresIn, "", scope, tokenType, System.currentTimeMillis());
		
		RefreshTokenType typeRefresh = new RefreshTokenType();
		
		typeRefresh.setClientId(clientId);
		typeRefresh.setClientSecret(clientSecret);
		typeRefresh.setTypeRefresh(Oauth2RefreshTokenType.CLIENT_CREDENTIALS);
		
		token.setRefreshModeType(typeRefresh);
		
		return token;
	}
}
