package it.indra.sigea2vip.persistence.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.indra.sigea2vip.persistence.entity.GiornochiusuraEntity;

/**
* Generated by Spring Data Generator on 21/07/2020
*/
@Repository
public interface GiornochiusuraEntityRepository extends JpaRepository<GiornochiusuraEntity, Long>{

	public long deleteByIdorario(long idOrario);

	public Set<GiornochiusuraEntity> findAllByIdorario(long idorario);

}