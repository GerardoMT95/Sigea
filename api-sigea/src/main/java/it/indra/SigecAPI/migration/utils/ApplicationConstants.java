package it.indra.SigecAPI.migration.utils;

import java.util.HashMap;
import java.util.Map;

public class ApplicationConstants {


	public static final String STATOPUBBLICAZIONE="PUBBLICATO";
	public static final String STATOEVENTOVALIDATO="VALIDATO";

	public static final String EVENTO = 		"EVENTO";
	public static final String RASSEGNA = 		"RASSEGNA";
	public static final String EVENTOSCHEDA = 	"evento";

	public static final String GRATUITO = 	"gratuito";
	public static final String PAGAMENTO = 	"pagamento";
	public static final String INDEFINITO = "indefinito";

	public static final String GRATIS = 		"GRATIS";
	public static final String RIDOTTO = 		"RIDUZIONE";
	public static final String ANZIANI = 		"ANZIANI";
	public static final String BAMBINI = 		"BAMBINI";
	public static final String CONVENZIONI = 	"CONVENZIONI_ATTIVE";
	public static final String DISABILI = 		"DISABILI";
	public static final String ACCOMPAGNATORI = "ACCOMP_DISABILI";

	public static final Long infoPoint=1L;
	public static final Long serviziIgienici=2L;
	public static final Long parcheggioDisabili=3L;
	public static final Long ingressi=4L;
	public static final Long percorsiTattili=5L;
	public static final Long guideBraille=6L;
	public static final Long segnaleticaBraille=7L;
	public static final Long segnaleticaLeggibile=8L;
	public static final Long materialeLeggibile=9L;
	public static final Long postazioniaudio=10L;
	public static final Long audioguideipovedenti=11L;
	public static final Long audioguidetattili=12L;
	public static final Long materialesottotitolato=13L;
	public static final Long riproduzionitattili=14L;
	public static final Long computerdescrizionivocali=15L;
	public static final Long personalelinguasegni=16L;

	public static final String DISAB_UDITIVA = 	"DISAB_UDITIVA";
	public static final String DISAB_VISIVA = 	"DISAB_VISIVA";
	public static final String ANIMALI = 		"ANIMALI";
	public static final String DISAB_FISICA = 	"DISAB_FISICA";
	public static final String GRAVIDANZA = 	"GRAVIDANZA";

	public static final String BIBLIOT_BAMBINI = 	"BIBLIOT_BAMBINI";
	public static final String GIARDINI = 			"GIARDINI";
	public static final String LUDOTECA = 			"LUDOTECA";
	public static final String NURSERY = 			"NURSERY";
	public static final String PARCO_GIOCHI = 		"PARCO_GIOCHI";

	public static final String ARTE = 			"ARTE";
	public static final String ASS_SPETTACOLI = "ASS_SPETTACOLI";
	public static final String AUTENTICO = 		"AUTENTICO";
	public static final String AVVENTURA = 		"AVVENTURA";
	public static final String BACKPACKERS = 	"BACKPACKERS";
	public static final String BENESSERE = 		"BENESSERE";
	public static final String CELEBRAZIONI = 	"CELEBRAZIONI";
	public static final String CONGRESSI = 		"CONGRESSI";
	public static final String DEVOZIONE = 		"DEVOZIONE";
	public static final String LGBT = 			"LGBT";
	public static final String LUSSO = 			"LUSSO";
	public static final String RELAX = 			"RELAX";
	public static final String SALUTE = 		"SALUTE";
	public static final String SHOPPING = 		"SHOPPING";
	public static final String SHORT_BREAK = 	"SHORT_BREAK";
	public static final String SPORT = 			"SPORT";
	public static final String STAYING = 		"STAYING";
	public static final String SVAGO = 			"SVAGO";
	public static final String VAC_ANZIANI = 	"VAC_ANZIANI";
	public static final String VAC_BAMBINI = 	"VAC_BAMBINI";
	public static final String VIAG_AFFARI = 	"VIAG_AFFARI";
	public static final String VIAG_ALL_INCL = 	"VIAG_ALL_INCL";
	public static final String VIAG_AMICI = 	"VIAG_AMICI";
	public static final String VIAG_DISABILI = 	"VIAG_DISABILI";
	public static final String VIAG_INCENTIVE = "VIAG_INCENTIVE";
	public static final String VIAG_NATURA = 	"VIAG_NATURA";
	public static final String VIAG_NOZZE = 	"VIAG_NOZZE";

	public static final String TUTTI_CANI = 		"TUTTI_CANI";
	public static final String CANI_TAGLIA_MEDIA = 	"CANI_TAGLIA_MEDIA";
	public static final String CANI_TAGLIA_PICC = 	"CANI_TAGLIA_PICC";

