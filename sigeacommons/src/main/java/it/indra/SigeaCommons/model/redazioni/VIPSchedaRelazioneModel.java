package it.indra.SigeaCommons.model.redazioni;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class VIPSchedaRelazioneModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long relazioneId;
	
	private Long eventoRelazionatoId;
	
	private String tipoEventoAssociato;
	
	private String tipoRelazione;
	
	private String titolo;
	
	private Boolean mantieni;
	
	private String comune;
	
	private String periodo;
	
	private String schedePubblicazione;
	
	private String statoEventoAssociato;
	
	private String redazioneId;

}
