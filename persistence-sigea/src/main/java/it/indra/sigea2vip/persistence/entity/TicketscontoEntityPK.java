package it.indra.sigea2vip.persistence.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Id;

public class TicketscontoEntityPK implements Serializable {
	private long idticket;
	private long idsconto;

	@Column(name = "IDTICKET")
	@Id
	public long getIdticket() {
		return idticket;
	}

	public void setIdticket(long idticket) {
		this.idticket = idticket;
	}

	@Column(name = "IDSCONTO")
	@Id
	public long getIdsconto() {
		return idsconto;
	}

	public void setIdsconto(long idsconto) {
		this.idsconto = idsconto;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TicketscontoEntityPK that = (TicketscontoEntityPK) o;
		return idticket == that.idticket &&
						idsconto == that.idsconto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idticket, idsconto);
	}
}
