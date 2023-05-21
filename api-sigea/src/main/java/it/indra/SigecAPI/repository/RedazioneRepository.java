package it.indra.SigecAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.indra.SigecAPI.entity.Redazione;

@Repository
@Transactional
public interface RedazioneRepository extends JpaRepository<Redazione, String>{
}