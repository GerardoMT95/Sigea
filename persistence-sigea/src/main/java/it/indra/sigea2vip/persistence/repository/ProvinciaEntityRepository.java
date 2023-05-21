package it.indra.sigea2vip.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.indra.sigea2vip.persistence.entity.ProvinciaEntity;

public interface ProvinciaEntityRepository extends JpaRepository<ProvinciaEntity, Long> {

	public ProvinciaEntity findByCodiceProvincia(String codice);

	public ProvinciaEntity findBySigla(String idprovincia);
	
}
