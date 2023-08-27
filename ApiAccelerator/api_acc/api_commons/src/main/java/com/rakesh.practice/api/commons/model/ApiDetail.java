package com.rakesh.practice.apii.commons.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.rakesh.practice.api.commons.enums.ProductType;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"apiName",
"apiVersion",
"specificationPath",
"assetId",
"apiMethod",
"productType",
"endPoint",
"resourcePath"
})
public class ApiDetail {
	
	@JsonProperty("apiName")
	private String apiName;
	@JsonProperty("apiVersion")
	private String apiVersion;
	@JsonProperty("specificationPath")
	private String specificationPath;
	@JsonProperty("assetId")
	private String assetId;
	@JsonProperty("apiMethod")
	private ApiMethod apiMethod;
	@JsonProperty("productType")
	private ProductType productType;
	@JsonProperty("endPoint")
	private String endPoint;
	@JsonProperty("resourcePath")
	private String resourcePath;

	/**
	 * @return the apiName
	 */
	public String getApiName() {
		return apiName;
	}
	/**
	 * @param apiName the apiName to set
	 */
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	/**
	 * @return the specificationPath
	 */
	public String getSpecificationPath() {
		return specificationPath;
	}
	/**
	 * @param specificationPath the specificationPath to set
	 */
	public void setSpecificationPath(String specificationPath) {
		this.specificationPath = specificationPath;
	}
	
	/**
	 * @return the apiMethod
	 */
	public ApiMethod getApiMethod() {
		return apiMethod;
	}
	/**
	 * @param apiMethod the apiMethod to set
	 */
	public void setApiMethod(ApiMethod apiMethod) {
		this.apiMethod = apiMethod;
	}
	/**
	 * @return the apiVersion
	 */
	public String getApiVersion() {
		return apiVersion;
	}
	/**
	 * @param apiVersion the apiVersion to set
	 */
	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ApiDetail [apiName=" + apiName + ", apiVersion=" + apiVersion + ", specificationPath="
				+ specificationPath + ", assetId=" + assetId + ", apiMethod=" + apiMethod + ", productType="
				+ productType + ", endPoint=" + endPoint + ", resourcePath=" + resourcePath + "]";
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
	 * @return the assetId
	 */
	public String getAssetId() {
		return assetId;
	}
	/**
	 * @param assetId the assetId to set
	 */
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}
	/**
	 * @return the endPoint
	 */
	public String getEndPoint() {
		return endPoint;
	}
	/**
	 * @param endPoint the endPoint to set
	 */
	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}
	/**
	 * @return the resourcePath
	 */
	public String getResourcePath() {
		return resourcePath;
	}
	/**
	 * @param resourcePath the resourcePath to set
	 */
	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}
	
	

}
