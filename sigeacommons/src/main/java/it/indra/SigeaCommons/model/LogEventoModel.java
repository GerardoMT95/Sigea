package it.indra.SigeaCommons.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LogEventoModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long logId;
	
	@Size(max=100)
	private String tipoOperazione;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp dataModifica;
	
	@Size(max=100)
	private String nomeUtente;
	
	@Size(max=4000)
	private String operazioni;
	
	@Size(max=50)
	private String descrizioneStato;
	
	@Size(max=100)
	private String denominazioneAttivita;
	
}
