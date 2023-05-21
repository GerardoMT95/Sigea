package it.indra.sigea2vip.persistence.entity;

import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "ACCESSIBILITA", schema = "TURISMO")
public class AccessibilitaEntity {
	private long idmapping;
	private Boolean flpercorsoacc;
	private Boolean flsitowebacc;

	@Id
	@Column(name = "IDMAPPING")
	public long getIdmapping() {
		return idmapping;
	}

	public void setIdmapping(long idmapping) {
		this.idmapping = idmapping;
	}

	@Basic
	@Column(name = "FLPERCORSOACC")
	public Boolean getFlpercorsoacc() {
		return flpercorsoacc;
	}

	public void setFlpercorsoacc(Boolean flpercorsoacc) {
		this.flpercorsoacc = flpercorsoacc;
	}

	@Basic
	@Column(name = "FLSITOWEBACC")
	public Boolean getFlsitowebacc() {
		return flsitowebacc;
	}

	public void setFlsitowebacc(Boolean flsitowebacc) {
		this.flsitowebacc = flsitowebacc;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AccessibilitaEntity that = (AccessibilitaEntity) o;
		return idmapping == that.idmapping &&
						Objects.equals(flpercorsoacc, that.flpercorsoacc) &&
						Objects.equals(flsitowebacc, that.flsitowebacc);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idmapping, flpercorsoacc, flsitowebacc);
	}
}
