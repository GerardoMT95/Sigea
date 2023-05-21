package it.indra.SigecAPI.util;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface WrapperFilterRepository <T, ID extends Serializable> extends JpaRepository<T, ID>{
	
	WrapperFilterResponse<?> findByPageFilter (WrapperFilterRequest<?> request, Class<?> DTOClass, WrappedFilterInterface filterManager);
		
	
}
