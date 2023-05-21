package it.indra.SigecAPI.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.javers.core.metamodel.annotation.DiffIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="t_SIGEA_ACCESSIBILITA",schema="sigec")
@Data
@EqualsAndHashCode(exclude="evento")
public class Accessibilita implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "EVENTO_ID")
	private Long eventoId;

	//Accessibilità per persone con esigenze specifiche
	@Column(name = "FLAG_FAMIGLIE_BAMBINI")
	private Boolean flagFamiglieBambini;
	@Column(name = "FLAG_PERSONE_ANIMALI")
	private Boolean flagPersoneAnimali;
	@Column(name = "FLAG_DISABILITA_FISICA")
	private Boolean flagDisabilitaFisica;
	@Column(name = "FLAG_DISABILITA_VISIVA")
	private Boolean flagDisabilitaVisiva;
	@Column(name = "FLAG_DISABILITA_UDITIVA")
	private Boolean flagDisabilitaUditiva;
	@Column(name = "FLAG_GRAVIDANZA")
	private Boolean flagGravidanza;
	@Column(name = "FLAG_ANZIANI")
	private Boolean flagAnziani;
	
	//Animali ammessi
	@Column(name = "FLAG_CANI_MEDI")
	private Boolean flagCaniMedi;
	@Column(name = "FLAG_CANI_PICCOLI")
	private Boolean flagCaniPiccoli;
	@Column(name = "FLAG_CANI")
	private Boolean flagCani;
	
	//Famiglie con bambini ammesse
	@Column(name = "FLAG_BIBLIOTECA")
	private Boolean flagBiblioteca;
	@Column(name = "FLAG_GIARDINI")
	private Boolean flagGiardini;
	@Column(name = "FLAG_LUDOTECA")
	private Boolean flagLudoteca;
	@Column(name = "FLAG_NURSEY")
	private Boolean flagNursey;
	@Column(name = "FLAG_PARCOGIOCHI")
	private Boolean flagParcogiochi;
	
	//Percorsi accessibili
	@Column(name = "FLAG_PERCORSI")
	private Boolean flagPercorsi;
	
	//Sito web accessibile
	/*
	 * @Column(name = "FLAG_SITO")
	private Boolean flagSito=null;
	*/
	
	//Servizi accessibili
	@Column(name = "FLAG_INFOPOINT")
	private Boolean flagInfopoint;
	@Column(name = "FLAG_SERVIZI_IGIENICI")
	private Boolean flagServiziIgienici;
	@Column(name = "FLAG_PARCHEGGIO_DISABILI")
	private Boolean flagParcheggioDisabili;
	@Column(name = "FLAG_INGRESSI")
	private Boolean flagIngressi;
	@Column(name = "FLAG_PERCORSI_TATTILI")
	private Boolean flagPercorsiTattili;
	@Column(name = "FLAG_GUIDE_BRAILLE")
	private Boolean flagGuideBraille;
	@Column(name = "FLAG_SEGNALETICA_BRAILLE")
	private Boolean flagSegnaleticaBraille;
	@Column(name = "FLAG_SEGNALETICA_LEGGIBILE")
	private Boolean flagSegnaleticaLeggibile;
	@Column(name = "FLAG_MATERIALE_LEGGIBILE")
	private Boolean flagMaterialeLeggibile;
	@Column(name = "FLAG_POSTAZIONI_AUDIO")
	private Boolean flagPostazioniAudio;
	@Column(name = "FLAG_AUDIOGUIDE")
	private Boolean flagAudioguide;
	@Column(name = "FLAG_AUDIOGUIDE_PERCORSI")
	private Boolean flagAudioguidePercorsi;
	@Column(name = "FLAG_MATERIALE_SOTTOTITOLATO")
	private Boolean flagMaterialeSottotitolato;
	@Column(name = "FLAG_RIPRODUZIONE_TATTILI")
	private Boolean flagRiproduzioneTattili;
	@Column(name = "FLAG_COMPUTER")
	private Boolean flagComputer;
	@Column(name = "FLAG_LIS")
	private Boolean flagLis;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="EVENTO_ID")
	@MapsId
	@DiffIgnore
	private Evento evento;
}
