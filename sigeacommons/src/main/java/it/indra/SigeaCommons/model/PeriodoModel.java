package it.indra.SigeaCommons.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PeriodoModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long periodoId;
	
	private Boolean dataSecca;
	

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "it-IT", timezone = "Europe/Rome")
	private Date dataDa;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "it-IT", timezone = "Europe/Rome")
	private Date dataA;
	
	private Boolean fgContinuato;
	
	@Size(max=10)
	private String orarioApertura;
	
	@Size(max=10)
	private String orarioChiusura;
	
	@Size(max=10)
	private String orarioAperturaMattina;
	
	@Size(max=10)
	private String orarioChiusuraMattina;

	@Size(max=10)
	private String orarioAperturaPomeriggio;

	@Size(max=10)
	private String orarioChiusuraPomeriggio;
	
	private Boolean chiusuraDom = false;
	
	private Boolean chiusuraLun = false;

	private Boolean chiusuraMar = false;

	private Boolean chiusuraMer = false;

	private Boolean chiusuraGio = false;

	private Boolean chiusuraVen = false;

	private Boolean chiusuraSab = false;
	
	private String cadenza = "Nessuna";
	
	private Boolean cadenzaDom = false;
	
	private Boolean cadenzaLun = false;

	private Boolean cadenzaMar = false;

	private Boolean cadenzaMer = false;

	private Boolean cadenzaGio = false;

	private Boolean cadenzaVen = false;

	private Boolean cadenzaSab = false;
	
	private Set<String> cadenzaMensile = new HashSet<>();
	
}
