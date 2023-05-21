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
@Table(name = "ATTRATTOREINT", schema = "TURISMO")
public class AttrattoreIntEntity {

	private long idAttrattore;
	private String idLingua;
	private String denominazione;


	@Column(name = "IDATTRATTORE")
	@Id
	public long getIdAttrattore() {
		return idAttrattore;
	}

	public void setIdAttrattore(long idAttrattore) {
		this.idAttrattore = idAttrattore;
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
