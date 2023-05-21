package it.indra.SigeaCommons.model;

import java.util.Set;

public class BandoEventoDTO {

	private String idEvento;
	private String idProgetto;
	private String cfPromotore;
	private String cfAziendaPromotrice;
	private String denominazioneAziendaPromotrice;
	private String ragioneSocialeAziendaPromotrice;
	private String nomeOrganizzazione;
	private String titoloEvento;
	private Set<BandoPeriodoDTO> dataEvento;
	private Set<BandoLocationDTO> luogoEvento;
	private String dataPresentazione;
	private String dataValidazione;
	private String dataPubblicazione;

	public String getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(String idEvento) {
		this.idEvento = idEvento;
	}

	public String getIdProgetto() {
		return idProgetto;
	}

	public void setIdProgetto(String idProgetto) {
		this.idProgetto = idProgetto;
	}

	public String getCfPromotore() {
		return cfPromotore;
	}

	public void setCfPromotore(String cfPromotore) {
		this.cfPromotore = cfPromotore;
	}

	public String getCfAziendaPromotrice() {
		return cfAziendaPromotrice;
	}

	public void setCfAziendaPromotrice(String cfAziendaPromotrice) {
		this.cfAziendaPromotrice = cfAziendaPromotrice;
	}

	public String getDenominazioneAziendaPromotrice() {
		return denominazioneAziendaPromotrice;
	}

	public void setDenominazioneAziendaPromotrice(String denominazioneAziendaPromotrice) {
		this.denominazioneAziendaPromotrice = denominazioneAziendaPromotrice;
	}

	public String getRagioneSocialeAziendaPromotrice() {
		return ragioneSocialeAziendaPromotrice;
	}

	public void setRagioneSocialeAziendaPromotrice(String ragioneSocialeAziendaPromotrice) {
		this.ragioneSocialeAziendaPromotrice = ragioneSocialeAziendaPromotrice;
	}

	public String getNomeOrganizzazione() {
		return nomeOrganizzazione;
	}

	public void setNomeOrganizzazione(String nomeOrganizzazione) {
		this.nomeOrganizzazione = nomeOrganizzazione;
	}

	public String getTitoloEvento() {
		return titoloEvento;
	}

	public void setTitoloEvento(String titoloEvento) {
		this.titoloEvento = titoloEvento;
	}


	public Set<BandoPeriodoDTO> getDataEvento() {
		return dataEvento;
	}

	public Set<BandoLocationDTO> getLuogoEvento() {
		return luogoEvento;
	}

	public void setDataEvento(Set<BandoPeriodoDTO> dataEvento) {
		this.dataEvento = dataEvento;
	}

	public void setLuogoEvento(Set<BandoLocationDTO> luogoEvento) {
		this.luogoEvento = luogoEvento;
	}

	public String getDataPresentazione() {
		return dataPresentazione;
	}

	public void setDataPresentazione(String dataPresentazione) {
		this.dataPresentazione = dataPresentazione;
	}

	public String getDataValidazione() {
		return dataValidazione;
	}

	public void setDataValidazione(String dataValidazione) {
		this.dataValidazione = dataValidazione;
	}

	public String getDataPubblicazione() {
		return dataPubblicazione;
	}

	public void setDataPubblicazione(String dataPubblicazione) {
		this.dataPubblicazione = dataPubblicazione;
	}

}
