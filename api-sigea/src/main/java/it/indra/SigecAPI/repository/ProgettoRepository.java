package it.indra.SigecAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.indra.SigecAPI.entity.Progetto;

@Repository
@Transactional
public interface ProgettoRepository extends JpaRepository<Progetto, Long>{
	
	//public List<Progetto> findByPartitaIva(String partitaIva);
}
