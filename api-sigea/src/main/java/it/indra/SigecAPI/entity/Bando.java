package it.indra.SigecAPI.entity;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="t_SIGEA_BANDI",schema="sigec")
@Data
public class Bando implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Id
	@Column(name = "BANDO_ID", updatable=false)
	private String bandoId;
	
	@Column(name = "TITOLO_BANDO", updatable=false)
	private String titoloBando;

}
