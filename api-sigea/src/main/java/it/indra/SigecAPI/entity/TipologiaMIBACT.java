package it.indra.SigecAPI.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="t_SIGEA_TIPOLOGIE_MIBACT",schema="sigec")
@Data
public class TipologiaMIBACT implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "TIPOLOGIA_ID")
	private String idMIBACT;
	
	@Column(name = "DESCRIZIONE")
	private String tipologiaMIBACT;
	
}