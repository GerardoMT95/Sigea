package it.indra.SigecAPI.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.javers.core.metamodel.annotation.DiffIgnore;

import it.indra.SigecAPI.extjpa.JsonDataUserType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="t_SIGEA_DATIGENERALI",schema="sigec")
@TypeDef(name = "JsonDataUserType", typeClass = JsonDataUserType.class)
@Data
@EqualsAndHashCode(exclude="evento")
public class DatiGenerali implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "EVENTO_ID")
	private Long eventoId;
	
	@Column(name = "TITOLO", length=300)
	private String titolo;
	
	@Column(name = "TITOLO_MULTI")
	@Type(type = "JsonDataUserType", parameters = { @Parameter(name = "class", value = "java.util.Map") })
	private Map<String,String> titoloMulti = new HashMap<>();
	
	@Column(name = "ABSTRACT", length=300)
	private String abstractDescr;
	
	@Column(name = "ABSTRACT_MULTI")
	@Type(type = "JsonDataUserType", parameters = { @Parameter(name = "class", value = "java.util.Map") })
	private Map<String,String> abstractDescrMulti = new HashMap<>();
	
	@Column(name = "SNIPPET", length=300)
	private String snippet;
	
	@Column(name = "SNIPPET_MULTI")
	@Type(type = "JsonDataUserType", parameters = { @Parameter(name = "class", value = "java.util.Map") })
	private Map<String,String> snippetMulti = new HashMap<>();
	
	@Column(name = "DESCRIZIONE", length=4000)
	private String descrizione;
	
	@Column(name = "DESCRIZIONE_MULTI")
	@Type(type = "JsonDataUserType", parameters = { @Parameter(name = "class", value = "java.util.Map") })
	private Map<String,String> descrizioneMulti = new HashMap<>();
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="EVENTO_ID")
	@MapsId
	@DiffIgnore
	private Evento evento;

}
