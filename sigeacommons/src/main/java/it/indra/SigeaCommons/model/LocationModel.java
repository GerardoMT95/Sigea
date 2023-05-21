package it.indra.SigeaCommons.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LocationModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long locationId;
	
	private Boolean fgPrincipale;
	
	private String codNazione;
	
	private String codRegione;
	
	private String codProvincia;
	
	private String codComune;
	
	private String codArea;
	
	@Size(max=300)
	private String indirizzo;
	
	@Size(max=5)
	private String cap;
	
	private Boolean puglia;
	
	@Size(max=300)
	private String nazione;
	
	@Size(max=300)
	private String regione;
	
	@Size(max=300)
	private String area;
	
	@Size(max=300)
	private String provincia;
	
	@Size(max=300)
	private String comune;
	
	@Size(max=300)
	private String comuneEstero;
	
	@Size(max=300)
	private String link;
	
	@Size(max=300)
	private String nomeLocation;
	
	private BigDecimal latitudine;
	
	private BigDecimal longitudine;
	
	private Set<AttrattoreModel> attrattoriSet  = new HashSet<>();
}
