package it.indra.SigecAPI.mapper;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import it.indra.SigeaCommons.model.AttrattoreModel;
import it.indra.SigecAPI.entity.Attrattore;

@Mapper
public interface AttrattoreMapper {
	AttrattoreMapper INSTANCE = Mappers.getMapper(AttrattoreMapper.class);
	
	AttrattoreModel entityToDto(Attrattore entity);
	Attrattore dtoToEntity(AttrattoreModel dto);
	
	Set<AttrattoreModel> entityToDtoSet(Set<Attrattore> entitySet);
	Set<Attrattore> dtoToEntitySet(Set<AttrattoreModel> dtoSet);
}
