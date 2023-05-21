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
@Table(name = "TICKET", schema = "TURISMO")
public class TicketEntity {
	private long idticket;
	private Double prezzointero;
	private Long numerominimoriduzione;
	private Double prezzogruppo;
	private Double prezzoridotto;
	private String linkprenotazione;
	private String convenzioniattive;

	@Id
	@Column(name = "IDTICKET")
	public long getIdticket() {
		return idticket;
	}

	public void setIdticket(long idticket) {
		this.idticket = idticket;
	}

	@Basic
	@Column(name = "PREZZOINTERO")
	public Double getPrezzointero() {
		return prezzointero;
	}

	public void setPrezzointero(Double prezzointero) {
		this.prezzointero = prezzointero;
	}

	@Basic
	@Column(name = "NUMEROMINIMORIDUZIONE")
	public Long getNumerominimoriduzione() {
		return numerominimoriduzione;
	}

	public void setNumerominimoriduzione(Long numerominimoriduzione) {
		this.numerominimoriduzione = numerominimoriduzione;
	}

	@Basic
	@Column(name = "PREZZOGRUPPO")
	public Double getPrezzogruppo() {
		return prezzogruppo;
	}

	public void setPrezzogruppo(Double prezzogruppo) {
		this.prezzogruppo = prezzogruppo;
	}

	@Basic
	@Column(name = "PREZZORIDOTTO")
	public Double getPrezzoridotto() {
		return prezzoridotto;
	}

	public void setPrezzoridotto(Double prezzoridotto) {
		this.prezzoridotto = prezzoridotto;
	}

	@Basic
	@Column(name = "LINKPRENOTAZIONE")
	public String getLinkprenotazione() {
		return linkprenotazione;
	}

	public void setLinkprenotazione(String linkprenotazione) {
		this.linkprenotazione = linkprenotazione;
	}

	@Basic
	@Column(name = "CONVENZIONIATTIVE")
	public String getConvenzioniattive() {
		return convenzioniattive;
	}

	public void setConvenzioniattive(String convenzioniattive) {
		this.convenzioniattive = convenzioniattive;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TicketEntity that = (TicketEntity) o;
		return idticket == that.idticket &&
						Objects.equals(prezzointero, that.prezzointero) &&
						Objects.equals(numerominimoriduzione, that.numerominimoriduzione) &&
						Objects.equals(prezzogruppo, that.prezzogruppo) &&
						Objects.equals(prezzoridotto, that.prezzoridotto) &&
						Objects.equals(linkprenotazione, that.linkprenotazione) &&
						Objects.equals(convenzioniattive, that.convenzioniattive);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idticket, prezzointero, numerominimoriduzione, prezzogruppo, prezzoridotto, linkprenotazione, convenzioniattive);
	}


}
