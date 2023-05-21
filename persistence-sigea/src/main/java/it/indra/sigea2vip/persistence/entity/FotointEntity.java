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
@Table(name = "FOTOINT", schema = "TURISMO", catalog = "")
@IdClass(FotointEntityPK.class)
public class FotointEntity {
	private long idfoto;
	private String idlingua;
	private String descrizione;

	@Id
	@Column(name = "IDFOTO")
	public long getIdfoto() {
		return idfoto;
	}

	public void setIdfoto(long idfoto) {
		this.idfoto = idfoto;
	}

	@Id
	@Column(name = "IDLINGUA")
	public String getIdlingua() {
		return idlingua;
	}

	public void setIdlingua(String idlingua) {
		this.idlingua = idlingua;
	}

	@Basic
	@Column(name = "DESCRIZIONE")
	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		FotointEntity that = (FotointEntity) o;
		return idfoto == that.idfoto &&
						Objects.equals(idlingua, that.idlingua) &&
						Objects.equals(descrizione, that.descrizione);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idfoto, idlingua, descrizione);
	}
}
