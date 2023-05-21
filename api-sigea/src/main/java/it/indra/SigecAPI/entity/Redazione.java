package it.indra.SigecAPI.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="t_SIGEA_REDAZIONI",schema="sigec")
@Data
public class Redazione implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "REDAZIONE_ID")
	private String redazioneId;
	
	@Column(name = "NOME_REDAZIONE")
	private String nome;
	
	@Column(name = "LINK_REDAZIONE")
	private String link;
	
	@Column(name = "NOME_JSP")
	private String jspName;
	
	@Column(name = "LINK_SCHEDA")
	private String linkScheda;
	
	@Column(name = "AUTO_SPUBBLICAZIONE")
	private Boolean autoSpubblicazione;
	
	@Column(name = "ATTIVAZIONE_JMS")
	private Boolean attivazioneJMS;

}
