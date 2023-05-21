package it.indra.SigecAPI.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.javers.core.metamodel.annotation.DiffIgnore;

import it.indra.SigecAPI.converter.SetToStringConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="t_SIGEA_PERIODI",schema="sigec")
@Data
@EqualsAndHashCode(exclude="evento")
public class Periodo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sigec.SIGEA_PERIODI_SEQ")
	@SequenceGenerator(name = "sigec.SIGEA_PERIODI_SEQ", sequenceName = "sigec.SIGEA_PERIODI_SEQ", allocationSize = 1, initialValue = 1)
	@Column(name = "PERIODO_ID")
	private Long periodoId;
	
	@Column(name="DATA_SECCA")
	private Boolean dataSecca;
	
	@Column(name="DATA_DA")
	private Date dataDa;

	@Column(name="DATA_A")
	private Date dataA;
	
	@Column(name="CONTINUATO")
	private Boolean fgContinuato;
	
	@Column(name="ORARIO_APERTURA", length=10)
	private String orarioApertura;
	
	@Column(name="ORARIO_CHIUSURA", length=10)
	private String orarioChiusura;
	
	@Column(name="ORARIO_APERTURA_MATTINA", length=10)
	private String orarioAperturaMattina;
	
	@Column(name="ORARIO_CHIUSURA_MATTINA", length=10)
	private String orarioChiusuraMattina;

	@Column(name="ORARIO_APERTURA_POMERIGGIO", length=10)
	private String orarioAperturaPomeriggio;

	@Column(name="ORARIO_CHIUSURA_POMERIGGIO", length=10)
	private String orarioChiusuraPomeriggio;
	
	@Column(name="CHIUSURA_DOMENICA")
	private Boolean chiusuraDom = false;
	
	@Column(name="CHIUSURA_LUNEDI")
	private Boolean chiusuraLun = false;

	@Column(name="CHIUSURA_MARTEDI")
	private Boolean chiusuraMar = false;

	@Column(name="CHIUSURA_MERCOLEDI")
	private Boolean chiusuraMer = false;

	@Column(name="CHIUSURA_GIOVEDI")
	private Boolean chiusuraGio = false;

	@Column(name="CHIUSURA_VENERDI")
	private Boolean chiusuraVen = false;

	@Column(name="CHIUSURA_SABATO")
	private Boolean chiusuraSab = false;
	
	@Column(name="CADENZA")
	private String cadenza = "Nessuna";
	
	@Column(name="CADENZA_DOMENICA")
	private Boolean cadenzaDom = false;
	
	@Column(name="CADENZA_LUNEDI")
	private Boolean cadenzaLun = false;

	@Column(name="CADENZA_MARTEDI")
	private Boolean cadenzaMar = false;

	@Column(name="CADENZA_MERCOLEDI")
	private Boolean cadenzaMer = false;

	@Column(name="CADENZA_GIOVEDI")
	private Boolean cadenzaGio = false;

	@Column(name="CADENZA_VENERDI")
	private Boolean cadenzaVen = false;

	@Column(name="CADENZA_SABATO")
	private Boolean cadenzaSab = false;
	
	@Column(name="CADENZA_MENSILE")
	@Convert(converter = SetToStringConverter.class)
	private Set<String> cadenzaMensile = new HashSet<>();
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="EVENTO_ID", referencedColumnName="EVENTO_ID")
	@DiffIgnore
	private Evento evento;

	@Override
	public String toString() {
		return "Periodo [periodoId=" + periodoId + ", dataSecca=" + dataSecca + ", dataDa=" + dataDa + ", dataA="
				+ dataA + ", fgContinuato=" + fgContinuato + ", orarioApertura=" + orarioApertura + ", orarioChiusura="
				+ orarioChiusura + ", orarioAperturaMattina=" + orarioAperturaMattina + ", orarioChiusuraMattina="
				+ orarioChiusuraMattina + ", orarioAperturaPomeriggio=" + orarioAperturaPomeriggio
				+ ", orarioChiusuraPomeriggio=" + orarioChiusuraPomeriggio + ", chiusuraDom=" + chiusuraDom
				+ ", chiusuraLun=" + chiusuraLun + ", chiusuraMar=" + chiusuraMar + ", chiusuraMer=" + chiusuraMer
				+ ", chiusuraGio=" + chiusuraGio + ", chiusuraVen=" + chiusuraVen + ", chiusuraSab=" + chiusuraSab
				+ ", cadenza=" + cadenza + ", cadenzaDom=" + cadenzaDom + ", cadenzaLun=" + cadenzaLun + ", cadenzaMar="
				+ cadenzaMar + ", cadenzaMer=" + cadenzaMer + ", cadenzaGio=" + cadenzaGio + ", cadenzaVen="
				+ cadenzaVen + ", cadenzaSab=" + cadenzaSab + ", cadenzaMensile=" + cadenzaMensile + ", evento="
				+ evento + "]";
	}
	
	
	
}
