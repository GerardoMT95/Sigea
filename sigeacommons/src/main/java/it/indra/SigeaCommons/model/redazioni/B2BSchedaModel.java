package it.indra.SigeaCommons.model.redazioni;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown=true)
public class B2BSchedaModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Set<String> prodottiSet = new HashSet<>();
	private Set<String> segmentiSet = new HashSet<>();
	private Set<String> sottotipologieSet = new HashSet<>();
	private Boolean accreditamentoSeller = false;
	private Set<String> modalitaPartecipazioneSet = new HashSet<>();
	private String nomePartecipante;
	private String cognomePartecipante;
	private String telefonoPartecipante;
	private String mailPartecipante;
	private Boolean accreditamentoBuyer = false;
	private Boolean agenda = false;
	private Boolean pagamento = false;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dataDaAccreditamento;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dataAAccreditamento;
	private Set<B2BSchedaDocumentoModel> documentoSetAggiunto = new HashSet<>();
}
