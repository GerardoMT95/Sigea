package it.indra.SigeaCommons.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AttrattoreModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long attrattoreId;
	
	@NotNull
	@Size(max=300)
	private String etichetta;
}
