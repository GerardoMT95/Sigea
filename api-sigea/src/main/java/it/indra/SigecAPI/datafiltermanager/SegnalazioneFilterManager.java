package it.indra.SigecAPI.datafiltermanager;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import it.indra.SigeaCommons.model.SegnalazioneFilter;
import it.indra.SigeaCommons.model.SegnalazioneModelList;
import it.indra.SigecAPI.entity.DettaglioUtente;
import it.indra.SigecAPI.entity.DettaglioUtente_;
import it.indra.SigecAPI.entity.Segnalazione;
import it.indra.SigecAPI.entity.Segnalazione_;
import it.indra.SigecAPI.util.CommonUtility;
import it.indra.SigecAPI.util.WrappedFilterInterface;
import it.indra.SigecAPI.util.WrapperFilterRequest;

public class SegnalazioneFilterManager implements WrappedFilterInterface {

	@SuppressWarnings({ "unchecked" })
	@Override
	public <T> TypedQuery<T> createDynamicDatatableQuery(EntityManager em, WrapperFilterRequest<?> request,
			boolean isCount, boolean addSearch, Class<T> classe) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(classe);

		Root<Segnalazione> root = cq.from(Segnalazione.class);
		Join<Segnalazione, DettaglioUtente> join = root.join(Segnalazione_.usernameIns, JoinType.INNER);

		SegnalazioneFilter filter = (SegnalazioneFilter) request.getFilter();

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (filter.getIdUtenteIns() != null) {
			predicates.add(cb.equal(join.get(DettaglioUtente_.utenteId), filter.getIdUtenteIns()));
		}
		if (filter.getStatus() != null) {
			predicates.add(cb.like(cb.upper(root.get(Segnalazione_.status)),"%" + filter.getStatus().toUpperCase() + "%"));
		}
		if (filter.getDataDa() != null) {
			Expression<Date> columnDate = cb.function(CommonUtility.to_date, Date.class, cb.function(CommonUtility.to_char, String.class, root.get(Segnalazione_.dataDa),cb.literal(CommonUtility.ukDateFormat)), cb.literal(CommonUtility.ukDateFormat));
			Expression<Date> filterDate = cb.function(CommonUtility.to_date, Date.class, cb.literal(filter.getDataDa()), cb.literal(CommonUtility.ukDateFormat));
			predicates.add(cb.greaterThanOrEqualTo(columnDate,filterDate));
		}
		if (filter.getDataA() != null) {
			Expression<Date> columnDate = cb.function(CommonUtility.to_date, Date.class, cb.function(CommonUtility.to_char, String.class, root.get(Segnalazione_.dataA),cb.literal(CommonUtility.ukDateFormat)), cb.literal(CommonUtility.ukDateFormat));
			Expression<Date> filterDate = cb.function(CommonUtility.to_date, Date.class, cb.literal(filter.getDataA()), cb.literal(CommonUtility.ukDateFormat));
			predicates.add(cb.lessThanOrEqualTo(columnDate,filterDate));
		}
		if (filter.getTitolo() != null) {
			predicates.add(cb.like(cb.upper(root.get(Segnalazione_.nome)),"%" + filter.getTitolo().toUpperCase() + "%"));
		}
		if (filter.getCodIstat() != null) {
			predicates.add(cb.equal(root.get(Segnalazione_.codIstat), filter.getCodIstat()));
		}
		if (filter.getComuneEstero()!= null) {
			predicates.add(cb.like(cb.upper(root.get(Segnalazione_.comune)),"%" + filter.getComuneEstero().toUpperCase() + "%"));
		}

		if (isCount) {
			cq.select((Selection<? extends T>) cb.count(root));
		} else {

			cq.select(cb.construct(classe, 
					root.get(Segnalazione_.segnalazioneId).as(String.class),
					root.get(Segnalazione_.nome),
					cb.function(CommonUtility.to_char, String.class, root.get(Segnalazione_.dataDa), cb.literal(CommonUtility.italianDateFormat)),
					cb.function(CommonUtility.to_char, String.class, root.get(Segnalazione_.dataA), cb.literal(CommonUtility.italianDateFormat)),
					root.get(Segnalazione_.comune), 
					root.get(Segnalazione_.riferimento),
					cb.function(CommonUtility.to_char, String.class, root.get(Segnalazione_.dataIns), cb.literal(CommonUtility.italianDateFormat)),
					root.get(Segnalazione_.status),
					cb.selectCase().when(cb.isNull(join.get(DettaglioUtente_.nome)), join.get(DettaglioUtente_.username)).otherwise(cb.concat(cb.concat(join.get(DettaglioUtente_.nome), cb.literal(" ")),join.get(DettaglioUtente_.cognome))),
					cb.selectCase().when(cb.isNull(join.get(DettaglioUtente_.email)), cb.literal("")).otherwise(join.get(DettaglioUtente_.email))));
	
		}

		if (addSearch) {
			String globalSearch = request.getDetail().getSearch();
			List<Predicate> searchPredicates = new ArrayList<>();
			if (globalSearch != null && !globalSearch.equals("")) {
				searchPredicates.add(cb.like(cb.upper(root.get(Segnalazione_.segnalazioneId).as(String.class)), "%" + globalSearch.toUpperCase().trim() + "%"));
				searchPredicates.add(cb.like(cb.upper(root.get(Segnalazione_.nome)), "%" + globalSearch.toUpperCase().trim() + "%"));
				searchPredicates.add(cb.like(cb.upper(root.get(Segnalazione_.comune)), "%" + globalSearch.toUpperCase().trim() + "%"));
				searchPredicates.add(cb.like(cb.upper(root.get(Segnalazione_.riferimento)),"%" + globalSearch.toUpperCase().trim() + "%"));
				searchPredicates.add(cb.like(cb.upper(root.get(Segnalazione_.status)),"%" + globalSearch.toUpperCase().trim() + "%"));
			}
			if (searchPredicates.size() > 0) {
				predicates.add(cb.or(searchPredicates.toArray(new Predicate[0])));
			}
		}

		if (predicates.size() > 0) {
			cq.where(predicates.toArray(new Predicate[0]));
		}
			
		if (!isCount && request.getDetail().getOrder() != null) {
			String orderby = request.getDetail().getOrder();
			String direction = request.getDetail().getDir();
			Expression<?> orderExpression = null;
			if (orderby.equals(SegnalazioneModelList.Fields.segnalazioneId)) {
				orderExpression = root.get(Segnalazione_.segnalazioneId);
			} else if (orderby.equals(SegnalazioneModelList.Fields.nome)) {
				orderExpression = root.get(Segnalazione_.nome);
			} else if (orderby.equals(SegnalazioneModelList.Fields.dataDa)) {
				orderExpression = root.get(Segnalazione_.dataDa);
			} else if (orderby.equals(SegnalazioneModelList.Fields.dataA)) {
				orderExpression = root.get(Segnalazione_.dataA);
			} else if (orderby.equals(SegnalazioneModelList.Fields.comune)) {
				orderExpression = root.get(Segnalazione_.comune);
			} else if (orderby.equals(SegnalazioneModelList.Fields.riferimento)) {
				orderExpression = root.get(Segnalazione_.riferimento);
			} else if (orderby.equals(SegnalazioneModelList.Fields.dataIns)) {
				orderExpression = root.get(Segnalazione_.dataIns);
			} else if (orderby.equals(SegnalazioneModelList.Fields.status)) {
				orderExpression = root.get(Segnalazione_.status);
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
