package it.indra.SigeaWeb.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
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
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Data
public class TerritorialService {
	
//	@Autowired
//	GeneralProperties properties;
	
	@Autowired
	private SigeaPropertiesSettings sigeaPropertiesSettings;
	
//	@Autowired
//	@Qualifier("genericRestTemplate")
//	RestTemplate restTemplate;
	
	JSONObject italia;
	JSONObject puglia;
	JSONArray nazioniList;
	JSONArray regioniList;
	JSONArray areeList;
	JSONArray provinceList;
	JSONArray comuniList;
	JSONObject provincePerRegione;
	JSONObject provincePerArea;
	JSONObject comuniPerRegione;
	JSONObject comuniPerArea;
	JSONObject comuniPerProvincia;
	Map<String, JSONObject> areaPerComune;
	
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private static LocalDateTime lastCall = null;
	
	public synchronized void getTerritorialData(WSO2Token token) {
		
		if(lastCall != null) {
			LocalDateTime now = LocalDateTime.now();
			Duration duration = Duration.between(lastCall,now);
			if(duration.toMinutes()<1440) {
				return;
			}
		}
		
		try {
			italia = new JSONObject();
			puglia = new JSONObject();
			nazioniList = new JSONArray();
			regioniList = new JSONArray();
			areeList = new JSONArray();
			provinceList = new JSONArray();
			comuniList = new JSONArray();
			provincePerRegione = new JSONObject();
			provincePerArea = new JSONObject();
			comuniPerRegione = new JSONObject();
			comuniPerArea = new JSONObject();
			comuniPerProvincia = new JSONObject();
			
			UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(sigeaPropertiesSettings.getUrlTerritorial() + "nations");
//			String nazioniStringList = restTemplate.exchange(url.build().toUriString(), HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), String.class).getBody();
			
			@SuppressWarnings({ "unchecked" })
			ResponseEntity<String> res = (ResponseEntity<String>) new RestTemplateBuilder()
					.url(url.build().toUriString()).returnClass(String.class).oauth2Token(token).httpMethod(HttpMethod.GET)
					.build().executeRequest(false);
			
			String nazioniStringList =  res.getBody();
			
			JSONArray tempNazioniList = new JSONArray(nazioniStringList);
			tempNazioniList.forEach(item -> {
				JSONObject objOrig = (JSONObject) item;
				JSONObject objConverted = new JSONObject();
				objConverted.put("codNazione", objOrig.getString("nationCode"));
				objConverted.put("nazione", objOrig.getString("nationName"));
				nazioniList.put(objConverted);
				if (objOrig.getString("nationName").toUpperCase().equals("ITALIA")) {
					italia.put("codNazione", objOrig.getString("nationCode"));
					italia.put("nazione", objOrig.getString("nationName"));
				}
			});
			
			url = UriComponentsBuilder.fromHttpUrl(sigeaPropertiesSettings.getUrlTerritorial() + "administrativeArea");
//			String regioniStringList = restTemplate.exchange(url.build().toUriString(), HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), String.class).getBody();
			
			@SuppressWarnings({ "unchecked" })
			ResponseEntity<String> res1 = (ResponseEntity<String>) new RestTemplateBuilder()
					.url(url.build().toUriString()).returnClass(String.class).oauth2Token(token).httpMethod(HttpMethod.GET)
					.build().executeRequest(false);
			
			String regioniStringList =  res1.getBody();
			
			JSONArray tempRegioniList = new JSONArray(regioniStringList);
			tempRegioniList.forEach(item -> {
				JSONObject objOrig = (JSONObject) item;
				JSONObject objConverted = new JSONObject();
				objConverted.put("codRegione", objOrig.getString("regionCode"));
				objConverted.put("regione", objOrig.getString("regionName"));
				regioniList.put(objConverted);
				if (objOrig.getString("regionName").toUpperCase().equals("PUGLIA")) {
					puglia.put("codRegione", objOrig.getString("regionCode"));
					puglia.put("regione", objOrig.getString("regionName"));
				}
				UriComponentsBuilder innerUrl = UriComponentsBuilder.fromHttpUrl(sigeaPropertiesSettings.getUrlTerritorial() + "administrativeArea/" + objOrig.getString("regionCode"));
//				String provPerRegioniStringList = restTemplate.exchange(innerUrl.build().toUriString(), HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), String.class).getBody();
				
				@SuppressWarnings({ "unchecked" })
				ResponseEntity<String> res2 = null;
				try {
					res2 = (ResponseEntity<String>) new RestTemplateBuilder()
							.url(innerUrl.build().toUriString()).returnClass(String.class).oauth2Token(token).httpMethod(HttpMethod.GET)
							.build().executeRequest(false);
				} catch (RestException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				String provPerRegioniStringList =  res2.getBody();
				
				JSONArray tempProvincePerRegione = new JSONArray(provPerRegioniStringList);
				JSONArray tempProvincePerRegione2 = new JSONArray();
				tempProvincePerRegione.forEach(item2 -> {
					JSONObject objOrig2 = (JSONObject) item2;
					JSONObject objConverted2 = new JSONObject();
					objConverted2.put("codProvincia", objOrig2.getString("provinceCode"));
					objConverted2.put("provincia", objOrig2.getString("provinceName"));
					tempProvincePerRegione2.put(objConverted2);
				});
				provincePerRegione.put(objOrig.getString("regionCode"), tempProvincePerRegione2);
				innerUrl = UriComponentsBuilder.fromHttpUrl(sigeaPropertiesSettings.getUrlTerritorial() + "administrativeArea/" + objOrig.getString("regionCode") +"/000");
//				String comPerRegioniStringList = restTemplate.exchange(innerUrl.build().toUriString(), HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), String.class).getBody();
				
				@SuppressWarnings({ "unchecked" })
				ResponseEntity<String> res3 = null;
				try {
					res3 = (ResponseEntity<String>) new RestTemplateBuilder()
							.url(innerUrl.build().toUriString()).returnClass(String.class).oauth2Token(token).httpMethod(HttpMethod.GET)
							.build().executeRequest(false);
				} catch (RestException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String comPerRegioniStringList = res3.getBody();
				
				JSONArray tempComunePerRegione = new JSONArray(comPerRegioniStringList);
				JSONArray tempComunePerRegione2 = new JSONArray();
				tempComunePerRegione.forEach(item2 -> {
					JSONObject objOrig2 = (JSONObject) item2;
					JSONObject objConverted2 = new JSONObject();
					objConverted2.put("codComune", objOrig2.getString("municipalityCode"));
					objConverted2.put("comune", objOrig2.getString("municipalityName"));
					tempComunePerRegione2.put(objConverted2);
				});
				comuniPerRegione.put(objOrig.getString("regionCode"), tempComunePerRegione2);
			});
			
			url = UriComponentsBuilder.fromHttpUrl(sigeaPropertiesSettings.getUrlTerritorial() + "territorialAreas");
//			String areeStringList = restTemplate.exchange(url.build().toUriString(), HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), String.class).getBody();
			
			@SuppressWarnings({ "unchecked" })
			ResponseEntity<String> res4 = (ResponseEntity<String>) new RestTemplateBuilder()
					.url(url.build().toUriString()).returnClass(String.class).oauth2Token(token).httpMethod(HttpMethod.GET)
					.build().executeRequest(false);
			
			String areeStringList =  res4.getBody();
			
			JSONArray tempAreeList = new JSONArray(areeStringList);
			tempAreeList.forEach(item -> {
				JSONObject objOrig = (JSONObject) item;
				JSONObject objConverted = new JSONObject();
				objConverted.put("codArea", objOrig.getString("territorialAreaCode"));
				objConverted.put("areaTerritoriale", objOrig.getString("territorialAreaName"));
				areeList.put(objConverted);
				UriComponentsBuilder innerUrl = UriComponentsBuilder.fromHttpUrl(sigeaPropertiesSettings.getUrlTerritorial() + "administrativeArea/00").queryParam("terrAreaCode", objOrig.getString("territorialAreaCode"));
//				String provPerAreaStringList = restTemplate.exchange(innerUrl.build().toUriString(), HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), String.class).getBody();
				
				@SuppressWarnings({ "unchecked" })
				ResponseEntity<String> res5 = null;
				try {
					res5 = (ResponseEntity<String>) new RestTemplateBuilder()
							.url(innerUrl.build().toUriString()).returnClass(String.class).oauth2Token(token).httpMethod(HttpMethod.GET)
							.build().executeRequest(false);
				} catch (RestException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				String provPerAreaStringList =  res5.getBody();
				
				JSONArray tempProvincePerArea = new JSONArray(provPerAreaStringList);
				JSONArray tempProvincePerArea2 = new JSONArray();
				tempProvincePerArea.forEach(item2 -> {
					JSONObject objOrig2 = (JSONObject) item2;
					JSONObject objConverted2 = new JSONObject();
					objConverted2.put("codProvincia", objOrig2.getString("provinceCode"));
					objConverted2.put("provincia", objOrig2.getString("provinceName"));
					tempProvincePerArea2.put(objConverted2);
				});
				provincePerArea.put(objOrig.getString("territorialAreaCode"), tempProvincePerArea2);
				innerUrl = UriComponentsBuilder.fromHttpUrl(sigeaPropertiesSettings.getUrlTerritorial() + "administrativeArea/00/000").queryParam("terrAreaCode", objOrig.getString("territorialAreaCode"));
//				String comPerAreaStringList = restTemplate.exchange(innerUrl.build().toUriString(), HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), String.class).getBody();
				
				@SuppressWarnings({ "unchecked" })
				ResponseEntity<String> res6 = null;
				try {
					res6 = (ResponseEntity<String>) new RestTemplateBuilder()
							.url(innerUrl.build().toUriString()).returnClass(String.class).oauth2Token(token).httpMethod(HttpMethod.GET)
							.build().executeRequest(false);
				} catch (RestException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String comPerAreaStringList =  res6.getBody();
				
				JSONArray tempComunePerArea = new JSONArray(comPerAreaStringList);
				JSONArray tempComunePerArea2 = new JSONArray();
				tempComunePerArea.forEach(item2 -> {
					JSONObject objOrig2 = (JSONObject) item2;
					JSONObject objConverted2 = new JSONObject();
					objConverted2.put("codComune", objOrig2.getString("municipalityCode"));
					objConverted2.put("comune", objOrig2.getString("municipalityName"));
					tempComunePerArea2.put(objConverted2);
				});
				comuniPerArea.put(objOrig.getString("territorialAreaCode"), tempComunePerArea2);
			});
			
			url = UriComponentsBuilder.fromHttpUrl(sigeaPropertiesSettings.getUrlTerritorial() + "administrativeArea/00");
//			String provinceStringList = restTemplate.exchange(url.build().toUriString(), HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), String.class).getBody();
			
			@SuppressWarnings({ "unchecked" })
			ResponseEntity<String> res7 = (ResponseEntity<String>) new RestTemplateBuilder()
					.url(url.build().toUriString()).returnClass(String.class).oauth2Token(token).httpMethod(HttpMethod.GET)
					.build().executeRequest(false);
			
			String provinceStringList =  res7.getBody();
			
			JSONArray tempProvinceList = new JSONArray(provinceStringList);
			tempProvinceList.forEach(item -> {
				JSONObject objOrig = (JSONObject) item;
				JSONObject objConverted = new JSONObject();
				objConverted.put("codProvincia", objOrig.getString("provinceCode"));
				objConverted.put("provincia", objOrig.getString("provinceName"));
				provinceList.put(objConverted);
				UriComponentsBuilder innerUrl = UriComponentsBuilder.fromHttpUrl(sigeaPropertiesSettings.getUrlTerritorial() + "administrativeArea/00/" + objOrig.getString("provinceCode"));
//				String comPerProvinciaStringList = restTemplate.exchange(innerUrl.build().toUriString(), HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), String.class).getBody();
				
				@SuppressWarnings({ "unchecked" })
				ResponseEntity<String> res8 = null;
				try {
					res8 = (ResponseEntity<String>) new RestTemplateBuilder()
							.url(innerUrl.build().toUriString()).returnClass(String.class).oauth2Token(token).httpMethod(HttpMethod.GET)
							.build().executeRequest(false);
				} catch (RestException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String comPerProvinciaStringList =  res8.getBody();
				
				JSONArray tempComunePerProvincia = new JSONArray(comPerProvinciaStringList);
				JSONArray tempComunePerProvincia2 = new JSONArray();
				tempComunePerProvincia.forEach(item2 -> {
					JSONObject objOrig2 = (JSONObject) item2;
					JSONObject objConverted2 = new JSONObject();
					objConverted2.put("codComune", objOrig2.getString("municipalityCode"));
					objConverted2.put("comune", objOrig2.getString("municipalityName"));
					tempComunePerProvincia2.put(objConverted2);
				});
				comuniPerProvincia.put(objOrig.getString("provinceCode"), tempComunePerProvincia2);
			});
			
			url = UriComponentsBuilder.fromHttpUrl(sigeaPropertiesSettings.getUrlTerritorial() + "administrativeArea/00/000");
//			String comuniStringList = restTemplate.exchange(url.build().toUriString(), HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), String.class).getBody();
			
			@SuppressWarnings({ "unchecked" })
			ResponseEntity<String> res9 = (ResponseEntity<String>) new RestTemplateBuilder()
					.url(url.build().toUriString()).returnClass(String.class).oauth2Token(token).httpMethod(HttpMethod.GET)
					.build().executeRequest(false);
			
			String comuniStringList =  res9.getBody();
			
			JSONArray tempComuniList = new JSONArray(comuniStringList);
			areaPerComune = new HashMap<String, JSONObject>();
			tempComuniList.forEach(item -> {
				JSONObject objOrig = (JSONObject) item;
				JSONObject objConverted = new JSONObject();
				objConverted.put("codComune", objOrig.getString("municipalityCode"));
				objConverted.put("comune", objOrig.getString("municipalityName"));
				JSONObject objArea = new JSONObject();
				try {
					if(!objOrig.isNull("territorialAreaCode")) {
						objArea.put("codArea", objOrig.getString("territorialAreaCode"));
						objArea.put("areaTerritoriale", objOrig.getString("territorialAreaName"));
						areaPerComune.put(objOrig.getString("municipalityCode"), objArea);
					}
				}catch(Exception e) {}
				comuniList.put(objConverted);
			});
			
			lastCall = LocalDateTime.now();
		}catch(RestException e) {
			log.error("ERRORE TERRITORIAL SERVICE");
		}catch(Exception e) {
			log.error("ERRORE TERRITORIAL SERVICE");
		}
	}
}