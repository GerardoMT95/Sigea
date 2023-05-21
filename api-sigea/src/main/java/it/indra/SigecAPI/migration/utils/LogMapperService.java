package it.indra.SigecAPI.migration.utils;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import it.indra.SigecAPI.entity.DettaglioUtente;
import it.indra.SigecAPI.entity.Evento;
import it.indra.SigecAPI.entity.Prodotto;
import it.indra.SigecAPI.entity.Redazione;
import it.indra.SigecAPI.entity.TipologiaMIBACT;
import it.indra.SigecAPI.migration.entity.LogMigration;
import it.indra.sigea2vip.persistence.entity.AccessibilitaEntity;
import it.indra.sigea2vip.persistence.entity.AccessibilitaanimaleEntity;
import it.indra.sigea2vip.persistence.entity.AccessibilitadisabilitaEntity;
import it.indra.sigea2vip.persistence.entity.AccessibilitaservizioEntity;
import it.indra.sigea2vip.persistence.entity.AccessibilitaspaziobambiniEntity;
import it.indra.sigea2vip.persistence.entity.BackeventiutentiEntity;
import it.indra.sigea2vip.persistence.entity.CategoriaeventoEntity;
import it.indra.sigea2vip.persistence.entity.EventoEntity;
import it.indra.sigea2vip.persistence.entity.EventoallegatiEntity;
import it.indra.sigea2vip.persistence.entity.EventoallegatiintEntity;
import it.indra.sigea2vip.persistence.entity.EventointEntity;
import it.indra.sigea2vip.persistence.entity.EventolocationEntity;
import it.indra.sigea2vip.persistence.entity.EventomicroesperienzemappingEntity;
import it.indra.sigea2vip.persistence.entity.GiornochiusuraEntity;
import it.indra.sigea2vip.persistence.entity.OrarioEntity;
import it.indra.sigea2vip.persistence.entity.ScontoEntity;
import it.indra.sigea2vip.persistence.entity.TicketEntity;
import it.indra.sigea2vip.persistence.entity.TicketnoteintEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LogMapperService {



	public LogMigration setLogMigration( LogMigration logMigration, Evento eventoFinal, EventoEntity dto, Set<CategoriaeventoEntity> categorieVipSet,
			Set<CategoriaeventoEntity> prodottiVipSet, List<TipologiaMIBACT> tipologiaMibactSigea,
			Redazione redazioneGeneral, Redazione redazioneVip, DettaglioUtente chiamante, List<Prodotto> prodottiSigea,
			BackeventiutentiEntity utenteVip, TicketEntity ticketVip, Set<TicketnoteintEntity> ticketnoteintVipSet,
			Set<ScontoEntity> scontoVipSet, Evento evento, AccessibilitaEntity accessibilitaVip,
			Set<AccessibilitaanimaleEntity> animaleVipSet, Set<AccessibilitadisabilitaEntity> disabiVipSet,
			Set<AccessibilitaservizioEntity> servizioVipSet, Set<AccessibilitaspaziobambiniEntity> spaziobambiniVipSet,
			Set<EventointEntity> eventoIntVipSet, Set<EventolocationEntity> eventoLocationVipSet,
			Set<OrarioEntity> orarioVipSet, Set<GiornochiusuraEntity> giorniChiusuraVipSet,
			List<EventoallegatiEntity> eventoAllegatiVipSet, Set<EventoallegatiintEntity> allegatiIntVipSet,
			Set<CategoriaeventoEntity> categoriaEventoVipSet, List<EventomicroesperienzemappingEntity> microesperienze,
			String jsonMetadata) {

		log.debug("log migrazione");
		
		logMigration.setEventoIdSigea(eventoFinal.getEventoId());
		logMigration.setEventoIdVip(dto.getIdevento());
		logMigration.setRisultato(ApplicationConstants.SUCCESS);
		logMigration.setUtility(
				"CATEGORIE VIP: "	+ 	((categorieVipSet==null) ? 		"" 	:categorieVipSet.toString())		+ApplicationConstants.DASHSPACE+
				"PRODOTTI VIP: "	+ 	((prodottiVipSet==null) ? 		"" 	:prodottiVipSet.toString())  		+ApplicationConstants.DASHSPACE+
				"TIPOLOGIE SIGEA: "	+ 	((tipologiaMibactSigea==null) ? ""	:tipologiaMibactSigea.toString())	+ApplicationConstants.DASHSPACE+
				"REDAZIONE SIGEA: "	+ 	((redazioneGeneral==null) ? 	"" 	:redazioneGeneral.toString())		+ApplicationConstants.DASHSPACE+
				"REDAZIONE VIP: "	+ 	((redazioneVip==null) ? 		"" 	:redazioneVip.toString()) 			+ApplicationConstants.DASHSPACE+
				"PRODOTTI  SIGEA: "	+	((prodottiSigea==null) ? 		"" 	:prodottiSigea.toString())			+ApplicationConstants.DASHSPACE
				);
		logMigration.setEventoVip((dto==null) ?				"" : dto.toString());
		logMigration.setEventoSigea((eventoFinal==null) ?	"" : eventoFinal.toString());
		logMigration.setUtenteVip((utenteVip==null) ?		"" : utenteVip.toString());
		logMigration.setTicketVip(
				"TICKET: " 	+	((ticketVip==null) ? 			"" :ticketVip.toString()) 			+ApplicationConstants.DASHSPACE+ 
				"TICKETNOTE: "+ 	((ticketnoteintVipSet==null) ? 	"" :ticketnoteintVipSet.toString())	+ApplicationConstants.DASHSPACE+
				"SCONTI: "   	+ 	((scontoVipSet==null) ? 		"" :scontoVipSet.toString())		+ApplicationConstants.DASHSPACE
				);
		logMigration.setTicketSigea((evento.getTicket()==null) ?	"" : evento.getTicket().toString());
		logMigration.setAccessibilitaVip(	
				"ACCESSO: "		+	((accessibilitaVip==null) 	?	"" :accessibilitaVip.toString()) 	+ApplicationConstants.DASHSPACE+ 
				"ANIMALE: "  	+ 	((animaleVipSet==null) 		?   "" :animaleVipSet.toString()) 		+ApplicationConstants.DASHSPACE+ 
				"DISABI: "		+ 	((disabiVipSet==null) 		? 	"" :disabiVipSet.toString()) 		+ApplicationConstants.DASHSPACE+
				"SERVIZIO: " 	+ 	((servizioVipSet==null) 	? 	"" :servizioVipSet.toString()) 		+ApplicationConstants.DASHSPACE+
				"SPAZIBAMBINI: "+ 	((spaziobambiniVipSet==null)? 	"" :spaziobambiniVipSet.toString())	+ApplicationConstants.DASHSPACE
				);
		logMigration.setAccessibilitaSigea	((evento.getAccessibilita()==null) 	?	"" : evento.getAccessibilita().toString());
		logMigration.setDatiGeneraliVip		((eventoIntVipSet==null) 			? 	"" : eventoIntVipSet.toString());
		logMigration.setDatiGeneraliSigea	((evento.getDatiGenerali()==null) 	? 	"" : evento.getDatiGenerali().toString());
		logMigration.setLocationVip			((eventoLocationVipSet==null) 		?	"" : eventoLocationVipSet.toString());
		logMigration.setLocationSigea		((evento.getLocationSet()==null) 	? 	"" : evento.getLocationSet().toString());
		logMigration.setPeriodoVip(	
				"ORARIO: " 		+	((orarioVipSet==null) ? 			"" :orarioVipSet.toString()) 			+ApplicationConstants.DASHSPACE+ 
				"CHIUSURE: "	+ 	((giorniChiusuraVipSet==null) ? 	"" :giorniChiusuraVipSet.toString())	+ApplicationConstants.DASHSPACE
				);
		logMigration.setPeriodoSigea((evento.getPeriodoSet()==null) ?	"" : evento.getPeriodoSet().toString());

		logMigration.setAllegatiVip(
				"EVENTIALLEGATI: " 		+	((eventoAllegatiVipSet==null) ?	"" :eventoAllegatiVipSet.toString()) 	+ApplicationConstants.DASHSPACE+ 
				"ALLEGATIINT: "			+ 	((allegatiIntVipSet==null) ? 	"" :allegatiIntVipSet.toString())		+ApplicationConstants.DASHSPACE
				);
		logMigration.setImmaginiSigea		((evento.getImmagineSet()==null) ?	"" : evento.getImmagineSet().toString());
		logMigration.setDocumentiSigea		((evento.getDocumentoSet()==null) ?	"" : evento.getDocumentoSet().toString());
		logMigration.setLinkSigea			((evento.getLinkSet()==null) ?		"" : evento.getLinkSet().toString());
		logMigration.setProdottiVip			((categoriaEventoVipSet==null) ? 	"" : categoriaEventoVipSet.toString());
		logMigration.setMicroesperienzeVip	((microesperienze==null) ? 			"" : microesperienze.toString());
		logMigration.setMetadataSigea(jsonMetadata);

		return logMigration;
	}

}
