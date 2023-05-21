package it.puglia.spc.ect.apibandi.service;

import org.springframework.stereotype.Service;

import com.indra.es.utils.exceptions.RestException;
import com.indra.es.utils.model.WSO2Token;

import it.puglia.spc.ect.apibandi.dto.Bandi;
import it.puglia.spc.ect.apibandi.dto.Progetti;
import it.puglia.spc.ect.commonsbandi.BandiDTO;
import it.puglia.spc.ect.commonsbandi.ProgettiDTO;

@Service
public interface BandiService {

	
	public BandiDTO findBandi() throws RestException;

	public ProgettiDTO findProgetti(String codiceFiscalePiva) throws RestException;

}