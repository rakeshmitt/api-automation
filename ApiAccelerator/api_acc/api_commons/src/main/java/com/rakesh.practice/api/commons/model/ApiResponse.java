package com.rakesh.practice.api.commons.model;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.EnumDeserializer;
import com.rakesh.practice.api.commons.enums.PolicyType;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"policyType",
"httpStatus",
"apiError"
})
public class ApiResponse {
	
	@JsonProperty("policyType")
	private PolicyType policyType;
	
	@JsonProperty("httpStatus")
	private HttpStatus httpStatus;
	
	@JsonProperty("apiError")
	private ApiError apiError;
	/**
	 * @return the policyType
	 */
	public PolicyType getPolicyType() {
		return policyType;
	}
	/**
	 * @param policyType the policyType to set
	 */
	public void setPolicyType(PolicyType policyType) {
		this.policyType = policyType;
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
	 * @return the apiError
	 */
	public ApiError getApiError() {
		return apiError;
	}
	/**
	 * @param apiError the apiError to set
	 */
	public void setApiError(ApiError apiError) {
		this.apiError = apiError;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ApiResponse [policyType=" + policyType + ", httpStatus=" + httpStatus + ", apiError=" + apiError + "]";
	}
	
}
