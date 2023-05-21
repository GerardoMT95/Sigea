package it.indra.SigeaCommons.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LinkModel implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	private Long linkId;
	
	private Map<String,String> titoloMulti = new HashMap<>();
	
	private Map<String,String> didascaliaMulti = new HashMap<>();
	
	private String credits;
	
	@Size(max=300)
	private String link;

	private Long ordine;
}
