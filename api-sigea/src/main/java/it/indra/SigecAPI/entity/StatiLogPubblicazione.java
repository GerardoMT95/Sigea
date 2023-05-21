package it.indra.SigecAPI.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.TypeDef;

import it.indra.SigecAPI.extjpa.JsonDataUserType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

@Entity
@Table(name="t_SIGEA_STATI_PUBBLICAZIONI",schema="sigec")
@TypeDef(name = "JsonDataUserType", typeClass = JsonDataUserType.class)
@Data
public class StatiLogPubblicazione implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CODICE_STATO")
	private String statoId;

	@Column(name = "DESCRIZIONE_stato")
	private String descrizione;
	

}
