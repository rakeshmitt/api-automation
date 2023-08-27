package com.rakesh.practice.api.web.handler;

import org.springframework.web.client.HttpStatusCodeException;

import com.rakesh.practice.api.commons.model.ApiError;

public class RestErrorHandler implements ApiError{

	private String message;
	
	public RestErrorHandler(HttpStatusCodeException hsce) {
		setMessage(hsce.getMessage());
	}
	
	public RestErrorHandler(Exception exception) {
		setMessage(exception.getMessage());
	}
	
	public String getMessage() {
		return this.message;
	}
	
	@Override
	public void setMessage(String message) {
		this.message = message;
	}

}
