package it.indra.SigecAPI.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import it.indra.SigeaCommons.model.BandoModel;
import it.indra.SigecAPI.entity.Bando;


@Mapper(uses = { ProgettoMapper.class})
public interface BandoMapper {

	BandoMapper INSTANCE = Mappers.getMapper(BandoMapper.class);
	
	BandoModel entityToDto(Bando entity);
	Bando dtoToEntity(BandoModel dto);
	
	List<BandoModel> entityToDtoList(List<Bando> entitySet);
	List<Bando> dtoToEntityList(List<BandoModel> dtoSet);
}
