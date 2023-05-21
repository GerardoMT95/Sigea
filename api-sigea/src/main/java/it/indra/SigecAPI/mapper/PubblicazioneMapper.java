package it.indra.SigecAPI.mapper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;

import it.indra.SigeaCommons.model.LogPubblicazioniModel;
import it.indra.SigeaCommons.model.PubblicazioneModel;
import it.indra.SigecAPI.entity.LogPubblicazioni;
import it.indra.SigecAPI.entity.Pubblicazione;
import it.indra.SigecAPI.repository.DettaglioUtenteRepository;

@Mapper(uses = LogPubblicazioniMapper.class )
public interface PubblicazioneMapper {

	PubblicazioneMapper INSTANCE = Mappers.getMapper(PubblicazioneMapper.class);
	@Mappings({
		@Mapping(source = "entity.redattore.utenteId", target = "ownerId"),
		@Mapping(source = "entity.redattore.nome", target = "nomeOwner"),
		@Mapping(source = "entity.redattore.cognome", target = "cognomeOwner"),
		@Mapping(source = "entity.redazione.redazioneId", target = "redazioneId"),
		@Mapping(source = "entity.redazione.nome", target = "nomeRedazione")
	})
	PubblicazioneModel entityToDto(Pubblicazione entity);
	
	Set<PubblicazioneModel> entityToDtoSet(Set<Pubblicazione> entitySet);
	
	
	
	
	@AfterMapping
	default void afterMappingFix(@MappingTarget PubblicazioneModel model, Pubblicazione pubblicazione) {
		model.setGenericMetadata(pubblicazione.getGenericMetadataWip());
//		if(pubblicazione.getGenericMetadata() != null) {
//			model.setFgPubblicato(true);
//		} else {
//			model.setFgPubblicato(false);
//		}
		
		model.setFgPubblicato(pubblicazione.getEvento().getFgPubblicato());
		
		JsonNode node = pubblicazione.getGenericMetadataWip();
		
		if(node!=null && node.has("tipoScheda")) {
		String tipologiaScheda = node.get("tipoScheda").asText();
		model.setTipologiaScheda(tipologiaScheda);
		
		}
		List<LogPubblicazioni> listLogEntity = new ArrayList<LogPubblicazioni>(pubblicazione.getLogPubblicazioni());
		listLogEntity.sort(Comparator.comparing(LogPubblicazioni::getDataModifica));
		
		boolean primaPubblicazioneFind= false;
		
		Timestamp primaPubblicazione= null;
		for(LogPubblicazioni logs : listLogEntity)
		{
			
			if(logs.getStato().getStatoId().equalsIgnoreCase("PUBBLICATO") ) 
			{
				if(!primaPubblicazioneFind) {
					primaPubblicazione = logs.getDataModifica();
					primaPubblicazioneFind = true;
				}
				
				model.setDataPubblicazione(logs.getDataModifica());
//				if(logs.getIdUtenteModifica()!= null)
					model.setOwnerId(logs.getIdUtenteModifica());
			}
		
			
			if(logs.getStato().getStatoId().equalsIgnoreCase("RIPUBBLICATO") ) 
			{
				model.setDataPubblicazione(logs.getDataModifica());
//				if(logs.getIdUtenteModifica()!= null)
					model.setOwnerId(logs.getIdUtenteModifica());
			}
			LogPubblicazioniModel logModel = new LogPubblicazioniModel();
			logModel.setStatoPubblicazione(logs.getStato().getStatoId());
			logModel.setDataModifica(logs.getDataModifica());
			model.getLogPubblicazioniSet().add(logModel);
			
			
		}
		
		
		
		
		if(!primaPubblicazioneFind)
		{
			model.setDataPubblicazione(null);
			model.setCognomeOwner(null);
			model.setNomeOwner(null);
			model.setOwnerId(null);
		}
		model.setDataPrimaPubblicazione(primaPubblicazione);
		
	}
	
}
