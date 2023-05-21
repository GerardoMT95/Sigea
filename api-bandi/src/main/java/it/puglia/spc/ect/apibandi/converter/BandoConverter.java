package it.puglia.spc.ect.apibandi.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import it.puglia.spc.ect.apibandi.dto.Bando;
import it.puglia.spc.ect.commonsbandi.BandoDTO;

@Component
public class BandoConverter implements Converter<Bando, BandoDTO> {

	@Override
	public BandoDTO convert(Bando source) {
		BandoDTO bando = null;
		if (source != null) {
			bando = new BandoDTO();
			bando.setId_bando(source.getId_bando());
			bando.setNome_bando(source.getNome_bando());
		}
		return bando;
	}
}
