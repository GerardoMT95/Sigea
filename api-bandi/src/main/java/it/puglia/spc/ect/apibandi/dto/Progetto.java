package it.puglia.spc.ect.apibandi.dto;

public class Progetto {
	String id_bando;
	String nome_bando;
	String id_progetto;
	String titolo_progetto;
	String id_impresa;
	String codice_fiscale;
	String denominazione;
	String forma_partecipazione;


	public String getId_progetto() {
		return id_progetto;
	}

	public void setId_progetto(String id_progetto) {
		this.id_progetto = id_progetto;
	}

	public String getTitolo_progetto() {
		return titolo_progetto;
	}

	public void setTitolo_progetto(String titolo_progetto) {
		this.titolo_progetto = titolo_progetto;
	}

	public String getId_impresa() {
		return id_impresa;
	}

	public void setId_impresa(String id_impresa) {
		this.id_impresa = id_impresa;
	}

	public String getCodice_fiscale() {
		return codice_fiscale;
	}

	public void setCodice_fiscale(String codice_fiscale) {
		this.codice_fiscale = codice_fiscale;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public String getForma_partecipazione() {
		return forma_partecipazione;
	}

	public void setForma_partecipazione(String forma_partecipazione) {
		this.forma_partecipazione = forma_partecipazione;
	}

	public String getId_bando() {
		return id_bando;
	}

	public void setId_bando(String id_bando) {
		this.id_bando = id_bando;
	}

	public String getNome_bando() {
		return nome_bando;
	}

	public void setNome_bando(String nome_bando) {
		this.nome_bando = nome_bando;
	}

	@Override
	public String toString() {
		return "Progetto [id_bando=" + id_bando + ", nome_bando=" + nome_bando + ", id_progetto=" + id_progetto
				+ ", titolo_progetto=" + titolo_progetto + ", id_impresa=" + id_impresa + ", codice_fiscale="
				+ codice_fiscale + ", denominazione=" + denominazione + ", forma_partecipazione=" + forma_partecipazione
				+ "]";
	}

	
	
}
