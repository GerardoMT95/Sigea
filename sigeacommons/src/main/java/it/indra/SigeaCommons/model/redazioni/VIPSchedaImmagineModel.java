package it.indra.SigeaCommons.model.redazioni;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class VIPSchedaImmagineModel implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long immagineId;
	
	@Size(max=300)
	private Map<String,String> didascaliaMulti = new HashMap<>();
	
	@Size(max=300)
	private String credits;
	
	private String nomeOriginale;
	
	private String estensione;
	
	private Long dimensione;
	
	private Long ordine;
	
	private Boolean banner = false;
	private Boolean daPubblicare = false;
	
}
