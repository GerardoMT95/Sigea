package it.indra.SigecAPI.repository;

import java.sql.Timestamp;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.indra.SigecAPI.entity.LogEvento;

@Repository
@Transactional
public interface LogEventoRepository extends JpaRepository<LogEvento, Long>{

	Set<LogEvento> findByEvento_EventoId(Long eventoId);
	
	Set<LogEvento> findByDataModificaGreaterThanEqualAndDataModificaLessThanEqual(Timestamp dataDa , Timestamp dataA);
	
}