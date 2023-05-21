package it.indra.sigea2vip.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class MicroesperienzaintEntityPK implements Serializable {
	private String idlingua;
	private String idmicroesperienza;

	@Column(name = "IDLINGUA")
	@Id
	public String getIdlingua() {
		return idlingua;
	}

	public void setIdlingua(String idlingua) {
		this.idlingua = idlingua;
	}

	@Column(name = "IDMICROESPERIENZA")
	@Id
	public String getIdmicroesperienza() {
		return idmicroesperienza;
	}

	public void setIdmicroesperienza(String idmicroesperienza) {
		this.idmicroesperienza = idmicroesperienza;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		MicroesperienzaintEntityPK that = (MicroesperienzaintEntityPK) o;
		return Objects.equals(idlingua, that.idlingua) &&
						Objects.equals(idmicroesperienza, that.idmicroesperienza);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idlingua, idmicroesperienza);
	}
}
