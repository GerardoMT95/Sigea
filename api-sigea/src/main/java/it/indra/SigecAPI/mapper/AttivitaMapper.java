package it.indra.SigecAPI.mapper;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import it.indra.SigeaCommons.model.AttivitaModel;
import it.indra.SigecAPI.entity.Attivita;

@Mapper
public interface AttivitaMapper {

	AttivitaMapper INSTANCE = Mappers.getMapper(AttivitaMapper.class);
	
	AttivitaModel entityToDto(Attivita entity);
	Attivita dtoToEntity(AttivitaModel dto);
	
	Set<AttivitaModel> entityToDtoSet(Set<Attivita> entitySet);
	Set<Attivita> dtoToEntitySet(Set<AttivitaModel> dtoSet);
}
