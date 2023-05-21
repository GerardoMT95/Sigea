package it.indra.SigecAPI.mapper;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import it.indra.SigeaCommons.model.AccessibilitaModel;
import it.indra.SigecAPI.entity.Accessibilita;

@Mapper
public interface AccessibilitaMapper {
	AccessibilitaMapper INSTANCE = Mappers.getMapper(AccessibilitaMapper.class);
	
	AccessibilitaModel entityToDto(Accessibilita entity);
	Accessibilita dtoToEntity(AccessibilitaModel dto);
	
	Set<AccessibilitaModel> entityToDtoSet(Set<Accessibilita> entitySet);
	Set<Accessibilita> dtoToEntitySet(Set<AccessibilitaModel> dtoSet);
}
