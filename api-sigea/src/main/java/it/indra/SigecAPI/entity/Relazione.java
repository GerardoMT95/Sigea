package it.indra.SigecAPI.entity;

import java.io.Serializable;
import java.util.Set;

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

import org.javers.core.metamodel.annotation.DiffIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="t_SIGEA_RELAZIONI",schema="sigec")
@Data
@EqualsAndHashCode(exclude= {"evento","eventoRelazionato"})
public class Relazione implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sigec.SIGEA_RELAZIONI_SEQ")
	@SequenceGenerator(name = "sigec.SIGEA_RELAZIONI_SEQ", sequenceName = "sigec.SIGEA_RELAZIONI_SEQ", allocationSize = 1, initialValue = 1)
	@Column(name = "RELAZIONE_ID")
	private Long relazioneId;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="EVENTO_ID", referencedColumnName="EVENTO_ID")
	@DiffIgnore
	private Evento evento;
	
	@Column(name = "TIPO_RELAZIONE", length=20)
	private String tipoRelazione;
	
	@Column(name = "EVENTO_RELAZIONATO_ID")
	private Long eventoRelazionatoId;
	
	@Column(name = "TIPO_EVENTO_RELAZIONATO")
	private String tipoEventoAssociato;
	
	@Column(name = "REDAZIONE_ID")
	private String redazioneId;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="EVENTO_RELAZIONATO_ID", referencedColumnName="EVENTO_ID", updatable = false, insertable = false)
	@DiffIgnore
	private Evento eventoRelazionato;
}
