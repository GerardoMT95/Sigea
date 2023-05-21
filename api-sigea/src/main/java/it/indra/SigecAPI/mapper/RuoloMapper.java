package it.indra.SigecAPI.mapper;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import it.indra.SigeaCommons.model.RuoloModel;
import it.indra.SigecAPI.entity.Ruolo;

@Mapper
public interface RuoloMapper {
	
	RuoloMapper INSTANCE = Mappers.getMapper(RuoloMapper.class);
	
	RuoloModel entityToDto(Ruolo entity);
	Ruolo dtoToEntity(RuoloModel dto);
	
	Set<RuoloModel> entityToDtoSet(Set<Ruolo> entitySet);
	Set<Ruolo> dtoToEntitySet(Set<RuoloModel> dtoSet);
}
