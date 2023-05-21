package it.indra.SigeaWeb.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.indra.es.builder.RestTemplateBuilder;
import com.indra.es.utils.LogUtility;
import com.indra.es.utils.exceptions.RestException;
import com.indra.es.utils.model.WSO2Token;

import it.indra.SigeaCommons.model.DecodificaModel;
import it.indra.SigeaWeb.utilities.SigeaPropertiesSettings;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DecodificaService {

//	@Autowired
//	GeneralProperties properties;

	@Autowired
	private SigeaPropertiesSettings sigeaPropertiesSettings;

//	@Autowired
//	@Qualifier("genericRestTemplate")
//	RestTemplate restTemplate;

	private List<DecodificaModel> decodifiche;

	//TODO verificare necessit- di token
	public List<DecodificaModel> getDecodifiche(WSO2Token token) {
		if (decodifiche == null) {

			decodifiche = new ArrayList<DecodificaModel>();
			UriComponentsBuilder url = UriComponentsBuilder
					.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + "decodifiche");
//			decodifiche = restTemplate.exchange(url.build().toUriString(), HttpMethod.GET,new HttpEntity<String>(new HttpHeaders()), new ParameterizedTypeReference<List<DecodificaModel>>() {}).getBody();

			try {
				@SuppressWarnings({ "unchecked" })
				ResponseEntity<DecodificaModel[]> res = (ResponseEntity<DecodificaModel[]>) new RestTemplateBuilder()
						.url(url.build().toUriString()).returnClass(DecodificaModel[].class).oauth2Token(token)
						.httpMethod(HttpMethod.GET).build().executeRequest(false);
				
				decodifiche = new ArrayList<DecodificaModel>(Arrays.asList(res.getBody()));
			} catch (RestException e) {

				log.error(LogUtility.exceptionToLog(e));
			}
	

		}
		return decodifiche;
	}

	public void cleanDecodifiche() {
		decodifiche = null;
	}

}
