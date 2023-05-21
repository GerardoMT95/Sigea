package it.indra.SigecAPI.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;



import lombok.Data;

@Entity
@Table(name="t_SIGEA_PROGETTI",schema="sigec")
@Data
public class Progetto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PROGETTO_ID", updatable=false)
	private String progettoId;

	@Column(name = "TITOLO_PROGETTO", updatable=false)
	private String titoloProgetto;
	
	@Column(name = "NOME_ORGANIZZAZIONE", updatable=false)
	private String nomeOrganizzazione;
	
	
	//@OneToOne(mappedBy = "progetto", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false, orphanRemoval = true)
	//@PrimaryKeyJoinColumn
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name="BANDO_ID", referencedColumnName="BANDO_ID")
	private Bando bando;
	
}
