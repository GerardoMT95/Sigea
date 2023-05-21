package it.indra.SigecAPI.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import it.indra.SigeaCommons.model.DecodificaModel;
import it.indra.SigecAPI.entity.Decodifica;

@Mapper
public interface DecodificaMapper {
	
	DecodificaMapper INSTANCE = Mappers.getMapper(DecodificaMapper.class);
	
	DecodificaModel entityToDto(Decodifica entity);
	Decodifica dtoToEntity(DecodificaModel dto);
	
	List<DecodificaModel> entityToDtoList(List<Decodifica> entitySet);
	List<Decodifica> dtoToEntityList(List<DecodificaModel> dtoSet);

}
