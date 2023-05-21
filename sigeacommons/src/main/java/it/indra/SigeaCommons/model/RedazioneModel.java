package it.indra.SigeaCommons.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RedazioneModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String redazioneId;
	
	private String nome;
	
	private String link;
	
	private String jspName;
	
	private String linkScheda;
	
	private Boolean autoSpubblicazione;
	
	private Boolean attivazioneJMS;
}
