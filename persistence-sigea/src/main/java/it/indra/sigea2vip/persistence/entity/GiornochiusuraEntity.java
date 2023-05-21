package it.indra.sigea2vip.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "GIORNOCHIUSURA", schema = "TURISMO")
@IdClass(GiornochiusuraEntityPK.class)
public class GiornochiusuraEntity {
	private long idorario;
	private long idgiornosettimana;

	@Id
	@Column(name = "IDORARIO")
	public long getIdorario() {
		return idorario;
	}

	public void setIdorario(long idorario) {
		this.idorario = idorario;
	}

	@Id
	@Column(name = "IDGIORNOSETTIMANA")
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
		GiornochiusuraEntity that = (GiornochiusuraEntity) o;
		return idorario == that.idorario &&
						idgiornosettimana == that.idgiornosettimana;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idorario, idgiornosettimana);
	}
}
