package it.indra.sigea2vip.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class AttivitaturisticaintEntityPK implements Serializable {
	private long idattivita;
	private String idlingua;

	@Column(name = "IDATTIVITA")
	@Id
	public long getIdattivita() {
		return idattivita;
	}

	public void setIdattivita(long idattivita) {
		this.idattivita = idattivita;
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
		AttivitaturisticaintEntityPK that = (AttivitaturisticaintEntityPK) o;
		return idattivita == that.idattivita &&
						Objects.equals(idlingua, that.idlingua);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idattivita, idlingua);
	}
}
