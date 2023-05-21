package it.indra.SigeaCommons.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class ElencoEventiEmail {
	
	private String email;
	
	private Map<Long,String> eventi = new HashMap<Long,String>();
	
	private String motivazioneErrore;

}
