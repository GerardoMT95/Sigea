package it.indra.SigecAPI.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.javers.core.metamodel.annotation.DiffIgnore;

import lombok.Data;

@Entity
@Table(name="t_SIGEA_EVENTI",schema="sigec")
@Data
public class Evento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sigec.SIGEA_EVENTI_SEQ")
	@SequenceGenerator(name = "sigec.SIGEA_EVENTI_SEQ", sequenceName = "sigec.SIGEA_EVENTI_SEQ", allocationSize = 1, initialValue = 1)
	@Column(name = "EVENTO_ID")
	private Long eventoId;
	
	@Column(name = "EMAIL_RICONCILIAZIONE")
	private String emailRiconciliazione;
	
	@Column(name = "DA_RICONCILIARE")
	private Boolean daRiconciliare = false;
	
	@Column(name = "TIPO", length=100)
	private String tipo;
	
	@Column(name = "ID_MIBACT")
	private String idMIBACT;
	
	@Column(name = "TIPOLOGIA_MIBACT")
	private String tipologiaMIBACT;
	
	@Column(name = "GRANDE_EVENTO")
	private Boolean grandeEvento;
	
	@Column(name = "ULTIME_NOTE", length=4000)
	private String ultimeNote;
	
	@Column(name="DATA_INSERIMENTO", updatable=false)
	@CreationTimestamp
	private Timestamp dataIns;
	
	@Column(name="DATA_MODIFICA")
	@UpdateTimestamp
	private Timestamp dataMod;
	
	@Column(name="DATA_DA_MIN")
	private Date dataDaMin;
	
	@Column(name="DATA_A_MAX")
	private Date dataAMax;
	
	@Column(name="FG_PUBBLICATO")
	private Boolean fgPubblicato = false;
	
	@Column(name="FG_FINANZIATO")
	private Boolean fgFinanziato;
	
	@Column(name="FG_VALIDAZIONE_PREGRESSA")
	private Boolean fgValidazionePregressa;
	
	@Column(name="FG_VALIDAZIONE_ULTIMO_GIORNO")
	private Boolean fgValidazioneUltimoGiorno;

	
	@OneToOne(mappedBy = "evento", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false, orphanRemoval = true)
	@PrimaryKeyJoinColumn
	private Ticket ticket;
	
	@OneToOne(mappedBy = "evento", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false, orphanRemoval = true)
	@PrimaryKeyJoinColumn
	private DatiGenerali datiGenerali;
	
	@OneToOne(mappedBy = "evento", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false, orphanRemoval = true)
	@PrimaryKeyJoinColumn
	private Accessibilita accessibilita;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name="BANDO_ID", referencedColumnName="BANDO_ID")
	private Bando bando;	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name="PROGETTO_ID", referencedColumnName="PROGETTO_ID")
	private Progetto progetto;
	
	/*
	@Column(name="PROGETTO_ID")
	private String progettoId;
	
	@Column(name="BANDO_ID")
	private String bandoId;
	*/


	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="OWNER_ID", referencedColumnName="UTENTE_ID", updatable= false)
	private DettaglioUtente owner;	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="UTENTE_ULTIMA_MODIFICA_ID", referencedColumnName="UTENTE_ID")
	private DettaglioUtente ownerUltimaModifica;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name="ATTIVITA_ID", referencedColumnName="ATTIVITA_ID")
	private Attivita attivita;	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name="RICHIESTA_ID", referencedColumnName="RICHIESTA_ID")
	private RichiestaAttivita richiestaAttivita;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name="STATO_ID", referencedColumnName="STATO_ID")
	private Stato stato;
	
	@OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<Location> locationSet;
	
	@OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<Immagine> immagineSet  = new HashSet<>();
	
	@OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<Link> linkSet  = new HashSet<>();
	
	@OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<DocumentoEvento> documentoSet = new HashSet<>();
	
	@OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<Contatto> contattoSet = new HashSet<>();
	
	@OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<Relazione> relazioneSet = new HashSet<>();
	
	@OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<LogEvento> logSet = new HashSet<>();
	
	@OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<Periodo> periodoSet = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="EVENTO_ID", referencedColumnName="EVENTO_ID", updatable = false, insertable = false)
	@DiffIgnore
	private Set<Pubblicazione> pubblicazioneSet;
	
	@Override
	public int hashCode() {
		
		return eventoId.intValue();
	}

	@Override
	public String toString() {
		return "Evento [eventoId=" + eventoId + ", emailRiconciliazione=" + emailRiconciliazione + ", daRiconciliare="
				+ daRiconciliare + ", tipo=" + tipo + ", idMIBACT=" + idMIBACT + ", tipologiaMIBACT=" + tipologiaMIBACT
				+ ", dataIns=" + dataIns + ", dataDaMin=" + dataDaMin + ", dataAMax=" + dataAMax + "]";
	}


	
}
