package it.puglia.spc.ect.apibandi.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import it.puglia.spc.ect.apibandi.dto.Progetti;
import it.puglia.spc.ect.apibandi.dto.Progetto;
import it.puglia.spc.ect.commonsbandi.ProgettiDTO;
import it.puglia.spc.ect.commonsbandi.ProgettoDTO;

@Component
public class ProgettiConverter implements Converter<Progetti, ProgettiDTO> {

	@Autowired
	ProgettoConverter progettoConverter;

	@Override
	public ProgettiDTO convert(Progetti source) {
		ProgettiDTO progetti = null;

		if (source != null) {
			progetti = new ProgettiDTO();
			if (source.getItems() != null) {
				List<ProgettoDTO> progettiDTO = new ArrayList<ProgettoDTO>();
				for (Progetto b : source.getItems()) {
					ProgettoDTO progettoDTO = progettoConverter.convert(b);
					progettiDTO.add(progettoDTO);
				}
				progetti.setItems(progettiDTO);
			}
		}

		return progetti;
	}
}
