package it.indra.sigea2vip.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.indra.sigea2vip.persistence.entity.GruppoEntity;

/**
* Generated by Spring Data Generator on 20/07/2020
*/
@Repository
public interface GruppoEntityRepository extends JpaRepository<GruppoEntity, Long>{

	public GruppoEntity findByIopAccessKeyId(String idAttivita);
	public GruppoEntity findByIopSignatureKey(String idOwner);

}
