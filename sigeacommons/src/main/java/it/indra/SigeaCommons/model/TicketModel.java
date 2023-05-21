package it.indra.SigeaCommons.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TicketModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long eventoId;
	
	@Size(max=50)
	private String tipoTicket;

	private BigDecimal ticketIntero;

	private BigDecimal ticketRidotto;

	private BigDecimal ticketGruppiRidotto;

	private Long ticketGruppiNumero;

	private Boolean flagGratisAnziani = false;

	private Boolean flagGratisBambini = false;

	private Boolean flagGratisConvenzioni = false;

	private Boolean flagGratisDisabili = false;

	private Boolean flagGratisAccompagnatori = false;

	private Boolean flagRidottoAnziani = false;

	private Boolean flagRidottoBambini = false;

	private Boolean flagRidottoConvenzioni = false;

	private Boolean flagRidottoDisabili = false;
	
	private Boolean flagRidottoAccompagnatori = false;

	@Size(max=300)
	private String convenzioniAttiveG;
	
	@Size(max=300)
	private String convenzioniAttiveR;

	@Size(max=300)
	private String linkPrenotazioni;
	
	private Map<String,String> notaMulti = new HashMap<>();
}
