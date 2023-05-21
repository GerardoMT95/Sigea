package it.indra.SigeaCommons.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DettaglioUtenteModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long utenteId;

	private String username;
	
	private String nome;

	private String cognome;
	
	private String codFiscale;
	
	private String email;
	
	private String tel;
	
	private String cel;
	
	private Long entitaId;
	
	private TipologiaModel tipologia;
	
}
