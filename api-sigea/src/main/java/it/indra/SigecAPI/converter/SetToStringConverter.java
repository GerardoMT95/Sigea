package it.indra.SigecAPI.converter;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Converter
public class SetToStringConverter implements AttributeConverter<Set<String>, String>{

	@Override
	public String convertToDatabaseColumn(Set<String> attribute) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		
		String json = null;
		try {
			json = mapper.writeValueAsString(attribute);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> convertToEntityAttribute(String dbData) {
		ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        
        Set < String > cadenza = new HashSet <String> ();
        try {
        	cadenza = mapper.readValue(dbData, Set.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return cadenza;
	}

}
