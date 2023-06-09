package it.indra.sigea2vip.persistence.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.indra.sigea2vip.persistence.entity.TicketscontoEntity;

/**
* Generated by Spring Data Generator on 11/05/2020
*/
@Repository
public interface TicketscontoEntityRepository extends JpaRepository<TicketscontoEntity, Long>{

	Long deleteAllByIdticket(long idticket);

	Set<TicketscontoEntity> findAllByIdticket(long idticket);

}
