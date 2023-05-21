package it.indra.sigea2vip.persistence.entity;

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

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "GRUPPO", schema = "TURISMO")
public class GruppoEntity {

	private long idGruppo;
	private long idGruppoPadre;
	private String descrizione;
	private String idTipologiaGruppo;
	private String iopAccessKeyId;
	private String iopSignatureKey;
	

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GRUPPO_IDGRUPPO_SEQ")
	@SequenceGenerator(schema = "TURISMO", sequenceName = "GRUPPO_IDGRUPPO_SEQ", allocationSize = 1, name = "GRUPPO_IDGRUPPO_SEQ")
	@Column(name = "IDGRUPPO")
	public long getIdGruppo() {
		return idGruppo;
	}

	public void setIdGruppo(long idGruppo) {
		this.idGruppo = idGruppo;
	}
	
	
	@Column(name = "IDGRUPPOPADRE")
	public long getIdGruppoPadre() {
		return idGruppoPadre;
	}

	public void setIdGruppoPadre(long idGruppoPadre) {
		this.idGruppoPadre = idGruppoPadre;
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
	@Column(name = "IDTIPOLOGIAGRUPPO")
	public String getIdTipologiaGruppo() {
		return idTipologiaGruppo;
	}

	public void setIdTipologiaGruppo(String idTipologiaGruppo) {
		this.idTipologiaGruppo = idTipologiaGruppo;
	}

	@Basic
	@Column(name = "IOPACCESSKEYID")
	public String getIopAccessKeyId() {
		return iopAccessKeyId;
	}

	public void setIopAccessKeyId(String iopAccessKeyId) {
		this.iopAccessKeyId = iopAccessKeyId;
	}

	@Basic
	@Column(name = "IOPSIGNATUREKEY")
	public String getIopSignatureKey() {
		return iopSignatureKey;
	}

	public void setIopSignatureKey(String iopSignatureKey) {
		this.iopSignatureKey = iopSignatureKey;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		GruppoEntity that = (GruppoEntity) o;
		return idGruppo == that.idGruppo &&
						idGruppoPadre == that.idGruppoPadre &&
						Objects.equals(descrizione, that.descrizione) &&
						Objects.equals(idTipologiaGruppo, that.idTipologiaGruppo) &&
						Objects.equals(iopAccessKeyId, that.iopAccessKeyId) &&
						Objects.equals(iopSignatureKey, that.iopSignatureKey);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idGruppo, idGruppoPadre, descrizione, idTipologiaGruppo, iopAccessKeyId, iopSignatureKey);
	}

	
}
