package it.indra.SigecAPI.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.indra.SigeaCommons.enumeration.StatoPubblicazione;
import it.indra.SigecAPI.entity.Evento;
import it.indra.SigecAPI.entity.Pubblicazione;
import it.indra.SigecAPI.projection.PubblicazioneProjection;

@Repository
@Transactional
public interface PubblicazioneRepository extends JpaRepository<Pubblicazione, Long>, JpaSpecificationExecutor<Pubblicazione>{
	
	Optional<Pubblicazione> findFirstByRedazione_RedazioneIdAndEvento_EventoIdOrderByDataPubblicazioneDesc(String redazioneId, Long eventoId);
	Set<Pubblicazione> findByGenericMetadataNotNullAndEvento_EventoIdAndRedazione_RedazioneIdNot(Long eventoId, String redazioneId);
	public Boolean existsByGenericMetadataNotNullAndEvento_EventoIdAndRedazione_RedazioneIdNot(Long eventoId, String redazioneId);
	Set<Pubblicazione> findByGenericMetadataNotNullAndEvento_EventoIdAndRedazione_RedazioneIdNotAndRedazione_AutoSpubblicazioneTrue(Long eventoId, String redazioneId);
	Set<Pubblicazione> findByEvento_EventoIdIn(List<Long> eventoIdList);
	
	
	
	Optional<Pubblicazione> findByGenericMetadataNotNullAndEvento_EventoIdAndRedazione_RedazioneId(Long eventoId, String redazioneId);
	
	Set<PubblicazioneProjection> findByRedazione_RedazioneIdAndEvento_DatiGenerali_TitoloIgnoreCaseLikeAndEvento_TipoInAndEvento_Stato_StatoId(String redazioneId, String titolo, List<String> tipi, String stato);
	
	Set<PubblicazioneProjection> findByRedazione_RedazioneIdAndEvento_DatiGenerali_TitoloIgnoreCaseLikeAndEvento_TipoInAndEvento_Stato_StatoIdIn(String redazioneId, String titolo, List<String> tipi, List<String> stato);
	
	Set<Pubblicazione> findByEvento_EventoIdInAndRedazione_RedazioneId(List<Long> eventoIdList, String redazioneId);
	
	Set<Pubblicazione> findByEvento_EventoIdAndRedazione_RedazioneIdNot(Long eventoId, String redazioneId);

	void deleteByEvento(Evento eventoSigea);
	Set<Pubblicazione> findAllByEvento(Evento eventoSigea);
	Pubblicazione findAllByGenericMetadataNotNullAndEvento_EventoIdAndRedazione_RedazioneId(Long idEventoVip,
			String redazioneVip);


	Set<Pubblicazione> findAllByGenericMetadataNotNullAndEvento_EventoIdBetweenAndRedazione_RedazioneIdAndStatoPubblicazione(
			Long risorsaDa, Long risorsaA, String string, StatoPubblicazione pubblicato);
}