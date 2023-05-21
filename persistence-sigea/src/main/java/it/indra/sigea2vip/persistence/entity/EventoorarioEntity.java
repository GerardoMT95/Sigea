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
@Table(name = "EVENTOORARIO", schema = "TURISMO")
@IdClass(EventoorarioEntityPK.class)
public class EventoorarioEntity {
	private long idorario;
	private long idevento;
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
	@Column(name = "IDEVENTO")
	public long getIdevento() {
		return idevento;
	}

	public void setIdevento(long idevento) {
		this.idevento = idevento;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		EventoorarioEntity that = (EventoorarioEntity) o;
		return idorario == that.idorario &&
						idevento == that.idevento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idorario, idevento);
	}

/*
	@ManyToOne
	@JoinColumn(name = "IDORARIO", referencedColumnName = "IDORARIO", nullable = false)
	public OrarioEntity getOrarioByIdorario() {
		return orarioByIdorario;
	}
*/

//	public void setOrarioByIdorario(OrarioEntity orarioByIdorario) {
//		this.orarioByIdorario = orarioByIdorario;
//	}
}
