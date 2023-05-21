package it.indra.sigea2vip.persistence.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "MULTIMEDIA", schema = "TURISMO", catalog = "")
public class MultimediaEntity {
	private long idmultimedia;
	private String nomefile;
	private Date datacreazione;
	private String autorecreazione;
	private String ipautorecreazione;
	private Date datamodifica;
	private String autoremodifica;
	private String ipautoremodifica;
	private String nomefilefoto;
	private Date dataaggiornamento;
	private String nomefotominiatura;
	private String dimensionerisorsa;
	private String nomefileen;
	private String nomefilede;
	private String idStato;
	private String idTipologiaMultimedia;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MULTIMEDIA_IDMULTIMEDIA_SEQ")
	@SequenceGenerator(schema = "TURISMO", sequenceName = "MULTIMEDIA_IDMULTIMEDIA_SEQ", allocationSize = 1, name = "MULTIMEDIA_IDMULTIMEDIA_SEQ")
	@Column(name = "IDMULTIMEDIA")
	public long getIdmultimedia() {
		return idmultimedia;
	}

	public void setIdmultimedia(long idmultimedia) {
		this.idmultimedia = idmultimedia;
	}

	@Basic
	@Column(name = "NOMEFILE")
	public String getNomefile() {
		return nomefile;
	}

	public void setNomefile(String nomefile) {
		this.nomefile = nomefile;
	}

	@Basic
	@Column(name = "DATACREAZIONE")
	public Date getDatacreazione() {
		return datacreazione;
	}

	public void setDatacreazione(Date datacreazione) {
		this.datacreazione = datacreazione;
	}

	@Basic
	@Column(name = "AUTORECREAZIONE")
	public String getAutorecreazione() {
		return autorecreazione;
	}

	public void setAutorecreazione(String autorecreazione) {
		this.autorecreazione = autorecreazione;
	}

	@Basic
	@Column(name = "IPAUTORECREAZIONE")
	public String getIpautorecreazione() {
		return ipautorecreazione;
	}

	public void setIpautorecreazione(String ipautorecreazione) {
		this.ipautorecreazione = ipautorecreazione;
	}

	@Basic
	@Column(name = "DATAMODIFICA")
	public Date getDatamodifica() {
		return datamodifica;
	}

	public void setDatamodifica(Date datamodifica) {
		this.datamodifica = datamodifica;
	}

	@Basic
	@Column(name = "AUTOREMODIFICA")
	public String getAutoremodifica() {
		return autoremodifica;
	}

	public void setAutoremodifica(String autoremodifica) {
		this.autoremodifica = autoremodifica;
	}

	@Basic
	@Column(name = "IPAUTOREMODIFICA")
	public String getIpautoremodifica() {
		return ipautoremodifica;
	}

	public void setIpautoremodifica(String ipautoremodifica) {
		this.ipautoremodifica = ipautoremodifica;
	}

	@Basic
	@Column(name = "NOMEFILEFOTO")
	public String getNomefilefoto() {
		return nomefilefoto;
	}

	public void setNomefilefoto(String nomefilefoto) {
		this.nomefilefoto = nomefilefoto;
	}

	@Basic
	@Column(name = "DATAAGGIORNAMENTO")
	public Date getDataaggiornamento() {
		return dataaggiornamento;
	}

	public void setDataaggiornamento(Date dataaggiornamento) {
		this.dataaggiornamento = dataaggiornamento;
	}

	@Basic
	@Column(name = "NOMEFOTOMINIATURA")
	public String getNomefotominiatura() {
		return nomefotominiatura;
	}

	public void setNomefotominiatura(String nomefotominiatura) {
		this.nomefotominiatura = nomefotominiatura;
	}

	@Basic
	@Column(name = "DIMENSIONERISORSA")
	public String getDimensionerisorsa() {
		return dimensionerisorsa;
	}

	public void setDimensionerisorsa(String dimensionerisorsa) {
		this.dimensionerisorsa = dimensionerisorsa;
	}

	@Basic
	@Column(name = "NOMEFILEEN")
	public String getNomefileen() {
		return nomefileen;
	}

	public void setNomefileen(String nomefileen) {
		this.nomefileen = nomefileen;
	}

	@Basic
	@Column(name = "NOMEFILEDE")
	public String getNomefilede() {
		return nomefilede;
	}

	public void setNomefilede(String nomefilede) {
		this.nomefilede = nomefilede;
	}
	
	@Basic
	@Column(name = "IDSTATO")
	public String getIdStato() {
		return idStato;
	}

	public void setIdStato(String idStato) {
		this.idStato = idStato;
	}
	
	@Basic
	@Column(name = "IDTIPOLOGIAMULTIMEDIA")
	public String getIdTipologiaMultimedia() {
		return idTipologiaMultimedia;
	}

	public void setIdTipologiaMultimedia(String idTipologiaMultimedia) {
		this.idTipologiaMultimedia = idTipologiaMultimedia;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		MultimediaEntity that = (MultimediaEntity) o;
		return idmultimedia == that.idmultimedia &&
						Objects.equals(nomefile, that.nomefile) &&
						Objects.equals(datacreazione, that.datacreazione) &&
						Objects.equals(autorecreazione, that.autorecreazione) &&
						Objects.equals(ipautorecreazione, that.ipautorecreazione) &&
						Objects.equals(datamodifica, that.datamodifica) &&
						Objects.equals(autoremodifica, that.autoremodifica) &&
						Objects.equals(ipautoremodifica, that.ipautoremodifica) &&
						Objects.equals(nomefilefoto, that.nomefilefoto) &&
						Objects.equals(dataaggiornamento, that.dataaggiornamento) &&
						Objects.equals(nomefotominiatura, that.nomefotominiatura) &&
						Objects.equals(dimensionerisorsa, that.dimensionerisorsa) &&
						Objects.equals(nomefileen, that.nomefileen) &&
						Objects.equals(nomefilede, that.nomefilede) &&
						Objects.equals(idStato, that.idStato) &&
						Objects.equals(idTipologiaMultimedia, that.idTipologiaMultimedia);
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(idmultimedia, nomefile, datacreazione, autorecreazione, ipautorecreazione, datamodifica, autoremodifica, ipautoremodifica, nomefilefoto, dataaggiornamento, nomefotominiatura, dimensionerisorsa, nomefileen, nomefilede, idStato, idTipologiaMultimedia);
	}


}
