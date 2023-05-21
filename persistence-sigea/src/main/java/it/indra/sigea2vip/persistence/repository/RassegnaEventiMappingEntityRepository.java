package it.indra.sigea2vip.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.indra.sigea2vip.persistence.entity.RassegnaEventiMappingEntity;

public interface RassegnaEventiMappingEntityRepository extends JpaRepository<RassegnaEventiMappingEntity, Long>  {

	RassegnaEventiMappingEntity findByIdEvento(Long idEventoVip);

	List<RassegnaEventiMappingEntity> findAllByIdRassegna(Long idEventoVip);
	
	List<RassegnaEventiMappingEntity> findAllByIdEvento(Long idEventoVip);

	List<RassegnaEventiMappingEntity> findFirstByIdEvento(Long idVip);

	List<RassegnaEventiMappingEntity> findFirstByIdRassegna(Long idVip);


}
