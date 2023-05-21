package it.indra.SigeaCommons.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@AllArgsConstructor
@FieldNameConstants
public class SmartModelList implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String codComune;
	
	private String comune;
	
	private String codProvincia;
	
	private String provincia;
	
	private String codRegione;
	
	private String regione;
	
	private String codNazione;
	
	private String nazione;
	
	private Long numEventi;

}
