package com.rakesh.practice.api.commons.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rakesh.practice.api.commons.config.ApiCommonsConfig;
import com.rakesh.practice.api.commons.constant.Constants;
import com.rakesh.practice.api.commons.enums.PolicyType;
import com.rakesh.practice.api.commons.factory.PolicyFactory;
import com.rakesh.practice.api.commons.model.ApiDetail;
import com.rakesh.practice.api.commons.model.CanonicalAPIProxyModel;
import com.rakesh.practice.api.commons.model.Policy;
import com.rakesh.practice.api.commons.model.PolicyDetail;
import com.rakesh.practice.api.commons.model.PolicyParameter;
import com.rakesh.practice.api.commons.model.Product;
import com.rakesh.practice.api.commons.util.PolicyUtil;

@Component
public class ApiDocumentParserV2 {
	
	private static final Logger logger = LogManager.getLogger(ApiDocumentParserV2.class);

	@Autowired
	private ApiCommonsConfig config;

	@SuppressWarnings("resource")
	public CanonicalAPIProxyModel processExcel(File file, Product product) throws IOException {

		FileInputStream excelFile = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(excelFile);

		Integer[] sheets = config.getSheetIndexes();
		
		List<ApiDetail> apiDetails = getApiDetail(workbook.getSheetAt(sheets[Constants.ZERO]), file.getName(), product);

		Sheet sheet = workbook.getSheetAt(sheets[Constants.ONE]);

		// Map<String, Policy> policies = generateMapping(sheet);

		CanonicalAPIProxyModel canonicalModel = new CanonicalAPIProxyModel();
		canonicalModel.setApiDetails(apiDetails);
		canonicalModel.setPolicyDetail(generateMapping(sheet));
//		canonicalModel.setPolicyList(generatePolicyList(sheet));;
		canonicalModel.setProduct(product);

		// log.info(policies.toString());
		return canonicalModel;
	}

	private List<ApiDetail> getApiDetail(Sheet sheet, String fileName, Product product) {
		final Iterator<Row> rowIterator = sheet.iterator();
		DataFormatter formatter = new DataFormatter();
		ApiDetail apiDetail = null;
		List<ApiDetail> apiList = new ArrayList<ApiDetail>();
		while (rowIterator.hasNext()) {
			Row currentRow = rowIterator.next();

			if (currentRow.getRowNum() > config.getApiStartRowIndex()) {
				for (int i = 0; i < currentRow.getPhysicalNumberOfCells(); i++) {

					Cell cell = currentRow.getCell(i);
					if (null != cell) {
						if (cell.getColumnIndex() == Constants.ZERO && !isCellBlank(cell)
								&& isMergedCell(sheet, cell)) {

							apiDetail = new ApiDetail();
							apiDetail.setApiName(formatter.formatCellValue(cell));
							apiDetail.setApiVersion(formatter
									.formatCellValue(currentRow.getCell(cell.getColumnIndex() + Constants.ONE)).trim());
							apiDetail.setSpecificationPath(formatter
									.formatCellValue(currentRow.getCell(cell.getColumnIndex() + Constants.TWO)).trim());
							apiDetail.setAssetId(formatter
									.formatCellValue(currentRow.getCell(cell.getColumnIndex() + Constants.THREE)).trim());
							apiDetail.setEndPoint(formatter
									.formatCellValue(currentRow.getCell(cell.getColumnIndex() + Constants.FIVE)).trim());
							
							/*if(!StringUtils.isBlank(apiDetail.getAssetId()) && StringUtils.isBlank(apiDetail.getSpecificationPath())){
								apiDetail.setProductType(ProductType.MULESOFT);
							}else if(StringUtils.isBlank(apiDetail.getAssetId()) && !StringUtils.isBlank(apiDetail.getSpecificationPath())) {
								apiDetail.setProductType(ProductType.AWS);
							}*/
							apiDetail.setProductType(product.getProductType());
							
							/*else {
								throw new IllegalArgumentException("Please review the uploaded excel sheet "+sheet.getSheetName() + " of file "+fileName);
							}*/
							
							//TODO if neither AssetId or SwaggerPath mentioned then an exception must be thrown.

							if (null != apiDetail)
								apiList.add(apiDetail);
						}else 
							if (cell.getColumnIndex() == Constants.ZERO && !isCellBlank(cell)
							&& !isMergedCell(sheet, cell)) {

						apiDetail = new ApiDetail();
						apiDetail.setApiName(formatter.formatCellValue(cell));
						apiDetail.setApiVersion(formatter
								.formatCellValue(currentRow.getCell(cell.getColumnIndex() + Constants.ONE)).trim());
						apiDetail.setSpecificationPath(formatter
								.formatCellValue(currentRow.getCell(cell.getColumnIndex() + Constants.TWO)).trim());
						apiDetail.setAssetId(formatter
								.formatCellValue(currentRow.getCell(cell.getColumnIndex() + Constants.THREE)).trim());
						apiDetail.setResourcePath(formatter
								.formatCellValue(currentRow.getCell(cell.getColumnIndex() + Constants.FOUR)).trim());
						apiDetail.setEndPoint(formatter
								.formatCellValue(currentRow.getCell(cell.getColumnIndex() + Constants.FIVE)).trim());
						
						/*if(!StringUtils.isBlank(apiDetail.getAssetId()) && StringUtils.isBlank(apiDetail.getSpecificationPath())){
							apiDetail.setProductType(ProductType.MULESOFT);
						}else if(StringUtils.isBlank(apiDetail.getAssetId()) && !StringUtils.isBlank(apiDetail.getSpecificationPath())) {
							apiDetail.setProductType(ProductType.AWS);
						}*//*else {
							throw new IllegalArgumentException("Please review the uploaded excel sheet "+sheet.getSheetName() + " of file "+fileName);
						}*/
						
						apiDetail.setProductType(product.getProductType());
						
						//TODO if neither AssetId or SwaggerPath mentioned then an exception must be thrown.

						if (null != apiDetail)
							apiList.add(apiDetail);
					}
				
					}

				}
			}

		}

		return apiList;
	}

