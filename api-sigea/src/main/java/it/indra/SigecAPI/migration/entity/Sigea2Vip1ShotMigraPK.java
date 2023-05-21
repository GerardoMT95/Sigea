package it.indra.SigecAPI.migration.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class Sigea2Vip1ShotMigraPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idSigea;
	private Long idSigea2;
	
	@Id
	@Column(name = "ID_SIGEA")
	public Long getIdSigea() {
		return idSigea;
	}
	public void setIdSigea(Long idSigea) {
		this.idSigea = idSigea;
	}
	
	@Id
	@Column(name = "ID_SIGEA_2")
	public Long getIdSigea2() {
		return idSigea2;
	}
	public void setIdSigea2(Long idSigea2) {
		this.idSigea2 = idSigea2;
	}
	
	
}
