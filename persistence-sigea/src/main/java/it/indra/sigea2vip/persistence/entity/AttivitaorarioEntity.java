package it.indra.sigea2vip.persistence.entity;

import java.io.Serializable;

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
@ToString
@Entity
@Table(name = "ATTIVITAORARIO", schema = "TURISMO")
@IdClass(AttivitaorarioEntityPK.class)
public class AttivitaorarioEntity implements Serializable {

	private long idOrario;
	private long idAttivita;
	@Id
	@Column(name = "IDORARIO")
	public long getIdOrario() {
		return idOrario;
	}

	public void setIdOrario(long idOrario) {
		this.idOrario = idOrario;
	}
	@Id
	@Column(name = "IDATTIVTA")
	public long getIdAttivita() {
		return idAttivita;
	}

	public void setIdAttivita(long idAttivita) {
		this.idAttivita = idAttivita;
	}

}
