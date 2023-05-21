package it.indra.SigecAPI.converter;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Converter
public class MapToStringConverter implements AttributeConverter<Map<String,String>, String>{

	@Override
	public String convertToDatabaseColumn(Map<String, String> attribute) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		
		String json = null;
		try {
			json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(attribute);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> convertToEntityAttribute(String dbData) {
		ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        
        Map <String,String> map = new HashMap <String,String> ();
        try {
        	map = mapper.readValue(dbData, Map.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return map;
	}


}
