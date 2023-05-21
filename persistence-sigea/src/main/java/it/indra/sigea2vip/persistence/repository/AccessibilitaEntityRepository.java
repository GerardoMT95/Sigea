package it.indra.sigea2vip.persistence.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.indra.sigea2vip.persistence.entity.AccessibilitaEntity;


/**
* Generated by Spring Data Generator on 11/05/2020
*/
@Repository
public interface AccessibilitaEntityRepository extends JpaRepository<AccessibilitaEntity, Long>{

	AccessibilitaEntity findByIdmapping(long id);

}