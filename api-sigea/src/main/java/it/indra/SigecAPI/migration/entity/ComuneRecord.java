package it.indra.SigecAPI.migration.entity;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ComuneRecord implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String regionCode;
	private String regionName;
	private String provinceCode;
	private String provinceName;
	private String provincePlate;
	private String territorialAreaCode;
	private String territorialAreaName;
	private String territorialAreaNameEng;
	private String municipalityCode;
	private String municipalityName;
	private String codiceBelfiore;
	private List<Integer> validFrom;
	private List<Integer> validTo;
}
