package it.indra.sigea2vip.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class AttivitamicroesperienzaEntityPK implements Serializable {
	
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
