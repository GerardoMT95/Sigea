package it.indra.sigea2vip.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class AccessibilitadisabilitaEntityPK implements Serializable {
	private long idmapping;
	private String iddisabilita;

	@Column(name = "IDMAPPING")
	@Id
	public long getIdmapping() {
		return idmapping;
	}

	public void setIdmapping(long idmapping) {
		this.idmapping = idmapping;
	}

	@Column(name = "IDDISABILITA")
	@Id
	public String getIddisabilita() {
		return iddisabilita;
	}

	public void setIddisabilita(String iddisabilita) {
		this.iddisabilita = iddisabilita;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AccessibilitadisabilitaEntityPK that = (AccessibilitadisabilitaEntityPK) o;
		return idmapping == that.idmapping &&
						Objects.equals(iddisabilita, that.iddisabilita);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idmapping, iddisabilita);
	}
}
