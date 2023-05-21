package it.indra.SigecAPI.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.TypeDef;

import it.indra.SigecAPI.extjpa.JsonDataUserType;
import lombok.Data;

@Entity
@Table(name = "t_SIGEA_CACHE_TOKEN", schema = "sigec")
@Data
public class CacheToken implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CLIENT_ID", length = 20)
	private String clientId;

	@Column(name = "ACCESS_TOKEN", length = 20)
	private String accessToken;

	@Column(name = "EXPIRES_IN", length = 20)
	private Integer expiresIn;

	@Column(name = "CREATION_DATE", length = 20)
	private LocalDateTime creationDate;

	public boolean isValid() {

		LocalDateTime now = LocalDateTime.now();
		LocalDateTime expired = creationDate.plusSeconds(expiresIn).minusMinutes(5);

		if (now.isAfter(expired)) {
			return false;
		} else {
			return true;
		}
	}

}
