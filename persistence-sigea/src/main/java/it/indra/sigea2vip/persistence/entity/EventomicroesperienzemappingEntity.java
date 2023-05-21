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
@Table(name = "EVENTOMICROESPERIENZEMAPPING", schema = "TURISMO")
@IdClass(EventomicroesperienzemappingEntityPK.class)
public class EventomicroesperienzemappingEntity {
	private String idmicroesperienza;
	private long idevento;

	@Id
	@Column(name = "IDMICROESPERIENZA")
	public String getIdmicroesperienza() {
		return idmicroesperienza;
	}

	public void setIdmicroesperienza(String idmicroesperienza) {
		this.idmicroesperienza = idmicroesperienza;
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
		EventomicroesperienzemappingEntity that = (EventomicroesperienzemappingEntity) o;
		return idevento == that.idevento &&
						Objects.equals(idmicroesperienza, that.idmicroesperienza);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idmicroesperienza, idevento);
	}
}
