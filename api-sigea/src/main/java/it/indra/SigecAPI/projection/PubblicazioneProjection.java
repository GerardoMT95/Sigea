package it.indra.SigecAPI.projection;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.databind.JsonNode;

import it.indra.SigeaCommons.enumeration.StatoPubblicazione;
import it.indra.SigeaCommons.model.EventoModel;
import it.indra.SigecAPI.entity.DettaglioUtente;
import it.indra.SigecAPI.entity.Redazione;

public interface PubblicazioneProjection {

	@Value("#{target.pubblicazioneId}")
	Long getPubblicazioneId();
	
	@Value("#{target.dataPubblicazione}")
	Timestamp getDataPubblicazione();
	
	@Value("#{target.redattore}")
	DettaglioUtente getRedattore();	
	
	@Value("#{target.noteAggiuntive}")
	String getNoteAggiuntive();
	
	@Value("#{target.statoPubblicazione}")
	StatoPubblicazione getStatoPubblicazione();
	
	@Value("#{target.eventoModel}")
	EventoModel getEventoModel();
	
	@Value("#{target.genericMetadataWip}")
	JsonNode getGenericMetadataWip();
	
	@Value("#{target.genericMetadata}")
	JsonNode getGenericMetadata();
	
	@Value("#{target.evento}")
	EventoTitoloProjection getEvento();
	
	@Value("#{target.redazione}")
	Redazione getRedazione();
}
