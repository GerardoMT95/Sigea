package it.indra.sigea2vip.persistence.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "EVENTO", schema = "TURISMO")
public class EventoEntity {
	private long idevento;
	private String tipologia;
	private String tel;
	private String email;
	private String web;
	private String link1;
	private String link2;
	private Boolean homepage;
	private Boolean homecarousel;
	private Boolean tonewsletter;
	private Boolean tipodate;
	private String tipoticket;
	private Long idstatoapprovazione;
	private Long idutenteapprovazione;
	private Date datainserimento;
	private Long idutenteinserimento;
	private Long idutentemodifica;
	private Date datamodifica;
	private Boolean archiviato;
	private Boolean rilevante;
	private Short ranking;


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVENTO_SEQ")
	@SequenceGenerator(schema = "TURISMO", sequenceName = "EVENTO_SEQ", allocationSize = 1, name = "EVENTO_SEQ")
	@Column(name = "IDEVENTO")
	public long getIdevento() {
		return idevento;
	}

	public void setIdevento(long idevento) {
		this.idevento = idevento;
	}

	@Basic
	@Column(name = "TIPOLOGIA")
	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	@Basic
	@Column(name = "TEL")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Basic
	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Basic
	@Column(name = "WEB")
	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	@Basic
	@Column(name = "LINK1")
	public String getLink1() {
		return link1;
	}

	public void setLink1(String link1) {
		this.link1 = link1;
	}

	@Basic
	@Column(name = "LINK2")
	public String getLink2() {
		return link2;
	}

	public void setLink2(String link2) {
		this.link2 = link2;
	}

	@Basic
	@Column(name = "HOMEPAGE")
	public Boolean getHomepage() {
		return homepage;
	}

	public void setHomepage(Boolean homepage) {
		this.homepage = homepage;
	}

	@Basic
	@Column(name = "HOMECAROUSEL")
	public Boolean getHomecarousel() {
		return homecarousel;
	}

	public void setHomecarousel(Boolean homecarousel) {
		this.homecarousel = homecarousel;
	}

	@Basic
	@Column(name = "TONEWSLETTER")
	public Boolean getTonewsletter() {
		return tonewsletter;
	}

	public void setTonewsletter(Boolean tonewsletter) {
		this.tonewsletter = tonewsletter;
	}

	@Basic
	@Column(name = "TIPODATE")
	public Boolean getTipodate() {
		return tipodate;
	}

	public void setTipodate(Boolean tipodate) {
		this.tipodate = tipodate;
	}

	@Basic
	@Column(name = "TIPOTICKET")
	public String getTipoticket() {
		return tipoticket;
	}

	public void setTipoticket(String tipoticket) {
		this.tipoticket = tipoticket;
	}

	@Basic
	@Column(name = "IDSTATOAPPROVAZIONE")
	public Long getIdstatoapprovazione() {
		return idstatoapprovazione;
	}

	public void setIdstatoapprovazione(Long idstatoapprovazione) {
		this.idstatoapprovazione = idstatoapprovazione;
	}

	@Basic
	@Column(name = "IDUTENTEAPPROVAZIONE")
	public Long getIdutenteapprovazione() {
		return idutenteapprovazione;
	}

	public void setIdutenteapprovazione(Long idutenteapprovazione) {
		this.idutenteapprovazione = idutenteapprovazione;
	}

	@Basic
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATAINSERIMENTO", updatable = false)
	public Date getDatainserimento() {
		return datainserimento;
	}

	public void setDatainserimento(Date datainserimento) {
		this.datainserimento = datainserimento;
	}

	@Basic
	@Column(name = "IDUTENTEINSERIMENTO")
	public Long getIdutenteinserimento() {
		return idutenteinserimento;
	}

	public void setIdutenteinserimento(Long idutenteinserimento) {
		this.idutenteinserimento = idutenteinserimento;
	}

	@Basic
	@Column(name = "IDUTENTEMODIFICA")
	public Long getIdutentemodifica() {
		return idutentemodifica;
	}

	public void setIdutentemodifica(Long idutentemodifica) {
		this.idutentemodifica = idutentemodifica;
	}

	@Basic
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATAMODIFICA")
	public Date getDatamodifica() {
		return datamodifica;
	}

	public void setDatamodifica(Date datamodifica) {
		this.datamodifica = datamodifica;
	}

	@Basic
	@Column(name = "ARCHIVIATO")
	public Boolean getArchiviato() {
		return archiviato;
	}

	public void setArchiviato(Boolean archiviato) {
		this.archiviato = archiviato;
	}

	@Basic
	@Column(name = "RILEVANTE")
	public Boolean getRilevante() {
		return rilevante;
	}

	public void setRilevante(Boolean rilevante) {
		this.rilevante = rilevante;
	}

	@Basic
	@Column(name = "RANKING")
	public Short getRanking() {
		return ranking;
	}

	public void setRanking(Short ranking) {
		this.ranking = ranking;
	}
	
	@PreUpdate
	void dataModifica() {
		this.datamodifica = new Date(System.currentTimeMillis());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		EventoEntity that = (EventoEntity) o;
		return idevento == that.idevento &&
						Objects.equals(tipologia, that.tipologia) &&
						Objects.equals(tel, that.tel) &&
						Objects.equals(email, that.email) &&
						Objects.equals(web, that.web) &&
						Objects.equals(link1, that.link1) &&
						Objects.equals(link2, that.link2) &&
						Objects.equals(homepage, that.homepage) &&
						Objects.equals(homecarousel, that.homecarousel) &&
						Objects.equals(tonewsletter, that.tonewsletter) &&
						Objects.equals(tipodate, that.tipodate) &&
						Objects.equals(tipoticket, that.tipoticket) &&
						Objects.equals(idstatoapprovazione, that.idstatoapprovazione) &&
						Objects.equals(idutenteapprovazione, that.idutenteapprovazione) &&
						Objects.equals(datainserimento, that.datainserimento) &&
						Objects.equals(idutenteinserimento, that.idutenteinserimento) &&
						Objects.equals(idutentemodifica, that.idutentemodifica) &&
						Objects.equals(datamodifica, that.datamodifica) &&
						Objects.equals(archiviato, that.archiviato) &&
						Objects.equals(rilevante, that.rilevante) &&
						Objects.equals(ranking, that.ranking);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idevento, tipologia, tel, email, web, link1, link2, homepage, homecarousel, tonewsletter, tipodate, tipoticket, idstatoapprovazione, idutenteapprovazione, datainserimento, idutenteinserimento, idutentemodifica, datamodifica, archiviato, rilevante, ranking);
	}
}
