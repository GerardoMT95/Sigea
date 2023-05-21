package it.indra.SigecAPI.repository;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.indra.SigeaCommons.model.BandoModelList;
import it.indra.SigecAPI.entity.Evento;
import it.indra.SigecAPI.projection.EventoTitoloProjection;
import it.indra.SigecAPI.util.WrapperFilterRepository;

@Repository
@Transactional
public interface EventoRepository extends WrapperFilterRepository<Evento, Long>, EventoRepositoryCustom {
	
	public EventoTitoloProjection findByEventoId(Long id);
	
	public Set<EventoTitoloProjection> findByDatiGenerali_TitoloIgnoreCaseLikeAndStatoIsNotNull(String titolo);
	
	public Set<EventoTitoloProjection> findByTipoAndDatiGenerali_TitoloIgnoreCaseLikeAndStatoIsNotNull(String tipoEvento, String titolo);
	
	public Set<EventoTitoloProjection> findByTipoAndOwner_UtenteIdAndDatiGenerali_TitoloIgnoreCaseLikeAndStatoIsNotNull(String tipoEvento, Long utenteId, String titolo);
	
	public Iterable<Evento> findByRichiestaAttivita_RichiestaAttivitaId(Long id);
	
	public List<Evento> findByFgValidazioneUltimoGiorno(Boolean fgValidazioneUltimoGiorno);
	
	public List<Evento> findAllByEmailRiconciliazioneIgnoreCase(String email);
	
	// Per select associazioni
	public Set<EventoTitoloProjection> findByDatiGenerali_TitoloIgnoreCaseLikeAndTipoInAndAttivita_AttivitaIdAndStatoIsNotNull(String titolo, List<String> tipo, Long attivitaId);
	
	public Set<EventoTitoloProjection> findByDatiGenerali_TitoloIgnoreCaseLikeAndTipoInAndRichiestaAttivita_RichiestaAttivitaIdAndStatoIsNotNull(String titolo, List<String> tipo, Long richiestaAttivitaId);

	public Evento findAllByEventoId(Long idSigea);

	public Set<Evento> findAllByEventoIdBetween(Long start, Long end);

	public int deleteByEventoId(Long eventoSigea);

	public Set<BandoModelList> findBandiProgetti(Long idAttivita);
	
	public List<Evento> findByBando_BandoId(String idBando);
	
	public List<Evento> findByBando_BandoIdAndStato_statoIdNot(String idBando, String stato);
	
	public List<Evento> findByStato_statoId(String stato);
	
	
	
}