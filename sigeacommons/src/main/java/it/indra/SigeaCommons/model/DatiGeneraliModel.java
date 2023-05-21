package it.indra.SigeaCommons.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DatiGeneraliModel implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long eventoId;
	
	private Map<String,String> titoloMulti = new HashMap<>();
	
	private Map<String,String> abstractDescrMulti = new HashMap<>();
	
	private Map<String,String> snippetMulti = new HashMap<>();
	
	private Map<String,String> descrizioneMulti = new HashMap<>();
}
