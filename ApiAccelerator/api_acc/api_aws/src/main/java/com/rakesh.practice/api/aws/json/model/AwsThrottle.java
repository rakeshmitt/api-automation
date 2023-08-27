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
@JsonPropertyOrder({ "burstLimit", "rateLimit" })
public class AwsThrottle {

	@JsonProperty("burstLimit")
	private Long burstLimit;
	
	@JsonProperty("rateLimit")
	private Long rateLimit;
	
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("burstLimit")
	public Long getBurstLimit() {
		return burstLimit;
	}

	@JsonProperty("burstLimit")
	public void setBurstLimit(Long burstLimit) {
		this.burstLimit = burstLimit;
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
	 * @return the rateLimit
	 */
	@JsonProperty("rateLimit")
	public Long getRateLimit() {
		return rateLimit;
	}

	/**
	 * @param rateLimit the rateLimit to set
	 */
	@JsonProperty("rateLimit")
	public void setRateLimit(Long rateLimit) {
		this.rateLimit = rateLimit;
	}

}