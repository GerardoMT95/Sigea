package it.indra.sigea2vip.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class OrariocadenzaEntityPK implements Serializable {
	private long idorario;
	private long idcadenza;

	@Column(name = "IDORARIO")
	@Id
	public long getIdorario() {
		return idorario;
	}

	public void setIdorario(long idorario) {
		this.idorario = idorario;
	}

	@Column(name = "IDCADENZA")
	@Id
	public long getIdcadenza() {
		return idcadenza;
	}

	public void setIdcadenza(long idcadenza) {
		this.idcadenza = idcadenza;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		OrariocadenzaEntityPK that = (OrariocadenzaEntityPK) o;
		return idorario == that.idorario &&
						idcadenza == that.idcadenza;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idorario, idcadenza);
	}
}
