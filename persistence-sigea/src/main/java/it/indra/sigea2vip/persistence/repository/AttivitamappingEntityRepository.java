package it.indra.sigea2vip.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.indra.sigea2vip.persistence.entity.AttivitamappingEntity;

public interface AttivitamappingEntityRepository extends JpaRepository<AttivitamappingEntity, Long>{

	Long deleteByIdAttivita(Long idAttivita);

}
