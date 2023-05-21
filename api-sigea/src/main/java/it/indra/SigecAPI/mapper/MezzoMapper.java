package it.indra.SigecAPI.mapper;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import it.indra.SigeaCommons.model.MezzoModel;
import it.indra.SigecAPI.entity.Mezzo;

@Mapper
public interface MezzoMapper {
	
	MezzoMapper INSTANCE = Mappers.getMapper(MezzoMapper.class);
	
	MezzoModel entityToDto(Mezzo entity);
	Mezzo dtoToEntity(MezzoModel dto);
	
	Set<MezzoModel> entityToDtoSet(Set<Mezzo> entitySet);
	Set<Mezzo> dtoToEntitySet(Set<MezzoModel> dtoSet);

}