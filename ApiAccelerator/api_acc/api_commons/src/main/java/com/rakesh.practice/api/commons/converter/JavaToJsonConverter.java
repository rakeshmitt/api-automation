package com.rakesh.practice.api.commons.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JavaToJsonConverter {
	
	ObjectMapper mapper = new ObjectMapper();

	
	public String convertToJson(Object javaObj) {
		String jsonInString = null;

		try {
			jsonInString = mapper.writeValueAsString(javaObj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return jsonInString;
	}
	
}
