package it.indra.sigea2vip.persistence.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Id;

public class EventocategoriamappingEntityPK implements Serializable {
	private long idevento;
	private long idcategoria;

	@Column(name = "IDEVENTO")
	@Id
	public long getIdevento() {
		return idevento;
	}

	public void setIdevento(long idevento) {
		this.idevento = idevento;
	}

	@Column(name = "IDCATEGORIA")
	@Id
	public long getIdcategoria() {
		return idcategoria;
	}

	public void setIdcategoria(long idcategoria) {
		this.idcategoria = idcategoria;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		EventocategoriamappingEntityPK that = (EventocategoriamappingEntityPK) o;
		return idevento == that.idevento &&
						idcategoria == that.idcategoria;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idevento, idcategoria);
	}
}
