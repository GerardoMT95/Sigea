package it.indra.sigea2vip.persistence.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "EVENTOLOCATION", schema = "TURISMO")
public class EventolocationEntity {
	private long idevento;
	private String nome;
	private Long idcomune;
	private String idprovincia;
	private String indirizzo;
	private String cap;
	private String latitudine;
	private String longitudine;
	private Date datamodifica;

	@Id
	@Column(name = "IDEVENTO")
	public long getIdevento() {
		return idevento;
	}

	public void setIdevento(long idevento) {
		this.idevento = idevento;
	}

	@Basic
	@Column(name = "NOME")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Basic
	@Column(name = "IDCOMUNE")
	public Long getIdcomune() {
		return idcomune;
	}

	public void setIdcomune(Long idcomune) {
		this.idcomune = idcomune;
	}

	@Basic
	@Column(name = "IDPROVINCIA")
	public String getIdprovincia() {
		return idprovincia;
	}

	public void setIdprovincia(String idprovincia) {
		this.idprovincia = idprovincia;
	}

	@Basic
	@Column(name = "INDIRIZZO")
	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	@Basic
	@Column(name = "CAP")
	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	@Basic
	@Column(name = "LATITUDINE")
	public String getLatitudine() {
		return latitudine;
	}

	public void setLatitudine(String latitudine) {
		this.latitudine = latitudine;
	}

	@Basic
	@Column(name = "LONGITUDINE")
	public String getLongitudine() {
		return longitudine;
	}

	public void setLongitudine(String longitudine) {
		this.longitudine = longitudine;
	}

	@Basic
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATAMODIFICA")
	public Date getDatamodifica() {
		return datamodifica;
	}

	public void setDatamodifica(Date datamodifica) {
		this.datamodifica = datamodifica;
	}

	@PreUpdate
	void dataModifica() {
		this.datamodifica = new Date(System.currentTimeMillis());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		EventolocationEntity that = (EventolocationEntity) o;
		return idevento == that.idevento &&
						Objects.equals(nome, that.nome) &&
						Objects.equals(idcomune, that.idcomune) &&
						Objects.equals(idprovincia, that.idprovincia) &&
						Objects.equals(indirizzo, that.indirizzo) &&
						Objects.equals(cap, that.cap) &&
						Objects.equals(latitudine, that.latitudine) &&
						Objects.equals(longitudine, that.longitudine) &&
						Objects.equals(datamodifica, that.datamodifica);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idevento, nome, idcomune, idprovincia, indirizzo, cap, latitudine, longitudine, datamodifica);
	}
}
