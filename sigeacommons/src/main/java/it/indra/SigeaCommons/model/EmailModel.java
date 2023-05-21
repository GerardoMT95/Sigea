package it.indra.SigeaCommons.model;

import java.io.Serializable;

import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class EmailModel implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String tipo = "EMAIL";
	
	private Long contattoId;
	
	@Size(max=300)
	private String contatto;
}