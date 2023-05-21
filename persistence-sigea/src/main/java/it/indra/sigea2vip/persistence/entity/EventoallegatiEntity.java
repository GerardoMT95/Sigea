package it.indra.sigea2vip.persistence.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
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
@Entity
@ToString
@Table(name = "EVENTOALLEGATI", schema = "TURISMO")
public class EventoallegatiEntity {
	private long idallegato;
	private long idref;
	private String suffisso;
	private String nomefile;
	private String nomeoriginale;
	private String estensione;
	private String tipologia;
	private long peso;
	private long idutente;
	private Date datainserimento;
	private Date datamodifica;
	private String categoria;
	private Long ranking;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVENTOALLEGATI_SEQ")
	@SequenceGenerator(schema = "TURISMO", sequenceName = "EVENTOALLEGATI_SEQ", allocationSize = 1, name = "EVENTOALLEGATI_SEQ")
	@Column(name = "IDALLEGATO")
	public long getIdallegato() {
		return idallegato;
	}

	public void setIdallegato(long idallegato) {
		this.idallegato = idallegato;
	}

	@Basic
	@Column(name = "IDREF")
	public long getIdref() {
		return idref;
	}

	public void setIdref(long idref) {
		this.idref = idref;
	}

	@Basic
	@Column(name = "SUFFISSO")
	public String getSuffisso() {
		return suffisso;
	}

	public void setSuffisso(String suffisso) {
		this.suffisso = suffisso;
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
	@Column(name = "NOMEORIGINALE")
	public String getNomeoriginale() {
		return nomeoriginale;
	}

	public void setNomeoriginale(String nomeoriginale) {
		this.nomeoriginale = nomeoriginale;
	}

	@Basic
	@Column(name = "ESTENSIONE")
	public String getEstensione() {
		return estensione;
	}

	public void setEstensione(String estensione) {
		this.estensione = estensione;
	}

	@Basic
	@Column(name = "TIPOLOGIA")
	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	@Basic
	@Column(name = "PESO")
	public long getPeso() {
		return peso;
	}

	public void setPeso(long peso) {
		this.peso = peso;
	}

	@Basic
	@Column(name = "IDUTENTE")
	public long getIdutente() {
		return idutente;
	}

	public void setIdutente(long idutente) {
		this.idutente = idutente;
	}

	@Basic
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATAINSERIMENTO", updatable = false)
	public Date getDatainserimento() {
		return datainserimento;
	}

	public void setDatainserimento(Date datainserimento) {
		this.datainserimento = datainserimento;
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

	@Basic
	@Column(name = "CATEGORIA")
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Basic
	@Column(name = "RANKING")
	public Long getRanking() {
		return ranking;
	}

	public void setRanking(Long ranking) {
		this.ranking = ranking;
	}
	
	@PreUpdate
	void dataModifica() {
		this.datamodifica = new Date(System.currentTimeMillis());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		EventoallegatiEntity that = (EventoallegatiEntity) o;
		return idallegato == that.idallegato &&
						idref == that.idref &&
						peso == that.peso &&
						idutente == that.idutente &&
						Objects.equals(suffisso, that.suffisso) &&
						Objects.equals(nomefile, that.nomefile) &&
						Objects.equals(nomeoriginale, that.nomeoriginale) &&
						Objects.equals(estensione, that.estensione) &&
						Objects.equals(tipologia, that.tipologia) &&
						Objects.equals(datainserimento, that.datainserimento) &&
						Objects.equals(datamodifica, that.datamodifica) &&
						Objects.equals(categoria, that.categoria) &&
						Objects.equals(ranking, that.ranking);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idallegato, idref, suffisso, nomefile, nomeoriginale, estensione, tipologia, peso, idutente, datainserimento, datamodifica, categoria, ranking);
	}
}
