package it.indra.sigea2vip.persistence.entity;

import java.util.Objects;

import javax.persistence.Basic;
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
@Table(name = "MULTIMEDIAEVENTO", schema = "TURISMO")
@IdClass(MultimediaeventoEntityPK.class)
public class MultimediaeventoEntity {
	private Long posizione;
	private long idmultimedia;
	private long idevento;
//	private MultimediaEntity multimediaByIdmultimedia;

	@Basic
	@Column(name = "POSIZIONE")
	public Long getPosizione() {
		return posizione;
	}

	public void setPosizione(Long posizione) {
		this.posizione = posizione;
	}

	@Id
	@Column(name = "IDMULTIMEDIA")
	public long getIdmultimedia() {
		return idmultimedia;
	}

	public void setIdmultimedia(long idmultimedia) {
		this.idmultimedia = idmultimedia;
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
		MultimediaeventoEntity that = (MultimediaeventoEntity) o;
		return idmultimedia == that.idmultimedia &&
						idevento == that.idevento &&
						Objects.equals(posizione, that.posizione);
	}

	@Override
	public int hashCode() {
		return Objects.hash(posizione, idmultimedia, idevento);
	}

	/*@ManyToOne
	@JoinColumn(name = "IDMULTIMEDIA", referencedColumnName = "IDMULTIMEDIA", nullable = false)
	public MultimediaEntity getMultimediaByIdmultimedia() {
		return multimediaByIdmultimedia;
	}*/

//	public void setMultimediaByIdmultimedia(MultimediaEntity multimediaByIdmultimedia) {
//		this.multimediaByIdmultimedia = multimediaByIdmultimedia;
//	}
}
