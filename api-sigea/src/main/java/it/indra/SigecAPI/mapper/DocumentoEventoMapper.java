package it.indra.SigecAPI.mapper;

import java.util.Map;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import it.indra.SigeaCommons.model.DocumentoEventoModel;
import it.indra.SigecAPI.entity.DocumentoEvento;

@Mapper
public interface DocumentoEventoMapper {

	DocumentoEventoMapper INSTANCE = Mappers.getMapper(DocumentoEventoMapper.class);
	
	DocumentoEventoModel entityToDto(DocumentoEvento entity);
	@Mapping(source = "dto.titoloMulti", target = "titolo", qualifiedByName = "getItaFromMultiMap")
	DocumentoEvento dtoToEntity(DocumentoEventoModel dto);
	
	Set<DocumentoEventoModel> entityToDtoSet(Set<DocumentoEvento> entitySet);
	Set<DocumentoEvento> dtoToEntitySet(Set<DocumentoEventoModel> dtoSet);
	
	@Named("getItaFromMultiMap")
	default String getItaFromMultiMap(Map<String,String> map) {
		return map.get("IT");
	}
}
