package it.indra.sigea2vip.persistence.entity;

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
@Table(name = "SCONTO", schema = "TURISMO")
public class ScontoEntity {

	private long idsconto;
	private String idTipologia;
	private String idCausaleSconto;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SCONTO_SEQ")
	@SequenceGenerator(schema = "TURISMO", sequenceName = "SCONTO_SEQ", allocationSize = 1, name = "SCONTO_SEQ")
	@Column(name = "IDSCONTO")
	public long getIdsconto() {
		return idsconto;
	}

	public void setIdsconto(long idsconto) {
		this.idsconto = idsconto;
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
	@Column(name = "IDCAUSALESCONTO")
	public String getIdCausaleSconto() {
		return idCausaleSconto;
	}

	public void setIdCausaleSconto(String idCausaleSconto) {
		this.idCausaleSconto = idCausaleSconto;
	}

}
