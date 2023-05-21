package it.indra.SigecAPI.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="t_SIGEA_ATTIVITA",schema="sigec")
@Data
public class Attivita implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ATTIVITA_ID")
	private Long attivitaId;
	
	@Column(name = "DENOMINAZIONE")
	private String denominazione;
	
	//TODO aggiungere campi
	
}
