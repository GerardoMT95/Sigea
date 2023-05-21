package it.indra.sigea2vip.persistence.entity;

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
@Table(name = "FOTO", schema = "TURISMO")
public class FotoEntity {
	private long idfoto;
	private String fotopiccola;
	private String fotogrande;
	private Long posizione;
	private String autore;
	private String tipo;
	private Long dimensione;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FOTO_IDFOTO_SEQ")
	@SequenceGenerator(schema = "TURISMO", sequenceName = "FOTO_IDFOTO_SEQ", allocationSize = 1, name = "FOTO_IDFOTO_SEQ")
	@Column(name = "IDFOTO")
	public long getIdfoto() {
		return idfoto;
	}

	public void setIdfoto(long idfoto) {
		this.idfoto = idfoto;
	}

	@Basic
	@Column(name = "FOTOPICCOLA")
	public String getFotopiccola() {
		return fotopiccola;
	}

	public void setFotopiccola(String fotopiccola) {
		this.fotopiccola = fotopiccola;
	}

	@Basic
	@Column(name = "FOTOGRANDE")
	public String getFotogrande() {
		return fotogrande;
	}

	public void setFotogrande(String fotogrande) {
		this.fotogrande = fotogrande;
	}

	@Basic
	@Column(name = "POSIZIONE")
	public Long getPosizione() {
		return posizione;
	}

	public void setPosizione(Long posizione) {
		this.posizione = posizione;
	}

	@Basic
	@Column(name = "AUTORE")
	public String getAutore() {
		return autore;
	}

	public void setAutore(String autore) {
		this.autore = autore;
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
	@Column(name = "DIMENSIONE")
	public Long getDimensione() {
		return dimensione;
	}

	public void setDimensione(Long dimensione) {
		this.dimensione = dimensione;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		FotoEntity that = (FotoEntity) o;
		return idfoto == that.idfoto &&
						Objects.equals(fotopiccola, that.fotopiccola) &&
						Objects.equals(fotogrande, that.fotogrande) &&
						Objects.equals(posizione, that.posizione) &&
						Objects.equals(autore, that.autore) &&
						Objects.equals(tipo, that.tipo) &&
						Objects.equals(dimensione, that.dimensione);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idfoto, fotopiccola, fotogrande, posizione, autore, tipo, dimensione);
	}
}
