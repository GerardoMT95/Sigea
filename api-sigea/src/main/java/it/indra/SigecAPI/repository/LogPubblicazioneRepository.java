package it.indra.SigecAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.indra.SigecAPI.entity.LogPubblicazioni;
import it.indra.SigecAPI.entity.Pubblicazione;

public interface LogPubblicazioneRepository extends JpaRepository<LogPubblicazioni, Long>{

	void deleteByPubblicazione(Pubblicazione pubblicazione);

	List<LogPubblicazioni> findByPubblicazione(Pubblicazione pubblicazioneVip);


}
