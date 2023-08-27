package com.rakesh.practice.api.web.managers;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.rakesh.practice.api.commons.model.CanonicalAPIProxyModel;

@Component
public class ApiProductServiceApiManager implements ApiServiceApiManager{
	  
	@Autowired
	RestTemplateManager restTemplate;
	
	public String invokeRemoteService(String remoteServiceURL, CanonicalAPIProxyModel proxyModel) throws JsonMappingException, JsonProcessingException {
		HttpHeaders headers = new HttpHeaders();

		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.set("Content-Type", "application/json");

		headers.set("Accept", "*/*");
		headers.setCacheControl("no-cache");
		headers.set("Accept-Encoding", "gzip, deflate, br");
		headers.set("Connection", "keep-alive");

		HttpEntity<CanonicalAPIProxyModel> request = new HttpEntity<CanonicalAPIProxyModel>(proxyModel, headers);
		
		ResponseEntity<String> responseString = restTemplate.restTemplate().exchange(remoteServiceURL, HttpMethod.POST, 
				request, String.class);
		
		return responseString.getBody();

	}
	  
}
