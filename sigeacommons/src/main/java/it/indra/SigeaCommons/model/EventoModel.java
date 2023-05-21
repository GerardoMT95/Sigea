package it.indra.SigeaCommons.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EventoModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long eventoId;
	
	private String tipo;
	
	private String idMIBACT;
	
	private String tipologiaMIBACT;
	
	private Boolean grandeEvento;
	
	private String ultimeNote;
	
	private String statoId;
	
	private String descrizioneStato;
	
	private Boolean fgValidazionePregressa;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp dataIns;
	
	//private String progettoId	
	
	private BandoModel bando;
	
	private ProgettoModel progetto;
	
	private Long ownerId;
	
	private String nomeOwner;
	
	private String cognomeOwner;
	
	private String usernameOwner;
	
	private String emailOwner;
	
	private String emailOldOwner;
	
	private String telOwner;
	
	private String celOwner;
	
	private Long ownerIdUltimaModifica;
	
	private String nomeOwnerUltimaModifica;
	
	private String cognomeOwnerUltimaModifica;
	
	private String usernameOwnerUltimaModifica;
	
	private String emailOwnerUltimaModifica;
	
	private String telOwnerUltimaModifica;
	
	private String celOwnerUltimaModifica;
	
	private TicketModel ticket;
	
	private AccessibilitaModel accessibilita;
	
	private AttivitaModel attivita;	
	
	private RichiestaAttivitaModel richiestaAttivita;
	
	private DatiGeneraliModel datiGenerali;
	
	private Set<TelefonoModel> telefonoSet = new HashSet<>();
		
	private Set<EmailModel> emailSet = new HashSet<>();
		
	private Set<SitoModel> sitoSet = new HashSet<>();
	
	private Set<LocationModel> locationSet = new HashSet<>();
	
	private Set<ImmagineModel> immagineSet = new HashSet<>();
	
	private Set<LinkModel> linkSet = new HashSet<>();
	
	private Set<DocumentoEventoModel> documentoSet = new HashSet<>();
	
	private Set<RelazioneModel> relazioneSet = new HashSet<>();
	
	private Set<LogEventoModel> logSet = new HashSet<>();
	
	private Set<PeriodoModel> periodoSet = new HashSet<>();

}
