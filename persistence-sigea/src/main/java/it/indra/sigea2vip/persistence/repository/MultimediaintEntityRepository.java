package it.indra.sigea2vip.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.indra.sigea2vip.persistence.entity.MultimediaintEntity;

/**
* Generated by Spring Data Generator on 13/05/2020
*/
@Repository
public interface MultimediaintEntityRepository extends JpaRepository<MultimediaintEntity, String>{

	public Long deleteByIdmultimedia(long idMultimedia);

}
