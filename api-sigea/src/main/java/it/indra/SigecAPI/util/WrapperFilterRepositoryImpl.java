package it.indra.SigecAPI.util;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class WrapperFilterRepositoryImpl<T, ID extends Serializable>
extends SimpleJpaRepository<T, ID> implements WrapperFilterRepository<T, ID> {
   
  private EntityManager entityManager;

  public WrapperFilterRepositoryImpl(JpaEntityInformation<T, ?> 
    entityInformation, EntityManager entityManager) {
      super(entityInformation, entityManager);
      this.entityManager = entityManager;
  }
  
  @SuppressWarnings({ "rawtypes", "unchecked" })

public WrapperFilterResponse<?> findByPageFilter (WrapperFilterRequest<?> request, Class<?> DTOClass, WrappedFilterInterface filterManager) {
		
	WrapperFilterResponse response = new WrapperFilterResponse();
	response.setDraw(request.getDetail().getDraw());
	log.debug("countRecordsTotal START");	
	TypedQuery<Long> countRecordsTotal = filterManager.createDynamicDatatableQuery(entityManager,request,true,false,Long.class);
	log.debug("countRecordsTotal END");
	response.setRecordsTotal(new Integer(countRecordsTotal.getSingleResult().intValue()));
	log.debug("countRecordsFiltered START");	
	TypedQuery<Long> countRecordsFiltered = filterManager.createDynamicDatatableQuery(entityManager,request,true,true,Long.class);
	log.debug("countRecordsFiltered END");	
	response.setRecordsFiltered(new Integer(countRecordsFiltered.getSingleResult().intValue()));
	
	if (response.getRecordsFiltered().intValue() == 0) {
		response.setData(new ArrayList<>());
	}else {
		log.debug("dataQuery START");	
		TypedQuery<?> dataQuery = filterManager.createDynamicDatatableQuery(entityManager,request,false,true,DTOClass);
		log.debug("dataQuery END");	
		response.setData(dataQuery.getResultList());
	}
	log.info("RETURN RESPONSE DATATABLE");
	return response;
  }
}