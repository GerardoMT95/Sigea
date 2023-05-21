package it.indra.SigecAPI.mapper;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import it.indra.SigeaCommons.model.LogPubblicazioniModel;
import it.indra.SigecAPI.entity.LogPubblicazioni;

@Mapper
public interface LogPubblicazioniMapper {


	LogPubblicazioniMapper INSTANCE = Mappers.getMapper(LogPubblicazioniMapper.class);
	
	@Mappings({
		@Mapping(source = "entity.stato.statoId", target = "statoPubblicazione")})
	

	LogPubblicazioniModel entityToDto(LogPubblicazioni entity);
	LogPubblicazioni dtoToEntity(LogPubblicazioniModel dto);
	
	Set<LogPubblicazioniModel> entityToDtoSet(Set<LogPubblicazioni> entitySet);
	Set<LogPubblicazioni> dtoToEntitySet(Set<LogPubblicazioniModel> dtoSet);
}