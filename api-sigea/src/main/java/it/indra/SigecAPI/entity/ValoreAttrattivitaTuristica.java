package it.indra.SigecAPI.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name="t_SIGEA_VALORE_ATTRATTIVITA_TURISTICA",schema="sigec")
@Data
public class ValoreAttrattivitaTuristica implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "VALORE_ID")
	private String valoreId;
	
	@Column(name = "DESCRIZIONE")
	private String valore;
	
}