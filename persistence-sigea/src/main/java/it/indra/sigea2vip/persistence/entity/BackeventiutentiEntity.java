package it.indra.sigea2vip.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "BACKEVENTIUTENTI", schema = "TURISMO")
public class BackeventiutentiEntity {

	private long idutente;
	private String ragioneSociale;
	private String firstName;
	private String lastName;
	private String tel;
	private String tel2;
	private Boolean dichiarazione;
	private boolean privacy;
	private boolean guidelines;
	private Boolean facebook;
	private String idfacebook;
	private String culture;
	private String tipo;
	private String email;
	private String username;
	private String algorithm;
	private String salt;
	private String password;
	private Boolean attivo;
	private Boolean confermato;
	private Boolean superuser;
	private Time dataultimologin;
	private Time datainserimento;
	private Time datamodifica;
	private boolean sbloccadichiarazione;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BACKEVENTIUTENTI_SEQ")
	@SequenceGenerator(schema = "TURISMO", sequenceName = "BACKEVENTIUTENTI_SEQ", allocationSize = 1, name = "BACKEVENTIUTENTI_SEQ")
	@Column(name = "IDUTENTE")
	public long getIdutente() {
		return idutente;
	}

	public void setIdutente(long idutente) {
		this.idutente = idutente;
	}

	@Basic
	@Column(name = "RAGIONE_SOCIALE")
	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	@Basic
	@Column(name = "FIRST_NAME")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Basic
	@Column(name = "LAST_NAME")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	@Column(name = "TEL2")
	public String getTel2() {
		return tel2;
	}

	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}

	@Basic
	@Column(name = "DICHIARAZIONE")
	public Boolean getDichiarazione() {
		return dichiarazione;
	}

	public void setDichiarazione(Boolean dichiarazione) {
		this.dichiarazione = dichiarazione;
	}

	@Basic
	@Column(name = "PRIVACY")
	public boolean isPrivacy() {
		return privacy;
	}

	public void setPrivacy(boolean privacy) {
		this.privacy = privacy;
	}

	@Basic
	@Column(name = "GUIDELINES")
	public boolean isGuidelines() {
		return guidelines;
	}

	public void setGuidelines(boolean guidelines) {
		this.guidelines = guidelines;
	}

	@Basic
	@Column(name = "FACEBOOK")
	public Boolean getFacebook() {
		return facebook;
	}

	public void setFacebook(Boolean facebook) {
		this.facebook = facebook;
	}

	@Basic
	@Column(name = "IDFACEBOOK")
	public String getIdfacebook() {
		return idfacebook;
	}

	public void setIdfacebook(String idfacebook) {
		this.idfacebook = idfacebook;
	}

	@Basic
	@Column(name = "CULTURE")
	public String getCulture() {
		return culture;
	}

	public void setCulture(String culture) {
		this.culture = culture;
	}

	@Basic
	@Column(name = "TIPO")
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	@Column(name = "USERNAME")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Basic
	@Column(name = "ALGORITHM")
	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
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
	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Basic
	@Column(name = "ATTIVO")
	public Boolean getAttivo() {
		return attivo;
	}

	public void setAttivo(Boolean attivo) {
		this.attivo = attivo;
	}

	@Basic
	@Column(name = "CONFERMATO")
	public Boolean getConfermato() {
		return confermato;
	}

	public void setConfermato(Boolean confermato) {
		this.confermato = confermato;
	}

	@Basic
	@Column(name = "SUPERUSER")
	public Boolean getSuperuser() {
		return superuser;
	}

	public void setSuperuser(Boolean superuser) {
		this.superuser = superuser;
	}

	@Basic
	@Column(name = "DATAULTIMOLOGIN")
	public Time getDataultimologin() {
		return dataultimologin;
	}

	public void setDataultimologin(Time dataultimologin) {
		this.dataultimologin = dataultimologin;
	}

	@Basic
	@Column(name = "DATAINSERIMENTO")
	public Time getDatainserimento() {
		return datainserimento;
	}

	public void setDatainserimento(Time datainserimento) {
		this.datainserimento = datainserimento;
	}

	@Basic
	@Column(name = "DATAMODIFICA")
	public Time getDatamodifica() {
		return datamodifica;
	}

	public void setDatamodifica(Time datamodifica) {
		this.datamodifica = datamodifica;
	}

	@Basic
	@Column(name = "SBLOCCADICHIARAZIONE")
	public boolean isSbloccadichiarazione() {
		return sbloccadichiarazione;
	}

	public void setSbloccadichiarazione(boolean sbloccadichiarazione) {
		this.sbloccadichiarazione = sbloccadichiarazione;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BackeventiutentiEntity that = (BackeventiutentiEntity) o;
		return idutente == that.idutente &&
						privacy == that.privacy &&
						guidelines == that.guidelines &&
						sbloccadichiarazione == that.sbloccadichiarazione &&
						Objects.equals(ragioneSociale, that.ragioneSociale) &&
						Objects.equals(firstName, that.firstName) &&
						Objects.equals(lastName, that.lastName) &&
						Objects.equals(tel, that.tel) &&
						Objects.equals(tel2, that.tel2) &&
						Objects.equals(dichiarazione, that.dichiarazione) &&
						Objects.equals(facebook, that.facebook) &&
						Objects.equals(idfacebook, that.idfacebook) &&
						Objects.equals(culture, that.culture) &&
						Objects.equals(tipo, that.tipo) &&
						Objects.equals(email, that.email) &&
						Objects.equals(username, that.username) &&
						Objects.equals(algorithm, that.algorithm) &&
						Objects.equals(salt, that.salt) &&
						Objects.equals(password, that.password) &&
						Objects.equals(attivo, that.attivo) &&
						Objects.equals(confermato, that.confermato) &&
						Objects.equals(superuser, that.superuser) &&
						Objects.equals(dataultimologin, that.dataultimologin) &&
						Objects.equals(datainserimento, that.datainserimento) &&
						Objects.equals(datamodifica, that.datamodifica);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idutente, ragioneSociale, firstName, lastName, tel, tel2, dichiarazione, privacy, guidelines, facebook, idfacebook, culture, tipo, email, username, algorithm, salt, password, attivo, confermato, superuser, dataultimologin, datainserimento, datamodifica, sbloccadichiarazione);
	}
}
