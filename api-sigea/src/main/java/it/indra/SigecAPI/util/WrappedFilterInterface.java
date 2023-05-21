package it.indra.SigecAPI.util;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public interface WrappedFilterInterface {
	
	<T> TypedQuery<T>  createDynamicDatatableQuery (EntityManager em, WrapperFilterRequest<?> request, boolean isCount, boolean addSearch, Class<T> classe);
}
