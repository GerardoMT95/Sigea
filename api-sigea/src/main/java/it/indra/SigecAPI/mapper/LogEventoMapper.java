package it.indra.SigecAPI.mapper;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import it.indra.SigeaCommons.model.LogEventoModel;
import it.indra.SigecAPI.entity.LogEvento;

@Mapper
public interface LogEventoMapper {

	LogEventoMapper INSTANCE = Mappers.getMapper(LogEventoMapper.class);
	
	LogEventoModel entityToDto(LogEvento entity);
	LogEvento dtoToEntity(LogEventoModel dto);
	
	Set<LogEventoModel> entityToDtoSet(Set<LogEvento> entitySet);
	Set<LogEvento> dtoToEntitySet(Set<LogEventoModel> dtoSet);
}
