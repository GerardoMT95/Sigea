package it.indra.sigea2vip.persistence.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@IdClass(AttivitamediaEntityPK.class)
@Table(name = "ATTIVITAMEDIA", schema = "TURISMO")
public class AttivitamediaEntity {
	private long idAttivita;
	private long idMultimedia;
	private Long posizione;

	@Id
	@Basic
	@Column(name = "IDATTIVITA")
	public long getIdAttivita() {
		return idAttivita;
	}

	public void setIdAttivita(long idAttivita) {
		this.idAttivita = idAttivita;
	}

	@Id
	@Basic
	@Column(name = "IDMULTIMEDIA")
	public long getIdMultimedia() {
		return idMultimedia;
	}

	public void setIdMultimedia(long idMultimedia) {
		this.idMultimedia = idMultimedia;
	}


	@Basic
	@Column(name = "POSIZIONE")
	public Long getPosizione() {
		return posizione;
	}

	public void setPosizione(Long posizione) {
		this.posizione = posizione;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AttivitamediaEntity that = (AttivitamediaEntity) o;
		return Objects.equals(posizione, that.posizione);
	}

	@Override
	public int hashCode() {
		return Objects.hash(posizione);
	}
}
