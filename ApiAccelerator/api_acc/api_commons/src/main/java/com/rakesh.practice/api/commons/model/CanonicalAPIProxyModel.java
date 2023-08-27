package com.rakesh.practice.api.commons.model;

import java.util.List;

import com.rakesh.practice.api.commons.enums.ProductType;

public class CanonicalAPIProxyModel {
	
	private List<ApiDetail> apiDetails;
	
	private PolicyDetail policyDetail;
	
	private Product product;
	
	/**
	 * @return the apiDetails
	 */
	public List<ApiDetail> getApiDetails() {
		return apiDetails;
	}

	/**
	 * @param apiDetails the apiDetails to set
	 */
	public void setApiDetails(List<ApiDetail> apiDetails) {
		this.apiDetails = apiDetails;
	}

	/**
	 * @return the policyDetail
	 */
	public PolicyDetail getPolicyDetail() {
		return policyDetail;
	}

	/**
	 * @param policyDetail the policyDetail to set
	 */
	public void setPolicyDetail(PolicyDetail policyDetail) {
		this.policyDetail = policyDetail;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public ApiDetail getApiFromProductType(ProductType productType) {
		
		ApiDetail api = null;
		
		for(ApiDetail apiDetail: this.getApiDetails()) {
			if(apiDetail.getProductType().equals(productType)) {
				api = apiDetail; 
				break;
			}
		}

		return api;
	}

}
