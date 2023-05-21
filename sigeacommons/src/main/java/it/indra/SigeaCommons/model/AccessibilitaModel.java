package it.indra.SigeaCommons.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AccessibilitaModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long eventoId;
	
	//Accessibilità per persone con esigenze specifiche
	private Boolean flagFamiglieBambini;
	private Boolean flagPersoneAnimali;
	private Boolean flagDisabilitaFisica;
	private Boolean flagDisabilitaVisiva;
	private Boolean flagDisabilitaUditiva;
	private Boolean flagGravidanza;
	private Boolean flagAnziani;
		
	//Animali ammessi
	private Boolean flagCaniMedi;
	private Boolean flagCaniPiccoli;
	private Boolean flagCani;
	
	//Famiglie con bambini ammesse
	private Boolean flagBiblioteca;
	private Boolean flagGiardini;
	private Boolean flagLudoteca;
	private Boolean flagNursey;
	private Boolean flagParcogiochi;
	
	//Percorsi accessibili
	private Boolean flagPercorsi;
	
	//Sito web accessibile
	//private Boolean flagSito;
	
	//Servizi accessibili
	private Boolean flagInfopoint;
	private Boolean flagServiziIgienici;
	private Boolean flagParcheggioDisabili;
	private Boolean flagIngressi;
	private Boolean flagPercorsiTattili;
	private Boolean flagGuideBraille;
	private Boolean flagSegnaleticaBraille;
	private Boolean flagSegnaleticaLeggibile;
	private Boolean flagMaterialeLeggibile;
	private Boolean flagPostazioniAudio;
	private Boolean flagAudioguide;
	private Boolean flagAudioguidePercorsi;
	private Boolean flagMaterialeSottotitolato;
	private Boolean flagRiproduzioneTattili;
	private Boolean flagComputer;
	private Boolean flagLis;
}
