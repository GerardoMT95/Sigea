package it.indra.SigecAPI.entity;

import java.io.Serializable;
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
import org.javers.core.metamodel.annotation.DiffIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="t_SIGEA_LOGEVENTI",schema="sigec")
@Data
@EqualsAndHashCode(exclude="evento")
public class LogEvento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sigec.SIGEA_LOGEVENTI_SEQ")
	@SequenceGenerator(name = "sigec.SIGEA_LOGEVENTI_SEQ", sequenceName = "sigec.SIGEA_LOGEVENTI_SEQ", allocationSize = 1, initialValue = 1)
	@Column(name = "LOG_ID")
	private Long logId;
	
	@Column(name = "TIPO_OPERAZIONE", length=100)
	private String tipoOperazione;
	
	@Column(name="DATA_MODIFICA", updatable=false)
	@CreationTimestamp
	private Timestamp dataModifica;
	
	@Column(name="NOME_UTENTE", length=100)
	private String nomeUtente;
	
	@Column(name = "OPERAZIONI", length=4000)
	private String operazioni;
	
	@Column(name = "DESCRIZIONE_STATO", length=50)
	private String descrizioneStato;
	
	@Column(name = "NOTE", length=4000)
	private String note;
	
	@Column(name="DENOMINAZIONE_ATTIVITA", length=100)
	private String denominazioneAttivita;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="EVENTO_ID", referencedColumnName="EVENTO_ID")
	@DiffIgnore
	private Evento evento;
}
