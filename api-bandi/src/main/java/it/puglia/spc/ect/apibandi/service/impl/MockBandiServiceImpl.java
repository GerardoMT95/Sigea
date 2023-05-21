package it.puglia.spc.ect.apibandi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indra.es.utils.exceptions.RestException;

import it.puglia.spc.ect.apibandi.converter.BandiConverter;
import it.puglia.spc.ect.apibandi.converter.ProgettiConverter;
import it.puglia.spc.ect.apibandi.dto.BandiMock;
import it.puglia.spc.ect.apibandi.dto.Progetti;
import it.puglia.spc.ect.apibandi.dto.ProgettiMock;
import it.puglia.spc.ect.apibandi.dto.Progetto;
import it.puglia.spc.ect.apibandi.service.BandiService;
import it.puglia.spc.ect.commonsbandi.BandiDTO;
import it.puglia.spc.ect.commonsbandi.ProgettiDTO;
import lombok.extern.slf4j.Slf4j;

@Service("mockBandiService")
@Slf4j
public class MockBandiServiceImpl implements BandiService {

	@Autowired
	BandiMock bandiMock;
	@Autowired
	ProgettiMock progettiMock;

	@Autowired
	BandiConverter bandiConverter;

	@Autowired
	ProgettiConverter progettiConverter;

	public BandiDTO findBandi() throws RestException {

		log.info("Elenco bandi recuperati da valori mockati:" + bandiMock.toString());

		return bandiConverter.convert(bandiMock);

	}

	public ProgettiDTO findProgetti(String codiceFiscalePiva) throws RestException {

		Progetti filtered = progettiMock;
		
		List<Progetto> progettoList = filtered.getItems();
		progettoList=progettoList.stream()
				.filter(x -> x.getCodice_fiscale().equalsIgnoreCase(codiceFiscalePiva)).collect(Collectors.toList());
		filtered = new Progetti();
		filtered.setItems(progettoList);

		log.info("Elenco progetti recuperati da valori mockati con codice fiscale=" + codiceFiscalePiva + " :"
				+ filtered.toString());

		return progettiConverter.convert(filtered);
	}

}