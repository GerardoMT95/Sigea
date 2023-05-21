package it.indra.SigecAPI.mapper;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import it.indra.SigeaCommons.model.DettaglioUtenteModel;
import it.indra.SigecAPI.entity.DettaglioUtente;

@Mapper(uses = {TipologiaMapper.class})
public interface DettaglioUtenteMapper {
	DettaglioUtenteMapper INSTANCE = Mappers.getMapper(DettaglioUtenteMapper.class);
	
	DettaglioUtenteModel entityToDto(DettaglioUtente entity);
	DettaglioUtente dtoToEntity(DettaglioUtenteModel dto);
	
	Set<DettaglioUtenteModel> entityToDtoSet(Set<DettaglioUtente> entitySet);
	Set<DettaglioUtente> dtoToEntitySet(Set<DettaglioUtenteModel> dtoSet);
}