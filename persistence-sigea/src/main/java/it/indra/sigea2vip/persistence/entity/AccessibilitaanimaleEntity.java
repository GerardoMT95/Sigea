package it.indra.sigea2vip.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "ACCESSIBILITAANIMALE", schema = "TURISMO")
@IdClass(AccessibilitaanimaleEntityPK.class)
public class AccessibilitaanimaleEntity {
	private long idmapping;
	private String idtipoanimale;

	@Id
	@Column(name = "IDMAPPING")
	public long getIdmapping() {
		return idmapping;
	}

	public void setIdmapping(long idmapping) {
		this.idmapping = idmapping;
	}

	@Id
	@Column(name = "IDTIPOANIMALE")
	public String getIdtipoanimale() {
		return idtipoanimale;
	}

	public void setIdtipoanimale(String idtipoanimale) {
		this.idtipoanimale = idtipoanimale;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AccessibilitaanimaleEntity that = (AccessibilitaanimaleEntity) o;
		return idmapping == that.idmapping &&
						Objects.equals(idtipoanimale, that.idtipoanimale);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idmapping, idtipoanimale);
	}
}
