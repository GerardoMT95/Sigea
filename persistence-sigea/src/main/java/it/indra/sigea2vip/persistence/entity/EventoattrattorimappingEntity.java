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
@Entity
@ToString
@Table(name = "EVENTOATTRATTORIMAPPING", schema = "TURISMO")
@IdClass(EventoattrattorimappingEntityPK.class)
public class EventoattrattorimappingEntity {
	private long idattrattore;
	private long idevento;

	@Id
	@Column(name = "IDATTRATTORE")
	public long getIdattrattore() {
		return idattrattore;
	}

	public void setIdattrattore(long idattrattore) {
		this.idattrattore = idattrattore;
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
		EventoattrattorimappingEntity that = (EventoattrattorimappingEntity) o;
		return idattrattore == that.idattrattore &&
						idevento == that.idevento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idattrattore, idevento);
	}
}
