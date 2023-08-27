package com.rakesh.practice.api.aws.json.model;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "name", "description", "apiStages", "throttle", "quota" })
public class AwsThrottlingModel {

	@JsonProperty("name")
	private String name;
	@JsonProperty("description")
	private String description;
	@JsonProperty("apiStages")
	private List<AwsApiStage> apiStages = null;
	@JsonProperty("throttle")
	private AwsThrottle throttle;
	@JsonProperty("quota")
	private AwsQuota quota;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty("apiStages")
	public List<AwsApiStage> getApiStages() {
		return apiStages;
	}

	@JsonProperty("apiStages")
	public void setApiStages(List<AwsApiStage> apiStages) {
		this.apiStages = apiStages;
	}

	@JsonProperty("throttle")
	public AwsThrottle getThrottle() {
		return throttle;
	}

	@JsonProperty("throttle")
	public void setThrottle(AwsThrottle throttle) {
		this.throttle = throttle;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	/**
	 * @return the quota
	 */
	public AwsQuota getQuota() {
		return quota;
	}

	/**
	 * @param quota the quota to set
	 */
	public void setQuota(AwsQuota quota) {
		this.quota = quota;
	}

}