package it.indra.sigea2vip.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class AttivitamezzoEntityPK implements Serializable {
	private long idattivita;
	private String idmezzo;

	@Column(name = "IDATTIVITA")
	@Id
	public long getIdattivita() {
		return idattivita;
	}

	public void setIdattivita(long idattivita) {
		this.idattivita = idattivita;
	}

	@Column(name = "IDMEZZO")
	@Id
	public String getIdmezzo() {
		return idmezzo;
	}

	public void setIdmezzo(String idmezzo) {
		this.idmezzo = idmezzo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AttivitamezzoEntityPK that = (AttivitamezzoEntityPK) o;
		return idattivita == that.idattivita &&
						Objects.equals(idmezzo, that.idmezzo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idattivita, idmezzo);
	}
}
