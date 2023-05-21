package it.indra.SigeaCommons.enumeration;

import java.util.stream.Stream;

public enum StatoPubblicazione {
	BOZZA("In compilazione"), PUBBLICATO("Pubblicato"),
	REDATTO("Redatto"), RIFIUTATO("Respinto"), SOSPESO("Sospeso");

	private String statoEvento;

	private StatoPubblicazione(String statoEvento) {
		this.statoEvento = statoEvento;
	}

	public String getStatoEvento() {
		return statoEvento;
	}
	
	//Se non viene trovato uno stato evento valido l'evento viene salvato come BOZZA
	public static StatoPubblicazione of(String statoEvento) {
		return Stream.of(StatoPubblicazione.values()).filter(p -> p.getStatoEvento().equalsIgnoreCase(statoEvento)).findFirst().orElse(BOZZA);
	}
}
