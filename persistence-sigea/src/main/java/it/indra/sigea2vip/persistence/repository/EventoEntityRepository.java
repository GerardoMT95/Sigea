package it.indra.sigea2vip.persistence.repository;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.indra.sigea2vip.persistence.entity.EventoEntity;

/**
* Generated by Spring Data Generator on 11/05/2020
*/
@Repository
public interface EventoEntityRepository extends JpaRepository<EventoEntity, Long>{
	
	List<EventoEntity>findByIdeventoGreaterThanEqualAndIdeventoLessThanEqual(Long start, Long end);

	Long countAllByIdstatoapprovazione(Long statoapprovazione);

	Set<EventoEntity> findByIdeventoBetweenAndIdstatoapprovazioneAndTipologiaIsNotNull(Long start, Long end, long statoapprovazione);

	Long countAllByIdstatoapprovazioneAndTipologiaIsNotNull(long l);

	List<EventoEntity>  findByDatainserimentoBetweenAndIdstatoapprovazioneAndTipologiaIsNotNull(Date dataDa, Date dataA, long statoApprovazione);

}
