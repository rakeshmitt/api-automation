package com.rakesh.practice.api.commons.model;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)


@JsonDeserialize
public class RemoteServiceResponse {

	private String httpStatusCode;

	private Object httpResponse;

	private String httpResponseMessage;
	
	private String apiId;
	
	private JsonNode jsonNode;
	
	private HttpStatus httpStatus;
	
	private ApiError apiErrorResponse;

	public RemoteServiceResponse() {
		super();
	}
	
	public String getHttpResponseMessage() {
		return httpResponseMessage;
	}

	public void setHttpResponseMessage(String httpResponseMessage) {
		this.httpResponseMessage = httpResponseMessage;
	}

	public String getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(String httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}


	public String getApiId() {
		return apiId;
	}


	public void setApiId(String apiId) {
		this.apiId = apiId;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RemoteServiceResponse [httpStatusCode=" + httpStatusCode + ", httpResponse=" + httpResponse
				+ ", httpResponseMessage=" + httpResponseMessage + ", apiId=" + apiId + ", jsonNode=" + jsonNode
				+ ", httpStatus=" + httpStatus + "]";
	}


	/**
	 * @return the jsonNode
	 */
	public JsonNode getJsonNode() {
		return jsonNode;
	}


	/**
	 * @param jsonNode the jsonNode to set
	 */
	public void setJsonNode(JsonNode jsonNode) {
		this.jsonNode = jsonNode;
	}


	/**
	 * @return the httpStatus
	 */
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}


	/**
	 * @param httpStatus the httpStatus to set
	 */
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}




	/**
	 * @return the httpResponse
	 */
	public Object getHttpResponse() {
		return httpResponse;
	}




	/**
	 * @param httpResponse the httpResponse to set
	 */
	public void setHttpResponse(Object httpResponse) {
		this.httpResponse = httpResponse;
	}

	/**
	 * @return the apiErrorResponse
	 */
	public ApiError getApiErrorResponse() {
		return apiErrorResponse;
	}

	/**
	 * @param apiErrorResponse the apiErrorResponse to set
	 */
	public void setApiErrorResponse(ApiError apiErrorResponse) {
		this.apiErrorResponse = apiErrorResponse;
	}

}
