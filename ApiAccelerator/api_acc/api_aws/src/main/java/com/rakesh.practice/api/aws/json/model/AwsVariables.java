package com.rakesh.practice.api.aws.json.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "sv1" })
public class AwsVariables {

	@JsonProperty("sv1")
	private String sv1;

	@JsonProperty("sv1")
	public String getSv1() {
		return sv1;
	}

	@JsonProperty("sv1")
	public void setSv1(String sv1) {
		this.sv1 = sv1;
	}

}