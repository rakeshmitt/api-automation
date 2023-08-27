package com.rakesh.practice.api.aws.json.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "stage", "apiId" })
public class AwsApiStage {

	@JsonProperty("stage")
	private String stage;
	@JsonProperty("apiId")
	private String apiId;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("stage")
	public String getStage() {
		return stage;
	}

	@JsonProperty("stage")
	public void setStage(String stage) {
		this.stage = stage;
	}

	@JsonProperty("apiId")
	public String getApiId() {
		return apiId;
	}

	@JsonProperty("apiId")
	public void setApiId(String apiId) {
		this.apiId = apiId;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}