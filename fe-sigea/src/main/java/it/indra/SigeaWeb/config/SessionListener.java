package it.indra.SigeaWeb.config;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SessionListener implements HttpSessionListener {
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		
		log.info("SIGEA session created");
		event.getSession().setMaxInactiveInterval(6 * 60 * 60);//6 ore
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		
		log.info("SIGEA session destroyed");
	}
}