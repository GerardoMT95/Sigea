package it.indra.SigecAPI.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.text.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.indra.es.utils.LogUtility;

import it.indra.SigeaCommons.model.PubblicazioneModel;
import it.indra.SigecAPI.clientstub.DMSClientStub;
import it.indra.SigecAPI.entity.DettaglioUtente;
import it.indra.SigecAPI.entity.Pubblicazione;
import it.indra.SigecAPI.mapper.PubblicazioneMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AsyncService {

	private static final String PLACEHOLDER = "XXXXX";

	@Autowired
	private JMSService jmsService;

	@Autowired
	private MailService mailService;

	@Autowired
	private DMSClientStub dmsClientStub;

	@Autowired
	private PubblicazioneMapper pubblicazioneMapper;

	@Value("${jms.mail.configurazioneEmail}")
	private String configurazioneEmailString;

	@Value("#{'${mail.pugliapromozione}'.split(',')}")
	List<String> mailBackoffice;

	@Value("${jms.emailConduzione}")
	private String emailCoduzione;

	private JSONArray configurazioneEmail;

	@Autowired
	CacheTokenService cacheTokenService;

	@PostConstruct
	public void inizializzaConfigurazioneEmail() {
		configurazioneEmail = new JSONArray(configurazioneEmailString);
	}

	@Async
	public void invioMailSegnalazione(String titoloSegnalazione, String userEmail) {

		String content = "{\r\n" + "  \"titolo\": \"" + StringEscapeUtils.escapeJava(titoloSegnalazione) + "\",\r\n"
				+ "  \"empty\": false\r\n" + "}";
		String oggettoEmail = null;
		String templateId = null;
		for (int i = 0; i < configurazioneEmail.length(); i++) {
			JSONObject email = configurazioneEmail.getJSONObject(i);
			if (email.getString("id").equals("Segnalazione")) {
				oggettoEmail = email.getString("oggetto");
				templateId = email.getString("id");
				break;
			}
		}
		try {
			String token = cacheTokenService.getToken();
			mailService.sendEmail(oggettoEmail, content, new HashSet<>(Arrays.asList(userEmail)), null,
					new HashSet<>(mailBackoffice), templateId, token);
		} catch (Exception e) {
			log.error("IMPOSSIBILE INVIARE EMAIL: " + e.getMessage());log.error(LogUtility.exceptionToLog(e));
			
		}
	}

	@Async
	public void invioMailConseguenteJMSSpubblicazioneTotale(Set<Pubblicazione> spubblicazioniJMS, String content,
			String emailOwner, String emailRedattori, DettaglioUtente chiamante, List<String> emailAddressOwner,
			Set<String> tipologieRedazionali, boolean flagSpubblicazione) throws Exception {

		log.info("Entrato cambio stato evento [flagSpubblicazione:" + flagSpubblicazione + ",emailOwner:" + emailOwner
				+ ",emailRedattori:" + emailRedattori + ",chiamante.mail:" + chiamante.getEmail()
				+ ",emailAddressOwner:" + emailAddressOwner + "]");

		String token = null;

		if (flagSpubblicazione) {
			// eventuale spubblicazione
			if (!spubblicazioniJMS.isEmpty()) {
				log.info("Entrato spubblicazione jms");
				for (Pubblicazione pubblicazione : spubblicazioniJMS) {
					log.info("Entrato !flagSpubblicazione || spubblicazioniJMS.isEmpty(): emailRedattori= "
							+ emailRedattori + ", emailOwner= " + emailOwner + ", spubblicazioniJms="
							+ pubblicazione.getRedazione().getRedazioneId());
					log.info("SPUBBLICAZIONE SU JMS NECESSARIA A CAUSA DI SPUBBLICAZIONE GENERALE EVENTO");
					try {
						jmsService.sendMessageToJms(pubblicazioneMapper.entityToDto(pubblicazione));
					} catch (Exception e) {
						String oggettoEmail = null;
						String templateId = null;
						for (int i = 0; i < configurazioneEmail.length(); i++) {
							JSONObject email = configurazioneEmail.getJSONObject(i);
							if (email.getString("id").equals("ErroreSpubblicazione")) {
								oggettoEmail = email.getString("oggetto");
								templateId = email.getString("id");
								break;
							}
						}

						try {
							token = cacheTokenService.getToken();
							mailService.sendEmail(oggettoEmail, content,
									new HashSet<>(Arrays.asList(chiamante.getEmail())), null,
									new HashSet<>(Arrays.asList(emailCoduzione)), templateId, token);
						} catch (Exception e2) {
							log.error("IMPOSSIBILE INVIARE EMAIL: " + e2.getMessage());
							log.error(LogUtility.exceptionToLog(e2));
						}
						throw e;
					}

					// gestisco invio mail
					if (emailOwner != null && !emailOwner.isEmpty()) {
						String oggettoEmail = null;
						String templateId = null;
						for (int i = 0; i < configurazioneEmail.length(); i++) {
							JSONObject email = configurazioneEmail.getJSONObject(i);
							if (email.getString("id").equals(emailOwner)) {
								oggettoEmail = email.getString("oggetto");
								templateId = email.getString("id");
								break;
							}
						}
						try {
							token = cacheTokenService.getToken();
							mailService.sendEmail(oggettoEmail, content, new HashSet<>(emailAddressOwner), null,
									new HashSet<>(Arrays.asList(chiamante.getEmail())), templateId, token);
						} catch (Exception e) {
							log.error("IMPOSSIBILE INVIARE EMAIL: " + e.getMessage());
						}
					}
					if (emailRedattori != null && !emailRedattori.isEmpty()) {
						String oggettoEmail = null;
						String templateId = null;
						for (int i = 0; i < configurazioneEmail.length(); i++) {
							JSONObject email = configurazioneEmail.getJSONObject(i);
							if (email.getString("id").equals(emailRedattori)) {
								oggettoEmail = email.getString("oggetto");
								templateId = email.getString("id");
								break;
							}
						}
						try {
							token = cacheTokenService.getToken();

							Set<String> mailTo = dmsClientStub.getEmailRedattori(token, tipologieRedazionali);
							log.info("Mail redattori:" + mailTo);

							mailService.sendEmail(oggettoEmail, content, mailTo, null, null, templateId, token);
						} catch (Exception e) {
							log.error("IMPOSSIBILE INVIARE EMAIL: " + e.getMessage());
							log.error(LogUtility.exceptionToLog(e));
						}
					}
				}
			}
		}

		// IN CASO DI CAMBIO STATO SENZA SPUBBLICAZIONE DA VIP MANDO COMUNQUE L'EMAIL
		if ((!flagSpubblicazione || spubblicazioniJMS.isEmpty())) {
			log.info("Entrato !flagSpubblicazione || spubblicazioniJMS.isEmpty(): emailRedattori= " + emailRedattori
					+ ", emailOwner= " + emailOwner);
			// gestisco invio mail
			if (emailOwner != null && !emailOwner.isEmpty()) {
				String oggettoEmail = null;
				String templateId = null;
				for (int i = 0; i < configurazioneEmail.length(); i++) {
					JSONObject email = configurazioneEmail.getJSONObject(i);
					if (email.getString("id").equals(emailOwner)) {
						oggettoEmail = email.getString("oggetto");
						templateId = email.getString("id");
						break;
					}
				}
				try {
					token = cacheTokenService.getToken();
					mailService.sendEmail(oggettoEmail, content, new HashSet<>(emailAddressOwner), null,
							new HashSet<>(Arrays.asList(chiamante.getEmail())), templateId, token);
				} catch (Exception e) {
					log.error("IMPOSSIBILE INVIARE EMAIL: " + e.getMessage());
					log.error(LogUtility.exceptionToLog(e));
				}
			}
			if (emailRedattori != null && !emailRedattori.isEmpty() && !emailRedattori.equals("Revocato")) {
				String oggettoEmail = null;
				String templateId = null;
				for (int i = 0; i < configurazioneEmail.length(); i++) {
					JSONObject email = configurazioneEmail.getJSONObject(i);
					if (email.getString("id").equals(emailRedattori)) {
						oggettoEmail = email.getString("oggetto");
						templateId = email.getString("id");
						break;
					}
				}
				try {
					token = cacheTokenService.getToken();
					Set<String> mailTo = dmsClientStub.getEmailRedattori(token, tipologieRedazionali);
					log.info("Mail redattori:" + mailTo);

					mailService.sendEmail(oggettoEmail, content, mailTo, null, null, templateId, token);
				} catch (Exception e) {
					log.error("IMPOSSIBILE INVIARE EMAIL: " + e.getMessage());
					log.error(LogUtility.exceptionToLog(e));
				}
			}

		}

	}

	@Async
	public void invioMailConseguenteJMSSpubblicazioneTotaleModificaOwner(Set<Pubblicazione> spubblicazioniJMS,
			String content, String emailOwner, String emailRedattori, DettaglioUtente chiamante,
			String emailAddressOwner, Set<String> tipologieRedazionali, boolean flagSpubblicazione) throws Exception {

		log.info("Entrato cambio stato evento [flagSpubblicazione:" + flagSpubblicazione + ",emailOwner:" + emailOwner
				+ ",emailRedattori:" + emailRedattori + ",chiamante.mail:" + chiamante.getEmail()
				+ ",emailAddressOwner:" + emailAddressOwner + "]");

		String token = null;

		if (flagSpubblicazione) {
			// eventuale spubblicazione
			if (!spubblicazioniJMS.isEmpty()) {
				log.info("Entrato spubblicazione jms");
				for (Pubblicazione pubblicazione : spubblicazioniJMS) {
					log.info("SPUBBLICAZIONE SU JMS NECESSARIA A CAUSA DI SPUBBLICAZIONE GENERALE EVENTO");
					log.info("STATO PUBBLICAZIONE : " + pubblicazione.getStatoPubblicazione().getStatoEvento());
					try {
						
						jmsService.sendMessageToJms(pubblicazioneMapper.entityToDto(pubblicazione));
					} catch (Exception e) {
						String oggettoEmail = null;
						String templateId = null;
						for (int i = 0; i < configurazioneEmail.length(); i++) {
							JSONObject email = configurazioneEmail.getJSONObject(i);
							if (email.getString("id").equals("ErroreSpubblicazione")) {
								oggettoEmail = email.getString("oggetto");
								templateId = email.getString("id");
								break;
							}
						}

						try {
							token = cacheTokenService.getToken();
							mailService.sendEmail(oggettoEmail, content,
									new HashSet<>(Arrays.asList(chiamante.getEmail())), null,
									new HashSet<>(Arrays.asList(emailCoduzione)), templateId, token);
						} catch (Exception e2) {
							log.error("IMPOSSIBILE INVIARE EMAIL: " + e2.getMessage());
							log.error(LogUtility.exceptionToLog(e2));
						}
						throw e;
					}

				}

				if (emailRedattori != null && !emailRedattori.isEmpty()) {
					String oggettoEmail = null;
					String templateId = null;
					for (int i = 0; i < configurazioneEmail.length(); i++) {
						JSONObject email = configurazioneEmail.getJSONObject(i);
						if (email.getString("id").equals(emailRedattori)) {
							oggettoEmail = email.getString("oggetto");
							templateId = email.getString("id");
							break;
						}
					}
					try {
						token = cacheTokenService.getToken();

						Set<String> mailTo = dmsClientStub.getEmailRedattori(token, tipologieRedazionali);
						log.info("Mail redattori:" + mailTo);

						mailService.sendEmail(oggettoEmail, content, mailTo, null, null, templateId, token);
					} catch (Exception e) {
						log.error("IMPOSSIBILE INVIARE EMAIL: " + e.getMessage());
						log.error(LogUtility.exceptionToLog(e));
					}
				}
			}

		}

	}
	
	
	
	

	@Async
	public void invioMailConseguenteJMSPubblicazione(String content, Pubblicazione pubblicazione, String idRedazione,
			String emailChiamante, List<String> emailOwner) throws Exception {

		String token = null;
		String idVip = "-1"; // settato con numero negativo per i controlli
		PubblicazioneModel pubblicazioneJMS = pubblicazioneMapper.entityToDto(pubblicazione);
		if (pubblicazioneJMS != null) {
			try {
				idVip = jmsService.sendMessageToJms(pubblicazioneJMS);
			} catch (Exception e) {
				if (content != null) {
					String oggettoEmail = null;
					String templateId = null;
					for (int i = 0; i < configurazioneEmail.length(); i++) {
						JSONObject email = configurazioneEmail.getJSONObject(i);
						if (email.getString("id").equals("ErrorePubblicazione")) {
							oggettoEmail = email.getString("oggetto");
							templateId = email.getString("id");
							break;
						}
					}
					try {
						token = cacheTokenService.getToken();

						if (!idVip.equalsIgnoreCase("-1")) {
							content = content.replace(PLACEHOLDER, idVip);
						} else {
							log.error("idVip negativo effettuare verifiche");
							
						}
						log.debug("content CR " + content);

						List<String> listaMailConduzione = Arrays.asList(emailCoduzione);
						List<String> listaMailChiamante = Arrays.asList(emailChiamante);
//						if(listaMailConduzione.contains(emailChiamante))
						for (String mailC : listaMailChiamante) {
							listaMailConduzione.remove(mailC);
						}

						mailService.sendEmail(oggettoEmail, content, new HashSet<>(Arrays.asList(emailChiamante)), null,
								new HashSet<>(listaMailConduzione), templateId, token);
					} catch (Exception e2) {
						log.error("IMPOSSIBILE INVIARE EMAIL: " + e2.getMessage());
						log.error(LogUtility.exceptionToLog(e2));
					}
				}
				throw e;
			}
			if (content != null && !idRedazione.equals("GENERAL")) {
				String oggettoEmail = null;
				String templateId = null;
				for (int i = 0; i < configurazioneEmail.length(); i++) {
					JSONObject email = configurazioneEmail.getJSONObject(i);
					if (email.getString("id").equals("Pubblicato")) {
						oggettoEmail = email.getString("oggetto");
						templateId = email.getString("id");
						break;
					}
				}
				try {
					token = cacheTokenService.getToken();

					if (!idVip.equalsIgnoreCase("-1")) {
						content = content.replace(PLACEHOLDER, idVip);
					} else {
						log.error("idVip negativo effettuare verifiche");
						;
					}
					log.debug("content CR " + content);

					mailService.sendEmail(oggettoEmail, content, new HashSet<>(emailOwner), null, null, templateId,
							token);
				} catch (Exception e) {
					log.error("IMPOSSIBILE INVIARE EMAIL: " + e.getMessage());
					log.error(LogUtility.exceptionToLog(e));
				}
			}
		}
	}

	@Async
	public void invioJMSSpubblicazione(Pubblicazione pubblicazione, Long idEvento, String idRedazione,
			String emailChiamante) throws Exception {

		String token = null;

		String titolo = pubblicazione.getEventoModel().getDatiGenerali().getTitoloMulti().get("IT");

		String content = "{\r\n" + "  \"titolo\": \"" + StringEscapeUtils.escapeJava(titolo) + "\",\r\n"
				+ "  \"id\": \"" + idEvento + "\",\r\n" + "  \"empty\": false\r\n" + "}";

		PubblicazioneModel pubblicazioneJMS = pubblicazioneMapper.entityToDto(pubblicazione);
		if (pubblicazioneJMS != null) {
			try {
				jmsService.sendMessageToJms(pubblicazioneJMS);
			} catch (Exception e) {
				String oggettoEmail = null;
				String templateId = null;
				for (int i = 0; i < configurazioneEmail.length(); i++) {
					JSONObject email = configurazioneEmail.getJSONObject(i);
					if (email.getString("id").equals("ErroreSpubblicazione")) {
						oggettoEmail = email.getString("oggetto");
						templateId = email.getString("id");
						break;
					}
				}
				try {
					token = cacheTokenService.getToken();
					mailService.sendEmail(oggettoEmail, content, new HashSet<>(Arrays.asList(emailChiamante)), null,
							new HashSet<>(Arrays.asList(emailCoduzione)), templateId, token);
				} catch (Exception e2) {
					log.error("IMPOSSIBILE INVIARE EMAIL: " + e2.getMessage());
					log.error(LogUtility.exceptionToLog(e2));
				}
				throw e;
			}
			if (emailChiamante != null && !emailChiamante.isEmpty()) {
				String oggettoEmail = null;
				String templateId = null;
				for (int i = 0; i < configurazioneEmail.length(); i++) {
					JSONObject email = configurazioneEmail.getJSONObject(i);
					if (email.getString("id").equals("Revocato")) {
						oggettoEmail = email.getString("oggetto");
						templateId = email.getString("id");
						break;
					}
				}
				try {
					token = cacheTokenService.getToken();
					mailService.sendEmail(oggettoEmail, content, new HashSet<>(Arrays.asList(emailChiamante)), null,
							null, templateId, token);
				} catch (Exception e) {
					log.error("IMPOSSIBILE INVIARE EMAIL: " + e.getMessage());
					log.error(LogUtility.exceptionToLog(e));
				}
			}
		}

	}

	@Async
	public void invioMailConseguenteJMSRipubblicazione(String content, Pubblicazione pubblicazione, String idRedazione,
			String emailChiamante, List<String> emailOwner) throws Exception {

		String token = null;
		String idVip = "-1"; // settato con numero negativo per i controlli
		PubblicazioneModel pubblicazioneJMS = pubblicazioneMapper.entityToDto(pubblicazione);
		if (pubblicazioneJMS != null) {
			try {
				idVip = jmsService.sendMessageToJms(pubblicazioneJMS);
			} catch (Exception e) {
				if (content != null) {
					String oggettoEmail = null;
					String templateId = null;
					for (int i = 0; i < configurazioneEmail.length(); i++) {
						JSONObject email = configurazioneEmail.getJSONObject(i);
						if (email.getString("id").equals("ErrorePubblicazione")) {
							oggettoEmail = email.getString("oggetto");
							templateId = email.getString("id");
							break;
						}
					}
					try {
						token = cacheTokenService.getToken();

						if (!idVip.equalsIgnoreCase("-1")) {
							content = content.replace(PLACEHOLDER, idVip);
						} else {
							log.error("idVip negativo effettuare verifiche");
							;
						}
						log.debug("content CR " + content);

						List<String> listaMailConduzione = Arrays.asList(emailCoduzione);
						List<String> listaMailChiamante = Arrays.asList(emailChiamante);
//						if(listaMailConduzione.contains(emailChiamante))
						for (String mailC : listaMailChiamante) {
							listaMailConduzione.remove(mailC);
						}

						mailService.sendEmail(oggettoEmail, content, new HashSet<>(Arrays.asList(emailChiamante)), null,
								new HashSet<>(listaMailConduzione), templateId, token);
					} catch (Exception e2) {
						log.error("IMPOSSIBILE INVIARE EMAIL: " + e2.getMessage());
						log.error(LogUtility.exceptionToLog(e2));
					}
				}
				throw e;
			}
			if (content != null && !idRedazione.equals("GENERAL")) {
				String oggettoEmail = null;
				String templateId = null;
				for (int i = 0; i < configurazioneEmail.length(); i++) {
					JSONObject email = configurazioneEmail.getJSONObject(i);
					if (email.getString("id").equals("Ripubblicato")) {
						oggettoEmail = email.getString("oggetto");
						templateId = email.getString("id");
						break;
					}
				}
				try {
					token = cacheTokenService.getToken();

					if (!idVip.equalsIgnoreCase("-1")) {
						content = content.replace(PLACEHOLDER, idVip);
					} else {
						log.error("idVip negativo effettuare verifiche");
						;
					}
					log.debug("content CR " + content);

					mailService.sendEmail(oggettoEmail, content, new HashSet<>(emailOwner), null, null, templateId,
							token);
				} catch (Exception e) {
					log.error("IMPOSSIBILE INVIARE EMAIL: " + e.getMessage());
					log.error(LogUtility.exceptionToLog(e));
				}
			}
		}

	}
	
	
	@Async
	public void invioMailConseguenteJMSSpubblicazioneTotaleModificaOwnerUseModel(Set<PubblicazioneModel> spubblicazioniJMS,
			String content, String emailOwner, String emailRedattori, DettaglioUtente chiamante,
			String emailAddressOwner, Set<String> tipologieRedazionali, boolean flagSpubblicazione) throws Exception {

		log.info("Entrato cambio stato evento [flagSpubblicazione:" + flagSpubblicazione + ",emailOwner:" + emailOwner
				+ ",emailRedattori:" + emailRedattori + ",chiamante.mail:" + chiamante.getEmail()
				+ ",emailAddressOwner:" + emailAddressOwner + "]");

		String token = null;

		if (flagSpubblicazione) {
			// eventuale spubblicazione
			if (!spubblicazioniJMS.isEmpty()) {
				log.info("Entrato spubblicazione jms");
				for (PubblicazioneModel pubblicazione : spubblicazioniJMS) {
					log.info("SPUBBLICAZIONE SU JMS NECESSARIA A CAUSA DI SPUBBLICAZIONE GENERALE EVENTO");
					log.info("STATO PUBBLICAZIONE : " + pubblicazione.getStatoPubblicazione().getStatoEvento());
					try {
						jmsService.sendMessageToJms((pubblicazione));
					} catch (Exception e) {
						String oggettoEmail = null;
						String templateId = null;
						for (int i = 0; i < configurazioneEmail.length(); i++) {
							JSONObject email = configurazioneEmail.getJSONObject(i);
							if (email.getString("id").equals("ErroreSpubblicazione")) {
								oggettoEmail = email.getString("oggetto");
								templateId = email.getString("id");
								break;
							}
						}

						try {
							token = cacheTokenService.getToken();
							mailService.sendEmail(oggettoEmail, content,
									new HashSet<>(Arrays.asList(chiamante.getEmail())), null,
									new HashSet<>(Arrays.asList(emailCoduzione)), templateId, token);
						} catch (Exception e2) {
							log.error("IMPOSSIBILE INVIARE EMAIL: " + e2.getMessage());
							log.error(LogUtility.exceptionToLog(e2));
						}
						throw e;
					}

				}

				if (emailRedattori != null && !emailRedattori.isEmpty()) {
					String oggettoEmail = null;
					String templateId = null;
					for (int i = 0; i < configurazioneEmail.length(); i++) {
						JSONObject email = configurazioneEmail.getJSONObject(i);
						if (email.getString("id").equals(emailRedattori)) {
							oggettoEmail = email.getString("oggetto");
							templateId = email.getString("id");
							break;
						}
					}
					try {
						token = cacheTokenService.getToken();

						Set<String> mailTo = dmsClientStub.getEmailRedattori(token, tipologieRedazionali);
						log.info("Mail redattori:" + mailTo);

						mailService.sendEmail(oggettoEmail, content, mailTo, null, null, templateId, token);
					} catch (Exception e) {
						log.error("IMPOSSIBILE INVIARE EMAIL: " + e.getMessage());
						log.error(LogUtility.exceptionToLog(e));
					}
				}
			}

		}

	}
	
	
	@Async
	public void invioMailConseguenteJMSSpubblicazioneTotaleUseModel(Set<PubblicazioneModel> spubblicazioniJMS, String content,
			String emailOwner, String emailRedattori, DettaglioUtente chiamante, List<String> emailAddressOwner,
			Set<String> tipologieRedazionali, boolean flagSpubblicazione) throws Exception {

		log.info("Entrato cambio stato evento [flagSpubblicazione:" + flagSpubblicazione + ",emailOwner:" + emailOwner
				+ ",emailRedattori:" + emailRedattori + ",chiamante.mail:" + chiamante.getEmail()
				+ ",emailAddressOwner:" + emailAddressOwner + "]");

		String token = null;

		if (flagSpubblicazione) {
			// eventuale spubblicazione
			if (!spubblicazioniJMS.isEmpty()) {
				log.info("Entrato spubblicazione jms");
				for (PubblicazioneModel pubblicazione : spubblicazioniJMS) {
					log.info("Entrato !flagSpubblicazione || spubblicazioniJMS.isEmpty(): emailRedattori= "
							+ emailRedattori + ", emailOwner= " + emailOwner + ", spubblicazioniJms="
							+ pubblicazione.getRedazioneId());
					log.info("SPUBBLICAZIONE SU JMS NECESSARIA A CAUSA DI SPUBBLICAZIONE GENERALE EVENTO");
					try {
						jmsService.sendMessageToJms(pubblicazione);
					} catch (Exception e) {
						String oggettoEmail = null;
						String templateId = null;
						for (int i = 0; i < configurazioneEmail.length(); i++) {
							JSONObject email = configurazioneEmail.getJSONObject(i);
							if (email.getString("id").equals("ErroreSpubblicazione")) {
								oggettoEmail = email.getString("oggetto");
								templateId = email.getString("id");
								break;
							}
						}

						try {
							token = cacheTokenService.getToken();
							mailService.sendEmail(oggettoEmail, content,
									new HashSet<>(Arrays.asList(chiamante.getEmail())), null,
									new HashSet<>(Arrays.asList(emailCoduzione)), templateId, token);
						} catch (Exception e2) {
							log.error("IMPOSSIBILE INVIARE EMAIL: " + e2.getMessage());
						}
						throw e;
					}

					// gestisco invio mail
					if (emailOwner != null && !emailOwner.isEmpty()) {
						String oggettoEmail = null;
						String templateId = null;
						for (int i = 0; i < configurazioneEmail.length(); i++) {
							JSONObject email = configurazioneEmail.getJSONObject(i);
							if (email.getString("id").equals(emailOwner)) {
								oggettoEmail = email.getString("oggetto");
								templateId = email.getString("id");
								break;
							}
						}
						try {
							token = cacheTokenService.getToken();
							mailService.sendEmail(oggettoEmail, content, new HashSet<>(emailAddressOwner), null,
									new HashSet<>(Arrays.asList(chiamante.getEmail())), templateId, token);
						} catch (Exception e) {
							log.error("IMPOSSIBILE INVIARE EMAIL: " + e.getMessage());
							log.error(LogUtility.exceptionToLog(e));
						}
					}
					if (emailRedattori != null && !emailRedattori.isEmpty()) {
						String oggettoEmail = null;
						String templateId = null;
						for (int i = 0; i < configurazioneEmail.length(); i++) {
							JSONObject email = configurazioneEmail.getJSONObject(i);
							if (email.getString("id").equals(emailRedattori)) {
								oggettoEmail = email.getString("oggetto");
								templateId = email.getString("id");
								break;
							}
						}
						try {
							token = cacheTokenService.getToken();

							Set<String> mailTo = dmsClientStub.getEmailRedattori(token, tipologieRedazionali);
							log.info("Mail redattori:" + mailTo);

							mailService.sendEmail(oggettoEmail, content, mailTo, null, null, templateId, token);
						} catch (Exception e) {
							log.error("IMPOSSIBILE INVIARE EMAIL: " + e.getMessage());
							log.error(LogUtility.exceptionToLog(e));
						}
					}
				}
			}
		}

		// IN CASO DI CAMBIO STATO SENZA SPUBBLICAZIONE DA VIP MANDO COMUNQUE L'EMAIL
		if ((!flagSpubblicazione || spubblicazioniJMS.isEmpty())) {
			log.info("Entrato !flagSpubblicazione || spubblicazioniJMS.isEmpty(): emailRedattori= " + emailRedattori
					+ ", emailOwner= " + emailOwner);
			// gestisco invio mail
			if (emailOwner != null && !emailOwner.isEmpty()) {
				String oggettoEmail = null;
				String templateId = null;
				for (int i = 0; i < configurazioneEmail.length(); i++) {
					JSONObject email = configurazioneEmail.getJSONObject(i);
					if (email.getString("id").equals(emailOwner)) {
						oggettoEmail = email.getString("oggetto");
						templateId = email.getString("id");
						break;
					}
				}
				try {
					token = cacheTokenService.getToken();
					mailService.sendEmail(oggettoEmail, content, new HashSet<>(emailAddressOwner), null,
							new HashSet<>(Arrays.asList(chiamante.getEmail())), templateId, token);
				} catch (Exception e) {
					log.error("IMPOSSIBILE INVIARE EMAIL: " + e.getMessage());
					log.error(LogUtility.exceptionToLog(e));
				}
			}
			if (emailRedattori != null && !emailRedattori.isEmpty() && !emailRedattori.equals("Revocato")) {
				String oggettoEmail = null;
				String templateId = null;
				for (int i = 0; i < configurazioneEmail.length(); i++) {
					JSONObject email = configurazioneEmail.getJSONObject(i);
					if (email.getString("id").equals(emailRedattori)) {
						oggettoEmail = email.getString("oggetto");
						templateId = email.getString("id");
						break;
					}
				}
				try {
					token = cacheTokenService.getToken();
					Set<String> mailTo = dmsClientStub.getEmailRedattori(token, tipologieRedazionali);
					log.info("Mail redattori:" + mailTo);

					mailService.sendEmail(oggettoEmail, content, mailTo, null, null, templateId, token);
				} catch (Exception e) {
					log.error("IMPOSSIBILE INVIARE EMAIL: " + e.getMessage());
					log.error(LogUtility.exceptionToLog(e));
				}
			}

		}

	}


}
