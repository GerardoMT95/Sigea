package it.indra.SigecAPI.mapper;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import it.indra.SigeaCommons.model.TipologiaModel;
import it.indra.SigecAPI.entity.Tipologia;

@Mapper(uses = {RedazioneMapper.class, RuoloMapper.class})
public interface TipologiaMapper {

	TipologiaMapper INSTANCE = Mappers.getMapper(TipologiaMapper.class);
	
	TipologiaModel entityToDto(Tipologia entity);
	Tipologia dtoToEntity(TipologiaModel dto);
	
	Set<TipologiaModel> entityToDtoSet(Set<Tipologia> entitySet);
	Set<Tipologia> dtoToEntitySet(Set<TipologiaModel> dtoSet);
}
