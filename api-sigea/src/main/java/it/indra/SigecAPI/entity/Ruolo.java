package it.indra.SigecAPI.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import it.indra.SigecAPI.extjpa.JsonDataUserType;
import lombok.Data;

@Entity
@Table(name="t_SIGEA_RUOLI",schema="sigec")
@TypeDef(name = "JsonDataUserType", typeClass = JsonDataUserType.class)
@Data
public class Ruolo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "RUOLO_ID")
	private String ruoloId;
	
	@Column(name = "PERMESSI")
	@Type(type = "JsonDataUserType", parameters = { @Parameter(name = "class", value = "java.util.Map") })
	private Map<String,Boolean> permessi = new HashMap<>();
}
