package com.rakesh.practice.api.commons.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.rakesh.practice.api.commons.config.ApiCommonsConfig;
import com.rakesh.practice.api.commons.enums.ProductType;

//@Component
public class ProductUtils {
	
	@Value("${api.products}")
	private String[] productsName;

	
	public ProductType getProduct(String prodName) {
		ProductType prodType = ProductType.DEFAULT;
//		String[] productsName = config.getProductsName(); 
		for(String prod:productsName) {
		
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
