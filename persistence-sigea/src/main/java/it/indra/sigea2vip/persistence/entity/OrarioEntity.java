package it.indra.sigea2vip.persistence.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

import javax.persistence.Basic;
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
@Entity
@ToString
@Table(name = "ORARIO", schema = "TURISMO")
public class OrarioEntity {
	private long idorario;
	private String continuato;
	private String estivo;
	private String continuatodahh;
	private String continuatoahh;
	private String mattinadahh;
	private String mattinaahh;
	private String pomeriggiodahh;
	private String pomeriggioahh;
	private String sempreaperto;
	private String continuatodamm;
	private String continuatoamm;
	private String mattinadamm;
	private String mattinaamm;
	private String pomeriggiodamm;
	private String pomeriggioamm;
	private String straordinario;
	private Date datainizio;
	private Date datafine;
	private String prenotazione;
	private String tipo;
	private Long idgruppo;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORARIO_IDORARIO_SEQ")
	@SequenceGenerator(schema = "TURISMO", sequenceName = "ORARIO_IDORARIO_SEQ", allocationSize = 1, name = "ORARIO_IDORARIO_SEQ")
	@Column(name = "IDORARIO")
	public long getIdorario() {
		return idorario;
	}

	public void setIdorario(long idorario) {
		this.idorario = idorario;
	}

	@Basic
	@Column(name = "CONTINUATO")
	public String getContinuato() {
		return continuato;
	}

	public void setContinuato(String continuato) {
		this.continuato = continuato;
	}

	@Basic
	@Column(name = "ESTIVO")
	public String getEstivo() {
		return estivo;
	}

	public void setEstivo(String estivo) {
		this.estivo = estivo;
	}

	@Basic
	@Column(name = "CONTINUATODAHH")
	public String getContinuatodahh() {
		return continuatodahh;
	}

	public void setContinuatodahh(String continuatodahh) {
		this.continuatodahh = continuatodahh;
	}

	@Basic
	@Column(name = "CONTINUATOAHH")
	public String getContinuatoahh() {
		return continuatoahh;
	}

	public void setContinuatoahh(String continuatoahh) {
		this.continuatoahh = continuatoahh;
	}

	@Basic
	@Column(name = "MATTINADAHH")
	public String getMattinadahh() {
		return mattinadahh;
	}

	public void setMattinadahh(String mattinadahh) {
		this.mattinadahh = mattinadahh;
	}

	@Basic
	@Column(name = "MATTINAAHH")
	public String getMattinaahh() {
		return mattinaahh;
	}

	public void setMattinaahh(String mattinaahh) {
		this.mattinaahh = mattinaahh;
	}

	@Basic
	@Column(name = "POMERIGGIODAHH")
	public String getPomeriggiodahh() {
		return pomeriggiodahh;
	}

	public void setPomeriggiodahh(String pomeriggiodahh) {
		this.pomeriggiodahh = pomeriggiodahh;
	}

	@Basic
	@Column(name = "POMERIGGIOAHH")
	public String getPomeriggioahh() {
		return pomeriggioahh;
	}

	public void setPomeriggioahh(String pomeriggioahh) {
		this.pomeriggioahh = pomeriggioahh;
	}

	@Basic
	@Column(name = "SEMPREAPERTO")
	public String getSempreaperto() {
		return sempreaperto;
	}

	public void setSempreaperto(String sempreaperto) {
		this.sempreaperto = sempreaperto;
	}

	@Basic
	@Column(name = "CONTINUATODAMM")
	public String getContinuatodamm() {
		return continuatodamm;
	}

	public void setContinuatodamm(String continuatodamm) {
		this.continuatodamm = continuatodamm;
	}

	@Basic
	@Column(name = "CONTINUATOAMM")
	public String getContinuatoamm() {
		return continuatoamm;
	}

	public void setContinuatoamm(String continuatoamm) {
		this.continuatoamm = continuatoamm;
	}

