package it.indra.sigea2vip.persistence.entity;

import java.util.Objects;

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
@Table(name = "TICKETSCONTO", schema = "TURISMO", catalog = "")
@IdClass(TicketscontoEntityPK.class)
public class TicketscontoEntity {
	private long idticket;
	private long idsconto;
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
	@Column(name = "IDSCONTO")
	public long getIdsconto() {
		return idsconto;
	}

	public void setIdsconto(long idsconto) {
		this.idsconto = idsconto;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TicketscontoEntity that = (TicketscontoEntity) o;
		return idticket == that.idticket &&
						idsconto == that.idsconto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idticket, idsconto);
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
