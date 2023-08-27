package com.rakesh.practice.api.commons.exception;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.rakesh.practice.api.commons.model.RemoteServiceResponse;

public abstract class BaseException extends Exception{
    
	private static final long serialVersionUID = 1232432344454L;
	//Each exception message will be held here
    private String message;
    private JsonNode jsonNode;
    private RemoteServiceResponse remoteServiceResponse;
    
    private final String DEFAULT_ERROR_MSG = "Something went wrong while creating API";
 
    public BaseException(String msg)
    {
        this.message = msg;
    }
    
    public BaseException(JsonNode jsonNode) {
    	this.jsonNode = jsonNode;
    	setMessage(jsonNode);
    }
    
    public BaseException(RemoteServiceResponse remoteServiceResponse) {
    	this.remoteServiceResponse = remoteServiceResponse;
    }
    //Message can be retrieved using this accessor method
    public String getMessage() {
        return message;
    }
    
    public String getJsonMessage() {
    	if(this.jsonNode != null && jsonNode instanceof TextNode) {
    		return ((TextNode)jsonNode).textValue();
    	}else {
    		return DEFAULT_ERROR_MSG;
    	}
    }
    
    public void setMessage(JsonNode jsonNode) {
    	if(this.jsonNode != null) {
    		if(jsonNode instanceof TextNode) {
        		this.message =  ((TextNode)jsonNode).textValue();
        	}else if(jsonNode instanceof ObjectNode) {
        		this.message = ((ObjectNode)jsonNode).get("message").toString();
        	}
    	}else {
    		this.message = DEFAULT_ERROR_MSG;
    	}
    }

	/**
	 * @return the remoteServiceResponse
	 */
	public RemoteServiceResponse getRemoteServiceResponse() {
		return remoteServiceResponse;
	}

	/**
	 * @param remoteServiceResponse the remoteServiceResponse to set
	 */
	public void setRemoteServiceResponse(RemoteServiceResponse remoteServiceResponse) {
		this.remoteServiceResponse = remoteServiceResponse;
	}
}