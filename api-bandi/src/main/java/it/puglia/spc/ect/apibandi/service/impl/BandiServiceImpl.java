package it.puglia.spc.ect.apibandi.service.impl;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.indra.es.builder.RestTemplateBuilder;
import com.indra.es.utils.exceptions.RestException;
import com.indra.es.utils.model.WSO2Token;

import it.puglia.spc.ect.apibandi.converter.BandiConverter;
import it.puglia.spc.ect.apibandi.converter.ProgettiConverter;
import it.puglia.spc.ect.apibandi.dto.Bandi;
import it.puglia.spc.ect.apibandi.dto.Progetti;
import it.puglia.spc.ect.apibandi.service.BandiService;
import it.puglia.spc.ect.commonsbandi.BandiDTO;
import it.puglia.spc.ect.commonsbandi.ProgettiDTO;
import lombok.extern.slf4j.Slf4j;

@Service("bandiService")
@Slf4j
public class BandiServiceImpl implements BandiService {

	public WSO2Token token;

	@Autowired
	@Qualifier(value = "mockBandiService")
	BandiService bandiServiceMocked;

	@Value("${api.bandi.monitoraggio.enabled}")
	boolean apiBandiMonitoraggioIsEnabled;

	@Value("${api.bandi.monitoraggio.url}")
	String urlServizioEsternoMonitoraggioBandi;

	@Value("${api.bandi.monitoraggio.client_id}")
	String clientIdServizioEsternoMonitoraggioBandi;

	@Value("${api.bandi.monitoraggio.client_secret}")
	String clientSecretEsternoMonitoraggioBandi;

	@Autowired
	BandiConverter bandiConverter;

	@Autowired
	ProgettiConverter progettiConverter;

	public BandiDTO findBandi() throws RestException {

		if (apiBandiMonitoraggioIsEnabled) {

			if (token == null || !token.isValid()) {
				setToken(getAccessToken());
			}

			Bandi bandi = (Bandi) new RestTemplateBuilder().bearerToken(token.getAccessToken())
					.url(urlServizioEsternoMonitoraggioBandi + "monitoraggio/bandi").httpMethod(HttpMethod.GET)
					.returnClass(Bandi.class).build().executeRequest(true);

			log.info("Elenco bandi recuperati da servizio di monitoraggio:" + bandi.toString());

			return bandiConverter.convert(bandi);
		} else {

			return bandiServiceMocked.findBandi();
		}

	}

	public ProgettiDTO findProgetti(String codiceFiscalePiva) throws RestException {

		if (apiBandiMonitoraggioIsEnabled) {

			if (token == null || !token.isValid()) {
				setToken(getAccessToken());
			}

			Progetti progetti = null;

			progetti = (Progetti) new RestTemplateBuilder().bearerToken(token.getAccessToken())
					.url(urlServizioEsternoMonitoraggioBandi + "monitoraggio/progetti").httpMethod(HttpMethod.GET)
					.returnClass(Progetti.class).queryParam("codice_fiscale", codiceFiscalePiva).build()
					.executeRequest(true);

			log.info("Elenco progetti recuperati da servizio di monitoraggio con codice fiscale=" + codiceFiscalePiva
					+ " :" + progetti.toString());

			return progettiConverter.convert(progetti);

		} else {

			return bandiServiceMocked.findProgetti(codiceFiscalePiva);
		}
	}

	public WSO2Token getAccessToken() throws RestException {

		log.info("Recuperando token per accesso a servizio esterno...");

		@SuppressWarnings("unchecked")
		ResponseEntity<String> oauthToken = (ResponseEntity<String>) new RestTemplateBuilder()
				.username(clientIdServizioEsternoMonitoraggioBandi).password(clientSecretEsternoMonitoraggioBandi)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED).queryParam("grant_type", "client_credentials")
				.httpMethod(HttpMethod.POST).url(urlServizioEsternoMonitoraggioBandi + "oauth/token")
				.returnClass(String.class).build().executeRequest(false);

		JSONObject jsonResponse = new JSONObject(oauthToken.getBody());
		WSO2Token token = new WSO2Token();
		token.setAccessToken(jsonResponse.getString("access_token"));
		token.setExpiresInSeconds(jsonResponse.getLong("expires_in"));
		token.setRequestTimeMillis(System.currentTimeMillis());

		log.info("Ottenuto nuovo token");

		return token;
	}

	public WSO2Token getToken() {
		return token;
	}

	public void setToken(WSO2Token token) {
		this.token = token;
	}

}