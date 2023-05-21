package it.indra.sigea2vip.persistence.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Id;

public class AccessibilitaintEntityPK implements Serializable {
	private String idaccessibilita;
	private String idlingua;

	@Column(name = "IDACCESSIBILITA")
	@Id
	public String getIdaccessibilita() {
		return idaccessibilita;
	}

	public void setIdaccessibilita(String idaccessibilita) {
		this.idaccessibilita = idaccessibilita;
	}

	@Column(name = "IDLINGUA")
	@Id
	public String getIdlingua() {
		return idlingua;
	}

	public void setIdlingua(String idlingua) {
		this.idlingua = idlingua;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AccessibilitaintEntityPK that = (AccessibilitaintEntityPK) o;
		return Objects.equals(idaccessibilita, that.idaccessibilita) &&
						Objects.equals(idlingua, that.idlingua);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idaccessibilita, idlingua);
	}
}
