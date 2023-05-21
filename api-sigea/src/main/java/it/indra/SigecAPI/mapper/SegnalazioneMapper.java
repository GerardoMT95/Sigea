package it.indra.SigecAPI.mapper;

import java.util.Set;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import it.indra.SigeaCommons.model.SegnalazioneModel;
import it.indra.SigecAPI.entity.Segnalazione;

@Mapper(uses = { DettaglioUtenteMapper.class})
public interface SegnalazioneMapper {
	SegnalazioneMapper INSTANCE = Mappers.getMapper(SegnalazioneMapper.class);
	
	@Mapping(source = "entity.usernameIns.utenteId", target = "utenteId")
	@Mapping(source="entity.usernameIns.email" , target="emailUtente")
	SegnalazioneModel entityToDto(Segnalazione entity);
	Segnalazione dtoToEntity(SegnalazioneModel dto);
	
	Set<SegnalazioneModel> entityToDtoSet(Set<Segnalazione> entitySet);
	Set<Segnalazione> dtoToEntitySet(Set<SegnalazioneModel> dtoSet);
	
	@AfterMapping
	default void setComune(@MappingTarget Segnalazione entity, SegnalazioneModel dto) {
		if (dto.getComuneEstero()!= null) {
			entity.setComune(dto.getComuneEstero());
		}
		
	}
	
	@AfterMapping
	default void setComuneEstero(@MappingTarget SegnalazioneModel dto, Segnalazione entity) {
		if (entity.getCodIstat()!= null) {
			dto.setComuneEstero(entity.getComune());
		}

		dto.setNomeUtente(entity.getUsernameIns().getNome() + " " + entity.getUsernameIns().getCognome() );
		
	}
}
