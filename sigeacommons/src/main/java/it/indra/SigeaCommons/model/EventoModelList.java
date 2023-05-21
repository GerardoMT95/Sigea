package it.indra.SigeaCommons.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@AllArgsConstructor 
@FieldNameConstants
public class EventoModelList implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String eventoId; //1
	
	private String tipo;//2
	
	private String titolo;//3
	
	private String tipologieMibact;//4
	
	private String owner;//5
	
	private String emailOwner;//6
	
	private String dataDa;//7
	
	private String dataA;//8
	
	private String comune;//9
	
	private String dataIns;//10
	
	private String dataMod;//11
	
	private String dataPubblicazione;//12
	
	private String stato;//13
	
	private String finanziato;//14
	
	private String redazioni;//15
	
	private String relazioni;//16
	
	private String ownerLock;//17
	
	private String pubblicatoRedazione;//18
	
	private String ripubblicatoRedazione;//19
	
	private String entitaOwner;//20
	
	private String tipologiaScheda;//21
	
	private String statoPubblicazione;//22
	
	private String prodotti;//23
	
	private String tipologieAttivita;//24
	
	private String statoImpresa;//25
	
	private String titoloBando; //26
	
	private String titoloProgetto; //27
	

}
