package it.puglia.spc.ect.apibandi.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import it.puglia.spc.ect.apibandi.dto.Progetto;
import it.puglia.spc.ect.commonsbandi.ProgettoDTO;
@Component
public class ProgettoConverter implements Converter<Progetto, ProgettoDTO> {

	@Override
	public ProgettoDTO convert(Progetto source) {
		ProgettoDTO progetto = null;
		if (source != null) {
			progetto = new ProgettoDTO();
			progetto.setId_bando(source.getId_bando());
			progetto.setNome_bando(source.getNome_bando());
			progetto.setCodice_fiscale(source.getCodice_fiscale());
			progetto.setDenominazione(source.getDenominazione());
			progetto.setForma_partecipazione(source.getForma_partecipazione());
			progetto.setId_progetto(source.getId_progetto());
			progetto.setTitolo_progetto(source.getTitolo_progetto());
			progetto.setId_impresa(source.getId_impresa());
		}
		return progetto;
	}
}
