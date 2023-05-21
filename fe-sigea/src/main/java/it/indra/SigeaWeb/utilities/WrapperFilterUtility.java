package it.indra.SigeaWeb.utilities;

import java.io.IOException;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.URLCodec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class WrapperFilterUtility {
	
	private WrapperFilterUtility(){};
	
	public static <T> WrapperFilterRequest<T> extractWrapperFromCodedString(String codedString, Class<T> filterClass) throws DecoderException, InstantiationException, IllegalAccessException, IOException{
		URLCodec codec = new URLCodec();
		String requestFilterString = codec.decode(codedString);
		ObjectMapper mapper = new ObjectMapper();
		JavaType type = mapper.getTypeFactory().constructParametricType(WrapperFilterRequest.class, filterClass);
		WrapperFilterRequest<T> wrapperFilter = mapper.readValue(requestFilterString, type);
		if (filterClass != null && wrapperFilter.getFilter() == null) {
			wrapperFilter.setFilter(filterClass.newInstance());
		}
		return wrapperFilter;
	}
	
	public static <T> String encodeWrapper(WrapperFilterRequest<T> wrapper) throws JsonProcessingException, EncoderException {
		ObjectMapper mapper = new ObjectMapper();
		URLCodec codec = new URLCodec();
		return codec.encode(mapper.writeValueAsString(wrapper));
	}

}
