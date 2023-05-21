package it.indra.SigecAPI;
import javax.jms.ConnectionFactory;
import javax.jms.Session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableJms
@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class })
@EnableScheduling
@EnableAsync
public class SigecAPIApplication {
	
	@Bean("jms-template-queue")
	public JmsTemplate createJmsTemplateQueue(ConnectionFactory connectionFactory) {
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(connectionFactory);
		jmsTemplate.setPubSubDomain(Boolean.FALSE);

		return jmsTemplate;
	}

	@Bean("jms-template-topic")
	public JmsTemplate createJmsTemplateTopic(ConnectionFactory connectionFactory) {
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(connectionFactory);
		jmsTemplate.setPubSubDomain(Boolean.TRUE);
		jmsTemplate.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);

		return jmsTemplate;
	}

	public static void main(String[] args) {
		System.setProperty("java.awt.headless", "true");
		SpringApplication.run(SigecAPIApplication.class, args);
	}

}
