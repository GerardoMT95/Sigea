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
import org.hibernate.annotations.TypeDef;
import org.javers.core.metamodel.annotation.DiffIgnore;

import it.indra.SigecAPI.extjpa.JsonDataUserType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="t_SIGEA_IMMAGINI",schema="sigec")
@TypeDef(name = "JsonDataUserType", typeClass = JsonDataUserType.class)
@Data
@EqualsAndHashCode(exclude="evento")
public class Immagine implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sigec.SIGEA_MEDIA_SEQ")
	@SequenceGenerator(name = "sigec.SIGEA_MEDIA_SEQ", sequenceName = "sigec.SIGEA_MEDIA_SEQ", allocationSize = 1, initialValue = 1)
	@Column(name = "IMMAGINE_ID")
	private Long immagineId;
	
	@Column(name = "DIDASCALIA", length=300)
	private String didascalia;
	
	@Column(name = "DIDASCALIA_MULTI")
	@Type(type = "JsonDataUserType", parameters = { @Parameter(name = "class", value = "java.util.Map") })
	private Map<String,String> didascaliaMulti = new HashMap<>();
	
	@Column(name = "CREDITS", length=300)
	private String credits;
	
	@Column(name = "NOME_ORIGINALE", length=300, updatable=false)
	private String nomeOriginale;
	
	@Column(name = "ESTENSIONE", length=20, updatable=false)
	private String estensione;
	
	@Column(name = "DIMENSIONE", updatable=false)
	private Long dimensione;
	

	@Column(name = "ORDINE")
	private Long ordine;
	
	@Column(name = "PATH_TO_COPY")
	private String pathToCopy;
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="EVENTO_ID", referencedColumnName="EVENTO_ID")
	@DiffIgnore
	private Evento evento;
}
