package it.indra.sigea2vip.persistence.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
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
@Table(name = "ATTIVITAMAPPING", schema = "TURISMO")
public class AttivitamappingEntity {
	private long id;
	private long idAttivita;
	private String idProdotto;
	private Long idAttrattore;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ATTIVITAMAPP_SEQ")
	@SequenceGenerator(schema = "TURISMO", sequenceName = "ATTIVITAMAPP_SEQ", allocationSize = 1, name = "ATTIVITAMAPP_SEQ")
	@Column(name = "ID")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "IDATTIVITA")
	public long getIdAttivita() {
		return idAttivita;
	}

	public void setIdAttivita(long idAttivita) {
		this.idAttivita = idAttivita;
	}

	@Column(name = "IDPRODOTTO")
	public String getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(String idProdotto) {
		this.idProdotto = idProdotto;
	}
	@Column(name = "IDATTRATTORE")
	public Long getIdAttrattore() {
		return idAttrattore;
	}

	public void setIdAttrattore(Long idAttrattore) {
		this.idAttrattore = idAttrattore;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AttivitamappingEntity that = (AttivitamappingEntity) o;
		return id == that.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

}
