package com.rakesh.practice.api.commons.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.rakesh.practice.api.commons.enums.ProductType;

@Configuration
public class Product {

	private String productName;
	
	private String prodVersion;
	
	@Value("${aws.accessKey}")
	private String awsAccessKey;
	
	private String awsRegionName;
	
	@Value("${aws.secretKey}")
	private String awsSecretKey;
	
	private String muleApiDeployDecision;
	
	private String awsApiDeployDecision;

	@Value("${mule.org}")	
	private String muleOrgId;
	
	@Value("${mule.org.env}")
	private String muleEnvId;
	
	@Value("${mule.deployment.type}")
	private String deploymentType;

//	@Value("${mule.username}")
	private String muleUsername;

//	@Value("${mule.password}")
	private String mulePassword;
	
	private ProductType productType;

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the prodVersion
	 */
	public String getProdVersion() {
		return prodVersion;
	}

	/**
	 * @param prodVersion the prodVersion to set
	 */
	public void setProdVersion(String prodVersion) {
		this.prodVersion = prodVersion;
	}

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
	 * @return the muleOrgId
	 */
	public String getMuleOrgId() {
		return muleOrgId;
	}

	/**
	 * @param muleOrgId the muleOrgId to set
	 */
	public void setMuleOrgId(String muleOrgId) {
		this.muleOrgId = muleOrgId;
	}

	/**
	 * @return the muleEnvId
	 */
	public String getMuleEnvId() {
		return muleEnvId;
	}

	/**
	 * @param muleEnvId the muleEnvId to set
	 */
	public void setMuleEnvId(String muleEnvId) {
		this.muleEnvId = muleEnvId;
	}

	/**
	 * @return the muleUsername
	 */
	public String getMuleUsername() {
		return muleUsername;
	}

	/**
	 * @param muleUsername the muleUsername to set
	 */
	public void setMuleUsername(String muleUsername) {
		this.muleUsername = muleUsername;
	}

	/**
	 * @return the mulePassword
	 */
	public String getMulePassword() {
		return mulePassword;
	}

	/**
	 * @param mulePassword the mulePassword to set
	 */
	public void setMulePassword(String mulePassword) {
		this.mulePassword = mulePassword;
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

	
	/**
	 * @return the productType
	 */
	public ProductType getProductType() {
		return productType;
	}

	/**
	 * @param productType the productType to set
	 */
	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	/**
	 * @return the deploymentType
	 */
	public String getDeploymentType() {
		return deploymentType;
	}

	/**
	 * @param deploymentType the deploymentType to set
	 */
	public void setDeploymentType(String deploymentType) {
		this.deploymentType = deploymentType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Product [productName=" + productName + ", prodVersion=" + prodVersion + ", awsAccessKey=" + awsAccessKey
				+ ", awsRegionName=" + awsRegionName + ", awsSecretKey=" + awsSecretKey + ", muleApiDeployDecision="
				+ muleApiDeployDecision + ", awsApiDeployDecision=" + awsApiDeployDecision + ", muleOrgId=" + muleOrgId
				+ ", muleEnvId=" + muleEnvId + ", deploymentType=" + deploymentType + ", muleUsername=" + muleUsername
				+ ", mulePassword=" + mulePassword + ", productType=" + productType + "]";
	}

	/**
	 * @return the muleApiDeployDecision
	 */
	public String getMuleApiDeployDecision() {
		return muleApiDeployDecision;
	}

	/**
	 * @param muleApiDeployDecision the muleApiDeployDecision to set
	 */
	public void setMuleApiDeployDecision(String muleApiDeployDecision) {
		this.muleApiDeployDecision = muleApiDeployDecision;
	}

	/**
	 * @return the awsApiDeployDecision
	 */
	public String getAwsApiDeployDecision() {
		return awsApiDeployDecision;
	}

	/**
	 * @param awsApiDeployDecision the awsApiDeployDecision to set
	 */
	public void setAwsApiDeployDecision(String awsApiDeployDecision) {
		this.awsApiDeployDecision = awsApiDeployDecision;
	}

	
}
