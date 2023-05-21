package it.indra.SigecAPI.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="t_SIGEA_PRODOTTI",schema="sigec")
@Data
public class Prodotto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PRODOTTO_ID")
	private String prodottoId;
	
	@Column(name = "DESCRIZIONE")
	private String prodotto;
	
}