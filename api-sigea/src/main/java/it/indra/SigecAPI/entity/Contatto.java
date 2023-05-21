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

import org.javers.core.metamodel.annotation.DiffIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="t_SIGEA_CONTATTI",schema="sigec")
@Data
@EqualsAndHashCode(exclude="evento")
public class Contatto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sigec.SIGEA_CONTATTI_SEQ")
	@SequenceGenerator(name = "sigec.SIGEA_CONTATTI_SEQ", sequenceName = "sigec.SIGEA_CONTATTI_SEQ", allocationSize = 1, initialValue = 1)
	@Column(name = "CONTATTO_ID")
	private Long contattoId;
	
	@Column(name = "TIPO", length=20)
	private String tipo;
	
	@Column(name = "CONTATTO", length=300)
	private String contatto;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="EVENTO_ID", referencedColumnName="EVENTO_ID")
	@DiffIgnore
	private Evento evento;
}
