package it.indra.SigecAPI.mapper;

import java.util.Map;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import it.indra.SigeaCommons.model.DatiGeneraliModel;
import it.indra.SigecAPI.entity.DatiGenerali;

@Mapper
public interface DatiGeneraliMapper {

	DatiGeneraliMapper INSTANCE = Mappers.getMapper(DatiGeneraliMapper.class);
	
	DatiGeneraliModel entityToDto(DatiGenerali entity);
	
	@Mappings({
		@Mapping(source = "dto.titoloMulti", target = "titolo", qualifiedByName = "getItaFromMultiMap"),
		@Mapping(source = "dto.abstractDescrMulti", target = "abstractDescr", qualifiedByName = "getItaFromMultiMap"),
		@Mapping(source = "dto.snippetMulti", target = "snippet", qualifiedByName = "getItaFromMultiMap"),
		@Mapping(source = "dto.descrizioneMulti", target = "descrizione", qualifiedByName = "getItaFromMultiMap")
	})
	DatiGenerali dtoToEntity(DatiGeneraliModel dto);
	
	Set<DatiGeneraliModel> entityToDtoSet(Set<DatiGenerali> entitySet);
	Set<DatiGenerali> dtoToEntitySet(Set<DatiGeneraliModel> dtoSet);
	
	@Named("getItaFromMultiMap")
	default String getItaFromMultiMap( Map<String,String> map) {
		return map.get("IT");
	}
}
