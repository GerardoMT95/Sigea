package it.indra.SigeaCommons.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BandoModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String bandoId;

	private String titoloBando;

	//private ProgettoModel progetto;

}
