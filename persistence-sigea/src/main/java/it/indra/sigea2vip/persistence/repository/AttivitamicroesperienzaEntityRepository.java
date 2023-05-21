package it.indra.sigea2vip.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.indra.sigea2vip.persistence.entity.AttivitamicroesperienzaEntity;

public interface AttivitamicroesperienzaEntityRepository  extends JpaRepository<AttivitamicroesperienzaEntity, Long>{

	Long deleteByIdAttivita(Long idAttivita);

}
