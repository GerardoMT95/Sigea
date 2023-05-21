package it.indra.SigecAPI.mapper;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import it.indra.SigeaCommons.model.PeriodoModel;
import it.indra.SigecAPI.entity.Periodo;

@Mapper
public interface PeriodoMapper {

	PeriodoMapper INSTANCE = Mappers.getMapper(PeriodoMapper.class);
	
	PeriodoModel entityToDto(Periodo entity);
	Periodo dtoToEntity(PeriodoModel dto);
	
	Set<PeriodoModel> entityToDtoSet(Set<Periodo> entitySet);
	Set<Periodo> dtoToEntitySet(Set<PeriodoModel> dtoSet);
}
