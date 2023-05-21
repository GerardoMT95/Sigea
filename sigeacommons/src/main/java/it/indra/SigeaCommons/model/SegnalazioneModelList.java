package it.indra.SigeaCommons.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@AllArgsConstructor
@FieldNameConstants
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SegnalazioneModelList implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String segnalazioneId;
	
	private String nome;
	
	private String dataDa;
	
	private String dataA;
	
	private String comune;
	
	private String riferimento;
	
	private String dataIns;
	
	private String status;
	
	private String nomeUtente;
	
	private String emailUtente;

}
