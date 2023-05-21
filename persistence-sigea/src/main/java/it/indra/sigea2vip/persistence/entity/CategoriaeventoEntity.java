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
@Table(name = "CATEGORIAEVENTO", schema = "TURISMO")
public class CategoriaeventoEntity {
	private long idcategoria;
	private Long idparent;
	private String descrizione;
	private Long pubblica;
	private Long ranking;
	private String idpercorso;
	private Long sottocategoriebase;
	
	@Id
	@Column(name = "IDCATEGORIA")
	public Long getIdcategoria() {
		return idcategoria;
	}
	public void setIdcategoria(Long idcategoria) {
		this.idcategoria = idcategoria;
	}
	
	@Basic
	@Column(name = "IDPARENT")
	public Long getIdparent() {
		return idparent;
	}
	public void setIdparent(Long idparent) {
		this.idparent = idparent;
	}
	
	@Basic
	@Column(name = "DESCRIZIONE")
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	@Basic
	@Column(name = "PUBBLICA")
	public Long getPubblica() {
		return pubblica;
	}
	public void setPubblica(Long pubblica) {
		this.pubblica = pubblica;
	}
	
	@Basic
	@Column(name = "RANKING")
	public Long getRanking() {
		return ranking;
	}
	public void setRanking(Long ranking) {
		this.ranking = ranking;
	}
	
	@Basic
	@Column(name = "IDPERCORSO")
	public String getIdpercorso() {
		return idpercorso;
	}
	public void setIdpercorso(String idpercorso) {
		this.idpercorso = idpercorso;
	}
	
	@Basic
	@Column(name = "SOTTOCATEGORIEBASE")
	public Long getSottocategoriebase() {
		return sottocategoriebase;
	}
	public void setSottocategoriebase(Long sottocategoriebase) {
		this.sottocategoriebase = sottocategoriebase;
	}

	

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CategoriaeventoEntity that = (CategoriaeventoEntity) o;
		return idparent == that.idparent &&
						idcategoria == that.idcategoria;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idparent, idcategoria);
	}


}
