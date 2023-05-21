package it.indra.SigecAPI.mapper;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import it.indra.SigeaCommons.model.ProdottoModel;
import it.indra.SigecAPI.entity.Prodotto;

@Mapper
public interface ProdottoMapper {
	
	ProdottoMapper INSTANCE = Mappers.getMapper(ProdottoMapper.class);
	
	ProdottoModel entityToDto(Prodotto entity);
	Prodotto dtoToEntity(ProdottoModel dto);
	
	Set<ProdottoModel> entityToDtoSet(Set<Prodotto> entitySet);
	Set<Prodotto> dtoToEntitySet(Set<ProdottoModel> dtoSet);

}