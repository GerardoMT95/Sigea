package it.indra.sigea2vip.persistence.entity;

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
@Table(name = "ATTIVITAMICROESPERIENZA", schema = "TURISMO")
@IdClass(AttivitamicroesperienzaEntityPK.class)
public class AttivitamicroesperienzaEntity {

	private long idAttivita;
	private String idMicroesperienza;
	
	@Column(name="IDATTIVITA")
	@Id
	public long getIdAttivita() {
		return idAttivita;
	}
	public void setIdAttivita(long idAttivita) {
		this.idAttivita = idAttivita;
	}
	@Column(name="IDMICROESPERIENZA")
	@Id
	public String getIdMicroesperienza() {
		return idMicroesperienza;
	}
	public void setIdMicroesperienza(String idMicroesperienza) {
		this.idMicroesperienza = idMicroesperienza;
	}
	
	
}
