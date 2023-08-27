package com.rakesh.practice.api.aws.json.model;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "patchOperations" })
public class AwsApiPolicy {

	@JsonProperty("patchOperations")
	private List<AwsPatchOperation> patchOperations = null;
	
	public List<AwsPatchOperation> getPatchOperations() {
		return patchOperations;
	}

	public void setPatchOperations(List<AwsPatchOperation> patchOperations) {
		this.patchOperations = patchOperations;
	}

}