	private PolicyDetail generateMapping(Sheet sheet) {

		PolicyDetail policyDetail = new PolicyDetail();

		String policyName = new String();

		String enabled = new String();

		List<PolicyParameter> policyParams = new ArrayList<PolicyParameter>();
		
		Set<CellRangeAddress> cellSet = getScopeRange(sheet);
		DataFormatter formatter = new DataFormatter();

		Iterator<CellRangeAddress> rangeSet = cellSet.iterator();
		
		while(rangeSet.hasNext()) {
			CellRangeAddress address = rangeSet.next();
			policyParams = new ArrayList<PolicyParameter>();
			for(int i = address.getFirstRow(); i<= address.getLastRow();i++) {
				
				Cell policyNameCell = sheet.getRow(i).getCell(Constants.ONE);
				if(!isCellBlank(policyNameCell)) {
					policyName = formatter.formatCellValue(policyNameCell).trim();
				}
				
				Cell enableStatusCell = sheet.getRow(i).getCell(Constants.TWO);
				if(!isCellBlank(enableStatusCell)) {
					enabled = formatter.formatCellValue(enableStatusCell);
//					logger.debug("Enabled Value in loop"+ enableStatusCell.getAddress() + "  " + enabled );
				}
				
				Cell paramNameCell = sheet.getRow(i).getCell(Constants.THREE);
				Cell paramValCell = sheet.getRow(i).getCell(Constants.FOUR);
				if(!isCellBlank(paramNameCell) && !isCellBlank(paramValCell)) {
					policyParams.add(new PolicyParameter(formatter.formatCellValue(paramNameCell).trim(), formatter.formatCellValue(paramValCell).trim()));
				}
				
			}
			PolicyType policyType = PolicyUtil.determinePolicyType(policyName);
//			logger.debug("Enabled Value out of loop "+ enabled);
			
			if(!StringUtils.isBlank(enabled) && BooleanUtils.toBoolean(enabled)) {
//				logger.debug("Enabled Value inside if condition before setting to Policy Detail List "+ address + "  " + enabled);
				logger.info(policyType);
				policyDetail.addPolicy(policyType, policyCreator(policyType, policyParams), BooleanUtils.toBoolean(enabled));
			}
		}
		logger.debug(policyDetail);
		return policyDetail;
	}

	/**
	 * Another flavour of list
	 * @param sheet
	 * @return
	 */
	
