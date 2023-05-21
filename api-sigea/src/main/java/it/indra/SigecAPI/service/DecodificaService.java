package it.indra.SigecAPI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.indra.SigeaCommons.model.DecodificaModel;
import it.indra.SigecAPI.mapper.DecodificaMapper;
import it.indra.SigecAPI.repository.DecodificaRepository;

@Service
public class DecodificaService {
	
	@Autowired
	DecodificaRepository decodificaRepository;
	
	@Autowired
	DecodificaMapper decodificaMapper;
	
	public List<DecodificaModel> getDecodifiche(){
		return decodificaMapper.entityToDtoList(decodificaRepository.findAll());
	}
}
