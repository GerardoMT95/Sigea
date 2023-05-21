package it.indra.SigecAPI.repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import it.indra.SigeaCommons.model.BandoModelList;
import it.indra.SigeaCommons.model.SmartModelList;
import it.indra.SigecAPI.entity.Attivita;
import it.indra.SigecAPI.entity.Attivita_;
import it.indra.SigecAPI.entity.Bando;
import it.indra.SigecAPI.entity.Bando_;
import it.indra.SigecAPI.entity.DettaglioUtente;
import it.indra.SigecAPI.entity.Evento;
import it.indra.SigecAPI.entity.Evento_;
import it.indra.SigecAPI.entity.Location;
import it.indra.SigecAPI.entity.Location_;
import it.indra.SigecAPI.entity.Progetto;
import it.indra.SigecAPI.entity.Progetto_;
import it.indra.SigecAPI.entity.Relazione_;
import it.indra.SigecAPI.entity.RichiestaAttivita_;
import it.indra.SigecAPI.entity.Stato_;
import it.indra.SigecAPI.util.CommonUtility;


@Repository
@Transactional
public class EventoRepositoryImpl implements EventoRepositoryCustom
{
	@Autowired
	@PersistenceContext(unitName = "sigecapiEntityManager")
	EntityManager em;

	public List<SmartModelList> findEventoGroupByComuneMoreThan9() {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<SmartModelList> cq = cb.createQuery(SmartModelList.class);
		Root<Evento> evento = cq.from(Evento.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		LocalDate nowDate = LocalDate.now();
		SetJoin<Evento, Location> joinLocationSet = evento.join(Evento_.locationSet, JoinType.INNER);
		
		cq.select(cb.construct(SmartModelList.class,joinLocationSet.get(Location_.codComune),
				joinLocationSet.get(Location_.comune),
				cb.count(evento.get(Evento_.eventoId))));

		predicates.add(cb.isTrue(joinLocationSet.get(Location_.fgPrincipale)));
		Expression<Date> columnDate = cb.function(CommonUtility.to_date, Date.class, cb.function(CommonUtility.to_char, String.class, evento.get(Evento_.dataAMax),cb.literal(CommonUtility.ukDateFormat)), cb.literal(CommonUtility.ukDateFormat));
		Expression<Date> filterDate = cb.function(CommonUtility.to_date, Date.class, cb.literal(nowDate), cb.literal(CommonUtility.ukDateFormat));
		predicates.add(cb.greaterThanOrEqualTo(columnDate, filterDate));
	
		cq.where(predicates.toArray(new Predicate[0]));
		cq.groupBy(joinLocationSet.get(Location_.codComune),joinLocationSet.get(Location_.comune));
		cq.having(cb.greaterThan(cb.count(evento.get(Evento_.eventoId)), 9L));
		
		return em.createQuery(cq).getResultList();
	}
	
	
	public List<SmartModelList> findEventoGroupByComuneMoreThan9(Boolean isRedazione) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<SmartModelList> cq = cb.createQuery(SmartModelList.class);
		Root<Evento> evento = cq.from(Evento.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		LocalDate nowDate = LocalDate.now();
		SetJoin<Evento, Location> joinLocationSet = evento.join(Evento_.locationSet, JoinType.INNER);
		
		cq.select(cb.construct(SmartModelList.class,
				joinLocationSet.get(Location_.codComune),
				joinLocationSet.get(Location_.comune),
				joinLocationSet.get(Location_.codProvincia),
				joinLocationSet.get(Location_.provincia),
				joinLocationSet.get(Location_.codRegione),
				joinLocationSet.get(Location_.regione),
				joinLocationSet.get(Location_.codNazione),
				joinLocationSet.get(Location_.nazione),
				cb.count(evento.get(Evento_.eventoId))));

		predicates.add(cb.isTrue(joinLocationSet.get(Location_.fgPrincipale)));
		Expression<Date> columnDate = cb.function(CommonUtility.to_date, Date.class, cb.function(CommonUtility.to_char, String.class, evento.get(Evento_.dataAMax),cb.literal(CommonUtility.ukDateFormat)), cb.literal(CommonUtility.ukDateFormat));
		Expression<Date> filterDate = cb.function(CommonUtility.to_date, Date.class, cb.literal(nowDate), cb.literal(CommonUtility.ukDateFormat));
		predicates.add(cb.greaterThanOrEqualTo(columnDate, filterDate));
	
		if(isRedazione)
		{
			predicates.add(cb.equal(evento.get(Evento_.stato).get(Stato_.statoId), "VALIDATO"));
		
		}
		
		cq.where(predicates.toArray(new Predicate[0]));
		cq.groupBy(joinLocationSet.get(Location_.codComune),joinLocationSet.get(Location_.comune),
				joinLocationSet.get(Location_.codProvincia),joinLocationSet.get(Location_.provincia),
				joinLocationSet.get(Location_.codRegione),joinLocationSet.get(Location_.regione),
				joinLocationSet.get(Location_.codNazione),joinLocationSet.get(Location_.nazione));
		cq.having(cb.greaterThan(cb.count(evento.get(Evento_.eventoId)), 9L));
		
		List<SmartModelList> smartFilters =  em.createQuery(cq).getResultList();
		
		return smartFilters;
	}
	
	public void updateOwner(Set<Long> listaEventoId, DettaglioUtente owner, Attivita attivita) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaUpdate<Evento> update = cb.createCriteriaUpdate(Evento.class);
		Root<Evento> root = update.from(Evento.class);
		update.set(root.get(Evento_.daRiconciliare), false);
		update.set(root.get(Evento_.owner), owner);
		update.set(root.get(Evento_.attivita), attivita);
        update.where(root.get(Evento_.eventoId).in(listaEventoId));
        em.createQuery(update).executeUpdate();
	}
	
