package it.indra.SigeaCommons.model.redazioni;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import it.indra.SigeaCommons.model.AttrattoreModel;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown=true)
public class VIPSchedaModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String tipoScheda;
	
	private Map<String,String> tipologieMIBACT = new HashMap<>();
	
	private Long infopointId;
	private String infopoint;
	
	private String riferimento;
	private Set<String> prodottiSet = new HashSet<>();
	private Set<String> mezziSet = new HashSet<>();
	private Set<String> tipologieAttivitaSet = new HashSet<>();
	
	private Map<String,String> titoloMulti = new HashMap<>();
	private Map<String,String> abstractDescrMulti = new HashMap<>();
	private Map<String,String> snippetMulti = new HashMap<>();
	private Map<String,String> descrizioneMulti = new HashMap<>();
	
	private Long valoreId;
	private String valore;
	
	private Boolean big = false;
	private Boolean slideshow = false;
	
	private Long ranking;
	
	//consigliato per
	private Boolean arteC = false;
	private Boolean ass_spettacoli = false;
	private Boolean autentico = false;
	private Boolean lgbt = false;
	private Boolean lusso = false;
	private Boolean relax = false;
	private Boolean vac_anziani = false;
	private Boolean vac_bambini = false;
	private Boolean viag_affari = false;
	private Boolean avventura = false;
	private Boolean backpackers = false;
	private Boolean benessere = false;
	private Boolean salute = false;
	private Boolean shopping = false;
	private Boolean short_break = false;
	private Boolean viag_all_incl = false;
	private Boolean viag_amici = false;
	private Boolean viag_disabili = false;
	private Boolean celebrazioni = false;
	private Boolean congressi = false;
	private Boolean devozione = false;
	private Boolean sportC = false;
	private Boolean staying = false;
	private Boolean svago = false;
	private Boolean viag_incentive = false;
	private Boolean viag_natura = false;
	private Boolean viag_nozze = false;
	
	private Set<VIPSchedaImmagineModel> immagineSet = new HashSet<>();
	private Set<VIPSchedaLinkModel> linkSet = new HashSet<>();
	private Set<VIPSchedaDocumentoModel> documentoSet = new HashSet<>();
	
	private Set<VIPSchedaRelazioneModel> relazioneSet = new HashSet<>();
	
	private Set<VIPSchedaImmagineModel> immagineSetAggiunto = new HashSet<>();
	private Set<VIPSchedaLinkModel> linkSetAggiunto = new HashSet<>();
	private Set<VIPSchedaDocumentoModel> documentoSetAggiunto = new HashSet<>();
	
	private Set<VIPSchedaRelazioneModel> relazioneSetAggiunto = new HashSet<>();
	
	private Set<AttrattoreModel> attrattoriSet = new HashSet<>();
}
