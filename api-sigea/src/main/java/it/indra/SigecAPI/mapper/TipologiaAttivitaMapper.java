package it.indra.SigecAPI.mapper;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import it.indra.SigeaCommons.model.TipologiaAttivitaModel;
import it.indra.SigecAPI.entity.TipologiaAttivita;

@Mapper
public interface TipologiaAttivitaMapper {
	
	TipologiaAttivitaMapper INSTANCE = Mappers.getMapper(TipologiaAttivitaMapper.class);
	
	TipologiaAttivitaModel entityToDto(TipologiaAttivita entity);
	TipologiaAttivita dtoToEntity(TipologiaAttivitaModel dto);
	
	Set<TipologiaAttivitaModel> entityToDtoSet(Set<TipologiaAttivita> entitySet);
	Set<TipologiaAttivita> dtoToEntitySet(Set<TipologiaAttivitaModel> dtoSet);

}