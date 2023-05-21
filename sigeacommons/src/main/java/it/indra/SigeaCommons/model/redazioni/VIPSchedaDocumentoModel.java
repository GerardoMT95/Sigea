package it.indra.SigeaCommons.model.redazioni;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class VIPSchedaDocumentoModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long documentoId;
	
	@Size(max=300)
	private Map<String,String> titoloMulti = new HashMap<>();
	
	private String nomeOriginale;

	private Long ordineNumerico;
	
	private String estensione;
	
	private Long dimensione;
	
	private Boolean daPubblicare;
}
