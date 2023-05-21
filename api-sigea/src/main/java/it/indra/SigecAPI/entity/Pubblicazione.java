package it.indra.SigecAPI.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.databind.JsonNode;

import it.indra.SigeaCommons.enumeration.StatoPubblicazione;
import it.indra.SigeaCommons.model.EventoModel;
import it.indra.SigecAPI.extjpa.JsonDataUserType;
import it.indra.SigecAPI.extjpa.JsonNodeUserType;
import lombok.Data;

@Entity
@Table(name="t_SIGEA_PUBBLICAZIONI",schema="sigec")
@TypeDef(name = "JsonDataUserType", typeClass = JsonDataUserType.class)
@TypeDef(name = "JsonNodeUserType", typeClass = JsonNodeUserType.class)
@Data
public class Pubblicazione implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sigec.SIGEA_PUBBLICAZIONI_SEQ")
	@SequenceGenerator(name = "sigec.SIGEA_PUBBLICAZIONI_SEQ", sequenceName = "sigec.SIGEA_PUBBLICAZIONI_SEQ", allocationSize = 1, initialValue = 1)
	@Column(name = "PUBBLICAZIONE_ID")
	private Long pubblicazioneId;
	
	@Column(name="DATA_PUBBLICAZIONE")
	//@UpdateTimestamp
	@CreationTimestamp
	private Timestamp dataPubblicazione;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="REDATTORE_ID", referencedColumnName="UTENTE_ID")
	private DettaglioUtente redattore;	
	
	@Column(name = "NOTE_AGGIUNTIVE")
	private String noteAggiuntive;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "STATO_PUBBLICAZIONE")
	private StatoPubblicazione statoPubblicazione;
	
	@Column(name = "EVENTO_PUBBLICATO")
	@Type(type = "JsonDataUserType", parameters = { @Parameter(name = "class", value = "it.indra.SigeaCommons.model.EventoModel") })
	private EventoModel eventoModel;
	
	@Column(name = "GENERIC_METADATA_WIP")
	@Type(type = "JsonNodeUserType")
	private JsonNode genericMetadataWip;
	
	@Column(name = "GENERIC_METADATA")
	@Type(type = "JsonNodeUserType")
	private JsonNode genericMetadata;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="EVENTO_ID", referencedColumnName="EVENTO_ID")
	private Evento evento;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="REDAZIONE_ID", referencedColumnName="REDAZIONE_ID")
	private Redazione redazione;
	
	@OneToMany(mappedBy = "pubblicazione")
	private Set<LogPubblicazioni> logPubblicazioni = new HashSet<>();
	
	
	public void addLog(LogPubblicazioni log) 
	{
		logPubblicazioni.add(log);
	}
}
