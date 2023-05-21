package it.indra.SigecAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.indra.SigecAPI.entity.Immagine;

@Repository
@Transactional
public interface ImmagineRepository extends JpaRepository<Immagine, Long>{}