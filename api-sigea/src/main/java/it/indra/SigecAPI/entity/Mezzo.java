package it.indra.SigecAPI.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="t_SIGEA_MEZZI",schema="sigec")
@Data
public class Mezzo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "MEZZO_ID")
	private String mezzoId;
	
	@Column(name = "DESCRIZIONE")
	private String mezzo;
	
}