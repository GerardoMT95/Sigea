package it.indra.sigea2vip.persistence.entity;

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
@ToString
@Entity
@Table(name = "RASSEGNAEVENTIMAPPING", schema = "TURISMO")
@IdClass(RassegnaeventiMappingEntityPK.class)
public class RassegnaEventiMappingEntity {
	
	private Long idRassegna;
	private Long idEvento;
	
	@Column(name = "IDRASSEGNA")
	@Id
	public Long getIdRassegna() {
		return idRassegna;
	}
	public void setIdRassegna(Long idRassegna) {
		this.idRassegna = idRassegna;
	}
	@Column(name = "IDEVENTO")
	@Id
	public Long getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(Long idEvento) {
		this.idEvento = idEvento;
	}

}
