package it.indra.sigea2vip.persistence.entity;

import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Lob;
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
@Table(name = "EVENTOINT", schema = "TURISMO", catalog = "")
@IdClass(EventointEntityPK.class)
public class EventointEntity {

	private long idEvento;
	private String idLingua;
	private String nome;
	private String descrizione;
	private String fieldAbstract;

	@Id
	@Column(name = "IDEVENTO")
	public long getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(long idEvento) {
		this.idEvento = idEvento;
	}
	
	@Id
	@Column(name = "IDLINGUA")
	public String getIdLingua() {
		return idLingua;
	}

	public void setIdLingua(String idLingua) {
		this.idLingua = idLingua;
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
	@Column(name = "DESCRIZIONE")
	@Lob 
	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	@Basic
	@Column(name = "ABSTRACT")
	public String getFieldAbstract() {
		return fieldAbstract;
	}

	public void setFieldAbstract(String fieldAbstract) {
		this.fieldAbstract = fieldAbstract;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		EventointEntity that = (EventointEntity) o;
		return idEvento == that.idEvento &&
						Objects.equals(idLingua, that.idLingua) &&
						Objects.equals(nome, that.nome) &&
						Objects.equals(descrizione, that.descrizione) &&
						Objects.equals(fieldAbstract, that.fieldAbstract);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idEvento, idLingua, nome, descrizione, fieldAbstract);
	}

}