	public void updatePubblicazioneFlag(boolean flag, Long eventoId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaUpdate<Evento> update = cb.createCriteriaUpdate(Evento.class);
		Root<Evento> root = update.from(Evento.class);
		update.set(root.get(Evento_.fgPubblicato), flag);
		update.where(cb.equal(root.get(Evento_.eventoId), eventoId));
		em.createQuery(update).executeUpdate();
	}


	public Set<BandoModelList> findBandiProgetti(Long idAttivita){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<BandoModelList> cq = cb.createQuery(BandoModelList.class);

		Root<Evento> root = cq.from(Evento.class);
		Join<Evento, Bando> joinBando = root.join(Evento_.bando, JoinType.INNER);
		Join<Evento, Progetto> joinProgetto = root.join(Evento_.progetto, JoinType.LEFT);

		cq.select(cb.construct(BandoModelList.class,
				joinBando.get(Bando_.bandoId).as(String.class),
				joinBando.get(Bando_.titoloBando),
				joinProgetto.get(Progetto_.progettoId).as(String.class),
				joinProgetto.get(Progetto_.titoloProgetto),
				joinProgetto.get(Progetto_.nomeOrganizzazione)
				)).distinct(true);


		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (idAttivita!=null) {
			Predicate predicateAttivita
			  = cb.equal(root.get(Evento_.attivita).get(Attivita_.attivitaId), idAttivita);
			Predicate predicateRichiesta
			  = cb.equal(root.get(Evento_.richiestaAttivita).get(RichiestaAttivita_.richiestaAttivitaId), idAttivita);
			Predicate predicateRichiestaAttivita
			  = cb.or(predicateAttivita,predicateRichiesta);
			predicates.add(predicateRichiestaAttivita);
		}else {
			predicates.add(cb.notEqual(root.get(Evento_.stato).get(Stato_.statoId), "BOZZA"));
		}
			cq.where(predicates.toArray(new Predicate[0]));

			List<BandoModelList> bandoModelList =  em.createQuery(cq).getResultList();
			Set<BandoModelList> bandoModelSet = new HashSet<BandoModelList>(bandoModelList);

			return bandoModelSet;
		}
}