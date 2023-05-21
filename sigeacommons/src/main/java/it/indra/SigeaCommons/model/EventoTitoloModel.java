package it.indra.SigeaCommons.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class EventoTitoloModel {
	
	private Long eventoId;
	
	private String titolo;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp dataIns;
	
	private String tipo;
	
	private String comune;
	
	private String periodo;

}
