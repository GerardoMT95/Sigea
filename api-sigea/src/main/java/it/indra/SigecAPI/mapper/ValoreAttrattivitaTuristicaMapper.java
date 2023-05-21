package it.indra.SigecAPI.mapper;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import it.indra.SigeaCommons.model.ValoreAttrattivitaTuristicaModel;
import it.indra.SigecAPI.entity.ValoreAttrattivitaTuristica;


@Mapper
public interface ValoreAttrattivitaTuristicaMapper {
	
	ValoreAttrattivitaTuristicaMapper INSTANCE = Mappers.getMapper(ValoreAttrattivitaTuristicaMapper.class);
	
	ValoreAttrattivitaTuristicaModel entityToDto(ValoreAttrattivitaTuristica entity);
	ValoreAttrattivitaTuristica dtoToEntity(ValoreAttrattivitaTuristicaModel dto);
	
	Set<ValoreAttrattivitaTuristicaModel> entityToDtoSet(Set<ValoreAttrattivitaTuristica> entitySet);
	Set<ValoreAttrattivitaTuristica> dtoToEntitySet(Set<ValoreAttrattivitaTuristicaModel> dtoSet);

}