package it.indra.sigea2vip.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class EventoallegatiintEntityPK implements Serializable {
	private long idallegato;
	private String idlingua;

	@Column(name = "IDALLEGATO")
	@Id
	public long getIdallegato() {
		return idallegato;
	}

	public void setIdallegato(long idallegato) {
		this.idallegato = idallegato;
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
		EventoallegatiintEntityPK that = (EventoallegatiintEntityPK) o;
		return idallegato == that.idallegato &&
						Objects.equals(idlingua, that.idlingua);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idallegato, idlingua);
	}
}
