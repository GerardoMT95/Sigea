package it.indra.SigecAPI.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="t_SIGEA_DETTAGLIOUTENTE",schema="sigec")
@Data
public class DettaglioUtente {
	
	@Id
	@Column(name="UTENTE_ID")
	private Long utenteId;
	
	@Column(name="USERNAME", length=100)
	private String username;
	
	@Column(name="NOME", length=100)
	private String nome;
	
	@Column(name="COGNOME", length=100)
	private String cognome;
	
	@Column(name="COD_FISCALE", length=20)
	private String codFiscale;
	
	@Column(name="EMAIL", length=100)
	private String email;
	
	@Column(name="TEL")
	private String tel;
	
	@Column(name="CEL")
	private String cel;
	
	@Column(name="ENTITA_ID")
	private Long entitaId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="TIPOLOGIA_ID", referencedColumnName="TIPOLOGIA_ID")
	private Tipologia tipologia;

}
