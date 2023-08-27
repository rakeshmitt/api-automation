package com.rakesh.practice.api.aws.json.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.rakesh.practice.api.commons.model.ApiError;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "logref", "message" })
public class AwsApiError implements ApiError {

	@JsonProperty("logref")
	private String logref;
	@JsonProperty("message")
	private String message;

	@JsonProperty("logref")
	public String getLogref() {
		return logref;
	}

	@JsonProperty("logref")
	public void setLogref(String logref) {
		this.logref = logref;
	}

	@JsonProperty("message")
	public String getMessage() {
		return message;
	}

	@JsonProperty("message")
	public void setMessage(String message) {
		this.message = message;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AwsApiError [logref=" + logref + ", message=" + message + "]";
	}

}