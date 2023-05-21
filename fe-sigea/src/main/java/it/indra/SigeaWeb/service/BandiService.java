package it.indra.SigeaWeb.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.indra.es.builder.RestTemplateBuilder;
import com.indra.es.utils.exceptions.RestException;
import com.indra.es.utils.model.WSO2Token;

import it.indra.SigeaCommons.model.BandoModel;
import it.indra.SigeaCommons.model.BandoModelList;
import it.indra.SigeaCommons.model.MezzoModel;
import it.indra.SigeaCommons.model.ProdottoModel;
import it.indra.SigeaCommons.model.TipologiaAttivitaModel;
import it.indra.SigeaCommons.model.TipologiaMIBACTModel;
import it.indra.SigeaCommons.model.TipologiaModel;
import it.indra.SigeaCommons.model.ValoreAttrattivitaTuristicaModel;
import it.indra.SigeaWeb.utilities.SigeaPropertiesSettings;
import it.puglia.spc.ect.commonsbandi.BandiDTO;
import it.puglia.spc.ect.commonsbandi.ProgettiDTO;

@Service
public class BandiService {

//	@Autowired
//	GeneralProperties properties;

	@Autowired
	private SigeaPropertiesSettings sigeaPropertiesSettings;

	public Set<BandoModelList> getBandiProgetti(WSO2Token token, Long idEntita) throws RestException {

		String destination="";
		
		if (idEntita!=null) {
			destination="?idAttivita="+ idEntita;
		}
		
		UriComponentsBuilder url = UriComponentsBuilder
			.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + "bandiProgetti" + destination);
	
	
		@SuppressWarnings({ "unchecked" })
		ResponseEntity<BandoModelList[]> res = (ResponseEntity<BandoModelList[]>) new RestTemplateBuilder()
				.url(url.build().toUriString()).returnClass(BandoModelList[].class).oauth2Token(token)
				.httpMethod(HttpMethod.GET).build().executeRequest(false);

		Set<BandoModelList> result = new HashSet<BandoModelList>();
		
		if (res.getBody()!=null) {
		 result = new HashSet<BandoModelList>(
				Arrays.asList(res.getBody()));
		}

		return result;
	}

	public BandiDTO findBandi(WSO2Token token) throws RestException {

		UriComponentsBuilder url = UriComponentsBuilder
				.fromHttpUrl(sigeaPropertiesSettings.getUrlBandiServices() + "bandi");

		@SuppressWarnings("unchecked")
		ResponseEntity<BandiDTO> res = (ResponseEntity<BandiDTO>) new RestTemplateBuilder()
		.url(url.build().toUriString()).returnClass(BandiDTO.class).oauth2Token(token)
		.httpMethod(HttpMethod.GET).build().executeRequest(false);


		return res.getBody();
	}

	public ProgettiDTO findProgetti(String codiceFiscale, WSO2Token token) throws RestException {
		
		UriComponentsBuilder url = UriComponentsBuilder
				.fromHttpUrl(sigeaPropertiesSettings.getUrlBandiServices() + "progetti?codice_fiscale="+codiceFiscale);	
		
			@SuppressWarnings({ "unchecked" })
			ResponseEntity<ProgettiDTO> res = (ResponseEntity<ProgettiDTO>) new RestTemplateBuilder()
					.url(url.build().toUriString()).returnClass(ProgettiDTO.class).oauth2Token(token)
					.httpMethod(HttpMethod.GET).build().executeRequest(false);


			return res.getBody();
	}

}
