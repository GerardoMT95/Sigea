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
@Table(name = "ACCESSIBILITAMAPPING", schema = "TURISMO", catalog = "")
public class AccessibilitamappingEntity {
	private long id;
	private Long iditinerario;
	private Long idevento;
	private Long idattivita;
	private Long idtappainterregionale;

//	private AccessibilitaEntity accessibilitaById;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCESSIBILITAMAPP_SEQ")
	@SequenceGenerator(schema = "TURISMO", sequenceName = "ACCESSIBILITAMAPP_SEQ", allocationSize = 1, name = "ACCESSIBILITAMAPP_SEQ")
	@Column(name = "ID")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Basic
	@Column(name = "IDITINERARIO")
	public Long getIditinerario() {
		return iditinerario;
	}

	public void setIditinerario(Long iditinerario) {
		this.iditinerario = iditinerario;
	}

	@Basic
	@Column(name = "IDEVENTO")
	public Long getIdevento() {
		return idevento;
	}

	public void setIdevento(Long idevento) {
		this.idevento = idevento;
	}

	@Basic
	@Column(name = "IDATTIVITA")
	public Long getIdattivita() {
		return idattivita;
	}

	public void setIdattivita(Long idattivita) {
		this.idattivita = idattivita;
	}
	
	@Basic
	@Column(name = "IDTAPPAINTERREGIONALE")
	public Long getIdtappainterregionale() {
		return idtappainterregionale;
	}

	public void setIdtappainterregionale(Long idtappainterregionale) {
		this.idtappainterregionale = idtappainterregionale;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AccessibilitamappingEntity that = (AccessibilitamappingEntity) o;
		return id == that.id &&
						Objects.equals(iditinerario, that.iditinerario) &&
						Objects.equals(idevento, that.idevento) &&
						Objects.equals(idattivita, that.idattivita) &&
						Objects.equals(idtappainterregionale, that.idtappainterregionale);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, iditinerario, idevento, idattivita, idtappainterregionale);
	}


//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "PK_IDMAPPING")
//	public AccessibilitaEntity getAccessibilitaById() {
//		return accessibilitaById;
//	}
//
//	public void setAccessibilitaById(AccessibilitaEntity accessibilitaById) {
//		this.accessibilitaById = accessibilitaById;
//	}
}
