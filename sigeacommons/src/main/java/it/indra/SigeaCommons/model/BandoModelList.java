package it.indra.SigeaCommons.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@AllArgsConstructor
@FieldNameConstants
public class BandoModelList implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private String bandoId;

	private String titoloBando;
	
	private String progettoId;

	private String titoloProgetto;

	private String nomeOrganizzazione;



}
