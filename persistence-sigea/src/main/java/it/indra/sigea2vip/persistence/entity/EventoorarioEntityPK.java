package it.indra.sigea2vip.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class EventoorarioEntityPK implements Serializable {
	private long idorario;
	private long idevento;

	@Column(name = "IDORARIO")
	@Id
	public long getIdorario() {
		return idorario;
	}

	public void setIdorario(long idorario) {
		this.idorario = idorario;
	}

	@Column(name = "IDEVENTO")
	@Id
	public long getIdevento() {
		return idevento;
	}

	public void setIdevento(long idevento) {
		this.idevento = idevento;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		EventoorarioEntityPK that = (EventoorarioEntityPK) o;
		return idorario == that.idorario &&
						idevento == that.idevento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idorario, idevento);
	}
}
