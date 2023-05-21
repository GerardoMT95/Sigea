package it.indra.sigea2vip.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.indra.sigea2vip.persistence.entity.BackeventiutentiEntity;
import it.indra.sigea2vip.persistence.entity.UtenteEntity;

/**
* Generated by Spring Data Generator on 11/05/2020
*/
@Repository
public interface UtenteEntityRepository extends JpaRepository<UtenteEntity, Long>{

	UtenteEntity findByEmail(String email);


}