package it.indra.sigea2vip.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.indra.sigea2vip.persistence.entity.ComuneRiferimentoEntity;

public interface ComuneRiferimentoRepository extends JpaRepository<ComuneRiferimentoEntity, Long> {

	public ComuneRiferimentoEntity findIdComuneRiferimentoByCodiceIstat(String codComune);

}
