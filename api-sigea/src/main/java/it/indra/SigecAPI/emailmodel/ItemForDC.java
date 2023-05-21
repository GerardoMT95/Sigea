package it.indra.SigecAPI.emailmodel;

import java.io.Serializable;

public class ItemForDC implements Serializable{

	private static final long serialVersionUID = -7391598003349521566L;

	//tenant della risorsa
	private String tenantId;
	//id del template caricato
	private String engineId;
	//tipo della risorsa se mustacke o acroform
	private String engine;
	//content per i diversi formati che può essere base64->xml per acroform o base64->json per mustake
	private String content;

	public ItemForDC(String tenantId, String engineId, String engine, String content) {
		super();
		this.tenantId = tenantId;
		this.engineId = engineId;
		this.engine = engine;
		this.content = content;
	}

	public ItemForDC() {
		super();
	}

	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getEngineId() {
		return engineId;
	}
	public void setEngineId(String engineId) {
		this.engineId = engineId;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}


}
