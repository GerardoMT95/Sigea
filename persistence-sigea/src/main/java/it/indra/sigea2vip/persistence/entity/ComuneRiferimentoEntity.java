package it.indra.sigea2vip.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "COMUNERIFERIMENTO", schema = "TURISMO")
public class ComuneRiferimentoEntity {

	private long idComuneRiferimento;
	private String codiceIstat;

	@Column(name = "IDCOMUNERIFERIMENTO")
	@Id
	public long getIdComuneRiferimento() {
		return idComuneRiferimento;
	}

	public void setIdComuneRiferimento(long idComuneRiferimento) {
		this.idComuneRiferimento = idComuneRiferimento;
	}

	@Column(name = "CODICEISTAT")
	public String getCodiceIstat() {
		return codiceIstat;
	}

	public void setCodiceIstat(String codiceIstat) {
		this.codiceIstat = codiceIstat;
	}

}
