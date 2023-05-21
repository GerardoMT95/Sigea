package it.indra.SigeaCommons.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProgettoModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String progettoId;

	private String titoloProgetto;

	private String nomeOrganizzazione;

	private String bandoId;

	private String codiceFiscale;

}
