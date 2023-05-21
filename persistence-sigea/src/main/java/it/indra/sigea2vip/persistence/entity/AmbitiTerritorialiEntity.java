package it.indra.sigea2vip.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "AMBITITERRITORIALI", schema = "TURISMO")
public class AmbitiTerritorialiEntity {

	private long idComuneSirtur;
	private String idTerritorio;
	private String descTerritorio;

	@Column(name = "IDCOMUNESIRTUR")
	@Id
	public long getIdComuneSirtur() {
		return idComuneSirtur;
	}

	public void setIdComuneSirtur(long idComuneSirtur) {
		this.idComuneSirtur = idComuneSirtur;
	}

	@Column(name = "IDTERRITORIO")
	public String getIdTerritorio() {
		return idTerritorio;
	}

	public void setIdTerritorio(String idTerritorio) {
		this.idTerritorio = idTerritorio;
	}

	@Column(name = "DESCTERRITORIO")
	public String getDescTerritorio() {
		return descTerritorio;
	}

	public void setDescTerritorio(String descTerritorio) {
		this.descTerritorio = descTerritorio;
	}
	
	

}
