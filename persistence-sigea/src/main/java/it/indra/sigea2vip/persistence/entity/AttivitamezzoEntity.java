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
@Table(name = "ATTIVITAMEZZO", schema = "TURISMO")
@IdClass(AttivitamezzoEntityPK.class)
public class AttivitamezzoEntity {
	private long idattivita;
	private String idmezzo;

	@Id
	@Column(name = "IDATTIVITA")
	public long getIdattivita() {
		return idattivita;
	}

	public void setIdattivita(long idattivita) {
		this.idattivita = idattivita;
	}

	@Id
	@Column(name = "IDMEZZO")
	public String getIdmezzo() {
		return idmezzo;
	}

	public void setIdmezzo(String idmezzo) {
		this.idmezzo = idmezzo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AttivitamezzoEntity that = (AttivitamezzoEntity) o;
		return idattivita == that.idattivita &&
						Objects.equals(idmezzo, that.idmezzo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idattivita, idmezzo);
	}
}
