package it.indra.SigecAPI.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.javers.core.metamodel.annotation.DiffIgnore;

import it.indra.SigecAPI.extjpa.JsonDataUserType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="t_SIGEA_TICKETS",schema="sigec")
@TypeDef(name = "JsonDataUserType", typeClass = JsonDataUserType.class)
@Data
@EqualsAndHashCode(exclude="evento")
public class Ticket implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "EVENTO_ID")
	private Long eventoId;
	
	@Column(name = "TIPO_TICKET", length=50)
	private String tipoTicket;

	@Column(name = "TICKET_INTERO")
	private BigDecimal ticketIntero;

	@Column(name = "TICKET_RIDOTTO")
	private BigDecimal ticketRidotto;

	@Column(name = "TICKET_GRUPPIRIDOTTO")
	private BigDecimal ticketGruppiRidotto;

	@Column(name = "TICKET_GRUPPINUMERO")
	private Long ticketGruppiNumero;
	
	@Column(name = "FLAG_GRATIS_ANZIANI")
	private Boolean flagGratisAnziani;
	@Column(name = "FLAG_GRATIS_BAMBINI")
	private Boolean flagGratisBambini;
	@Column(name = "FLAG_GRATIS_CONVENZIONI")
	private Boolean flagGratisConvenzioni;
	@Column(name = "FLAG_GRATIS_DISABILI")
	private Boolean flagGratisDisabili;
	@Column(name = "FLAG_GRATIS_ACCOMPAGNATORI")
	private Boolean flagGratisAccompagnatori;
	
	@Column(name = "FLAG_RIDOTTO_ANZIANI")
	private Boolean flagRidottoAnziani;
	@Column(name = "FLAG_RIDOTTO_BAMBINI")
	private Boolean flagRidottoBambini;
	@Column(name = "FLAG_RIDOTTO_CONVENZIONI")
	private Boolean flagRidottoConvenzioni;
	@Column(name = "FLAG_RIDOTTO_DISABILI")
	private Boolean flagRidottoDisabili;
	@Column(name = "FLAG_RIDOTTO_ACCOMPAGNATORI")
	private Boolean flagRidottoAccompagnatori;
	
	@Column(name = "CONVEZIONI_GRATUITE", length=300)
	private String convenzioniAttiveG;
	
	@Column(name = "CONVENZIONI_RIDOTTE", length=300)
	private String convenzioniAttiveR;

	@Column(name = "LINK_PRENOTAZIONI", length=300)
	private String linkPrenotazioni;
	
	@Column(name = "NOTA", length=300)
	private String nota;
	
	@Column(name = "NOTA_MULTI")
	@Type(type = "JsonDataUserType", parameters = { @Parameter(name = "class", value = "java.util.Map") })
	private Map<String,String> notaMulti = new HashMap<>();
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="EVENTO_ID")
	@MapsId
	@DiffIgnore
	private Evento evento;
}
