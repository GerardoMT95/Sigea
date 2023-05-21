package it.indra.sigea2vip.persistence.entity;

import java.sql.Timestamp;
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
@Table(name = "ATTIVITATURISTICA", schema = "TURISMO")
public class AttivitaturisticaEntity {
	private long id;
	private String latitudine;
	private String longitudine;
	private Timestamp dataaggiornamento;
	private Long ranking;
	private String idiat;
	private String idstato;
	private Long idgruppoautore;
	private String idworkflow;
	private String autorecreazione;
	private String autoremodifica;
	private String idTipologia;
	private Long idFoto1;
	private Long idFoto2;
	private Long idFoto3;
//	private AttivitaanagraficaEntity attivitaanagraficaById;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ATTIVITATURISTICA_SEQ")
	@SequenceGenerator(schema = "TURISMO", sequenceName = "ATTIVITATURISTICA_SEQ", allocationSize = 1, name = "ATTIVITATURISTICA_SEQ")
	@Column(name = "ID")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
	@Column(name = "DATAAGGIORNAMENTO")
	public Timestamp getDataaggiornamento() {
		return dataaggiornamento;
	}

	public void setDataaggiornamento(Timestamp dataaggiornamento) {
		this.dataaggiornamento = dataaggiornamento;
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
	@Column(name = "IDIAT")
	public String getIdiat() {
		return idiat;
	}

	public void setIdiat(String idiat) {
		this.idiat = idiat;
	}

	@Basic
	@Column(name = "IDSTATO")
	public String getIdstato() {
		return idstato;
	}

	public void setIdstato(String idstato) {
		this.idstato = idstato;
	}

	@Basic
	@Column(name = "IDGRUPPOAUTORE")
	public Long getIdgruppoautore() {
		return idgruppoautore;
	}

	public void setIdgruppoautore(Long idgruppoautore) {
		this.idgruppoautore = idgruppoautore;
	}

	@Basic
	@Column(name = "IDWORKFLOW")
	public String getIdworkflow() {
		return idworkflow;
	}

	public void setIdworkflow(String idworkflow) {
		this.idworkflow = idworkflow;
	}

	@Basic
	@Column(name = "AUTORECREAZIONE")
	public String getAutorecreazione() {
		return autorecreazione;
	}

	public void setAutorecreazione(String autorecreazione) {
		this.autorecreazione = autorecreazione;
	}

	@Basic
	@Column(name = "AUTOREMODIFICA")
	public String getAutoremodifica() {
		return autoremodifica;
	}

	public void setAutoremodifica(String autoremodifica) {
		this.autoremodifica = autoremodifica;
	}
	@Basic
	@Column(name = "IDTIPOLOGIA")
	public String getIdTipologia() {
		return idTipologia;
	}

	public void setIdTipologia(String idTipologia) {
		this.idTipologia = idTipologia;
	}
	@Basic
	@Column(name = "IDFOTO1")
	public Long getIdFoto1() {
		return idFoto1;
	}

	public void setIdFoto1(Long idFoto1) {
		this.idFoto1 = idFoto1;
	}
	@Basic
	@Column(name = "IDFOTO2")
	public Long getIdFoto2() {
		return idFoto2;
	}

	public void setIdFoto2(Long idFoto2) {
		this.idFoto2 = idFoto2;
	}
	@Basic
	@Column(name = "IDFOTO3")
	public Long getIdFoto3() {
		return idFoto3;
	}

	public void setIdFoto3(Long idFoto3) {
		this.idFoto3 = idFoto3;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AttivitaturisticaEntity that = (AttivitaturisticaEntity) o;
		return id == that.id &&
						Objects.equals(latitudine, that.latitudine) &&
						Objects.equals(longitudine, that.longitudine) &&
						Objects.equals(dataaggiornamento, that.dataaggiornamento) &&
						Objects.equals(ranking, that.ranking) &&
						Objects.equals(idiat, that.idiat) &&
						Objects.equals(idstato, that.idstato) &&
						Objects.equals(idgruppoautore, that.idgruppoautore) &&
						Objects.equals(idworkflow, that.idworkflow) &&
						Objects.equals(autorecreazione, that.autorecreazione) &&
						Objects.equals(autoremodifica, that.autoremodifica) &&
						Objects.equals(idTipologia, that.idTipologia)  &&					
						Objects.equals(idFoto1, that.idFoto1) &&
						Objects.equals(idFoto2, that.idFoto2) &&
						Objects.equals(idFoto3, that.idFoto3);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, latitudine, longitudine, dataaggiornamento, ranking, idiat, idstato, idgruppoautore, idworkflow, autorecreazione, autoremodifica, idTipologia, idFoto1, idFoto2 ,idFoto3);
	}



//	public void setAttivitaanagraficaById(AttivitaanagraficaEntity attivitaanagraficaById) {
//		this.attivitaanagraficaById = attivitaanagraficaById;
//	}
}
