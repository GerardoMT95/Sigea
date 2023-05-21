package it.indra.sigea2vip.persistence.entity;

import java.util.Objects;

import javax.persistence.Basic;
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
@Entity
@ToString
@Table(name = "ACCESSIBILITASERVIZIO", schema = "TURISMO")
@IdClass(AccessibilitaservizioEntityPK.class)
public class AccessibilitaservizioEntity {
	private long idmapping;
	private long idservizioacc;
	private String tiposervizio;
	//private AccessibilitamappingEntity accessibilitamappingByIdmapping;

	@Id
	@Column(name = "IDMAPPING")
	public long getIdmapping() {
		return idmapping;
	}

	public void setIdmapping(long idmapping) {
		this.idmapping = idmapping;
	}

	@Id
	@Column(name = "IDSERVIZIOACC")
	public long getIdservizioacc() {
		return idservizioacc;
	}

	public void setIdservizioacc(long idservizioacc) {
		this.idservizioacc = idservizioacc;
	}

	@Basic
	@Column(name = "TIPOSERVIZIO")
	public String getTiposervizio() {
		return tiposervizio;
	}

	public void setTiposervizio(String tiposervizio) {
		this.tiposervizio = tiposervizio;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AccessibilitaservizioEntity that = (AccessibilitaservizioEntity) o;
		return idmapping == that.idmapping &&
						idservizioacc == that.idservizioacc &&
						Objects.equals(tiposervizio, that.tiposervizio);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idmapping, idservizioacc, tiposervizio);
	}

//	@ManyToOne
//	@JoinColumn(name = "PK_IDMAPPING", referencedColumnName = "ID", nullable = false)
//	public AccessibilitamappingEntity getAccessibilitamappingByIdmapping() {
//		return accessibilitamappingByIdmapping;
//	}
//
//	public void setAccessibilitamappingByIdmapping(AccessibilitamappingEntity accessibilitamappingByIdmapping) {
//		this.accessibilitamappingByIdmapping = accessibilitamappingByIdmapping;
//	}
}
