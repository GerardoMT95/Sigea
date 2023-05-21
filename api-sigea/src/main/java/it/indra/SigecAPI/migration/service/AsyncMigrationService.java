package it.indra.SigecAPI.migration.service;



import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.indra.SigeaCommons.enumeration.StatoPubblicazione;
import it.indra.SigeaCommons.model.PubblicazioneModel;
import it.indra.SigeaCommons.model.redazioni.VIPSchedaModel;
import it.indra.SigecAPI.entity.DettaglioUtente;
import it.indra.SigecAPI.entity.Evento;
import it.indra.SigecAPI.entity.Prodotto;
import it.indra.SigecAPI.entity.Pubblicazione;
import it.indra.SigecAPI.entity.Redazione;
import it.indra.SigecAPI.entity.TipologiaMIBACT;
import it.indra.SigecAPI.mapper.PubblicazioneMapper;
import it.indra.SigecAPI.mapper.RelazioneMapper;
import it.indra.SigecAPI.migration.entity.ComuneRecord;
import it.indra.SigecAPI.migration.entity.LogMigration;
import it.indra.SigecAPI.migration.entity.Sigea2Vip1ShotMigra;
import it.indra.SigecAPI.migration.entity.Sigea2VipMigra;
import it.indra.SigecAPI.migration.repository.LogMigrationRepository;
import it.indra.SigecAPI.migration.repository.Sigea2Vip1ShotMigraRepository;
import it.indra.SigecAPI.migration.repository.Sigea2VipMigraRepository;
import it.indra.SigecAPI.migration.utils.ApplicationConstants;
import it.indra.SigecAPI.repository.DettaglioUtenteRepository;
import it.indra.SigecAPI.repository.EventoRepository;
import it.indra.SigecAPI.repository.LogEventoRepository;
import it.indra.SigecAPI.repository.LogPubblicazioneRepository;
import it.indra.SigecAPI.repository.ProdottoRepository;
import it.indra.SigecAPI.repository.PubblicazioneRepository;
import it.indra.SigecAPI.repository.RedazioneRepository;
import it.indra.SigecAPI.repository.RelazioneRepository;
import it.indra.SigecAPI.repository.TipologiaMIBACTRepository;
import it.indra.SigecAPI.service.RelazioniService;
import it.indra.sigea2vip.persistence.entity.AccessibilitaEntity;
import it.indra.sigea2vip.persistence.entity.AccessibilitaanimaleEntity;
import it.indra.sigea2vip.persistence.entity.AccessibilitadisabilitaEntity;
import it.indra.sigea2vip.persistence.entity.AccessibilitamappingEntity;
import it.indra.sigea2vip.persistence.entity.AccessibilitaservizioEntity;
import it.indra.sigea2vip.persistence.entity.AccessibilitaspaziobambiniEntity;
import it.indra.sigea2vip.persistence.entity.AttrattoreIntEntity;
import it.indra.sigea2vip.persistence.entity.BackeventiutentiEntity;
import it.indra.sigea2vip.persistence.entity.CategoriaeventoEntity;
import it.indra.sigea2vip.persistence.entity.EventoEntity;
import it.indra.sigea2vip.persistence.entity.EventoallegatiEntity;
import it.indra.sigea2vip.persistence.entity.EventoallegatiintEntity;
import it.indra.sigea2vip.persistence.entity.EventoattrattorimappingEntity;
import it.indra.sigea2vip.persistence.entity.EventocategoriamappingEntity;
import it.indra.sigea2vip.persistence.entity.EventointEntity;
import it.indra.sigea2vip.persistence.entity.EventolocationEntity;
import it.indra.sigea2vip.persistence.entity.EventomicroesperienzemappingEntity;
import it.indra.sigea2vip.persistence.entity.EventoorarioEntity;
import it.indra.sigea2vip.persistence.entity.GiornochiusuraEntity;
import it.indra.sigea2vip.persistence.entity.OrarioEntity;
import it.indra.sigea2vip.persistence.entity.ScontoEntity;
import it.indra.sigea2vip.persistence.entity.Sigea2VipEntity;
import it.indra.sigea2vip.persistence.entity.TicketEntity;
import it.indra.sigea2vip.persistence.entity.TicketmappingEntity;
import it.indra.sigea2vip.persistence.entity.TicketnoteintEntity;
import it.indra.sigea2vip.persistence.entity.TicketscontoEntity;
import it.indra.sigea2vip.persistence.repository.AccessibilitaEntityRepository;
import it.indra.sigea2vip.persistence.repository.AccessibilitaanimaleEntityRepository;
import it.indra.sigea2vip.persistence.repository.AccessibilitadisabilitaEntityRepository;
import it.indra.sigea2vip.persistence.repository.AccessibilitamappingEntityRepository;
import it.indra.sigea2vip.persistence.repository.AccessibilitaservizioEntityRepository;
import it.indra.sigea2vip.persistence.repository.AccessibilitaspaziobambiniEntityRepository;
import it.indra.sigea2vip.persistence.repository.AttivitaturisticaEntityRepository;
import it.indra.sigea2vip.persistence.repository.AttrattoreIntEntityRepository;
import it.indra.sigea2vip.persistence.repository.BackeventiutentiEntityRepository;
import it.indra.sigea2vip.persistence.repository.CategoriaeventoEntityRepository;
import it.indra.sigea2vip.persistence.repository.EventoEntityRepository;
import it.indra.sigea2vip.persistence.repository.EventoallegatiEntityRepository;
import it.indra.sigea2vip.persistence.repository.EventoallegatiintEntityRepository;
import it.indra.sigea2vip.persistence.repository.EventoattrattorimappingEntityRepository;
import it.indra.sigea2vip.persistence.repository.EventocategoriamappingEntityRepository;
import it.indra.sigea2vip.persistence.repository.EventointEntityRepository;
import it.indra.sigea2vip.persistence.repository.EventolocationEntityRepository;
import it.indra.sigea2vip.persistence.repository.EventomicroesperienzemappingEntityRepository;
import it.indra.sigea2vip.persistence.repository.EventoorarioEntityRepository;
import it.indra.sigea2vip.persistence.repository.GiornochiusuraEntityRepository;
import it.indra.sigea2vip.persistence.repository.OrarioEntityRepository;
import it.indra.sigea2vip.persistence.repository.RassegnaEventiMappingEntityRepository;
import it.indra.sigea2vip.persistence.repository.ScontoEntityRepository;
import it.indra.sigea2vip.persistence.repository.Sigea2VipEntityRepository;
import it.indra.sigea2vip.persistence.repository.TicketEntityRepository;
import it.indra.sigea2vip.persistence.repository.TicketmappingEntityRepository;
import it.indra.sigea2vip.persistence.repository.TicketnoteintEntityRepository;
import it.indra.sigea2vip.persistence.repository.TicketscontoEntityRepository;
import it.indra.sigea2vip.persistence.repository.UtenteEntityRepository;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class AsyncMigrationService {

	@Value("${constants.migration.owner}")
	private Long ownerV;

	@Value("${constants.migration.limitIteration.events}")
	private Long limitIterationEventsV;

	@Value("${constants.migration.limitIteration.related}")
	private Long limitIterationRelatedV;

	@Value("${constants.migration.limitIteration.events_del}")
	private Long limitIterationEventsDelV;



	@Autowired EventoRepository 							eventoRepository;
	@Autowired EventoEntityRepository 						eventoEntityRepository;
	@Autowired UtenteEntityRepository 						utenteEntityRepository;	
	@Autowired TicketmappingEntityRepository 				ticketmappingEntityRepository;
	@Autowired TicketEntityRepository 						ticketEntityRepository;
	@Autowired TicketnoteintEntityRepository				ticketnoteintEntityRepository;
	@Autowired TicketscontoEntityRepository					ticketscontoEntityRepository;
	@Autowired ScontoEntityRepository           			scontoEntityRepository;
	@Autowired AccessibilitamappingEntityRepository			accessibilitamappingEntityRepository;
	@Autowired AccessibilitaanimaleEntityRepository 		accessibilitaanimaleEntityRepository;
	@Autowired AccessibilitadisabilitaEntityRepository 		accessibilitadisabilitaEntityRepository;
	@Autowired AccessibilitaservizioEntityRepository 		accessibilitaservizioEntityRepository;
	@Autowired AccessibilitaspaziobambiniEntityRepository 	accessibilitaspaziobambiniEntityRepository;
	@Autowired EventointEntityRepository					eventointEntityRepository;
	@Autowired EventolocationEntityRepository				eventolocationEntityRepository;
	@Autowired EventoorarioEntityRepository					eventoorarioEntityRepository;
	@Autowired OrarioEntityRepository						orarioEntityRepository;
	@Autowired EventoallegatiEntityRepository				eventoallegatiEntityRepository;
	@Autowired EventoallegatiintEntityRepository			eventoallegatiintEntityRepository;
	@Autowired RassegnaEventiMappingEntityRepository 		rassegnaEventiMappingEntityRepository;
	@Autowired EventocategoriamappingEntityRepository		eventocategoriamappingEntityRepository;
	@Autowired CategoriaeventoEntityRepository				categoriaeventoEntityRepository;
	@Autowired AccessibilitaEntityRepository				accessibilitaEntityRepository;
	@Autowired EventoattrattorimappingEntityRepository		eventoattrattorimappingEntityRepository;
	@Autowired AttrattoreIntEntityRepository				attrattoreIntEntityRepository;
	@Autowired GiornochiusuraEntityRepository				giornochiusuraEntityRepository;
	@Autowired EventomicroesperienzemappingEntityRepository	eventomicroesperienzemappingEntityRepository;
	@Autowired BackeventiutentiEntityRepository				backeventiutentiEntityRepository;
	@Autowired Sigea2Vip1ShotMigraRepository				sigea2Vip1ShotMigraRepository;

	@Autowired TipologiaMIBACTRepository					tipologiaMIBACTRepository;
	@Autowired MapperService								mapperService;
	@Autowired DettaglioUtenteRepository					dettaglioUtenteRepository;
	@Autowired RedazioneRepository							redazioneRepository;
	@Autowired ProdottoRepository							prodottoRepository;
	@Autowired Sigea2VipMigraRepository 					sigea2VipMigraRepository;
	@Autowired RelazioneRepository							relazioneRepository;
	@Autowired PubblicazioneRepository						pubblicazioneRepository;
	@Autowired LogEventoRepository							logEventoRepository;
	@Autowired RelazioneMapper								relazioneMapper;
	@Autowired RelazioniService								relazioniService;
	@Autowired LogMigrationRepository						logMigrationRepository;
	@Autowired LogPubblicazioneRepository					logPubblicazioneRepository;
	
	@Autowired Sigea2VipEntityRepository 					sigea2VipEntityRepository;
	@Autowired AttivitaturisticaEntityRepository 			attivitaturisticaEntityRepository;
	
	@Autowired
	private PubblicazioneMapper pubblicazioneMapper;
	
	@Resource(name = "jms-template-queue")
	private JmsTemplate jmsTemplate;


	@Value("${jms.queue.newVip.evento}")
	private String destinazioneNewVipEvento;
	
	@Value("${jms.queue.newVip.attivita}")
	private String destinazioneNewVipAttivita;
	
	@Value("${jms.newVipEnabled}")
	private boolean newVipEnabled;
	

	/**
	 * 
	 * @param start : il primo idevento da cui iniziare la migrazione
	 * @param end :	  l'ultimo idevento a conclusione della migrazione. Chiude il range cominciato dallo start
	 *
	 *
	 *constants:
		  migration:
		    owner: 51878 
		    	//il dettaglio utente a cui attribuire la migrazione
		    riconciliare: true 
		    	//settato a true se si intende far convergere gli eventi di migrazione a una riconciliazione
		    attivita: 
		      id: 62291 
		      	//attivita a cui associare gli eventi migrati
		      denominazione: #HIDDEN# 
		      	//nome dell'attivita'
		    basePath:
		      attachedFile: http://#HIDDEN#/pe_newlib 
		      	//path di collaudo primitivo da dove recuperare gli allegati
		      #attachedFile:https://pugliaevents.viaggiareinpuglia.it/pe_newlib 
		      	//path di produzione primitivo da dove recuperare gli allegati
		    limitIteration:
		      events: 30	
		      	//limita gli eventi da migrare per evitare casi accidentali disastrosi di lancio 
		      related: 10   
		      	//limita le relazione da migrare per evitare casi accidentali disastrosi di lancio
		      events_del: 2 
		      	//limita la cancellazione di eventi per evitare casi accidentali disastrosi di lancio
	 */


	@Async
	public void migrationEvents(Long start, Long end, HttpServletRequest request) {
		log.info("MIGRAZIONE EVENTI");

		int countEvents = 0;
		int eventNumber = 0;
		long idEvento=0;

		Map<Long, Exception> 					notMigrate = new HashMap<>();

		Long countEventsToMigrate= 							eventoEntityRepository.countAllByIdstatoapprovazioneAndTipologiaIsNotNull(1L);
		Set<EventoEntity> eventiVipSet=						eventoEntityRepository.findByIdeventoBetweenAndIdstatoapprovazioneAndTipologiaIsNotNull(start,end,1L);
		Set<CategoriaeventoEntity> sottoCategorieVipSet=	categoriaeventoEntityRepository.findBySottocategoriebase(1L);
		Set<CategoriaeventoEntity> prodottiVipSet=			categoriaeventoEntityRepository.findByIdparentIsNull();
		List<TipologiaMIBACT> tipologiaMibactSigea= 		tipologiaMIBACTRepository.findAll();
		Redazione redazioneGeneral = 						redazioneRepository.findById(ApplicationConstants.GENERAL).get();
		Redazione redazioneVip = 							redazioneRepository.findById(ApplicationConstants.VIP).get();
		List<Prodotto> prodottiSigea= 						prodottoRepository.findAll();

		List<ComuneRecord> comuneRecordList = mapperService.getMunicipalities();
	


		DettaglioUtente chiamante = dettaglioUtenteRepository.findById(ownerV).get();

		log.info("GLI EVENTI TOTALI DA MIGRARE SONO: " + countEventsToMigrate);
		log.info("MIGRAZIONE NEL RANGE: " + start + " - "+ end);
		log.info("NEL RANGE TROVATI " + eventiVipSet.size() + " EVENTI");

		for (EventoEntity eventoVip : eventiVipSet){

			if (eventNumber>=limitIterationEventsV) {

				log.info("LIMITE DI ITERAZIONE MIGRAZIONE EVENTI  RAGGIUNTO! LIMITE IMPOSTATO A " + limitIterationEventsV);
				break;

			}

			idEvento=eventoVip.getIdevento();
			Evento eventoVip2Sigea=null;
			++countEvents;
			++eventNumber;

			Sigea2VipMigra sigea2VipMigra= sigea2VipMigraRepository.findByIdEventoVip(idEvento);

			if (sigea2VipMigra!=null){

				notMigrate= throwExceptionAndContinue(
						"C",
						"ERRORE: MIGRAZIONE EVENTO N. "+ eventNumber+ " GIA MIGRATO! idVip/idSigea:" + sigea2VipMigra.getIdEventoVip()+ "/" + sigea2VipMigra.getIdSigea(),
						eventoVip.getIdevento(),
						notMigrate
						);
				--countEvents;
				continue;			 
			}


			Set<ScontoEntity> 						scontoVipSet = null;
			Set<OrarioEntity> 						orarioVipSet = null;
			Set<GiornochiusuraEntity>				giorniChiusuraVipSet=null;
			Set<EventoallegatiintEntity> 			allegatiIntVipSet = null;
			TicketEntity 							ticketVip = null;
			Set<TicketnoteintEntity> 				ticketnoteintVipSet = null;
			Set<TicketscontoEntity> 				ticketscontoVipSet = null;
			AccessibilitaEntity						accessibilitaVip = null;
			Set<AccessibilitaanimaleEntity> 		animaleVipSet = null;
			Set<AccessibilitadisabilitaEntity> 		disabiVipSet = null;
			Set<AccessibilitaservizioEntity> 		servizioVipSet = null;
			Set<AccessibilitaspaziobambiniEntity> 	spaziobambiniVipSet = null;
			Set<CategoriaeventoEntity> 				categoriaEventoVipSet=null;
			Set<AttrattoreIntEntity>  				attrattoreVipSet=null;
			AttrattoreIntEntity						attrattoreIntVip=null;

			try {
				log.info("MIGRAZIONE EVENTO N. "+ eventNumber+ " di " + eventiVipSet.size() + " - ID EVENTO VIP: " +idEvento);

				BackeventiutentiEntity utenteVip = 	
						backeventiutentiEntityRepository.findByIdutente(eventoVip.getIdutenteinserimento());

				if (utenteVip==null){
					log.info("utente inserimento non presente, tenta recupero per utente ultima modifica");

					utenteVip = 	
							backeventiutentiEntityRepository.findByIdutente(eventoVip.getIdutentemodifica());
				}
				
				if (utenteVip==null){
					log.info("utente non presente, inserita mail di default");

					utenteVip =new BackeventiutentiEntity();
					utenteVip.setEmail("nd@viaggiareinpuglia.it");

				}

				Set<TicketmappingEntity> ticketmappingVip=		
						ticketmappingEntityRepository.findByIdevento(idEvento);
				
				//get(0) = rapporto idticket-evento 1a1
				if (ticketmappingVip!=null && !ticketmappingVip.isEmpty()){
					TicketmappingEntity firstTicketMapping = 	
							ticketmappingVip.iterator().next();
					ticketVip=									ticketEntityRepository.findByIdticket(firstTicketMapping.getIdticket());
					ticketnoteintVipSet=						ticketnoteintEntityRepository.findAllByIdticket(firstTicketMapping.getIdticket());//PK LINGUA
					ticketscontoVipSet=							ticketscontoEntityRepository.findAllByIdticket(firstTicketMapping.getIdticket());//PK SCONTO

					if (ticketscontoVipSet!=null && !ticketscontoVipSet.isEmpty())
						scontoVipSet = new HashSet<ScontoEntity>();

					for (TicketscontoEntity ticketscontoVip: ticketscontoVipSet){
						Set<ScontoEntity>	scontoVip=			
								scontoEntityRepository.findAllByIdsconto(ticketscontoVip.getIdsconto());
						if (scontoVip!=null)
							scontoVipSet.addAll(scontoVip);
					}
				}
				List<AccessibilitamappingEntity> accessibilitamappingVip=		
						accessibilitamappingEntityRepository.findAllByIdevento(idEvento);
				//get(0) = rapporto idmapping-evento 1a1
				if (accessibilitamappingVip!=null && !accessibilitamappingVip.isEmpty()){

					AccessibilitamappingEntity firstAccessibilitamapping=accessibilitamappingVip.iterator().next();
					accessibilitaVip=		accessibilitaEntityRepository.findByIdmapping(firstAccessibilitamapping.getId());
					animaleVipSet=			accessibilitaanimaleEntityRepository.findAllByIdmapping(firstAccessibilitamapping.getId());
					disabiVipSet=			accessibilitadisabilitaEntityRepository.findByIdmapping(firstAccessibilitamapping.getId());
					servizioVipSet=			accessibilitaservizioEntityRepository.findAllByIdmapping(firstAccessibilitamapping.getId());
					spaziobambiniVipSet=	accessibilitaspaziobambiniEntityRepository.findAllByIdmapping(firstAccessibilitamapping.getId());
				}
				Set<EventointEntity> eventoIntVipSet=		
						eventointEntityRepository.findAllByIdEvento(idEvento); 


				Set<EventolocationEntity> eventoLocationVipSet=			
						eventolocationEntityRepository.findAllByIdevento(idEvento); 

				//get(0) = rapporto evento e location 1a1
				if (eventoLocationVipSet!=null && !eventoLocationVipSet.isEmpty()){
					EventolocationEntity firstLocationVip = 	
							eventoLocationVipSet.iterator().next();
					
					if (firstLocationVip.getIdcomune()==null) {

						notMigrate= throwExceptionAndContinue(
								"C",
								"ERRORE: MIGRAZIONE EVENTO N. "+ eventNumber+ " FALLITA ! Location non presente idVip" + eventoVip.getIdevento(),
								eventoVip.getIdevento(),
								notMigrate
								);
						--countEvents;
						continue;		
					}

				}

				Set<EventoattrattorimappingEntity>  eventoAttrattoriMappingVipSet=	
						eventoattrattorimappingEntityRepository.findAllByIdevento(idEvento); 

				if (eventoAttrattoriMappingVipSet!=null && !eventoAttrattoriMappingVipSet.isEmpty()) {
					attrattoreVipSet= new HashSet<AttrattoreIntEntity>();
					for (EventoattrattorimappingEntity eventoattrattorimapping: eventoAttrattoriMappingVipSet){
						attrattoreIntVip =	attrattoreIntEntityRepository.findAllByidAttrattoreAndIdLingua(eventoattrattorimapping.getIdattrattore(),"it"); 
						if (attrattoreIntVip!=null)
							attrattoreVipSet.add(attrattoreIntVip);
					}
				}

				List<EventoorarioEntity>	
				eventoOrarioVipSet=	eventoorarioEntityRepository.findAllByIdevento(idEvento); 

				if (eventoOrarioVipSet!=null && !eventoOrarioVipSet.isEmpty())
				{
					orarioVipSet= new HashSet<OrarioEntity>();
					giorniChiusuraVipSet= new HashSet<GiornochiusuraEntity>();
				}

				for (EventoorarioEntity eventoOrarioVip: eventoOrarioVipSet){
					OrarioEntity orarioVip =	
							orarioEntityRepository.findAllByIdorario(eventoOrarioVip.getIdorario());
					if (orarioVip!=null)
						orarioVipSet.add(orarioVip);
				}

				if (orarioVipSet!=null) {
					for (OrarioEntity orarioVip: orarioVipSet){
						Set<GiornochiusuraEntity> giorniChiusuraVip =	
								giornochiusuraEntityRepository.findAllByIdorario(orarioVip.getIdorario());
						if (giorniChiusuraVip!=null)
							giorniChiusuraVipSet.addAll(giorniChiusuraVip);
					}
				}

				List<EventoallegatiEntity>	eventoAllegatiVipSet=		
						eventoallegatiEntityRepository.findByIdref(idEvento);

				if (eventoAllegatiVipSet!=null && !eventoAllegatiVipSet.isEmpty())
					allegatiIntVipSet = new HashSet<EventoallegatiintEntity>();

				for (EventoallegatiEntity eventoAllegatiVip: eventoAllegatiVipSet){
					Set<EventoallegatiintEntity> allegatiIntVip =			
							eventoallegatiintEntityRepository.findByIdallegato(eventoAllegatiVip.getIdallegato());
					if (allegatiIntVip!=null)
						allegatiIntVipSet.addAll(allegatiIntVip);
				}


				Set<EventocategoriamappingEntity>	eventoCategoriaMappingVipSet= 
						eventocategoriamappingEntityRepository.findAllByIdevento(idEvento);


				if (eventoCategoriaMappingVipSet!=null && !eventoCategoriaMappingVipSet.isEmpty())
					categoriaEventoVipSet = new HashSet<CategoriaeventoEntity>();

				for (EventocategoriamappingEntity eventoCategoriaMappingVip: eventoCategoriaMappingVipSet){
					CategoriaeventoEntity categoriaEventoVip =			
							categoriaeventoEntityRepository.findByIdcategoria(eventoCategoriaMappingVip.getIdcategoria());
					if (categoriaEventoVip==null)
						categoriaEventoVip = categoriaeventoEntityRepository.findByIdparent(eventoCategoriaMappingVip.getIdcategoria());
					if (categoriaEventoVip!=null)
						categoriaEventoVipSet.add(categoriaEventoVip);
				}

				List<EventomicroesperienzemappingEntity> microesperienze=
						eventomicroesperienzemappingEntityRepository.findByIdevento(idEvento);


				eventoVip2Sigea= mapperService.entityVipToEntitySigea(
						sottoCategorieVipSet,
						prodottiVipSet,
						tipologiaMibactSigea,
						eventoVip, 
						utenteVip, 
						ticketVip, 
						ticketnoteintVipSet, 
						scontoVipSet,
						accessibilitaVip,
						animaleVipSet,
						disabiVipSet,
						servizioVipSet,
						spaziobambiniVipSet,
						eventoIntVipSet,
						eventoLocationVipSet,
						attrattoreVipSet,
						orarioVipSet,
						giorniChiusuraVipSet,
						allegatiIntVipSet,
						eventoAllegatiVipSet,
						categoriaEventoVipSet,
						chiamante,
						redazioneGeneral,
						redazioneVip,
						microesperienze,
						prodottiSigea,
						comuneRecordList
						);


			}catch(Exception e){
				e.printStackTrace();
				notMigrate= mapperService.throwExceptionAndContinue(
						"C",
						e,
						eventNumber,
						eventoVip.getIdevento(),
						notMigrate
						);
				--countEvents;
				continue;
			}

			log.info("MIGRAZIONE EVENTO N. "+ eventNumber + " CON ID EVENTO VIP: " +eventoVip.getIdevento() + " e ID SIGEA: "+ eventoVip2Sigea.getEventoId() +" CONCLUSA CON SUCCESSO");

		};

		log.info("MIGRATI: "+countEvents+ " di " + eventiVipSet.size());
		log.info("LISTA EVENTI NON MIGRATI: " + notMigrate.toString());

	}


	//TODO 
	/*
	 * 1) CREA BACKUP DELLA TABELLA PUBBLICAZIONE create_sigea_pubblicazione_backup.sql/
	 * 2) CREA TABELLA MAPPING PER LE RELAZIONI create_sigea2vip1shot.sql
	 */



	@Async
	public void relateEvents(Long start, Long end, HttpServletRequest request) {
		log.info("RELAZIONA EVENTI");

		//Set<EventoEntity> eventiVipSet=				eventoEntityRepository.findByIdeventoBetweenAndIdstatoapprovazioneAndTipologiaIsNotNull(start,end,1L);
		
		List<Sigea2Vip1ShotMigra>  sigea2Vip1ShotMigraList = sigea2Vip1ShotMigraRepository.findByidVipBetweenAndRelated(start,end,0L);
		int countRelateEvents= sigea2Vip1ShotMigraList.size();

		log.info("IL NUMERO DELLE RELAZIONI DA APPLICARE SONO: " + countRelateEvents);


		if ( sigea2Vip1ShotMigraList != null ) {

			int countEvents = 0;
			int eventNumber = 0;
			String eventoNumerString="";
			Map<Long, Exception> notMigrate = new HashMap<>();

			for (Sigea2Vip1ShotMigra sigea2Vip1ShotMigra:sigea2Vip1ShotMigraList) {

				++eventNumber;
				++countEvents;
				
				if (eventNumber>=limitIterationRelatedV) {

					log.info("LIMITE DI ITERAZIONE RELAZIONE RAGGIUNTO! LIMITE IMPOSTATO A " + limitIterationRelatedV );
					break;

				}


				if (sigea2Vip1ShotMigra==null){
					log.error("RELAZIONE EVENTO N."+ eventNumber + " DATI INCONSISTENTI, VEDI LA TABELLA SIGEA2VIP1SHOT");
					eventoNumerString= eventoNumerString + " DATO INCONSISTENTE SUL EVENTO CON PROGRESSIVO " + eventNumber +  " - ";
					--countEvents;
					continue;
				}

				
				if (sigea2Vip1ShotMigra.getRelated()==1){//SE E' UNICA LA RELAZIONE E' STATA GIA APPLICATA

					notMigrate= throwExceptionAndContinue(
							"R",
							"RELAZIONE EVENTO N."+ eventNumber+ " STAI TENTANDO DI ESEGUIRE UNA RELAZIONE GIA ESEGUITA! isSigea/idSigea2:" + sigea2Vip1ShotMigra.getIdSigea()+ "/" + sigea2Vip1ShotMigra.getIdSigea2(),
							sigea2Vip1ShotMigra.getIdVip(),
							notMigrate
							);
					--countEvents;
					continue;
				}

				mapperService.reportVipToReportSigea(
						sigea2Vip1ShotMigra,
						eventNumber,
						countRelateEvents,
						notMigrate,
						countEvents
						);

			}
			log.info("RELAZIONATI: "+countEvents+ " di " + countRelateEvents);
			log.info("LISTA EVENTI NON RELAZIONATI: " + notMigrate.toString());
		}

	}




	@Async
	@Transactional
	public void deleteEvents(Long start, Long end, HttpServletRequest request) {

		log.info("MIGRAZIONE EVENTI");

		Map<Long, Exception> 					notMigrate = new HashMap<>();
		int eventNumber = 0;
		int countEvents= 0;


		Set<Evento> idEventsSet= eventoRepository.findAllByEventoIdBetween(start,end);

		log.info("GLI EVENTI TOTALI DA CANCELLARE SONO: " + idEventsSet.size());
		log.info("DELETE NEL RANGE: " + start + " - "+ end);

		for (Evento eventoSigea : idEventsSet){

			if (eventNumber>=limitIterationEventsDelV) {

				log.info("LIMITE DI ITERAZIONE CANCELLAZIONE EVENTI RAGGIUNTO! LIMITE IMPOSTATO A " + limitIterationEventsDelV);
				break;

			}
			Long eventoid=eventoSigea.getEventoId();
			LogMigration eventLog = logMigrationRepository.findAllByEventoIdSigea(eventoid);
			++countEvents;
			++eventNumber;

			LogMigration logMigration = new LogMigration();
			logMigration.setRisultato(ApplicationConstants.DELETE);
			logMigration.setEventoIdVip(eventLog.getEventoIdVip());
			logMigration.setEventoIdSigea(eventoid);


			try{
				Set<Pubblicazione>	pubblicazioni =pubblicazioneRepository.findAllByEvento(eventoSigea);

				for (Pubblicazione pubblicazione: pubblicazioni) {
					logPubblicazioneRepository.deleteByPubblicazione(pubblicazione);
				}

				pubblicazioneRepository.deleteByEvento(eventoSigea);
				int result= eventoRepository.deleteByEventoId(eventoid);
				relazioneRepository.deleteByEventoRelazionatoId(eventoid);

				if (result==1) { 
					log.info("CANCELLAZIONE EVENTO N. "+ eventNumber + " CON ID EVENTO SIGEA: " + eventoid + " CONCLUSA CON SUCCESSO");
					int resultSigea2Vip = sigea2VipMigraRepository.deleteByIdSigea(eventoid);
					LogMigration resultLogMigrationVip = logMigrationRepository.save(logMigration);

					if (resultSigea2Vip==1 && resultLogMigrationVip!=null) {
						log.info("CANCELLAZIONE EVENTO SIGEA: " + eventoid + " dalla tabella sigea2vip e log OK");
					}else{
						log.error("CANCELLAZIONE EVENTO SIGEA: " + eventoid + " dalla tabella sigea2vip e log KO");
					}
				}else { 
					log.error("EVENTO N. "+ eventNumber + " CON ID EVENTO SIGEA : "+  eventoid + " NON TROVATO");
				}

			}catch(Exception e){
				log.error("CANCELLAZIONE EVENTO N. "+ eventNumber + " CON ID EVENTO SIGEA: " +eventoid + " FALLITA PER IL SEGUENTE MOTIVO: ");
				log.error("ERRORE: " + e);
				notMigrate.put(eventoid,e);
				--countEvents;
			}
		}
		log.info("CANCELLATI: "+countEvents+ " di " + idEventsSet.size());
		log.info("LISTA EVENTI NON CANCELLATI: " + notMigrate.toString());
	}

	private Map<Long, Exception> throwExceptionAndContinue(String endpoint, String ex, long idevento, Map<Long, Exception> notMigrate) {
		log.error(ex);
		Exception exx= new Exception(ex);
		notMigrate.put(idevento,exx);

		LogMigration logMigration=new LogMigration();

		if (endpoint.equalsIgnoreCase("C"))
			logMigration.setRisultato(ex.toString());
		else if (endpoint.equalsIgnoreCase("R"))
			logMigration.setRisultatoRel(ex.toString());


		logMigration.setEventoIdVip(idevento);


		try {
			logMigrationRepository.save(logMigration);
		}catch(Exception e ) {

			log.error("Errore scrittura tabella di log:" +e.getMessage());
		}

		return notMigrate;

	}

	/*
	 * 
	Migrazione degli eventi pubblicati sulla coda degli eventiNewVip
	Invio tramite un servizio rest con DataDa DataA come parametri
	Chiamata al Db di VIP per il recupero degli eventi e le attività attive, presenti in Sigea per l'invio al new VIP.
	Invio dell'oggetto PubblicazioneModel al JMS
	Il recupero dei file è di competenza di ENG

	 */

	@Async
	@Transactional
	public void pushEvent2NewVip(Long risorsaDa, Long risorsaA, HttpServletRequest request) {

		log.info("START");

		//Date pubbDa=Date.valueOf(dataDa);
		//Date pubbA=Date.valueOf(dataA);
		int count=1;
		int sendEvents=0;
		int sendActivities=0;
		ObjectMapper mapper = new ObjectMapper();
		String payload;
		Long idSigea;

		log.info("RICHIEDI GLI EVENTI PUBBLICATI IN SIGEA TRA GLI ID SIGEA " + risorsaDa +" E "+ risorsaA);
		Set<Pubblicazione> pubblicazioni = pubblicazioneRepository
				.findAllByGenericMetadataNotNullAndEvento_EventoIdBetweenAndRedazione_RedazioneIdAndStatoPubblicazione(risorsaDa,risorsaA
						, "VIP",StatoPubblicazione.PUBBLICATO);

		log.info("LE RISORSE SONO : " + pubblicazioni.size());

		for (Pubblicazione pubblicazione : pubblicazioni){
			try {
				
				payload = null;
				idSigea=pubblicazione.getEvento().getEventoId();

				Sigea2VipEntity risorsa=	sigea2VipEntityRepository.findByIdSigea(idSigea);

				if (risorsa!=null) {
					
					PubblicazioneModel modelPubblicazione= pubblicazioneMapper.entityToDto(pubblicazione);
					payload = mapper.writeValueAsString(modelPubblicazione);
					
					VIPSchedaModel vipSchedaModel = mapper.treeToValue(modelPubblicazione.getGenericMetadata(), VIPSchedaModel.class);
					
					if (vipSchedaModel.getTipoScheda().equalsIgnoreCase("EVENTO") ||
							vipSchedaModel.getTipoScheda().equalsIgnoreCase("RASSEGNA")){
						
						jmsTemplate.convertAndSend(destinazioneNewVipEvento, payload);
						sendEvents++;
						log.info				("N."+count++ +"! EVENTO VIP " +  risorsa.getIdEventoVip() + 	" e EVENTO SIGEA " +  risorsa.getIdSigea() + " INVIO PAYLOAD");
						log.debug(payload);
					}else if (vipSchedaModel.getTipoScheda().equalsIgnoreCase("ATTIVITA") ){
						
						jmsTemplate.convertAndSend(destinazioneNewVipAttivita, payload);
						sendActivities++;
						log.info				("N."+count++ +"! ATTIVITA VIP " +  risorsa.getIdAttivitaVip() + " e ATTIVITA SIGEA " +  risorsa.getIdSigea() + " INVIO PAYLOAD");
						log.debug(payload);
					}
					else {
						log.error				("N."+count++ +"! Non esiste corrispondenza tra Sigea e Vip! Esiste qualche problema per questo evento SIGEA " + idSigea);
					}
				}else {
					log.error					("N."+count++ +"! Non esiste corrispondenza tra Sigea e Vip! Esiste qualche problema per questo evento SIGEA " + idSigea);
				}
			}
			catch (JsonProcessingException e) {
				log.error("errore jsonparse");
				e.printStackTrace();
			} catch (Exception e) {
				log.error("errore generico");
				e.printStackTrace();
			} 
		}
		
		log.info("EVENTI INVIATI: " + sendEvents);
		log.info("ATTIVITA' INVIATE: " + sendActivities);
		log.info("END");
	}
/*
	@Async
	@Transactional
	public void pushActivities2NewVip(String dataDa, String dataA, HttpServletRequest request) {

		log.info("START");

		Date dataInizio=Date.valueOf(dataDa);
		Date dataFine=Date.valueOf(dataA);
		int count=0;
		int send=0;

		log.info("RICHIEDI LE ATTIVITA IN STATO PUBBLIC, INSERITI TRA LA DATA " + dataDa +"E LA DATA "+ dataA  );
		List<AttivitaturisticaEntity> attivitaTur=	attivitaturisticaEntityRepository.findBydataaggiornamentoBetweenAndIdstato(dataInizio,dataFine,"PUBBLIC");

		log.info("Le attività sono : " + attivitaTur.size());

		for (AttivitaturisticaEntity attTur : attivitaTur){
			try {
				ObjectMapper mapper = new ObjectMapper();
				String payload = null;

				Sigea2VipEntity attivita=	sigea2VipEntityRepository.findByIdAttivitaVip(attTur.getId());

				if (attivita!=null) {
					Pubblicazione pubblicazione = pubblicazioneRepository
							.findAllByGenericMetadataNotNullAndEvento_EventoIdAndRedazione_RedazioneId(
									attivita.getIdSigea(), "VIP");

					PubblicazioneModel modelPubblicazione= pubblicazioneMapper.entityToDto(pubblicazione);

					try {
						payload = mapper.writeValueAsString(modelPubblicazione);
					} catch (JsonProcessingException e) {
						log.error("errore jsonparse");
						e.printStackTrace();
					}

					if (payload==null || payload.equals("null"))
					{
						//	System.out.println		("N."+count++ +"! Non è stata trovata corrispondenza tra l'attivita VIP " +  attivita.getIdAttivitaVip() + " e l'attivita SIGEA " +  attivita.getIdSigea());
						log.error				("N."+count++ +"! Non è stata trovata corrispondenza tra l'attivita VIP " +  attivita.getIdAttivitaVip() + " e l'attivita SIGEA " +  attivita.getIdSigea());
					}else {
						jmsTemplate.convertAndSend(destinazioneNewVipAttivita, payload);
						send++;
						//	System.out.println		("N."+count++ +"! Attivita VIP " +  attivita.getIdAttivitaVip() + " e Attivita SIGEA " +  attivita.getIdSigea() + " INVIO PAYLOAD: "+ payload);
						log.info				("N."+count++ +"! Attivita VIP " +  attivita.getIdAttivitaVip() + " e Attivita SIGEA " +  attivita.getIdSigea() + " INVIO PAYLOAD: "+ payload);
					}	
				}else {
					//System.out.println			("N."+count++ +"! L'attività " +attTur.getId() + " non appartiene a Sigea ma è nativa di VIP");
					log.error					("N."+count++ +"! L'attività VIP " +attTur.getId() + " non appartiene a Sigea ma è nativa di VIP");

				}
			} catch (Exception e) {
				log.error("errore generico");
				e.printStackTrace();
			}
		}
		log.info("INVIATI: " + send);
		log.info("END");
	}
*/
}

