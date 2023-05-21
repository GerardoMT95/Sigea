package it.indra.SigeaCommons.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.HashSet;
import java.util.Set;

import it.indra.SigeaCommons.enumeration.StatoPubblicazione;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PubblicazioneModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long pubblicazioneId;	
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp dataPubblicazione;
	
	private Long ownerId;
	
	private String nomeOwner;
	
	private String cognomeOwner;
	
	private String redazioneId;
	
	private String nomeRedazione;
	
	private String noteAggiuntive;

	private StatoPubblicazione statoPubblicazione;
	
	private EventoModel eventoModel;
	
	private JsonNode genericMetadata;
	
	private Boolean fgPubblicato;
	
	private String tipologiaScheda;
	
	private Long eventoId;

	private String statsUUID;
	
	private Set<LogPubblicazioniModel> logPubblicazioniSet = new HashSet<>();
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp dataPrimaPubblicazione;
	

}