	public static final String GA_DA = "GA-DA";
	public static final String PU_IM = "PU-IM";
	public static final String BA_CO = "BA-CO";
	public static final String MA_GR = "MA-GR";
	public static final String VA_IT = "VA-IT";
	public static final String SA_LE = "SA-LE";
	public static final String GA_DA_DESC = "Gargano e Daunia";
	public static final String PU_IM_DESC = "Puglia Imperiale";
	public static final String BA_CO_DESC = "Bari e la costa";
	public static final String MA_GR_DESC = "Magna Grecia, Murgia e Gravine";
	public static final String VA_IT_DESC = "Valle d'Itria e Murgia dei Trulli";
	public static final String SA_LE_DESC = "Salento";
	public static final String A1 = "A1";
	public static final String A2 = "A2";
	public static final String A3 = "A3";
	public static final String A4 = "A4";
	public static final String A5 = "A5";
	public static final String A6 = "A6";

	public static final String IMMAGINE = 	"immagine";
	public static final String SI = 		"si";
	public static final String NO = 		"no";
	public static final String NESSUNA =	"Nessuna";
	public static final String DOT=			".";
	public static final String DOUBLEDOT=	":";
	public static final String DASHSPACE=	" - ";



	public static final Long DOM=0L;
	public static final Long LUN=1L;
	public static final Long MAR=2L;
	public static final Long MER=3L;
	public static final Long GIO=4L;
	public static final Long VEN=5L;
	public static final Long SAB=6L;

	public static final String CODREGIONE=		"16";
	public static final String NOMEREGIONE=		"PUGLIA";
	public static final String CODICENAZIONE=	"100";
	public static final String NOMENAZIONE=		"ITALIA";

	public static final String BOZZA=		"Bozza";
	public static final String INSERIMENTO=	"INSERIMENTO";
	public static final String MODIFICA=	"MODIFICA";
	public static final String VALIDATO=	"Validato";

	public static final String SALONE=	"Fiera/Salone";
	public static final String FIERA=	"Fiera";
	public static final String GARA=	"Gara/Torneo/Competizione";
	public static final String PARTITA=	"Partita";

	public static final String BANNER="banner";

	public static final String GENERAL="GENERAL";
	public static final String VIP=		"VIP";
	public static final String EVENTO_RASSEGNA ="evento_rassegna";

	public static final String BASE_PATH_FILE = "/file.php?id=";
	public static final String BASE_PATH_IMG = 	"/image.php?img=";
	
	public static final String SUCCESS =	"SUCCESS";
	public static final String DELETE =		"DELETE";
	
	public static final String CONTENUTO=	"CONTENUTO";
	public static final String SEPARATOR=	"/";

	
	public static final Map<String, String> createMapProdotti() {
		Map<String,String> myMap = new HashMap<String,String>();
		//VipVsSigea IDENTICI TRA PROD E COLLAUDO.
		myMap.put("9", "1");
		myMap.put("7", "2");
		myMap.put("6", "3");
		myMap.put("4", "4");
		myMap.put("39","5");
		myMap.put("2", "6");
		myMap.put("1", "7");
		myMap.put("3", "8");
		myMap.put("5", "9");
		myMap.put("8","10");

		return myMap;
	}
	
	public static final Map<String, String> createMapCodArea() {
		Map<String,String> codiciArea = new HashMap<String,String>();
		codiciArea.put(ApplicationConstants.GA_DA, 					ApplicationConstants.A1); 
		codiciArea.put(ApplicationConstants.PU_IM, 					ApplicationConstants.A2); 
		codiciArea.put(ApplicationConstants.BA_CO, 					ApplicationConstants.A3); 
		codiciArea.put(ApplicationConstants.MA_GR, 					ApplicationConstants.A4); 
		codiciArea.put(ApplicationConstants.VA_IT, 					ApplicationConstants.A5); 
		codiciArea.put(ApplicationConstants.SA_LE, 					ApplicationConstants.A6); 

		return codiciArea;
	}
	
	public static final Map<String, String> createMapDescArea() {
		Map<String, String> descrizioniArea=new HashMap<>();
		descrizioniArea.put(ApplicationConstants.GA_DA, 			ApplicationConstants.GA_DA_DESC); 
		descrizioniArea.put(ApplicationConstants.PU_IM, 			ApplicationConstants.PU_IM_DESC); 
		descrizioniArea.put(ApplicationConstants.BA_CO, 			ApplicationConstants.BA_CO_DESC); 
		descrizioniArea.put(ApplicationConstants.MA_GR, 			ApplicationConstants.MA_GR_DESC); 
		descrizioniArea.put(ApplicationConstants.VA_IT, 			ApplicationConstants.VA_IT_DESC); 
		descrizioniArea.put(ApplicationConstants.SA_LE, 			ApplicationConstants.SA_LE_DESC); 
		
		return descrizioniArea;
	}
	
	//MAPPA GENERICA DA INSERIRE NEI CAMPI DB CHE LO RICHIEDONO
	public static final Map<String, String> createMapMultiLanguage() {
		Map<String,String> multiLang = new HashMap<String,String>();

		multiLang.put("DE","");
		multiLang.put("EN","");
		multiLang.put("FR","");
		multiLang.put("IT","");
		multiLang.put("RU","");
		multiLang.put("SP","");

		return multiLang;
	}

}
