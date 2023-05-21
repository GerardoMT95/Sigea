package it.indra.SigecAPI.repository;

import java.util.List;
import java.util.Set;

import it.indra.SigeaCommons.model.BandoModel;
import it.indra.SigeaCommons.model.BandoModelList;
import it.indra.SigeaCommons.model.SmartModelList;
import it.indra.SigecAPI.entity.Attivita;
import it.indra.SigecAPI.entity.DettaglioUtente;

public interface EventoRepositoryCustom {
	
	List<SmartModelList> findEventoGroupByComuneMoreThan9();
	
	List<SmartModelList> findEventoGroupByComuneMoreThan9(Boolean isRedazione);
	
	public void updateOwner(Set<Long> listaEventoId, DettaglioUtente owner, Attivita attivita);
	public void updatePubblicazioneFlag(boolean flag, Long eventoId);
	
	Set<BandoModelList> findBandiProgetti(Long idAttivita);
}
