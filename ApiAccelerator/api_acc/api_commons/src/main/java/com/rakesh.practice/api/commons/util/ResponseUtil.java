package com.rakesh.practice.api.commons.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rakesh.practice.api.commons.enums.PolicyType;
import com.rakesh.practice.api.commons.model.ApiResponse;
import com.rakesh.practice.api.commons.model.RemoteServiceResponse;


public class ResponseUtil {

	private static final Logger logger = LogManager.getLogger(ResponseUtil.class);
	
	public static ApiResponse getResponse(PolicyType policyType, RemoteServiceResponse remoteResponse) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setApiError(remoteResponse.getApiErrorResponse());
		apiResponse.setHttpStatus(remoteResponse.getHttpStatus());
		apiResponse.setPolicyType(policyType);
		
		logger.debug("AAP Response for UI");
		logger.debug(apiResponse);
		
		return apiResponse;
	}
	
}
