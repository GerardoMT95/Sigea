
package it.indra.SigecAPI.datafiltermanager;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.persistence.criteria.SetJoin;
import javax.persistence.criteria.Subquery;

import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.internal.CriteriaImpl;

import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.loader.criteria.CriteriaJoinWalker;
import org.hibernate.loader.criteria.CriteriaQueryTranslator;
import org.hibernate.persister.entity.OuterJoinLoadable;
import org.hibernate.query.criteria.internal.CriteriaQueryImpl;
import org.jfree.util.Log;

import it.indra.SigeaCommons.enumeration.StatoPubblicazione;
import it.indra.SigeaCommons.model.EventoFilter;
import it.indra.SigeaCommons.model.EventoModelList;
import it.indra.SigecAPI.entity.Attivita;
import it.indra.SigecAPI.entity.Attivita_;
import it.indra.SigecAPI.entity.Bando;
import it.indra.SigecAPI.entity.Bando_;
import it.indra.SigecAPI.entity.DatiGenerali;
import it.indra.SigecAPI.entity.DatiGenerali_;
import it.indra.SigecAPI.entity.DettaglioUtente;
import it.indra.SigecAPI.entity.DettaglioUtente_;
import it.indra.SigecAPI.entity.Evento;
import it.indra.SigecAPI.entity.Evento_;
import it.indra.SigecAPI.entity.Location;
import it.indra.SigecAPI.entity.Location_;
import it.indra.SigecAPI.entity.LogPubblicazioni;
import it.indra.SigecAPI.entity.LogPubblicazioni_;
import it.indra.SigecAPI.entity.Progetto;
import it.indra.SigecAPI.entity.Progetto_;
import it.indra.SigecAPI.entity.Pubblicazione;
import it.indra.SigecAPI.entity.Pubblicazione_;
import it.indra.SigecAPI.entity.Redazione;
import it.indra.SigecAPI.entity.Redazione_;
import it.indra.SigecAPI.entity.Relazione;
import it.indra.SigecAPI.entity.Relazione_;
import it.indra.SigecAPI.entity.RichiestaAttivita;
import it.indra.SigecAPI.entity.RichiestaAttivita_;
import it.indra.SigecAPI.entity.StatiLogPubblicazione;
import it.indra.SigecAPI.entity.StatiLogPubblicazione_;
import it.indra.SigecAPI.entity.Stato;
import it.indra.SigecAPI.entity.Stato_;
import it.indra.SigecAPI.util.CommonUtility;
import it.indra.SigecAPI.util.WrappedFilterInterface;
import it.indra.SigecAPI.util.WrapperFilterRequest;

public class EventoFilterManager implements WrappedFilterInterface {

