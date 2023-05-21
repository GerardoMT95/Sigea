package it.indra.SigeaCommons.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SegnalazioneFilter {

	String titolo;

	String dataDa;

	String dataA;
	
	Long idUtenteIns;

	String status;
	
	String codIstat;
	
	String comuneEstero;
}
