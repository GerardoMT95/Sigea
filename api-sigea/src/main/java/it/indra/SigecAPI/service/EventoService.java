package it.indra.SigecAPI.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.diff.custom.BigDecimalComparatorWithFixedEquals;
import org.json.JSONObject;
//import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import it.indra.SigeaCommons.enumeration.StatoPubblicazione;
import it.indra.SigeaCommons.model.AttivitaModel;
import it.indra.SigeaCommons.model.DettaglioUtenteModel;
import it.indra.SigeaCommons.model.DocumentoEventoModel;
import it.indra.SigeaCommons.model.EventoFilter;
import it.indra.SigeaCommons.model.EventoModel;
import it.indra.SigeaCommons.model.EventoModelList;
import it.indra.SigeaCommons.model.EventoTitoloModel;
import it.indra.SigeaCommons.model.ImmagineModel;
import it.indra.SigeaCommons.model.LocationModel;
import it.indra.SigeaCommons.model.LogEventoModel;
import it.indra.SigeaCommons.model.PeriodoModel;
import it.indra.SigeaCommons.model.PubblicazioneAllegatoModel;
import it.indra.SigeaCommons.model.PubblicazioneModel;
import it.indra.SigeaCommons.model.SmartModelList;
import it.indra.SigeaCommons.model.StatoModel;
import it.indra.SigeaCommons.model.StatoRaggiungibile;
import it.indra.SigeaCommons.model.TicketModel;
import it.indra.SigeaCommons.model.redazioni.VIPSchedaDocumentoModel;
import it.indra.SigeaCommons.model.redazioni.VIPSchedaImmagineModel;
import it.indra.SigecAPI.clientstub.DMSClientStub;
//import it.indra.SigecAPI.clientstub.DMSClientStub;
import it.indra.SigecAPI.configuration.MessageRetriever;
import it.indra.SigecAPI.datafiltermanager.EventoFilterManager;
import it.indra.SigecAPI.entity.Attivita;
import it.indra.SigecAPI.entity.Attrattore;
import it.indra.SigecAPI.entity.Bando;
import it.indra.SigecAPI.entity.Contatto;
import it.indra.SigecAPI.entity.DettaglioUtente;
import it.indra.SigecAPI.entity.DocumentoEvento;
import it.indra.SigecAPI.entity.Evento;
import it.indra.SigecAPI.entity.Immagine;
import it.indra.SigecAPI.entity.Location;
import it.indra.SigecAPI.entity.LogEvento;
import it.indra.SigecAPI.entity.LogPubblicazioni;
import it.indra.SigecAPI.entity.Periodo;
import it.indra.SigecAPI.entity.Pubblicazione;
import it.indra.SigecAPI.entity.PubblicazioneAllegato;
import it.indra.SigecAPI.entity.Pubblicazione_;
import it.indra.SigecAPI.entity.Redazione;
import it.indra.SigecAPI.entity.Redazione_;
import it.indra.SigecAPI.entity.Relazione;
import it.indra.SigecAPI.entity.RichiestaAttivita;
import it.indra.SigecAPI.entity.StatiLogPubblicazione;
import it.indra.SigecAPI.entity.Stato;
import it.indra.SigecAPI.entity.Tipologia;
import it.indra.SigecAPI.exception.LockedResourceException;
import it.indra.SigecAPI.filerepository.FileRepository;
import it.indra.SigecAPI.mapper.AttivitaMapper;
import it.indra.SigecAPI.mapper.DettaglioUtenteMapper;
import it.indra.SigecAPI.mapper.DocumentoEventoMapper;
import it.indra.SigecAPI.mapper.EventoMapper;
import it.indra.SigecAPI.mapper.ImmagineMapper;
import it.indra.SigecAPI.mapper.LogEventoMapper;
import it.indra.SigecAPI.mapper.PeriodoMapper;
import it.indra.SigecAPI.mapper.PubblicazioneAllegatoMapper;
import it.indra.SigecAPI.mapper.PubblicazioneMapper;
import it.indra.SigecAPI.mapper.StatoMapper;
import it.indra.SigecAPI.projection.EventoTitoloProjection;
import it.indra.SigecAPI.projection.PubblicazioneProjection;
import it.indra.SigecAPI.repository.AttivitaRepository;
import it.indra.SigecAPI.repository.AttrattoreRepository;
import it.indra.SigecAPI.repository.BandoRepository;
import it.indra.SigecAPI.repository.DettaglioUtenteRepository;
import it.indra.SigecAPI.repository.DocumentoEventoRepository;
import it.indra.SigecAPI.repository.EventoRepository;
import it.indra.SigecAPI.repository.ImmagineRepository;
import it.indra.SigecAPI.repository.LogEventoRepository;
import it.indra.SigecAPI.repository.LogPubblicazioneRepository;
import it.indra.SigecAPI.repository.ProgettoRepository;
import it.indra.SigecAPI.repository.PubblicazioneAllegatoRepository;
import it.indra.SigecAPI.repository.PubblicazioneRepository;
import it.indra.SigecAPI.repository.RedazioneRepository;
import it.indra.SigecAPI.repository.RelazioneRepository;
import it.indra.SigecAPI.repository.RichiestaAttivitaRepository;
import it.indra.SigecAPI.repository.StatoPubblicazioneRepository;
import it.indra.SigecAPI.repository.StatoRepository;
import it.indra.SigecAPI.repository.TipologiaRepository;
import it.indra.SigecAPI.rsql.CustomRsqlVisitor;
import it.indra.SigecAPI.util.CommonUtility;
import it.indra.SigecAPI.util.DateCustomComparator;
import it.indra.SigecAPI.util.WrapperFilterRequest;
import it.indra.SigecAPI.util.WrapperFilterResponse;
import it.puglia.spc.ect.commons.mail.dto.AttachmentDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EventoService {

	private static final String SCHEDA_EVENTO = "evento";
	private static final String SCHEDA_ATTIVITA = "attivita";
	private static final String PLACEHOLDER = "XXXXX";

	@Value("#{'${pugliaevents.ownerList}'.split(';')}")
	private List<Integer> pugliaEventsOwnerList;

	@Autowired
	private AsyncService asyncService;

	@Autowired
	private DMSClientStub dmsService;

	@Autowired
	private RelazioniService relazioniService;

	@Autowired
	private LockService lockService;

	@Autowired
	private MessageRetriever messageRetriever;

	@Autowired
	private MailService mailService;

	@Autowired
	private EventoRepository eventoRepository;

	@Autowired
	private AttivitaRepository attivitaRepository;

	@Autowired
	private RichiestaAttivitaRepository richiestaAttivitaRepository;

	@Autowired
	private RelazioneRepository relazioneRepository;

	@Autowired
	private DettaglioUtenteRepository dettaglioUtenteRepository;

	@Autowired
	private StatoRepository statoRepository;

	@Autowired
	private AttrattoreRepository attrattoreRepository;

	@Autowired
	private ImmagineRepository immagineRepository;

	@Autowired
	private DocumentoEventoRepository documentoRepository;

	@Autowired
	private PubblicazioneRepository pubblicazioneRepository;

	@Autowired
	private RedazioneRepository redazioneRepository;

	@Autowired
	private PubblicazioneAllegatoRepository pubblicazioneAllegatoRepository;

	@Autowired
	private TipologiaRepository tipologiaRepository;

	@Autowired
	private BandoRepository bandoRepository;

	@Autowired
	private ProgettoRepository progettoRepository;

	@Autowired
	private LogEventoRepository logEventoRepository;

	@Autowired
	private EventoMapper eventoMapper;

	@Autowired
	private DettaglioUtenteMapper dettaglioUtenteMapper;

	@Autowired
	private StatoMapper statoMapper;

	@Autowired
	private ImmagineMapper immagineMapper;

	@Autowired
	private DocumentoEventoMapper documentoMapper;

	@Autowired
	private PubblicazioneMapper pubblicazioneMapper;

	@Autowired
	private PubblicazioneAllegatoMapper pubblicazioneAllegatoMapper;

	@Autowired
	private AttivitaMapper attivitaMapper;

	@Autowired
	private PeriodoMapper periodoMapper;

	@Autowired
	private LogEventoMapper logEventoMapper;

	@Autowired
	CacheTokenService cacheTokenService;

	@Autowired
	private RicevutaService ricevutaService;

	@Value("${filesystem.repository.root}")
	private String repositoryRoot;

	@Value("${filesystem.repository.image}")
	private String imageFolderName;

	@Value("${filesystem.repository.document}")
	private String documentFolderName;

	@Value("${filesystem.repository.publication}")
	private String publicationFolderName;

	// private JSONArray configurazioneEmail;

	Stato statoIniziale;

	Stato statoFinale;

	@PostConstruct
	public void inizializzaConfigurazione() {
		statoIniziale = statoRepository.findByStatoInizioTrue();
		statoFinale = statoRepository.findByStatoFineTrue();
	}

	public Long saveEvento(EventoModel eventoModel, Long idUtente, String stato)
			throws LockedResourceException, IOException, Exception {

		boolean flagSpubblicazione = false;
		boolean flagPubblicazioneGenerica = false;
		boolean flagAddNote = false;
		String emailOwner = null;
		String emailRedattori = null;

		// recupero dal DB l'utente che sta facendo la chiamata
		DettaglioUtente chiamante = dettaglioUtenteRepository.findById(idUtente).get();
		
		/*
		 * il caso gestisce il passaggio automatico da IN ATTESA DI
		 * VALUTAZIONE(VALUTAZIONE) a IN VALUTAZIONE (LAVORAZIONE)
		 */
		if (stato != null && stato.equalsIgnoreCase("PASSAGGIO_AUTOMATICO_LAVORAZIONE")) {

			if (chiamante.getTipologia().getRuoliSet().stream()
					.anyMatch(ruolo -> ruolo.getRuoloId().equalsIgnoreCase("VALIDATORE"))) {
				stato="LAVORAZIONE";
				eventoModel = getEvento(eventoModel.getEventoId(), idUtente);
				// VERIFICA SE LO STATO DELL'EVENTO E' EFFETTIVAMENTE IN ATTESA DI VALUTAZIONE O RIVALUTAZIONE:
				if (!(eventoModel.getStatoId().equalsIgnoreCase("VALUTAZIONE") || eventoModel.getStatoId().equalsIgnoreCase("RIVALUTAZIONE")) ) {
					return 0L;
				}
			} else {
				return 0L;
			}
		}
		


		// pulisco l'evento da eventuli dati sporchi
		cleanEventoModel(eventoModel);

		// Ricavo l'entity dal model
		final Evento evento = eventoMapper.dtoToEntity(eventoModel);

		// Predispongo un eventuale evento old nel caso si tratti di un update
		Evento oldEvento = null;

		// Verifico se si tratta di un aggiornamento di un evento pre-esistente
		if (eventoModel.getEventoId() != null) {

			// Mi accerto che il chiamante abbia il lock per questo evento
			lockService.lockRisorsa(evento.getEventoId(), idUtente, Evento.class);

			// Prendo la versione corrente dell'evento
			oldEvento = eventoRepository.findById(evento.getEventoId()).get();

			if (stato != null && statoFinale.getStatoId().equals(stato)) {

				if (oldEvento != null) {

					Set<LogEvento> logEventoSet = oldEvento.getLogSet();

					for (LogEvento log : logEventoSet) {
						if (log.getDescrizioneStato().equals(statoFinale.getDescrizione())) {
							stato = "RIVALIDATO";
						}
					}
				}
			}

			if (stato == null || stato.equals("AGGIORNA")) {
				evento.setStato(oldEvento.getStato());
			} else {
				evento.setStato(statoRepository.getOne(stato));
			}
			evento.setOwner(oldEvento.getOwner());
			evento.setOwnerUltimaModifica(chiamante);
			evento.setFgPubblicato(oldEvento.getFgPubblicato());
			evento.setFgValidazionePregressa(oldEvento.getFgValidazionePregressa());
		} else {
			evento.setStato(statoIniziale);
			evento.setOwner(chiamante);
			evento.setOwnerUltimaModifica(chiamante);
		}

		Set<LogEvento> logEventoSet = null;

		// Creo o aggiorno il log
		LogEvento logEvento = new LogEvento();
//		Set<LogEvento> logEventoSet = null;
		logEvento.setNomeUtente(evento.getOwner().getNome() + " " + evento.getOwner().getCognome());

		if (chiamante.getEntitaId() != null) {
			Optional<Attivita> attivita = attivitaRepository.findById(chiamante.getEntitaId());
			if (attivita.isPresent()) {
				logEvento.setDenominazioneAttivita(attivita.get().getDenominazione());
			} else {
				Optional<RichiestaAttivita> attivitaRichiesta = richiestaAttivitaRepository
						.findById(chiamante.getEntitaId());
				if (attivitaRichiesta.isPresent()) {
					logEvento.setDenominazioneAttivita(attivitaRichiesta.get().getDenominazione());
				}
			}
		}

		if (oldEvento != null) {

			logEventoSet = oldEvento.getLogSet();
			try {

				boolean ricevutaCreata = ricevutaService.checkFile(evento.getEventoId());

				if (evento.getBando() != null && !ricevutaCreata) {

					if (stato != null && (stato.equalsIgnoreCase("VALUTAZIONE") ||stato.equalsIgnoreCase("RIVALUTAZIONE")  || stato.equalsIgnoreCase("VALIDATO")
							|| stato.equalsIgnoreCase("RIVALIDATO"))) {

						byte[] ricevuta = ricevutaService.createRicevuta(eventoModel, stato);
						if (ricevuta == null) {
							throw new Exception();
						}
						String stringRicevuta = DatatypeConverter.printBase64Binary(ricevuta);
						String nomeFile = "Ricevuta evento finanziato";
						AttachmentDTO allegato = new AttachmentDTO();
						allegato.setAttachment(stringRicevuta);
						allegato.setEstensione("pdf");
						allegato.setNomeFile(nomeFile);

						Set<String> destinatari = new HashSet<>();
						destinatari.add(evento.getOwner().getEmail());
						
						String mailSedeOperativa = "";
						
						log.info("Evento id attività:"+evento.getAttivita());
						log.info("Evento id richiestaAttività:"+evento.getRichiestaAttivita());
						log.info("EventoModel id attività:"+(eventoModel.getAttivita()!=null?eventoModel.getAttivita().getAttivitaId()+"":""));
						log.info("EventoModel id richiestaAttività:"+(eventoModel.getRichiestaAttivita()!=null && eventoModel.getRichiestaAttivita().getRichiestaAttivitaId()!=null?eventoModel.getRichiestaAttivita().getRichiestaAttivitaId()+"":""));
						
						if (eventoModel.getAttivita()!=null) {
							String token = cacheTokenService.getToken();
							mailSedeOperativa = dmsService.getMailImpresa(token, eventoModel.getAttivita().getAttivitaId());
						} else if (eventoModel.getRichiestaAttivita() != null) {
							String token = cacheTokenService.getToken();
							mailSedeOperativa = dmsService.getMailRegistrazioneImpresa(token,
									eventoModel.getRichiestaAttivita().getRichiestaAttivitaId());
						}
						
						log.info("Mail sede operativa:"+mailSedeOperativa);
						
						if (!mailSedeOperativa.isEmpty()) {
							destinatari.add(mailSedeOperativa);
						}
						
						List<AttachmentDTO> attachment = new ArrayList<>();
						String contentMail = "{" + "\"titolo\": \""
								+ StringEscapeUtils.escapeJava(evento.getDatiGenerali().getTitolo()) + "\",\r\n"
								+ "\"id\": \"" + evento.getEventoId() + "\",\r\n" + "  \"empty\": false\r\n" + "}";
						attachment.add(allegato);
						mailService.sendEmailAttachment("Ricevuta evento finanziato", contentMail, destinatari, null,
								null, "Ricevuta", cacheTokenService.getToken(), attachment);
					}
				}
			} catch (Exception e) {
				log.error("ERRORE NELLA CREAZIONE DELLA PRIMA RICEVUTA PER EVENTO " + evento.getEventoId(),e);
				throw e;
			}

			logEvento.setTipoOperazione(messageRetriever.getMODIFICA());
			logEvento.setNomeUtente(chiamante.getNome() + " " + chiamante.getCognome());

			// calcolo delle differenze - auditing tramite javers
			Javers javers = JaversBuilder.javers()
					.registerValue(BigDecimal.class, new BigDecimalComparatorWithFixedEquals())
					.registerCustomType(Date.class, new DateCustomComparator()).build();
			Set<String> differenze = new HashSet<String>();
			Map<String, String> operationDifEx = new HashMap<String, String>();

			if (!oldEvento.getTipo().equals(evento.getTipo())) {
				log.error("ERRORE DIFF:" + messageRetriever.getPeriodi());
				log.info("################### messageRetriever.getTipologia()" +messageRetriever.getTipologia());
				differenze.add(messageRetriever.getTipologia());
			}
			if (!oldEvento.getStato().getStatoId().equals(evento.getStato().getStatoId())) {
				differenze.add(messageRetriever.getStato());
				log.info("################### messageRetriever.getStato()" +messageRetriever.getStato());
				if (evento.getStato().getStatoId().equals(statoFinale.getStatoId())) {
					evento.setFgValidazionePregressa(true);
					evento.setFgValidazioneUltimoGiorno(true);
				}
			}

			Diff diff = javers.compare(oldEvento.getDatiGenerali(), evento.getDatiGenerali());
			if (diff.hasChanges()) {
				differenze.add(messageRetriever.getDati_Generali());
				log.info("################### messageRetriever.getDati_Generali()" +messageRetriever.getDati_Generali());
				try {
					operationDifEx.put(messageRetriever.getDati_Generali(), diff.getChanges().toString());
				} catch (Exception ee) {
					log.error("ERRORE DIFF:" + messageRetriever.getDati_Generali());
				}
			}

			diff = javers.compare(oldEvento.getTicket(), evento.getTicket());
			if (diff.hasChanges()) {
				differenze.add(messageRetriever.getTicket());
				log.info("################### messageRetriever.getTicket()" +messageRetriever.getTicket());
				try {
					operationDifEx.put(messageRetriever.getTicket(), diff.getChanges().toString());
				} catch (Exception ee) {
					log.error("ERRORE DIFF:" + messageRetriever.getTicket());
				}
			}
			diff = javers.compare(oldEvento.getAccessibilita(), evento.getAccessibilita());
			if (diff.hasChanges()) {
				differenze.add(messageRetriever.getAccessibilita());
				log.info("################### messageRetriever.getAccessibilita()" +messageRetriever.getAccessibilita());
				try {
					operationDifEx.put(messageRetriever.getAccessibilita(), diff.getChanges().toString());
				} catch (Exception ee) {
					log.error("ERRORE DIFF:" + messageRetriever.getAccessibilita());
				}
			}

			if (evento.getLocationSet().stream().anyMatch(location -> location.getLocationId() == null)) {
				differenze.add(messageRetriever.getLocation());
				log.info("################### messageRetriever.getLocation()" +messageRetriever.getLocation());
			} else {

				// non verificare le differenze con gli attrattori: non appartiene al nucleo.
				oldEvento.getLocationSet().stream().forEach(location -> location.setAttrattoriSet(new HashSet<>()));

				diff = javers.compareCollections(oldEvento.getLocationSet(), evento.getLocationSet(), Location.class);
				if (diff.hasChanges()) {
					differenze.add(messageRetriever.getLocation());
					log.info("################### messageRetriever.getLocation()" +messageRetriever.getLocation());
					try {
						operationDifEx.put(messageRetriever.getLocation(), diff.getChanges().toString());
					} catch (Exception ee) {
						log.error("ERRORE DIFF:" + messageRetriever.getLocation());
					}
				}
			}

			diff = javers.compareCollections(oldEvento.getImmagineSet(), evento.getImmagineSet(), Immagine.class);
			if (diff.hasChanges()) {
				differenze.add(messageRetriever.getImmagini());
				log.info("################### messageRetriever.getImmagini()" +messageRetriever.getImmagini());
				try {
					operationDifEx.put(messageRetriever.getImmagini(), diff.getChanges().toString());
				} catch (Exception ee) {
					log.error("ERRORE DIFF:" + messageRetriever.getImmagini());
				}
			}

			diff = javers.compareCollections(oldEvento.getDocumentoSet(), evento.getDocumentoSet(),
					DocumentoEvento.class);
			if (diff.hasChanges()) {
				differenze.add(messageRetriever.getDocumenti());
				log.info("################### messageRetriever.getDocumenti()" +messageRetriever.getDocumenti());
				try {
					operationDifEx.put(messageRetriever.getDocumenti(), diff.getChanges().toString());
				} catch (Exception ee) {
					log.error("ERRORE DIFF:" + messageRetriever.getDocumenti());
				}
			}

			if (evento.getContattoSet().stream().anyMatch(contatto -> contatto.getContattoId() == null)) {
				differenze.add(messageRetriever.getContatti());
				log.info("################### messageRetriever.getContatti()" +messageRetriever.getContatti());
			} else {
				diff = javers.compareCollections(oldEvento.getContattoSet(), evento.getContattoSet(), Contatto.class);
				if (diff.hasChanges()) {
					differenze.add(messageRetriever.getContatti());
					log.info("################### messageRetriever.getContatti()" +messageRetriever.getContatti());
					try {
						operationDifEx.put(messageRetriever.getContatti(), diff.getChanges().toString());
					} catch (Exception ee) {
						log.error("ERRORE DIFF:" + messageRetriever.getContatti());
					}
				}
			}

			if (evento.getRelazioneSet().stream().anyMatch(relazione -> relazione.getRelazioneId() == null)) {
				differenze.add(messageRetriever.getRelazioni());
				log.info("################### messageRetriever.getRelazioni()" +messageRetriever.getRelazioni());
			} else {
				diff = javers.compareCollections(oldEvento.getRelazioneSet(), evento.getRelazioneSet(),
						Relazione.class);
				if (diff.hasChanges()) {
					differenze.add(messageRetriever.getRelazioni());
					log.info("################### messageRetriever.getRelazioni()" +messageRetriever.getRelazioni());
					try {
						operationDifEx.put(messageRetriever.getRelazioni(), diff.getChanges().toString());
					} catch (Exception ee) {
						log.error("ERRORE DIFF:" + messageRetriever.getRelazioni());
					}
				}
			}

			if (evento.getPeriodoSet().stream().anyMatch(periodo -> periodo.getPeriodoId() == null)) {
				differenze.add(messageRetriever.getPeriodi());
				log.info("################### messageRetriever.getPeriodi()" +messageRetriever.getPeriodi());
			} else {
				diff = javers.compareCollections(oldEvento.getPeriodoSet(), evento.getPeriodoSet(), Periodo.class);
				if (diff.hasChanges()) {

					differenze.add(messageRetriever.getPeriodi());
					log.info("################### messageRetriever.getPeriodi()" +messageRetriever.getPeriodi());
					try {
						operationDifEx.put(messageRetriever.getPeriodi(), diff.getChanges().toString());
					} catch (Exception ee) {
						log.error("ERRORE DIFF:" + messageRetriever.getPeriodi());
					}
				}
			}

			if (oldEvento.getBando() != null && evento.getBando() != null) {

				Bando bando = new Bando();
				bando.setBandoId(oldEvento.getBando().getBandoId());
				bando.setTitoloBando(oldEvento.getBando().getTitoloBando());
				diff = javers.compare(bando, evento.getBando());

				if (diff.hasChanges()) {
					differenze.add(messageRetriever.getBandi());
					log.info("################### messageRetriever.getBandi()" +messageRetriever.getBandi());
					try {
						operationDifEx.put(messageRetriever.getBandi(), diff.getChanges().toString());
					} catch (Exception ee) {
						log.error("ERRORE DIFF:" + messageRetriever.getPeriodi());
					}
				}
			} else if ((oldEvento.getBando() != null && evento.getBando() == null)
					|| (oldEvento.getBando() == null && evento.getBando() != null)) {
				differenze.add(messageRetriever.getBandi());
				log.info("################### messageRetriever.getBandi()" +messageRetriever.getBandi());
			}

			StringBuilder operazioniBuilder = new StringBuilder("");
			for (String differenza : differenze) {
				operazioniBuilder.append(differenza).append(", ");
			}

			String operazioni = operazioniBuilder.toString();

			log.info("Modifiche evento " + eventoModel.getEventoId() + ":" + operazioni);

			if (!operazioni.isEmpty()) {

				log.info("Entrato modifica evento " + eventoModel.getEventoId());
				// intercetto la transizione
				Stato statoCorrente = oldEvento.getStato();
				Stato statoDestinazione = evento.getStato();
				// attivitaId recuperato dal db perchè non presente in eventoModel
				Long idEntitaOwner = null;

				if (oldEvento.getAttivita() != null) {
					idEntitaOwner = oldEvento.getAttivita().getAttivitaId().longValue();
				} else if (oldEvento.getRichiestaAttivita() != null) {
					idEntitaOwner = oldEvento.getRichiestaAttivita().getRichiestaAttivitaId().longValue();
				}

				// boolean isOwner = evento.getOwner().getUtenteId().longValue() ==
				// chiamante.getUtenteId().longValue()?true:false;
				boolean isOwner = idEntitaOwner.longValue() == chiamante.getEntitaId().longValue() ? true : false;
				boolean isWorker = oldEvento.getOwnerUltimaModifica().getUtenteId().longValue() == chiamante
						.getUtenteId().longValue() ? true : false;

				// se si tratta di una transizione e non di un aggiornamento allora devo
				// gestirla
				if (!statoDestinazione.getStatoId().contentEquals(statoCorrente.getStatoId())) {
					List<StatoRaggiungibile> possibiliTransizioni = statoCorrente.getConfigurazioneStato()
							.getStatiRaggiungibili();
					StatoRaggiungibile transizioneAttuale = null;
					for (StatoRaggiungibile t : possibiliTransizioni) {
						if (t.getStatoId().equals(statoDestinazione.getStatoId())) {
							boolean reservedToOwner = t.getPermessi().getOrDefault("mustBeOwner", new Boolean(false))
									.booleanValue();
							boolean anyUser = t.getPermessi().getOrDefault("anyUser", new Boolean(false))
									.booleanValue();
							if ((reservedToOwner && isOwner) || (!reservedToOwner && !isOwner) || anyUser) {
//							if ((reservedToOwner && isOwner) || (!reservedToOwner) ){
//							if ((isOwner) || (!reservedToOwner && !isOwner)){
								transizioneAttuale = t;
								break;
							}
						}
					}

					log.info("Modificando stato evento " + eventoModel.getEventoId() + " da "
							+ statoCorrente.getStatoId() + " a " + statoDestinazione.getStatoId());
					if (transizioneAttuale == null) {
						log.error("Transazione non autorizzata");
						throw new Exception("Cambio di stato non presente in configurazione");
					}

					// valorizzo le specifiche della transizione
					flagSpubblicazione = transizioneAttuale.getRevocaPubblicazione().booleanValue();
					flagPubblicazioneGenerica = transizioneAttuale.getPubblicazione().booleanValue();
					flagAddNote = transizioneAttuale.getAddNote().booleanValue();
					emailOwner = transizioneAttuale.getEmailOwner();
					emailRedattori = transizioneAttuale.getEmailOperatori();

					// mi accerto che il chiamante sia effettivamente autorizzato ad eseguire la
					// transizione
					if (!checkTransizioneAutorizzata(isOwner, isWorker, statoDestinazione, statoCorrente,
							transizioneAttuale, chiamante.getTipologia().getPermessiSommati())) {
						throw new Exception();
					}

					// se è la prima validazione, non bisogna mandare email
					if (evento.getStato().getStatoId().equals(statoFinale.getStatoId())
							&& (oldEvento.getFgValidazionePregressa() == null
									|| !oldEvento.getFgValidazionePregressa().booleanValue())) {
						emailRedattori = "";
					}

					// gestisco le note
					if (flagAddNote) {
						logEvento.setNote(evento.getUltimeNote());
					}
//					else {
//						evento.setUltimeNote(null);
//					}
				}

				// determino se gli attributi dell'evento sono stati modificati
				if ((!differenze.contains(messageRetriever.getStato()) && differenze.size() > 0)
						|| (differenze.contains(messageRetriever.getStato()) && differenze.size() > 1)) {
					if (!checkModificaAutorizzata(isOwner, isWorker, statoCorrente,
							chiamante.getTipologia().getPermessiSommati())) {
						log.error("DIFFERENZA SULLE OPERAZIONI:" + operazioni);
						log.error("VERIFICA LE DIFFERENZE OLD/new DALLA SEGUENTE MAPPA: " + operationDifEx.toString());
						throw new Exception();
					}
				}

				operazioni = operazioni.substring(0, operazioni.length() - 2);
				logEvento.setOperazioni(operazioni);
				logEventoSet.add(logEvento);
			}
		} else {
			logEvento.setTipoOperazione(messageRetriever.getINSERIMENTO());
			logEvento.setOperazioni(messageRetriever.getCreazione_Evento());
			logEventoSet = new HashSet<LogEvento>();
			logEventoSet.add(logEvento);
		}

		logEvento.setDescrizioneStato(evento.getStato().getDescrizione());

		logEvento.setEvento(evento);
		evento.setLogSet(logEventoSet);
		evento.getLogSet().stream().forEach(log -> log.setEvento(evento));

		// salvo prima le entità collegate all'evento come many to one perchè la jpa è
		// impostata volutamente per non gestirle in automatico

		if (oldEvento == null) {
			if (evento.getAttivita() != null) {
				attivitaRepository.save(evento.getAttivita());
			}
			if (evento.getRichiestaAttivita() != null) {
				richiestaAttivitaRepository.save(evento.getRichiestaAttivita());
			}

		} else {
			// se l'evento esiste già vado a settare manualmente le entità per evitare
			// sovrascritture
			evento.setOwner(oldEvento.getOwner());
			evento.setOwnerUltimaModifica(chiamante);
			evento.setAttivita(oldEvento.getAttivita());
			evento.setRichiestaAttivita(oldEvento.getRichiestaAttivita());
		}
		// salvo eventuali attrattori non ancora censiti nel DB
		for (Location l : evento.getLocationSet()) {
			for (Attrattore a : l.getAttrattoriSet()) {
				attrattoreRepository.save(a);
			}
		}

		// salvo eventuali bandi e progetti non ancora censiti nel DB
		if (evento.getBando() != null) {
			bandoRepository.save(evento.getBando());

		}
		if (evento.getProgetto() != null && evento.getProgetto().getProgettoId()!=null) {
			progettoRepository.save(evento.getProgetto());
		}
		else
		{
			evento.setProgetto(null);
		
		}

		// rimuovo le immagini e i documenti non più presenti
		if (oldEvento != null) {
			// Calcolo il path in cui ci sono le immagini dell'evento
			String path = repositoryRoot + File.separator + evento.getEventoId().toString() + File.separator
					+ imageFolderName;
			for (Immagine immagineOld : oldEvento.getImmagineSet()) {
				if (!evento.getImmagineSet().stream().anyMatch(
						img -> img.getImmagineId().toString().equals(immagineOld.getImmagineId().toString()))) {
					FileRepository.deleteFile(
							immagineOld.getImmagineId().toString() + "." + immagineOld.getEstensione(), path);
				}
			}
			// Calcolo il path in cui ci sono i documenti dell'evento
			path = repositoryRoot + File.separator + evento.getEventoId().toString() + File.separator
					+ documentFolderName;
			for (DocumentoEvento documentoOld : oldEvento.getDocumentoSet()) {
				if (!evento.getDocumentoSet().stream().anyMatch(
						doc -> doc.getDocumentoId().toString().equals(documentoOld.getDocumentoId().toString()))) {
					FileRepository.deleteFile(
							documentoOld.getDocumentoId().toString() + "." + documentoOld.getEstensione(), path);
				}
			}
		}

		boolean eventoPubblicatoStored = evento.getFgPubblicato();
		if (flagSpubblicazione) {
			evento.setFgPubblicato(false);
		}
		// salvo (finalmente) l'evento nel repository
		Evento eventoSalvato = eventoRepository.save(evento);

		String note = evento.getUltimeNote() != null ? evento.getUltimeNote() : "";
		String content = "{\r\n" + "  \"titolo\": \""
				+ StringEscapeUtils.escapeJava(evento.getDatiGenerali().getTitolo()) + "\",\r\n" + "  \"id\": \""
				+ evento.getEventoId() + "\",\r\n" + "  \"note\": \"" + StringEscapeUtils.escapeJava(note) + "\",\r\n"
				+ "  \"empty\": false\r\n" + "}";

		// eventuale spubblicazione
		Set<Pubblicazione> spubblicazioniJMS = new HashSet<Pubblicazione>();
		if (flagSpubblicazione) {
			Set<Pubblicazione> pubblicazioni = pubblicazioneRepository
					.findByGenericMetadataNotNullAndEvento_EventoIdAndRedazione_RedazioneIdNotAndRedazione_AutoSpubblicazioneTrue(
							evento.getEventoId(), "GENERAL");
			if (!pubblicazioni.isEmpty()) {
				for (Pubblicazione pubblicazione : pubblicazioni) {
					// JMS
					if (pubblicazione.getRedazione().getAttivazioneJMS() != null
							&& pubblicazione.getRedazione().getAttivazioneJMS().booleanValue()) {
						spubblicazioniJMS.add(pubblicazione);
					}
					///
					pubblicazione.setGenericMetadata(null);
				
				}
				pubblicazioneRepository.saveAll(pubblicazioni);
			}

		}

		try {

			Set<String> tipologieRedazionali = new HashSet<String>();
			tipologieRedazionali.add("RED-VIP");
			tipologieRedazionali.add("REDCAP-VIP");

			List<String> mailOwnerList = new ArrayList<>();
			mailOwnerList.add(eventoSalvato.getOwner().getEmail());

			String mailSedeOperativa = "";

			if (eventoSalvato.getAttivita() != null) {
				String token = cacheTokenService.getToken();
				mailSedeOperativa = dmsService.getMailImpresa(token, eventoSalvato.getAttivita().getAttivitaId());
			} else if (eventoSalvato.getRichiestaAttivita() != null) {
				String token = cacheTokenService.getToken();
				mailSedeOperativa = dmsService.getMailRegistrazioneImpresa(token,
						eventoSalvato.getRichiestaAttivita().getRichiestaAttivitaId());
			}

			if (!mailSedeOperativa.isEmpty()) {
				mailOwnerList.add(mailSedeOperativa);
			}

			if (stato.equals("MODIFICA")) {
				log.info("verificando invio mail stato " + stato + "...");

				spubblicazioniJMS.clear();
				
			
				Set<Pubblicazione> pubblicazioni = pubblicazioneRepository
						.findByEvento_EventoIdAndRedazione_RedazioneIdNot(
								evento.getEventoId(), "GENERAL");
				
				for(Pubblicazione pubblicazione : pubblicazioni) 
				{
					pubblicazione.setStatoPubblicazione(StatoPubblicazione.BOZZA);
//					pubblicazione.setGenericMetadata(null);
//					pubblicazione.setGenericMetadataWip(null);
					
					
					
					spubblicazioniJMS.add(pubblicazioneRepository.save(pubblicazione));
					
					
					}
				
				if (!eventoPubblicatoStored) {
					spubblicazioniJMS.clear();
				}
//				evento.setFgPubblicato(false);
				Set<PubblicazioneModel> spubbDTO= new HashSet<>();
				
				spubbDTO =  pubblicazioneMapper.entityToDtoSet(spubblicazioniJMS);
				boolean isOwner = chiamante.getUtenteId().longValue() == evento.getOwner().getUtenteId().longValue();
				if (isOwner) {
					
					
				
					log.info("verificando invio mail stato 'in modifica' dell'owner...");
					asyncService.invioMailConseguenteJMSSpubblicazioneTotaleModificaOwnerUseModel(spubbDTO, content,
							emailOwner, emailRedattori, chiamante, eventoSalvato.getOwner().getEmail(),
							tipologieRedazionali, flagSpubblicazione);
				} else {
					log.info("verificando invio mail stato 'in modifica' del validatore...");
					asyncService.invioMailConseguenteJMSSpubblicazioneTotaleUseModel(spubbDTO, content, emailOwner,
							emailRedattori, chiamante, mailOwnerList, tipologieRedazionali, flagSpubblicazione);
				}
				
				for(Pubblicazione pubblicazione : pubblicazioni) 
				{
					pubblicazione.setStatoPubblicazione(StatoPubblicazione.SOSPESO);
					pubblicazioneRepository.save(pubblicazione);
				}
			
			} else {

				log.info("verificando invio mail stato " + stato + "...");

				asyncService.invioMailConseguenteJMSSpubblicazioneTotale(spubblicazioniJMS, content, emailOwner,
						emailRedattori, chiamante, mailOwnerList, tipologieRedazionali, flagSpubblicazione);
			}

		} catch (Exception e) {
			log.error("ERRORE JMS: " + e.getMessage());
		}

		// gestisco le relazioni opposte a quelle correnti per mantenere la coerenza
		// della base dati
		relazioneRepository.deleteByEventoRelazionatoId(eventoSalvato.getEventoId());
		for (Relazione relazione : eventoSalvato.getRelazioneSet()) {

			Relazione relazioneOpposta = new Relazione();

			relazioneOpposta.setEvento(eventoRepository.findById(relazione.getEventoRelazionatoId()).get());
			relazioneOpposta.setEventoRelazionatoId(eventoSalvato.getEventoId());
			relazioneOpposta.setTipoEventoAssociato(eventoSalvato.getTipo());
			relazioneOpposta.setRedazioneId(relazione.getRedazioneId());

			if (relazione.getTipoRelazione().equals("CONTENUTO")) {
				relazioneOpposta.setTipoRelazione("CONTIENE");
			} else {
				relazioneOpposta.setTipoRelazione("CONTENUTO");
			}

			relazioneRepository.save(relazioneOpposta);

		}

		if (flagPubblicazioneGenerica) {
			addMetadata(eventoSalvato.getEventoId(), dettaglioUtenteMapper.entityToDto(chiamante), null, "GENERAL",
					null, null, "PUBBLICATO", null);
		}
		
		 if(stato!=null) {
		
		if(stato.equalsIgnoreCase("RIVALIDATO") || stato.equalsIgnoreCase("VALIDATO") )
		{		
		Set<Pubblicazione> pubblicazioni = pubblicazioneRepository
				.findByEvento_EventoIdAndRedazione_RedazioneIdNot(
						evento.getEventoId(), "GENERAL");
		if (!pubblicazioni.isEmpty()) {
			for (Pubblicazione pubblicazione : pubblicazioni) {
				if(pubblicazione.getStatoPubblicazione().equals(StatoPubblicazione.SOSPESO)) {
				pubblicazione.setStatoPubblicazione(StatoPubblicazione.BOZZA);
				// JMS
				if (pubblicazione.getRedazione().getAttivazioneJMS() != null
						&& pubblicazione.getRedazione().getAttivazioneJMS().booleanValue()) {
					spubblicazioniJMS.add(pubblicazione);
				}
				///
				pubblicazione.setGenericMetadata(null);
				// evento.setFgPubblicato(false);
				}
			}
			pubblicazioneRepository.saveAll(pubblicazioni);
		}
		}
		 }
		

		return eventoSalvato.getEventoId();
	}

	public boolean checkTransizioneAutorizzata(boolean isOwner, boolean isWorker, Stato statoDestinazione,
			Stato statoCorrente, StatoRaggiungibile transizioneAttuale, Map<String, Boolean> permessiChiamante) {

		// verifico se se si tratta di un passaggio di stato
		if (!statoDestinazione.getStatoId().contentEquals(statoCorrente.getStatoId())) {
			Map<String, Boolean> permessiNecessari = transizioneAttuale.getPermessi();
			if (permessiNecessari.getOrDefault("autorizzato", new Boolean(false)).booleanValue() && !permessiChiamante
					.getOrDefault("cambioStato" + statoDestinazione.getStatoId(), new Boolean(false)).booleanValue()) {
				return false;
			}
			if (permessiNecessari.getOrDefault("mustBeOwner", new Boolean(false)).booleanValue() && !isOwner) {
				return false;
			}
			if (permessiNecessari.getOrDefault("mustNotBeOwner", new Boolean(false)).booleanValue() && isOwner) {
				return false;
			}
			if (permessiNecessari.getOrDefault("mustBeWorker", new Boolean(false)).booleanValue() && !isWorker) {
				return false;
			}
			if (permessiNecessari.getOrDefault("mustNotBeWorker", new Boolean(false)).booleanValue() && isWorker) {
				return false;
			}
		}

		return true;
	}

	public boolean checkModificaAutorizzata(boolean isOwner, boolean isWorker, Stato statoCorrente,
			Map<String, Boolean> permessiChiamante) {
		if (!(permessiChiamante.getOrDefault("writableNucleo" + statoCorrente.getStatoId(), new Boolean(false)))
				&& !isOwner) {
			return false;
		}
		if (isOwner && !statoCorrente.getConfigurazioneStato().getIsEditableByOwner().booleanValue()) {
			return false;
		}
		if (statoCorrente.getConfigurazioneStato().getIsWorkerExclusive().booleanValue() && !isWorker) {
			return false;
		}
		return true;
	}

	public EventoModel getEvento(Long id, Long idUtente) throws Exception, NoSuchElementException {
		// Mi accerto di avere il lock per questo evento e eventualmente lo rinnovo
		// lockService.lockRisorsa(id, idUtente, Evento.class);
		Evento evento = null;
		try {
			evento = eventoRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			return null;
		}

		// pulizia file - spenta in quanto non serve in relazione alle attuali
		// disposizioni del cliente
		/*
		 * if(!evento.getStato().getStatoId().equals("BOZZA") &&
		 * !evento.getStato().getStatoId().equals("MODIFICA") &&
		 * !evento.getStato().getStatoId().equals("VALIDAZIONE")) { String path =
		 * repositoryRoot + File.separator + evento.getEventoId().toString() +
		 * File.separator + imageFolderName; List<Immagine> listaImmagini = new
		 * ArrayList<Immagine>(); for(Immagine immagine : evento.getImmagineSet() ) { if
		 * (immagine.getDidascalia() == null || immagine.getDidascalia().isEmpty()) {
		 * FileRepository.deleteFile(immagine.getImmagineId().toString() + "." +
		 * immagine.getEstensione(), path); listaImmagini.add(immagine); } } path =
		 * repositoryRoot + File.separator + evento.getEventoId().toString() +
		 * File.separator + documentFolderName; List<DocumentoEvento> listaDocumenti =
		 * new ArrayList<DocumentoEvento>(); for(DocumentoEvento documento :
		 * evento.getDocumentoSet() ) { if (documento.getTitolo() == null ||
		 * documento.getTitolo().isEmpty()) {
		 * FileRepository.deleteFile(documento.getDocumentoId().toString() + "." +
		 * documento.getEstensione(), path); listaDocumenti.add(documento); } }
		 * for(Immagine immagine : listaImmagini ) {
		 * evento.getImmagineSet().remove(immagine); } for(DocumentoEvento documento :
		 * listaDocumenti ) { evento.getDocumentoSet().remove(documento); } evento =
		 * eventoRepository.save(evento); }
		 */
		EventoModel eventoModel = eventoMapper.entityToDto(evento, eventoRepository);

		// verifica puntuale su idUtente per visualizzazione emailOldOwner
		if (!pugliaEventsOwnerList.contains(idUtente.intValue())) {
			eventoModel.setEmailOldOwner("");
		}

		return eventoModel;
	}

	public EventoModel getEvento(Long id) throws Exception, NoSuchElementException {

		Evento evento = null;
		try {
			evento = eventoRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			return null;
		}

		EventoModel eventoModel = eventoMapper.entityToDto(evento, eventoRepository);

		return eventoModel;
	}

	@Transactional
	public WrapperFilterResponse<?> getListaPaginata(WrapperFilterRequest<EventoFilter> request, Long idUtente) {
		
		DettaglioUtente chiamante = dettaglioUtenteRepository.findById(idUtente).get();
		if (chiamante.getTipologia().getPermessiSommati().getOrDefault("filtroEventoSeeAll", false) != true
				|| "PROM".equals(request.getFilter().getServiceCode())) {
//			request.getFilter().setIdUtenteIns(idUtente);
		}

		if ("RED".equalsIgnoreCase(request.getFilter().getServiceCode())) {
			Redazione redazioneAttuale = chiamante.getTipologia().getRedazioniSet().stream()
					.filter(x -> x.getRedazioneId().equalsIgnoreCase(request.getFilter().getRedazioneAttuale()))
					.findAny().orElse(null);
			if (redazioneAttuale != null) {
				request.getFilter().setRedazioneAttuale(redazioneAttuale.getNome());
			}
		}

		if ("FIN".equalsIgnoreCase(request.getFilter().getServiceCode())) {
			request.getFilter().setProfilo(chiamante.getTipologia().getTipologiaId());
		}

		return eventoRepository.findByPageFilter(request, EventoModelList.class, new EventoFilterManager());
	}

	public List<SmartModelList> getEventoPerComune() {
		return eventoRepository.findEventoGroupByComuneMoreThan9();
	}

	public Set<StatoModel> getStatiEvento() throws Exception {
		List<Stato> stati = statoRepository.findAll();
		Set<Stato> targetSet = new HashSet<>(stati);
		Set<StatoModel> statiModel = statoMapper.entityToDtoSet(targetSet);
		return statiModel;
	}

	public void lockEvento(Long id, Long idUtente) throws LockedResourceException, Exception {
		lockService.lockRisorsa(id, idUtente, Evento.class);
	}

	public byte[] getFile(Long id, String fileName, String fileType, Long idPubblicazione) throws IOException {
		String path = null;
		if (idPubblicazione == null) {
			String folderName = null;
			if (fileType.contentEquals("immagine")) {
				folderName = imageFolderName;
			} else {
				folderName = documentFolderName;
			}
			path = repositoryRoot + File.separator + id.toString() + File.separator + folderName + File.separator
					+ fileName;
		} else {
			path = repositoryRoot + File.separator + id.toString() + File.separator + publicationFolderName
					+ File.separator + idPubblicazione.toString() + File.separator + fileName;
		}
		return FileRepository.getFile(path);
	}

	public Object saveFile(Long idEvento, MultipartFile file, Long idUtente, Long idPubblicazione, Integer resizeWidth,
			Integer resizeHeight, Long ordine,String tipologia) throws LockedResourceException, Exception {

		String estensione = FilenameUtils.getExtension(file.getOriginalFilename().toLowerCase());
		long dimensione = file.getSize();

		String convertedString = Normalizer.normalize(file.getOriginalFilename(), Normalizer.Form.NFD)
				.replaceAll("[^\\p{ASCII}]", "");

		String nomeOriginale = convertedString.replaceAll(" ", "_").toLowerCase();

		byte[] b = file.getBytes();
		if (resizeWidth != null && resizeHeight != null) {
			b = CommonUtility.scale(b, resizeWidth.intValue(), resizeHeight.intValue(), estensione);
		}
		if (idPubblicazione == null) {
			Evento evento = eventoRepository.findById(idEvento).get();
			String folderName = null;
			Long idFile = null;
			Immagine immagineSalvata = null;
			
			DocumentoEvento documentoSalvato = null;
			if(tipologia.equalsIgnoreCase("immagine"))
			{
			
				ImmagineModel immagineModel = new ImmagineModel();
				immagineModel.setNomeOriginale(nomeOriginale);
				immagineModel.setEstensione(estensione);
				immagineModel.setDimensione(dimensione);
				immagineModel.setOrdine(ordine);
				Immagine immagine = immagineMapper.dtoToEntity(immagineModel);
				immagine.setEvento(evento);
				immagineSalvata = immagineRepository.save(immagine);
				idFile = immagineSalvata.getImmagineId();
				evento.getImmagineSet().add(immagineSalvata);
				folderName = imageFolderName;
				
			} else {
				DocumentoEventoModel documentoModel = new DocumentoEventoModel();
				documentoModel.setNomeOriginale(nomeOriginale);
				documentoModel.setEstensione(estensione);
				documentoModel.setDimensione(dimensione);
				documentoModel.setOrdine(ordine);
				DocumentoEvento documento = documentoMapper.dtoToEntity(documentoModel);
				documento.setEvento(evento);
				documentoSalvato = documentoRepository.save(documento);
				idFile = documentoSalvato.getDocumentoId();
				evento.getDocumentoSet().add(documentoSalvato);
				folderName = documentFolderName;
			}
			String path = repositoryRoot + File.separator + idEvento.toString() + File.separator + folderName;
			EventoModel eventoModel = eventoMapper.entityToDto(evento, eventoRepository);
			saveEvento(eventoModel, idUtente, null);
			FileRepository.saveFile(b, idFile + "." + estensione, path);
			if (immagineSalvata != null) {
				return immagineMapper.entityToDto(immagineSalvata);
			} else {
				return documentoMapper.entityToDto(documentoSalvato);
			}
		} else {
			PubblicazioneAllegato allegato = new PubblicazioneAllegato();
			Pubblicazione pubblicazione = pubblicazioneRepository.findById(idPubblicazione).get();
			allegato.setPubblicazione(pubblicazione);
			allegato.setDimensione(dimensione);
			allegato.setNomeOriginale(nomeOriginale);
			allegato.setEstensione(estensione);
			PubblicazioneAllegato allegatoSalvato = pubblicazioneAllegatoRepository.save(allegato);
			String path = repositoryRoot + File.separator + idEvento.toString() + File.separator + publicationFolderName
					+ File.separator + idPubblicazione.toString();
			FileRepository.saveFile(b, allegatoSalvato.getAllegatoId() + "." + estensione, path);
			
			ObjectMapper mapper = new ObjectMapper();
			PubblicazioneAllegatoModel allegatoSalvatoDTO = pubblicazioneAllegatoMapper.entityToDto(allegatoSalvato);
			
			
				
					JsonNode gmWip = pubblicazione.getGenericMetadataWip();

			if(tipologia.equalsIgnoreCase("immagine"))
			{


				JsonNode img = gmWip.get("immagineSetAggiunto");
				VIPSchedaImmagineModel vipImmagine = new VIPSchedaImmagineModel();
				vipImmagine.setImmagineId(allegatoSalvato.getAllegatoId());
				vipImmagine.setNomeOriginale(allegatoSalvato.getNomeOriginale());
				
				
				vipImmagine.setEstensione(allegatoSalvato.getEstensione());
				vipImmagine.setDimensione(allegatoSalvato.getDimensione());
				vipImmagine.setOrdine(ordine);
				
				vipImmagine.setDaPubblicare(true);
				
				JSONObject jsObj = new JSONObject(vipImmagine);

				JsonNode newImage = (mapper.readValue(jsObj.toString() , JsonNode.class));


				if(img==null)
				{
					img = mapper.createArrayNode();
					((ObjectNode)gmWip).putObject("immagineSetAggiunto");
				}

				((ArrayNode)img).add(newImage);



				((ObjectNode)gmWip).set("immagineSetAggiunto", img);

			}else {

				VIPSchedaDocumentoModel vipSchedaDocumentoModel = new VIPSchedaDocumentoModel();
				vipSchedaDocumentoModel= new VIPSchedaDocumentoModel(); 
				vipSchedaDocumentoModel.setDimensione		(allegatoSalvato.getDimensione());
				vipSchedaDocumentoModel.setDocumentoId		(allegatoSalvato.getAllegatoId());
				vipSchedaDocumentoModel.setEstensione		(allegatoSalvato.getEstensione());
				vipSchedaDocumentoModel.setNomeOriginale	(allegatoSalvato.getNomeOriginale());
				vipSchedaDocumentoModel.setTitoloMulti		(null);
				vipSchedaDocumentoModel.setDaPubblicare		(true);
				vipSchedaDocumentoModel.setOrdineNumerico	(ordine);


				JsonNode img = gmWip.get("documentoSetAggiunto");

				JSONObject jsObj = new JSONObject(vipSchedaDocumentoModel);

				JsonNode newImage = (mapper.readValue(jsObj.toString() , JsonNode.class));


				if(img==null)
				{
					img = mapper.createArrayNode();
					((ObjectNode)gmWip).putObject("documentoSetAggiunto");
				}

				((ArrayNode)img).add(newImage);



				((ObjectNode)gmWip).set("documentoSetAggiunto", img);
			}

			pubblicazione.setGenericMetadataWip(gmWip);
			pubblicazioneRepository.save(pubblicazione);
			return allegatoSalvatoDTO;
		}
	}

	public Set<EventoTitoloModel> getTitoliEvento(Long idEventoCorrente, String tipoEvento, String query,
			Long idUtente) {

		Set<EventoTitoloModel> eventoTitoloModelSet = new HashSet<EventoTitoloModel>();
		Set<EventoTitoloProjection> eventoProjection = new HashSet<EventoTitoloProjection>();

		DettaglioUtente chiamante = dettaglioUtenteRepository.findById(idUtente).get();

		Evento evento = eventoRepository.findById(idEventoCorrente).get();

//		if (chiamante.getTipologia().getPermessiSommati().getOrDefault("filtroEventoSeeAll", false) != true) {
//			eventoProjection = eventoRepository.findByTipoAndOwner_UtenteIdAndDatiGenerali_TitoloIgnoreCaseLikeAndStatoIsNotNull(tipoEvento,idUtente,"%"+query+"%");
//		}else {
//			if (tipoEvento.equals("RASSEGNA")) {
//				eventoProjection = eventoRepository.findByTipoAndDatiGenerali_TitoloIgnoreCaseLikeAndStatoIsNotNull(tipoEvento,"%"+query+"%");
//			}else {
//				eventoProjection = eventoRepository.findByDatiGenerali_TitoloIgnoreCaseLikeAndStatoIsNotNull("%"+query+"%");
//			}
//		}

		List<String> tipi = new ArrayList<String>();

		if (chiamante.getTipologia().getTipologiaId().contains("VAL")
				|| chiamante.getTipologia().getTipologiaId().contains("PROM")) {

			if (tipoEvento.equals("RASSEGNA")) {
				tipi.add(tipoEvento);
			} else {
				tipi.add("EVENTO");
			}

			if (evento.getAttivita() != null) {
				eventoProjection = eventoRepository
						.findByDatiGenerali_TitoloIgnoreCaseLikeAndTipoInAndAttivita_AttivitaIdAndStatoIsNotNull(
								"%" + query + "%", tipi, evento.getAttivita().getAttivitaId());
			} else if (evento.getRichiestaAttivita() != null) {
				eventoProjection = eventoRepository
						.findByDatiGenerali_TitoloIgnoreCaseLikeAndTipoInAndRichiestaAttivita_RichiestaAttivitaIdAndStatoIsNotNull(
								"%" + query + "%", tipi, evento.getRichiestaAttivita().getRichiestaAttivitaId());
			}

		} else {

			// TODO Gestione di rezioni diverse da VIP

			if (evento.getTipo().equals("EVENTO")) {// Se sono un evento cerco solo RASSEGNE
				tipi.add("RASSEGNA");
			} else {// Se sono una rassegna cerco tutto
				tipi.add(evento.getTipo());
				tipi.add("EVENTO");
			}

			List<String> statiValidazione = new ArrayList<>();
			statiValidazione.add("VALIDATO");
			statiValidazione.add("RIVALIDATO");

			Set<PubblicazioneProjection> tmpList = new HashSet<PubblicazioneProjection>();
			tmpList = pubblicazioneRepository
					.findByRedazione_RedazioneIdAndEvento_DatiGenerali_TitoloIgnoreCaseLikeAndEvento_TipoInAndEvento_Stato_StatoIdIn(
							"VIP", "%" + query + "%", tipi, statiValidazione);
			if (tmpList != null) {

				for (PubblicazioneProjection tmp : tmpList) {
					eventoProjection.add(tmp.getEvento());
				}
			}
		}

		// Elaborazione dato di ritorno
		for (EventoTitoloProjection eP : eventoProjection) {
			Long id = eP.getId();
			if (idEventoCorrente.longValue() != id.longValue()) {
				String titolo = eP.getTitolo();
				Timestamp dataIns = eP.getDataIns();
				String tipo = eP.getTipo();

				// Dati elaborati
				String comune = null;
				String periodo = null;
				Date dataMin = null;
				Date dataMax = null;

				if (eP.getLocationSet() != null) {
					Location tmpLocation = eP.getLocationSet().stream().findFirst().orElse(new Location());
					if (StringUtils.isNotBlank(tmpLocation.getComune())) {
						comune = tmpLocation.getComune();
					} else {
						comune = tmpLocation.getComuneEstero();
					}

				}

				if (eP.getPeriodoSet() != null) {

					for (Periodo period : eP.getPeriodoSet()) {

						if (period.getDataDa() != null && dataMin == null) {
							dataMin = period.getDataDa();
						} else if (period.getDataDa() != null && period.getDataDa().compareTo(dataMin) < 0) {
							dataMin = period.getDataDa();
						}

						if (period.getDataA() != null && dataMax == null) {
							dataMax = period.getDataA();
						} else if (period.getDataA() != null && period.getDataA().compareTo(dataMax) > 0) {
							dataMax = period.getDataA();
						}
					}

					SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");
					if (dataMin != null && dataMax != null) {
						if (dataMin.compareTo(dataMax) == 0) {
							periodo = format.format(dataMin);
						} else if (dataMin.compareTo(dataMax) != 0) {
							periodo = "dal " + format.format(dataMin) + " al " + format.format(dataMax);
						}
					} else {
						periodo = "n/d";
					}
				}

				EventoTitoloModel eventoTitoloModel = new EventoTitoloModel();

				eventoTitoloModel.setPeriodo(periodo);
				eventoTitoloModel.setEventoId(id);
				eventoTitoloModel.setTitolo(titolo);
				eventoTitoloModel.setDataIns(dataIns);
				eventoTitoloModel.setTipo(tipo);
				eventoTitoloModel.setComune(comune);

				eventoTitoloModelSet.add(eventoTitoloModel);
			}
		}
		return eventoTitoloModelSet;
	}

	public boolean cambiaAttivita(Attivita attivita, Long richiestaAttivitaId, String jwt) {
		Attivita attivitaSalvata = attivitaRepository.save(attivita);
		Iterable<Evento> eventoSet = eventoRepository.findByRichiestaAttivita_RichiestaAttivitaId(richiestaAttivitaId);
		List<Long> eventoIdList = new ArrayList<Long>();
		for (Evento evento : eventoSet) {
			evento.setAttivita(attivitaSalvata);
			evento.setRichiestaAttivita(null);
			eventoIdList.add(evento.getEventoId());
		}
		Set<Pubblicazione> pubblicazioneList = pubblicazioneRepository.findByEvento_EventoIdIn(eventoIdList);
		for (Pubblicazione pubblicazione : pubblicazioneList) {
			pubblicazione.getEventoModel().setAttivita(attivitaMapper.entityToDto(attivitaSalvata));
			pubblicazione.getEventoModel().setRichiestaAttivita(null);
		}

		List<Pubblicazione> pubblicazioneListSalvate = pubblicazioneRepository.saveAll(pubblicazioneList);
		eventoRepository.saveAll(eventoSet);

		for (Pubblicazione pubblicazione : pubblicazioneListSalvate) {
			if (pubblicazione.getRedazione().getAttivazioneJMS() != null
					&& pubblicazione.getRedazione().getAttivazioneJMS().booleanValue()
					&& pubblicazione.getGenericMetadata() != null) {
				log.info("PUBBLICAZIONE SU JMS NECESSARIA");

				// JMS e invio mail
				try {
					asyncService.invioMailConseguenteJMSPubblicazione(null, pubblicazione, null, null, null);
				} catch (Exception e) {
					log.error("ERRORE JMS: " + e.getMessage());
				}
				///
			}
		}
		return true;
	}

	public void duplicaEvento(Long idEvento, Long idUtente) throws Exception {
		EventoModel eventoModel = getEvento(idEvento, idUtente);
		eventoModel.setEventoId(null);
		if (eventoModel.getAccessibilita() != null) {
			eventoModel.getAccessibilita().setEventoId(null);
		}
		if (eventoModel.getDatiGenerali() != null) {
			eventoModel.getDatiGenerali().setEventoId(null);
		}
		if (eventoModel.getTicket() != null) {
			eventoModel.getTicket().setEventoId(null);
		}
		if (eventoModel.getLocationSet() != null) {
			eventoModel.getLocationSet().forEach(location -> location.setLocationId(null));
		}
//		if (eventoModel.getTelefonoSet()!=null) {
//			eventoModel.getTelefonoSet().forEach(telefono -> telefono.setContattoId(null));
//		}
//		if (eventoModel.getEmailSet()!=null) {
//			eventoModel.getEmailSet().forEach(email -> email.setContattoId(null));
//		}
//		if (eventoModel.getSitoSet()!=null) {
//			eventoModel.getSitoSet().forEach(sito -> sito.setContattoId(null));
//		}
		if (eventoModel.getRelazioneSet() != null) {
			eventoModel.getRelazioneSet().forEach(relazione -> relazione.setRelazioneId(null));
		}
		if (eventoModel.getLogSet() != null) {
			eventoModel.getLogSet().forEach(log -> log.setLogId(null));
		}
		if (eventoModel.getPeriodoSet() != null) {
			eventoModel.getPeriodoSet().forEach(periodo -> periodo.setPeriodoId(null));
		}
		if (eventoModel.getLinkSet() != null) {
			eventoModel.getLinkSet().forEach(link -> link.setLinkId(null));
		}
		if (eventoModel.getImmagineSet() != null) {
			eventoModel.getImmagineSet().forEach(immagine -> {
				immagine.setPathToCopy(repositoryRoot + File.separator + idEvento.toString() + File.separator
						+ imageFolderName + File.separator + immagine.getImmagineId() + "." + immagine.getEstensione());
				immagine.setImmagineId(null);
			});
		}
		if (eventoModel.getDocumentoSet() != null) {
			eventoModel.getDocumentoSet().forEach(documento -> {
				documento.setPathToCopy(
						repositoryRoot + File.separator + idEvento.toString() + File.separator + documentFolderName
								+ File.separator + documento.getDocumentoId() + "." + documento.getEstensione());
				documento.setDocumentoId(null);
			});
		}
		eventoModel.setOwnerId(idUtente);
		String titoloDaCopiare = eventoModel.getDatiGenerali().getTitoloMulti().get("IT");
		if (!titoloDaCopiare.endsWith("(copia)")) {
			eventoModel.getDatiGenerali().getTitoloMulti().replace("IT", titoloDaCopiare + " (copia)");
		}

		eventoModel.getEmailSet().clear();
		eventoModel.getTelefonoSet().clear();
		eventoModel.getSitoSet().clear();
		eventoModel.getPeriodoSet().clear();
		eventoModel.getRelazioneSet().clear();
		Long idEventoDuplicato = saveEvento(eventoModel, idUtente, "BOZZA");
		EventoModel eventoDuplicatoModel = getEvento(idEventoDuplicato, idUtente);
		for (ImmagineModel img : eventoDuplicatoModel.getImmagineSet()) {
			byte[] b = FileRepository.getFile(img.getPathToCopy());
			String newPath = repositoryRoot + File.separator + idEventoDuplicato.toString() + File.separator
					+ imageFolderName;
			FileRepository.saveFile(b, img.getImmagineId() + "." + img.getEstensione(), newPath);
		}
		for (DocumentoEventoModel doc : eventoDuplicatoModel.getDocumentoSet()) {
			byte[] b = FileRepository.getFile(doc.getPathToCopy());
			String newPath = repositoryRoot + File.separator + idEventoDuplicato.toString() + File.separator
					+ documentFolderName;
			FileRepository.saveFile(b, doc.getDocumentoId() + "." + doc.getEstensione(), newPath);
		}
	}

	public void duplicaEvento(Long idEvento, Long idUtente, AttivitaModel attivita) throws Exception {

		DettaglioUtente chiamante = dettaglioUtenteRepository.findById(idUtente).get();

		EventoModel eventoModel = getEvento(idEvento, idUtente);
		eventoModel.setEventoId(null);
		if (eventoModel.getAccessibilita() != null) {
			eventoModel.getAccessibilita().setEventoId(null);
		}
		if (eventoModel.getDatiGenerali() != null) {
			eventoModel.getDatiGenerali().setEventoId(null);
		}
		if (eventoModel.getTicket() != null) {
			eventoModel.getTicket().setEventoId(null);
		}
		if (eventoModel.getLocationSet() != null) {
			eventoModel.getLocationSet().forEach(location -> location.setLocationId(null));
		}
//		if (eventoModel.getTelefonoSet()!=null) {
//			eventoModel.getTelefonoSet().forEach(telefono -> telefono.setContattoId(null));
//		}
//		if (eventoModel.getEmailSet()!=null) {
//			eventoModel.getEmailSet().forEach(email -> email.setContattoId(null));
//		}
//		if (eventoModel.getSitoSet()!=null) {
//			eventoModel.getSitoSet().forEach(sito -> sito.setContattoId(null));
//		}
		if (eventoModel.getRelazioneSet() != null) {
			eventoModel.getRelazioneSet().forEach(relazione -> relazione.setRelazioneId(null));
		}
		if (eventoModel.getLogSet() != null) {
			eventoModel.getLogSet().forEach(log -> log.setLogId(null));
		}
		if (eventoModel.getPeriodoSet() != null) {
			eventoModel.getPeriodoSet().forEach(periodo -> periodo.setPeriodoId(null));
		}
		if (eventoModel.getLinkSet() != null) {
			eventoModel.getLinkSet().forEach(link -> link.setLinkId(null));
		}
		if (eventoModel.getImmagineSet() != null) {
			eventoModel.getImmagineSet().forEach(immagine -> {
				immagine.setPathToCopy(repositoryRoot + File.separator + idEvento.toString() + File.separator
						+ imageFolderName + File.separator + immagine.getImmagineId() + "." + immagine.getEstensione());
				immagine.setImmagineId(null);
			});
		}
		if (eventoModel.getDocumentoSet() != null) {
			eventoModel.getDocumentoSet().forEach(documento -> {
				documento.setPathToCopy(
						repositoryRoot + File.separator + idEvento.toString() + File.separator + documentFolderName
								+ File.separator + documento.getDocumentoId() + "." + documento.getEstensione());
				documento.setDocumentoId(null);
			});
		}
		eventoModel.setOwnerId(idUtente);
		String titoloDaCopiare = eventoModel.getDatiGenerali().getTitoloMulti().get("IT");
		if (!titoloDaCopiare.endsWith("(copia)")) {
			eventoModel.getDatiGenerali().getTitoloMulti().replace("IT", titoloDaCopiare + " (copia)");
		}
		// se duplichi l'evento ma l'attivita del nuovo evento sarebbe diversa da quella
		// esistente, non portarti dietro le info di bando e progetto.
		if ((eventoModel.getAttivita() != null
				&& !eventoModel.getAttivita().getAttivitaId().equals(attivita.getAttivitaId()))
				|| (eventoModel.getRichiestaAttivita() != null && !eventoModel.getRichiestaAttivita()
						.getRichiestaAttivitaId().equals(attivita.getAttivitaId()))) {
			eventoModel.setBando(null);
			eventoModel.setProgetto(null);
		}

		eventoModel.setUltimeNote(null);

		eventoModel.setFgValidazionePregressa(false);

		eventoModel.setAttivita(attivita);
		eventoModel.getEmailSet().clear();
		eventoModel.getTelefonoSet().clear();
		eventoModel.getSitoSet().clear();
		eventoModel.getPeriodoSet().clear();
		eventoModel.getRelazioneSet().clear();
		Long idEventoDuplicato = saveEvento(eventoModel, idUtente, "BOZZA");
		EventoModel eventoDuplicatoModel = getEvento(idEventoDuplicato, idUtente);
		for (ImmagineModel img : eventoDuplicatoModel.getImmagineSet()) {
			byte[] b = FileRepository.getFile(img.getPathToCopy());
			String newPath = repositoryRoot + File.separator + idEventoDuplicato.toString() + File.separator
					+ imageFolderName;
			FileRepository.saveFile(b, img.getImmagineId() + "." + img.getEstensione(), newPath);
		}
		for (DocumentoEventoModel doc : eventoDuplicatoModel.getDocumentoSet()) {
			byte[] b = FileRepository.getFile(doc.getPathToCopy());
			String newPath = repositoryRoot + File.separator + idEventoDuplicato.toString() + File.separator
					+ documentFolderName;
			FileRepository.saveFile(b, doc.getDocumentoId() + "." + doc.getEstensione(), newPath);
		}
	}

	public void cleanEventoModel(EventoModel model) {
		if (!model.getTicket().getTipoTicket().equals("pagamento")) {
			TicketModel ticketModelFixed = new TicketModel();
			ticketModelFixed.setEventoId(model.getTicket().getEventoId());
			ticketModelFixed.setTipoTicket(model.getTicket().getTipoTicket());
			ticketModelFixed.setNotaMulti(model.getTicket().getNotaMulti());
			ticketModelFixed.setLinkPrenotazioni(model.getTicket().getLinkPrenotazioni());
			model.setTicket(ticketModelFixed);
		}
		for (LocationModel locationModel : model.getLocationSet()) {
			if (!locationModel.getPuglia()) {
				locationModel.getAttrattoriSet().clear();
				if (locationModel.getComuneEstero() != null) {
					locationModel.setCap(null);
				}
			}
		}
		for (PeriodoModel periodoModel : model.getPeriodoSet()) {
			if (periodoModel.getDataSecca()) {
				periodoModel.setDataA(periodoModel.getDataDa());
				periodoModel.setCadenza("Nessuna");
				periodoModel.setCadenzaDom(false);
				periodoModel.setCadenzaLun(false);
				periodoModel.setCadenzaMar(false);
				periodoModel.setCadenzaMer(false);
				periodoModel.setCadenzaGio(false);
				periodoModel.setCadenzaVen(false);
				periodoModel.setCadenzaSab(false);
				periodoModel.getCadenzaMensile().clear();
				periodoModel.setChiusuraDom(false);
				periodoModel.setChiusuraLun(false);
				periodoModel.setChiusuraMar(false);
				periodoModel.setChiusuraMer(false);
				periodoModel.setChiusuraGio(false);
				periodoModel.setChiusuraVen(false);
				periodoModel.setChiusuraSab(false);
			}
			if (periodoModel.getFgContinuato()) {
				periodoModel.setOrarioAperturaMattina(null);
				periodoModel.setOrarioChiusuraMattina(null);
				periodoModel.setOrarioAperturaPomeriggio(null);
				periodoModel.setOrarioChiusuraPomeriggio(null);
			} else {
				periodoModel.setOrarioApertura(null);
				periodoModel.setOrarioChiusura(null);
			}
			if (periodoModel.getCadenza().equals("Nessuna")) {
				periodoModel.setCadenzaDom(false);
				periodoModel.setCadenzaLun(false);
				periodoModel.setCadenzaMar(false);
				periodoModel.setCadenzaMer(false);
				periodoModel.setCadenzaGio(false);
				periodoModel.setCadenzaVen(false);
				periodoModel.setCadenzaSab(false);
				periodoModel.getCadenzaMensile().clear();
			} else if (periodoModel.getCadenza().equals("Settimanale")) {
				periodoModel.getCadenzaMensile().clear();
			} else if (periodoModel.getCadenza().equals("Mensile")) {
				periodoModel.setCadenzaDom(false);
				periodoModel.setCadenzaLun(false);
				periodoModel.setCadenzaMar(false);
				periodoModel.setCadenzaMer(false);
				periodoModel.setCadenzaGio(false);
				periodoModel.setCadenzaVen(false);
				periodoModel.setCadenzaSab(false);
			}
		}
	}

	@Autowired
	StatoPubblicazioneRepository statoPubblicazioneRepository;

	@Autowired
	LogPubblicazioneRepository logPubblicazioneRepository;

	public Long addMetadata(Long idEvento, DettaglioUtenteModel dattaglioUtenteModel, String tipologiaId,
			String idRedazione, String jsonMetadata, String noteAggiuntive, String statoPubblicazione, String jwt)
			throws IllegalAccessException, Exception {

		// recupero l'eventuale pubblicazione generale
		PubblicazioneModel pubblicazioneGeneral = getMetadata(idEvento, "GENERAL", null);
		EventoModel ultimoEventoValidato = null;
		if (pubblicazioneGeneral != null) {
			ultimoEventoValidato = pubblicazioneGeneral.getEventoModel();
		} else if (!idRedazione.equals("GENERAL")) {

			addMetadata(idEvento, dattaglioUtenteModel, tipologiaId, "GENERAL", jsonMetadata, noteAggiuntive,
					statoPubblicazione, jwt);
			// throw new IllegalAccessException();
		}

		// recupero la redazione e i dati del redattore
		Redazione redazione = redazioneRepository.findById(idRedazione).get();
		DettaglioUtente redattore;
		if (!idRedazione.equals("GENERAL")) {
			Tipologia tipologia = tipologiaRepository.findById(tipologiaId).get();

			if (!tipologia.getRedazioniSet().stream().anyMatch(r -> r.getRedazioneId().equals(idRedazione))) {
				throw new IllegalAccessException();
			}

			redattore = dettaglioUtenteMapper.dtoToEntity(dattaglioUtenteModel);
			redattore.setTipologia(tipologia);
			dettaglioUtenteRepository.save(redattore);
		} else {
			redattore = dettaglioUtenteMapper.dtoToEntity(dattaglioUtenteModel);
		}

		// verifico che l'evento esista e sia nello stato validato se per la redazione è
		// attiva l'autospubblicazione - in ogni caso, se l'evento è validato, aggiorno
		// ultima validazione
		EventoModel eventoModel = null;
		try {
			eventoModel = getEvento(idEvento, redattore.getUtenteId());
		} catch (NoSuchElementException e) {
			throw new IllegalAccessException();
		}
		if (redazione.getAutoSpubblicazione() != null && redazione.getAutoSpubblicazione().booleanValue()) {
			if (!eventoModel.getStatoId().equals(statoFinale.getStatoId()) && !eventoModel.getStatoId().equals("RIVALIDATO")) {
				throw new IllegalAccessException();
			}
		}
		if (eventoModel.getStatoId().equals(statoFinale.getStatoId()) || eventoModel.getStatoId().equals("RIVALIDATO")) {
			ultimoEventoValidato = eventoModel;
		}

		boolean ripubblicazione = false;

		// creo la pubblicazione e la valorizzo con un eventuale pubblicazione presente
		// dello stesso tipo
		Pubblicazione pubblicazione = new Pubblicazione();
		Optional<Pubblicazione> optional = pubblicazioneRepository
				.findFirstByRedazione_RedazioneIdAndEvento_EventoIdOrderByDataPubblicazioneDesc(idRedazione, idEvento);

		if (optional.isPresent()) {
			pubblicazione = optional.get();
			ripubblicazione = pubblicazione.getLogPubblicazioni().stream()
					.anyMatch(x -> x.getStato().getStatoId().equalsIgnoreCase("PUBBLICATO"));
		}

		boolean refused = false;

		String statoPubblicazioneCode;
		switch (statoPubblicazione) {
		case "BOZZA":
			statoPubblicazioneCode = "IN_COMPILAZIONE";
			break;
		case "PUBBLICATO":
			statoPubblicazioneCode = ripubblicazione ? "RIPUBBLICATO" : "PUBBLICATO";
			break;
		case "RIFIUTATO":
			statoPubblicazioneCode = "RIFIUTATA";
			statoPubblicazione = "Respinto";
			refused = true;
			break;
		case "REDATTO":
			statoPubblicazioneCode = "REDATTO";
			break;
		default:
			statoPubblicazioneCode = statoPubblicazione;
			break;
		}

		StatiLogPubblicazione statoPubblicazioneEnt = statoPubblicazioneRepository.findById(statoPubblicazioneCode)
				.orElse(null);

		LogPubblicazioni logPub = new LogPubblicazioni();
		boolean statoTrovato = false;
		if (statoPubblicazioneEnt != null) {
			statoTrovato = true;

			logPub.setIdUtenteModifica(dattaglioUtenteModel.getUtenteId());
			logPub.setStato(statoPubblicazioneEnt);
			logPub.setNote(noteAggiuntive);

		}

		// Mantengo il vecchio metadata
		JsonNode oldGenericMetadata = pubblicazione.getGenericMetadataWip();

		pubblicazione.setRedazione(redazione);
		pubblicazione.setNoteAggiuntive(noteAggiuntive);
		pubblicazione.setStatoPubblicazione(StatoPubblicazione.of(statoPubblicazione));
		pubblicazione.setRedattore(redattore);
		pubblicazione.setEventoModel(ultimoEventoValidato);

		ObjectMapper mapper = new ObjectMapper();

		if (jsonMetadata != null) {
			pubblicazione.setGenericMetadataWip(mapper.readValue(jsonMetadata, JsonNode.class));
		}

		Evento evento = (eventoRepository.getOne(idEvento));
		if (StatoPubblicazione.of(statoPubblicazione) == StatoPubblicazione.PUBBLICATO) {
			if (jsonMetadata != null) {
				pubblicazione.setGenericMetadata(mapper.readValue(jsonMetadata, JsonNode.class));
			}

			if (!idRedazione.equals("GENERAL")) {
				evento.setFgPubblicato(true);
//			eventoRepository.updatePubblicazioneFlag(true,idEvento);
			}
		} else if (!pubblicazioneRepository
				.existsByGenericMetadataNotNullAndEvento_EventoIdAndRedazione_RedazioneIdNot(idEvento, "GENERAL")
				.booleanValue() || refused) {
//		eventoRepository.updatePubblicazioneFlag(false,idEvento);
			pubblicazione.setGenericMetadata(null);

			evento.setFgPubblicato(false);
		}

		String destinationUrl = "";
		String link = "";

		if (jsonMetadata != null && pubblicazione.getRedazione().getRedazioneId().equals("VIP")) {
			if (jsonMetadata.contains("\"tipoScheda\":\"" + SCHEDA_EVENTO + "\"")) {
				destinationUrl = "/evento/" + PLACEHOLDER + "/it/"
						+ eventoModel.getDatiGenerali().getTitoloMulti().get("IT").replace(" ", "-");
			} else if (jsonMetadata.contains("\"tipoScheda\":\"" + SCHEDA_ATTIVITA + "\"")) {
				destinationUrl = "/attivita/it/" + PLACEHOLDER;
			}
		}

		if (pubblicazione.getRedazione() != null && pubblicazione.getRedazione().getLink() != null) {
			link = StringEscapeUtils.escapeJava(pubblicazione.getRedazione().getLink())
					+ destinationUrl.replaceAll("\"", "");
		}

		log.info("Path VIP " + link);

		String content = "{\r\n" + "  \"titolo\": \""
				+ StringEscapeUtils.escapeJava(ultimoEventoValidato.getDatiGenerali().getTitoloMulti().get("IT"))
				+ "\",\r\n" + "  \"id\": \"" + ultimoEventoValidato.getEventoId() + "\",\r\n" + "  \"redazione\": \""
				+ StringEscapeUtils.escapeJava(pubblicazione.getRedazione().getNome()) + "\",\r\n" + "  \"link\": \""
				+ link + "\",\r\n" + "  \"empty\": false\r\n" + "}";
		eventoRepository.save(evento);

		pubblicazione.setEvento(evento);
		
		pubblicazione.setDataPubblicazione(Timestamp.from(java.time.ZonedDateTime.now().toInstant()));;

		Pubblicazione pubblicazioneSalvata = pubblicazioneRepository.save(pubblicazione);

		if (statoTrovato) {
			logPub.setPubblicazione(pubblicazioneSalvata);
			logPub = logPubblicazioneRepository.save(logPub);
			pubblicazioneSalvata.addLog(logPub);
		}
		pubblicazioneSalvata = pubblicazioneRepository.save(pubblicazioneSalvata);
		if (!idRedazione.equals("GENERAL") && !refused) {
			// Prendo i metadata wip perchè sempre aggiornati
			relazioniService.allignRelazioniScheda(pubblicazioneSalvata, oldGenericMetadata, eventoModel, idRedazione);
		}

		// JMS e invio mail

		try {
			if (redazione.getAttivazioneJMS() != null && redazione.getAttivazioneJMS().booleanValue()
					&& StatoPubblicazione.of(statoPubblicazione) == StatoPubblicazione.PUBBLICATO) {
				log.info("PUBBLICAZIONE SU JMS NECESSARIA");

				List<String> mailOwnerList = new ArrayList<>();
				mailOwnerList.add(ultimoEventoValidato.getEmailOwner());
				String mailSedeOperativa = "";

				if (ultimoEventoValidato.getAttivita() != null) {
					String token = cacheTokenService.getToken();
					mailSedeOperativa = dmsService.getMailImpresa(token,
							ultimoEventoValidato.getAttivita().getAttivitaId());
				} else if (ultimoEventoValidato.getRichiestaAttivita() != null) {
					String token = cacheTokenService.getToken();
					mailSedeOperativa = dmsService.getMailRegistrazioneImpresa(token,
							ultimoEventoValidato.getRichiestaAttivita().getRichiestaAttivitaId());
				}
				if (!mailSedeOperativa.isEmpty()) {
					mailOwnerList.add(mailSedeOperativa);
				}

				if (ripubblicazione) {
					asyncService.invioMailConseguenteJMSRipubblicazione(content, pubblicazioneSalvata, idRedazione,
							redattore.getEmail(), mailOwnerList);

				} else {
					asyncService.invioMailConseguenteJMSPubblicazione(content, pubblicazioneSalvata, idRedazione,
							redattore.getEmail(), mailOwnerList);
				}
			}
		} catch (Exception e) {
			log.error("ERRORE JMS: " + e.getMessage());
		}
		///

		return pubblicazioneSalvata.getPubblicazioneId();
	}

	public Long recoverMetadata(Long idEvento, DettaglioUtenteModel dattaglioUtenteModel, String tipologiaId,
			String idRedazione) throws IllegalAccessException, Exception {
		Tipologia tipologia = tipologiaRepository.findById(tipologiaId).get();

		if (!eventoRepository.existsById(idEvento)) {
			throw new IllegalAccessException();
		}

		if (!tipologia.getRedazioniSet().stream().anyMatch(r -> r.getRedazioneId().equals(idRedazione))) {
			throw new IllegalAccessException();
		}

		DettaglioUtente redattore = dettaglioUtenteMapper.dtoToEntity(dattaglioUtenteModel);
		redattore.setTipologia(tipologia);
		dettaglioUtenteRepository.save(redattore);

		Pubblicazione pubblicazione = new Pubblicazione();
		Optional<Pubblicazione> optional = pubblicazioneRepository
				.findFirstByRedazione_RedazioneIdAndEvento_EventoIdOrderByDataPubblicazioneDesc(idRedazione, idEvento);
		if (optional.isPresent()) {
			pubblicazione = optional.get();
			if (pubblicazione.getGenericMetadata() == null) {
				throw new IllegalAccessException();
			}

			pubblicazione.setStatoPubblicazione(StatoPubblicazione.PUBBLICATO);
			pubblicazione.setRedattore(redattore);
			pubblicazione.setGenericMetadataWip(pubblicazione.getGenericMetadata());
			Evento evento = (eventoRepository.getOne(idEvento));
			evento.setFgPubblicato(true);
			eventoRepository.save(evento);
//			eventoRepository.updatePubblicazioneFlag(true, idEvento);
		} else {
			throw new IllegalAccessException();
		}
		Pubblicazione pubblicazioneSalvata = pubblicazioneRepository.save(pubblicazione);
		LogPubblicazioni logPub = new LogPubblicazioni();
		String statoPubblicazioneCode = "RIPUBBLICATO";
		StatiLogPubblicazione statoPubblicazioneEnt = statoPubblicazioneRepository.findById(statoPubblicazioneCode)
				.get();

		logPub.setIdUtenteModifica(dattaglioUtenteModel.getUtenteId());
		logPub.setStato(statoPubblicazioneEnt);
		logPub.setPubblicazione(pubblicazioneSalvata);
		logPub = logPubblicazioneRepository.save(logPub);
		pubblicazioneSalvata.addLog(logPub);
		pubblicazioneSalvata = pubblicazioneRepository.save(pubblicazioneSalvata);

		EventoModel ultimoEventoValidato = pubblicazioneSalvata.getEventoModel();
		Redazione redazione = redazioneRepository.findById(idRedazione).get();
		// JMS e invio mail
		String content = "{\r\n" + "  \"titolo\": \""
				+ StringEscapeUtils.escapeJava(ultimoEventoValidato.getDatiGenerali().getTitoloMulti().get("IT"))
				+ "\",\r\n" + "  \"id\": \"" + ultimoEventoValidato.getEventoId() + "\",\r\n" + "  \"redazione\": \""
				+ StringEscapeUtils.escapeJava(pubblicazione.getRedazione().getNome()) + "\",\r\n" + "  \"link\": \""
				+ StringEscapeUtils.escapeJava(pubblicazione.getRedazione().getLink().replaceAll("\"", "")) + "\",\r\n"
				+ "  \"empty\": false\r\n" + "}";
		try {
			if (redazione.getAttivazioneJMS() != null && redazione.getAttivazioneJMS().booleanValue()) {
				log.info("PUBBLICAZIONE SU JMS NECESSARIA");

				List<String> mailOwnerList = new ArrayList<>();
				mailOwnerList.add(ultimoEventoValidato.getEmailOwner());
				String mailSedeOperativa = "";

				if (ultimoEventoValidato.getAttivita() != null) {
					String token = cacheTokenService.getToken();
					mailSedeOperativa = dmsService.getMailImpresa(token,
							ultimoEventoValidato.getAttivita().getAttivitaId());
				} else if (ultimoEventoValidato.getRichiestaAttivita() != null) {
					String token = cacheTokenService.getToken();
					mailSedeOperativa = dmsService.getMailRegistrazioneImpresa(token,
							ultimoEventoValidato.getRichiestaAttivita().getRichiestaAttivitaId());
				}
				if (!mailSedeOperativa.isEmpty()) {
					mailOwnerList.add(mailSedeOperativa);
				}

				asyncService.invioMailConseguenteJMSRipubblicazione(content, pubblicazioneSalvata, idRedazione,
						redattore.getEmail(), mailOwnerList);
			}
		} catch (Exception e) {
			log.error("ERRORE JMS: " + e.getMessage());
		}

		return pubblicazioneSalvata.getPubblicazioneId();
	}

	public Long deleteMetadata(Long idEvento, DettaglioUtenteModel dattaglioUtenteModel, String tipologiaId,
			String idRedazione, String jwt) throws IllegalAccessException, Exception {
		Tipologia tipologia = tipologiaRepository.findById(tipologiaId).get();

		if (!eventoRepository.existsById(idEvento)) {
			throw new IllegalAccessException();
		}

		if (!tipologia.getRedazioniSet().stream().anyMatch(r -> r.getRedazioneId().equals(idRedazione))) {
			throw new IllegalAccessException();
		}

		DettaglioUtente redattore = dettaglioUtenteMapper.dtoToEntity(dattaglioUtenteModel);
		redattore.setTipologia(tipologia);
		dettaglioUtenteRepository.save(redattore);

		Pubblicazione pubblicazione = new Pubblicazione();
		Optional<Pubblicazione> optional = pubblicazioneRepository
				.findFirstByRedazione_RedazioneIdAndEvento_EventoIdOrderByDataPubblicazioneDesc(idRedazione, idEvento);
		if (optional.isPresent()) {
			pubblicazione = optional.get(); 
			if (pubblicazione.getGenericMetadata() == null) {
				throw new IllegalAccessException();
			}

			pubblicazione.setStatoPubblicazione(StatoPubblicazione.BOZZA);
			pubblicazione.setRedattore(redattore);
			// pubblicazione.setGenericMetadata(null);

			Evento evento = (eventoRepository.getOne(idEvento));
			evento.setFgPubblicato(false);
			eventoRepository.save(evento);
//				eventoRepository.updatePubblicazioneFlag(false, idEvento);

		} else {
			throw new IllegalAccessException();
		}

		Redazione redazione = tipologia.getRedazioniSet().stream().filter(r -> r.getRedazioneId().equals(idRedazione))
				.findFirst().get();
		LogPubblicazioni logPub = new LogPubblicazioni();
		logPub.setPubblicazione(pubblicazione);

		String statoPubblicazioneCode = "SPUBBLICATO";

		StatiLogPubblicazione statoPubblicazioneEnt = statoPubblicazioneRepository.findById(statoPubblicazioneCode)
				.get();

		logPub.setIdUtenteModifica(dattaglioUtenteModel.getUtenteId());
		logPub.setStato(statoPubblicazioneEnt);

		logPub.setPubblicazione(pubblicazione);
		logPub = logPubblicazioneRepository.save(logPub);
		pubblicazione.addLog(logPub);
		Pubblicazione pubblicazioneSalvata = pubblicazioneRepository.save(pubblicazione);

		// JMS
		try {
			if (redazione.getAttivazioneJMS() != null && redazione.getAttivazioneJMS().booleanValue()) {
				log.info("PUBBLICAZIONE SU JMS NECESSARIA");
				asyncService.invioJMSSpubblicazione(pubblicazioneSalvata, idEvento, idRedazione, redattore.getEmail());
			}
		} catch (Exception e) {
			log.error("ERRORE JMS: " + e.getMessage());
		}
		///

		return pubblicazioneSalvata.getPubblicazioneId();
	}

	public PubblicazioneModel getMetadata(Long idEvento, String idRedazione, Long idPubblicazione) {
		Pubblicazione pubblicazione = null;
		if (idPubblicazione == null) {
			Optional<Pubblicazione> optional = pubblicazioneRepository
					.findFirstByRedazione_RedazioneIdAndEvento_EventoIdOrderByDataPubblicazioneDesc(idRedazione,
							idEvento);
			if (optional.isPresent()) {
				pubblicazione = optional.get();
			} else {
				return null;
			}
		} else {
			try {
				pubblicazione = pubblicazioneRepository.findById(idPubblicazione).get();
			} catch (NoSuchElementException e) {
				return null;
			}
		}

		PubblicazioneModel model = pubblicazioneMapper.entityToDto(pubblicazione);

		if (model.getOwnerId() != null) {
			DettaglioUtente dettaglio = dettaglioUtenteRepository.findById(model.getOwnerId()).orElse(null);
			model.setNomeOwner(dettaglio.getNome());
			model.setCognomeOwner(dettaglio.getCognome());
		}

		// codice spostato nel mapper
		/*
		 * model.setGenericMetadata(pubblicazione.getGenericMetadataWip());
		 * if(pubblicazione.getGenericMetadata() != null) { model.setFgPubblicato(true);
		 * } else { model.setFgPubblicato(false); }
		 */

		return model;
	}

	public Set<PubblicazioneModel> getPubblicazioneList(Long eventoId) {
		Set<Pubblicazione> pubblicazioneSet = pubblicazioneRepository
				.findByGenericMetadataNotNullAndEvento_EventoIdAndRedazione_RedazioneIdNot(eventoId, "GENERAL");
		Set<PubblicazioneModel> pubblicazioneModelSet = pubblicazioneMapper.entityToDtoSet(pubblicazioneSet);
		for (PubblicazioneModel model : pubblicazioneModelSet) {
			model.setEventoId(model.getEventoModel().getEventoId());
			model.setEventoModel(null);
			model.setGenericMetadata(null);
		}
		return pubblicazioneModelSet.stream().filter(p -> !p.getRedazioneId().equals("GENERAL"))
				.collect(Collectors.toSet());
	}

	public Set<PubblicazioneModel> getPubblicazioneListByMetadata(String idRedazione, String search) {
		Specification<Pubblicazione> spec = new Specification<Pubblicazione>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Pubblicazione> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<Pubblicazione, Redazione> joinRedazione = root.join(Pubblicazione_.redazione, JoinType.INNER);
				return cb.and(cb.isNotNull(root.get(Pubblicazione_.genericMetadata)),
						cb.equal(joinRedazione.get(Redazione_.redazioneId), idRedazione));
			}
		};
		if (search != null) {
			Node rootNode = new RSQLParser().parse(search);
			spec = spec.and(rootNode.accept(new CustomRsqlVisitor<Pubblicazione>()));
		}

		List<Pubblicazione> pubblicazioneList = pubblicazioneRepository.findAll(spec);
		Set<Pubblicazione> pubblicazioneSet = pubblicazioneList.stream().collect(Collectors.toSet());
		Set<PubblicazioneModel> pubblicazioneModelSet = pubblicazioneMapper.entityToDtoSet(pubblicazioneSet);
		for (PubblicazioneModel model : pubblicazioneModelSet) {
			model.setEventoId(model.getEventoModel().getEventoId());
		}
		return pubblicazioneModelSet;
	}

	public void cancellaEvento(Long idEvento, Long idUtente) throws LockedResourceException, Exception {
		lockService.lockRisorsa(idEvento, idUtente, Evento.class);
		Evento evento = eventoRepository.findById(idEvento).get();
		DettaglioUtente chiamante = dettaglioUtenteRepository.findById(idUtente).get();
		if (evento.getStato().getStatoId().equals("BOZZA")) {
			evento.setStato(null);
			relazioneRepository.deleteByEventoRelazionatoId(evento.getEventoId());
			LogEvento logEvento = new LogEvento();
			logEvento.setTipoOperazione(messageRetriever.getCANCELLAZIONE());
			logEvento.setNomeUtente(chiamante.getNome() + " " + chiamante.getCognome());
			logEvento.setEvento(evento);
			Set<LogEvento> logEventoSet = evento.getLogSet();
			logEventoSet.add(logEvento);
			evento.setLogSet(logEventoSet);
			eventoRepository.save(evento);
		}
	}

	public List<SmartModelList> getEventoPerComune(Boolean isRedazione) {

		return eventoRepository.findEventoGroupByComuneMoreThan9(isRedazione);

	}

	public Set<LogEventoModel> getLogEventiList(Long eventoId) {
		Set<LogEvento> logEventoSet = logEventoRepository.findByEvento_EventoId(eventoId);
		Set<LogEventoModel> logEventoModelSet = logEventoMapper.entityToDtoSet(logEventoSet);

		return logEventoModelSet;
	}

