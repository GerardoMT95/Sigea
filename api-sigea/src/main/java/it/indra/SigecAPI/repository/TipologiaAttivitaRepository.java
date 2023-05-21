package it.indra.SigecAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.indra.SigecAPI.entity.TipologiaAttivita;

@Repository
@Transactional
public interface TipologiaAttivitaRepository extends JpaRepository<TipologiaAttivita, String> {}