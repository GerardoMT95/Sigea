package it.indra.SigecAPI.migration.service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.indra.es.builder.RestTemplateBuilder;

import it.indra.SigeaCommons.enumeration.StatoPubblicazione;
import it.indra.SigeaCommons.model.AttrattoreModel;
import it.indra.SigeaCommons.model.EmailModel;
import it.indra.SigeaCommons.model.EventoModel;
import it.indra.SigeaCommons.model.RelazioneModel;
import it.indra.SigeaCommons.model.SitoModel;
import it.indra.SigeaCommons.model.TelefonoModel;
import it.indra.SigeaCommons.model.redazioni.VIPSchedaDocumentoModel;
import it.indra.SigeaCommons.model.redazioni.VIPSchedaImmagineModel;
import it.indra.SigeaCommons.model.redazioni.VIPSchedaLinkModel;
import it.indra.SigeaCommons.model.redazioni.VIPSchedaModel;
import it.indra.SigeaCommons.model.redazioni.VIPSchedaRelazioneModel;
import it.indra.SigecAPI.configuration.MessageRetriever;
import it.indra.SigecAPI.entity.Accessibilita;
import it.indra.SigecAPI.entity.Attivita;
import it.indra.SigecAPI.entity.Attrattore;
import it.indra.SigecAPI.entity.Contatto;
import it.indra.SigecAPI.entity.DatiGenerali;
import it.indra.SigecAPI.entity.DettaglioUtente;
import it.indra.SigecAPI.entity.DocumentoEvento;
import it.indra.SigecAPI.entity.Evento;
import it.indra.SigecAPI.entity.Immagine;
import it.indra.SigecAPI.entity.Link;
import it.indra.SigecAPI.entity.Location;
import it.indra.SigecAPI.entity.LogEvento;
import it.indra.SigecAPI.entity.LogPubblicazioni;
import it.indra.SigecAPI.entity.Periodo;
import it.indra.SigecAPI.entity.Prodotto;
import it.indra.SigecAPI.entity.Pubblicazione;
import it.indra.SigecAPI.entity.Redazione;
import it.indra.SigecAPI.entity.Relazione;
import it.indra.SigecAPI.entity.StatiLogPubblicazione;
import it.indra.SigecAPI.entity.Stato;
import it.indra.SigecAPI.entity.Ticket;
import it.indra.SigecAPI.entity.TipologiaMIBACT;
import it.indra.SigecAPI.filerepository.FileRepository;
import it.indra.SigecAPI.mapper.EventoMapper;
import it.indra.SigecAPI.migration.entity.ComuneRecord;
import it.indra.SigecAPI.migration.entity.LogMigration;
import it.indra.SigecAPI.migration.entity.Sigea2Vip1ShotMigra;
import it.indra.SigecAPI.migration.entity.Sigea2VipMigra;
import it.indra.SigecAPI.migration.repository.LogMigrationRepository;
import it.indra.SigecAPI.migration.repository.Sigea2Vip1ShotMigraRepository;
import it.indra.SigecAPI.migration.repository.Sigea2VipMigraRepository;
import it.indra.SigecAPI.migration.utils.ApplicationConstants;
import it.indra.SigecAPI.migration.utils.LogMapperService;
import it.indra.SigecAPI.repository.AttivitaRepository;
import it.indra.SigecAPI.repository.AttrattoreRepository;
import it.indra.SigecAPI.repository.EventoRepository;
import it.indra.SigecAPI.repository.LogEventoRepository;
import it.indra.SigecAPI.repository.LogPubblicazioneRepository;
import it.indra.SigecAPI.repository.PubblicazioneRepository;
import it.indra.SigecAPI.repository.RelazioneRepository;
import it.indra.SigecAPI.service.CacheTokenService;
import it.indra.SigecAPI.service.RelazioniService;
import it.indra.sigea2vip.persistence.entity.AccessibilitaEntity;
import it.indra.sigea2vip.persistence.entity.AccessibilitaanimaleEntity;
import it.indra.sigea2vip.persistence.entity.AccessibilitadisabilitaEntity;
import it.indra.sigea2vip.persistence.entity.AccessibilitaservizioEntity;
import it.indra.sigea2vip.persistence.entity.AccessibilitaspaziobambiniEntity;
import it.indra.sigea2vip.persistence.entity.AmbitiTerritorialiEntity;
import it.indra.sigea2vip.persistence.entity.AttrattoreIntEntity;
import it.indra.sigea2vip.persistence.entity.BackeventiutentiEntity;
import it.indra.sigea2vip.persistence.entity.CategoriaeventoEntity;
import it.indra.sigea2vip.persistence.entity.ComuneRiferimentoEntity;
import it.indra.sigea2vip.persistence.entity.EventoEntity;
import it.indra.sigea2vip.persistence.entity.EventoallegatiEntity;
import it.indra.sigea2vip.persistence.entity.EventoallegatiintEntity;
import it.indra.sigea2vip.persistence.entity.EventointEntity;
import it.indra.sigea2vip.persistence.entity.EventolocationEntity;
import it.indra.sigea2vip.persistence.entity.EventomicroesperienzemappingEntity;
import it.indra.sigea2vip.persistence.entity.GiornochiusuraEntity;
import it.indra.sigea2vip.persistence.entity.OrarioEntity;
import it.indra.sigea2vip.persistence.entity.RassegnaEventiMappingEntity;
import it.indra.sigea2vip.persistence.entity.ScontoEntity;
import it.indra.sigea2vip.persistence.entity.TicketEntity;
import it.indra.sigea2vip.persistence.entity.TicketnoteintEntity;
import it.indra.sigea2vip.persistence.repository.AmbitiTerritorialiEntityRepository;
import it.indra.sigea2vip.persistence.repository.ComuneRiferimentoEntityRepository;
import it.indra.sigea2vip.persistence.repository.ComuneRiferimentoIntEntityRepository;
import it.indra.sigea2vip.persistence.repository.ProvinciaEntityRepository;
import it.indra.sigea2vip.persistence.repository.RassegnaEventiMappingEntityRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MapperService {

	@Value("${filesystem.repository.root}")
	private String repositoryRoot;

	@Value("${filesystem.repository.image}")
	private String imageFolderName;

	@Value("${filesystem.repository.document}")
	private String documentFolderName;

	@Value("${filesystem.repository.publication}")
	private String publicationFolderName;

	@Value("${constants.migration.riconciliare}")
	private Boolean riconciliareV;

	@Value("${constants.migration.attivita.id}")
	private Long attivitaIdV;

	@Value("${constants.migration.attivita.denominazione}")
	private String attivitaDenominazioneV;

	@Value("${constants.migration.basePath.attachedFile}")
	private String basePathAttachedFileV;

	@Value("${constants.migration.basePath.territorialService}")
	private String basePathTerritorialServiceV;

	@Autowired
	CacheTokenService cacheTokenService;



	List<Long> IDsSIGEABANNER = null;
	LogMigration logMigration  =null;

	@Autowired ComuneRiferimentoIntEntityRepository 		comuneRiferimentoIntEntityRepository;
	@Autowired ComuneRiferimentoEntityRepository 			comuneRiferimentoEntityRepository;
	@Autowired ProvinciaEntityRepository 					provinciaEntityRepository;
	@Autowired AmbitiTerritorialiEntityRepository 			ambitiTerritorialiEntityRepository;
	@Autowired EventoRepository 							eventoRepository;
	@Autowired AttivitaRepository 							attivitaRepository;
	@Autowired AttrattoreRepository							attrattoreRepository;	
	@Autowired EventoMapper									eventoMapper;
	@Autowired LogPubblicazioneRepository					logPubblicazioneRepository;
	@Autowired PubblicazioneRepository						pubblicazioneRepository;
	@Autowired MessageRetriever 							messageRetriever;
	@Autowired LogMapperService								logMapperService;
	@Autowired LogMigrationRepository						logMigrationRepository;
	@Autowired Sigea2VipMigraRepository						sigea2VipMigraRepository;
	@Autowired RelazioneRepository							relazioneRepository;
	@Autowired RelazioniService								relazioniService;
	@Autowired RassegnaEventiMappingEntityRepository		rassegnaEventiMappingEntityRepository;
	@Autowired LogEventoRepository							logEventoRepository;
	@Autowired Sigea2Vip1ShotMigraRepository				sigea2Vip1ShotMigraRepository;


	@Transactional
	public Evento entityVipToEntitySigea(	
			Set<CategoriaeventoEntity> sottoCategorieVipSet, 
			Set<CategoriaeventoEntity> prodottiVipSet, 
			List<TipologiaMIBACT> tipologiaMibactSigea, 
			EventoEntity dto, 
			BackeventiutentiEntity utenteVip, 
			TicketEntity ticketVip, 
			Set<TicketnoteintEntity> ticketnoteintVipSet, 
			Set<ScontoEntity> scontoVipSet, 
			AccessibilitaEntity accessibilitaVip, 
			Set<AccessibilitaanimaleEntity> animaleVipSet, 
			Set<AccessibilitadisabilitaEntity> disabiVipSet, 
			Set<AccessibilitaservizioEntity> servizioVipSet, 
			Set<AccessibilitaspaziobambiniEntity> spaziobambiniVipSet, 
			Set<EventointEntity> eventoIntVipSet, 
			Set<EventolocationEntity> eventoLocationVipSet, 
			Set<AttrattoreIntEntity> attrattoreVipSet, 
			Set<OrarioEntity> orarioVipSet, 
			Set<GiornochiusuraEntity> giorniChiusuraVipSet, 
			Set<EventoallegatiintEntity> allegatiIntVipSet, 
			List<EventoallegatiEntity> eventoAllegatiVipSet, 
			Set<CategoriaeventoEntity> categoriaEventoVipSet, 
			DettaglioUtente chiamante, 
			Redazione redazioneGeneral, 
			Redazione redazioneVip, 
			List<EventomicroesperienzemappingEntity> microesperienze, 
			List<Prodotto> prodottiSigea, 
			List<ComuneRecord> comuneRecordList) throws Exception {

		if ( dto == null ) {
			return null;
		}

		log.debug("PREPARAZIONE DATI");

		Evento evento = new Evento();
		Stato statoIniziale=new Stato();

		//utility list per salvare gli id delle immagini banner
		IDsSIGEABANNER = new ArrayList<Long>();

		Map<String,java.sql.Date> extremeDates = new HashMap<String,java.sql.Date>();
		StringBuilder operazioniBuilder = new StringBuilder("");
		StatiLogPubblicazione statiLogPubblicazione=new StatiLogPubblicazione();
		logMigration = new LogMigration();

		statoIniziale.setStatoId(ApplicationConstants.STATOEVENTOVALIDATO);
		statiLogPubblicazione.setStatoId(ApplicationConstants.STATOPUBBLICAZIONE);
		Map<String,String> tipologiaMibactFromCategorie = getTipologiaMibactFromCategorie(sottoCategorieVipSet, categoriaEventoVipSet,tipologiaMibactSigea);
		Set<String> prodottiFromCategorie = getProdottiFromCategorie(prodottiVipSet, categoriaEventoVipSet, prodottiSigea);

		log.debug("START SETTING");

		evento.setTipo(checkTipologia(dto.getTipologia()));
		evento.setGrandeEvento(false);
		evento.setUltimeNote(null);
		evento.getProgetto().setProgettoId(null);
		evento.getProgetto().setTitoloProgetto(null);
		evento.setFgValidazionePregressa(true);
		//NEL CASO CI FOSSERO PIU' CORRISPONDENZE CON MIBACT, PRENDI UNA QUALSIASI (SCELTA LA PRIMA)
		evento.setIdMIBACT( tipologiaMibactFromCategorie.entrySet().stream().findFirst().get().getKey());
		evento.setTipologiaMIBACT( tipologiaMibactFromCategorie.entrySet().stream().findFirst().get().getValue() ); 

		evento.setEmailRiconciliazione(utenteVip.getEmail());
		evento.setDaRiconciliare(riconciliareV);
		evento.setFgFinanziato(false);
		evento.setFgPubblicato(true);//dipende dalla presenza o meno di GenericMetadata
		evento.setFgValidazionePregressa(true);
		evento.setFgValidazioneUltimoGiorno(true);
		evento.setOwner(chiamante);
		evento.setOwnerUltimaModifica(chiamante);
		evento.setStato(statoIniziale);
		evento.setUltimeNote(null);
		evento.setRichiestaAttivita(null);

		evento.setContattoSet(
				contattiVipToContattiSigea(
						dto,
						evento));

		evento.setTicket( 	
				ticketVipToTicketSigea(
						evento,
						dto.getTipoticket(), 
						ticketVip,
						ticketnoteintVipSet,
						scontoVipSet)
				);

		evento.setDatiGenerali( 
				datiGeneraliVipToDatiGeneraliSigea(
						evento,
						eventoIntVipSet)
				);

		evento.setAccessibilita( 
				accessibilitaVipToAccessibilitaSigea(
						evento,
						accessibilitaVip,
						animaleVipSet,
						disabiVipSet, 
						servizioVipSet, 
						spaziobambiniVipSet
						)
				);

		evento.setAttivita(
				attivitaVipToAttivitaSigea()
				);


		evento.setLocationSet(
				locationVipToLocationSigea(
						evento,
						eventoLocationVipSet,
						attrattoreVipSet,
						comuneRecordList)
				);


		evento.setPeriodoSet(
				periodoVipToPeriodoSigea(
						evento,
						orarioVipSet, 
						giorniChiusuraVipSet
						)
				);

		evento.setLinkSet(link2VipToLinkEsterniSigea(
				evento,
				dto
				)
				);

		//SUCCESSIVO al setPeriodoSet, altrimenti non riusciremmo a rendere gli estremi
		extremeDates = getExtremeDates(evento);
		evento.setDataDaMin(extremeDates.get("dataDaMin"));
		evento.setDataAMax(extremeDates.get("dataAMax"));

		evento.setImmagineSet(
				placeholderImmaginiSigea(
						evento,
						allegatiIntVipSet,
						eventoAllegatiVipSet)
				);

		evento.setDocumentoSet(
				placeholderDocumentiSigea(
						evento,
						allegatiIntVipSet,
						eventoAllegatiVipSet)
				);

		//SUCCESSIVO A TUTTI I SET PRECEDENTI, è un set di log
		operazioniBuilder= getOperazioniBuilder(evento);
		evento.setLogSet(
				logVipToSigea(
						evento,
						chiamante,
						operazioniBuilder
						)
				);



		//salvo eventuali attrattori non ancora censiti nel DB
		for (Location l: evento.getLocationSet()) {
			if (l.getAttrattoriSet()!=null) {
				for(Attrattore a : l.getAttrattoriSet()) {
					attrattoreRepository.save(a);
				}
			}
		}

		if(evento.getAttivita()!=null) {
			attivitaRepository.save(evento.getAttivita());
		}


		//salva e genera tutti gli id 
		//non posso salvare gia gli allegati in tutti i suoi attributi perchè ho la doppia chiave id e idevento
		Evento	eventoSalvato = eventoRepository.save(evento);

		//ora salvo gli allegati per tutti i suoi attributi
		eventoSalvato.setImmagineSet(
				immaginiVipToImmaginiSigea(
						eventoSalvato,
						allegatiIntVipSet,
						eventoAllegatiVipSet,
						logMigration)
				);

		eventoSalvato.setDocumentoSet(
				documentiVipToDocumentiSigea(
						eventoSalvato,
						allegatiIntVipSet,
						eventoAllegatiVipSet,
						logMigration)
				);


		//salvo evento con allegati
		Evento eventoFinal = eventoRepository.save(eventoSalvato);

		//salvo nella tabella da ribaltare su schema turismo.
		sigea2VipMigraRepository.save(Sigea2VipMigra.builder().idEventoVip(dto.getIdevento()).idSigea(eventoFinal.getEventoId()).build());

		EventoModel eventoModel = eventoMapper.entityToDto(eventoFinal, eventoRepository);

		Pubblicazione pubblicazione = setPubblicazione(
				redazioneGeneral, 
				StatoPubblicazione.of(ApplicationConstants.STATOPUBBLICAZIONE),
				chiamante,
				eventoModel,
				eventoFinal);

		pubblicazioneRepository.save(pubblicazione);


		VIPSchedaModel vipSchedaModel=entitySigeaToModelSchedaVIP(
				eventoFinal,
				tipologiaMibactFromCategorie,
				prodottiFromCategorie,
				dto,
				microesperienze
				);


		pubblicazione = setPubblicazione(
				redazioneVip, 
				StatoPubblicazione.of(ApplicationConstants.STATOPUBBLICAZIONE),
				chiamante,
				eventoModel,
				eventoFinal);

		ObjectMapper mapper = new ObjectMapper();
		String jsonMetadata = mapper.writeValueAsString(vipSchedaModel);
		pubblicazione.setGenericMetadataWip(mapper.readValue(jsonMetadata, JsonNode.class));
		pubblicazione.setGenericMetadata(mapper.readValue(jsonMetadata, JsonNode.class));
		pubblicazione.setNoteAggiuntive(null);

		Pubblicazione pubblicazioneVip = pubblicazioneRepository.save(pubblicazione);

		LogPubblicazioni logPubblicazioniRedVip = setLogPubblicazioni(statiLogPubblicazione, pubblicazioneVip);

		logPubblicazioneRepository.save(logPubblicazioniRedVip);


		logMigration=	
				logMapperService.setLogMigration(
						logMigration,
						eventoFinal,
						dto,
						sottoCategorieVipSet,
						prodottiVipSet,
						tipologiaMibactSigea,
						redazioneGeneral,
						redazioneVip,
						chiamante,
						prodottiSigea,
						utenteVip,
						ticketVip,
						ticketnoteintVipSet,
						scontoVipSet,
						evento,
						accessibilitaVip,
						animaleVipSet,
						disabiVipSet,
						servizioVipSet,
						spaziobambiniVipSet,
						eventoIntVipSet,
						eventoLocationVipSet,
						orarioVipSet,
						giorniChiusuraVipSet,
						eventoAllegatiVipSet,
						allegatiIntVipSet,
						categoriaEventoVipSet,
						microesperienze,
						jsonMetadata
						);

		try {
			logMigrationRepository.save(logMigration);
		}catch(Exception e ) {
			log.error("Errore scrittura tabella di log: "+e.getMessage());
		}

		return eventoFinal;  
	}



	private Set<Link> link2VipToLinkEsterniSigea(Evento evento, EventoEntity dto) {

		Set<Link> linkSet = new HashSet<Link>();
		Link link = null;

		if (dto.getLink1()!=null) {

			link = new Link();
			link.setLink(dto.getLink1());
			link.setTitolo(dto.getLink1());
			link.setDidascalia("");
			link.setDidascaliaMulti(ApplicationConstants.createMapMultiLanguage());

			Map<String, String> map1 = ApplicationConstants.createMapMultiLanguage();
			map1.put("IT", dto.getLink1());
			link.setTitoloMulti(map1);

			link.setEvento(evento);
			linkSet.add(link);
		}

		if (dto.getLink2()!=null) {

			link = new Link();
			link.setLink(dto.getLink2());
			link.setTitolo(dto.getLink2());
			link.setDidascaliaMulti(ApplicationConstants.createMapMultiLanguage());
			link.setDidascalia("");

			Map<String, String> map2 = ApplicationConstants.createMapMultiLanguage();
			map2.put("IT", dto.getLink2());
			link.setTitoloMulti(map2);

			link.setEvento(evento);
			linkSet.add(link);
		}


		return linkSet;
	}



	private Map<String, java.sql.Date> getExtremeDates(Evento evento) {

		Map<String,java.sql.Date> extremeDates = new HashMap<String,java.sql.Date>();

		List<java.util.Date> dataDaList = new ArrayList<java.util.Date>(); 
		List<Date> dataAList = new ArrayList<Date>();

		if (evento.getPeriodoSet()!=null) {
			for(Periodo periodo : evento.getPeriodoSet()) {
				if (periodo.getDataDa()!=null) {
					dataDaList.add(new java.util.Date(periodo.getDataDa().getTime()));
				}
				if (periodo.getDataA()!=null) {
					dataAList.add(new java.util.Date(periodo.getDataA().getTime()));
				}
			}
			dataDaList.sort(Comparator.naturalOrder());
			dataAList.sort(Comparator.naturalOrder());
			java.sql.Date dataDaMin = null;
			if (dataDaList != null && dataDaList.size() > 0 && dataDaList.get(0)!=null) {
				dataDaMin = new java.sql.Date(dataDaList.get(0).getTime());
			}
			java.sql.Date dataAMax = null;
			if (dataAList != null && dataAList.size() > 0 && dataAList.get(dataAList.size() - 1) !=null) {
				dataAMax = new java.sql.Date(dataAList.get(dataAList.size() - 1).getTime());
			}

			extremeDates.put("dataDaMin",dataDaMin );
			extremeDates.put("dataAMax",dataAMax );
		}	 

		return extremeDates;
	}



	private StringBuilder getOperazioniBuilder(Evento evento) {

		StringBuilder operazioniBuilder = new StringBuilder("");

		if (evento.getDatiGenerali()!=null)
			operazioniBuilder.append(messageRetriever.getDati_Generali()).append(", ");
		if (evento.getTicket()!=null)
			operazioniBuilder.append(messageRetriever.getTicket()).append(", ");
		if (evento.getImmagineSet()!=null)
			operazioniBuilder.append(messageRetriever.getImmagini()).append(", ");
		if (evento.getDocumentoSet()!=null)
			operazioniBuilder.append(messageRetriever.getDocumenti()).append(", ");
		if (evento.getContattoSet()!=null)
			operazioniBuilder.append(messageRetriever.getContatti()).append(", ");
		if (evento.getPeriodoSet()!=null)
			operazioniBuilder.append(messageRetriever.getPeriodi()).append(", ");
		if (evento.getStato()!=null)
			operazioniBuilder.append(messageRetriever.getStato()).append(", ");
		if (evento.getAccessibilita()!=null)
			operazioniBuilder.append(messageRetriever.getAccessibilita()).append(", ");
		if (evento.getLocationSet()!=null)
			operazioniBuilder.append(messageRetriever.getLocation()).append(", ");

		if( operazioniBuilder.length() > 0 )
			operazioniBuilder.deleteCharAt( operazioniBuilder.length() - 2 ); //togli spazio e virgola

		return operazioniBuilder;
	}



	Set<String> getProdottiFromCategorie(
			Set<CategoriaeventoEntity> prodottiVipSet,
			Set<CategoriaeventoEntity> categoriaEventoVipSet,
			List<Prodotto> prodottiSigea) {


		String prodottoSigea=null;
		Set<String> prodottiSet= new HashSet<String>();
		Map<String,String> mapProdottiVipVsSigea = ApplicationConstants.createMapProdotti();

		if (prodottiVipSet==null)
			System.out.println("non ci sono prodotti in vip: GRAVE!!");

		if (categoriaEventoVipSet!=null) {
			for (CategoriaeventoEntity categoriaEvento: categoriaEventoVipSet){
				for (CategoriaeventoEntity prodottoVip: prodottiVipSet){ 
					if(categoriaEvento.getIdparent()==null &&
							!(mapProdottiVipVsSigea.get(categoriaEvento.getIdcategoria().toString()).isEmpty()) &&
							categoriaEvento.getIdcategoria()==prodottoVip.getIdcategoria()){
						prodottoSigea= "";
						prodottoSigea=prodottiSigea.get( 
								Integer.valueOf(
										mapProdottiVipVsSigea.get(
												categoriaEvento.getIdcategoria().toString()
												)
										)											
								- 1
								).getProdotto() ;
						prodottiSet.add(prodottoSigea);
					}

				}
			}
		}
		/*	IN PRODUZIONE GLI EVENTI PUBBLICATI HANNO TUTTI UN PRODOTTO ASSOCIATO. 
		 * 	IN COLLAUDO C'E' QUALCHE ECCEZIONE
		 */		 
		if (prodottiSet.isEmpty()) {
			log.error("prodottoVip is empty");
			prodottoSigea= "";
			prodottoSigea="Intrattenimento";
			prodottiSet.add(prodottoSigea);	
		}

		return prodottiSet;

	}



	Set<DocumentoEvento> placeholderDocumentiSigea(Evento evento, Set<EventoallegatiintEntity> allegatiIntVipSet,
			List<EventoallegatiEntity> eventoAllegatiVipSet) {

		if ( eventoAllegatiVipSet == null ) {
			return null;
		}

		Set<DocumentoEvento> set = new HashSet<DocumentoEvento>( Math.max( (int) ( eventoAllegatiVipSet.size() / .75f ) + 1, 16 ) );

		for ( EventoallegatiEntity eventoallegati : eventoAllegatiVipSet ) {
			if (!(eventoallegati.getTipologia().equalsIgnoreCase(ApplicationConstants.IMMAGINE))){

				DocumentoEvento documentoEvento = new DocumentoEvento();

				documentoEvento.setEvento			(evento);
				documentoEvento.setNomeOriginale	(eventoallegati.getNomeoriginale());
				documentoEvento.setEstensione		(eventoallegati.getEstensione() );
				documentoEvento.setDimensione		(eventoallegati.getPeso() );

				//placeholder per salvare l'informazione old dell'id allegato.
				//verrà cancellato nella function successiva
				documentoEvento.setPathToCopy( String.valueOf(eventoallegati.getIdallegato()));

				if (allegatiIntVipSet!=null) {	
					Map<String, String> map = ApplicationConstants.createMapMultiLanguage();
					for(EventoallegatiintEntity eventoallegatiint : allegatiIntVipSet ){

						if (eventoallegatiint.getIdallegato()==eventoallegati.getIdallegato()){
							map.put(getLanguage(eventoallegatiint.getIdlingua()), nullToEmpty(eventoallegatiint.getDescrizione()));
						}
					}
					documentoEvento.setTitolo( getItaFromMultiMap( map));
					documentoEvento.setTitoloMulti( new HashMap<String, String>( map ) );
				}
				set.add( documentoEvento  );
			}
		}
		return set;
	}



	Set<Immagine> placeholderImmaginiSigea(Evento evento, Set<EventoallegatiintEntity> allegatiIntVipSet,
			List<EventoallegatiEntity> eventoAllegatiVipSet) {

		if ( eventoAllegatiVipSet == null ) {
			return null;
		}

		Set<Immagine> set = new HashSet<Immagine>( Math.max( (int) ( eventoAllegatiVipSet.size() / .75f ) + 1, 16 ) );


		for ( EventoallegatiEntity eventoallegati : eventoAllegatiVipSet ) {

			if (eventoallegati.getTipologia().equalsIgnoreCase(ApplicationConstants.IMMAGINE)){

				Immagine immagineEvento = new Immagine();
				boolean isBanner=false;

				//placeholder per salvare l'informazione se l'immagine è banner o meno.
				//verrà cancellato nella function successiva
				if (eventoallegati.getCategoria()!=null && eventoallegati.getCategoria().equalsIgnoreCase(ApplicationConstants.BANNER))
					isBanner=true;


				immagineEvento.setEvento		(evento);
				immagineEvento.setNomeOriginale	(eventoallegati.getNomeoriginale() );
				immagineEvento.setEstensione	(eventoallegati.getEstensione() );
				immagineEvento.setDimensione	(eventoallegati.getPeso());

				//placeholder per salvare l'informazione old dell'id allegato e se è una immagine di banner.
				//verrà cancellato nella function successiva
				immagineEvento.setPathToCopy    ( String.valueOf(eventoallegati.getIdallegato()) +"|"+ isBanner ); 


				if (allegatiIntVipSet!=null) {	
					Map<String, String> map = ApplicationConstants.createMapMultiLanguage();
					for(EventoallegatiintEntity eventoallegatiint : allegatiIntVipSet ){

						if (eventoallegatiint.getIdallegato()==eventoallegati.getIdallegato()){
							map.put(getLanguage(eventoallegatiint.getIdlingua()), nullToEmpty(eventoallegatiint.getDescrizione()));
						}
					}

					//come richiesto post collaudo 11/05
					immagineEvento.setCredits(null);

					immagineEvento.setDidascalia( nullToEmpty(getItaFromMultiMap( map)));
					immagineEvento.setDidascaliaMulti( new HashMap<String, String>( map ) );
				}

				set.add( immagineEvento  );
			}
		}
		return set;
	}




	Set<Contatto> contattiVipToContattiSigea(EventoEntity dto, Evento evento) {

		Set<Contatto> contattoSet = new HashSet<Contatto>();
		Contatto contatto = null;

		if (dto.getTel()!=null) {
			contatto = new Contatto();
			contatto.setContatto(dto.getTel());
			contatto.setTipo(TelefonoModel.tipo);
			contatto.setEvento(evento);
			contattoSet.add(contatto);
		}


		if (dto.getEmail()!=null) {	
			contatto = new Contatto();
			contatto.setContatto(dto.getEmail());
			contatto.setTipo(EmailModel.tipo);
			contatto.setEvento(evento);
			contattoSet.add(contatto);
		}

		if (dto.getWeb()!=null) {
			contatto = new Contatto();
			contatto.setContatto(dto.getWeb());
			contatto.setTipo(SitoModel.tipo);
			contatto.setEvento(evento);
			contattoSet.add(contatto);
		}

		return contattoSet;
	}




	Set<LogEvento> logVipToSigea(
			Evento evento, DettaglioUtente chiamante, 
			StringBuilder operazioniBuilder) {

		if ( operazioniBuilder == null ) {
			return null;
		}

		Set<LogEvento> set = new HashSet<LogEvento>( Math.max( (int) ( 2 / .75f ) + 1, 16 ) );

		LogEvento logEvento = new LogEvento();
		logEvento.setTipoOperazione( ApplicationConstants.INSERIMENTO );
		logEvento.setDataModifica( new Timestamp(System.currentTimeMillis()) );
		logEvento.setNomeUtente(chiamante.getNome() + " " + chiamante.getCognome() );
		logEvento.setOperazioni( messageRetriever.getCreazione_Evento());
		logEvento.setDescrizioneStato(ApplicationConstants.BOZZA);
		logEvento.setEvento(evento);
		set.add(logEvento);

		logEvento=null;
		logEvento = new LogEvento();
		logEvento.setTipoOperazione( ApplicationConstants.MODIFICA );
		logEvento.setDataModifica( new Timestamp(System.currentTimeMillis()) );
		logEvento.setNomeUtente(chiamante.getNome() + " " + chiamante.getCognome() );
		logEvento.setOperazioni( operazioniBuilder.toString() );
		logEvento.setDescrizioneStato( ApplicationConstants.VALIDATO );
		logEvento.setEvento(evento);
		set.add(logEvento);

		return set;
	}




	Set<Periodo> periodoVipToPeriodoSigea(
			Evento evento, Set<OrarioEntity> orarioVipSet,
			Set<GiornochiusuraEntity> giorniChiusuraVipSet) throws Exception {

		if ( orarioVipSet == null || orarioVipSet.isEmpty() ) {
			throw new Exception("orarioVipSet nullo o vuoto");
		}

		Set<Periodo> set = new HashSet<Periodo>( Math.max( (int) ( orarioVipSet.size() / .75f ) + 1, 16 ) );

		for ( OrarioEntity orario : orarioVipSet ) {

			Periodo periodo = new Periodo();

			periodo.setFgContinuato( (orario.getContinuato()==null || orario.getContinuato().equalsIgnoreCase(ApplicationConstants.SI)) ? true :
				(orario.getContinuato().equalsIgnoreCase(ApplicationConstants.NO)) ? false : 
					null );
			periodo.setDataDa(orario.getDatainizio());
			periodo.setDataA( orario.getDatafine() );
			periodo.setDataSecca( (orario.getDatainizio()==orario.getDatafine()) ? true :false );
			periodo.setChiusuraDom(false);
			periodo.setChiusuraLun(false);
			periodo.setChiusuraMar(false);
			periodo.setChiusuraMer(false);
			periodo.setChiusuraGio(false);
			periodo.setChiusuraVen(false);
			periodo.setChiusuraSab(false);

			//LA TABELLA ORARIO CADENZA IN VIP NON VIENE UTILIZZATA
			//INOLTRE, COME EREDITATO, SIGEA NON PASSA INFORMAZIONI A VIP SULLA CADENZA
			periodo.setCadenza(ApplicationConstants.NESSUNA); 
			periodo.setCadenzaDom(false);
			periodo.setCadenzaLun(false);
			periodo.setCadenzaMar(false);
			periodo.setCadenzaMer(false);
			periodo.setCadenzaGio(false);
			periodo.setCadenzaVen(false);
			periodo.setCadenzaSab(false);
			periodo.getCadenzaMensile().clear();
			periodo.setCadenzaMensile( new HashSet<String>() );

			periodo.setOrarioApertura(null);
			periodo.setOrarioChiusura(null);
			periodo.setOrarioAperturaMattina(null);
			periodo.setOrarioChiusuraMattina(null);
			periodo.setOrarioAperturaPomeriggio(null);
			periodo.setOrarioChiusuraPomeriggio(null);


			if (periodo.getFgContinuato()) {
				String orarioApertura=(orario.getContinuatodahh()!=null) ? (orario.getDatainizio() + " " + orario.getContinuatodahh() + nullToEmptyPeriod(orario.getContinuatodamm())) : null;
				String orarioChiusura=verifyDate(
						orario.getContinuatodahh(),
						orario.getContinuatodamm(),
						orario.getDatainizio(),
						orario.getDatafine(),
						orario.getContinuatoahh(),
						orario.getContinuatoamm(),
						"C");

				periodo.setOrarioApertura(orarioApertura);
				periodo.setOrarioChiusura(orarioChiusura);				

			}else if (!(periodo.getFgContinuato())){
				String orarioAperturaMattutina=		(orario.getMattinadahh()!=null) ? 		(orario.getDatainizio() + " " + orario.getMattinadahh() + 		nullToEmptyPeriod(orario.getMattinadamm())) : null;
				String orarioAperturaPomeridiana=	(orario.getPomeriggiodahh()!=null) ? 	(orario.getDatainizio() + " " + orario.getPomeriggiodahh() + 	nullToEmptyPeriod(orario.getPomeriggiodamm())) : null;		
				String orarioChiusuraMattutina=		(orario.getMattinaahh()!=null) ? 		(orario.getDatafine() + " " + orario.getMattinaahh() + 		nullToEmptyPeriod(orario.getMattinaamm())) : null;	

				String orarioChiusuraPomeridiana=verifyDate(
						orario.getPomeriggiodahh(),
						orario.getPomeriggiodamm(),
						orario.getDatainizio(),
						orario.getDatafine(),
						orario.getPomeriggioahh(),
						orario.getPomeriggioamm(),
						"P");

				periodo.setOrarioAperturaMattina(orarioAperturaMattutina);
				periodo.setOrarioChiusuraMattina(orarioChiusuraMattutina);
				periodo.setOrarioAperturaPomeriggio(orarioAperturaPomeridiana);
				periodo.setOrarioChiusuraPomeriggio(orarioChiusuraPomeridiana);
			}


			if(!periodo.getDataSecca()) {
				for(GiornochiusuraEntity giorniChiusura: giorniChiusuraVipSet){
					if (orario.getIdorario()==giorniChiusura.getIdorario()){
						if (giorniChiusura.getIdgiornosettimana()==ApplicationConstants.DOM)
							periodo.setChiusuraDom(true);
						if (giorniChiusura.getIdgiornosettimana()==ApplicationConstants.LUN) 
							periodo.setChiusuraLun(true);						
						if (giorniChiusura.getIdgiornosettimana()==ApplicationConstants.MAR) 
							periodo.setChiusuraMar(true);						
						if (giorniChiusura.getIdgiornosettimana()==ApplicationConstants.MER) 
							periodo.setChiusuraMer(true);						
						if (giorniChiusura.getIdgiornosettimana()==ApplicationConstants.GIO) 
							periodo.setChiusuraGio(true);						
						if (giorniChiusura.getIdgiornosettimana()==ApplicationConstants.VEN) 
							periodo.setChiusuraVen(true);						
						if (giorniChiusura.getIdgiornosettimana()==ApplicationConstants.SAB) 
							periodo.setChiusuraSab(true);						
					}
				}
			}
			periodo.setEvento(evento);
			set.add(  periodo );
		}

		return set;
	}



	private String verifyDate(String dahh, String damm, java.sql.Date dataInizio,java.sql.Date dataFine, String ahh, String amm, String fascia) {

		String orarioChiusura=(ahh!=null) 
				? (dataFine + " " + ahh + nullToEmptyPeriod(amm)): null;

				//GESTISCI CASO PARTICOLARE DI ORARIO IN DATA SUCCESSIVA
				try {

					LocalTime timeDa = LocalTime.parse(dahh+":"+damm);		
					LocalTime timeA =  LocalTime.parse(ahh+	":"+ahh);	

					if( timeA.isBefore(timeDa)) {	
						orarioChiusura= dataFine.toLocalDate().plusDays(1) + " " + ahh + nullToEmptyPeriod(amm);			
					}
				}catch (Exception e){
					log.debug(fascia +" Eccezione, orarioChiusura senza valutazione data successiva");
					return orarioChiusura;
				}
				return orarioChiusura;
	}



	Set<DocumentoEvento> documentiVipToDocumentiSigea(
			Evento evento, Set<EventoallegatiintEntity> allegatiIntVipSet, 
			List<EventoallegatiEntity> eventoAllegatiVipSet, LogMigration logMigration) {

		if ( eventoAllegatiVipSet == null ) {
			return null;
		}

		Set<DocumentoEvento> set = new HashSet<DocumentoEvento>( Math.max( (int) ( eventoAllegatiVipSet.size() / .75f ) + 1, 16 ) );
		String downloadedFile="";
		for(DocumentoEvento docSigea : evento.getDocumentoSet()) {

			long allegatoOld=Long.valueOf(docSigea.getPathToCopy());

			String result = downloadAndSaveAttachedFile(null, allegatoOld, docSigea);
			downloadedFile = downloadedFile + result + ApplicationConstants.DASHSPACE;
			log.debug(result);

			docSigea.setPathToCopy( null ); 
			set.add( docSigea  );
		}

		logMigration.setAllegatiVip2Sigea( nullToEmpty(logMigration.getAllegatiVip2Sigea()) + downloadedFile);
		return set;
	}


	Set<Immagine> immaginiVipToImmaginiSigea(
			Evento evento, Set<EventoallegatiintEntity> allegatiIntVipSet, 
			List<EventoallegatiEntity> eventoAllegatiVipSet, LogMigration logMigration) {

		if ( eventoAllegatiVipSet == null ) {
			return null;
		}

		Set<Immagine> set = new HashSet<Immagine>( Math.max( (int) ( eventoAllegatiVipSet.size() / .75f ) + 1, 16 ) );
		String downloadedFile="";

		for(Immagine imgSigea : evento.getImmagineSet()) {

			//splitta il placeholder per recuperare idallegato old e il banner. dopodiché setta a null. 
			//Il pathtocopy serve solo in caso di funzione duplica di Sigea
			String pathcopy=imgSigea.getPathToCopy();
			String[] parts = pathcopy.split("\\|");
			imgSigea.setPathToCopy( null ); 

			long allegatoOld=Long.valueOf(parts[0]);
			boolean isBanner=Boolean.parseBoolean(parts[1]);

			if(isBanner)
				IDsSIGEABANNER.add(imgSigea.getImmagineId());

			String result =downloadAndSaveAttachedFile(imgSigea, allegatoOld, null);
			downloadedFile = downloadedFile + result;
			log.debug(result);

			set.add( imgSigea  );		
		}
		logMigration.setAllegatiVip2Sigea( nullToEmpty(logMigration.getAllegatiVip2Sigea()) + downloadedFile);
		return set;
	}



	Set<Location> locationVipToLocationSigea(
			Evento evento, Set<EventolocationEntity> eventoLocationVipSet, 
			Set<AttrattoreIntEntity> attrattoreVipSet, List<ComuneRecord> comuneRecordList) throws Exception {

		if ( eventoLocationVipSet == null )/*|| eventoLocationVipSet.isEmpty() )*/{
			//throw new Exception("eventoLocationVipSet nullo o vuoto");
			eventoLocationVipSet= new HashSet<EventolocationEntity>();
		}

		Set<Location> set = new HashSet<Location>( Math.max( (int) ( eventoLocationVipSet.size() / .75f ) + 1, 16 ) );

		Set<Attrattore> attrattoreSet =  null;
		Map<String, String> codiciArea=ApplicationConstants.createMapCodArea();
		Map<String, String> descrizioniArea=ApplicationConstants.createMapDescArea();

		boolean fgPrincipale = true;
		BigDecimal latitudine=null;
		BigDecimal longitudine=null;

		if (eventoLocationVipSet.isEmpty())
		{
			Location location = new Location();
			location.setCodNazione( ApplicationConstants.CODICENAZIONE );
			location.setCodRegione( ApplicationConstants.CODREGIONE );
			location.setFgPrincipale(fgPrincipale);
			location.setNazione( ApplicationConstants.NOMENAZIONE );
			location.setRegione( ApplicationConstants.NOMEREGIONE );
			location.setPuglia		(true);
			location.setEvento(evento);

			set.add(location);

			return set;


		}
		for ( EventolocationEntity eventolocationEntity : eventoLocationVipSet ) {

			Location location = new Location();

			if (eventolocationEntity.getLatitudine()!=null) 
				latitudine= new BigDecimal(eventolocationEntity.getLatitudine().trim().replace(",", "."));
			if (eventolocationEntity.getLongitudine()!=null) 
				longitudine= new BigDecimal(eventolocationEntity.getLongitudine().trim().replace(",", "."));
			if (eventolocationEntity.getIdprovincia()!=null && !eventolocationEntity.getIdprovincia().equalsIgnoreCase("EX")){

				ComuneRiferimentoEntity comuneRiferimento = 		comuneRiferimentoEntityRepository.findByIdComuneRiferimento(eventolocationEntity.getIdcomune());

				//fusione tra comuni. Inserisco nuovo codice istat
				if (comuneRiferimento.getCodiceIstat().equalsIgnoreCase("075062") || 
						comuneRiferimento.getCodiceIstat().equalsIgnoreCase("075001") ) {
					comuneRiferimento.setCodiceIstat("075098");
				}


				AmbitiTerritorialiEntity ambitiTerritoriali= 		ambitiTerritorialiEntityRepository.findByIdComuneSirtur(eventolocationEntity.getIdcomune());

				ComuneRecord comuneRecord= comuneRecordList.stream().filter(rec -> rec.getMunicipalityCode().equalsIgnoreCase(comuneRiferimento.getCodiceIstat())).findFirst().orElse(null);

				location.setPuglia( true );
				location.setCodNazione( ApplicationConstants.CODICENAZIONE );
				location.setNazione( ApplicationConstants.NOMENAZIONE );
				location.setCodComune(comuneRecord.getMunicipalityCode());																							//	comuneRecord.getMunicipalityCode();
				location.setComune(comuneRecord.getMunicipalityName()); 
				location.setCodProvincia( comuneRecord.getProvinceCode() );
				location.setProvincia( comuneRecord.getProvinceName() );
				location.setCodArea(codiciArea.get(ambitiTerritoriali.getIdTerritorio()));
				location.setArea(descrizioniArea.get(ambitiTerritoriali.getIdTerritorio()));

				location.setCodRegione( ApplicationConstants.CODREGIONE );
				location.setRegione( ApplicationConstants.NOMEREGIONE );
				location.setCap( eventolocationEntity.getCap());
				location.setFgPrincipale(fgPrincipale);
				location.setIndirizzo( (eventolocationEntity.getNome()!=null) ? eventolocationEntity.getNome() : "n/a");
				location.setLatitudine( latitudine );
				location.setLongitudine( longitudine );
				location.setNomeLocation( eventolocationEntity.getIndirizzo());
				location.setLink(null);
				location.setEvento(evento);
				//In vip esiste cmq una sola location per evento/rassegna
				fgPrincipale=false;
			}else{ 
				//LASCIATO NEL CASO IN CUI SFUGGE QUALCOSA. NON ARRIVA QUI
				location.setCodNazione( ApplicationConstants.CODICENAZIONE );
				location.setCodRegione( ApplicationConstants.CODREGIONE );
				location.setFgPrincipale(fgPrincipale);
				location.setNazione( ApplicationConstants.NOMENAZIONE );
				location.setRegione( ApplicationConstants.NOMEREGIONE );
				location.setPuglia		(true);
				location.setEvento(evento);
			}

			if (attrattoreVipSet!=null) {
				attrattoreSet = new HashSet<Attrattore>( Math.max( (int) ( attrattoreVipSet.size() / .75f ) + 1, 16 ) );

				for ( AttrattoreIntEntity attrattoreVip : attrattoreVipSet ) {

					Attrattore attrattore = new Attrattore();
					attrattore.setAttrattoreId( attrattoreVip.getIdAttrattore() );
					attrattore.setEtichetta( attrattoreVip.getDenominazione() );

					attrattoreSet.add(attrattore);
				}
			}

			location.setAttrattoriSet(attrattoreSet);
			set.add(location);
		}


		return set;
	}


	Attivita attivitaVipToAttivitaSigea() {

		Attivita attivita = new Attivita();
		attivita.setAttivitaId(attivitaIdV);
		attivita.setDenominazione(attivitaDenominazioneV);

		return attivita;
	}


	Accessibilita accessibilitaVipToAccessibilitaSigea(
			Evento evento,
			AccessibilitaEntity accessibilitaVip,
			Set<AccessibilitaanimaleEntity> animaleVipSet, 
			Set<AccessibilitadisabilitaEntity> disabiVipSet,
			Set<AccessibilitaservizioEntity> servizioVipSet,
			Set<AccessibilitaspaziobambiniEntity> spaziobambiniVipSet) {


		Accessibilita accessibilita = new Accessibilita();

		accessibilita.setFlagFamiglieBambini( false );
		accessibilita.setFlagPersoneAnimali( false );
		accessibilita.setFlagDisabilitaFisica( false );
		accessibilita.setFlagDisabilitaVisiva( false );
		accessibilita.setFlagDisabilitaUditiva( false );
		accessibilita.setFlagGravidanza( false );
		accessibilita.setFlagAnziani( false );
		accessibilita.setFlagCaniMedi( false );
		accessibilita.setFlagCaniPiccoli( false );
		accessibilita.setFlagCani( false );
		accessibilita.setFlagBiblioteca( false );
		accessibilita.setFlagGiardini( false );
		accessibilita.setFlagLudoteca( false);
		accessibilita.setFlagNursey( false );
		accessibilita.setFlagParcogiochi( false );
		accessibilita.setFlagPercorsi( false );
		accessibilita.setFlagInfopoint( false );
		accessibilita.setFlagServiziIgienici( false );
		accessibilita.setFlagParcheggioDisabili( false );
		accessibilita.setFlagIngressi( false );
		accessibilita.setFlagPercorsiTattili( false );
		accessibilita.setFlagGuideBraille( false );
		accessibilita.setFlagSegnaleticaBraille( false );
		accessibilita.setFlagSegnaleticaLeggibile( false );
		accessibilita.setFlagMaterialeLeggibile( false );
		accessibilita.setFlagPostazioniAudio( false );
		accessibilita.setFlagAudioguide( false );
		accessibilita.setFlagAudioguidePercorsi( false );
		accessibilita.setFlagMaterialeSottotitolato( false );
		accessibilita.setFlagRiproduzioneTattili( false );
		accessibilita.setFlagComputer( false );
		accessibilita.setFlagLis( false );
		accessibilita.setFlagPercorsi( false );
		accessibilita.setEvento( evento );

		if ( accessibilitaVip == null ) {
			return accessibilita;
		}

		accessibilita.setFlagPercorsi((accessibilitaVip.getFlpercorsoacc()!=null && accessibilitaVip.getFlpercorsoacc()) ? true : false);

		for (AccessibilitaservizioEntity accessibilitaservizio: servizioVipSet) {
			if (accessibilitaservizio.getIdservizioacc()==ApplicationConstants.infoPoint) 
				accessibilita.setFlagInfopoint(true);
			if (accessibilitaservizio.getIdservizioacc()==ApplicationConstants.serviziIgienici) 
				accessibilita.setFlagServiziIgienici( true );
			if (accessibilitaservizio.getIdservizioacc()==ApplicationConstants.parcheggioDisabili) 
				accessibilita.setFlagParcheggioDisabili( true );
			if (accessibilitaservizio.getIdservizioacc()==ApplicationConstants.ingressi) 
				accessibilita.setFlagIngressi( true );
			if (accessibilitaservizio.getIdservizioacc()==ApplicationConstants.percorsiTattili) 
				accessibilita.setFlagPercorsiTattili( true);
			if (accessibilitaservizio.getIdservizioacc()==ApplicationConstants.guideBraille) 
				accessibilita.setFlagGuideBraille( true );
			if (accessibilitaservizio.getIdservizioacc()==ApplicationConstants.segnaleticaBraille) 
				accessibilita.setFlagSegnaleticaBraille( true);
			if (accessibilitaservizio.getIdservizioacc()==ApplicationConstants.segnaleticaLeggibile) 
				accessibilita.setFlagSegnaleticaLeggibile( true );
			if (accessibilitaservizio.getIdservizioacc()==ApplicationConstants.materialeLeggibile) 
				accessibilita.setFlagMaterialeLeggibile( true);
			if (accessibilitaservizio.getIdservizioacc()==ApplicationConstants.postazioniaudio) 
				accessibilita.setFlagPostazioniAudio( true );
			if (accessibilitaservizio.getIdservizioacc()==ApplicationConstants.audioguideipovedenti) 
				accessibilita.setFlagAudioguide( true );
			if (accessibilitaservizio.getIdservizioacc()==ApplicationConstants.audioguidetattili) 
				accessibilita.setFlagAudioguidePercorsi( true );
			if (accessibilitaservizio.getIdservizioacc()==ApplicationConstants.materialesottotitolato) 
				accessibilita.setFlagMaterialeSottotitolato( true );
			if (accessibilitaservizio.getIdservizioacc()==ApplicationConstants.riproduzionitattili) 
				accessibilita.setFlagRiproduzioneTattili( true );
			if (accessibilitaservizio.getIdservizioacc()==ApplicationConstants.computerdescrizionivocali) 
				accessibilita.setFlagComputer( true );
			if (accessibilitaservizio.getIdservizioacc()==ApplicationConstants.personalelinguasegni) 
				accessibilita.setFlagLis( true );
		}


		for (AccessibilitadisabilitaEntity accessibilitadisabilita: disabiVipSet) {
			if (accessibilitadisabilita.getIddisabilita().equalsIgnoreCase(ApplicationConstants.DISAB_UDITIVA))
				accessibilita.setFlagDisabilitaUditiva( true );
			if (accessibilitadisabilita.getIddisabilita().equalsIgnoreCase(ApplicationConstants.BAMBINI))
				accessibilita.setFlagFamiglieBambini( true );
			if (accessibilitadisabilita.getIddisabilita().equalsIgnoreCase(ApplicationConstants.DISAB_VISIVA))
				accessibilita.setFlagDisabilitaVisiva( true);
			if (accessibilitadisabilita.getIddisabilita().equalsIgnoreCase(ApplicationConstants.ANIMALI))
				accessibilita.setFlagPersoneAnimali( true );
			if (accessibilitadisabilita.getIddisabilita().equalsIgnoreCase(ApplicationConstants.DISAB_FISICA))
				accessibilita.setFlagDisabilitaFisica( true );
			if (accessibilitadisabilita.getIddisabilita().equalsIgnoreCase(ApplicationConstants.GRAVIDANZA))
				accessibilita.setFlagGravidanza( true );
			if (accessibilitadisabilita.getIddisabilita().equalsIgnoreCase(ApplicationConstants.ANZIANI))
				accessibilita.setFlagAnziani( true );
		}

		for (AccessibilitaspaziobambiniEntity accessibilitaspaziobambini: spaziobambiniVipSet) {

			if (accessibilitaspaziobambini.getIdspazio().equalsIgnoreCase(ApplicationConstants.BIBLIOT_BAMBINI))
				accessibilita.setFlagBiblioteca( true);
			if (accessibilitaspaziobambini.getIdspazio().equalsIgnoreCase(ApplicationConstants.GIARDINI))
				accessibilita.setFlagGiardini( true );
			if (accessibilitaspaziobambini.getIdspazio().equalsIgnoreCase(ApplicationConstants.LUDOTECA))
				accessibilita.setFlagLudoteca( true );
			if (accessibilitaspaziobambini.getIdspazio().equalsIgnoreCase(ApplicationConstants.NURSERY))
				accessibilita.setFlagNursey( true);
			if (accessibilitaspaziobambini.getIdspazio().equalsIgnoreCase(ApplicationConstants.PARCO_GIOCHI))
				accessibilita.setFlagParcogiochi( true );
		}

		for (AccessibilitaanimaleEntity accessibilitaanimale: animaleVipSet) {

			if (accessibilitaanimale.getIdtipoanimale().equalsIgnoreCase(ApplicationConstants.TUTTI_CANI))
				accessibilita.setFlagCaniMedi( true);
			if (accessibilitaanimale.getIdtipoanimale().equalsIgnoreCase(ApplicationConstants.CANI_TAGLIA_MEDIA))
				accessibilita.setFlagCaniPiccoli( true );
			if (accessibilitaanimale.getIdtipoanimale().equalsIgnoreCase(ApplicationConstants.CANI_TAGLIA_PICC))
				accessibilita.setFlagCani( true );
		}

		return accessibilita;
	}




	DatiGenerali datiGeneraliVipToDatiGeneraliSigea(
			Evento evento, 
			Set<EventointEntity> eventoIntVipSet) {

		if ( eventoIntVipSet == null ) {
			return null;
		}

		DatiGenerali datiGenerali = new DatiGenerali();

		Map<String, String> mapAbstract = ApplicationConstants.createMapMultiLanguage();
		Map<String, String> mapDescrizione = ApplicationConstants.createMapMultiLanguage();
		Map<String, String> mapTitolo = ApplicationConstants.createMapMultiLanguage();
		Map<String, String> mapSnippet = ApplicationConstants.createMapMultiLanguage();

		for(EventointEntity eventoint: eventoIntVipSet) {

			mapAbstract.put		(	getLanguage(eventoint.getIdLingua()), nullToEmpty(eventoint.getFieldAbstract()));
			mapDescrizione.put	(	getLanguage(eventoint.getIdLingua()), nullToEmpty(eventoint.getDescrizione()));
			mapTitolo.put		(	getLanguage(eventoint.getIdLingua()), nullToEmpty(eventoint.getNome() ));
		}

		datiGenerali.setAbstractDescr( getItaFromMultiMap(mapAbstract) );
		datiGenerali.setAbstractDescrMulti( new HashMap<String, String>( mapAbstract ) );
		datiGenerali.setDescrizione( getItaFromMultiMap( mapDescrizione) );
		datiGenerali.setDescrizioneMulti( new HashMap<String, String>( mapDescrizione ) );
		datiGenerali.setTitolo( getItaFromMultiMap( mapTitolo ) );
		datiGenerali.setTitoloMulti( new HashMap<String, String>( mapTitolo ) );
		datiGenerali.setSnippet("");
		datiGenerali.setSnippetMulti( new HashMap<String, String>( mapSnippet ) );
		datiGenerali.setEvento(evento);

		return datiGenerali;
	}




	Ticket ticketVipToTicketSigea(
			Evento evento, 
			String tipoTicket, 
			TicketEntity ticketVip, 
			Set<TicketnoteintEntity> ticketnoteintVipSet,
			Set<ScontoEntity> scontoVipSet) {

		Ticket ticket = new Ticket();
		Map<String, String> map = ApplicationConstants.createMapMultiLanguage();

		BigDecimal prezzoIntero=null;
		BigDecimal prezzoRidotto=null;
		BigDecimal gruppiRidotto=null;


		ticket.setFlagGratisAnziani( false);
		ticket.setFlagGratisBambini( false);
		ticket.setFlagGratisConvenzioni( false );
		ticket.setFlagGratisDisabili( false );
		ticket.setFlagGratisAccompagnatori( false);
		ticket.setFlagRidottoAnziani( false );
		ticket.setFlagRidottoBambini( false );
		ticket.setFlagRidottoConvenzioni( false);
		ticket.setFlagRidottoDisabili( false);
		ticket.setFlagRidottoAccompagnatori( false);
		ticket.setTipoTicket(checkTipoTicket(tipoTicket,ticketVip));
		ticket.setEvento( evento );
		ticket.setConvenzioniAttiveG(null);


		ticket.setNota("");
		ticket.setNotaMulti( new HashMap<String, String>( map ) );

		if ( ticketVip == null) {
			return ticket;
		}	

		ticket.setConvenzioniAttiveR(ticketVip.getConvenzioniattive());

		if (ticketVip.getPrezzointero()!=null) 
			prezzoIntero=BigDecimal.valueOf(ticketVip.getPrezzointero());
		if (ticketVip.getPrezzoridotto()!=null) 
			prezzoRidotto=BigDecimal.valueOf(ticketVip.getPrezzoridotto());
		if (ticketVip.getPrezzogruppo()!=null) 
			gruppiRidotto=BigDecimal.valueOf(ticketVip.getPrezzogruppo());


		if (ticket.getTipoTicket().equalsIgnoreCase(ApplicationConstants.PAGAMENTO))
		{	
			ticket.setLinkPrenotazioni( ticketVip.getLinkprenotazione() );
			ticket.setTicketIntero(prezzoIntero);
			ticket.setTicketRidotto(prezzoRidotto);
			ticket.setTicketGruppiRidotto(gruppiRidotto);
			ticket.setTicketGruppiNumero(ticketVip.getNumerominimoriduzione());
		}



		if (ticketnoteintVipSet!=null)
		{
			for(TicketnoteintEntity ticketnoteint: ticketnoteintVipSet) {
				map.put(getLanguage(ticketnoteint.getIdlingua()),nullToEmpty(ticketnoteint.getNote()));
			}
			ticket.setNota(nullToEmpty(getItaFromMultiMap(map)) );
			ticket.setNotaMulti( new HashMap<String, String>( map ) );
		}	

		if (scontoVipSet!=null) {
			for (ScontoEntity  scontoVip: scontoVipSet) {

				if (scontoVip.getIdTipologia().equalsIgnoreCase(ApplicationConstants.GRATIS)){
					if (scontoVip.getIdCausaleSconto().equalsIgnoreCase(ApplicationConstants.ANZIANI))
						ticket.setFlagGratisAnziani( true );
					if (scontoVip.getIdCausaleSconto().equalsIgnoreCase(ApplicationConstants.BAMBINI))
						ticket.setFlagGratisBambini( true );
					if (scontoVip.getIdCausaleSconto().equalsIgnoreCase(ApplicationConstants.CONVENZIONI))
						ticket.setFlagGratisConvenzioni( true );
					if (scontoVip.getIdCausaleSconto().equalsIgnoreCase(ApplicationConstants.DISABILI))
						ticket.setFlagGratisDisabili( true );
					if (scontoVip.getIdCausaleSconto().equalsIgnoreCase(ApplicationConstants.ACCOMPAGNATORI))
						ticket.setFlagGratisAccompagnatori( true );
				}else if (scontoVip.getIdTipologia().equalsIgnoreCase(ApplicationConstants.RIDOTTO)){
					if (scontoVip.getIdCausaleSconto().equalsIgnoreCase(ApplicationConstants.ANZIANI))
						ticket.setFlagRidottoAnziani( true );
					if (scontoVip.getIdCausaleSconto().equalsIgnoreCase(ApplicationConstants.BAMBINI))
						ticket.setFlagRidottoBambini( true );
					if (scontoVip.getIdCausaleSconto().equalsIgnoreCase(ApplicationConstants.CONVENZIONI))
						ticket.setFlagRidottoConvenzioni( true );
					if (scontoVip.getIdCausaleSconto().equalsIgnoreCase(ApplicationConstants.DISABILI))
						ticket.setFlagRidottoDisabili( true );
					if (scontoVip.getIdCausaleSconto().equalsIgnoreCase(ApplicationConstants.ACCOMPAGNATORI))
						ticket.setFlagRidottoAccompagnatori(  true );
				}    
			}
		}
		return ticket;
	}



	Map<String, String> getTipologiaMibactFromCategorie(
			Set<CategoriaeventoEntity> sottoCategorieVipSet,
			Set<CategoriaeventoEntity> categoriaEventoVipSet, 
			List<TipologiaMIBACT> tipologiaMibactSigea){

		Map<String, String> tipologiaMibactMap= new HashMap<String, String>();


		if (tipologiaMibactSigea==null || tipologiaMibactSigea.isEmpty())
			System.out.println("non ci sono mibact in sigea: GRAVE!!");

		if (sottoCategorieVipSet != null && !sottoCategorieVipSet.isEmpty() && 
				categoriaEventoVipSet != null && !categoriaEventoVipSet.isEmpty()) {

			//cerca per idcategoria: sarebbe meglio in quanto il rapporto è 1a1 con le tipologie
			for (CategoriaeventoEntity categoriaEvento: categoriaEventoVipSet){
				for (CategoriaeventoEntity categoria: sottoCategorieVipSet){ 
					if(categoriaEvento.getIdcategoria()==categoria.getIdcategoria()){
						for (TipologiaMIBACT mibact: tipologiaMibactSigea){ 
							//RAPLACE NECESSARIE PER MAPPING TRA VIP E SIGEA
							String categoriaDesc=null;
							if (categoriaEvento.getDescrizione().equalsIgnoreCase(ApplicationConstants.FIERA)) { 
								categoriaDesc=ApplicationConstants.SALONE;
							}
							else if (categoriaEvento.getDescrizione().equalsIgnoreCase(ApplicationConstants.PARTITA)) {
								categoriaDesc=ApplicationConstants.GARA;
							}
							else {
								categoriaDesc=categoriaEvento.getDescrizione();
							}


							if (categoriaDesc.equalsIgnoreCase(mibact.getTipologiaMIBACT())){
								tipologiaMibactMap.put(mibact.getIdMIBACT(), mibact.getTipologiaMIBACT());
							}
						}
					}

				}
			}

			if (tipologiaMibactMap.isEmpty()) {
				//in alternativa all'id categoria prova con il primo idparent che trovi
				for (CategoriaeventoEntity categoriaEvento: categoriaEventoVipSet){
					for (CategoriaeventoEntity categoria: sottoCategorieVipSet){ 
						if(categoriaEvento.getIdcategoria()==categoria.getIdparent()){
							for (TipologiaMIBACT mibact: tipologiaMibactSigea){ 
								//RAPLACE NECESSARIE PER MAPPING TRA VIP E SIGEA
								String categoriaDesc=null;
								if (categoria.getDescrizione().equalsIgnoreCase(ApplicationConstants.FIERA)) { 
									categoriaDesc=ApplicationConstants.SALONE;
								}
								else if (categoria.getDescrizione().equalsIgnoreCase(ApplicationConstants.PARTITA)) {
									categoriaDesc=ApplicationConstants.GARA;
								}
								else {
									categoriaDesc=categoria.getDescrizione();
								}


								if (categoriaDesc.equalsIgnoreCase(mibact.getTipologiaMIBACT())){
									tipologiaMibactMap.put(mibact.getIdMIBACT(), mibact.getTipologiaMIBACT());
								}
							}
						}

					}
				}
			}
		}

		//CI SONO SEMPRE I RIFERIMENTI!!! QUESTA CONDIZIONE DOVREBBE ESSERE SEMPRE FALSE'
		if (tipologiaMibactMap.isEmpty()) {
			log.error("tipologiaMibact isEmpty()");
			tipologiaMibactMap.put(tipologiaMibactSigea.get(18).getIdMIBACT(), tipologiaMibactSigea.get(18).getTipologiaMIBACT());
		}

		return tipologiaMibactMap;
	}


	//i tipi di evento sono semplice o rassegna
	String checkTipologia(String tipo) {
		switch(tipo.toLowerCase()) {
		case "semplice":
			return ApplicationConstants.EVENTO;
		case "rassegna":
			return ApplicationConstants.RASSEGNA;
		default:
			return ApplicationConstants.EVENTO;
		}
	}

	//i tipi di evento sono semplice o rassegna
	private String checkTipoTicket(String tipo, TicketEntity ticketVip) {
		if (tipo!=null) {
			switch(tipo.toLowerCase()) {
			case "gratis":
				return ApplicationConstants.GRATUITO;
			case "pagamento":
				return ApplicationConstants.PAGAMENTO;
			default:
				return ApplicationConstants.INDEFINITO;
			}
		}else {

			if(ticketVip!=null &&
					(ticketVip.getPrezzointero()!= null || 
					ticketVip.getPrezzoridotto() != null || 
					ticketVip.getPrezzogruppo()!=null ||
					ticketVip.getNumerominimoriduzione()!=null
							)) {

				return ApplicationConstants.PAGAMENTO;
			}
			//il linkprenotazione sarà cancellato se è l'unico campo a non essere null
			return ApplicationConstants.INDEFINITO;
		}
	}

	private String getItaFromMultiMap( Map<String,String> map) {
		return map.get("IT");
	}


	//Mapping lingue da sigea a vip (nb: per lo spagnolo arriva SP e diventa ES)
	public String getLanguage(String key) {
		switch (key) {
		case "es":
			return "SP";
		}
		return key.toUpperCase(); // altre lingue
	}


	private String nullToEmpty(String field) {

		return (field == null) ? "" : HtmlUtils.htmlUnescape(field);
	}

	private String nullToEmptyPeriod(String field) {

		return (field == null) ? "" : (ApplicationConstants.DOUBLEDOT + field);
	}


	public VIPSchedaModel entitySigeaToModelSchedaVIP(
			Evento eventoVip2Sigea, 
			Map<String, String> tipologiaMibactFromCategorie, 
			Set<String> prodottiFromCategorie, 
			EventoEntity dto, 
			List<EventomicroesperienzemappingEntity> microesperienze) {

		VIPSchedaModel vipSchedaModel= new VIPSchedaModel();
		Map<String,String> mapMibact =new HashMap<>();
		int i=0;


		for (Map.Entry<String, String> entry : tipologiaMibactFromCategorie.entrySet()) {
			mapMibact.put(entry.getKey(), entry.getValue());
			++i;
			//NON PIU DI 3
			if (i>=3) {
				break;
			}
		}


		vipSchedaModel.setTipoScheda(ApplicationConstants.EVENTOSCHEDA);
		vipSchedaModel.setTipologieMIBACT(mapMibact);
		vipSchedaModel.setProdottiSet(prodottiFromCategorie);

		vipSchedaModel.setTitoloMulti(eventoVip2Sigea.getDatiGenerali().getTitoloMulti());
		vipSchedaModel.setAbstractDescrMulti(eventoVip2Sigea.getDatiGenerali().getAbstractDescrMulti());
		vipSchedaModel.setDescrizioneMulti(eventoVip2Sigea.getDatiGenerali().getDescrizioneMulti());
		vipSchedaModel.setSnippetMulti(eventoVip2Sigea.getDatiGenerali().getSnippetMulti());
		vipSchedaModel.setBig(		(dto.getHomepage()!=null) 		? dto.getHomepage(): null);
		vipSchedaModel.setSlideshow((dto.getHomecarousel()!=null) 	? dto.getHomecarousel():null );
		vipSchedaModel.setRanking(	(dto.getRanking()!=null) 		? dto.getRanking().longValue(): null);

		//consigliato per
		vipSchedaModel.setArteC 		(getBooleanMicroesperienza(microesperienze ,ApplicationConstants.ARTE));
		vipSchedaModel.setAss_spettacoli(getBooleanMicroesperienza(microesperienze ,ApplicationConstants.ASS_SPETTACOLI));
		vipSchedaModel.setAutentico 	(getBooleanMicroesperienza(microesperienze ,ApplicationConstants.AUTENTICO));
		vipSchedaModel.setLgbt 			(getBooleanMicroesperienza(microesperienze ,ApplicationConstants.LGBT));
		vipSchedaModel.setLusso 		(getBooleanMicroesperienza(microesperienze ,ApplicationConstants.LUSSO));
		vipSchedaModel.setRelax 		(getBooleanMicroesperienza(microesperienze ,ApplicationConstants.RELAX));
		vipSchedaModel.setVac_anziani 	(getBooleanMicroesperienza(microesperienze ,ApplicationConstants.VAC_ANZIANI));
		vipSchedaModel.setVac_bambini 	(getBooleanMicroesperienza(microesperienze ,ApplicationConstants.VAC_BAMBINI));
		vipSchedaModel.setViag_affari 	(getBooleanMicroesperienza(microesperienze ,ApplicationConstants.VIAG_AFFARI));
		vipSchedaModel.setAvventura 	(getBooleanMicroesperienza(microesperienze ,ApplicationConstants.AVVENTURA));
		vipSchedaModel.setBackpackers 	(getBooleanMicroesperienza(microesperienze ,ApplicationConstants.BACKPACKERS));
		vipSchedaModel.setBenessere 	(getBooleanMicroesperienza(microesperienze ,ApplicationConstants.BENESSERE));
		vipSchedaModel.setSalute 		(getBooleanMicroesperienza(microesperienze ,ApplicationConstants.SALUTE));
		vipSchedaModel.setShopping 		(getBooleanMicroesperienza(microesperienze ,ApplicationConstants.SHOPPING));
		vipSchedaModel.setShort_break 	(getBooleanMicroesperienza(microesperienze ,ApplicationConstants.SHORT_BREAK));
		vipSchedaModel.setViag_all_incl (getBooleanMicroesperienza(microesperienze ,ApplicationConstants.VIAG_ALL_INCL));
		vipSchedaModel.setViag_amici 	(getBooleanMicroesperienza(microesperienze ,ApplicationConstants.VIAG_AMICI));
		vipSchedaModel.setViag_disabili (getBooleanMicroesperienza(microesperienze ,ApplicationConstants.VIAG_DISABILI));
		vipSchedaModel.setCelebrazioni 	(getBooleanMicroesperienza(microesperienze ,ApplicationConstants.CELEBRAZIONI));
		vipSchedaModel.setCongressi 	(getBooleanMicroesperienza(microesperienze ,ApplicationConstants.CONGRESSI));
		vipSchedaModel.setDevozione 	(getBooleanMicroesperienza(microesperienze ,ApplicationConstants.DEVOZIONE));
		vipSchedaModel.setSportC 		(getBooleanMicroesperienza(microesperienze ,ApplicationConstants.SPORT));
		vipSchedaModel.setStaying 		(getBooleanMicroesperienza(microesperienze ,ApplicationConstants.STAYING));
		vipSchedaModel.setSvago 		(getBooleanMicroesperienza(microesperienze ,ApplicationConstants.SVAGO));
		vipSchedaModel.setViag_incentive(getBooleanMicroesperienza(microesperienze ,ApplicationConstants.VIAG_INCENTIVE));
		vipSchedaModel.setViag_natura 	(getBooleanMicroesperienza(microesperienze ,ApplicationConstants.VIAG_NATURA));
		vipSchedaModel.setViag_nozze 	(getBooleanMicroesperienza(microesperienze ,ApplicationConstants.VIAG_NOZZE));

		vipSchedaModel.setImmagineSet	(
				immaginiSigeaToVipSchedaModel(	eventoVip2Sigea.getImmagineSet())
				);
		vipSchedaModel.setDocumentoSet	(
				documentiSigeaToVipSchedaModel( eventoVip2Sigea.getDocumentoSet())
				);

		vipSchedaModel.setAttrattoriSet(
				attrattoriSigeaToVipSchedaModel(eventoVip2Sigea.getLocationSet())
				);

		vipSchedaModel.setLinkSet(
				linkSetSigeaToVipSchedaModel(eventoVip2Sigea.getLinkSet())
				);

		return vipSchedaModel;
	}


	private Set<VIPSchedaLinkModel> linkSetSigeaToVipSchedaModel(Set<Link> linkSet) {

		Set<VIPSchedaLinkModel>  vipSchedaLinkModelSet=null;
		VIPSchedaLinkModel vipSchedaLinkModel = null;
		long numberOrder=1;

		if (linkSet!=null && !linkSet.isEmpty());
		{
			vipSchedaLinkModelSet  = new HashSet<>();

			for (Link link:  linkSet) {
				vipSchedaLinkModel= new VIPSchedaLinkModel(); 
				vipSchedaLinkModel.setCredits(null);		
				vipSchedaLinkModel.setDidascaliaMulti(link.getDidascaliaMulti());		
				vipSchedaLinkModel.setLink(link.getLink());		
				vipSchedaLinkModel.setLinkId(link.getLinkId());    
				vipSchedaLinkModel.setTitoloMulti(link.getTitoloMulti());
				vipSchedaLinkModel.setOrdineNumerico	(link.getOrdine());
				vipSchedaLinkModelSet.add(vipSchedaLinkModel);
			}
		}

		return vipSchedaLinkModelSet;

	}



	private Set<AttrattoreModel> attrattoriSigeaToVipSchedaModel(Set<Location> locationSet) {

		AttrattoreModel attrattoreModel =null;
		Set<AttrattoreModel> attrattoreModelSet =null;

		if (!locationSet.isEmpty()) {
			attrattoreModel =new AttrattoreModel();
			attrattoreModelSet= new HashSet<>();
			//salvo eventuali attrattori non ancora censiti nel DB
			for (Location l: locationSet) {
				if (l.getAttrattoriSet()!=null) {
					for(Attrattore a : l.getAttrattoriSet()) {
						attrattoreModel.setAttrattoreId(a.getAttrattoreId());
						attrattoreModel.setEtichetta(a.getEtichetta());
						attrattoreModelSet.add(attrattoreModel);
					}
				}
			}
		}
		return attrattoreModelSet;

	}




	private Set<VIPSchedaDocumentoModel> documentiSigeaToVipSchedaModel(Set<DocumentoEvento> documentoSet) {

		Set<VIPSchedaDocumentoModel>  vipSchedaDocumentoModelSet=null;
		VIPSchedaDocumentoModel vipSchedaDocumentoModel = null;
		long numberOrder=1;

		if (!documentoSet.isEmpty());
		{
			vipSchedaDocumentoModelSet  = new HashSet<>();

			for (DocumentoEvento documento:  documentoSet) {
				vipSchedaDocumentoModel= new VIPSchedaDocumentoModel(); 
				vipSchedaDocumentoModel.setDimensione		(documento.getDimensione());
				vipSchedaDocumentoModel.setDocumentoId		(documento.getDocumentoId());
				vipSchedaDocumentoModel.setEstensione		(documento.getEstensione());
				vipSchedaDocumentoModel.setNomeOriginale	(documento.getNomeOriginale());
				vipSchedaDocumentoModel.setTitoloMulti		(documento.getTitoloMulti());
				vipSchedaDocumentoModel.setDaPubblicare		(true);
				vipSchedaDocumentoModel.setOrdineNumerico	(documento.getOrdine());
				vipSchedaDocumentoModelSet.add(vipSchedaDocumentoModel);

			}
		}

		return vipSchedaDocumentoModelSet;
	}


	private Set<VIPSchedaImmagineModel> immaginiSigeaToVipSchedaModel(Set<Immagine> immagineSet) {

		Set<VIPSchedaImmagineModel>  vipSchedaImmagineModelSet=null;
		VIPSchedaImmagineModel vipSchedaImmagineModel = null;
		long numberOrder=1;

		if (!immagineSet.isEmpty());
		{
			vipSchedaImmagineModelSet  = new HashSet<>();

			for (Immagine immagine:  immagineSet) {

				vipSchedaImmagineModel= new VIPSchedaImmagineModel(); 
				Boolean isBanner=false;

				for (Long idImgSigeaBanner :IDsSIGEABANNER)
				{
					if (idImgSigeaBanner==immagine.getImmagineId())
						isBanner=true;
				}

				vipSchedaImmagineModel.setBanner			(isBanner);
				vipSchedaImmagineModel.setCredits			("");
				vipSchedaImmagineModel.setDaPubblicare		(true);
				vipSchedaImmagineModel.setDidascaliaMulti	(immagine.getDidascaliaMulti());
				vipSchedaImmagineModel.setDimensione		(immagine.getDimensione());
				vipSchedaImmagineModel.setImmagineId		(immagine.getImmagineId());
				vipSchedaImmagineModel.setEstensione		(immagine.getEstensione());
				vipSchedaImmagineModel.setNomeOriginale 	(immagine.getNomeOriginale());
				vipSchedaImmagineModel.setOrdine			(immagine.getOrdine());

				vipSchedaImmagineModelSet.add(vipSchedaImmagineModel);
			}

		}

		return vipSchedaImmagineModelSet;
	}



	private Boolean getBooleanMicroesperienza(
			List<EventomicroesperienzemappingEntity> microesperienze, 
			String me) {

		for(EventomicroesperienzemappingEntity microesperienza : microesperienze ){
			if (microesperienza.getIdmicroesperienza().equalsIgnoreCase(me))
				return true;
		}
		return false;
	}


	public Pubblicazione setPubblicazione(
			Redazione redazioneGeneral, 
			StatoPubblicazione statoPubblicazione, 
			DettaglioUtente chiamante,
			EventoModel eventoModel, 
			Evento eventoFinal) {

		Pubblicazione pubblicazione = new Pubblicazione();
		pubblicazione.setRedazione(redazioneGeneral);
		pubblicazione.setStatoPubblicazione(statoPubblicazione);
		pubblicazione.setRedattore(chiamante);
		pubblicazione.setEventoModel(eventoModel);
		pubblicazione.setEvento(eventoFinal);

		return pubblicazione;
	}


	public LogPubblicazioni setLogPubblicazioni(StatiLogPubblicazione statiLogPubblicazione, Pubblicazione pubblicazioneVip) {

		LogPubblicazioni logPubblicazioni= new LogPubblicazioni();
		logPubblicazioni.setStato(statiLogPubblicazione);
		logPubblicazioni.setPubblicazione(pubblicazioneVip);
		logPubblicazioni.setNote("");
		return logPubblicazioni;
	}
	/** 
	 * pathType: costante path che identifica un file o una immagine 
	 * 			 BASE_PATH_FILE OR BASE_PATH_IMG	
	 * @param idAllegatoOld 
	 * */

	private String downloadAndSaveAttachedFile(Immagine immagineEvento, long idAllegatoOld, DocumentoEvento documentoEvento) {

		InputStream in = null;
		byte[] fileBytes = null;
		Long idEventoNew = null;
		Long idAllegatoNew = null;
		String folderName = null;
		String fileName = null;
		String pathType = null;

		if (immagineEvento!=null) {
			idAllegatoNew=	immagineEvento.getImmagineId();
			idEventoNew = 	immagineEvento.getEvento().getEventoId();
			folderName = 	imageFolderName;
			fileName=		immagineEvento.getImmagineId()+ ApplicationConstants.DOT + immagineEvento.getEstensione();
			pathType=		ApplicationConstants.BASE_PATH_IMG;
		}else if (documentoEvento!=null) {
			idAllegatoNew=	documentoEvento.getDocumentoId();
			idEventoNew = 	documentoEvento.getEvento().getEventoId();
			folderName = 	documentFolderName;
			fileName=		documentoEvento.getDocumentoId()+ ApplicationConstants.DOT + documentoEvento.getEstensione();
			pathType=		ApplicationConstants.BASE_PATH_FILE;
		}else {
			log.error("INFO NON CORRETTE");
			return "INFO NON CORRETTE";
		}

		String pathFile = 	
				repositoryRoot + 
				ApplicationConstants.SEPARATOR + 
				idEventoNew + 
				ApplicationConstants.SEPARATOR + 
				folderName + 
				ApplicationConstants.SEPARATOR; 


		log.debug("DOWNLOADING ALLEGATO:" + idAllegatoNew+"/"+idAllegatoOld);
		try {

			in = new URL(basePathAttachedFileV + pathType + idAllegatoOld).openStream();

		} catch (MalformedURLException malformedURLException) {
			log.error("ERRORE: URL MALFORMATO:" +malformedURLException.getMessage());
			return "FILE " + idAllegatoNew+"/"+idAllegatoOld +" NON SALVATO "+malformedURLException.getMessage() ;
		} catch (IOException ioGet) {
			log.error("ERRORE: FILE NON TROVATO:" +ioGet.getMessage());
			return "FILE " + idAllegatoNew+"/"+idAllegatoOld +" NON SALVATO "+ioGet.getMessage() ;
		}	

		try {

			fileBytes = IOUtils.toByteArray(in);

		} catch (IOException ioCon) {
			log.error("ERRORE: CONVERSIONE IS IN BYTE:" +ioCon.getMessage());
			return "FILE " + idAllegatoNew+"/"+idAllegatoOld +" NON SALVATO "+ioCon.getMessage() ;
		}



		try {
			if(fileBytes.length>0) {
				FileRepository.saveFile(fileBytes, fileName, pathFile);
			}else {
				throw new IOException("Lunghezza file = 0");
			}
		} catch (IOException ioSav) {
			log.error("ERRORE: SALVATAGGIO FILE:" +ioSav.getMessage());
			return "FILE " + idAllegatoNew+"/"+idAllegatoOld +" NON SALVATO "+ioSav.getMessage() ;
		}

		return "Allegato sigea/vip " + idAllegatoNew+"/"+idAllegatoOld + " SALVATO CORRETTAMENTE";
	}

	public List<ComuneRecord> getMunicipalities() {

		try {
			String token = cacheTokenService.getToken();
			Boolean returnBodyInSteadHttpResponseEntity = false;
			String urlComune = basePathTerritorialServiceV
					+ "administrativeArea/00/000";

			@SuppressWarnings("unchecked")
			ResponseEntity<ComuneRecord[]> res = (ResponseEntity<ComuneRecord[]>) new RestTemplateBuilder()
			.url(urlComune).returnClass(ComuneRecord[].class).bearerToken(token).httpMethod(HttpMethod.GET)
			.build().executeRequest(returnBodyInSteadHttpResponseEntity);


			/*		UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(basePathTerritorialServiceV + "territorialAreas");

				@SuppressWarnings({ "unchecked" })
				ResponseEntity<String> res4 = (ResponseEntity<String>) new RestTemplateBuilder()
						.url(url.build().toUriString()).returnClass(String.class).bearerToken(token).httpMethod(HttpMethod.GET)
						.build().executeRequest(false);

				String areeStringList =  res4.getBody();
				System.out.println(areeStringList);
			 */			
			return Arrays.asList(res.getBody());

		} catch (Exception e) {

			log.error(e.getLocalizedMessage());
			return null;
		}
	}


	@Transactional
	public void reportVipToReportSigea(Sigea2Vip1ShotMigra sigea2Vip1ShotMigra, int eventNumber, int countRelateEvents, Map<Long, Exception> notMigrate, int countEvents) {

		try {
			Relazione relazioneEvento = new Relazione();
			List<RassegnaEventiMappingEntity> rassegnaEventoVipSet = null;
			String previousRassegne="";

			Evento eventoSigea = eventoRepository.findAllByEventoId(sigea2Vip1ShotMigra.getIdSigea());

			log.info("RELAZIONE EVENTO N. "+ eventNumber+ " di " + countRelateEvents + " - ID EVENTO VSIGEA: " +eventoSigea.getEventoId());

			Set<Pubblicazione> pubblicazioneSet = 	eventoSigea.getPubblicazioneSet();
			Set<LogEvento> logSet = 				eventoSigea.getLogSet();

			//L'EVENTO o LA RASSEGNA
			relazioneEvento.setEvento(eventoSigea);
			//E'CONTENUTO o CONTIENE
			relazioneEvento.setTipoRelazione(sigea2Vip1ShotMigra.getCont());
			//NELLA RASSEGNA o L'EVENTO
			relazioneEvento.setTipoEventoAssociato(sigea2Vip1ShotMigra.getTipo());
			//con ID
			relazioneEvento.setEventoRelazionatoId(sigea2Vip1ShotMigra.getIdSigea2());
			//AGGIUNGI EVENTUALE REDAZIONE_ID VIP
			relazioneEvento.setRedazioneId(null);

			relazioneEvento=relazioneRepository.save(relazioneEvento);


			for (Pubblicazione pubblicazione : pubblicazioneSet) {

				VIPSchedaRelazioneModel vipSchedaRelazioneModel= new VIPSchedaRelazioneModel();

				if (pubblicazione.getRedazione().getRedazioneId().equalsIgnoreCase(ApplicationConstants.VIP))
				{
					ObjectMapper mapper = new ObjectMapper();
					JsonNode genericMetadata = pubblicazione.getGenericMetadata();
					VIPSchedaModel vipSchedaModel=mapper.treeToValue(genericMetadata, VIPSchedaModel.class);

					if (vipSchedaModel!=null) {
						vipSchedaRelazioneModel.setRelazioneId(relazioneEvento.getRelazioneId());
						vipSchedaRelazioneModel.setEventoRelazionatoId(relazioneEvento.getEventoRelazionatoId());
						vipSchedaRelazioneModel.setTipoEventoAssociato(relazioneEvento.getTipoEventoAssociato());
						vipSchedaRelazioneModel.setTipoRelazione(relazioneEvento.getTipoRelazione());
						vipSchedaRelazioneModel.setTitolo(ApplicationConstants.EVENTO_RASSEGNA);
						vipSchedaRelazioneModel.setMantieni(true);
						vipSchedaRelazioneModel.setComune(relazioniService.getRelazioneComuneFromEventoModel(eventoSigea.getLocationSet()));
						vipSchedaRelazioneModel.setPeriodo(relazioniService.getRelazionePeriodoFromEventoModel(eventoSigea.getPeriodoSet()));
						vipSchedaRelazioneModel.setSchedePubblicazione(ApplicationConstants.GENERAL);//se non ci sono relazioni da redazione vip
						vipSchedaRelazioneModel.setStatoEventoAssociato(ApplicationConstants.VALIDATO);
						vipSchedaRelazioneModel.setRedazioneId(null); //se non ci sono relazioni da redazione vip

						vipSchedaModel.getRelazioneSet().add(vipSchedaRelazioneModel);

						String jsonMetadata = mapper.writeValueAsString(vipSchedaModel);
						pubblicazione.setGenericMetadata(mapper.readValue(jsonMetadata, JsonNode.class));
						pubblicazione.setGenericMetadataWip(pubblicazione.getGenericMetadata());

						LogMigration logMigration= logMigrationRepository.findAllByEventoIdVipAndEventoIdSigea(sigea2Vip1ShotMigra.getIdVip(),sigea2Vip1ShotMigra.getIdSigea());
						logMigration.setRisultatoRel(ApplicationConstants.SUCCESS);
						logMigration.setMetadataPostRelSigea(jsonMetadata);

						//LOGGA
						if (sigea2Vip1ShotMigra.getCont().equalsIgnoreCase(ApplicationConstants.CONTENUTO)) {//E' UN EVENTO
							rassegnaEventoVipSet= rassegnaEventiMappingEntityRepository.findFirstByIdEvento(sigea2Vip1ShotMigra.getIdVip());
						}else{
							rassegnaEventoVipSet= rassegnaEventiMappingEntityRepository.findFirstByIdRassegna(sigea2Vip1ShotMigra.getIdVip());
						}

						previousRassegne= (logMigration.getRelazioniVip()==null || logMigration.getRelazioniVip().isEmpty() ) ? 	"" : logMigration.getRelazioniVip();
						logMigration.setRelazioniVip((rassegnaEventoVipSet==null) ? 	"" : previousRassegne + " - " + rassegnaEventoVipSet.toString());
						logMigrationRepository.save(logMigration);
					}
				}

				EventoModel eventoModel = pubblicazione.getEventoModel();
				RelazioneModel relazione = new RelazioneModel();

				relazione.setRelazioneId(vipSchedaRelazioneModel.getRelazioneId());
				relazione.setEventoRelazionatoId(vipSchedaRelazioneModel.getEventoRelazionatoId());
				relazione.setTipoEventoAssociato(vipSchedaRelazioneModel.getTipoEventoAssociato());
				relazione.setTipoRelazione(vipSchedaRelazioneModel.getTipoRelazione());
				relazione.setTitolo(ApplicationConstants.EVENTO_RASSEGNA);
				relazione.setComune(relazioniService.getRelazioneComuneFromEventoModel(eventoSigea.getLocationSet()));
				relazione.setPeriodo(relazioniService.getRelazionePeriodoFromEventoModel(eventoSigea.getPeriodoSet()));
				relazione.setSchedePubblicazione(ApplicationConstants.GENERAL);
				relazione.setStatoEventoAssociato(ApplicationConstants.VALIDATO);
				relazione.setRedazioneId(null); //se non ci sono relazioni da redazione vip

				eventoModel.getRelazioneSet().add(relazione);
				pubblicazione.setNoteAggiuntive(null);
				pubblicazione.setEventoModel(eventoModel);
				pubblicazioneRepository.save(pubblicazione);

			}


			for (LogEvento logEvento : logSet) {
				if (logEvento.getTipoOperazione().equalsIgnoreCase(ApplicationConstants.MODIFICA) &&
						!(logEvento.getOperazioni().toString().contains("Relazioni")))
				{
					logEvento.setOperazioni(logEvento.getOperazioni().toString().concat(", Relazioni"));
					logEventoRepository.save(logEvento);
				}
			}

			sigea2Vip1ShotMigra.setRelated(1);
			sigea2Vip1ShotMigraRepository.save(sigea2Vip1ShotMigra);


			log.info("RELAZIONE EVENTO N. "+ eventNumber + " CON ID EVENTO SIGEA: " + sigea2Vip1ShotMigra.getIdSigea() + " RELAZIONATO CON "+ sigea2Vip1ShotMigra.getIdSigea2() + " CONCLUSA CON SUCCESSO");

		} catch (JsonMappingException e) {

			notMigrate= throwExceptionAndContinue(
					"R",
					e,
					eventNumber,
					sigea2Vip1ShotMigra.getIdVip(),
					notMigrate
					);
			--countEvents;

		} catch (JsonProcessingException e) {

			notMigrate= throwExceptionAndContinue(
					"R",
					e,
					eventNumber,
					sigea2Vip1ShotMigra.getIdVip(),
					notMigrate
					);
			--countEvents;

		} catch (Exception e) {

			notMigrate= throwExceptionAndContinue(
					"R",
					e,
					eventNumber,
					sigea2Vip1ShotMigra.getIdVip(),
					notMigrate
					);
			--countEvents;
		}


	}

	Map<Long, Exception> throwExceptionAndContinue(String endpoint, Exception e, long eventNumber, long idevento, Map<Long, Exception> notMigrate) {

		log.error("MIGRAZIONE EVENTO N. "+ eventNumber + " CON IDEVENTOVIP " +idevento+ " FALLITA PER IL SEGUENTE MOTIVO: ");
		log.error("ERRORE: " + e.getMessage());

		notMigrate.put(idevento,e);

		LogMigration logMigration=new LogMigration();

		if (endpoint.equalsIgnoreCase("C"))
			logMigration.setRisultato(e.toString());
		else if (endpoint.equalsIgnoreCase("R"))
			logMigration.setRisultatoRel(e.toString());

		logMigration.setEventoIdVip(idevento);

		try {
			logMigrationRepository.save(logMigration);
		}catch(Exception exx ) {
			log.error("Errore scrittura tabella di log:" +exx.getMessage());
		}

		return notMigrate;
	}

}
