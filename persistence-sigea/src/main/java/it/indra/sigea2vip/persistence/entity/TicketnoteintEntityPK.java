package it.indra.sigea2vip.persistence.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Id;

public class TicketnoteintEntityPK implements Serializable {
	private long idticket;
	private String idlingua;

	@Column(name = "IDTICKET")
	@Id
	public long getIdticket() {
		return idticket;
	}

	public void setIdticket(long idticket) {
		this.idticket = idticket;
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
		TicketnoteintEntityPK that = (TicketnoteintEntityPK) o;
		return idticket == that.idticket &&
						Objects.equals(idlingua, that.idlingua);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idticket, idlingua);
	}
}
