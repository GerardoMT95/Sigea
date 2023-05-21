package it.indra.sigea2vip.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "SIGEA2VIP", schema = "TURISMO")
public class Sigea2VipEntity implements Serializable {
	//TODO valutare se inserire la tipologia se evento o attività
	private Long idEventoVip;
	private Long idSigea;
	private Long idAttivitaVip;
	private Date datainserimento;


	@Column(name = "IDEVENTO_VIP")
	public Long getIdEventoVip() {
		return idEventoVip;
	}
	public void setIdEventoVip(Long idEventoVip) {
		this.idEventoVip = idEventoVip;
	}


	@Id
	@Column(name = "ID_SIGEA")
	public Long getIdSigea() {
		return idSigea;
	}
	public void setIdSigea(Long idSigea) {
		this.idSigea = idSigea;
	}

	@Column(name = "IDATTIVITA_VIP")
	public Long getIdAttivitaVip() {
		return idAttivitaVip;
	}
	public void setIdAttivitaVip(Long idAttivitaVip) {
		this.idAttivitaVip = idAttivitaVip;
	}
	
	@Basic
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATAINSERIMENTO", updatable = false)
	public Date getDatainserimento() {
		return datainserimento;
	}
	public void setDatainserimento(Date datainserimento) {
		this.datainserimento = datainserimento;
	}



}
