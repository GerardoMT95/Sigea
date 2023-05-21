package it.indra.SigecAPI.repository;

import java.sql.Date;
import java.util.Set;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.indra.SigecAPI.entity.Segnalazione;
import it.indra.SigecAPI.util.WrapperFilterRepository;

@Repository
@Transactional
public interface SegnalazioneRepository extends WrapperFilterRepository<Segnalazione, Long>{
	
	Set<Segnalazione> findAllByDataDaAndCodIstat(Date dataDa, String codIstat);
}
