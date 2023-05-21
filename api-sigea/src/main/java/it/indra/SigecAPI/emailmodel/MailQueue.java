package it.indra.SigecAPI.emailmodel;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.MimeMessageHelper;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class MailQueue implements Serializable{

	private static final long serialVersionUID = -3534848005838847577L;

	private String id;

	private String from;
	private String[] to;
	private String[] cc;
	private String[] bcc;
	
	
	
	private Map<String, String> params;
	private List<ItemForDC> attachments;
	private ItemForDC content;
	private String subject;
	private String templateName;
	private ServerMailConf serverMailConf;
	
	/**
	 * permette di inviare un unico message per tutti gli indirizzi in A
	 * da implementare
	 */
	private boolean singleMessageForTo;
	
	private MimeMessage message;
	private MimeMessageHelper messageHelper;
	private String subjectMapped;
	private String bodyRendered;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Map<String, String> getParams() {
		return params;
	}
	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public String[] getTo() {
		return to;
	}
	public void setTo(String[] to) {
		this.to = to;
	}
	public String[] getCc() {
		return cc;
	}
	public void setCc(String[] cc) {
		this.cc = cc;
	}
	public String[] getBcc() {
		return bcc;
	}
	public void setBcc(String[] bcc) {
		this.bcc = bcc;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	
	public MimeMessage getMessage() {
		return message;
	}
	public void setMessage(MimeMessage message) {
		this.message = message;
	}
	public MimeMessageHelper getMessageHelper() {
		return messageHelper;
	}
	@JsonIgnore
	public void setMessageHelper(MimeMessageHelper messageHelper) {
		this.messageHelper = messageHelper;
	}
	public String getSubjectMapped() {
		return subjectMapped;
	}
	public void setSubjectMapped(String subjectMapped) {
		this.subjectMapped = subjectMapped;
	}
	public String getBodyRendered() {
		return bodyRendered;
	}
	public void setBodyRendered(String bodyRendered) {
		this.bodyRendered = bodyRendered;
	}
	
	
	
	public String getFrom() {
		return from;
	}
	public ServerMailConf getServerMailConf() {
		return serverMailConf;
	}
	public void setServerMailConf(ServerMailConf serverMailConf) {
		this.serverMailConf = serverMailConf;
	}

	
	public List<ItemForDC> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<ItemForDC> attachments) {
		this.attachments = attachments;
	}
	public ItemForDC getContent() {
		return content;
	}
	public void setContent(ItemForDC content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "MailQueue [id=" + id + ", from=" + from + ", to=" + Arrays.toString(to) + ", cc=" + Arrays.toString(cc)
				+ ", bcc=" + Arrays.toString(bcc) + ", params=" + params + ", attachments=" + attachments + ", content="
				+ content + ", subject=" + subject + ", templateName=" + templateName + ", serverMailConf="
				+ serverMailConf + ", singleMessageForTo=" + singleMessageForTo + ", message=" + message
				+ ", messageHelper=" + messageHelper + ", subjectMapped=" + subjectMapped + ", bodyRendered="
				+ bodyRendered + "]";
	}
	
	public boolean isSingleMessageForTo() {
		return singleMessageForTo;
	}
	
	public void setSingleMessageForTo(boolean singleMessageForTo) {
		this.singleMessageForTo = singleMessageForTo;
	}
	
	
	


	
	
	
}
