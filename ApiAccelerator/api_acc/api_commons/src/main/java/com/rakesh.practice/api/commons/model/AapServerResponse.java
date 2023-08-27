package com.rakesh.practice.api.commons.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.rakesh.practice.api.commons.enums.APIState;

@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME, 
		include = JsonTypeInfo.As.PROPERTY, 
		property = "type",
		visible = true
		)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"apiDetail",
	"apiState",
	"apiResponse"
})


public class ApiServerResponse {
	
	@JsonProperty("apiDetail")
	private ApiDetail apiDetail;
	@JsonProperty("apiState")
	private APIState apiState;
	@JsonProperty("apiResponse")
	private List<apiResponse> apiResponse = null;
	
	/**
	 * @return the apiDetail
	 */
	public ApiDetail getApiDetail() {
		return apiDetail;
	}

	/**
	 * @param apiDetail the apiDetail to set
	 */
	public void setApiDetail(ApiDetail apiDetail) {
		this.apiDetail = apiDetail;
	}


	/**
	 * @return the apiResponse
	 */
	public List<ApiResponse> getApiResponse() {
		return apiResponse;
	}

	/**
	 * @param apiResponse the apiResponse to set
	 */
	public void setapiResponse(List<apiResponse> apiResponse) {
		this.apiResponse = apiResponse;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ApiServerResponse [apiDetail=" + apiDetail + ", apiState=" + apiState + ", apiResponse=" + apiResponse
				+ "]";
	}

	/**
	 * @return the apiState
	 */
	public APIState getApiState() {
		return apiState;
	}

	/**
	 * @param apiState the apiState to set
	 */
	public void setApiState(APIState apiState) {
		this.apiState = apiState;
	}


}
