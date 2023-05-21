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

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "TICKETMAPPING", schema = "TURISMO")
public class TicketmappingEntity {
	private long idticket;
	private Long idattrattore;
	private Long idevento;
	private Long idattivita;
	private Long idtappainterregionale;

//	private TicketEntity ticketByIdticket;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TICKETMAPP_SEQ")
	@SequenceGenerator(schema = "TURISMO", sequenceName = "TICKETMAPP_SEQ", allocationSize = 1, name = "TICKETMAPP_SEQ")
	@Column(name = "IDTICKET")
	public long getIdticket() {
		return idticket;
	}

	public void setIdticket(long idticket) {
		this.idticket = idticket;
	}

	@Basic
	@Column(name = "IDATTRATTORE")
	public Long getIdattrattore() {
		return idattrattore;
	}

	public void setIdattrattore(Long idattrattore) {
		this.idattrattore = idattrattore;
	}

	@Basic
	@Column(name = "IDEVENTO")
	public Long getIdevento() {
		return idevento;
	}

	public void setIdevento(Long idevento) {
		this.idevento = idevento;
	}
	
	@Basic
	@Column(name = "IDATTIVITA")
	public Long getIdattivita() {
		return idattivita;
	}

	public void setIdattivita(Long idattivita) {
		this.idattivita = idattivita;
	}

	@Basic
	@Column(name = "IDTAPPAINTERREGIONALE")
	public Long getIdtappainterregionale() {
		return idtappainterregionale;
	}

	public void setIdtappainterregionale(Long idtappainterregionale) {
		this.idtappainterregionale = idtappainterregionale;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TicketmappingEntity that = (TicketmappingEntity) o;
		return idticket == that.idticket &&
						Objects.equals(idattrattore, that.idattrattore) &&
						Objects.equals(idevento, that.idevento) &&
						Objects.equals(idattivita, that.idattivita) &&
						Objects.equals(idtappainterregionale, that.idtappainterregionale);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idticket, idattrattore, idevento, idattivita, idtappainterregionale);
	}



//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "pk_ticketByIdticket")
//	public TicketEntity getTicketByIdticket() {
//		return ticketByIdticket;
//	}
//
//	public void setTicketByIdticket(TicketEntity ticketByIdticket) {
//		this.ticketByIdticket = ticketByIdticket;
//	}
}
