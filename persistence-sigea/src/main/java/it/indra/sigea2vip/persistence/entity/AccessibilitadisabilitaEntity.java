package it.indra.sigea2vip.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "ACCESSIBILITADISABILITA", schema = "TURISMO")
@IdClass(AccessibilitadisabilitaEntityPK.class)
public class AccessibilitadisabilitaEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long idmapping;
	private String iddisabilita;

	@Id
	@Column(name = "IDMAPPING")
	public long getIdmapping() {
		return idmapping;
	}

	public void setIdmapping(long idmapping) {
		this.idmapping = idmapping;
	}

	@Id
	@Column(name = "IDDISABILITA")
	public String getIddisabilita() {
		return iddisabilita;
	}

	public void setIddisabilita(String iddisabilita) {
		this.iddisabilita = iddisabilita;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AccessibilitadisabilitaEntity that = (AccessibilitadisabilitaEntity) o;
		return idmapping == that.idmapping &&
						Objects.equals(iddisabilita, that.iddisabilita);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idmapping, iddisabilita);
	}
}
