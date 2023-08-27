package com.rakesh.practice.api.aws.json.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"stageName",
"stageDescription",
"description",
"cacheClusterEnabled",
"cacheClusterSize",
"variables"
})
public class AwsResponseCache {

	@JsonProperty("stageName")
	private String stageName;
	@JsonProperty("stageDescription")
	private String stageDescription;
	@JsonProperty("description")
	private String description;
	@JsonProperty("cacheClusterEnabled")
	private String cacheClusterEnabled;
	@JsonProperty("cacheClusterSize")
	private String cacheClusterSize;
	@JsonProperty("variables")
	private AwsVariables variables;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("stageName")
	public String getStageName() {
	return stageName;
	}

	@JsonProperty("stageName")
	public void setStageName(String stageName) {
	this.stageName = stageName;
	}

	@JsonProperty("stageDescription")
	public String getStageDescription() {
	return stageDescription;
	}

	@JsonProperty("stageDescription")
	public void setStageDescription(String stageDescription) {
	this.stageDescription = stageDescription;
	}

	@JsonProperty("description")
	public String getDescription() {
	return description;
	}

	@JsonProperty("description")
	public void setDescription(String description) {
	this.description = description;
	}

	@JsonProperty("cacheClusterEnabled")
	public String getCacheClusterEnabled() {
	return cacheClusterEnabled;
	}

	@JsonProperty("cacheClusterEnabled")
	public void setCacheClusterEnabled(String cacheClusterEnabled) {
	this.cacheClusterEnabled = cacheClusterEnabled;
	}

	@JsonProperty("cacheClusterSize")
	public String getCacheClusterSize() {
	return cacheClusterSize;
	}

	@JsonProperty("cacheClusterSize")
	public void setCacheClusterSize(String cacheClusterSize) {
	this.cacheClusterSize = cacheClusterSize;
	}

	@JsonProperty("variables")
	public AwsVariables getVariables() {
	return variables;
	}

	@JsonProperty("variables")
	public void setVariables(AwsVariables variables) {
	this.variables = variables;
	}

		
}
