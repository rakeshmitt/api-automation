package com.rakesh.practice.api.commons.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.rakesh.practice.api.commons.constant.CommonsTestConstants;
import com.rakesh.practice.api.commons.enums.ProductType;
import com.rakesh.practice.api.commons.parser.ApiDocumentParserV2;

@ExtendWith(SpringExtension.class)
public class CanonicalAPIProxyModelTest {
	
	CanonicalAPIProxyModel apiProxyModel;
	
	@Mock
	ResourceLoader resourceLoader;
	
	@InjectMocks
	ApiDocumentParserV2 documentParserMock; 
	
	@BeforeEach
	public void setUp() {
		ReflectionTestUtils.setField(apiProxyModel, "sheetIndexes", CommonsTestConstants.SHEET_INDICES);
		ReflectionTestUtils.setField(apiProxyModel, "apiStartRowIndex", CommonsTestConstants.API_START_ROW_INDEX);
	}
	
	@Test
	public void testProcessExcel() throws IOException {
		apiProxyModel = documentParserMock.processExcel(new File("API_Policy_Info_Capture_Template2.0.xlsx"), getProduct());
		assertEquals(apiProxyModel.getProduct().getProductType(), getProduct().getProductType());
	}

	private Product getProduct() {
		Product product = new Product();
		product.setMuleEnvId("");
		product.setMuleOrgId("");
		product.setMuleUsername("");
		product.setMulePassword("");
		product.setProductName("Mulesoft");
		product.setProductType(ProductType.MULESOFT);

		return product;
	}
	
}
