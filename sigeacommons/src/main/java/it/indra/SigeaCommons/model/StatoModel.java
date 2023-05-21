package it.indra.SigeaCommons.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StatoModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String statoId;
	
	@NotNull
	@Size(max=50)
	private String descrizione;
	
	private Boolean statoInizio;
	
	private Boolean statoFine;
	
	private Boolean accessoCondizionato;
	
	private String nome;
	
	private ConfigurazioneStato configurazioneStato;
}
