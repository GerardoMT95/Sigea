package it.indra.sigea2vip.persistence.entity;

import java.util.Objects;

import javax.persistence.Basic;
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
@Entity
@ToString
@Table(name = "EVENTOALLEGATIINT", schema = "TURISMO")
@IdClass(EventoallegatiintEntityPK.class)
public class EventoallegatiintEntity {
	private long idallegato;
	private String idlingua;
	private String descrizione;
	private EventoallegatiEntity eventoallegatiByIdallegato;

	@Id
	@Column(name = "IDALLEGATO")
	public long getIdallegato() {
		return idallegato;
	}

	public void setIdallegato(long idallegato) {
		this.idallegato = idallegato;
	}

	@Id
	@Column(name = "IDLINGUA")
	public String getIdlingua() {
		return idlingua;
	}

	public void setIdlingua(String idlingua) {
		this.idlingua = idlingua;
	}

	@Basic
	@Column(name = "DESCRIZIONE")
	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		EventoallegatiintEntity that = (EventoallegatiintEntity) o;
		return idallegato == that.idallegato &&
						Objects.equals(idlingua, that.idlingua) &&
						Objects.equals(descrizione, that.descrizione);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idallegato, idlingua, descrizione);
	}

	public void setEventoallegatiByIdallegato(EventoallegatiEntity eventoallegatiByIdallegato) {
		this.eventoallegatiByIdallegato = eventoallegatiByIdallegato;
	}
}
