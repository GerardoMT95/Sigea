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
import com.indra.es.utils.exceptions.RestException;
import com.indra.es.utils.model.WSO2Token;

import it.indra.SigeaCommons.model.ProgettoModel;
import it.indra.SigeaWeb.utilities.SigeaPropertiesSettings;

@Service
public class ProgettoService {

//	@Autowired
//	GeneralProperties properties;

	@Autowired
	private SigeaPropertiesSettings sigeaPropertiesSettings;

//	@Autowired
//	@Qualifier("genericRestTemplate")
//	RestTemplate restTemplate;

	public List<ProgettoModel> getProgettiByPIVA(WSO2Token token, String partitaIva) throws RestException {
		UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + "progetti");
		if (partitaIva != null) {
			url.queryParam("partitaIva", partitaIva);
		}
//		List<ProgettoModel> listaProgetti = restTemplate.exchange(url.build().toUriString(), HttpMethod.GET,new HttpEntity<String>(new HttpHeaders()), new ParameterizedTypeReference<List<ProgettoModel>>() {}).getBody();

		@SuppressWarnings({ "unchecked" })
		ResponseEntity<ProgettoModel[]> res = (ResponseEntity<ProgettoModel[]>) new RestTemplateBuilder()
				.url(url.build().toUriString()).returnClass(ProgettoModel[].class).oauth2Token(token).httpMethod(HttpMethod.GET)
				.build().executeRequest(false);

		List<ProgettoModel> result = new ArrayList<ProgettoModel>(Arrays.asList(res.getBody()));

		return result;

	}

}
