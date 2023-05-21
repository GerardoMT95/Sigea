package it.indra.SigecAPI.projection;

import java.sql.Timestamp;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;

import it.indra.SigecAPI.entity.Location;
import it.indra.SigecAPI.entity.Periodo;

public interface EventoTitoloProjection {
	
	@Value("#{target.eventoId}")
	Long getId();
	
	@Value("#{target.datiGenerali.titolo}")
    String getTitolo();
	
	@Value("#{target.dataIns}")
	Timestamp getDataIns();
	
	@Value("#{target.tipo}")
	String getTipo();

	@Value("#{target.locationSet}")
	Set<Location> getLocationSet();
	
	@Value("#{target.periodoSet}")
	Set<Periodo> getPeriodoSet();
}
