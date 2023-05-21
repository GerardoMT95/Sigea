package it.indra.sigea2vip.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class FotointEntityPK implements Serializable {
	private long idfoto;
	private String idlingua;

	@Column(name = "IDFOTO")
	@Id
	public long getIdfoto() {
		return idfoto;
	}

	public void setIdfoto(long idfoto) {
		this.idfoto = idfoto;
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
		FotointEntityPK that = (FotointEntityPK) o;
		return idfoto == that.idfoto &&
						Objects.equals(idlingua, that.idlingua);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idfoto, idlingua);
	}
}
