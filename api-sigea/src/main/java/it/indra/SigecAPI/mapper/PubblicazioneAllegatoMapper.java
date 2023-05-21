package it.indra.SigecAPI.mapper;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import it.indra.SigeaCommons.model.PubblicazioneAllegatoModel;
import it.indra.SigecAPI.entity.PubblicazioneAllegato;

@Mapper
public interface PubblicazioneAllegatoMapper {

	PubblicazioneAllegatoMapper INSTANCE = Mappers.getMapper(PubblicazioneAllegatoMapper.class);
	
	PubblicazioneAllegatoModel entityToDto(PubblicazioneAllegato entity);
	PubblicazioneAllegato dtoToEntity(PubblicazioneAllegatoModel dto);

	Set<PubblicazioneAllegatoModel> entityToDtoSet(Set<PubblicazioneAllegato> entitySet);
	Set<PubblicazioneAllegato> dtoToEntitySet(Set<PubblicazioneAllegatoModel> dtoSet);
}
