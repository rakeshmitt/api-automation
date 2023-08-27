package com.rakesh.practice.api.web.service;

import com.rakesh.practice.api.commons.enums.ProductType;

public interface ProductVersionService {

	public String[] getProductNames() ;
	
	public String[] getProductVersions(String productName) ;
	
	public String[] getMuleDeploymentEnvs();

	public String[] getMuleApiGatewayVersions();
	
	public ProductType getProduct(String prodName);
	
}
