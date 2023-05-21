package it.indra.SigecAPI.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.javers.core.metamodel.annotation.DiffIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "t_SIGEA_LOCATION", schema = "sigec")
@Data
@EqualsAndHashCode(exclude = "evento")
public class Location implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sigec.SIGEA_LOCATION_SEQ")
	@SequenceGenerator(name = "sigec.SIGEA_LOCATION_SEQ", sequenceName = "sigec.SIGEA_LOCATION_SEQ", allocationSize = 1, initialValue = 1)
	@Column(name = "LOCATION_ID")
	private Long locationId;

	@Column(name = "FG_PRINCIPALE")
	private Boolean fgPrincipale = false;

	@Column(name = "INDIRIZZO", length = 300)
	private String indirizzo;

	@Column(name = "CAP", length = 5)
	private String cap;

	@Column(name = "FG_PUGLIA")
	private Boolean puglia;

	@Column(name = "COD_NAZIONE")
	private String codNazione;

	@Column(name = "COD_REGIONE")
	private String codRegione;

	@Column(name = "COD_COMUNE")
	private String codComune;

	@Column(name = "COD_PROVINCIA")
	private String codProvincia;

	@Column(name = "COD_AREA")
	private String codArea;

	@Column(name = "NAZIONE", length = 300)
	private String nazione;

	@Column(name = "REGIONE", length = 300)
	private String regione;

	@Column(name = "AREA_TERRITORIALE", length = 300)
	private String area;

	@Column(name = "PROVINCIA", length = 300)
	private String provincia;

	@Column(name = "COMUNE", length = 300)
	private String comune;

	@Column(name = "COMUNE_ESTERO", length = 300)
	private String comuneEstero;

	@Column(name = "LINK", length = 300)
	private String link;

	@Column(name = "NOME_LOCATION", length = 300)
	private String nomeLocation;

	@Column(name = "LATITUDINE")
	private BigDecimal latitudine;

	@Column(name = "LONGITUDINE")
	private BigDecimal longitudine;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "EVENTO_ID", referencedColumnName = "EVENTO_ID")
	@DiffIgnore
	private Evento evento;

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(schema = "sigec", name = "T_SIGEA_LOCATION_ATTRATTORE", joinColumns = {
			@JoinColumn(name = "LOCATION_ID") }, inverseJoinColumns = { @JoinColumn(name = "ATTRATTORE_ID") })
	private Set<Attrattore> attrattoriSet = new HashSet<>();

	@Override
	public String toString() {
		return "Location [locationId=" + locationId + ", fgPrincipale=" + fgPrincipale + ", indirizzo=" + indirizzo
				+ ", cap=" + cap + ", puglia=" + puglia + ", codNazione=" + codNazione + ", codRegione=" + codRegione
				+ ", codComune=" + codComune + ", codProvincia=" + codProvincia + ", codArea=" + codArea + ", nazione="
				+ nazione + ", regione=" + regione + ", area=" + area + ", provincia=" + provincia + ", comune="
				+ comune + ", comuneEstero=" + comuneEstero + ", link=" + link + ", nomeLocation=" + nomeLocation
				+ ", latitudine=" + latitudine + ", longitudine=" + longitudine + ", evento=" + evento
				+ ", attrattoriSet=" + attrattoriSet + "]";
	}



}
