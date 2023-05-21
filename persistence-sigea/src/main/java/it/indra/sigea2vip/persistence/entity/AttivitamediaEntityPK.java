package it.indra.sigea2vip.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class AttivitamediaEntityPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long idMultimedia;
	private long idAttivita;

	@Column(name = "IDMULTIMEDIA")
	@Id
	public long getIdMultimedia() {
		return idMultimedia;
	}

	public void setIdMultimedia(long idMultimedia) {
		this.idMultimedia = idMultimedia;
	}

	@Column(name = "IDATTIVITA")
	@Id
	public long getIdAttivita() {
		return idAttivita;
	}

	public void setIdAttivita(long idAttivita) {
		this.idAttivita = idAttivita;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AttivitamediaEntityPK that = (AttivitamediaEntityPK) o;
		return idMultimedia == that.idMultimedia &&
						idAttivita == that.idAttivita;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idMultimedia, idAttivita);
	}
}
