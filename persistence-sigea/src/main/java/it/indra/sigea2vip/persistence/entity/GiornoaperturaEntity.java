package it.indra.sigea2vip.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "GIORNOAPERTURA", schema = "TURISMO")
public class GiornoaperturaEntity {
	
	private Long idgiornoapertura;
	private Long idorario;
	private Long idcadenza;
	private String idlingua;
	private Long idgiornosettimana;
	private Long specificogiorno;
	private Long specificomese;
	private Long genericoordinale;
	private Long genericogiorno;
	private Long genericoperiodo;
	private Long  genericomese;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GIORNOAPERTURA_SEQ")
	@SequenceGenerator(schema = "TURISMO", sequenceName = "GIORNOAPERTURA_SEQ", allocationSize = 1, name = "GIORNOAPERTURA_SEQ")
	@Column(name = "IDGIORNOAPERTURA")
	public Long getIdgiornoapertura() {
		return idgiornoapertura;
	}
	
	@Column(name = "IDORARIO")
	public Long getIdorario() {
		return idorario;
	}
	
	@Column(name = "IDCADENZA")
	public Long getIdcadenza() {
		return idcadenza;
	}
	
	@Column(name = "IDLINGUA")
	public String getIdlingua() {
		return idlingua;
	}
	
	@Column(name = "IDGIORNOSETTIMANA")
	public Long getIdgiornosettimana() {
		return idgiornosettimana;
	}
	
	@Column(name = "SPECIFICOGIORNO")
	public Long getSpecificogiorno() {
		return specificogiorno;
	}
	
	@Column(name = "SPECIFICOMESE")
	public Long getSpecificomese() {
		return specificomese;
	}
	
	@Column(name = "GENERICOORDINALE")
	public Long getGenericoordinale() {
		return genericoordinale;
	}
	
	@Column(name = "GENERICOGIORNO")
	public Long getGenericogiorno() {
		return genericogiorno;
	}
	
	@Column(name = "GENERICOPERIODO")
	public Long getGenericoperiodo() {
		return genericoperiodo;
	}
	
	@Column(name = "GENERICOMESE")
	public Long getGenericomese() {
		return genericomese;
	}
	
	public void setIdgiornoapertura(Long idgiornoapertura) {
		this.idgiornoapertura = idgiornoapertura;
	}
	public void setIdorario(Long idorario) {
		this.idorario = idorario;
	}
	public void setIdcadenza(Long idcadenza) {
		this.idcadenza = idcadenza;
	}
	public void setIdlingua(String idlingua) {
		this.idlingua = idlingua;
	}
	public void setIdgiornosettimana(Long idgiornosettimana) {
		this.idgiornosettimana = idgiornosettimana;
	}
	public void setSpecificogiorno(Long specificogiorno) {
		this.specificogiorno = specificogiorno;
	}
	public void setSpecificomese(Long specificomese) {
		this.specificomese = specificomese;
	}
	public void setGenericoordinale(Long genericoordinale) {
		this.genericoordinale = genericoordinale;
	}
	public void setGenericogiorno(Long genericogiorno) {
		this.genericogiorno = genericogiorno;
	}
	public void setGenericoperiodo(Long genericoperiodo) {
		this.genericoperiodo = genericoperiodo;
	}
	public void setGenericomese(Long genericomese) {
		this.genericomese = genericomese;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		GiornoaperturaEntity that = (GiornoaperturaEntity) o;
		return idgiornoapertura == that.idgiornoapertura ;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idgiornoapertura);
	}


}