	@SuppressWarnings("unchecked")
	@Override
	public <T> TypedQuery<T> createDynamicDatatableQuery(EntityManager em, WrapperFilterRequest<?> request, boolean isCount, boolean addSearch, Class<T> classe) {


		EventoFilter filter = (EventoFilter) request.getFilter();

		String redazione = "VIP";

		if(filter.getRedazioneAttuale()!=null && !filter.getRedazioneAttuale().isEmpty())
			switch (filter.getRedazioneAttuale()) 
			{
			case "Viaggiare in Puglia":
				redazione="VIP";
				break;
			default:
				redazione="";
				break;

			}


		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(classe);

		//query principale
		Root<Evento> root = cq.from(Evento.class);
		Join<Evento, DettaglioUtente> joinUtente = root.join(Evento_.owner, JoinType.INNER);
		Join<Evento, DatiGenerali> joinDatiGenerali = root.join(Evento_.datiGenerali, JoinType.INNER);
		Join<Evento, Stato> joinStato = root.join(Evento_.stato, JoinType.INNER);
		Join<Evento, Attivita> joinAttivita = root.join(Evento_.attivita, JoinType.LEFT);
		Join<Evento, RichiestaAttivita> joinRichiestaAttivita = root.join(Evento_.richiestaAttivita, JoinType.LEFT);
		SetJoin<Evento, Location> joinLocationSet = root.join(Evento_.locationSet, JoinType.LEFT);
		joinLocationSet.on(cb.isTrue(joinLocationSet.get(Location_.fgPrincipale)));

		SetJoin<Evento, Pubblicazione> joinPubblicazione = null;
		Join<Pubblicazione,Redazione> joinRedazioneDataR=null;
		
		Join<Evento, Bando> joinBando = null;
		Join<Evento, Progetto> joinProgetto = null;


		if (	
				(filter.getRedazioniGenerali() != null && !filter.getRedazioniGenerali().isEmpty()) || 
				(filter.getRedazioni() != null && !filter.getRedazioni().isEmpty()) || 
				(filter.getFiltroMIBACT() != null && !filter.getFiltroMIBACT().isEmpty() && "RED".equals(filter.getServiceCode())) || 
				(filter.getTipologia2() != null && !filter.getTipologia2().isEmpty()) ||
				(!redazione.matches("") && filter.getStatoPubblicazione() != null && !filter.getStatoPubblicazione().isEmpty() && 
				!filter.getStatoPubblicazione().equalsIgnoreCase("In attesa di redazione")) ) {

			joinPubblicazione = root.join(Evento_.pubblicazioneSet, JoinType.INNER);
			joinRedazioneDataR = joinPubblicazione.join(Pubblicazione_.redazione,JoinType.INNER);

		}
		
		if ("FIN".equals(filter.getServiceCode())) {
			joinBando = root.join(Evento_.bando, JoinType.INNER);
			joinProgetto = root.join(Evento_.progetto, JoinType.LEFT);
		}

		
		
		
		
		//subquery lock
		Subquery<String> sqOwnerUltimaModifica = cq.subquery(String.class);
		Root<DettaglioUtente> subOwnerUltimaModifica = sqOwnerUltimaModifica.from(DettaglioUtente.class);
		// controllo il lock sugli eventi
		// TODO gestione lock pubblicazioni
		sqOwnerUltimaModifica.where(cb.equal(subOwnerUltimaModifica.get(DettaglioUtente_.utenteId), root.get(Evento_.ownerUltimaModifica).get(DettaglioUtente_.utenteId)));
		sqOwnerUltimaModifica.select(cb.concat(subOwnerUltimaModifica.get(DettaglioUtente_.nome), cb.concat(" ", subOwnerUltimaModifica.get(DettaglioUtente_.cognome))));

		//subquery per relazioni
		Subquery<String> sqRelazioni = cq.subquery(String.class);
		Root<Relazione> subRootRelazioni = sqRelazioni.from(Relazione.class);

		if("RED".equals(filter.getServiceCode())) {

			Predicate orOnRedazioneId =cb.or(cb.equal(subRootRelazioni.get(Relazione_.redazioneId), redazione), cb.isNull(subRootRelazioni.get(Relazione_.redazioneId)));
			Predicate andOnEventoWithOrOnRedazioneId = cb.and(cb.equal(subRootRelazioni.get(Relazione_.evento), root), orOnRedazioneId);
			Predicate andOnEventoRelazionatoStatoWithAndOnEventoWithOrOnRedazioneId = cb.and(andOnEventoWithOrOnRedazioneId , cb.equal(subRootRelazioni.get(Relazione_.eventoRelazionato).get(Evento_.stato).get(Stato_.statoId), "VALIDATO"));

			sqRelazioni.where(andOnEventoRelazionatoStatoWithAndOnEventoWithOrOnRedazioneId);
		} else {
			sqRelazioni.where(cb.and(cb.equal(subRootRelazioni.get(Relazione_.evento), root), cb.isNull(subRootRelazioni.get(Relazione_.redazioneId))));
		}
		sqRelazioni.select(cb.function("string_agg", String.class, subRootRelazioni.get(Relazione_.eventoRelazionato).get(Evento_.datiGenerali).get(DatiGenerali_.titolo),cb.literal('|'),subRootRelazioni.get(Relazione_.eventoRelazionato).get(Evento_.datiGenerali).get(DatiGenerali_.titolo)));	

		//subquery per concatenare comuni
		Subquery<String> sqComuni = cq.subquery(String.class);
		Root<Location> subRootComuni = sqComuni.from(Location.class);
		sqComuni.where(cb.equal(subRootComuni.get(Location_.evento), root));

		Expression<Object> tmp = cb.selectCase().when(cb.isNull(subRootComuni.get(Location_.comune)), cb.selectCase().when(cb.isNull(subRootComuni.get(Location_.comuneEstero)), " ").otherwise(subRootComuni.get(Location_.comuneEstero))).otherwise(subRootComuni.get(Location_.comune));

		sqComuni.select(cb.function("string_agg", String.class,  tmp, cb.literal(','), tmp));

		//subquery per concatenare le redazioni
		Subquery<String> sqRedazioni = cq.subquery(String.class);
		Root<Pubblicazione> subRootPubblicazioni = sqRedazioni.from(Pubblicazione.class);
		Join<Pubblicazione,Redazione> joinRedazione = subRootPubblicazioni.join(Pubblicazione_.redazione,JoinType.INNER);
		sqRedazioni.where(cb.and(cb.equal(subRootPubblicazioni.get(Pubblicazione_.evento), root),cb.isNotNull(subRootPubblicazioni.get(Pubblicazione_.genericMetadata))));
		sqRedazioni.select(cb.function("string_agg", String.class, joinRedazione.get(Redazione_.nome),cb.literal(','),joinRedazione.get(Redazione_.nome)));


		//subquery per tirare fuori la data modifica sulla pubblicazione
		Subquery<String> sqRedazioniDataMod = cq.subquery(String.class);
		Root<Pubblicazione> subRootPubblicazioniDataMod = sqRedazioniDataMod.from(Pubblicazione.class);
		Join<Pubblicazione,Redazione> joinRedazioneDataMod = subRootPubblicazioniDataMod.join(Pubblicazione_.redazione,JoinType.INNER);
		sqRedazioniDataMod.where(cb.and(cb.equal(subRootPubblicazioniDataMod.get(Pubblicazione_.evento), root),cb.isNotNull(subRootPubblicazioniDataMod.get(Pubblicazione_.genericMetadataWip)), cb.equal(joinRedazioneDataMod.get(Redazione_.redazioneId), redazione)));
		sqRedazioniDataMod.select(cb.function(CommonUtility.to_char, String.class, subRootPubblicazioniDataMod.get(Pubblicazione_.dataPubblicazione), cb.literal(CommonUtility.ukDateFormatSec)));
		//	sqRedazioniDataMod.select(cb.selectCase().when(cb.isNull(subRootPubblicazioniDataMod.get(Pubblicazione_.dataPubblicazione)),cb.function(CommonUtility.to_char, String.class, root.get(Evento_.dataMod), cb.literal(CommonUtility.italianDateFormat))).otherwise(cb.function(CommonUtility.to_char, String.class, subRootPubblicazioniDataMod.get(Pubblicazione_.dataPubblicazione), cb.literal(CommonUtility.italianDateFormat))).as(String.class));


		//subquery per concatenare le redazioni
		Subquery<String> sqPubblicazione = cq.subquery(String.class);
		Root<Pubblicazione> subRootPubblicazioniData = sqPubblicazione.from(Pubblicazione.class);
		Join<Pubblicazione,Redazione> joinRedazioneData = subRootPubblicazioniData.join(Pubblicazione_.redazione,JoinType.INNER);
		//		sqPubblicazione.where(cb.and(cb.equal(subRootPubblicazioniData.get(Pubblicazione_.evento), root) , cb.equal(joinRedazioneData.get(Redazione_.redazioneId), filter.getRedazioneAttuale())));
		sqPubblicazione.where(cb.and(cb.equal(subRootPubblicazioniData.get(Pubblicazione_.evento), root) , cb.equal(joinRedazioneData.get(Redazione_.redazioneId), redazione)));
		sqPubblicazione.select(cb.selectCase().when(cb.isNull(subRootPubblicazioniData.get(Pubblicazione_.genericMetadataWip)), "").otherwise(cb.function("jsonb_extract_path_text", 
				String.class, 
				subRootPubblicazioniData.get(Pubblicazione_.genericMetadataWip), 
				cb.literal("tipoScheda"))).as(String.class));


		//subquery per concatenare le redazioni
		Subquery<String> sqPubblicazioneStato = cq.subquery(String.class);
		Root<Pubblicazione> subRootPubblicazioniStato= sqPubblicazioneStato.from(Pubblicazione.class);
		Join<Pubblicazione,Redazione> joinRedazioneStato = subRootPubblicazioniStato.join(Pubblicazione_.redazione,JoinType.INNER);

		//		sqPubblicazioneStato.where(cb.and(cb.equal(subRootPubblicazioniStato.get(Pubblicazione_.evento), root) , cb.equal(joinRedazioneStato.get(Redazione_.redazioneId), filter.getRedazioneAttuale())));
		sqPubblicazioneStato.where(cb.and(cb.equal(subRootPubblicazioniStato.get(Pubblicazione_.evento), root) , cb.equal(joinRedazioneStato.get(Redazione_.redazioneId),redazione)));

		//		sqPubblicazioneStato.select(cb.selectCase().
		//				when(cb.isNull(subRootPubblicazioniStato.get(Pubblicazione_.pubblicazioneId)), "In attesa di redazione").
		//				otherwise(subRootPubblicazioniStato.get(Pubblicazione_.pubblicazioneId)).as(String.class));

		sqPubblicazioneStato.select(cb.selectCase().when(cb.isNull(subRootPubblicazioniStato.get(Pubblicazione_.pubblicazioneId)), "").otherwise(
				subRootPubblicazioniStato.get(Pubblicazione_.statoPubblicazione)).as(String.class));




		Subquery<String> sqPubblicazioneLog = cq.subquery(String.class);
		Root<LogPubblicazioni> subRootPubblicazioniLog= sqPubblicazioneLog.from(LogPubblicazioni.class);
		Join<LogPubblicazioni, Pubblicazione> joinLog= subRootPubblicazioniLog.join(LogPubblicazioni_.pubblicazione,JoinType.INNER);
		Join<Pubblicazione,Redazione> joinRedazioneLog = joinLog.join(Pubblicazione_.redazione,JoinType.INNER);
		Join<LogPubblicazioni, StatiLogPubblicazione> joinStatiLog = subRootPubblicazioniLog.join(LogPubblicazioni_.stato,JoinType.INNER);

		//		sqPubblicazioneStato.where(cb.and(cb.equal(subRootPubblicazioniStato.get(Pubblicazione_.evento), root) , cb.equal(joinRedazioneStato.get(Redazione_.redazioneId), filter.getRedazioneAttuale())));
		sqPubblicazioneLog.where(cb.and(cb.equal(joinLog.get(Pubblicazione_.evento), root) , cb.equal(joinRedazioneLog.get(Redazione_.redazioneId), redazione), cb.equal(joinStatiLog.get(StatiLogPubblicazione_.statoId), "RIPUBBLICATO")));

		//		sqPubblicazioneStato.select(cb.selectCase().
		//				when(cb.isNull(subRootPubblicazioniStato.get(Pubblicazione_.pubblicazioneId)), "In attesa di redazione").
		//				otherwise(subRootPubblicazioniStato.get(Pubblicazione_.pubblicazioneId)).as(String.class));

		sqPubblicazioneLog.select(cb.function("count", Long.class, subRootPubblicazioniLog.get(LogPubblicazioni_.logId)).as(String.class));
	
		
		
		


		Subquery<Long> sqPubblicazioneLogRed = cq.subquery(Long.class);
		Root<LogPubblicazioni> subRootPubblicazioniLogRed= sqPubblicazioneLogRed.from(LogPubblicazioni.class);
		Join<LogPubblicazioni, Pubblicazione> joinLogRed= subRootPubblicazioniLogRed.join(LogPubblicazioni_.pubblicazione,JoinType.INNER);
		Join<Pubblicazione,Redazione> joinRedazioneLogRed = joinLogRed.join(Pubblicazione_.redazione,JoinType.INNER);
		Join<LogPubblicazioni, StatiLogPubblicazione> joinStatiLogRed = subRootPubblicazioniLogRed.join(LogPubblicazioni_.stato,JoinType.INNER);

		
		//		sqPubblicazioneStato.where(cb.and(cb.equal(subRootPubblicazioniStato.get(Pubblicazione_.evento), root) , cb.equal(joinRedazioneStato.get(Redazione_.redazioneId), filter.getRedazioneAttuale())));
		sqPubblicazioneLogRed.where(cb.and(cb.and(cb.equal(joinLogRed.get(Pubblicazione_.evento), root) , cb.equal(joinRedazioneLogRed.get(Redazione_.redazioneId), redazione), cb.equal(joinStatiLogRed.get(StatiLogPubblicazione_.statoId), "IN_COMPILAZIONE")),cb.and(cb.notLike(root.get(Evento_.stato).get(Stato_.statoId), "VALIDATO"), cb.notLike(root.get(Evento_.stato).get(Stato_.statoId), "RIVALIDATO"))));

		
		sqPubblicazioneLogRed.select(joinLogRed.get(Pubblicazione_.evento).get(Evento_.eventoId));
		//		sqPubblicazioneStato.select(cb.selectCase().
		//				when(cb.isNull(subRootPubblicazioniStato.get(Pubblicazione_.pubblicazioneId)), "In attesa di redazione").
		//				otherwise(subRootPubblicazioniStato.get(Pubblicazione_.pubblicazioneId)).as(String.class));

	
	
	
	
		//subquerylog pubblicazione data modifica
		Subquery<String> sqPubblicazioneMIB = cq.subquery(String.class);
		Root<Pubblicazione> subRootPubblicazioniMIB = sqPubblicazioneMIB.from(Pubblicazione.class);
		Join<Pubblicazione, Redazione> joinRedazioneMIB = subRootPubblicazioniMIB.join(Pubblicazione_.redazione,
				JoinType.INNER);
		//		sqPubblicazioneMIB.where(cb.and(cb.equal(subRootPubblicazioniMIB.get(Pubblicazione_.evento), root) , cb.equal(joinRedazioneMIB.get(Redazione_.redazioneId), filter.getRedazioneAttuale())));
		sqPubblicazioneMIB.where(cb.and(cb.equal(subRootPubblicazioniMIB.get(Pubblicazione_.evento), root),
				cb.equal(joinRedazioneMIB.get(Redazione_.redazioneId), redazione)));
		sqPubblicazioneMIB.select(
				cb.selectCase().when(cb.isNull(subRootPubblicazioniMIB.get(Pubblicazione_.genericMetadataWip)), "")
				.otherwise(cb.selectCase()
						.when(cb.isNull(cb.function("jsonb_extract_path_text", String.class,
								subRootPubblicazioniMIB.get(Pubblicazione_.genericMetadataWip),
								cb.literal("tipologieMIBACT"))), "")
						.otherwise(
								cb.function("jsonb_extract_path_text", String.class,
										subRootPubblicazioniMIB.get(Pubblicazione_.genericMetadataWip),
										cb.literal("tipologieMIBACT"))))
				.as(String.class));
		
		//subquery prodotti
		Subquery<String> sqPubblicazioneProdotti = cq.subquery(String.class);
		Root<Pubblicazione> subRootPubblicazioniProdotti = sqPubblicazioneProdotti.from(Pubblicazione.class);
		Join<Pubblicazione, Redazione> joinRedazioneProdotti = subRootPubblicazioniProdotti.join(Pubblicazione_.redazione,
				JoinType.INNER);
		sqPubblicazioneProdotti.where(cb.and(cb.equal(subRootPubblicazioniProdotti.get(Pubblicazione_.evento), root),
				cb.equal(joinRedazioneProdotti.get(Redazione_.redazioneId), redazione)));
		sqPubblicazioneProdotti.select(
				cb.selectCase().when(cb.isNull(subRootPubblicazioniProdotti.get(Pubblicazione_.genericMetadataWip)), "")
				.otherwise(cb.selectCase()
						.when(cb.isNull(cb.function("jsonb_extract_path_text", String.class,
								subRootPubblicazioniProdotti.get(Pubblicazione_.genericMetadataWip),
								cb.literal("prodottiSet"))), "")
						.otherwise(
								cb.function("jsonb_extract_path_text", String.class,
										subRootPubblicazioniProdotti.get(Pubblicazione_.genericMetadataWip),
										cb.literal("prodottiSet"))))
				.as(String.class));
		
		//subquery tipologieAttivita
		Subquery<String> sqPubblicazioneTipologieAttivita = cq.subquery(String.class);
		Root<Pubblicazione> subRootPubblicazioniTipologieAttivita = sqPubblicazioneTipologieAttivita.from(Pubblicazione.class);
		Join<Pubblicazione, Redazione> joinRedazioneTipologieAttivita = subRootPubblicazioniTipologieAttivita.join(Pubblicazione_.redazione,
				JoinType.INNER);
		sqPubblicazioneTipologieAttivita.where(cb.and(cb.equal(subRootPubblicazioniTipologieAttivita.get(Pubblicazione_.evento), root),
				cb.equal(joinRedazioneTipologieAttivita.get(Redazione_.redazioneId), redazione)));
		sqPubblicazioneTipologieAttivita.select(
				cb.selectCase().when(cb.isNull(subRootPubblicazioniTipologieAttivita.get(Pubblicazione_.genericMetadataWip)), "")
				.otherwise(cb.selectCase()
						.when(cb.isNull(cb.function("jsonb_extract_path_text", String.class,
								subRootPubblicazioniTipologieAttivita.get(Pubblicazione_.genericMetadataWip),
								cb.literal("tipologieAttivitaSet"))), "")
						.otherwise(
								cb.function("jsonb_extract_path_text", String.class,
										subRootPubblicazioniTipologieAttivita.get(Pubblicazione_.genericMetadataWip),
										cb.literal("tipologieAttivitaSet"))))
				.as(String.class));

		if (isCount) {
			cq.select((Selection<? extends T>) cb.count(root));
		} else {
			cq.select(cb.construct(classe, 
					root.get(Evento_.eventoId).as(String.class),//eventoId 1
					root.get(Evento_.tipo),//tipo 2
					joinDatiGenerali.get(DatiGenerali_.titolo),//titolo 3
					(redazione.matches("")) ? root.get(Evento_.tipologiaMIBACT) : 	cb.selectCase().when(cb.isNull(sqPubblicazioneMIB.getSelection()),cb.concat(root.get(Evento_.idMIBACT), cb.concat(":",root.get(Evento_.tipologiaMIBACT)))).otherwise(sqPubblicazioneMIB.getSelection()),//tipologieMibact 4
					cb.selectCase().when(cb.isNull(joinUtente.get(DettaglioUtente_.nome)), joinUtente.get(DettaglioUtente_.username)).otherwise(cb.concat(cb.concat(joinUtente.get(DettaglioUtente_.nome), cb.literal(" ")),joinUtente.get(DettaglioUtente_.cognome))),//owner 5
					cb.selectCase().when(cb.isNull(joinUtente.get(DettaglioUtente_.email)), cb.literal("")).otherwise(joinUtente.get(DettaglioUtente_.email)),//emailOwner 6
					cb.selectCase().when(cb.isNull(cb.function(CommonUtility.to_char, String.class, root.get(Evento_.dataDaMin), cb.literal(CommonUtility.italianDateFormat)))," ").otherwise(cb.function(CommonUtility.to_char, String.class, root.get(Evento_.dataDaMin), cb.literal(CommonUtility.italianDateFormat))),//dataDa  7
					cb.selectCase().when(cb.isNull(cb.function(CommonUtility.to_char, String.class, root.get(Evento_.dataAMax), cb.literal(CommonUtility.italianDateFormat)))," ").otherwise(cb.function(CommonUtility.to_char, String.class, root.get(Evento_.dataAMax), cb.literal(CommonUtility.italianDateFormat))),//dataA 8
					cb.selectCase().when(cb.isNull(joinLocationSet.get(Location_.comune)),cb.selectCase().when(cb.isNull(joinLocationSet.get(Location_.comuneEstero))," ").otherwise(sqComuni.getSelection())).otherwise(sqComuni.getSelection()), //comune 9
					cb.function(CommonUtility.to_char, String.class, root.get(Evento_.dataIns), cb.literal(CommonUtility.italianDateFormat)),//dataIns 10
					cb.function(CommonUtility.to_char, String.class, root.get(Evento_.dataMod), cb.literal(CommonUtility.italianDateFormat)),//dataMod 11
					cb.selectCase().when(cb.isNull(sqRedazioniDataMod.getSelection()),cb.function(CommonUtility.to_char, String.class, root.get(Evento_.dataMod), cb.literal(CommonUtility.ukDateFormatSec))).otherwise(sqRedazioniDataMod.getSelection()),//dataPubblicazione 12
					joinStato.get(Stato_.descrizione),//stato 13
					cb.selectCase().when(cb.isTrue(root.get(Evento_.fgFinanziato)), "Si").otherwise("No"),//finanziato 14
					cb.selectCase().when(cb.isTrue(root.get(Evento_.fgPubblicato)), sqRedazioni.getSelection()).otherwise(" "),//redazioni 15
					cb.selectCase().when(cb.isNotEmpty(root.get(Evento_.relazioneSet)), sqRelazioni.getSelection()).otherwise(" "),//relazioni 16
					cb.selectCase().when(cb.equal(root.get(Evento_.stato).get(Stato_.statoId), "LAVORAZIONE"), sqOwnerUltimaModifica.getSelection()).otherwise(" "),//ownerlock 17
					//cb.selectCase().when(cb.like(sqRedazioni.getSelection(),filter.getRedazioneAttuale()), "SI").otherwise("NO"),//pubblicatoredazione 18
					cb.selectCase().when(cb.isTrue(root.get(Evento_.fgPubblicato)), "SI").otherwise("NO"),
					sqPubblicazioneLog.getSelection(),//ripubblicatoredazione 19
					cb.selectCase().when(cb.isNotNull(root.get(Evento_.attivita)), joinAttivita.get(Attivita_.denominazione)).otherwise(joinRichiestaAttivita.get(RichiestaAttivita_.denominazione)),//entitaOwner 20
					cb.selectCase().when(cb.like(sqPubblicazione.getSelection() , "evento"), "Evento").otherwise(cb.selectCase().when(cb.like(sqPubblicazione.getSelection() , "attivita"), "Attivita").otherwise(" ")),//tipologiaScheda 21
					cb.selectCase().when(cb.isNull(sqPubblicazioneStato.getSelection()), "IN ATTESA DI REDAZIONE").otherwise(cb.selectCase().when(cb.equal(sqPubblicazioneStato.getSelection(), "BOZZA") , "IN COMPILAZIONE").otherwise(sqPubblicazioneStato.getSelection())),//statoPublicazione 22
					sqPubblicazioneProdotti.getSelection(),//prodotti 23
					sqPubblicazioneTipologieAttivita.getSelection(),//tipologieAttivita 24
					cb.selectCase().when(cb.isNull(root.get(Evento_.attivita)), cb.selectCase().when(cb.isNotNull(root.get(Evento_.richiestaAttivita)),"IN VALIDAZIONE").otherwise("VALIDATO")).otherwise("VALIDATO"),//statoImpresa 25
					("FIN".equals(filter.getServiceCode())) ? joinBando.get(Bando_.titoloBando) : 			root.get(Evento_.bando).get(Bando_.bandoId).as(String.class), //inserito idbando come segnaposto non sapendo come mettere un null //titoloBando 26 
					("FIN".equals(filter.getServiceCode())) ? joinProgetto.get(Progetto_.titoloProgetto) : 	root.get(Evento_.progetto).get(Progetto_.progettoId).as(String.class) //inserito idbando come segnaposto non sapendo come mettere un null //titoloProgetto 27
					));
		}



		List<Predicate> predicates = new ArrayList<Predicate>();

		
		predicates.add(cb.isNotNull(root.get(Evento_.stato)));

		if("FIN".equals(filter.getServiceCode()) && 
				filter.getProfilo()!=null && 
					!(filter.getProfilo().equalsIgnoreCase("PROMUOVI_EVENTO"))) {
			predicates.add(cb.not(cb.equal(root.get(Evento_.stato).get(Stato_.statoId), "BOZZA")));
		}

		if("VAL".equals(filter.getServiceCode())) {
			predicates.add(cb.not(cb.equal(root.get(Evento_.stato).get(Stato_.statoId), "BOZZA")));
		}
		if("RED".equals(filter.getServiceCode())) {
			
			
//			if(filter.getStatoPubblicazione()!=null && "SOSPESO".equalsIgnoreCase(filter.getStatoPubblicazione()))
//			{
//				In<Long> presiInCarico = cb.in(root.get(Evento_.eventoId));
//				presiInCarico.value(sqPubblicazioneLogRed.getSelection());
//				
//				
//				predicates.add(presiInCarico);	
//			}
//			else {
			In<Long> presiInCarico = cb.in(root.get(Evento_.eventoId));
			presiInCarico.value(sqPubblicazioneLogRed.getSelection());
			
			
			In<String> inClause = cb.in(root.get(Evento_.stato).get(Stato_.statoId));
			inClause.value("VALIDATO");
			inClause.value("RIVALIDATO");
			
//			predicates.add(inClause);
			
			Predicate or = cb.or(presiInCarico, inClause);
			predicates.add(or);
//			}
		}

		if (filter.getIdUtenteIns()!=null) {
			predicates.add(cb.equal(joinUtente.get(DettaglioUtente_.utenteId), filter.getIdUtenteIns()));
		}

		if (filter.getTipologia()!=null) {
			predicates.add(cb.equal(root.get(Evento_.tipo), filter.getTipologia()));
		}

		if (filter.getIdAttivita()!=null) {
			predicates.add(cb.equal(joinAttivita.get(Attivita_.attivitaId), filter.getIdAttivita()));
		}
		
		
		if(filter.getProdottiNome()!=null && (filter.getProdottiNome().size()>0))
		{
			List<Predicate> prodottiPredicates = new ArrayList<Predicate>();

			for(String prodotto: filter.getProdottiNome()) {

				prodotto = prodotto.toUpperCase();
				
				prodottiPredicates.add(cb.like(cb.upper(sqPubblicazioneProdotti.getSelection()), "%" + prodotto+ "%"));
				
			}
				predicates.add(cb.or(prodottiPredicates.toArray(new Predicate[prodottiPredicates.size()])));
			//				predicates.add(cb.equal(cb.upper(sqPubblicazione.getSelection()) , tipologia2));
				
		}


		if (filter.getIdRichiestaAttivita()!=null) {
			predicates.add(cb.equal(joinRichiestaAttivita.get(RichiestaAttivita_.richiestaAttivitaId), filter.getIdRichiestaAttivita()));
		}

		if (filter.getEventoId()!=null) {
			predicates.add(cb.equal(root.get(Evento_.eventoId), filter.getEventoId()));
		}

		if (filter.getTitolo()!=null) {
			predicates.add(cb.like(cb.upper(joinDatiGenerali.get(DatiGenerali_.titolo)),"%" + filter.getTitolo().toUpperCase() + "%"));
		}

		//		if (filter.getIdMIBACT()!=null) {
		//			predicates.add(cb.equal(root.get(Evento_.idMIBACT), filter.getIdMIBACT()));
		//		}

		if (filter.getFiltroMIBACT() != null && !filter.getFiltroMIBACT().isEmpty()) {
			if("RED".equals(filter.getServiceCode()) && (filter.getStatoPubblicazione()==null || !filter.getStatoPubblicazione().equalsIgnoreCase("In attesa di redazione")) ) {
				//subquery per concatenare le redazioni
				


				List<Predicate> tipoMIBPredicates = new ArrayList<Predicate>();

				for(String tipologia : filter.getFiltroMIBACT()) {

					tipoMIBPredicates.add(cb.like(cb.upper(cb.function("jsonb_extract_path_text", 
							String.class, 
							joinPubblicazione.get(Pubblicazione_.genericMetadataWip), 
							cb.literal("tipologieMIBACT"))).as(String.class), "%" + tipologia + "%"));
				}
					predicates.add(cb.or(tipoMIBPredicates.toArray(new Predicate[tipoMIBPredicates.size()])));
				//				predicates.add(cb.equal(cb.upper(sqPubblicazione.getSelection()) , tipologia2));
			}
			else
			{
				predicates.add(root.get(Evento_.idMIBACT).in(filter.getFiltroMIBACT()));
			}
		}



		if (filter.getDataInsDa() != null) {
			Expression<Date> columnDate = cb.function(CommonUtility.to_date, Date.class, cb.function(CommonUtility.to_char, String.class, root.get(Evento_.dataIns),cb.literal(CommonUtility.ukDateFormat)), cb.literal(CommonUtility.ukDateFormat));
			Expression<Date> filterDate = cb.function(CommonUtility.to_date, Date.class, cb.literal(filter.getDataInsDa()), cb.literal(CommonUtility.ukDateFormat));
			predicates.add(cb.greaterThanOrEqualTo(columnDate,filterDate));
		}

		if (filter.getDataInsA() != null) {
			Expression<Date> columnDate = cb.function(CommonUtility.to_date, Date.class, cb.function(CommonUtility.to_char, String.class, root.get(Evento_.dataIns),cb.literal(CommonUtility.ukDateFormat)), cb.literal(CommonUtility.ukDateFormat));
			Expression<Date> filterDate = cb.function(CommonUtility.to_date, Date.class, cb.literal(filter.getDataInsA()), cb.literal(CommonUtility.ukDateFormat));
			predicates.add(cb.lessThanOrEqualTo(columnDate,filterDate));
		}

		if (filter.getEmailOwner() != null) {
			predicates.add(cb.like(cb.upper(joinUtente.get(DettaglioUtente_.email)),"%" + filter.getEmailOwner().toUpperCase() + "%"));
		}

		if (filter.getCognomeOwner() != null) {
			predicates.add(cb.like(cb.upper(joinUtente.get(DettaglioUtente_.cognome)),"%" + filter.getCognomeOwner().toUpperCase() + "%"));
		}

		if (filter.getDenominazioneAttivita() != null) {
			List<Predicate> orPredicates = new ArrayList<>();
			orPredicates.add(cb.like(cb.upper(joinAttivita.get(Attivita_.denominazione)),"%" + filter.getDenominazioneAttivita().replaceAll("'", "%").toUpperCase() + "%"));
			orPredicates.add(cb.like(cb.upper(joinRichiestaAttivita.get(RichiestaAttivita_.denominazione)),"%" + filter.getDenominazioneAttivita().toUpperCase() + "%"));
			predicates.add(cb.or(orPredicates.toArray(new Predicate[0])));
		}

		if (filter.getPubblicato() != null) {
			if(filter.getPubblicato().equalsIgnoreCase("ONLINE")) {
				predicates.add(cb.isTrue(root.get(Evento_.fgPubblicato)));
			}else {
				predicates.add(cb.isFalse(root.get(Evento_.fgPubblicato)));
			}
		}

		if (filter.getFinanziato() != null) {
			if(filter.getFinanziato().toUpperCase().startsWith("S")) {
				predicates.add(cb.isTrue(root.get(Evento_.fgFinanziato)));
			}else {
				predicates.add(cb.isFalse(root.get(Evento_.fgFinanziato)));
			}
		}

		if (filter.getProgettoId() != null) {
			predicates.add(cb.equal(root.get(Evento_.progetto).get(Progetto_.progettoId),filter.getProgettoId()));
		}
		
		if (filter.getBandoId() != null) {
			predicates.add(cb.equal(root.get(Evento_.bando).get(Bando_.bandoId),filter.getBandoId()));
		}

		if (filter.getStato() != null) {
			predicates.add(cb.equal(joinStato.get(Stato_.statoId), filter.getStato()));
		}

		if (filter.getDataDa() != null) {
			Expression<Date> columnDate = cb.function(CommonUtility.to_date, Date.class, cb.function(CommonUtility.to_char, String.class, root.get(Evento_.dataDaMin),cb.literal(CommonUtility.ukDateFormat)), cb.literal(CommonUtility.ukDateFormat));
			Expression<Date> filterDate = cb.function(CommonUtility.to_date, Date.class, cb.literal(filter.getDataDa()), cb.literal(CommonUtility.ukDateFormat));
			predicates.add(cb.greaterThanOrEqualTo(columnDate,filterDate));
		}

		if (filter.getDataA() != null) {
			Expression<Date> columnDate = cb.function(CommonUtility.to_date, Date.class, cb.function(CommonUtility.to_char, String.class, root.get(Evento_.dataAMax),cb.literal(CommonUtility.ukDateFormat)), cb.literal(CommonUtility.ukDateFormat));
			Expression<Date> filterDate = cb.function(CommonUtility.to_date, Date.class, cb.literal(filter.getDataA()), cb.literal(CommonUtility.ukDateFormat));
			predicates.add(cb.lessThanOrEqualTo(columnDate, filterDate));
		}

		if(filter.getCodComuneSet() != null || filter.getCodProvinciaSet() != null || filter.getCodArea() != null || filter.getCodRegione() != null || filter.getComuneEstero()!=null || filter.getCodNazione() != null) {
			Subquery<Location> sq = cq.subquery(Location.class);
			Root<Location> subRoot = sq.from(Location.class);
			Join<Location, Evento> joinEvento = subRoot.join(Location_.evento, JoinType.INNER);
			sq.select(subRoot);

			List<Predicate> subPredicates = new ArrayList<Predicate>();

			subPredicates.add(cb.equal(joinEvento.get(Evento_.eventoId), root.get(Evento_.eventoId)));

			if (filter.getCodNazione() != null) {
				subPredicates.add(cb.equal(subRoot.get(Location_.codNazione), filter.getCodNazione()));
			}

			if (filter.getCodRegione() != null) {
				subPredicates.add(cb.equal(subRoot.get(Location_.codRegione), filter.getCodRegione()));
			}

			if (filter.getCodArea() != null) {
				subPredicates.add(cb.equal(subRoot.get(Location_.codArea), filter.getCodArea()));
			}

			if (filter.getCodProvinciaSet() != null) {
				subPredicates.add(subRoot.get(Location_.codProvincia).in(filter.getCodProvinciaSet()));
			}

			if (filter.getCodComuneSet() != null) {
				subPredicates.add(subRoot.get(Location_.codComune).in(filter.getCodComuneSet()));
			}

			if (filter.getComuneEstero() != null) {
				subPredicates.add(cb.like(cb.upper(subRoot.get(Location_.comuneEstero)),"%" + filter.getComuneEstero().toUpperCase() + "%"));
			}

			sq.where(subPredicates.toArray(new Predicate[0]));

			predicates.add(cb.exists(sq));
		}

		if(filter.getRedazioniGenerali() != null && !filter.getRedazioniGenerali().isEmpty()) {
			//			Subquery<Evento> sqPubb = cq.subquery(Evento.class);
			//			Root<Pubblicazione> subRootPubb = sqPubb.from(Pubblicazione.class);
			//			Join<Pubblicazione,Redazione> joinRedaz = subRootPubb.join(Pubblicazione_.redazione,JoinType.INNER);
			//			
			//			List<Predicate> subPredicatesPubb = new ArrayList<Predicate>();
			//			subPredicatesPubb.add(cb.equal(subRootPubb.get(Pubblicazione_.evento), root));
			//			subPredicatesPubb.add(joinRedaz.get(Redazione_.nome).in(filter.getRedazioniGenerali()));
			//			subPredicatesPubb.add(cb.isNotNull(subRootPubb.get(Pubblicazione_.genericMetadata)));
			//			
			//			sqPubb.where(subPredicatesPubb.toArray(new Predicate[0]));
			//			sqPubb.select(subRootPubb.get(Pubblicazione_.evento));
			//			
			//			predicates.add(root.in(sqPubb.getSelection()));

			predicates.add(joinRedazioneDataR.get(Redazione_.nome).in(filter.getRedazioniGenerali()));
			predicates.add(cb.isNotNull(joinPubblicazione.get(Pubblicazione_.genericMetadata)));


		}

		if(filter.getRedazioni() != null && !filter.getRedazioni().isEmpty()) {
			//			Subquery<Evento> sqPubb = cq.subquery(Evento.class);
			//			Root<Pubblicazione> subRootPubb = sqPubb.from(Pubblicazione.class);
			//			Join<Pubblicazione,Redazione> joinRedaz = subRootPubb.join(Pubblicazione_.redazione,JoinType.INNER);
			//			
			//			List<Predicate> subPredicatesPubb = new ArrayList<Predicate>();
			//			subPredicatesPubb.add(cb.equal(subRootPubb.get(Pubblicazione_.evento), root));
			//			subPredicatesPubb.add(joinRedaz.get(Redazione_.nome).in(filter.getRedazioni()));
			//			
			//			sqPubb.where(subPredicatesPubb.toArray(new Predicate[0]));
			//			sqPubb.select(subRootPubb.get(Pubblicazione_.evento));
			//			
			//			predicates.add(root.in(sqPubb.getSelection()));

			predicates.add(joinRedazioneDataR.get(Redazione_.nome).in(filter.getRedazioni()));
			predicates.add(cb.isNotNull(joinPubblicazione.get(Pubblicazione_.genericMetadata)));
		}

		if(filter.getStatoPubblicazione() != null && !filter.getStatoPubblicazione().isEmpty() ) {
			
//			if(!"SOSPESO".equalsIgnoreCase(filter.getStatoPubblicazione())){
			Subquery<Evento> sqPubbStato = cq.subquery(Evento.class);
			Root<Pubblicazione> subRootPubbStato = sqPubbStato.from(Pubblicazione.class);
			Join<Pubblicazione,Redazione> joinRedaz = subRootPubbStato.join(Pubblicazione_.redazione,JoinType.INNER);
			sqPubbStato.select(subRootPubbStato.get(Pubblicazione_.evento));

			if (filter.getStatoPubblicazione().equalsIgnoreCase("In attesa di redazione")) {
				List<Predicate> subPredicatesPubbStato = new ArrayList<Predicate>();
				subPredicatesPubbStato.add(cb.equal(joinRedaz.get(Redazione_.redazioneId), redazione));
				sqPubbStato.where(subPredicatesPubbStato.toArray(new Predicate[0]));
				predicates.add(root.in(sqPubbStato.getSelection()).not());

				//				predicates.add(cb.notEqual(joinRedazioneDataR.get(Redazione_.redazioneId),redazione));

			}else {
				//				List<Predicate> subPredicatesPubbStato = new ArrayList<Predicate>();
				//				subPredicatesPubbStato.add(cb.equal(joinRedaz.get(Redazione_.redazioneId),redazione));
				//				subPredicatesPubbStato.add(cb.equal(subRootPubbStato.get(Pubblicazione_.evento), root));
				//				subPredicatesPubbStato.add(cb.equal(subRootPubbStato.get(Pubblicazione_.statoPubblicazione), StatoPubblicazione.of(filter.getStatoPubblicazione())));

				predicates.add(cb.equal(joinRedazioneDataR.get(Redazione_.redazioneId),redazione));
				predicates.add(cb.equal(joinPubblicazione.get(Pubblicazione_.statoPubblicazione),StatoPubblicazione.of(filter.getStatoPubblicazione())));

				//	sqPubbStato.where(subPredicatesPubbStato.toArray(new Predicate[0]));
				//	predicates.add(root.in(sqPubbStato.getSelection()));
			}
//			}
		}

		if(filter.getTipologia2() != null && !filter.getTipologia2().isEmpty())
		{
			String tipologia2 = filter.getTipologia2().replaceAll("'","");
			predicates.add(cb.equal(cb.upper(cb.function("jsonb_extract_path_text", 
					String.class, 
					joinPubblicazione.get(Pubblicazione_.genericMetadataWip), 
					cb.literal("tipoScheda"))).as(String.class), tipologia2));


			//			cb.selectCase().when(cb.like(sqPubblicazione.getSelection() , "evento"), "Evento").otherwise(
			//					cb.selectCase().when(cb.like(sqPubblicazione.getSelection() , "attivita"), "Attivita").otherwise(sqPubblicazione.getSelection()))
		}
		
		
		if (filter.getDataAttivoDa() != null) {
			Expression<Date> columnDate = cb.function(CommonUtility.to_date, Date.class, cb.function(CommonUtility.to_char, String.class, root.get(Evento_.dataAMax),cb.literal(CommonUtility.ukDateFormat)), cb.literal(CommonUtility.ukDateFormat));
			Expression<Date> filterDate = cb.function(CommonUtility.to_date, Date.class, cb.literal(filter.getDataAttivoDa()), cb.literal(CommonUtility.ukDateFormat));
			predicates.add(cb.greaterThanOrEqualTo(columnDate,filterDate));
		}

		if (filter.getDataAttivoA() != null) {
			Expression<Date> columnDate = cb.function(CommonUtility.to_date, Date.class, cb.function(CommonUtility.to_char, String.class, root.get(Evento_.dataDaMin),cb.literal(CommonUtility.ukDateFormat)), cb.literal(CommonUtility.ukDateFormat));
			Expression<Date> filterDate = cb.function(CommonUtility.to_date, Date.class, cb.literal(filter.getDataAttivoA()), cb.literal(CommonUtility.ukDateFormat));
			predicates.add(cb.lessThanOrEqualTo(columnDate,filterDate));
		}

		if (addSearch) {
			String globalSearch = request.getDetail().getSearch();
			List<Predicate> searchPredicates = new ArrayList<>();
			if (globalSearch != null && !globalSearch.equals("")) {
				
				searchPredicates.add(cb.like(cb.upper(joinDatiGenerali.get(DatiGenerali_.titolo)), "%" + globalSearch.toUpperCase().trim() + "%"));
				searchPredicates.add(cb.like(cb.upper(cb.<String>selectCase().when(cb.isNull(joinUtente.get(DettaglioUtente_.nome)), joinUtente.get(DettaglioUtente_.username)).otherwise(cb.concat(cb.concat(joinUtente.get(DettaglioUtente_.nome), cb.literal(" ")),joinUtente.get(DettaglioUtente_.cognome)))), "%" + globalSearch.toUpperCase().trim() + "%"));
				searchPredicates.add(cb.like(cb.upper(joinLocationSet.get(Location_.comune)),"%" + globalSearch.toUpperCase().trim() + "%"));
				searchPredicates.add(cb.like(cb.upper(cb.<String>selectCase().when(cb.isNull(joinLocationSet.get(Location_.comune))," ").otherwise(sqComuni.getSelection())),"%" + globalSearch.toUpperCase().trim() + "%"));
				System.out.println("SERVICE CODE "+ filter.getServiceCode());
				
				if ("PROM".equals(filter.getServiceCode()) || filter.getServiceCode()==null){
					searchPredicates.add(cb.like(cb.upper(root.get(Evento_.eventoId).as(String.class)),"%" + globalSearch.toUpperCase().trim() + "%"));
					searchPredicates.add(cb.like(cb.upper(root.get(Evento_.tipo)),"%" + globalSearch.toUpperCase().trim() + "%"));
					searchPredicates.add(cb.like(cb.upper(joinStato.get(Stato_.descrizione)),"%" + globalSearch.toUpperCase().trim() + "%"));
					searchPredicates.add(cb.like(cb.upper(cb.<String>selectCase().when(cb.isTrue(root.get(Evento_.fgPubblicato)), sqRedazioni.getSelection()).otherwise(" ")),"%" + globalSearch.toUpperCase().trim() + "%"));
					}
			}
			if (searchPredicates.size() > 0) {
				predicates.add(cb.or(searchPredicates.toArray(new Predicate[0])));
			}
		}

		cq.where(predicates.toArray(new Predicate[0]));


		if (!isCount && request.getDetail().getOrder() != null) {
			String orderby = request.getDetail().getOrder();
			String direction = request.getDetail().getDir();
			Expression<?> orderExpression = null;
			if (orderby.equals(EventoModelList.Fields.eventoId)) {
				orderExpression = root.get(Evento_.eventoId);
			} else if (orderby.equals(EventoModelList.Fields.tipo)) {
				orderExpression = root.get(Evento_.tipo);
			} else if (orderby.equals(EventoModelList.Fields.titolo)) {
				orderExpression = joinDatiGenerali.get(DatiGenerali_.titolo);
			} else if (orderby.equals(EventoModelList.Fields.tipologieMibact)) {
				orderExpression = cb.literal(4);
			} else if (orderby.equals(EventoModelList.Fields.owner)) {
				orderExpression = cb.literal(5);
			} else if (orderby.equals(EventoModelList.Fields.dataDa)) {
				orderExpression = root.get(Evento_.dataDaMin);
			} else if (orderby.equals(EventoModelList.Fields.dataA)) {
				orderExpression = root.get(Evento_.dataAMax);
			} else if (orderby.equals(EventoModelList.Fields.comune)) {
				orderExpression = cb.literal(9);
			} else if (orderby.equals(EventoModelList.Fields.dataIns)) {
				orderExpression = root.get(Evento_.dataIns);
			} else if (orderby.equals(EventoModelList.Fields.dataMod)) {
				orderExpression = root.get(Evento_.dataMod);
			} else if (orderby.equals(EventoModelList.Fields.dataPubblicazione)) {
				orderExpression = cb.literal(12);
			} else if (orderby.equals(EventoModelList.Fields.stato)) {
				orderExpression = joinStato.get(Stato_.descrizione);
			} else if (orderby.equals(EventoModelList.Fields.finanziato)) {
				orderExpression = cb.selectCase().when(cb.isTrue(root.get(Evento_.fgFinanziato)), "Sì").otherwise("No");
			} else if (orderby.equals(EventoModelList.Fields.redazioni)) {
				orderExpression = cb.literal(15);
			} else if (orderby.equals(EventoModelList.Fields.titoloBando)) {
				orderExpression = joinBando.get(Bando_.titoloBando);
			} else if (orderby.equals(EventoModelList.Fields.redazioni)) {
				orderExpression = joinProgetto.get(Progetto_.titoloProgetto);
			}


			if (orderExpression != null) {
				Order order = null;
				if (direction != null && direction.equals("asc")) {
					order = cb.asc(orderExpression);
				} else if (direction != null) {
					order = cb.desc(orderExpression);
				}
				cq.orderBy(order);
			}
		}


		if (!isCount) {
			if (request.getDetail().getLength().intValue() == -1) {
				return em.createQuery(cq);
			}else {
				return em.createQuery(cq).setFirstResult(request.getDetail().getStart()).setMaxResults(request.getDetail().getLength());
			}
		} else {
			return em.createQuery(cq);
		}
		
		
		
	}

}