package it.indra.SigecAPI.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.indra.es.utils.RestUtility;

import it.puglia.spc.ect.commons.mail.dto.AttachmentDTO;
import it.puglia.spc.ect.commons.mail.dto.MailDTO;
import lombok.extern.log4j.Log4j2;


@Service
@Log4j2
public class MailService {
	
	@Value("${jms.urlservice}")
	private String urlservice;
	
	@Value("${DIRECTURL.MAIL.BASEPATH}")
	private String urlServiceMail;
	
	@Value("${jms.id}")
	private String jmsId;
	
	@Value("${jms.templateName}")
	private String templateName;
	
	@Value("${jms.tenant.id}")
	private String tenantId;
	
	@Value("${jms.mail.engine.name}")
	private String engine;
	
	@Value("${jms.mail.from}")
	private String from;
	
	@Value("${jms.queue.channel}")
	private String masterQueue;
	
	@Value("#{${jms.params}}")  
	private Map<String,String> params;

	@Autowired
	private ResourceLoader resourceLoader;
	
	public void sendEmail(String oggetto, String jsonContenuto, Set<String> listaDestinatari, Set<String> listaCC, Set<String> listaCCN, String idTemplate, String jwt) throws JsonGenerationException, JsonMappingException, IOException {
		log.info("Entrato send mail");
		
		Set<String> listaDestinatariPulita = new HashSet<String>();
		Set<String> listaCCPulita = new HashSet<String>();
		Set<String> listaCCNPulita = new HashSet<String>();
		for(String email : listaDestinatari) {
			if(email!=null && !email.trim().isEmpty()) {
				listaDestinatariPulita.add(email);
			}
		}
		if (listaCC!=null) {
			for(String email : listaCC) {
				if(email!=null && !email.trim().isEmpty()) {
					listaCCPulita.add(email);
				}
			}
		}
		if (listaCCN!=null) {
			for(String email : listaCCN) {
				if(email!=null && !email.trim().isEmpty()) {
					listaCCNPulita.add(email);
				}
			}
		}
		listaDestinatari = listaDestinatariPulita;
		if(listaDestinatari.isEmpty()) {
			log.info("LISTA DESTINATARI VUOTA, EMAIL NON INVIATA");
			return;
		}
		
		MailDTO mail=new MailDTO();
		mail.setBcc(new ArrayList<String>(listaCCNPulita));
		mail.setCc(new ArrayList<String>(listaCCPulita));
		mail.setEmail(new ArrayList<String>(listaDestinatariPulita));
		mail.setMailSubject(oggetto);
		mail.setHtmlId(idTemplate);
		mail.setHtmlContext("eventi");
		mail.setHtmlJsonBody(jsonContenuto);
		mail.setHtmlTemplate(true);
		
		try {
			RestUtility.builder().url(urlServiceMail+"mail/send").bearerToken(jwt).body(mail).httpMethod(HttpMethod.POST).build()
			.executeRequest();
		} catch (Exception e) {
			log.error("ESITO CHIAMATA INVIO EMAIL:", e);
		}	
	
	}

	public void sendEmailAttachment(String oggetto, String jsonContenuto, Set<String> listaDestinatari, Set<String> listaCC, Set<String> listaCCN, String idTemplate,  String jwt , List<AttachmentDTO> allegati) throws JsonGenerationException, JsonMappingException, IOException 
	{
		log.info("Entrato send mail attachment");
		
		
		
		Set<String> listaDestinatariPulita = new HashSet<String>();
		Set<String> listaCCPulita = new HashSet<String>();
		Set<String> listaCCNPulita = new HashSet<String>();
		for(String email : listaDestinatari) {
			if(email!=null && !email.trim().isEmpty()) {
				listaDestinatariPulita.add(email);
			}
		}
		if (listaCC!=null) {
			for(String email : listaCC) {
				if(email!=null && !email.trim().isEmpty()) {
					listaCCPulita.add(email);
				}
			}
		}
		if (listaCCN!=null) {
			for(String email : listaCCN) {
				if(email!=null && !email.trim().isEmpty()) {
					listaCCNPulita.add(email);
				}
			}
		}
		listaDestinatari = listaDestinatariPulita;
		if(listaDestinatari.isEmpty()) {
			log.info("LISTA DESTINATARI VUOTA, EMAIL NON INVIATA");
			return;
		}
		
		MailDTO mail=new MailDTO();
		mail.setBcc(new ArrayList<String>(listaCCNPulita));
		mail.setCc(new ArrayList<String>(listaCCPulita));
		mail.setEmail(new ArrayList<String>(listaDestinatariPulita));
		mail.setMailSubject(oggetto);
		mail.setHtmlId(idTemplate);
		mail.setHtmlContext("eventi");
		mail.setHtmlJsonBody(jsonContenuto);
		mail.setHtmlTemplate(true);
		mail.setAttachment(allegati);
		
		try {
			RestUtility.builder().url(urlServiceMail+"mail/send").bearerToken(jwt).body(mail).httpMethod(HttpMethod.POST).build()
			.executeRequest();
		} catch (Exception e) {
			log.error("ESITO CHIAMATA INVIO EMAIL:", e);
		}	
	
	}
}
