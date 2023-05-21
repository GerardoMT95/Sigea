package it.indra.SigecAPI.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.TypeDef;

import it.indra.SigecAPI.extjpa.JsonDataUserType;
import lombok.Data;

@Entity
@Table(name="t_SIGEA_TIPOLOGIE_UTENTI",schema="sigec")
@TypeDef(name = "JsonDataUserType", typeClass = JsonDataUserType.class)
@Data
public class Tipologia implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "TIPOLOGIA_ID")
	private String tipologiaId;

	@Column(name = "DESCRIZIONE")
	private String descrizione;
	
	@Column(name = "IS_REDATTORE")
	private Boolean isRedattore;
	
	@ManyToMany(cascade=CascadeType.MERGE)
	@JoinTable(schema="sigec",name = "T_SIGEA_TIPOLOGIA_RUOLO", joinColumns = { @JoinColumn(name="TIPOLOGIA_ID")}, inverseJoinColumns = { @JoinColumn(name="RUOLO_ID")})
	private Set<Ruolo> ruoliSet;
	
	@ManyToMany(cascade=CascadeType.MERGE)
	@JoinTable(schema="sigec",name = "T_SIGEA_TIPOLOGIA_REDAZIONE", joinColumns = { @JoinColumn(name="TIPOLOGIA_ID")}, inverseJoinColumns = { @JoinColumn(name="REDAZIONE_ID")})
	private Set<Redazione> redazioniSet;
	
	public Map<String,Boolean> getPermessiSommati() {
		Map<String,Boolean> permessiSommati = new HashMap<String,Boolean>();
		for (Ruolo ruolo : ruoliSet) {
			permessiSommati.putAll(ruolo.getPermessi());
		}
		return permessiSommati;
	}
	
}
