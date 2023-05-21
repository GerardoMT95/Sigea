package it.indra.sigea2vip.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class EventomicroesperienzemappingEntityPK implements Serializable {
	private String idmicroesperienza;
	private long idevento;

	@Column(name = "IDMICROESPERIENZA")
	@Id
	public String getIdmicroesperienza() {
		return idmicroesperienza;
	}

	public void setIdmicroesperienza(String idmicroesperienza) {
		this.idmicroesperienza = idmicroesperienza;
	}

	@Column(name = "IDEVENTO")
	@Id
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
		EventomicroesperienzemappingEntityPK that = (EventomicroesperienzemappingEntityPK) o;
		return idevento == that.idevento &&
						Objects.equals(idmicroesperienza, that.idmicroesperienza);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idmicroesperienza, idevento);
	}
}
