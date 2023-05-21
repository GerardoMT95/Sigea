package it.indra.sigea2vip.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class RassegnaeventiMappingEntityPK implements Serializable {

	private Long idRassegna;
	private Long idEvento;
	
	@Column(name = "IDRASSEGNA")
	@Id
	public Long getIdRassegna() {
		return idRassegna;
	}
	public void setIdRassegna(Long idRassegna) {
		this.idRassegna = idRassegna;
	}
	@Column(name = "IDEVENTO")
	@Id
	public Long getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(Long idEvento) {
		this.idEvento = idEvento;
	}
	
	
}
