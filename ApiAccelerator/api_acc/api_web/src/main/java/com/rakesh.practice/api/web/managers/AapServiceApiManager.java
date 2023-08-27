package com.rakesh.practice.api.web.managers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.rakesh.practice.api.commons.model.CanonicalAPIProxyModel;

public interface ApiServiceApiManager {

	public String invokeRemoteService(String remoteServiceURL, CanonicalAPIProxyModel proxyModel) throws JsonMappingException, JsonProcessingException;
}
