package it.indra.sigea2vip.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.indra.sigea2vip.persistence.entity.AttivitamezzoEntity;

/**
* Generated by Spring Data Generator on 23/07/2020
*/
@Repository
public interface AttivitamezzoEntityRepository extends JpaRepository<AttivitamezzoEntity, Long>{

	@Modifying
	@Query("delete from AttivitamezzoEntity b where b.idattivita=?1")
	void deleteByIdattivita(@Param("idattivita") long idattivita);


}