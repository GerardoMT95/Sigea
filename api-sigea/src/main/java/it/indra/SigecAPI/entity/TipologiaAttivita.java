package it.indra.SigecAPI.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name="t_SIGEA_TIPOLOGIE_ATTIVITA",schema="sigec")
@Data
public class TipologiaAttivita implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "TIPOLOGIA_ID")
	private String tipologiaId;
	
	@Column(name = "DESCRIZIONE")
	private String tipologia;
	
}