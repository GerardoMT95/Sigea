package it.indra.sigea2vip.persistence.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "UTENTE", schema = "TURISMO")
public class UtenteEntity {
	private Long idutente;
	private String idprofilo;
	private String nomeutente;
	private String password;
	private String nome;
	private String cognome;
	private String telefono;
	private String fax;
	private String cellulare;
	private String email;
	private String salt;
	private Long idtipologiautente;
	private String indirizzo;
	private String numerocivico;
	private String cap;
	private String idprovinciariferimento;
	private Long idcomuneriferimento;
	private String sitoweb;
	private String posizioneazienda;
	private String newsletter;
	private String idstato;
	private Date datainserimento;
	
	@Id
	@Column(name = "IDUTENTE")
	public Long getIdutente() {
		return idutente;
	}
	public void setIdutente(Long idutente) {
		this.idutente = idutente;
	}
	
	@Basic
	@Column(name = "IDPROFILO")
	public String getIdprofilo() {
		return idprofilo;
	}
	public void setIdprofilo(String idprofilo) {
		this.idprofilo = idprofilo;
	}
	
	@Basic
	@Column(name = "NOMEUTENTE")
	public String getNomeutente() {
		return nomeutente;
	}
	public void setNomeutente(String nomeutente) {
		this.nomeutente = nomeutente;
	}
	
	@Basic
	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Basic
	@Column(name = "NOME")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Basic
	@Column(name = "COGNOME")
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	@Basic
	@Column(name = "TELEFONO")
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	@Basic
	@Column(name = "FAX")
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	
	@Basic
	@Column(name = "CELLULARE")
	public String getCellulare() {
		return cellulare;
	}
	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
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
	@Column(name = "SALT")
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	@Basic
	@Column(name = "IDTIPOLOGIAUTENTE")
	public Long getIdtipologiautente() {
		return idtipologiautente;
	}
	public void setIdtipologiautente(Long idtipologiautente) {
		this.idtipologiautente = idtipologiautente;
	}
	
	@Basic
	@Column(name = "INDIRIZZO")
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	@Basic
	@Column(name = "NUMEROCIVICO")
	public String getNumerocivico() {
		return numerocivico;
	}
	public void setNumerocivico(String numerocivico) {
		this.numerocivico = numerocivico;
	}
	
	@Basic
	@Column(name = "CAP")
	public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}
	
	@Basic
	@Column(name = "IDPROVINCIARIFERIMENTO")
	public String getIdprovinciariferimento() {
		return idprovinciariferimento;
	}
	public void setIdprovinciariferimento(String idprovinciariferimento) {
		this.idprovinciariferimento = idprovinciariferimento;
	}
	
	@Basic
	@Column(name = "IDCOMUNERIFERIMENTO")
	public Long getIdcomuneriferimento() {
		return idcomuneriferimento;
	}
	public void setIdcomuneriferimento(Long idcomuneriferimento) {
		this.idcomuneriferimento = idcomuneriferimento;
	}
	
	@Basic
	@Column(name = "SITOWEB")
	public String getSitoweb() {
		return sitoweb;
	}
	public void setSitoweb(String sitoweb) {
		this.sitoweb = sitoweb;
	}
	
	@Basic
	@Column(name = "POSIZIONEAZIENDA")
	@Lob 
	public String getPosizioneazienda() {
		return posizioneazienda;
	}
	public void setPosizioneazienda(String posizioneazienda) {
		this.posizioneazienda = posizioneazienda;
	}
	
	@Basic
	@Column(name = "NEWSLETTER")
	public String getNewsletter() {
		return newsletter;
	}
	public void setNewsletter(String newsletter) {
		this.newsletter = newsletter;
	}
	
	@Basic
	@Column(name = "IDSTATO")
	public String getIdstato() {
		return idstato;
	}
	public void setIdstato(String idstato) {
		this.idstato = idstato;
	}
	
	@Basic
	@Column(name = "DATAINSERIMENTO")
	public Date getDatainserimento() {
		return datainserimento;
	}
	public void setDatainserimento(Date datainserimento) {
		this.datainserimento = datainserimento;
	}
	


//	@Override
//	public boolean equals(Object o) {
//		if (this == o) return true;
//		if (o == null || getClass() != o.getClass()) return false;
//		UtenteEntity that = (UtenteEntity) o;
//		return idticket == that.idticket &&
//						Objects.equals(prezzointero, that.prezzointero) &&
//						Objects.equals(numerominimoriduzione, that.numerominimoriduzione) &&
//						Objects.equals(prezzogruppo, that.prezzogruppo) &&
//						Objects.equals(prezzoridotto, that.prezzoridotto) &&
//						Objects.equals(linkprenotazione, that.linkprenotazione) &&
//						Objects.equals(convenzioniattive, that.convenzioniattive);
//	}

	@Override
	public int hashCode() {
		return Objects.hash(idutente, nomeutente, cognome, telefono, fax, cellulare, email);
	}

}
