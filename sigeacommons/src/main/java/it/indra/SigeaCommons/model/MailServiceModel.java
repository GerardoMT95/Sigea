package it.indra.SigeaCommons.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MailServiceModel implements Serializable
{
	private static final long serialVersionUID = -1719273138736548967L;

	private String email[];
	private String subject;
	private String body;
}