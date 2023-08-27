package com.rakesh.practice.api.commons.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("commons.properties")
@ConfigurationProperties
public class ApiCommonsConfig {

	@Value("${sheet.read.indexes}")
	private Integer[] sheetIndexes;

	@Value("${sheet.0.start.column.index}")
	private int apiStartColumnIndex;
	
	@Value("${sheet.0.start.row.index}")
	private int apiStartRowIndex;
	
	@Value("${sheet.column.count.read}")
	private int headerColumnCount;
	
	@Value("${api.products}")
	private String[] productsName;
	
	/**
	 * @return the sheetIndexes
	 */
	public Integer[] getSheetIndexes() {
		return sheetIndexes;
	}

	/**
	 * @param sheetIndexes the sheetIndexes to set
	 */
	public void setSheetIndexes(Integer[] sheetIndexes) {
		this.sheetIndexes = sheetIndexes;
	}

	/**
	 * @return the apiStartColumnIndex
	 */
	public int getApiStartColumnIndex() {
		return apiStartColumnIndex;
	}

	/**
	 * @param apiStartColumnIndex the apiStartColumnIndex to set
	 */
	public void setApiStartColumnIndex(int apiStartColumnIndex) {
		this.apiStartColumnIndex = apiStartColumnIndex;
	}

	/**
	 * @return the apiStartRowIndex
	 */
	public int getApiStartRowIndex() {
		return apiStartRowIndex;
	}

	/**
	 * @param apiStartRowIndex the apiStartRowIndex to set
	 */
	public void setApiStartRowIndex(int apiStartRowIndex) {
		this.apiStartRowIndex = apiStartRowIndex;
	}

	/**
	 * @return the headerColumnCount
	 */
	public int getHeaderColumnCount() {
		return headerColumnCount;
	}

	/**
	 * @param headerColumnCount the headerColumnCount to set
	 */
	public void setHeaderColumnCount(int headerColumnCount) {
		this.headerColumnCount = headerColumnCount;
	}

	/**
	 * @return the productsName
	 */
	public String[] getProductsName() {
		return productsName;
	}
	
}
