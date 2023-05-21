package it.indra.SigecAPI.mapper;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import it.indra.SigeaCommons.model.LocationModel;
import it.indra.SigecAPI.entity.Location;

@Mapper(uses = { AttrattoreMapper.class})
public interface LocationMapper {

	LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);
	
	LocationModel entityToDto(Location entity);
	Location dtoToEntity(LocationModel dto);
	
	Set<LocationModel> entityToDtoSet(Set<Location> entitySet);
	Set<Location> dtoToEntitySet(Set<LocationModel> dtoSet);
}
