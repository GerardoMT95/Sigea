package it.puglia.spc.ect.apibandi.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import it.puglia.spc.ect.apibandi.dto.Bandi;
import it.puglia.spc.ect.apibandi.dto.Bando;
import it.puglia.spc.ect.commonsbandi.BandiDTO;
import it.puglia.spc.ect.commonsbandi.BandoDTO;

@Component
public class BandiConverter implements Converter<Bandi, BandiDTO> {

	@Autowired
	BandoConverter bandoConverter;

	@Override
	public BandiDTO convert(Bandi source) {
		BandiDTO bandi = null;

		if (source != null) {
			bandi = new BandiDTO();
			if (source.getItems() != null) {
				List<BandoDTO> bandiDTO = new ArrayList<BandoDTO>();
				for (Bando b : source.getItems()) {
					BandoDTO bandoDTO = bandoConverter.convert(b);
					bandiDTO.add(bandoDTO);
				}
				bandi.setItems(bandiDTO);
			}
		}

		return bandi;
	}

}
