package it.indra.sigea2vip.persistence.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Id;

public class EventointEntityPK implements Serializable {

	private long idEvento;
	private String idLingua;
	
	@Id
	@Column(name = "IDEVENTO")
	public long getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(long idEvento) {
		this.idEvento = idEvento;
	}
	
	@Id
	@Column(name = "IDLINGUA")
	public String getIdLingua() {
		return idLingua;
	}

	public void setIdLingua(String idLingua) {
		this.idLingua = idLingua;
	}
	

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		EventointEntityPK that = (EventointEntityPK) o;
		return idEvento == that.idEvento &&
				Objects.equals(idLingua, that.idLingua);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idEvento, idLingua);
	}

}
