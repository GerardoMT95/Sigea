package it.indra.SigecAPI.migration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.indra.SigecAPI.migration.entity.Sigea2VipMigra;

/**
* Generated by Spring Data Generator on 11/05/2020
*/
@Repository
public interface Sigea2VipMigraRepository extends JpaRepository<Sigea2VipMigra, Long>{

	Sigea2VipMigra findByIdEventoVip(Long idEvento);

	int deleteByIdSigea(Long eventoid);

	

}
