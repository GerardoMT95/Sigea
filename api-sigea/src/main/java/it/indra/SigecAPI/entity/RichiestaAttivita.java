package it.indra.SigecAPI.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="t_SIGEA_RICHIESTAATTIVITA",schema="sigec")
@Data
public class RichiestaAttivita implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "RICHIESTA_ID")
	private Long richiestaAttivitaId;
	
	@Column(name = "DENOMINAZIONE")
	private String denominazione;
	
	//TODO aggiungere campi
	
}