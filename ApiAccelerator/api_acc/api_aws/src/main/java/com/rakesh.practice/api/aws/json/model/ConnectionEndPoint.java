package com.rakesh.practice.api.aws.json.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "type", "httpMethod", "requestParameters", "uri", "connectionType" })
public class ConnectionEndPoint {

	@JsonProperty("type")
	private String type;
	@JsonProperty("httpMethod")
	private String httpMethod;
	@JsonProperty("requestParameters")
	private Map<String, String> requestParameters;
//	private RequestParameters requestParameters;
	@JsonProperty("uri")
	private String uri;
	@JsonProperty("connectionType")
	private String connectionType;
	

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("httpMethod")
	public String getHttpMethod() {
		return httpMethod;
	}

	@JsonProperty("httpMethod")
	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	/*@JsonProperty("requestParameters")
	public RequestParameters getRequestParameters() {
		return requestParameters;
	}

	@JsonProperty("requestParameters")
	public void setRequestParameters(RequestParameters requestParameters) {
		this.requestParameters = requestParameters;
	}*/

	@JsonProperty("uri")
	public String getUri() {
		return uri;
	}

	@JsonProperty("uri")
	public void setUri(String uri) {
		this.uri = uri;
	}

	@JsonProperty("connectionType")
	public String getConnectionType() {
		return connectionType;
	}

	@JsonProperty("connectionType")
	public void setConnectionType(String connectionType) {
		this.connectionType = connectionType;
	}

	/**
	 * @return the requestParameters
	 */
	public Map<String, String> getRequestParameters() {
		return requestParameters;
	}

	/**
	 * @param requestParameters the requestParameters to set
	 */
	public void setRequestParameters(Map<String, String> requestParameters) {
		this.requestParameters = requestParameters;
	}

}