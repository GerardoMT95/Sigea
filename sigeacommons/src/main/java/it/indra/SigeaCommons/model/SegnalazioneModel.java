package it.indra.SigeaCommons.model;

import java.io.Serializable;
import java.sql.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SegnalazioneModel implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long segnalazioneId;

	@NotNull
	@NotBlank
	@Size(max=100)
	private String nome;

	@Size(max=4000)
	private String descrizione;

	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dataDa;

	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dataA;

	@Size(max=500)
	private String location;

	@Size(max=500)
	private String indirizzo;

	@NotNull
	@NotBlank
	@Size(max=500)
	private String riferimento;
	
	private Long utenteId;
	
	private String codIstat;
	
	private String comune;
	
	private String comuneEstero;
	
	private String nomeUtente;
	
	private String emailUtente;
}
