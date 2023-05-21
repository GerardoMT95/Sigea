package it.indra.SigecAPI.mapper;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import it.indra.SigeaCommons.model.RichiestaAttivitaModel;
import it.indra.SigecAPI.entity.RichiestaAttivita;

@Mapper
public interface RichiestaAttivitaMapper {

	RichiestaAttivitaMapper INSTANCE = Mappers.getMapper(RichiestaAttivitaMapper.class);
	
	RichiestaAttivitaModel entityToDto(RichiestaAttivita entity);
	RichiestaAttivita dtoToEntity(RichiestaAttivitaModel dto);
	
	Set<RichiestaAttivitaModel> entityToDtoSet(Set<RichiestaAttivita> entitySet);
	Set<RichiestaAttivita> dtoToEntitySet(Set<RichiestaAttivitaModel> dtoSet);
}
