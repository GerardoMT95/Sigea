package it.indra.SigecAPI.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import it.indra.SigeaCommons.model.ConfigurazioneStato;
import it.indra.SigecAPI.extjpa.JsonDataUserType;
import lombok.Data;

@Entity
@Table(name="t_SIGEA_STATI",schema="sigec")
@TypeDef(name = "JsonDataUserType", typeClass = JsonDataUserType.class)
@Data
public class Stato implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "STATO_ID", length=20)
	private String statoId;
	
	@Column(name = "DESCRIZIONE", length=50)
	private String descrizione;
	
	@Column(name="STATO_INIZIO")
	private Boolean statoInizio;
	
	@Column(name="STATO_FINE")
	private Boolean statoFine;
	
	@Column(name="ACCESSO_CONDIZIONATO")
	private Boolean accessoCondizionato;
	
	@Column(name="NOME")
	private String nome;
	
	@Column(name="CONFIGURAZIONE")
	@Type(type = "JsonDataUserType", parameters = { @Parameter(name = "class", value = "it.indra.SigeaCommons.model.ConfigurazioneStato") })
	private ConfigurazioneStato configurazioneStato;
}
