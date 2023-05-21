package it.indra.sigea2vip.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class AccessibilitaspaziobambiniEntityPK implements Serializable {
	private long idmapping;
	private String idspazio;

	@Column(name = "IDMAPPING")
	@Id
	public long getIdmapping() {
		return idmapping;
	}

	public void setIdmapping(long idmapping) {
		this.idmapping = idmapping;
	}

	@Column(name = "IDSPAZIO")
	@Id
	public String getIdspazio() {
		return idspazio;
	}

	public void setIdspazio(String idspazio) {
		this.idspazio = idspazio;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AccessibilitaspaziobambiniEntityPK that = (AccessibilitaspaziobambiniEntityPK) o;
		return idmapping == that.idmapping &&
						Objects.equals(idspazio, that.idspazio);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idmapping, idspazio);
	}
}
