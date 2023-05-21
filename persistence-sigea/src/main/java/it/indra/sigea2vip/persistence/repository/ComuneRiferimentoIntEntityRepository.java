package it.indra.sigea2vip.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.indra.sigea2vip.persistence.entity.ComuneRiferimentoEntity;
import it.indra.sigea2vip.persistence.entity.ComuneRiferimentoIntEntity;

public interface ComuneRiferimentoIntEntityRepository extends JpaRepository<ComuneRiferimentoIntEntity, Long> {

	
	ComuneRiferimentoIntEntity findByIdComuneRiferimentoAndIdLingua(Long idcomune, String string);


}
