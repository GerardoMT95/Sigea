package it.indra.sigea2vip.persistence.entity;

import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "ATTIVITAANAGRAFICA", schema = "TURISMO", catalog = "")
public class AttivitaanagraficaEntity {
	private long idanagrafica;
	private String idprovinciariferimento;
	private Long idcomuneriferimento;
	private Long idlocalita;
	private String indirizzo;
	private String numerocivico;
	private String cap;
	private String telefono;
	private String cellulare;
	private String fax;
	private String email;
	private String sitoweb;
	private String personariferimento;

	@Id
	@Column(name = "IDANAGRAFICA")
	public long getIdanagrafica() {
		return idanagrafica;
	}

	public void setIdanagrafica(long idanagrafica) {
		this.idanagrafica = idanagrafica;
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
	@Column(name = "IDLOCALITA")
	public Long getIdlocalita() {
		return idlocalita;
	}

	public void setIdlocalita(Long idlocalita) {
		this.idlocalita = idlocalita;
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
	@Column(name = "TELEFONO")
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
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
	@Column(name = "FAX")
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
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
	@Column(name = "SITOWEB")
	public String getSitoweb() {
		return sitoweb;
	}

	public void setSitoweb(String sitoweb) {
		this.sitoweb = sitoweb;
	}

	@Basic
	@Column(name = "PERSONARIFERIMENTO")
	public String getPersonariferimento() {
		return personariferimento;
	}

	public void setPersonariferimento(String personariferimento) {
		this.personariferimento = personariferimento;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AttivitaanagraficaEntity that = (AttivitaanagraficaEntity) o;
		return idanagrafica == that.idanagrafica &&
						Objects.equals(idprovinciariferimento, that.idprovinciariferimento) &&
						Objects.equals(idcomuneriferimento, that.idcomuneriferimento) &&
						Objects.equals(idlocalita, that.idlocalita) &&
						Objects.equals(indirizzo, that.indirizzo) &&
						Objects.equals(numerocivico, that.numerocivico) &&
						Objects.equals(cap, that.cap) &&
						Objects.equals(telefono, that.telefono) &&
						Objects.equals(cellulare, that.cellulare) &&
						Objects.equals(fax, that.fax) &&
						Objects.equals(email, that.email) &&
						Objects.equals(sitoweb, that.sitoweb) &&
						Objects.equals(personariferimento, that.personariferimento);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idanagrafica, idprovinciariferimento, idcomuneriferimento, idlocalita, indirizzo, numerocivico, cap, telefono, cellulare, fax, email, sitoweb, personariferimento);
	}
}
