package it.indra.SigeaWeb.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;



public class SpringMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{
	private static final String LOCATION = "";
	private static final long MAX_FILE_SIZE = 1024 * 1024 * 100;
	private static final long MAX_REQUEST_SIZE = 1024 * 1024 * 100;
	private static final int FILE_SIZE_THRESHOLD = 0;
	
	@SuppressWarnings("unused")
	private String activeProfile = "http";

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { WebMvcConfig.class };
	} 

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		registration.setMultipartConfig(getMultipartConfigElement());
		registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
	}
	
	private MultipartConfigElement getMultipartConfigElement() {
		MultipartConfigElement multipartConfigElement = new MultipartConfigElement(LOCATION, MAX_FILE_SIZE,
				MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
		return multipartConfigElement;
	}
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		super.onStartup(servletContext);
		servletContext.addListener(new SessionListener());
		servletContext.addListener(new RequestContextListener());
	}
}
