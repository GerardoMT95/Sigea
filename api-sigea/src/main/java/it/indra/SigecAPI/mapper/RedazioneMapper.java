package it.indra.SigecAPI.mapper;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import it.indra.SigeaCommons.model.RedazioneModel;
import it.indra.SigecAPI.entity.Redazione;

@Mapper
public interface RedazioneMapper {
	
	RedazioneMapper INSTANCE = Mappers.getMapper(RedazioneMapper.class);
	
	RedazioneModel entityToDto(Redazione entity);
	Redazione dtoToEntity(RedazioneModel dto);
	
	Set<RedazioneModel> entityToDtoSet(Set<Redazione> entitySet);
	Set<Redazione> dtoToEntitySet(Set<RedazioneModel> dtoSet);

}