	@Basic
	@Column(name = "MATTINADAMM")
	public String getMattinadamm() {
		return mattinadamm;
	}

	public void setMattinadamm(String mattinadamm) {
		this.mattinadamm = mattinadamm;
	}

	@Basic
	@Column(name = "MATTINAAMM")
	public String getMattinaamm() {
		return mattinaamm;
	}

	public void setMattinaamm(String mattinaamm) {
		this.mattinaamm = mattinaamm;
	}

	@Basic
	@Column(name = "POMERIGGIODAMM")
	public String getPomeriggiodamm() {
		return pomeriggiodamm;
	}

	public void setPomeriggiodamm(String pomeriggiodamm) {
		this.pomeriggiodamm = pomeriggiodamm;
	}

	@Basic
	@Column(name = "POMERIGGIOAMM")
	public String getPomeriggioamm() {
		return pomeriggioamm;
	}

	public void setPomeriggioamm(String pomeriggioamm) {
		this.pomeriggioamm = pomeriggioamm;
	}

	@Basic
	@Column(name = "STRAORDINARIO")
	public String getStraordinario() {
		return straordinario;
	}

	public void setStraordinario(String straordinario) {
		this.straordinario = straordinario;
	}

	@Basic
	@Column(name = "DATAINIZIO")
	public Date getDatainizio() {
		return datainizio;
	}



	public void setDatainizio(Date datainizio) {
		this.datainizio = datainizio;
	}

	@Basic
	@Column(name = "DATAFINE")
	public Date getDatafine() {
		return datafine;
	}

	public void setDatafine(Date datafine) {
		this.datafine = datafine;
	}

	@Basic
	@Column(name = "PRENOTAZIONE")
	public String getPrenotazione() {
		return prenotazione;
	}

	public void setPrenotazione(String prenotazione) {
		this.prenotazione = prenotazione;
	}

	@Basic
	@Column(name = "TIPO")
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Basic
	@Column(name = "IDGRUPPO")
	public Long getIdgruppo() {
		return idgruppo;
	}

	public void setIdgruppo(Long idgruppo) {
		this.idgruppo = idgruppo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		OrarioEntity that = (OrarioEntity) o;
		return idorario == that.idorario &&
						Objects.equals(continuato, that.continuato) &&
						Objects.equals(estivo, that.estivo) &&
						Objects.equals(continuatodahh, that.continuatodahh) &&
						Objects.equals(continuatoahh, that.continuatoahh) &&
						Objects.equals(mattinadahh, that.mattinadahh) &&
						Objects.equals(mattinaahh, that.mattinaahh) &&
						Objects.equals(pomeriggiodahh, that.pomeriggiodahh) &&
						Objects.equals(pomeriggioahh, that.pomeriggioahh) &&
						Objects.equals(sempreaperto, that.sempreaperto) &&
						Objects.equals(continuatodamm, that.continuatodamm) &&
						Objects.equals(continuatoamm, that.continuatoamm) &&
						Objects.equals(mattinadamm, that.mattinadamm) &&
						Objects.equals(mattinaamm, that.mattinaamm) &&
						Objects.equals(pomeriggiodamm, that.pomeriggiodamm) &&
						Objects.equals(pomeriggioamm, that.pomeriggioamm) &&
						Objects.equals(straordinario, that.straordinario) &&
						Objects.equals(datainizio, that.datainizio) &&
						Objects.equals(datafine, that.datafine) &&
						Objects.equals(prenotazione, that.prenotazione) &&
						Objects.equals(tipo, that.tipo) &&
						Objects.equals(idgruppo, that.idgruppo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idorario, continuato, estivo, continuatodahh, continuatoahh, mattinadahh, mattinaahh, pomeriggiodahh, pomeriggioahh, sempreaperto, continuatodamm, continuatoamm, mattinadamm, mattinaamm, pomeriggiodamm, pomeriggioamm, straordinario, datainizio, datafine, prenotazione, tipo, idgruppo);
	}
}