//	public List<EventoModificatoModel> getListaEventiModificatiPerData(String dataDa, String dataA)
	public List<Long> getListaEventiModificatiPerData(String dataDa, String dataA) {

		Timestamp dataDaTimestamp = null;
		Timestamp dataATimestamp = null;

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
		if (dataDa == null || "".equals(dataDa)) {
			dataDa = "2000-01-01-00:00";
		}

		if (dataDa.length() == 10)
			dataDa += "-00:00";

		try {
			// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			java.util.Date parsedDate = dateFormat.parse(dataDa);
			dataDaTimestamp = new java.sql.Timestamp(parsedDate.getTime());

			if (dataA != null && !"".equals(dataA)) {
//		dataA +=" 23:59:59";

				if (dataA.length() == 10)
					dataA += "-23:59";

				parsedDate = dateFormat.parse(dataA);

				dataATimestamp = new java.sql.Timestamp(parsedDate.getTime());
			} else
				dataATimestamp = Timestamp.valueOf(java.time.LocalDateTime.now());
		} catch (Exception e) {
			e.printStackTrace();
		}

		Set<Long> idList = new HashSet<Long>();

		Set<LogEvento> logs = logEventoRepository
				.findByDataModificaGreaterThanEqualAndDataModificaLessThanEqual(dataDaTimestamp, dataATimestamp);

		for (LogEvento logEvento : logs) {

			// INVERTIRE IF SE NECESSARIO VERIFICARE TUTTI GLI STATI E NON SOLO IL VALIDATO
//			boolean validato = logEvento.getEvento().getLogSet().stream().anyMatch(x->x.getDescrizioneStato()!=null && x.getDescrizioneStato().equalsIgnoreCase("VALIDATO"));
//			if(validato) {
			if ((logEvento.getEvento().getStato() != null
					&& logEvento.getEvento().getStato().getStatoId().equals(statoFinale.getStatoId())) || (logEvento.getEvento().getStato() != null && logEvento.getEvento().getStato().getStatoId().equalsIgnoreCase("RIVALIDATO"))) {
				idList.add(logEvento.getEvento().getEventoId());
			}
		}

		List<Long> idFinal = new ArrayList<>();

		idFinal.addAll(idList);

		return idFinal;

//		
//		List<LogEvento> logModelSort = new ArrayList<>();
//		
//		logModelSort.addAll(logs);
//		
//	
//		
//		logModelSort.sort(Comparator.comparing(LogEvento::getDataModifica).reversed());
//	
//		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//		List<EventoModificatoModel> eventiModificati = new ArrayList<>();
//		
//		for(Long id : idList)
//		{
//			
//			try {
//				LogEvento ultimaModifica =logModelSort.stream().filter(x->  x.getEvento().getEventoId().longValue() == id.longValue()).findFirst().get(); 
//				EventoModificatoModel evento = new EventoModificatoModel();
//				evento.setIdEvento(id);
//				evento.setStato(ultimaModifica.getEvento().getStato().getStatoId());
//				evento.setDataModifica(formatter.format(ultimaModifica.getDataModifica()));
//				eventiModificati.add(evento);
//			}
//			catch (Exception e) {
//				log.error(LogUtility.exceptionToLog(e));
//			}
//		}
//		
//		return eventiModificati;
//		

	}

}
