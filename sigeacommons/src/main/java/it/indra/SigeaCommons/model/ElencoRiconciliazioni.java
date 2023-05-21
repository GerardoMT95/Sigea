package it.indra.SigeaCommons.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class ElencoRiconciliazioni {	
	
	private Long idAttivita;
	
	private String denominazioneAttivita;
	
	private Map<Long,String> eventi = new HashMap<Long,String>();

}
