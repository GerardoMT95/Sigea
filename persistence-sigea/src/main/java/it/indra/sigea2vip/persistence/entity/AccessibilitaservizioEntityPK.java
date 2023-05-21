package it.indra.sigea2vip.persistence.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Id;

public class AccessibilitaservizioEntityPK implements Serializable {
	private long idmapping;
	private long idservizioacc;

	@Column(name = "IDMAPPING")
	@Id
	public long getIdmapping() {
		return idmapping;
	}

	public void setIdmapping(long idmapping) {
		this.idmapping = idmapping;
	}

	@Column(name = "IDSERVIZIOACC")
	@Id
	public long getIdservizioacc() {
		return idservizioacc;
	}

	public void setIdservizioacc(long idservizioacc) {
		this.idservizioacc = idservizioacc;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AccessibilitaservizioEntityPK that = (AccessibilitaservizioEntityPK) o;
		return idmapping == that.idmapping &&
						idservizioacc == that.idservizioacc;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idmapping, idservizioacc);
	}
}
