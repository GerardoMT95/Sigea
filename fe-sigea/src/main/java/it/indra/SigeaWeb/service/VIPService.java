package it.indra.SigeaWeb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import it.indra.SigeaCommons.model.AttrattoreModel;
import it.indra.SigeaCommons.model.InfoPointModel;
import it.indra.SigeaWeb.utilities.GeneralProperties;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VIPService {
	
	@Autowired
	GeneralProperties properties;
	
	private List<AttrattoreModel> attrattoriList = new ArrayList<>();
	
	private List<InfoPointModel> infopointList = new ArrayList<>();
	
	@PostConstruct
	@Scheduled(cron = "0 0 3 * * *")
	public void getAttrattori()
	{
		log.info("Invocato metodo getAttrattori()");
		
		boolean attrattoriFromSigeo= properties.getAttrattoriSigeo();
		List<AttrattoreModel> attrattoriListNew = new ArrayList<AttrattoreModel>();
		final int limit = 5000;
		int start = 0;
		int num = limit;
		boolean repeat = true;
		Long tot = null;
		try
		{
			
			if(attrattoriFromSigeo) {
				
				log.info("Recuperando attrattori da SIGEO");

String jsonLogin = "{\"username\":\"lettoredati\",\"password\":\"admin\"}";

				 
				
				
				UriComponentsBuilder urlToken = UriComponentsBuilder.fromHttpUrl(properties.getUrlSigeoServices() +"session");
				HttpHeaders headerToken = new HttpHeaders();
				headerToken.setContentType(MediaType.APPLICATION_JSON);

				HttpEntity<String> httpEntityToken = new HttpEntity<String>(jsonLogin ,headerToken);
				
				
				RestTemplate restTemplateToken = new RestTemplate();
				String jsonStringToken = restTemplateToken.exchange(urlToken.build().toUriString(), HttpMethod.POST,httpEntityToken , String.class).getBody();
				JSONObject jsonToken = new JSONObject(jsonStringToken);
				String token = jsonToken.getString("id_token");
				
			
				UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(properties.getUrlSigeoServices() + "schede_nucleo/search" );

				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				headers.setBearerAuth(token);

				String filter = "{ \"filters\": [\r\n"
						+ "\r\n"
						+ "    {\r\n"
						+ "\r\n"
						+ "      \"condition\": \"EQUALS\",\r\n"
						+ "\r\n"
						+ "      \"field\": \"contentType.type\",\r\n"
						+ "\r\n"
						+ "      \"value\": \"LUOGO\"\r\n"
						+ "\r\n"
						+ "    },\r\n"
						+ "\r\n"
						+ "        {\r\n"
						+ "\r\n"
						+ "      \"condition\": \"EQUALS\",\r\n"
						+ "\r\n"
						+ "      \"field\": \"contentType.type\",\r\n"
						+ "\r\n"
						+ "      \"value\": \"PORTO_TURISTICO\"\r\n"
						+ "\r\n"
						+ "    }\r\n"
						+ "\r\n"
						+ " \r\n"
						+ "\r\n"
						+ "  ],\r\n"
						+ "\r\n"
						+ "  \"groups\": [],\r\n"
						+ "\r\n"
						+ "  \"joinOperator\": \"OR\"\r\n"
						+ "\r\n"
						+ "}";
				HttpEntity<String> httpEntity = new HttpEntity<String>(filter ,headers);
				
				
				RestTemplate restTemplate = new RestTemplate();
				String jsonString = restTemplate.exchange(url.build().toUriString(), HttpMethod.POST, httpEntity, String.class).getBody();
				

				
				JSONObject jsonResponse = new JSONObject(jsonString);
				JSONArray jsonList = jsonResponse.getJSONArray("items");
				if (tot == null) { tot = jsonResponse.getLong("totalItems"); }
				jsonList.forEach(item -> {
				    JSONObject obj = (JSONObject) item;
				    Long attrattoreId = obj.getLong("id");
				    
				    JSONObject campiTxt =   obj.getJSONObject("campiTestuali");
				    JSONObject campiAnagrafica =   obj.getJSONObject("anagrafica");
				    JSONObject campiComune=   campiAnagrafica.getJSONObject("comune");
				    String etichetta = campiTxt.getString("denominazione") + " - " + campiComune.getString("descrizione");
				    AttrattoreModel model = new AttrattoreModel();
				    model.setAttrattoreId(attrattoreId);
				    model.setEtichetta(etichetta);
				    attrattoriListNew.add(model);
				});
			
			if (!attrattoriListNew.isEmpty()) {
				attrattoriList = attrattoriListNew;
			}
			
			}else
			{
				log.info("Recuperando attrattori da VIP");
				while (repeat) {
					UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(properties.getUrlVipServices() + "attractors/all?language=it&start="+start+"&num="+num);
					RestTemplate restTemplate = new RestTemplate();
					String jsonString = restTemplate.exchange(url.build().toUriString(), HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), String.class).getBody();
					JSONObject jsonResponse = new JSONObject(jsonString);
					JSONArray jsonList = jsonResponse.getJSONArray("docs");
					if (tot == null) { tot = jsonResponse.getLong("numFound"); }
					jsonList.forEach(item -> {
					    JSONObject obj = (JSONObject) item;
					    Long attrattoreId = obj.getLong("id");
					    String etichetta = obj.getString("title") + " - " + obj.getString("locality");
					    AttrattoreModel model = new AttrattoreModel();
					    model.setAttrattoreId(attrattoreId);
					    model.setEtichetta(etichetta);
					    attrattoriListNew.add(model);
					});
					tot -= limit;
					if (start == 0) { start += 1; }
					start += limit;
					num += limit;
					if(tot <= 0) { repeat = false; }
				}
				if (!attrattoriListNew.isEmpty()) {
					attrattoriList = attrattoriListNew;
				}
			}
		}
		catch (HttpClientErrorException e)
		{
			log.error(e.getLocalizedMessage());
		}
		catch (Exception e)
		{
			log.error(e.getLocalizedMessage());
		}
	}
	
	@PostConstruct
	@Scheduled(cron = "0 0 3 * * *")
	public void getInfoPoint() {
		log.info("Invocato metodo getInfoPoint()");
		List<InfoPointModel> infopointListNew = new ArrayList<InfoPointModel>();
		try
		{
			UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(properties.getUrlVipServices() + "iat/all?language=it");
			RestTemplate restTemplate = new RestTemplate();
			String jsonString = restTemplate.exchange(url.build().toUriString(), HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), String.class).getBody();
			JSONObject jsonResponse = new JSONObject(jsonString);
			JSONArray jsonList = jsonResponse.getJSONArray("docs");
			jsonList.forEach(item -> {
				JSONObject obj = (JSONObject) item;
				Long infopointId = obj.getLong("id");
			    String title = obj.getString("title");
			    InfoPointModel model = new InfoPointModel();
			    model.setInfopointId(infopointId);
			    model.setTitle(title);
			    infopointListNew.add(model);
			});
			if (!infopointListNew.isEmpty()) {
				infopointList = infopointListNew;
			}
		}catch (HttpClientErrorException e)
		{
			log.error(e.getLocalizedMessage());
		}
		catch (Exception e)
		{
			log.error(e.getLocalizedMessage());
		}
	}
	
	public List<AttrattoreModel> getAttrattoreByTitle(String s) {
		return attrattoriList.stream().filter(a -> a.getEtichetta().toUpperCase().contains(s)).collect(Collectors.toList());
	}
	
	//TODO inutilizzato
	public List<InfoPointModel> getInfoPointByTitle(String s){
		return infopointList.stream().filter(i -> i.getTitle().toUpperCase().contains(s)).collect(Collectors.toList());
	}
	
	public List<InfoPointModel> getAllInfoPoint(){
		return infopointList;
	}

}
