package it.indra.SigecAPI.migration.entity;

import java.io.Serializable;

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
@Table(name="T_SIGEA_SIGEA2VIP1SHOT_MIGRA",schema="sigec")
@IdClass(Sigea2Vip1ShotMigraPK.class)
public class Sigea2Vip1ShotMigra implements Serializable {


	
	private static final long serialVersionUID = 1L;

	private Long idSigea;
	private Long idVip;
	private String cont;
	private String tipo;
	private Long idSigea2;
	private long related;

	@Id
	@Column(name = "ID_SIGEA")
	public Long getIdSigea() {
		return idSigea;
	}
	public void setIdSigea(Long idSigea) {
		this.idSigea = idSigea;
	}
	
	@Id
	@Column(name = "ID_SIGEA_2")
	public Long getIdSigea2() {
		return idSigea2;
	}
	public void setIdSigea2(Long idSigea2) {
		this.idSigea2 = idSigea2;
	}
	
	
	@Column(name = "ID_VIP")
	public Long getIdVip() {
		return idVip;
	}
	public void setIdVip(Long idVip) {
		this.idVip = idVip;
	}	
	
	@Column(name = "CONT")
	public String getCont() {
		return cont;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}
	
	@Column(name = "TIPO")
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	

	@Column(name = "RELATED")
	public long getRelated() {
		return related;
	}
	public void setRelated(long related) {
		this.related = related;
	}


	
}
