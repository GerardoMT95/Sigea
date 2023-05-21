package it.indra.SigecAPI.mapper;

import java.util.Map;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import it.indra.SigeaCommons.model.TicketModel;
import it.indra.SigecAPI.entity.Ticket;

@Mapper
public interface TicketMapper {

	TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);
	
	TicketModel entityToDto(Ticket entity);
	
	@Mapping(source = "dto.notaMulti", target = "nota", qualifiedByName = "getItaFromMultiMap")
	Ticket dtoToEntity(TicketModel dto);
	
	Set<TicketModel> entityToDtoSet(Set<Ticket> entitySet);
	Set<Ticket> dtoToEntitySet(Set<TicketModel> dtoSet);
	
	@Named("getItaFromMultiMap")
	default String getItaFromMultiMap( Map<String,String> map) {
		return map.get("IT");
	}
}
