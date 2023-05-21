package it.indra.SigeaCommons.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RuoloModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String ruoloId;
	
	private Map<String,Boolean> permessi = new HashMap<>();

}
