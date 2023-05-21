package it.indra.SigecAPI.mapper;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import it.indra.SigeaCommons.model.TipologiaMIBACTModel;
import it.indra.SigecAPI.entity.TipologiaMIBACT;

@Mapper
public interface TipologiaMIBACTMapper {
	
	TipologiaMIBACTMapper INSTANCE = Mappers.getMapper(TipologiaMIBACTMapper.class);
	
	TipologiaMIBACTModel entityToDto(TipologiaMIBACT entity);
	TipologiaMIBACT dtoToEntity(TipologiaMIBACTModel dto);
	
	Set<TipologiaMIBACTModel> entityToDtoSet(Set<TipologiaMIBACT> entitySet);
	Set<TipologiaMIBACT> dtoToEntitySet(Set<TipologiaMIBACTModel> dtoSet);

}
