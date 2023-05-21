package it.indra.SigeaWeb.utilities;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@PropertySource("file:${catalina.base}/conf/SigeaWeb.properties")
@Getter
public class SigeaPropertiesSettings {

	@Value("${url.login}")
	private String login;
	@Value("${url.dms}")
	private String urlDms;
	@Value("${url.logout}")
	private String logout;
	
	// API
	@Value("${urlSIGEC}")
	private String urlSIGEC;
	@Value("${urlTerritorialServices}")
	private String urlTerritorial;
	@Value("${urlDmsServices}")
	private String urlDmsServices;
	@Value("${urlUtentiServices}")
	private String urlUtentiServices;
	@Value("${urlVipServices}")
	private String urlVipServices;	
	@Value("${urlBandiServices}")
	private String urlBandiServices;	

	// WSO2
	@Value("${WSO2.defaultApplication_clientId}")
	private String wso2ClientId;
	@Value("${WSO2.defaultApplication_clientSecret}")
	private String wso2ClientSecret;
	
	//OIDC
	@Value("${application.auth.openidconnect.loginUrl}")
	private String oidcLoginUrl;
	@Value("${application.auth.openidconnect.clientId}")
	private String oidcClientId;
	@Value("${application.auth.openidconnect.clientSecret}")
	private String oidcClientSecret;
	@Value("${application.auth.openidconnect.accessTokenUri}")
	private String oidcAccessTokenUri;
	@Value("${application.auth.openidconnect.userAuthorizationUri}")
	private String oidcUserAuthorizationUri;
	@Value("${application.auth.openidconnect.issuer}")
	private String oidcIssuer;
	@Value("${application.auth.openidconnect.jwksUri}")
	private String oidcJwksUri;
	@Value("${application.auth.openidconnect.redirectUri}")
	private String oidcRedirectUri;
	@Value("#{'${application.auth.openidconnect.scopes}'.split(',')}")
	private List<String> oidcScopes;
	@Value("${application.auth.openidconnect.disableSsl}")
	private Boolean oidcDisableSsl;
	@Value("${application.auth.openidconnect.urlUserDetailsProvider}")
	private String urlUserDetails;

	
	
}
