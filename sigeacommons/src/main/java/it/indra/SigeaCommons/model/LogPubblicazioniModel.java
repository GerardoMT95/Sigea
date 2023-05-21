package it.indra.SigeaCommons.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LogPubblicazioniModel implements Serializable {

	private static final long serialVersionUID = 1L;
	

	private String statoPubblicazione;
	
	
	private Timestamp dataModifica;

}
