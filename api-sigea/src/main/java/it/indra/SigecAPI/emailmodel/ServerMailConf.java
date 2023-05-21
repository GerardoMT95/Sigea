package it.indra.SigecAPI.emailmodel;

import java.io.Serializable;




public class ServerMailConf implements Serializable {


	private static final long serialVersionUID = 3138565255619760052L;
	private String id;
	private String mailSmtpHost;
	private Integer mailSmtpPort;
	private String mailSmtpUsername;
	private String mailSmtpPassword;
	private String mailSmtpProtocol;
	private String mailSmtpDefaultEncoding;
	private boolean mailSmtpSsl;
	private boolean mailSmtpAuth;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getMailSmtpHost() {
		return mailSmtpHost;
	}
	public void setMailSmtpHost(String mailSmtpHost) {
		this.mailSmtpHost = mailSmtpHost;
	}
	public Integer getMailSmtpPort() {
		return mailSmtpPort;
	}
	public void setMailSmtpPort(Integer mailSmtpPort) {
		this.mailSmtpPort = mailSmtpPort;
	}
	public String getMailSmtpUsername() {
		return mailSmtpUsername;
	}
	public void setMailSmtpUsername(String mailSmtpUsername) {
		this.mailSmtpUsername = mailSmtpUsername;
	}
	public String getMailSmtpPassword() {
		return mailSmtpPassword;
	}
	public void setMailSmtpPassword(String mailSmtpPassword) {
		this.mailSmtpPassword = mailSmtpPassword;
	}
	public String getMailSmtpProtocol() {
		return mailSmtpProtocol;
	}
	public void setMailSmtpProtocol(String mailSmtpProtocol) {
		this.mailSmtpProtocol = mailSmtpProtocol;
	}
	public String getMailSmtpDefaultEncoding() {
		return mailSmtpDefaultEncoding;
	}
	public void setMailSmtpDefaultEncoding(String mailSmtpDefaultEncoding) {
		this.mailSmtpDefaultEncoding = mailSmtpDefaultEncoding;
	}
	public boolean isMailSmtpSsl() {
		return mailSmtpSsl;
	}
	public void setMailSmtpSsl(boolean mailSmtpSsl) {
		this.mailSmtpSsl = mailSmtpSsl;
	}
	public boolean isMailSmtpAuth() {
		return mailSmtpAuth;
	}
	public void setMailSmtpAuth(boolean mailSmtpAuth) {
		this.mailSmtpAuth = mailSmtpAuth;
	}
	
}
