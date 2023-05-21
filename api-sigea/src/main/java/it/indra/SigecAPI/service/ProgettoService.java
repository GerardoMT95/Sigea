package it.indra.SigecAPI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.indra.SigeaCommons.model.ProgettoModel;
import it.indra.SigecAPI.entity.Progetto;
import it.indra.SigecAPI.mapper.ProgettoMapper;
import it.indra.SigecAPI.repository.ProgettoRepository;

@Service
public class ProgettoService {
	
	@Autowired
	private ProgettoRepository progettoRepository;
	
	@Autowired
	private ProgettoMapper progettoMapper;
	


}
