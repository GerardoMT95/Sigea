package it.indra.SigecAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.indra.SigecAPI.entity.Relazione;

@Repository
@Transactional
public interface RelazioneRepository extends JpaRepository<Relazione, Long>{
	
	public Long deleteByEventoRelazionatoId(Long id);
	public Long deleteByEventoRelazionatoIdAndRedazioneId(Long id, String redazioneId);
	
	@Modifying
	public void deleteById(Long relazioneId);
}