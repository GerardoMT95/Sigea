package it.indra.SigeaWeb.config;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.CacheControl;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import it.indra.SigeaWeb.utilities.GeneralProperties;
import it.indra.SigeaWeb.utilities.SigeaPropertiesSettings;
import it.puglia.spc.auth.openid.OpenIDConnectSettings;

@Configuration
@EnableWebMvc
@EnableScheduling
//@Import(RestClientConfig.class)
@ComponentScan(basePackages = {"it.indra.SigeaWeb", "es.indra", "com.indra.es", "it.puglia.spc.auth"})
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Autowired
	private SigeaPropertiesSettings sigeaPropertiesSettings;
	
	@Bean
	public OpenIDConnectSettings getOpenIDConnectSettings() {
		
		OpenIDConnectSettings oauthSettings = new OpenIDConnectSettings();
		oauthSettings.setAccessTokenUri(sigeaPropertiesSettings.getOidcAccessTokenUri());
		oauthSettings.setCliendSecret(sigeaPropertiesSettings.getOidcClientSecret());
		oauthSettings.setClientId(sigeaPropertiesSettings.getOidcClientId());
		oauthSettings.setDisableSsl(sigeaPropertiesSettings.getOidcDisableSsl());
		oauthSettings.setIssuer(sigeaPropertiesSettings.getOidcIssuer());
		oauthSettings.setLoginUrl(sigeaPropertiesSettings.getOidcLoginUrl());
		oauthSettings.setScopes(sigeaPropertiesSettings.getOidcScopes());
		oauthSettings.setUserAuthorizationUri(sigeaPropertiesSettings.getOidcUserAuthorizationUri());
		oauthSettings.setJwksUri(sigeaPropertiesSettings.getOidcJwksUri());
		oauthSettings.setRedirectUri(sigeaPropertiesSettings.getOidcRedirectUri());

		return oauthSettings;
	}
	
	@Bean
	public ViewResolver viewResolver() {

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setExposeContextBeansAsAttributes(true);
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean(name = "multipartResolver")
	public StandardServletMultipartResolver resolver() {
		return new StandardServletMultipartResolver();
	}

	
/*
	In the addResourceHandler method, you specify URL, or URL pattern.
	In the addResourceLocations, you specify a valid directory actually containing (or possibly many directories separated by commas) the static resources.
*/
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/assets/js/sigeajs/**").addResourceLocations("/assets/js/sigeajs/").setCachePeriod(0);
		registry.addResourceHandler("/assets/js/**/summernote-ext-maxlength.js").addResourceLocations("/assets/js/").setCachePeriod(0);
		registry.addResourceHandler("/assets/**").addResourceLocations("/assets/").setCacheControl(CacheControl.maxAge(1, TimeUnit.DAYS));
	}
	
	

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(new Locale("it"));
		return localeResolver;
	}

	@Bean
	public MessageSource messageSource() {
		//TODO comprendere necessit-
//		final ReloadableResourceBundleMessageSource messageSource = new DatabaseMessageSource(false);
		final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public MessageSource messageSourceBuild() {
		final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:build");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer matcher) {
		matcher.setUseRegisteredSuffixPatternMatch(true);
	}
	
//	@Bean
//	public WSO2OpenIDConnectSettings getSettings() throws ConfigurationException {
//		File configDir = new File(System.getProperty("catalina.base"), "conf");
//		File configFile = new File(configDir, "SigeaWeb.properties");
//		
//		Parameters params = new Parameters();
//		FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
//				new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
//				.configure(params.properties().setFile(configFile));
//		
//		org.apache.commons.configuration2.Configuration config = builder.getConfiguration();
//		
//		Properties externalProperties = new Properties();
//		externalProperties = ConfigurationConverter.getProperties(config);
//		return new WSO2OpenIDConnectSettings(externalProperties);
//	}
	
	@Bean
	public GeneralProperties getGeneralProperies() {
		return new GeneralProperties();
	}
	
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setForceEncoding(true);
		characterEncodingFilter.setEncoding("UTF-8");
		registrationBean.setFilter(characterEncodingFilter);
		return registrationBean;
	}

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(20848820);
		return multipartResolver;
	}


	@Bean
	public RestTemplate defaultRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		return restTemplate;
	}
	
	
//	@Bean(name = "multipartRestTemplate")
//	public RestTemplate defaultMultipartRestTemplate() {
//		RestTemplate restTemplate = new RestTemplate();
//		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
//		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
//		return restTemplate;
//	}
}