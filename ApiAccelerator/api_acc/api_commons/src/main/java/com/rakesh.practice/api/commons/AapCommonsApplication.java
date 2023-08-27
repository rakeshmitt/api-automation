package com.rakesh.practice.api.commons;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakesh.practice.api.commons.enums.ProductType;
import com.rakesh.practice.api.commons.model.CanonicalAPIProxyModel;
import com.rakesh.practice.api.commons.model.Product;
import com.rakesh.practice.api.commons.parser.ApiDocumentParserV2;
import com.rakesh.practice.api.commons.util.ProductUtils;

@SpringBootApplication
//@PropertySource("commons.properties", "aws.properties")
@PropertySources({ @PropertySource("aws.properties"), @PropertySource("commons.properties"),
	@PropertySource("mulesoft.properties") })
public class ApiCommonsApplication {
	
	

	public static void main(String[] args) throws Exception {
		ApplicationContext ctxt = SpringApplication.run(ApiCommonsApplication.class, args);

		ApiDocumentParserV2 excelUtil = ctxt.getBean(ApiDocumentParserV2.class);
		
		Product product = new Product();
		product.setProductType(ProductType.MULESOFT);
		
		CanonicalAPIProxyModel model = excelUtil
				.processExcel(new File("API_Policy_Info_Capture_Template2.0.xlsx"), product);
		
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(model));

		System.out.println(model.getPolicyDetail());
	}

}
