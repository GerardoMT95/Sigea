package it.indra.sigea2vip.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class EventoattrattorimappingEntityPK implements Serializable {
	private long idattrattore;
	private long idevento;

	@Column(name = "IDATTRATTORE")
	@Id
	public long getIdattrattore() {
		return idattrattore;
	}

	public void setIdattrattore(long idattrattore) {
		this.idattrattore = idattrattore;
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
		EventoattrattorimappingEntityPK that = (EventoattrattorimappingEntityPK) o;
		return idattrattore == that.idattrattore &&
						idevento == that.idevento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idattrattore, idevento);
	}
}
