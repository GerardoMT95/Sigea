package it.indra.SigecAPI;

/*import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;


import it.indra.SigecAPI.model.EventoModel;
import it.indra.SigecAPI.model.RelazioneModel;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)*/
public class SIGECapiServiceApplicationTests {
	//I test contenuti in questa classe sono obsoleti
/*	public static WSO2Token token;
	
	public static HttpHeaders headers;
	
	public static String segnalazioneId;
	
	public static String eventoId;
	
	public static String rassegnaId;
	
	public static EventoModel evento;
	
	public static EventoModel rassegna;
	
	public MockMvc mockMvc;
	
    @Autowired
    private WebApplicationContext webApplicationContext;

   @BeforeEach
    public void setUp() {
    	this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    	token = new WSO2Token("eyJ4NXQiOiJPR1JpTnpWaU9EbG1ZelV4TW1KbU1XWXpOakV4WVRSalptTTRaamszTUdZek9XRTROemc1TUEiLCJraWQiOiJPR1JpTnpWaU9EbG1ZelV4TW1KbU1XWXpOakV4WVRSalptTTRaamszTUdZek9XRTROemc1TUEiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJwdWdsaWF1c2VyYWRtaW4yQHB1Z2xpYS5pdCIsImF1ZCI6Ind5QkR5NWFKTWFreGh2NDU0UDRWWnV5b0Yza2EiLCJuYmYiOjE1NTc3NjIxNzMsImF6cCI6Ind5QkR5NWFKTWFreGh2NDU0UDRWWnV5b0Yza2EiLCJzY29wZSI6ImRlZmF1bHQiLCJpc3MiOiJodHRwczpcL1wvaWFtLnRlc3QuZG1zLnB1Z2xpYS5pdDo0NDNcL29hdXRoMlwvdG9rZW4iLCJleHAiOjE1NTc3NjU3NzMsImlhdCI6MTU1Nzc2MjE3MywianRpIjoiYmYxNDljMzUtMTM1Zi00YTgzLThiYjctOGM4ZWIwNzU5M2NlIn0.MVVQcRqBvtv9YJ8P6UwsUtFHD_83ddrFgmnrjTBPzF7nJdZOfOSLSTjMyfj-AiE1WKgYDBXTodY9VUQr-OctRBNUwiQWbgzvODYKbtL-AjVWHiPjRuMkJXUmqv53NtrlUdqweOJrtzWkQppdA00pDRt6RTZ-wPbjbU1JpTv8fsqV66MCksISy2g226xiebU1LqhnSRiRUnO_-SpCVO58mvRvoeq9PWC6P8I5qJ9dIiqXY6y4F19J7d0Xvlf1vb_T9HYfmSC_yssT9ChRaBP1KhbE7KlLnAFv0Z66Iea1wyks38JBEG0mHsrLOQVJhrz7Yqty2ts5d-SHj0m3EC8ljA",3600,"c66bb10f-aa82-37e8-b142-f99f39e56288","", "Bearer", System.currentTimeMillis());
    	headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON); 
		headers.set("Authorization", "Bearer " + token.getAccessToken());
   }

	@Test
	@Order(1)
	public void test1() throws Exception {
		segnalazioneId = this.mockMvc.perform(post("/segnalazioni")
		.headers(headers)
        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).characterEncoding("utf-8")
        .content("{\n" + 
        		"  \"segnalazioneId\": null,\n" + 
        		"  \"nome\": \"Segnalazione Prova\",\n" + 
        		"  \"descrizione\": \"Descrizione Prova\",\n" + 
        		"  \"dataDa\": \"2019-11-09\",\n" + 
        		"  \"dataA\": \"2019-11-09\",\n" + 
        		"  \"location\": \"Location Prova\",\n" + 
        		"  \"indirizzo\": \"Indirizzo Prova\",\n" + 
        		"  \"riferimento\": \"Riferimento Prova\",\n" + 
        		"  \"utente\": {\n" + 
        		"    \"utenteId\": 1,\n" + 
        		"    \"username\": \"Pippo\",\n" + 
        		"    \"nome\": \"Mario\",\n" + 
        		"    \"cognome\": \"Rossi\",\n" + 
        		"    \"codFiscale\": \"MROEIRPE56556\",\n" + 
		        "    \"email\": \"prova@prova.it\",\n" + 
        		"    \"tipologia\": {\n" + 
        		"      \"tipologiaId\": \"C\",\n" + 
        		"      \"descrizione\": \"Cittadino\",\n" + 
        		"      \"isRedattore\": false\n" + 
        		"    }\n" + 
        		"  },\n" + 
        		"  \"codIstat\": \"072006\",\n" + 
        		"  \"comune\": \"BARI\"\n" + 
        		"}")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
	}
	
	@Test
	@Order(2)
	public void test2() throws Exception {
		this.mockMvc.perform(get("/segnalazioni/{id}",segnalazioneId)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString().equals("{\n" + 
        		"  \"segnalazioneId\": "+segnalazioneId+",\n" + 
        		"  \"nome\": \"Segnalazione Prova\",\n" + 
        		"  \"descrizione\": \"Descrizione Prova\",\n" + 
        		"  \"dataDa\": \"2019-11-09\",\n" + 
        		"  \"dataA\": \"2019-11-09\",\n" + 
        		"  \"location\": \"Location Prova\",\n" + 
        		"  \"indirizzo\": \"Indirizzo Prova\",\n" + 
        		"  \"riferimento\": \"Riferimento Prova\",\n" + 
        		"  \"utente\": {\n" + 
        		"    \"utenteId\": 1,\n" + 
        		"    \"username\": \"Pippo\",\n" + 
        		"    \"nome\": \"Mario\",\n" + 
        		"    \"cognome\": \"Rossi\",\n" + 
        		"    \"codFiscale\": \"MROEIRPE56556\",\n" + 
		        "    \"email\": \"prova@prova.it\",\n" +  
        		"    \"tipologia\": {\n" + 
        		"      \"tipologiaId\": \"C\",\n" + 
        		"      \"descrizione\": \"Cittadino\",\n" + 
        		"      \"isRedattore\": false\n" + 
        		"    }\n" + 
        		"  },\n" + 
        		"  \"codIstat\": \"072006\",\n" + 
        		"  \"comune\": \"BARI\"\n" + 
        		"}");
	}
	
	@Test
	@Order(3)
	public void test3() throws Exception {
		eventoId = this.mockMvc.perform(put("/eventi")
				.headers(headers)
		        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
		        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).characterEncoding("utf-8")
		        .content("{\n" + 
		        		"  \"eventoId\": null,\n" + 
		        		"  \"tipo\": \"EVENTO\",\n" + 
		        		"  \"statoId\": \"BOZZA\",\n" + 
		        		"  \"stato\": \"Bozza\",\n" + 
		        		"  \"ticket\": {\n" + 
		        		"    \"eventoId\": null,\n" + 
		        		"    \"tipoTicket\": \"PAGAMENTO\",\n" + 
		        		"    \"ticketIntero\": 1.00,\n" + 
		        		"    \"ticketRidotto\": 1.00,\n" + 
		        		"    \"ticketGruppiRidotto\": 1.00,\n" + 
		        		"    \"ticketGruppiNumero\": 3,\n" + 
		        		"    \"flagGratisAnziani\": true,\n" + 
		        		"    \"flagGratisBambini\": true,\n" + 
		        		"    \"flagGratisConvenzioni\": true,\n" + 
		        		"    \"flagGratisDisabili\": true,\n" + 
		        		"    \"flagGratisAccompagnatori\": true,\n" + 
		        		"    \"flagRidottoAnziani\": true,\n" + 
		        		"    \"flagRidottoBambini\": true,\n" + 
		        		"    \"flagRidottoConvenzioni\": true,\n" + 
		        		"    \"flagRidottoDisabili\": true,\n" + 
		        		"    \"flagRidottoAccompagnatori\": true,\n" + 
		        		"    \"convenzioniAttive\": \"Università\",\n" + 
		        		"    \"linkPrenotazioni\": \"www.prova.it\",\n" + 
		        		"    \"notaMulti\": {\n" + 
		        		"      \"IT\": \"Prova note\",\n" + 
		        		"      \"EN\": \"Prove notes\",\n" + 
		        		"      \"RU\": \"попробуйте заметки\"\n" + 
		        		"    }\n" + 
		        		"  },\n" +  
		        		"  \"accessibilita\": {\n" + 
		        		"    \"eventoId\": null,\n" +
		        		"    \"flagFamiglieBambini\": true,\n" +
		        		"    \"flagPersoneAnimali\": false,\n" +
		        		"    \"flagDisabilitaFisica\": true,\n" +
		        		"    \"flagDisabilitaVisiva\": false,\n" +
		        		"    \"flagDisabilitaUditiva\": true,\n" +
		        		"    \"flagGravidanza\": true,\n" +
		        		"    \"flagAnziani\": true,\n" +
		        		"    \"flagCaniMedi\": false,\n" +
		        		"    \"flagCaniPiccoli\": true,\n" +
		        		"    \"flagCani\": true,\n" +
		        		"    \"flagBiblioteca\": false,\n" +
		        		"    \"flagGiardini\": false,\n" +
		        		"    \"flagLudoteca\": false,\n" +
		        		"    \"flagNursey\": true,\n" +
		        		"    \"flagParcogiochi\": false,\n" +
		        		"    \"flagPercorsi\": false,\n" +
		        		"    \"flagSito\": false,\n" +
		        		"    \"flagInfopoint\": false,\n" +
		        		"    \"flagServiziIgienici\": true,\n" +
		        		"    \"flagParcheggioDisabili\": false,\n" +
		        		"    \"flagIngressi\": false,\n" +
		        		"    \"flagPercorsiTattili\": true,\n" +
		        		"    \"flagGuideBraille\": true,\n" +
		        		"    \"flagSegnaleticaBraille\": false,\n" +
		        		"    \"flagSegnaleticaLeggibile\": false,\n" +
		        		"    \"flagMaterialeLeggibile\": false,\n" +
		        		"    \"flagPostazioniAudio\": false,\n" +
		        		"    \"flagAudioguide\": false,\n" +
		        		"    \"flagAudioguidePercorsi\": false,\n" +
		        		"    \"flagMaterialeSottotitolato\": false,\n" +
		        		"    \"flagRiproduzioneTattili\": false,\n" +
		        		"    \"flagComputer\": false,\n" +
		        		"    \"flagLis\": true\n" +
		        		"  },\n" + 
		        		"  \"owner\": {\n" + 
		        		"    \"utenteId\": 1,\n" + 
		        		"    \"username\": \"Pippo\",\n" + 
		        		"    \"nome\": \"Mario\",\n" + 
		        		"    \"cognome\": \"Rossi\",\n" + 
		        		"    \"codFiscale\": \"MROEIRPE56556\",\n" + 
		        		"    \"email\": \"prova@prova.it\",\n" +  
		        		"    \"tipologia\": {\n" + 
		        		"      \"tipologiaId\": \"PR\",\n" + 
		        		"      \"descrizione\": \"Promotore\",\n" + 
		        		"      \"isRedattore\": false\n" + 
		        		"    }\n" + 
		        		"  },\n" + 
		        		"  \"attivita\": {\n" + 
		        		"    \"attivitaId\": 1,\n" + 
		        		"    \"denominazione\": \"Prova denominazione\"\n" + 
		        		"  },\n" + 
		        		"  \"richiestaAttivita\": null,\n" + 
		        		"  \"datiGenerali\": {\n" + 
		        		"    \"eventoId\": null,\n" + 
		        		"    \"titoloMulti\": {\n" + 
		        		"      \"IT\": \"Prova note\",\n" + 
		        		"      \"EN\": \"Prove notes\",\n" + 
		        		"      \"RU\": \"попробуйте заметки\"\n" + 
		        		"    },\n" + 
		        		"    \"abstractDescrMulti\": {\n" + 
		        		"      \"IT\": \"Prova note\",\n" + 
		        		"      \"EN\": \"Prove notes\",\n" + 
		        		"      \"RU\": \"попробуйте заметки\"\n" + 
		        		"    },\n" + 
		        		"    \"snippetMulti\": {\n" + 
		        		"      \"IT\": \"Prova note\",\n" + 
		        		"      \"EN\": \"Prove notes\",\n" + 
		        		"      \"RU\": \"попробуйте заметки\"\n" + 
		        		"    },\n" + 
		        		"    \"descrizioneMulti\": {\n" + 
		        		"      \"IT\": \"Prova note\",\n" + 
		        		"      \"EN\": \"Prove notes\",\n" + 
		        		"      \"RU\": \"попробуйте заметки\"\n" + 
		        		"    }\n" + 
		        		"  },\n" + 
		        		"  \"locationSet\": [\n" + 
		        		"    {\n" + 
		        		"      \"locationId\": null,\n" + 
		        		"      \"codNazione\": \"N123\",\n" +
		        		"      \"codRegione\": \"R123\",\n" +
		        		"      \"codProvincia\": \"P123\",\n" +
		        		"      \"codComune\": \"C123\",\n" +
		        		"      \"codArea\": \"A123\",\n" +
		        		"      \"indirizzo\": \"Indirizzo Prova\",\n" + 
		        		"      \"cap\": \"70042\",\n" + 
		        		"      \"nomeLocation\": \"Casa Mia\",\n" + 
		        		"      \"fgPuglia\": true,\n" + 
		        		"      \"nazione\": \"ITALIA\",\n" + 
		        		"      \"regione\": \"PUGLIA\",\n" + 
		        		"      \"provincia\": \"BA\",\n" + 
		        		"      \"comune\": \"MOLA DI BARI\",\n" + 
		        		"      \"comuneEstero\": null,\n" + 
		        		"      \"latitudine\": 0.00,\n" + 
		        		"      \"longitudine\": 0.00,\n" + 
		        		"      \"attrattoriSet\": [\n" + 
		        		"        {\n" + 
		        		"          \"attrattoreId\": 1,\n" + 
		        		"          \"etichetta\": \"N'derr alla lanz\"\n" + 
		        		"        }\n" + 
		        		"      ]\n" + 
		        		"    }\n" + 
		        		"  ],\n" + 
		        		"  \"telefonoSet\": [\n" + 
		        		"    {\n" + 
		        		"      \"contattoId\": null,\n" + 
		        		"      \"contatto\": \"08080808\"\n" + 
		        		"    }\n" + 
		        		"  ],\n" +  
		        		"  \"emailSet\": [\n" + 
		        		"    {\n" + 
		        		"      \"contattoId\": null,\n" + 
		        		"      \"contatto\": \"prova@prova.it\"\n" + 
		        		"    }\n" + 
		        		"  ],\n" +  
		        		"  \"sitoSet\": [],\n" + 
		        		"  \"relazioneSet\": [],\n" + 
		        		"  \"logSet\": [],\n" + 
		        		"  \"periodoSet\": [\n" + 
		        		"    {\n" + 
		        		"      \"periodoId\": null,\n" + 
		        		"      \"dataDa\": \"2019-12-11\",\n" + 
		        		"      \"dataA\": \"2019-12-22\",\n" + 
		        		"      \"fgContinuato\": true,\n" + 
		        		"      \"orarioApertura\": \"10.00\",\n" + 
		        		"      \"orarioChiusura\": \"15.00\",\n" + 
		        		"      \"orarioAperturaMattina\": null,\n" + 
		        		"      \"orarioChiusuraMattina\": null,\n" + 
		        		"      \"orarioAperturaPomeriggio\": null,\n" + 
		        		"      \"orarioChiusuraPomeriggio\": null,\n" + 
		        		"      \"chiusuraDom\": true,\n" + 
		        		"      \"chiusuraLun\": true,\n" + 
		        		"      \"chiusuraMar\": true,\n" + 
		        		"      \"chiusuraMer\": true,\n" + 
		        		"      \"chiusuraGio\": true,\n" + 
		        		"      \"chiusuraVen\": true,\n" + 
		        		"      \"chiusuraSab\": true,\n" + 
		        		"      \"cadenza\": \"Settimanale\",\n" + 
		        		"      \"cadenzaDom\": true,\n" + 
		        		"      \"cadenzaLun\": true,\n" + 
		        		"      \"cadenzaMar\": true,\n" + 
		        		"      \"cadenzaMer\": true,\n" + 
		        		"      \"cadenzaGio\": true,\n" + 
		        		"      \"cadenzaVen\": true,\n" + 
		        		"      \"cadenzaSab\": true,\n" + 
		        		"      \"cadenzaMensile\": []\n" + 
		        		"    }\n" + 
		        		"  ]\n" + 
		        		"}")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
	}
	
	@Test
	@Order(4)
	public void test4() throws Exception {
		String eventoJsonString = this.mockMvc.perform(get("/eventi/{id}",eventoId)
				.headers(headers)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		ObjectMapper objectMapper = new ObjectMapper();
		evento = objectMapper.readValue(eventoJsonString, EventoModel.class);
	}
	
	@Test
	@Order(5)
	public void test5() throws Exception {
		rassegnaId = this.mockMvc.perform(put("/eventi")
				.headers(headers)
		        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
		        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).characterEncoding("utf-8")
		        .content("{\n" + 
		        		"  \"eventoId\": null,\n" + 
		        		"  \"tipo\": \"RASSEGNA\",\n" + 
		        		"  \"statoId\": \"BOZZA\",\n" + 
		        		"  \"stato\": \"Bozza\",\n" + 
		        		"  \"ticket\": {\n" + 
		        		"    \"eventoId\": null,\n" + 
		        		"    \"tipoTicket\": \"PAGAMENTO\",\n" + 
		        		"    \"ticketIntero\": 1.00,\n" + 
		        		"    \"ticketRidotto\": 1.00,\n" + 
		        		"    \"ticketGruppiRidotto\": 1.00,\n" + 
		        		"    \"ticketGruppiNumero\": 3,\n" + 
		        		"    \"flagGratisAnziani\": true,\n" + 
		        		"    \"flagGratisBambini\": true,\n" + 
		        		"    \"flagGratisConvenzioni\": true,\n" + 
		        		"    \"flagGratisDisabili\": true,\n" + 
		        		"    \"flagGratisAccompagnatori\": true,\n" + 
		        		"    \"flagRidottoAnziani\": true,\n" + 
		        		"    \"flagRidottoBambini\": true,\n" + 
		        		"    \"flagRidottoConvenzioni\": true,\n" + 
		        		"    \"flagRidottoDisabili\": true,\n" + 
		        		"    \"flagRidottoAccompagnatori\": true,\n" + 
		        		"    \"convenzioniAttive\": \"Università\",\n" + 
		        		"    \"linkPrenotazioni\": \"www.prova.it\",\n" + 
		        		"    \"notaMulti\": {\n" + 
		        		"      \"IT\": \"Prova note\",\n" + 
		        		"      \"EN\": \"Prove notes\",\n" + 
		        		"      \"RU\": \"попробуйте заметки\"\n" + 
		        		"    }\n" + 
		        		"  },\n" +  
		        		"  \"accessibilita\": {\n" + 
		        		"    \"eventoId\": null,\n" +
		        		"    \"flagFamiglieBambini\": true,\n" +
		        		"    \"flagPersoneAnimali\": false,\n" +
		        		"    \"flagDisabilitaFisica\": true,\n" +
		        		"    \"flagDisabilitaVisiva\": false,\n" +
		        		"    \"flagDisabilitaUditiva\": true,\n" +
		        		"    \"flagGravidanza\": true,\n" +
		        		"    \"flagAnziani\": true,\n" +
		        		"    \"flagCaniMedi\": false,\n" +
		        		"    \"flagCaniPiccoli\": true,\n" +
		        		"    \"flagCani\": true,\n" +
		        		"    \"flagBiblioteca\": false,\n" +
		        		"    \"flagGiardini\": false,\n" +
		        		"    \"flagLudoteca\": false,\n" +
		        		"    \"flagNursey\": true,\n" +
		        		"    \"flagParcogiochi\": false,\n" +
		        		"    \"flagPercorsi\": false,\n" +
		        		"    \"flagSito\": false,\n" +
		        		"    \"flagInfopoint\": false,\n" +
		        		"    \"flagServiziIgienici\": true,\n" +
		        		"    \"flagParcheggioDisabili\": false,\n" +
		        		"    \"flagIngressi\": false,\n" +
		        		"    \"flagPercorsiTattili\": true,\n" +
		        		"    \"flagGuideBraille\": true,\n" +
		        		"    \"flagSegnaleticaBraille\": false,\n" +
		        		"    \"flagSegnaleticaLeggibile\": false,\n" +
		        		"    \"flagMaterialeLeggibile\": false,\n" +
		        		"    \"flagPostazioniAudio\": false,\n" +
		        		"    \"flagAudioguide\": false,\n" +
		        		"    \"flagAudioguidePercorsi\": false,\n" +
		        		"    \"flagMaterialeSottotitolato\": false,\n" +
		        		"    \"flagRiproduzioneTattili\": false,\n" +
		        		"    \"flagComputer\": false,\n" +
		        		"    \"flagLis\": true\n" +
		        		"  },\n" + 
		        		"  \"owner\": {\n" + 
		        		"    \"utenteId\": 1,\n" + 
		        		"    \"username\": \"Pippo\",\n" + 
		        		"    \"nome\": \"Mario\",\n" + 
		        		"    \"cognome\": \"Rossi\",\n" + 
		        		"    \"codFiscale\": \"MROEIRPE56556\",\n" + 
		        		"    \"email\": \"prova@prova.it\",\n" +  
		        		"    \"tipologia\": {\n" + 
		        		"      \"tipologiaId\": \"RED-PP\",\n" + 
		        		"      \"descrizione\": \"Redattore di Puglia Promozione\",\n" + 
		        		"      \"isRedattore\": true\n" + 
		        		"    }\n" + 
		        		"  },\n" + 
		        		"  \"attivita\": {\n" + 
		        		"    \"attivitaId\": 1,\n" + 
		        		"    \"denominazione\": \"Prova denominazione\"\n" + 
		        		"  },\n" + 
		        		"  \"richiestaAttivita\": null,\n" + 
		        		"  \"datiGenerali\": {\n" + 
		        		"    \"eventoId\": null,\n" + 
		        		"    \"titoloMulti\": {\n" + 
		        		"      \"IT\": \"Prova note\",\n" + 
		        		"      \"EN\": \"Prove notes\",\n" + 
		        		"      \"RU\": \"попробуйте заметки\"\n" + 
		        		"    },\n" + 
		        		"    \"abstractDescrMulti\": {\n" + 
		        		"      \"IT\": \"Prova note\",\n" + 
		        		"      \"EN\": \"Prove notes\",\n" + 
		        		"      \"RU\": \"попробуйте заметки\"\n" + 
		        		"    },\n" + 
		        		"    \"snippetMulti\": {\n" + 
		        		"      \"IT\": \"Prova note\",\n" + 
		        		"      \"EN\": \"Prove notes\",\n" + 
		        		"      \"RU\": \"попробуйте заметки\"\n" + 
		        		"    },\n" + 
		        		"    \"descrizioneMulti\": {\n" + 
		        		"      \"IT\": \"Prova note\",\n" + 
		        		"      \"EN\": \"Prove notes\",\n" + 
		        		"      \"RU\": \"попробуйте заметки\"\n" + 
		        		"    }\n" + 
		        		"  },\n" + 
		        		"  \"locationSet\": [\n" + 
		        		"    {\n" + 
		        		"      \"locationId\": null,\n" + 
		        		"      \"codNazione\": \"N123\",\n" +
		        		"      \"codRegione\": \"R123\",\n" +
		        		"      \"codProvincia\": \"P123\",\n" +
		        		"      \"codComune\": \"C123\",\n" +
		        		"      \"codArea\": \"A123\",\n" +
		        		"      \"indirizzo\": \"Indirizzo Prova\",\n" + 
		        		"      \"cap\": \"70042\",\n" + 
		        		"      \"nomeLocation\": \"Casa Mia\",\n" + 
		        		"      \"fgPuglia\": true,\n" + 
		        		"      \"nazione\": \"ITALIA\",\n" + 
		        		"      \"regione\": \"PUGLIA\",\n" + 
		        		"      \"provincia\": \"BA\",\n" + 
		        		"      \"comune\": \"MOLA DI BARI\",\n" + 
		        		"      \"comuneEstero\": null,\n" + 
		        		"      \"latitudine\": 0.00,\n" + 
		        		"      \"longitudine\": 0.00,\n" + 
		        		"      \"attrattoriSet\": [\n" + 
		        		"        {\n" + 
		        		"          \"attrattoreId\": 1,\n" + 
		        		"          \"etichetta\": \"N'derr alla lanz\"\n" + 
		        		"        }\n" + 
		        		"      ]\n" + 
		        		"    }\n" + 
		        		"  ],\n" + 
		        		"  \"telefonoSet\": [\n" + 
		        		"    {\n" + 
		        		"      \"contattoId\": null,\n" + 
		        		"      \"contatto\": \"08080808\"\n" + 
		        		"    }\n" + 
		        		"  ],\n" +  
		        		"  \"emailSet\": [\n" + 
		        		"    {\n" + 
		        		"      \"contattoId\": null,\n" + 
		        		"      \"contatto\": \"prova@prova.it\"\n" + 
		        		"    }\n" + 
		        		"  ],\n" +  
		        		"  \"sitoSet\": [],\n" + 
		        		"  \"relazioneSet\": [],\n" + 
		        		"  \"logSet\": [],\n" + 
		        		"  \"periodoSet\": [\n" + 
		        		"    {\n" + 
		        		"      \"periodoId\": null,\n" + 
		        		"      \"dataDa\": \"2019-12-11\",\n" + 
		        		"      \"dataA\": \"2019-12-22\",\n" + 
		        		"      \"fgContinuato\": true,\n" + 
		        		"      \"orarioApertura\": \"10.00\",\n" + 
		        		"      \"orarioChiusura\": \"15.00\",\n" + 
		        		"      \"orarioAperturaMattina\": null,\n" + 
		        		"      \"orarioChiusuraMattina\": null,\n" + 
		        		"      \"orarioAperturaPomeriggio\": null,\n" + 
		        		"      \"orarioChiusuraPomeriggio\": null,\n" + 
		        		"      \"chiusuraDom\": true,\n" + 
		        		"      \"chiusuraLun\": true,\n" + 
		        		"      \"chiusuraMar\": true,\n" + 
		        		"      \"chiusuraMer\": true,\n" + 
		        		"      \"chiusuraGio\": true,\n" + 
		        		"      \"chiusuraVen\": true,\n" + 
		        		"      \"chiusuraSab\": true,\n" + 
		        		"      \"cadenza\": \"Settimanale\",\n" + 
		        		"      \"cadenzaDom\": true,\n" + 
		        		"      \"cadenzaLun\": true,\n" + 
		        		"      \"cadenzaMar\": true,\n" + 
		        		"      \"cadenzaMer\": true,\n" + 
		        		"      \"cadenzaGio\": true,\n" + 
		        		"      \"cadenzaVen\": true,\n" + 
		        		"      \"cadenzaSab\": true,\n" + 
		        		"      \"cadenzaMensile\": []\n" + 
		        		"    }\n" + 
		        		"  ]\n" + 
		        		"}")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
	}
	
	@Test
	@Order(6)
	public void test6() throws Exception {
		String rassegnaJsonString = this.mockMvc.perform(get("/eventi/{id}",rassegnaId)
				.headers(headers)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		ObjectMapper objectMapper = new ObjectMapper();
		rassegna = objectMapper.readValue(rassegnaJsonString, EventoModel.class);
	}
	
    @Test
	@Order(7)
	public void test7() throws Exception {
    	Set<RelazioneModel> relazioneSet = new HashSet<RelazioneModel>();
    	RelazioneModel relazione = new RelazioneModel();
    	relazione.setTipoRelazione("CONTIENE");
    	relazione.setTitolo(evento.getDatiGenerali().getTitoloMulti().get("IT"));
    	relazione.setEventoRelazionatoId(evento.getEventoId());
    	relazioneSet.add(relazione);
    	rassegna.setRelazioneSet(relazioneSet);
    	ObjectMapper mapper = new ObjectMapper();
    	String rassegnaJson = mapper.writeValueAsString(rassegna);
    	
    	System.out.println(rassegnaJson);
    	
		rassegnaId = this.mockMvc.perform(put("/eventi")
				.headers(headers)
		        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
		        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).characterEncoding("utf-8")
		        .content(rassegnaJson)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
    }
*/
}
