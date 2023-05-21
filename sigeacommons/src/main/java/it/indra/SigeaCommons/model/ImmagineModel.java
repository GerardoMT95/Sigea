package it.indra.SigeaCommons.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties({"pathToCopy"})
public class ImmagineModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long immagineId;
	
	@Size(max=300)
	private Map<String,String> didascaliaMulti = new HashMap<>();
	
	@Size(max=300)
	private String credits;
	
	private String nomeOriginale;
	
	private String estensione;
	
	private Long dimensione;
	
	private String pathToCopy;
	
	private Long ordine;
}
