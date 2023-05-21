package it.indra.SigeaCommons.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TipologiaModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String tipologiaId;
	
	private String descrizione;
	
	private Boolean isRedattore;
	
	private Set<RuoloModel> ruoliSet;
	
	private Set<RedazioneModel> redazioniSet;
	
	public Map<String,Boolean> getPermessiSommati() {
		Map<String,Boolean> permessiSommati = new HashMap<String,Boolean>();
		for (RuoloModel ruolo : ruoliSet) {
			permessiSommati.putAll(ruolo.getPermessi());
		}
		return permessiSommati;
	}
	
}
