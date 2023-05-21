package it.indra.sigea2vip.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class GiornochiusuraEntityPK implements Serializable {
	private long idorario;
	private long idgiornosettimana;

	@Column(name = "IDORARIO")
	@Id
	public long getIdorario() {
		return idorario;
	}

	public void setIdorario(long idorario) {
		this.idorario = idorario;
	}

	@Column(name = "IDGIORNOSETTIMANA")
	@Id
	public long getIdgiornosettimana() {
		return idgiornosettimana;
	}

	public void setIdgiornosettimana(long idgiornosettimana) {
		this.idgiornosettimana = idgiornosettimana;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		GiornochiusuraEntityPK that = (GiornochiusuraEntityPK) o;
		return idorario == that.idorario &&
						idgiornosettimana == that.idgiornosettimana;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idorario, idgiornosettimana);
	}
}
