package it.indra.SigeaCommons.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class StatoRaggiungibile implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String statoId;
	
	private Boolean pubblicazione;
	
	private Boolean revocaPubblicazione;
	
	private String emailOwner;
	
	private String emailOperatori;
	
	private Boolean addNote;
	
	private List<String> statiSovrascritti;
	
	private Map<String,Boolean> permessi = new HashMap<>();

}
