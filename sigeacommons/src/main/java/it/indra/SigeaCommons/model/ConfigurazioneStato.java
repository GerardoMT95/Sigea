package it.indra.SigeaCommons.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ConfigurazioneStato implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Boolean isEditableByOwner;
	
	private Boolean isWorkerExclusive;

	private List<StatoRaggiungibile> statiRaggiungibili = new ArrayList<StatoRaggiungibile>();
}
