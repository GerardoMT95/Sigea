package it.indra.SigecAPI.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties("messages")
@Data
public class MessageRetriever {
	
	private String MODIFICA;
	
	private String INSERIMENTO;
	
	private String CANCELLAZIONE;

	private String Redattore_di;

	private String Creazione_Evento;

	private String Tipologia;

	private String Dati_Generali;

	private String Ticket;

	private String Location;

	private String Immagini;

	private String Documenti;

	private String Contatti;

	private String Relazioni;

	private String Periodi;

	private String Stato;

	private String Accessibilita;

	private String Attribuzioni;

	private String Caratteristiche;
	
	private String Bandi;

}
