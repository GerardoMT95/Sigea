package it.indra.sigea2vip.persistence.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ATTIVITA", schema = "TURISMO")
public class AttivitaEntity {
	private long id;
	private String tipologia;

	@Id
	@Basic
	@Column(name = "ID")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Basic
	@Column(name = "TIPOLOGIA")
	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AttivitaEntity that = (AttivitaEntity) o;
		return id == that.id &&
						Objects.equals(tipologia, that.tipologia);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, tipologia);
	}
}
