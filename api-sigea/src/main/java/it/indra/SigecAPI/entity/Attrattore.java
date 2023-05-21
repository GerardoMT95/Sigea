package it.indra.SigecAPI.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.javers.core.metamodel.annotation.DiffIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="t_SIGEA_ATTRATTORI",schema="sigec")
@Data
@EqualsAndHashCode(exclude="locationSet")
public class Attrattore implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ATTRATTORE_ID")
	private Long attrattoreId;
	
	@Column(name="ETICHETTA", nullable=false, length=300)
	private String etichetta;
	
	@ManyToMany(mappedBy="attrattoriSet")
	@DiffIgnore
	private Set<Location> locationSet = new HashSet<>();
}
