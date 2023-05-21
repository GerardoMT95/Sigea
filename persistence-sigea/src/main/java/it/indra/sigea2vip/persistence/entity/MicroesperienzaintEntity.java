package it.indra.sigea2vip.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@IdClass(MicroesperienzaintEntityPK.class)
public class MicroesperienzaintEntity {
	private String idlingua;
	private String idmicroesperienza;
	private String denominazione;

	@Id
	@Column(name = "IDLINGUA")
	public String getIdlingua() {
		return idlingua;
	}

	public void setIdlingua(String idlingua) {
		this.idlingua = idlingua;
	}

	@Id
	@Column(name = "IDMICROESPERIENZA")
	public String getIdmicroesperienza() {
		return idmicroesperienza;
	}

	public void setIdmicroesperienza(String idmicroesperienza) {
		this.idmicroesperienza = idmicroesperienza;
	}

	@Basic
	@Column(name = "DENOMINAZIONE")
	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		MicroesperienzaintEntity that = (MicroesperienzaintEntity) o;
		return Objects.equals(idlingua, that.idlingua) &&
						Objects.equals(idmicroesperienza, that.idmicroesperienza) &&
						Objects.equals(denominazione, that.denominazione);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idlingua, idmicroesperienza, denominazione);
	}
}
