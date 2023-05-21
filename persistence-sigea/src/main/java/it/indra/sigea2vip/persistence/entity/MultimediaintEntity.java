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
@Table(name = "MULTIMEDIAINT", schema = "TURISMO")
@IdClass(MultimediaintEntityPK.class)
public class MultimediaintEntity {
	private String idlingua;
	private String titolo;
	private String descrizione;
	private long idmultimedia;
	private String nomefile;
	private String dimensionerisorsa;
//	private MultimediaEntity multimediaByIdmultimedia;

	@Id
	@Column(name = "IDLINGUA")
	public String getIdlingua() {
		return idlingua;
	}

	public void setIdlingua(String idlingua) {
		this.idlingua = idlingua;
	}

	@Basic
	@Column(name = "TITOLO")
	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	@Basic
	@Column(name = "DESCRIZIONE")
	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Id
	@Column(name = "IDMULTIMEDIA")
	public long getIdmultimedia() {
		return idmultimedia;
	}

	public void setIdmultimedia(long idmultimedia) {
		this.idmultimedia = idmultimedia;
	}

	@Basic
	@Column(name = "NOMEFILE")
	public String getNomefile() {
		return nomefile;
	}

	public void setNomefile(String nomefile) {
		this.nomefile = nomefile;
	}

	@Basic
	@Column(name = "DIMENSIONERISORSA")
	public String getDimensionerisorsa() {
		return dimensionerisorsa;
	}

	public void setDimensionerisorsa(String dimensionerisorsa) {
		this.dimensionerisorsa = dimensionerisorsa;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		MultimediaintEntity that = (MultimediaintEntity) o;
		return idmultimedia == that.idmultimedia &&
						Objects.equals(idlingua, that.idlingua) &&
						Objects.equals(titolo, that.titolo) &&
						Objects.equals(descrizione, that.descrizione) &&
						Objects.equals(nomefile, that.nomefile) &&
						Objects.equals(dimensionerisorsa, that.dimensionerisorsa);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idlingua, titolo, descrizione, idmultimedia, nomefile, dimensionerisorsa);
	}

	/*@ManyToOne
	@JoinColumn(name = "IDMULTIMEDIA", referencedColumnName = "IDMULTIMEDIA", nullable = false)
	public MultimediaEntity getMultimediaByIdmultimedia() {
		return multimediaByIdmultimedia;
	}
*/
//	public void setMultimediaByIdmultimedia(MultimediaEntity multimediaByIdmultimedia) {
//		this.multimediaByIdmultimedia = multimediaByIdmultimedia;
//	}
}
