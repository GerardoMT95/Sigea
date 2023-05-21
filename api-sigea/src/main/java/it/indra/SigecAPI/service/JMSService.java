package it.indra.SigecAPI.service;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.indra.es.utils.LogUtility;

import it.indra.SigeaCommons.model.PubblicazioneModel;
import it.indra.SigeaCommons.model.redazioni.VIPSchedaModel;
import it.indra.SigecAPI.exception.JMSSigeaException;
import lombok.extern.log4j.Log4j2;
@Log4j2
@Service
public class JMSService {
	
	private static final String SCHEDA_EVENTO = "evento";
	private static final String SCHEDA_ATTIVITA = "attivita";
	
	@Value("${jms.urlservice}")
	private String urlservice;
	
	@Value("${jms.polling}")
	private String pollingString;
	
	@Value("${jms.timeout}")
	private String pollingTimeoutString;
	
	@Resource(name = "jms-template-queue")
	private JmsTemplate jmsTemplate;

	@Value("${jms.queue.evento}")
	private String destinationEvento;
	
	@Value("${jms.queue.attivita}")
	private String destinationAttivita;
	

	@Value("${jms.queue.newVip.evento}")
	private String destinazioneNewVipEvento;
	
	@Value("${jms.queue.newVip.attivita}")
	private String destinazioneNewVipAttivita;
	
	@Value("${jms.newVipEnabled}")
	private boolean newVipEnabled;

	
	@Autowired
	CacheTokenService cacheTokenService;
	
	/**
	 * Sends a message to a JMS queue.
	 * @param message  message to be sent
	 * @throws JsonProcessingException 
	 * @throws InterruptedException 
	 * @throws TimeoutException 
	 * @throws Exception 
	 * 
	 */
	public String sendMessageToJms(PubblicazioneModel modelPubblicazione) throws JMSSigeaException, JsonProcessingException, InterruptedException, TimeoutException {
		
		try {
		String token=cacheTokenService.getToken();
		
		//Recupero UUID
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer "+token);
		log.info("TOKEN PER JMS : " + token);
	UriComponentsBuilder urlUUID = UriComponentsBuilder.fromHttpUrl(urlservice + "/api/createUUID");
		String stringStatsUUID = restTemplate.exchange(urlUUID.build().toUriString(), HttpMethod.POST, new HttpEntity<>(headers), String.class).getBody();
		
		
		JSONObject obj = new JSONObject(stringStatsUUID);
		String statsUUID = obj.getString("code");
		modelPubblicazione.setStatsUUID(statsUUID);
		
		//Invoco JMS
		ObjectMapper mapper = new ObjectMapper();
		JsonNode genericMetadata = modelPubblicazione.getGenericMetadata();
	
		VIPSchedaModel vipSchedaModel = mapper.treeToValue(genericMetadata, VIPSchedaModel.class);
		
		String tipoScheda = vipSchedaModel.getTipoScheda();
		
		log.info("Invio {} a sigea2Vip", tipoScheda);
		String payload = mapper.writeValueAsString(modelPubblicazione);
		
		log.info("Sigea2Vip payload: {}", payload);
		switch (tipoScheda) {
		case SCHEDA_EVENTO:
			log.info("Sigea2Vip " + SCHEDA_EVENTO);
			jmsTemplate.convertAndSend(destinationEvento, payload);
			break;
		case SCHEDA_ATTIVITA:
			log.info("Sigea2Vip " + SCHEDA_ATTIVITA);
			jmsTemplate.convertAndSend(destinationAttivita, payload);
			break;
		default:
			log.error("Tipo scheda {} non riconosciuto", tipoScheda);
		}
		
		
		
		
		log.info("polling JMS con uuid :" + statsUUID);
		
		
		//Eseguo il polling per ottenere un feedback dal jms
		String result = null;
		String idVip="-1";
		int i = 0;
		int TIMEOUT = Integer.parseInt(pollingTimeoutString);
		long polling = Integer.parseInt(pollingString);
		UriComponentsBuilder urlCheckStato = UriComponentsBuilder.fromHttpUrl(urlservice + "/stats/" + statsUUID);
		while (i < TIMEOUT) {
			
			
			log.info("urlCheckStato ="  + urlservice + "/stats/" + statsUUID);
			
			token=cacheTokenService.getToken();
			headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", "Bearer "+token);
			
			String stringCheckStato = null;
			try {
				log.info("Verificando stato allineamentoVIP evento "+modelPubblicazione.getEventoId()+"...");
				stringCheckStato = restTemplate.exchange(urlCheckStato.build().toUriString(), HttpMethod.GET, new HttpEntity<>(headers), String.class).getBody();
			}catch (HttpStatusCodeException exception){
				log.error("POLLING JMS NON RISPONDE {}", exception.getMessage());
				log.error(LogUtility.exceptionToLog(exception));
				
				stringCheckStato = null;
			}
			obj = new JSONObject(stringCheckStato);
			result = obj.getString("status");

			if (stringCheckStato!= null && result.equals("COMPLETED")) {
	        	log.info("AllineamentoVIP evento "+modelPubblicazione.getEventoId()+": COMPLETED");
	            break;
	        } else if (stringCheckStato!= null && result.equals("ERROR")) {
	        	log.info("AllineamentoVIP evento "+modelPubblicazione.getEventoId()+": ERROR");
	        	log.error("ERRORE JMS {}", stringCheckStato);
	        	throw new JMSSigeaException("Il JMS non è riuscito a completare l'operazione di scrittura a causa di un errore");
	        } else {
	            TimeUnit.SECONDS.sleep(polling);
	            ++i;
	            if (i == TIMEOUT) {
	            	log.info("AllineamentoVIP evento "+modelPubblicazione.getEventoId()+": TIMEOUT");
	            	log.error("ERRORE JMS {}", stringCheckStato);
	                throw new TimeoutException("Il JMS non è riuscito a completare l'operazione entro i " + (polling * TIMEOUT) + " secondi a disposizione");
	            }
	        }
	    }
		
		try {
			String description = obj.getString("description");
			idVip = description.substring((description.indexOf("idVip:")+6), description.indexOf(",")); // da 0, incluso-non incluso
			log.info("idVip CR " + idVip);
		}catch (Exception exception){
			log.error("idVip non disponibile: ", exception.getMessage());
		}
		
		try {
			if (newVipEnabled) {
				log.info("*****INVIA A NEW VIP*****");
				switch (tipoScheda) {
				case SCHEDA_EVENTO:
					log.info("Sigea2NewVip " + SCHEDA_EVENTO);
					jmsTemplate.convertAndSend(destinazioneNewVipEvento, payload);
					break;
				case SCHEDA_ATTIVITA:
					log.info("Sigea2NewVip " + SCHEDA_ATTIVITA);
					jmsTemplate.convertAndSend(destinazioneNewVipAttivita, payload);
					break;
				default:
					log.error("Tipo scheda {} non riconosciuto", tipoScheda);
				}
			}
		}catch (Exception exception){
			log.error("ERRORE INVIO A NEW VIP: " + exception.getMessage());
		}
		
		return idVip;
		}
		catch (Exception e) {
			log.error(LogUtility.exceptionToLog(e));
			return null;
		}
	}

}
