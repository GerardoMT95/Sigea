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

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TICKETNOTEINT", schema = "TURISMO", catalog = "")
@IdClass(TicketnoteintEntityPK.class)
public class TicketnoteintEntity {
	private long idticket;
	private String idlingua;
	private String note;
	//private TicketEntity ticketByIdticket;

	@Id
	@Column(name = "IDTICKET")
	public long getIdticket() {
		return idticket;
	}

	public void setIdticket(long idticket) {
		this.idticket = idticket;
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
	@Column(name = "NOTE")
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TicketnoteintEntity that = (TicketnoteintEntity) o;
		return idticket == that.idticket &&
						Objects.equals(idlingua, that.idlingua) &&
						Objects.equals(note, that.note);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idticket, idlingua, note);
	}

//	@ManyToOne
//	@JoinColumn(name = "pk_ticketByIdticket", referencedColumnName = "IDTICKET", nullable = false)
//	public TicketEntity getTicketByIdticket() {
//		return ticketByIdticket;
//	}
//
//	public void setTicketByIdticket(TicketEntity ticketByIdticket) {
//		this.ticketByIdticket = ticketByIdticket;
//	}
}
