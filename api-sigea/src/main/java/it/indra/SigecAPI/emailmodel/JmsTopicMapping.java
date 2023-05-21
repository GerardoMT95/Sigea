package it.indra.SigecAPI.emailmodel;

import java.io.Serializable;


public class JmsTopicMapping implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String topicName;
	private String payload;
	
	public String getTopicName() {
		return topicName;
	}
	
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	
	public String getPayload() {
		return payload;
	}
	
	public void setPayload(String payload) {
		this.payload = payload;
	}
	
}
