package com.rakesh.practice.api.commons.policy.model;

import com.rakesh.practice.api.commons.enums.PolicyType;
import com.rakesh.practice.api.commons.model.Policy;

public class CORSPolicy extends Policy{
	
	String isPublicResource;
	String isSupportCredentials;
	String[] origins;
	String accessControlMaxAge;
	String[] allowedMethods;
	String[] headers;
	String[] exposedHeaders;

	public CORSPolicy() {
		super(PolicyType.CORS);
	}

	/**
	 * @return the isPublicResource
	 */
	public String getIsPublicResource() {
		return isPublicResource;
	}

	/**
	 * @param isPublicResource the isPublicResource to set
	 */
	public void setIsPublicResource(String isPublicResource) {
		this.isPublicResource = isPublicResource;
	}

	/**
	 * @return the isSupportCredentials
	 */
	public String getIsSupportCredentials() {
		return isSupportCredentials;
	}

	/**
	 * @param isSupportCredentials the isSupportCredentials to set
	 */
	public void setIsSupportCredentials(String isSupportCredentials) {
		this.isSupportCredentials = isSupportCredentials;
	}

	/**
	 * @return the origins
	 */
	public String[] getOrigins() {
		return origins;
	}

	/**
	 * @param origins the origins to set
	 */
	public void setOrigins(String[] origins) {
		this.origins = origins;
	}

	/**
	 * @return the accessControlMaxAge
	 */
	public String getAccessControlMaxAge() {
		return accessControlMaxAge;
	}

	/**
	 * @param accessControlMaxAge the accessControlMaxAge to set
	 */
	public void setAccessControlMaxAge(String accessControlMaxAge) {
		this.accessControlMaxAge = accessControlMaxAge;
	}

	/**
	 * @return the allowedMethods
	 */
	public String[] getAllowedMethods() {
		return allowedMethods;
	}

	/**
	 * @param allowedMethods the allowedMethods to set
	 */
	public void setAllowedMethods(String[] allowedMethods) {
		this.allowedMethods = allowedMethods;
	}

	/**
	 * @return the headers
	 */
	public String[] getHeaders() {
		return headers;
	}

	/**
	 * @param headers the headers to set
	 */
	public void setHeaders(String[] headers) {
		this.headers = headers;
	}

	/**
	 * @return the exposedHeaders
	 */
	public String[] getExposedHeaders() {
		return exposedHeaders;
	}

	/**
	 * @param exposedHeaders the exposedHeaders to set
	 */
	public void setExposedHeaders(String[] exposedHeaders) {
		this.exposedHeaders = exposedHeaders;
	}

}
