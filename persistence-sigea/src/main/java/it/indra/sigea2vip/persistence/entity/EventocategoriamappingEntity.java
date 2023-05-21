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

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EVENTOCATEGORIAMAPPING", schema = "TURISMO")
@IdClass(EventocategoriamappingEntityPK.class)
public class EventocategoriamappingEntity {
	private long idevento;
	private long idcategoria;
	//private EventoEntity eventoByIdevento;

	@Id
	@Column(name = "IDEVENTO")
	public long getIdevento() {
		return idevento;
	}

	public void setIdevento(long idevento) {
		this.idevento = idevento;
	}

	@Id
	@Column(name = "IDCATEGORIA")
	public long getIdcategoria() {
		return idcategoria;
	}

	public void setIdcategoria(long idcategoria) {
		this.idcategoria = idcategoria;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		EventocategoriamappingEntity that = (EventocategoriamappingEntity) o;
		return idevento == that.idevento &&
						idcategoria == that.idcategoria;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idevento, idcategoria);
	}

//	@ManyToOne
//	@JoinColumn(name = "pk_eventoByIdevento", referencedColumnName = "IDEVENTO", nullable = false)
//	public EventoEntity getEventoByIdevento() {
//		return eventoByIdevento;
//	}
//
//	public void setEventoByIdevento(EventoEntity eventoByIdevento) {
//		this.eventoByIdevento = eventoByIdevento;
//	}
}
