package it.indra.SigeaWeb.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.indra.es.builder.RestTemplateBuilder;
import com.indra.es.utils.model.WSO2Token;

import it.indra.SigeaCommons.model.SegnalazioneFilter;
import it.indra.SigeaCommons.model.SegnalazioneModel;
import it.indra.SigeaCommons.model.SegnalazioneModelList;
import it.indra.SigeaCommons.model.SegnalazioneStatoPatchModel;
import it.indra.SigeaWeb.utilities.SigeaPropertiesSettings;
import it.indra.SigeaWeb.utilities.WrapperFilterRequest;
import it.indra.SigeaWeb.utilities.WrapperFilterResponse;
import it.indra.SigeaWeb.utilities.WrapperFilterUtility;

@Service
public class SegnalazioneService {

//	@Autowired
//	GeneralProperties properties;

	@Autowired
	private SigeaPropertiesSettings sigeaPropertiesSettings;

//	@Autowired
//	@Qualifier("genericRestTemplate")
//	RestTemplate restTemplate;

	public WrapperFilterResponse<SegnalazioneModelList> getListaPaginataSegnalazioni(WSO2Token token,
			WrapperFilterRequest<SegnalazioneFilter> dataTableRequest) throws Exception {

		if (dataTableRequest.getFilter() == null) {
			SegnalazioneFilter filter = new SegnalazioneFilter();
			dataTableRequest.setFilter(filter);
		}

		UriComponentsBuilder url = UriComponentsBuilder
				.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + "segnalazioni/listapaginata")
				.queryParam("wrappedFilter", WrapperFilterUtility.encodeWrapper(dataTableRequest));
//		return restTemplate.exchange(url.build().toUriString(), HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()) , new ParameterizedTypeReference<WrapperFilterResponse<SegnalazioneModelList>>(){}).getBody();

		// TODO gestione WrapperFilterResponse<DATA>
		@SuppressWarnings({ "unchecked" })
		ResponseEntity<String> res = (ResponseEntity<String>) new RestTemplateBuilder()
				.url(url.build().toUriString()).returnClass(String.class).oauth2Token(token)
				.httpMethod(HttpMethod.GET).build().executeRequest(false);

		ObjectMapper objectMapper = new ObjectMapper();
		
		WrapperFilterResponse<SegnalazioneModelList> result = objectMapper.readValue(res.getBody(), new TypeReference<WrapperFilterResponse<SegnalazioneModelList>>() {
		});

		return result;
	}

	public String postSegnalazione(WSO2Token token, SegnalazioneModel model) throws Exception {
		UriComponentsBuilder url = UriComponentsBuilder
				.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + "segnalazioni");
//		return restTemplate.exchange(url.build().toUriString(), HttpMethod.POST, new HttpEntity<SegnalazioneModel>(model,new HttpHeaders()) , String.class).getBody();

		@SuppressWarnings({ "unchecked" })
		ResponseEntity<String> res = (ResponseEntity<String>) new RestTemplateBuilder().url(url.build().toUriString())
				.returnClass(String.class).oauth2Token(token).httpMethod(HttpMethod.POST).body(model).build().executeRequest(false);

		return res.getBody();
	}

	public List<String> getListaTitoliSegnalazioniByDataComune(WSO2Token token, Date dataDa, String codIstat)
			throws Exception {
		UriComponentsBuilder url = UriComponentsBuilder
				.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + "segnalazioni").queryParam("dataDa", dataDa)
				.queryParam("codIstat", codIstat);
//		List<SegnalazioneModel> listaEventi = restTemplate.exchange(url.build().toUriString(), HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), new ParameterizedTypeReference<List<SegnalazioneModel>>() {}).getBody();

		@SuppressWarnings({ "unchecked" })
		ResponseEntity<SegnalazioneModel[]> res = (ResponseEntity<SegnalazioneModel[]>) new RestTemplateBuilder()
				.url(url.build().toUriString()).returnClass(SegnalazioneModel[].class).oauth2Token(token)
				.httpMethod(HttpMethod.GET).build().executeRequest(false);

		List<SegnalazioneModel> listaEventi = new ArrayList<SegnalazioneModel>(Arrays.asList(res.getBody()));

		List<String> titoliSegnalazioni = new ArrayList<String>();

		for (SegnalazioneModel segnalazione : listaEventi) {
			titoliSegnalazioni.add(segnalazione.getNome());
		}

		return titoliSegnalazioni;
	}

	public String patchStato(WSO2Token token, Long segnalazioneId,
			SegnalazioneStatoPatchModel segnalazioneStatusPatchModel) throws Exception {
		UriComponentsBuilder url = UriComponentsBuilder
				.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + "segnalazioni/" + segnalazioneId);
//		return restTemplate.exchange(url.build().toUriString(), HttpMethod.PUT, new HttpEntity<SegnalazioneStatoPatchModel>(segnalazioneStatusPatchModel,new HttpHeaders()), String.class).getBody();

		@SuppressWarnings({ "unchecked" })
		ResponseEntity<String> res = (ResponseEntity<String>) new RestTemplateBuilder().url(url.build().toUriString())
				.returnClass(String.class).oauth2Token(token).httpMethod(HttpMethod.PUT)
				.body(segnalazioneStatusPatchModel).build().executeRequest(false);

		return res.getBody();
	}

	public String getSegnalazione(WSO2Token token, Long segnalazioneId) throws Exception {
		UriComponentsBuilder url = UriComponentsBuilder
				.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + "segnalazioni/" + segnalazioneId);
//		return restTemplate.exchange(url.build().toUriString(), HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), String.class).getBody();	

		@SuppressWarnings({ "unchecked" })
		ResponseEntity<String> res = (ResponseEntity<String>) new RestTemplateBuilder().url(url.build().toUriString())
				.returnClass(String.class).oauth2Token(token).httpMethod(HttpMethod.GET).build().executeRequest(false);

		return res.getBody();
	}
}
