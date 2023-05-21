package it.indra.SigecAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.indra.SigecAPI.entity.DettaglioUtente;

@Repository
@Transactional
public interface DettaglioUtenteRepository extends JpaRepository<DettaglioUtente, Long>{

}
