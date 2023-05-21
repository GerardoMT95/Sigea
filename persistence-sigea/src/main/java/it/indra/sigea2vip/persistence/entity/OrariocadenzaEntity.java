package it.indra.sigea2vip.persistence.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "ORARIOCADENZA", schema = "TURISMO")
@IdClass(OrariocadenzaEntityPK.class)
public class OrariocadenzaEntity {
	private long idorario;
	private long idcadenza;
	//private OrarioEntity orarioByIdorario;

	@Id
	@Column(name = "IDORARIO")
	public long getIdorario() {
		return idorario;
	}

	public void setIdorario(long idorario) {
		this.idorario = idorario;
	}

	@Id
	@Column(name = "IDCADENZA")
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
		OrariocadenzaEntity that = (OrariocadenzaEntity) o;
		return idorario == that.idorario &&
						idcadenza == that.idcadenza;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idorario, idcadenza);
	}

	/*@ManyToOne
	@JoinColumn(name = "IDORARIO", referencedColumnName = "IDORARIO", nullable = false)
	public OrarioEntity getOrarioByIdorario() {
		return orarioByIdorario;
	}
*/
//	public void setOrarioByIdorario(OrarioEntity orarioByIdorario) {
//		this.orarioByIdorario = orarioByIdorario;
//	}
}
