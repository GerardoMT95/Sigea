package it.indra.SigecAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.indra.SigecAPI.entity.Stato;

@Repository
@Transactional
public interface StatoRepository extends JpaRepository<Stato, String>{
	
	public Stato findByStatoInizioTrue();
	
	public Stato findByStatoFineTrue();
}