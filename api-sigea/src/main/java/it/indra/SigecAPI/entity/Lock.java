package it.indra.SigecAPI.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.UpdateTimestamp;
import org.javers.core.metamodel.annotation.DiffIgnore;

import lombok.Data;

@Entity
@Table(name="t_LOCK",schema="sigec", uniqueConstraints={@UniqueConstraint(columnNames={"ITEM_ID", "NOME_TABELLA"}, name="t_lock_unique")})
@Data
public class Lock {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sigec.LOCK_SEQ")
	@SequenceGenerator(name = "sigec.LOCK_SEQ", sequenceName = "sigec.LOCK_SEQ", allocationSize = 1, initialValue = 1)
	@Column(name = "LOCK_ID")
	private Long lockId;
	
	@Column(name = "ITEM_ID")
	private Long itemId;
	
	@Column(name = "NOME_TABELLA")
	private String tableName;
	
	@Column(name="OWNER_ID")
	private Long ownerId;
	
	@Column(name="DATA_LOCK")
	@UpdateTimestamp
	private Timestamp dataLock;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="OWNER_ID", referencedColumnName="UTENTE_ID", updatable = false, insertable = false)
	@DiffIgnore
	private DettaglioUtente owner;
}
