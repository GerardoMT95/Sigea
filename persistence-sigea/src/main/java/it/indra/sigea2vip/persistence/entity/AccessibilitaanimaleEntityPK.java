package it.indra.sigea2vip.persistence.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Id;

public class AccessibilitaanimaleEntityPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long idmapping;
	private String idtipoanimale;

	@Column(name = "IDMAPPING")
	@Id
	public long getIdmapping() {
		return idmapping;
	}

	public void setIdmapping(long idmapping) {
		this.idmapping = idmapping;
	}

	@Column(name = "IDTIPOANIMALE")
	@Id
	public String getIdtipoanimale() {
		return idtipoanimale;
	}

	public void setIdtipoanimale(String idtipoanimale) {
		this.idtipoanimale = idtipoanimale;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AccessibilitaanimaleEntityPK that = (AccessibilitaanimaleEntityPK) o;
		return idmapping == that.idmapping &&
						Objects.equals(idtipoanimale, that.idtipoanimale);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idmapping, idtipoanimale);
	}
}
