package it.indra.SigeaCommons.model;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Set;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;


import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RelazioneModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long relazioneId;
	
	private Long eventoRelazionatoId;
	
	private String tipoEventoAssociato;
	
	@Size(max=20)
	private String tipoRelazione;
	
	private String titolo;
	
	String comune;
	
	String periodo;
	
	String schedePubblicazione;
	
	String statoEventoAssociato;
	
	String redazioneId;
	
	@JsonIgnore
	Set<LocationModel> locationSet;
	
	@JsonIgnore
	Set<PeriodoModel> periodoSet;
	
	@JsonIgnore
	Set<PubblicazioneModel> pubblicazioneSet;
	
	public void setPeriodoSet(Set<PeriodoModel> periodoSet){
		this.periodoSet = periodoSet;
		
		String periodo = null;
		Date   dataMin=  null;
		Date   dataMax=  null;
		
		if (this.periodoSet != null) {

			for (PeriodoModel period : this.periodoSet  ) {

				if (period.getDataDa()!=null && dataMin==null) {
					dataMin=period.getDataDa();
				}else if (period.getDataDa()!=null && period.getDataDa().compareTo(dataMin)<0) {
					dataMin=period.getDataDa();
				}


				if (period.getDataA()!=null && dataMax==null) {
					dataMax=period.getDataA();
				}else if (period.getDataA()!=null && period.getDataA().compareTo(dataMax)>0) {
					dataMax=period.getDataA();
				}
			}

			SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");
			if (dataMin != null && dataMax!=null) {
				if (dataMin.compareTo(dataMax)==0) {
					periodo = format.format(dataMin);
				}else if (dataMin.compareTo(dataMax)!=0) {
					periodo = "dal " + format.format(dataMin) + " al " + format.format(dataMax);
				} 
			}else {
				periodo = "n/d";
			}
			
			if(this.periodo == null || this.periodo.isEmpty()) {
				this.periodo = periodo;
			}
		}
	}
	
	public void setLocationSet(Set<LocationModel> locationSet){
		this.locationSet = locationSet;
		
		if(this.locationSet != null) {
			String comune = "";
			LocationModel tmpLocation = this.locationSet.stream().findFirst().orElse(new LocationModel());
			
			if(tmpLocation.getComune() != null ) {
				comune = tmpLocation.getComune();
			} else if(tmpLocation.getComuneEstero() != null) {
				comune = tmpLocation.getComuneEstero();
			}
			
			if(this.comune == null || this.comune.isEmpty()) {
				this.comune = comune;
			}
		}
	}
	
	public void setPubblicazioneSet(Set<PubblicazioneModel> pubblicazioneSet){
		this.pubblicazioneSet = pubblicazioneSet;
		
		if(pubblicazioneSet != null) {
			
			Iterator<PubblicazioneModel> iter = pubblicazioneSet.iterator();
			
			String result = "";
			
			while(iter.hasNext()) {
				
				PubblicazioneModel tmp = iter.next();
				
				result = result + tmp.getRedazioneId();
				
				if(iter.hasNext()) {
					result = result + "|";
				}
			}
			this.schedePubblicazione = result;
		}
	}
}
