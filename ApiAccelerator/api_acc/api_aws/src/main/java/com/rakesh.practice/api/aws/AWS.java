package com.rakesh.practice.api.aws;

import com.rakesh.practice.api.commons.model.Product;

public class AWS extends Product {

	

	private String awsAccessKey;

	private String awsRegionName;

	private String awsSecretKey;

	
	/**
	 * @return the awsAccessKey
	 */
	public String getAwsAccessKey() {
		return awsAccessKey;
	}

	/**
	 * @param awsAccessKey the awsAccessKey to set
	 */
	public void setAwsAccessKey(String awsAccessKey) {
		this.awsAccessKey = awsAccessKey;
	}

	/**
	 * @return the awsRegionName
	 */
	public String getAwsRegionName() {
		return awsRegionName;
	}

	/**
	 * @param awsRegionName the awsRegionName to set
	 */
	public void setAwsRegionName(String awsRegionName) {
		this.awsRegionName = awsRegionName;
	}
	
	/**
	 * @return the awsSecretKey
	 */
	public String getAwsSecretKey() {
		return awsSecretKey;
	}

	/**
	 * @param awsSecretKey the awsSecretKey to set
	 */
	public void setAwsSecretKey(String awsSecretKey) {
		this.awsSecretKey = awsSecretKey;
	}
}
