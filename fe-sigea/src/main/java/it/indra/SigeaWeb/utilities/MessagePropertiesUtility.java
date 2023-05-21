package it.indra.SigeaWeb.utilities;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessagePropertiesUtility {

	public static Map<String,String> getPropertiesMap(){
		Properties properties;
		Resource resource = new ClassPathResource("/messages_it.properties");
		try {
			properties = PropertiesLoaderUtils.loadProperties(resource);
	    	Map<String,String> propMap = new HashMap<String,String>();
	        Set<Object> keys = properties.keySet();
	        for(Object k:keys){
	            String key = (String)k;
	            if (key.startsWith("js.")) {
	            	propMap.put("\"" +key+ "\"", "\"" +properties.getProperty(key)+ "\"");
	            }
	        }
	        return propMap;
		} catch (IOException ex) {
			log.error(ex.getMessage());
			return null;
		}
    }

}