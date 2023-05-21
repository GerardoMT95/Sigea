package it.indra.SigeaCommons.model;

import java.sql.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EventoFilter {
	
	Long idUtenteIns;
	
	Long idAttivita;
	
	Long idRichiestaAttivita;
	
	Long eventoId;
	
	String tipologia;

	String dataInsDa;	

	String dataInsA;
	
	String dataAttivoDa;	

	String dataAttivoA;

	String titolo;

	String cognomeOwner;

	String emailOwner;

	String denominazioneAttivita;

	String pubblicato;
	
	String finanziato;
	
	String progettoId;
	
	String bandoId;
	
	Set<String> redazioni;
	
	Set<String> redazioniGenerali;
	
	String statoPubblicazione;

	String stato;
	
	String comuneEstero;
	
	String codNazione;
	
	Set<String> codProvinciaSet;
	
	Set<String> codComuneSet;
	
	String codArea;
	
	String codRegione;
	
	Date dataDa;
	
	Date dataA;
	
	String serviceCode;
	
	String idMIBACT;
	
	Set<String> filtroMIBACT;
	
	String redazioneAttuale;
	
	String tipologia2;

	String profilo;
	
	Set<String>  prodotti;
	Set<String>  prodottiNome;

}
