package it.indra.sigea2vip.persistence.entity;

import java.util.stream.Stream;

public enum LinguaEvento {
	it("it"), en("en"), ru("ru"), de("de"), es("es"), fr("fr");

	private String linguaEvento;

	private LinguaEvento(String linguaEvento) {
		this.linguaEvento = linguaEvento;
	}

	public String getLinguaEvento() {
		return linguaEvento;
	}

	public static LinguaEvento of(String linguaEvento) {
		return Stream.of(LinguaEvento.values()).filter(p -> p.getLinguaEvento().equalsIgnoreCase(linguaEvento))
				.findFirst().orElse(null);
	}
}
