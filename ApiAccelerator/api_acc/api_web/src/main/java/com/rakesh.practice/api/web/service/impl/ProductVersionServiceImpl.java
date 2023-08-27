package com.rakesh.practice.api.web.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rakesh.practice.api.commons.enums.ProductType;
import com.rakesh.practice.api.web.service.ProductVersionService;

@Service("productVersionServiceImpl")
public class ProductVersionServiceImpl implements ProductVersionService{
	
	@Value("${api.products}")
	private String[] productsName;
	
	@Value("${api.products.mule.gateway.versions}")
	private String[] muleApiGatewayVersions;
	
	@Value("${api.products.mule.deploymentEnvs}")
	private String[] muleDeploymentEnvs;

	@Value("${api.products.aws.versions}")
	private String[] awsVersion;

	@Value("${api.products.apigee.versions}")
	private String[] apigeeVersion;
	
	
	public String[] getProductNames() {
		return productsName;
	}
	
	public String[] getProductVersions(String productName) {
		if(null != productName) {			
			if(productName.contains("mule")) {
				return muleApiGatewayVersions;
			}else if(productName.contains("aws")) {
				return awsVersion;
			}else if(productName.contains("apigee")) {
				return apigeeVersion;
			}
		}
		return null;
	}

	@Override
	public String[] getMuleDeploymentEnvs() {
		return muleDeploymentEnvs;
	}

	@Override
	public String[] getMuleApiGatewayVersions() {
		return muleApiGatewayVersions;
	}
	
	public ProductType getProduct(String prodName) {
		ProductType prodType = ProductType.DEFAULT;
		for(String prod:getProductNames()) {
		
			if(StringUtils.equalsIgnoreCase(prod, prodName)) {
				if(StringUtils.containsAny(prodName, ProductType.AWS.toString(), ProductType.AMAZON.toString())) {
					prodType = ProductType.AWS;
				}else if(StringUtils.equalsAnyIgnoreCase(prodName, ProductType.MULESOFT.toString())) {
					prodType = ProductType.MULESOFT;
				}
			}
			
		}
		return prodType;
	}
	
}
