package it.indra.SigeaWeb.utilities;

import java.io.File;
import java.util.Properties;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.ConfigurationConverter;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;

public class GeneralProperties
{
	private static Properties externalProperties = new Properties();
	
	public GeneralProperties()
	{
		try{
			File configDir = new File(System.getProperty("catalina.base"), "conf");
			File configFile = new File(configDir, "SigeaWeb.properties");
			
			Parameters params = new Parameters();
			FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
			    new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
			    .configure(params.properties().setFile(configFile));
			
			 Configuration config = builder.getConfiguration();
			
			//modifica dovuta a apache commons configuration per permettere interpolazione env variable
			/*externalProperties = new Properties();
			InputStream stream = new FileInputStream(configFile);
			externalProperties.load(stream);*/
			 
			 externalProperties = ConfigurationConverter.getProperties(config);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String getLogin() {
		return externalProperties.getProperty("url.login");
	}
	
	public String getUrldms() {
		return externalProperties.getProperty("url.dms");
	}
	
	public String getLogout() {
		return externalProperties.getProperty("url.logout");
	}
	
	/**
	 * Metodo per lettura URL base per le API di SIGEC.
	 * @return URL base per le API di SIGEC.
	 */
	public String getUrlSIGEC()
	{
		return externalProperties.getProperty("urlSIGEC");
	}
	
	/**
	 * Metodo per lettura URL base per le API di BANDI.
	 * @return URL base per le API di BANDI.
	 */
	public String getUrlBandiServices()
	{
		return externalProperties.getProperty("urlBandiServices");
	}
	
	/**
	 * Metodo per lettura URL base per le API di SIGEC.
	 * @return URL base per le API di SIGEC.
	 */
	public String getUrlTerritorial()
	{
		return externalProperties.getProperty("urlTerritorialServices");
	}
	
	/**
	 * Metodo per lettura URL base per le API del DMS.
	 * @return URL base per le API del DMS.
	 */
	public String getUrlDmsServices()
	{
		return externalProperties.getProperty("urlDmsServices");
	}
	
	/**
	 * Metodo per lettura URL base per le API di VIP.
	 * @return URL base per le API di VIP.
	 */
	public String getUrlVipServices()
	{
		return externalProperties.getProperty("urlVipServices");
	}

	public boolean getAttrattoriSigeo() {
		boolean attrattoriFromSigeo = false;
		try 
		{
			String prop = externalProperties.getProperty("provenienzaAttrattori");
			if(prop.equalsIgnoreCase("VIP")) 
			{
				attrattoriFromSigeo = false;
			}else if(prop.equalsIgnoreCase("SIGEO"))
			{
				attrattoriFromSigeo=true;
			}
		}catch (Exception e) {
			return false;
		}
		return attrattoriFromSigeo;
	}

	public String getUrlSigeoServices() {
		return externalProperties.getProperty("urlSigeoServices");
		
	}
}
