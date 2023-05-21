package it.indra.SigecAPI.mapper;

import java.util.Map;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import it.indra.SigeaCommons.model.LinkModel;
import it.indra.SigecAPI.entity.Link;

@Mapper
public interface LinkMapper {
	
	LinkMapper INSTANCE = Mappers.getMapper(LinkMapper.class);
	
	LinkModel entityToDto(Link entity);
	@Mappings({
		@Mapping(source = "dto.didascaliaMulti", target = "didascalia", qualifiedByName = "getItaFromMultiMap"),
		@Mapping(source = "dto.titoloMulti", target = "titolo", qualifiedByName = "getItaFromMultiMap")
	})
	Link dtoToEntity(LinkModel dto);
	
	Set<LinkModel> entityToDtoSet(Set<Link> entitySet);
	Set<Link> dtoToEntitySet(Set<LinkModel> dtoSet);
	
	@Named("getItaFromMultiMap")
	default String getItaFromMultiMap( Map<String,String> map) {
		return map.get("IT");
	}

}