	private List<Policy> generatePolicyList(Sheet sheet) {

		PolicyDetail policyDetail = new PolicyDetail();
		
		List<Policy> policyList = new ArrayList<Policy>();

		String policyName = new String();

		String enabled = new String();

		List<PolicyParameter> policyParams = new ArrayList<PolicyParameter>();
		
		Set<CellRangeAddress> cellSet = getScopeRange(sheet);
		DataFormatter formatter = new DataFormatter();

		Iterator<CellRangeAddress> rangeSet = cellSet.iterator();
		
		while(rangeSet.hasNext()) {
			CellRangeAddress address = rangeSet.next();
			policyParams = new ArrayList<PolicyParameter>();
			for(int i = address.getFirstRow(); i<= address.getLastRow();i++) {
				
				Cell policyNameCell = sheet.getRow(i).getCell(Constants.ONE);
				if(!isCellBlank(policyNameCell)) {
					policyName = formatter.formatCellValue(policyNameCell).trim();
				}
				
				Cell enableStatusCell = sheet.getRow(i).getCell(Constants.TWO);
				if(!isCellBlank(enableStatusCell)) {
					enabled = formatter.formatCellValue(enableStatusCell);
//					logger.debug("Enabled Value in loop"+ enableStatusCell.getAddress() + "  " + enabled );
				}
				
				Cell paramNameCell = sheet.getRow(i).getCell(Constants.THREE);
				Cell paramValCell = sheet.getRow(i).getCell(Constants.FOUR);
				if(!isCellBlank(paramNameCell) && !isCellBlank(paramValCell)) {
					policyParams.add(new PolicyParameter(formatter.formatCellValue(paramNameCell).trim(), formatter.formatCellValue(paramValCell).trim()));
				}
				
			}
			PolicyType policyType = PolicyUtil.determinePolicyType(policyName);
//			logger.debug("Enabled Value out of loop "+ enabled);
			
			if(!StringUtils.isBlank(enabled) && BooleanUtils.toBoolean(enabled)) {
//				logger.debug("Enabled Value inside if condition before setting to Policy Detail List "+ address + "  " + enabled);
				logger.info(policyType);
				policyDetail.addPolicy(policyType, policyCreator(policyType, policyParams), BooleanUtils.toBoolean(enabled));
				policyList.add(policyCreator(policyType, policyParams));
			}
		}
		logger.debug(policyList);
		return policyList;
	}

	
	
	private Set<CellRangeAddress> getScopeRange(Sheet sheet) {
		Set<CellRangeAddress> cellSet = new HashSet<CellRangeAddress>();
		for(int phyRows = 0; phyRows < sheet.getPhysicalNumberOfRows(); phyRows++)
		{
			Row currentRow = sheet.getRow(phyRows);
			Iterator<Cell> cellItr = currentRow.cellIterator();
			while(cellItr.hasNext()) {
				
				Cell currentCell = (Cell)cellItr.next();
				
				if(currentCell.getColumnIndex() == Constants.ONE && currentCell.getRowIndex() > Constants.ONE) 
				{
					if(!isCellBlank(currentCell)) {
						CellRangeAddress rangeAddress = getMergedCellRange(sheet, currentCell);
						cellSet.add(rangeAddress);
					}
					
				}
			}
		}
		return cellSet;
	}	
	
	@SuppressWarnings("deprecation")
	public boolean isCellBlank(Cell currentCell) {
		return (currentCell == null || currentCell.getCellType() == Cell.CELL_TYPE_BLANK);
	}

	
	public boolean isMergedCell(Sheet sheet, Cell cell) {
		int mergedRegions = sheet.getNumMergedRegions();
		boolean mergedStatus = false;
		for (int i = 0; i < mergedRegions; i++) {
			CellRangeAddress cellAddress = sheet.getMergedRegion(i);
			if (cellAddress.isInRange(cell.getRowIndex(), cell.getColumnIndex())) {
				mergedStatus = true;
				
			}
		}

		return mergedStatus;
	}
	
	public CellRangeAddress getMergedCellRange(Sheet sheet, Cell cell) {
		int mergedRegions = sheet.getNumMergedRegions();
		boolean mergedStatus = false;
		CellRangeAddress rangeAddress = null;
		for (int i = 0; i < mergedRegions; i++) {
			CellRangeAddress cellAddress = sheet.getMergedRegion(i);
			if (cellAddress.isInRange(cell.getRowIndex(), cell.getColumnIndex())) {
				mergedStatus = true;
				rangeAddress = cellAddress;
			}
		}

		if(!mergedStatus) {
				rangeAddress =  new CellRangeAddress(cell.getRowIndex(), cell.getRowIndex(), 
						cell.getColumnIndex(), cell.getColumnIndex());
		}

		return rangeAddress;
	}

	public Policy policyCreator(PolicyType policyType, List<PolicyParameter> params) {
		return PolicyFactory.buildPolicy(policyType, params);
	}

}
