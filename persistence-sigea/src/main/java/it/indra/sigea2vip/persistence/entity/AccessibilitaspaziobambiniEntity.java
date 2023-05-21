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
@Table(name = "ACCESSIBILITASPAZIOBAMBINI", schema = "TURISMO")
@IdClass(AccessibilitaspaziobambiniEntityPK.class)
public class AccessibilitaspaziobambiniEntity {
	private long idmapping;
	private String idspazio;

	@Id
	@Column(name = "IDMAPPING")
	public long getIdmapping() {
		return idmapping;
	}

	public void setIdmapping(long idmapping) {
		this.idmapping = idmapping;
	}

	@Id
	@Column(name = "IDSPAZIO")
	public String getIdspazio() {
		return idspazio;
	}

	public void setIdspazio(String idspazio) {
		this.idspazio = idspazio;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AccessibilitaspaziobambiniEntity that = (AccessibilitaspaziobambiniEntity) o;
		return idmapping == that.idmapping &&
						Objects.equals(idspazio, that.idspazio);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idmapping, idspazio);
	}
}
