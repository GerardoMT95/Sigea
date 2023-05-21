package it.indra.SigecAPI.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import it.indra.SigeaCommons.model.ProgettoModel;
import it.indra.SigecAPI.entity.Progetto;

@Mapper
public interface ProgettoMapper {

	ProgettoMapper INSTANCE = Mappers.getMapper(ProgettoMapper.class);
	
	ProgettoModel entityToDto(Progetto entity);
	Progetto dtoToEntity(ProgettoModel dto);
	
	List<ProgettoModel> entityToDtoList(List<Progetto> entitySet);
	List<Progetto> dtoToEntityList(List<ProgettoModel> dtoSet);
}
