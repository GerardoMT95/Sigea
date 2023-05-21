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
@Table(name = "ATTIVITATURISTICAINT", schema = "TURISMO")
@IdClass(AttivitaturisticaintEntityPK.class)
public class AttivitaturisticaintEntity {
	private long idattivita;
	private String idlingua;
	private String abtract;
	private String denominazione;
	private String descrizione;
	private String snippet;

	@Id
	@Column(name = "IDATTIVITA")
	public long getIdattivita() {
		return idattivita;
	}

	public void setIdattivita(long idattivita) {
		this.idattivita = idattivita;
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
	@Column(name = "DENOMINAZIONE")
	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	@Basic
	@Column(name = "ABSTRACT")
	public String getAbtract() {
		return abtract;
	}
	public void setAbtract(String abtract) {
		this.abtract = abtract;
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
	@Column(name = "SNIPPET")
	public String getSnippet() {
		return snippet;
	}

	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AttivitaturisticaintEntity that = (AttivitaturisticaintEntity) o;
		return idattivita == that.idattivita &&
						Objects.equals(idlingua, that.idlingua) &&
						Objects.equals(denominazione, that.denominazione) &&
						Objects.equals(descrizione, that.descrizione) &&
						Objects.equals(snippet, that.snippet);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idattivita, idlingua, denominazione, descrizione, snippet);
	}
}
