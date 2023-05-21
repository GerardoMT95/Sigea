package it.indra.SigeaCommons.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SegnalazioneStatoPatchModel {

	 String stato;
}
