package it.indra.SigeaWeb.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.indra.es.builder.RestTemplateBuilder;
import com.indra.es.utils.exceptions.RestException;
import com.indra.es.utils.model.WSO2Token;

import it.indra.SigeaCommons.model.MezzoModel;
import it.indra.SigeaCommons.model.ProdottoModel;
import it.indra.SigeaCommons.model.TipologiaAttivitaModel;
import it.indra.SigeaCommons.model.TipologiaMIBACTModel;
import it.indra.SigeaCommons.model.TipologiaModel;
import it.indra.SigeaCommons.model.ValoreAttrattivitaTuristicaModel;
import it.indra.SigeaWeb.utilities.SigeaPropertiesSettings;

@Service
public class OntologiaService {

//	@Autowired
//	GeneralProperties properties;

	@Autowired
	private SigeaPropertiesSettings sigeaPropertiesSettings;

//	@Autowired
//	@Qualifier("genericRestTemplate")
//	RestTemplate restTemplate;

	public Set<MezzoModel> getMezzi(WSO2Token token) throws RestException {
		UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + "mezzi");
//		Set<MezzoModel> listamezzi = restTemplate.exchange(url.build().toUriString(), HttpMethod.GET,new HttpEntity<String>(new HttpHeaders()), new ParameterizedTypeReference<Set<MezzoModel>>() {}).getBody();

		@SuppressWarnings({ "unchecked" })
		ResponseEntity<MezzoModel[]> res = (ResponseEntity<MezzoModel[]>) new RestTemplateBuilder()
				.url(url.build().toUriString()).returnClass(MezzoModel[].class).oauth2Token(token).httpMethod(HttpMethod.GET)
				.build().executeRequest(false);

		Set<MezzoModel> result = new HashSet<MezzoModel>(Arrays.asList(res.getBody()));

		return result;
	}

	public Set<ProdottoModel> getProdotti(WSO2Token token) throws RestException {
		UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + "prodotti");
//		Set<ProdottoModel> listaprodotti = restTemplate.exchange(url.build().toUriString(), HttpMethod.GET,new HttpEntity<String>(new HttpHeaders()), new ParameterizedTypeReference<Set<ProdottoModel>>() {}).getBody();

		@SuppressWarnings({ "unchecked" })
		ResponseEntity<ProdottoModel[]> res = (ResponseEntity<ProdottoModel[]>) new RestTemplateBuilder()
				.url(url.build().toUriString()).returnClass(ProdottoModel[].class).oauth2Token(token).httpMethod(HttpMethod.GET)
				.build().executeRequest(false);

		Set<ProdottoModel> result = new HashSet<ProdottoModel>(Arrays.asList(res.getBody()));

		return result;
	}

	public Set<TipologiaAttivitaModel> getTipologieAttivita(WSO2Token token) throws RestException {
		UriComponentsBuilder url = UriComponentsBuilder
				.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + "tipologieattivita");
//		Set<TipologiaAttivitaModel> listatipologie = restTemplate.exchange(url.build().toUriString(), HttpMethod.GET,new HttpEntity<String>(new HttpHeaders()), new ParameterizedTypeReference<Set<TipologiaAttivitaModel>>() {}).getBody();

		@SuppressWarnings({ "unchecked" })
		ResponseEntity<TipologiaAttivitaModel[]> res = (ResponseEntity<TipologiaAttivitaModel[]>) new RestTemplateBuilder()
				.url(url.build().toUriString()).returnClass(TipologiaAttivitaModel[].class).oauth2Token(token)
				.httpMethod(HttpMethod.GET).build().executeRequest(false);

		Set<TipologiaAttivitaModel> result = new HashSet<TipologiaAttivitaModel>(Arrays.asList(res.getBody()));

		return result;
	}

	public Set<TipologiaMIBACTModel> getTipologieMIBACT(WSO2Token token) throws RestException {
		UriComponentsBuilder url = UriComponentsBuilder
				.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + "tipologiemibact");
//		Set<TipologiaMIBACTModel> listamibact = restTemplate.exchange(url.build().toUriString(), HttpMethod.GET,new HttpEntity<String>(new HttpHeaders()), new ParameterizedTypeReference<Set<TipologiaMIBACTModel>>() {}).getBody();

		@SuppressWarnings({ "unchecked" })
		ResponseEntity<TipologiaMIBACTModel[]> res = (ResponseEntity<TipologiaMIBACTModel[]>) new RestTemplateBuilder()
				.url(url.build().toUriString()).returnClass(TipologiaMIBACTModel[].class).oauth2Token(token)
				.httpMethod(HttpMethod.GET).build().executeRequest(false);

		Set<TipologiaMIBACTModel> result = new HashSet<TipologiaMIBACTModel>(Arrays.asList(res.getBody()));

		return result;
	}

	public Set<ValoreAttrattivitaTuristicaModel> getValoriAttrattivita(WSO2Token token) throws RestException {
		UriComponentsBuilder url = UriComponentsBuilder
				.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + "valoriattrattivita");
//		Set<ValoreAttrattivitaTuristicaModel> listavalori = restTemplate.exchange(url.build().toUriString(), HttpMethod.GET,new HttpEntity<String>(new HttpHeaders()), new ParameterizedTypeReference<Set<ValoreAttrattivitaTuristicaModel>>() {}).getBody();

		@SuppressWarnings({ "unchecked" })
		ResponseEntity<ValoreAttrattivitaTuristicaModel[]> res = (ResponseEntity<ValoreAttrattivitaTuristicaModel[]>) new RestTemplateBuilder()
				.url(url.build().toUriString()).returnClass(ValoreAttrattivitaTuristicaModel[].class).oauth2Token(token)
				.httpMethod(HttpMethod.GET).build().executeRequest(false);

		Set<ValoreAttrattivitaTuristicaModel> result = new HashSet<ValoreAttrattivitaTuristicaModel>(
				Arrays.asList(res.getBody()));

		return result;
	}

}
