package com.rakesh.practice.api.commons.exception;

import com.fasterxml.jackson.databind.JsonNode;
import com.rakesh.practice.api.commons.model.RemoteServiceResponse;

public class ApiException extends Exception {

	private static final long serialVersionUID = -23423432543534545L;

	public static class AuthorizationException extends BaseException {

		private static final long serialVersionUID = 551761334457859L;

		public AuthorizationException(String msg) {
			super(msg);
		}
		
		public AuthorizationException(JsonNode jsonMessage) {
			super(jsonMessage);
		}
		
		public AuthorizationException(RemoteServiceResponse remoteServiceResponse) {
			super(remoteServiceResponse);
		}
	}

	public static class ApiCreationException extends BaseException {

		private static final long serialVersionUID = 1232432344454L;

		public ApiCreationException(String msg) {
			super(msg);
		}
		
		public ApiCreationException(JsonNode jsonNode) {
			super(jsonNode);
		}
		
		public ApiCreationException(RemoteServiceResponse remoteServiceResponse) {
			super(remoteServiceResponse);
		}
	}

	public static class PolicyApplicationException extends BaseException {

		private static final long serialVersionUID = 1232432344454L;

		public PolicyApplicationException(String msg) {
			super(msg);
		}
		
		public PolicyApplicationException(JsonNode jsonNode) {
			super(jsonNode);
		}
		
		public PolicyApplicationException(RemoteServiceResponse remoteServiceResponse) {
			super(remoteServiceResponse);
		}
	}
	
	public static class ApiDeploymentException extends BaseException {

		private static final long serialVersionUID = 1232432344454L;

		public ApiDeploymentException(String msg) {
			super(msg);
		}
		
		public ApiDeploymentException(JsonNode jsonNode) {
			super(jsonNode);
		}
		
		public ApiDeploymentException(RemoteServiceResponse remoteServiceResponse) {
			super(remoteServiceResponse);
		}
		
	}

}
