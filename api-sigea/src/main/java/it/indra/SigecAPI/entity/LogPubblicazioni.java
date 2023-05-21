package it.indra.SigecAPI.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.TypeDef;
import org.javers.core.metamodel.annotation.DiffIgnore;

import it.indra.SigecAPI.extjpa.JsonDataUserType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="t_SIGEA_LOG_PUBBLICAZIONI",schema="sigec")
@Data
@EqualsAndHashCode(exclude="pubblicazione")
@TypeDef(name = "JsonDataUserType", typeClass = JsonDataUserType.class)
public class LogPubblicazioni {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sigec.SIGEA_LOGPUBBLICAZIONI_SEQ")
	@SequenceGenerator(name = "sigec.SIGEA_LOGPUBBLICAZIONI_SEQ", sequenceName = "sigec.SIGEA_LOGPUBBLICAZIONI_SEQ", allocationSize = 1, initialValue = 1)
	@Column(name = "id_log_pubblicazioni")
	private Long logId;
	
	@OneToOne
	@JoinColumn(name="codice_Stato", referencedColumnName="codice_Stato")
	private StatiLogPubblicazione stato;
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="PUBBLICAZIONE_ID", referencedColumnName="PUBBLICAZIONE_ID")
	@DiffIgnore
	private Pubblicazione pubblicazione;
	
	
	@Column(name="NOTE")
	private String note;
	

	@Column(name="IDUTENTE_MODIFICA")
	private Long idUtenteModifica;
	
	
	@Column(name="DATA_MODIFICA", updatable=false)
	@CreationTimestamp
	private Timestamp dataModifica;
}
