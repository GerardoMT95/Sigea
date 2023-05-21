package it.indra.SigecAPI.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.indra.SigecAPI.entity.Bando;

@Repository
@Transactional
public interface BandoRepository extends JpaRepository<Bando, Long>{
	

}
