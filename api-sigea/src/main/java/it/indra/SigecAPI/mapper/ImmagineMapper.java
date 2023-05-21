package it.indra.SigecAPI.mapper;

import java.util.Map;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import it.indra.SigeaCommons.model.ImmagineModel;
import it.indra.SigecAPI.entity.Immagine;

@Mapper
public interface ImmagineMapper {

	ImmagineMapper INSTANCE = Mappers.getMapper(ImmagineMapper.class);
	
	ImmagineModel entityToDto(Immagine entity);
	
	@Mapping(source = "dto.didascaliaMulti", target = "didascalia", qualifiedByName = "getItaFromMultiMap")
	Immagine dtoToEntity(ImmagineModel dto);
	
	Set<ImmagineModel> entityToDtoSet(Set<Immagine> entitySet);
	Set<Immagine> dtoToEntitySet(Set<ImmagineModel> dtoSet);
	
	@Named("getItaFromMultiMap")
	default String getItaFromMultiMap( Map<String,String> map) {
		return map.get("IT");
	}
}
