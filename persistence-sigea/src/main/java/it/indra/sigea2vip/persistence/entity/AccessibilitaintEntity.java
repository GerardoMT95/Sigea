package it.indra.sigea2vip.persistence.entity;

import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "ACCESSIBILITAINT", schema = "TURISMO", catalog = "")
@IdClass(AccessibilitaintEntityPK.class)
public class AccessibilitaintEntity {
	private String idaccessibilita;
	private String idlingua;
	private String denominazione;

	@Id
	@Column(name = "IDACCESSIBILITA")
	public String getIdaccessibilita() {
		return idaccessibilita;
	}

	public void setIdaccessibilita(String idaccessibilita) {
		this.idaccessibilita = idaccessibilita;
	}

	@Id
	@Column(name = "IDLINGUA")
	public String getIdlingua() {
		return idlingua;
	}

	public void setIdlingua(String idlingua) {
		this.idlingua = idlingua;
	}

	@Basic
	@Column(name = "DENOMINAZIONE")
	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AccessibilitaintEntity that = (AccessibilitaintEntity) o;
		return Objects.equals(idaccessibilita, that.idaccessibilita) &&
						Objects.equals(idlingua, that.idlingua) &&
						Objects.equals(denominazione, that.denominazione);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idaccessibilita, idlingua, denominazione);
	}
}
