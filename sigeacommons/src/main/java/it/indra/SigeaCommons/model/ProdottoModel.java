package it.indra.SigeaCommons.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class ProdottoModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String prodottoId;
	
	private String prodotto;
	
}