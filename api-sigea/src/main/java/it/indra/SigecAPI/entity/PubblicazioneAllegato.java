package it.indra.SigecAPI.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="t_SIGEA_PUBB_ALLEGATI",schema="sigec")
@Data
public class PubblicazioneAllegato implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sigec.SIGEA_MEDIA_SEQ")
	@SequenceGenerator(name = "sigec.SIGEA_MEDIA_SEQ", sequenceName = "sigec.SIGEA_MEDIA_SEQ", allocationSize = 1, initialValue = 1)
	@Column(name = "ALLEGATO_ID")
	private Long allegatoId;
	
	@Column(name = "NOME_ORIGINALE", length=300, updatable=false)
	private String nomeOriginale;
	
	@Column(name = "ESTENSIONE", length=20, updatable=false)
	private String estensione;
	
	@Column(name = "DIMENSIONE", updatable=false)
	private Long dimensione;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="PUBBLICAZIONE_ID", referencedColumnName="PUBBLICAZIONE_ID")
	private Pubblicazione pubblicazione;

}
