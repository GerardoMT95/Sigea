package it.indra.SigeaWeb.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import com.indra.es.utils.auth.provider.OpenIdAuthenticationProvider;
import com.indra.es.utils.constants.SharedUrl;

import it.indra.SigeaWeb.utilities.GeneralProperties;
import it.indra.SigeaWeb.utilities.SigeaPropertiesSettings;
import it.puglia.spc.auth.openid.OpenIDConnectFilter;
import it.puglia.spc.auth.openid.OpenIDConnectSettings;

@Configuration
@EnableWebSecurity
//@EnableOAuth2Client
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
//	@Autowired
//	private WSO2OpenIDConnectSettings settings;
//	
//	@Autowired
//	private GeneralProperties properties;
//	
//	@Autowired
//	private OAuth2ClientContext clientContext;
	
	@Autowired
	private OpenIdAuthenticationProvider openIdAuthenticationProvider;
	
//	@Autowired
//	private GeneralProperties properties;

	@Autowired
	private SigeaPropertiesSettings sigeaPropertiesSettings;
	
	@Autowired
	private OpenIDConnectSettings settings;

	@Autowired
	private OAuth2RestTemplate restTemplate;
	
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(openIdAuthenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
//		LoginUrlAuthenticationEntryPoint entryPoint = new LoginUrlAuthenticationEntryPoint(settings.getLoginUrl());
//		entryPoint.setForceHttps(!settings.getDisableSsl());
		
		SharedUrl.URL_API_USER=sigeaPropertiesSettings.getUrlUtentiServices();
		SharedUrl.URL_TOKEN=sigeaPropertiesSettings.getOidcAccessTokenUri();
		
		httpSecurity.addFilterAfter(new OAuth2ClientContextFilter(), AbstractPreAuthenticatedProcessingFilter.class)
		.addFilterAfter(new OpenIDConnectFilter(settings).restTemplate(restTemplate),
				OAuth2ClientContextFilter.class)
		
		
		
//		.addFilterAfter(new OAuth2ClientContextFilter(), AbstractPreAuthenticatedProcessingFilter.class)
//		.addFilterAfter(new RedirectFilter(clientContext, settings), OAuth2ClientContextFilter.class)
//		.addFilterAfter(new OpenIDConnectClientFilter(settings),RedirectFilter.class)
//		.httpBasic().authenticationEntryPoint(entryPoint).and()
		
		.authorizeRequests()
//		.antMatchers("/accedi").permitAll()
		.antMatchers("/oidc/callback").permitAll()
		.antMatchers("/segnalazione/accedi").permitAll()
		.antMatchers("/gestione/accedi").permitAll()
		.antMatchers("/promozione/accedi").permitAll()
		.antMatchers("/errore").permitAll()
		.antMatchers("/doLogin").permitAll()
		.antMatchers("/accessonegato").permitAll()
		.antMatchers("/denied").permitAll()
		.anyRequest().authenticated()
		.and()
		.exceptionHandling()
		.accessDeniedHandler((request, response, accessDeniedException) -> { 
			response.sendError(HttpServletResponse.SC_FORBIDDEN); 
		})
		.authenticationEntryPoint((request, response, authException) -> {
			String ajaxHeader = ((HttpServletRequest) request).getHeader("X-Requested-With");
			if (!"XMLHttpRequest".equals(ajaxHeader)) {
				response.sendRedirect(sigeaPropertiesSettings.getLogin());
			}
			else {
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
			}
		}).accessDeniedPage("/denied").and().csrf().disable().logout();
	}

}
