package it.indra.sigea2vip.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.indra.sigea2vip.persistence.entity.AttivitaorarioEntity;

public interface AttivitaorarioEntityRepository extends JpaRepository<AttivitaorarioEntity, Long> {

	List<AttivitaorarioEntity> findAllByIdAttivita(Long idAttivita);

	Long deleteByIdOrarioAndIdAttivita(Long idOrario, Long idAttivita);

}
