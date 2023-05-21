package it.indra.sigea2vip.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "COMUNERIFERIMENTOINT", schema = "TURISMO")
public class ComuneRiferimentoIntEntity {

	private long idComuneRiferimento;
	private String idProvinciaRiferimento;
	private String idLingua;
	private String denominazione;

	@Column(name = "IDCOMUNERIFERIMENTO")
	@Id
	public long getIdComuneRiferimento() {
		return idComuneRiferimento;
	}

	public void setIdComuneRiferimento(long idComuneRiferimento) {
		this.idComuneRiferimento = idComuneRiferimento;
	}

	@Column(name = "IDPROVINCIARIFERIMENTO")
	public String getIdProvinciaRiferimento() {
		return idProvinciaRiferimento;
	}

	public void setIdProvinciaRiferimento(String idProvinciaRiferimento) {
		this.idProvinciaRiferimento = idProvinciaRiferimento;
	}

	@Column(name = "IDLINGUA")
	public String getIdLingua() {
		return idLingua;
	}

	public void setIdLingua(String idLingua) {
		this.idLingua = idLingua;
	}

	@Column(name = "DENOMINAZIONE")
	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}



}
