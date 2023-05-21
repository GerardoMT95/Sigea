package it.indra.SigecAPI.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name="t_SIGEA_SEGNALAZIONI",schema="sigec")
@Data
public class Segnalazione implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sigec.SIGEA_SEGNALAZIONI_SEQ")
	@SequenceGenerator(name = "sigec.SIGEA_SEGNALAZIONI_SEQ", sequenceName = "sigec.SIGEA_SEGNALAZIONI_SEQ", allocationSize = 1, initialValue = 1)
	@Column(name="SEGNALAZIONE_ID")
	private Long segnalazioneId;

	@Column(name="NOME", nullable=false, length=100)
	private String nome;

	@Column(name="DESCRIZIONE", length=4000)
	private String descrizione;

	@Column(name="DATA_DA", nullable=false)
	private Date dataDa;

	@Column(name="DATA_A", nullable=false)
	private Date dataA;

	@Column(name="LOCATION", length=500)
	private String location;

	@Column(name="INDIRIZZO", length=500)
	private String indirizzo;

	@Column(name="RIFERIMENTO", nullable=false, length=500)
	private String riferimento;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="IDUTENTE_INSERIMENTO", referencedColumnName="UTENTE_ID")
	private DettaglioUtente usernameIns;

	@Column(name="DATA_INSERIMENTO", updatable=false)
	@CreationTimestamp
	private Timestamp dataIns;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name="IDUTENTE_MODIFICA", referencedColumnName="UTENTE_ID")
	private DettaglioUtente usernameMod;

	@Column(name="DATA_MODIFICA")
	@UpdateTimestamp
	private Timestamp dataMod;

	@Column(name="STATUS", nullable=false, length=50)
	private String status = "Da valutare";
	
	@Column(name="CODISTAT", length=6)
	private String codIstat;
	
	@Column(name="COMUNE", length=300)
	private String comune;
	
	
}