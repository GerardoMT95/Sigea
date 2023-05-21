package it.indra.sigea2vip.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class MultimediaeventoEntityPK implements Serializable {
	private long idmultimedia;
	private long idevento;

	@Column(name = "IDMULTIMEDIA")
	@Id
	public long getIdmultimedia() {
		return idmultimedia;
	}

	public void setIdmultimedia(long idmultimedia) {
		this.idmultimedia = idmultimedia;
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
		MultimediaeventoEntityPK that = (MultimediaeventoEntityPK) o;
		return idmultimedia == that.idmultimedia &&
						idevento == that.idevento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idmultimedia, idevento);
	}
}
