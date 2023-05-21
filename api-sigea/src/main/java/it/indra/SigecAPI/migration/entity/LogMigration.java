package it.indra.SigecAPI.migration.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name="T_SIGEA_LOG_MIGRATION",schema="sigec")
@Data
public class LogMigration implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Id
	@Column(name = "EVENTO_ID_VIP")
	private long eventoIdVip;
	
	@Column(name = "EVENTO_ID_SIGEA")
	private long eventoIdSigea;
	
	@Column(name = "RISULTATO")
	private String risultato;
	
	@Column(name = "RISULTATO_REL")
	private String risultatoRel;
	
	@Column(name="DATA_INSERIMENTO")
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataIns;

	@Column(name = "UTILITY")
	private String utility;
	
	@Column(name = "EVENTO_VIP")
	private String eventoVip;
	
	@Column(name = "EVENTO_SIGEA")
	private String eventoSigea;
	
	@Column(name = "UTENTE_VIP")
	private String utenteVip;
	
	@Column(name = "TICKET_VIP")
	private String ticketVip;
	
	@Column(name = "TICKET_SIGEA")
	private String ticketSigea;
	
	@Column(name = "ACCESSIBILITA_VIP")
	private String accessibilitaVip;
	
	@Column(name = "ACCESSIBILITA_SIGEA")
	private String accessibilitaSigea;

	@Column(name = "DATI_GENERALI_VIP")
	private String datiGeneraliVip;
	
	@Column(name = "DATI_GENERALI_SIGEA")
	private String datiGeneraliSigea;
	
	@Column(name = "LOCATION_VIP")
	private String locationVip;
	
	@Column(name = "LOCATION_SIGEA")
	private String locationSigea;
	
	@Column(name = "PERIODO_VIP")
	private String periodoVip;
	
	@Column(name = "PERIODO_SIGEA")
	private String periodoSigea;
	
	@Column(name = "ALLEGATI_VIP")
	private String allegatiVip;
	
	@Column(name = "IMMAGINI_SIGEA")
	private String immaginiSigea;
	
	@Column(name = "DOCUMENTI_SIGEA")
	private String documentiSigea;
	
	@Column(name = "LINK_SIGEA")
	private String linkSigea;
	
	@Column(name = "ALLEGATI_VIP2SIGEA")
	private String allegatiVip2Sigea;
	
	@Column(name = "PRODOTTI_VIP")
	private String prodottiVip;
	
	@Column(name = "MICROESPERIENZE_VIP")
	private String microesperienzeVip;
	
	@Column(name = "METADATA_SIGEA")
	private String metadataSigea;
	
	@Column(name = "RELAZIONI_VIP")
	private String relazioniVip;
	
	@Column(name = "METADATA_POST_REL_SIGEA")
	private String metadataPostRelSigea;
	
	
}
