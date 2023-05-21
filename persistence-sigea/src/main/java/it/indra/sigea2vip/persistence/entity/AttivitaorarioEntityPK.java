package it.indra.sigea2vip.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class AttivitaorarioEntityPK implements Serializable {
	private long idOrario;
	private long idAttivita;

	@Column(name = "IDORARIO")
	@Id
	public long getIdOrario() {
		return idOrario;
	}

	public void setIdOrario(long idOrario) {
		this.idOrario = idOrario;
	}
	@Column(name = "IDATTIVITA")
	@Id
	public long getIdAttivita() {
		return idAttivita;
	}

	public void setIdAttivita(long idAttivita) {
		this.idAttivita = idAttivita;
	}

}
