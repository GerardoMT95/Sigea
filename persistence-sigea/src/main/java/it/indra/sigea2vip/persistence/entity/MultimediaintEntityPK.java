package it.indra.sigea2vip.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class MultimediaintEntityPK implements Serializable {
	private String idlingua;
	private long idmultimedia;

	@Column(name = "IDLINGUA")
	@Id
	public String getIdlingua() {
		return idlingua;
	}

	public void setIdlingua(String idlingua) {
		this.idlingua = idlingua;
	}

	@Column(name = "IDMULTIMEDIA")
	@Id
	public long getIdmultimedia() {
		return idmultimedia;
	}

	public void setIdmultimedia(long idmultimedia) {
		this.idmultimedia = idmultimedia;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		MultimediaintEntityPK that = (MultimediaintEntityPK) o;
		return idmultimedia == that.idmultimedia &&
						Objects.equals(idlingua, that.idlingua);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idlingua, idmultimedia);
	}
}
