package it.indra.SigecAPI.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="t_SIGEA_DECODIFICA",schema="sigec")
@Data
public class Decodifica implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="KEY")
	private String key;
	
	@Column(name="VALUE")
	private String value;
}
