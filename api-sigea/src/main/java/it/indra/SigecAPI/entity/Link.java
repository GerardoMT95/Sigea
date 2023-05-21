package it.indra.SigecAPI.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.javers.core.metamodel.annotation.DiffIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="t_SIGEA_LINK",schema="sigec")
@Data
@EqualsAndHashCode(exclude="evento")
public class Link implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sigec.SIGEA_LINK_SEQ")
	@SequenceGenerator(name = "sigec.SIGEA_LINK_SEQ", sequenceName = "sigec.SIGEA_LINK_SEQ", allocationSize = 1, initialValue = 1)
	@Column(name = "LINK_ID")
	private Long linkId;
	
	@Column(name = "TITOLO", length=300)
	private String titolo;
	
	@Column(name = "TITOLO_MULTI")
	@Type(type = "JsonDataUserType", parameters = { @Parameter(name = "class", value = "java.util.Map") })
	private Map<String,String> titoloMulti = new HashMap<>();
	
	@Column(name = "DIDASCALIA", length=300)
	private String didascalia;
	
	@Column(name = "DIDASCALIA_MULTI")
	@Type(type = "JsonDataUserType", parameters = { @Parameter(name = "class", value = "java.util.Map") })
	private Map<String,String> didascaliaMulti = new HashMap<>();
	
	@Column(name = "CREDITS", length=300)
	private String credits;
	
	@Column(name = "LINK", length=300)
	private String link;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="EVENTO_ID", referencedColumnName="EVENTO_ID")
	@DiffIgnore
	private Evento evento;
	
	@Column(name = "ordine")
	private Long ordine;
}