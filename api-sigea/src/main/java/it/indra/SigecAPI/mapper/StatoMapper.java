package it.indra.SigecAPI.mapper;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import it.indra.SigeaCommons.model.StatoModel;
import it.indra.SigecAPI.entity.Stato;

@Mapper
public interface StatoMapper {

	StatoMapper INSTANCE = Mappers.getMapper(StatoMapper.class);
	
	StatoModel entityToDto(Stato entity);
	Stato dtoToEntity(StatoModel dto);
	
	Set<StatoModel> entityToDtoSet(Set<Stato> entitySet);
	Set<Stato> dtoToEntitySet(Set<StatoModel> dtoSet);
}
