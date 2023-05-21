package it.indra.SigecAPI.mapper;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import it.indra.SigeaCommons.model.RelazioneModel;
import it.indra.SigecAPI.entity.Relazione;

@Mapper(uses = {LocationMapper.class, PubblicazioneMapper.class})
public interface RelazioneMapper {

	RelazioneMapper INSTANCE = Mappers.getMapper(RelazioneMapper.class);
	
	@Mappings({
		@Mapping(source = "entity.eventoRelazionato.datiGenerali.titolo", target = "titolo"),
		@Mapping(source = "entity.eventoRelazionato.locationSet", target = "locationSet"),
		@Mapping(source = "entity.eventoRelazionato.periodoSet", target = "periodoSet"),
		@Mapping(source = "entity.eventoRelazionato.stato.statoId", target = "statoEventoAssociato"),
		@Mapping(source = "entity.eventoRelazionato.pubblicazioneSet", target = "pubblicazioneSet"),
		@Mapping(ignore = true, target = "comune"),
		@Mapping(ignore = true, target = "periodo")
	})
	RelazioneModel entityToDto(Relazione entity);
	Relazione dtoToEntity(RelazioneModel dto);

	Set<RelazioneModel> entityToDtoSet(Set<Relazione> entitySet);
	Set<Relazione> dtoToEntitySet(Set<RelazioneModel> dtoSet);
}
