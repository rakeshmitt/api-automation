package com.rakesh.practice.api.commons.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
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

@Deprecated
/**
 * 
 * @deprecated This class has been deprecated.
 * Kindly use the updated @see com.rakesh.practice.api.commons.parser.ApiDocumentParserV2
 *
 */

@Component
public class ApiDocumentParser {

	@Autowired
	private ApiCommonsConfig config;

	@SuppressWarnings("resource")
	public CanonicalAPIProxyModel processExcel(File file) throws IOException {

		FileInputStream excelFile = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(excelFile);

		Integer[] sheets = config.getSheetIndexes();
		List<ApiDetail> apiDetails = getApiDetail(workbook.getSheetAt(sheets[0]));

		Sheet sheet = workbook.getSheetAt(sheets[1]);

		// Map<String, Policy> policies = generateMapping(sheet);

		CanonicalAPIProxyModel canonicalModel = new CanonicalAPIProxyModel();
		canonicalModel.setApiDetails(apiDetails);
		canonicalModel.setPolicyDetail(generateMapping(sheet));

		// log.info(policies.toString());
		return canonicalModel;
	}

	private List<ApiDetail> getApiDetail(Sheet sheet) {
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
									.formatCellValue(currentRow.getCell(cell.getColumnIndex() + Constants.ONE)));
							apiDetail.setSpecificationPath(formatter
									.formatCellValue(currentRow.getCell(cell.getColumnIndex() + Constants.TWO)));
							apiDetail.setAssetId(formatter
									.formatCellValue(currentRow.getCell(cell.getColumnIndex() + Constants.THREE)));

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

		final Iterator<Row> rowIterator = sheet.iterator();

		// final Map<PolicyType, Policy> policies = new HashMap<PolicyType, Policy>();

		PolicyDetail policyDetail = new PolicyDetail();

		String policyName = "";

		String enabled = "";

		List<PolicyParameter> policyParams = new ArrayList<PolicyParameter>();
		
		System.out.println("Sheet Rows @@@ "+sheet.getPhysicalNumberOfRows());
		
		Set<CellRangeAddress> cellSet = new HashSet<CellRangeAddress>();
		

//		while (rowIterator.hasNext()) 
		for(int phyRows = 0; phyRows < sheet.getPhysicalNumberOfRows(); phyRows++)
		{
//			Row currentRow = rowIterator.next();
			
			Row currentRow = sheet.getRow(phyRows);

			DataFormatter formatter = new DataFormatter();
			/**
			 * Commented here as the number of cells exceeded. Therefore, picking only
			 * interested cells.
			 */

			// int physicalCells = currentRow.getPhysicalNumberOfCells();

			if (currentRow.getRowNum() > Constants.ONE) {

				int physicalCells = Constants.FOUR;
				boolean resetFlag = false;
				for (int colRange = 0; colRange < physicalCells; colRange++) {

					Cell currentCell = currentRow.getCell(colRange);

					if (!isCellBlank(currentCell)) {

						/*
						 * log.debug(currentCell.getAddress() + "  " +
						 * formatter.formatCellValue(currentCell) + " isMerged :: = " +
						 * isMergedCell(sheet, currentCell));
						 */

						if (currentCell.getColumnIndex() == Constants.ONE) {
							if (isMergedCell(sheet, currentCell)) {
								policyName = currentCell.getStringCellValue();
								resetFlag = true;
							} else {
								policyName = formatter.formatCellValue(currentCell);
								resetFlag = true;
								policyParams = new ArrayList<PolicyParameter>();
								enabled = currentRow.getCell(currentCell.getColumnIndex()).getStringCellValue();
								if (enabled.equalsIgnoreCase(Constants.ENABLED)) {
									policyParams.add(new PolicyParameter(
											formatter.formatCellValue(
													currentRow.getCell(currentCell.getColumnIndex() + Constants.TWO)),
											formatter.formatCellValue(currentRow
													.getCell(currentCell.getColumnIndex() + Constants.THREE))));
									PolicyType policyType = determinePolicyType(policyName);

									policyDetail.addPolicy(policyType, policyCreator(policyType, policyParams), true);
								}
							}
						} else if(currentCell.getColumnIndex() == Constants.TWO){
							enabled = currentRow.getCell(currentCell.getColumnIndex()).getStringCellValue();
//							System.out.println("Value of " +currentCell.getAddress() + " enabled is >>>>   " + enabled);
							
						} else if (currentCell.getColumnIndex() == Constants.THREE) {

							int mergedRegions = sheet.getNumMergedRegions();

							for (int i = 0; i < mergedRegions; i++) {
								CellRangeAddress cellAddress = sheet.getMergedRegion(i);
								
								System.out.println( cellAddress);

								CellRangeAddress newAddress = new CellRangeAddress(cellAddress.getFirstRow(),
										cellAddress.getLastRow(), Constants.THREE, physicalCells - 1);
								
								enabled = currentRow.getCell(currentCell.getColumnIndex()-1).getStringCellValue();
								
//								System.out.println("ENABLED Value "+enabled + "   " + currentCell.getAddress());
								
								if (newAddress.isInRange(currentCell.getRowIndex(), currentCell.getColumnIndex())
										&& enabled.equalsIgnoreCase(Constants.ENABLED)) {
									policyParams.add(new PolicyParameter(formatter.formatCellValue(currentCell),
											formatter.formatCellValue(
													currentRow.getCell(currentCell.getColumnIndex() + Constants.ONE))));
									PolicyType policyType = determinePolicyType(policyName);
									policyDetail.addPolicy(policyType, policyCreator(policyType, policyParams), true);
									break;
								} else {
									if (resetFlag) {
										policyParams = new ArrayList<PolicyParameter>();
									}
									continue;
								}
							}
						}
					}
				}
			}
		}

		// return policies;
		return policyDetail;
	}

	public boolean isCellBlank(Cell currentCell) {
		return (currentCell == null || currentCell.getCellType() == Cell.CELL_TYPE_BLANK);
	}

	private PolicyType determinePolicyType(String policyName) {

		PolicyType policyType = PolicyType.NON_EXISTENT;

		if (StringUtils.containsIgnoreCase(policyName, PolicyType.QUOTA.toString())) {
			policyType = PolicyType.QUOTA;
		} else if (StringUtils.containsIgnoreCase(policyName, PolicyType.THROTTLING.toString())) {
			policyType = PolicyType.THROTTLING;
		} else if (StringUtils.containsIgnoreCase(policyName, PolicyType.RATE_LIMIT.toString())) {
			policyType = PolicyType.RATE_LIMIT;
		} else if (StringUtils.containsIgnoreCase(policyName, PolicyType.CLIENT_ID_ENFORCEMENT.toString())) {
			policyType = PolicyType.CLIENT_ID_ENFORCEMENT;
		} else if (StringUtils.containsIgnoreCase(policyName, PolicyType.HEADER_INJECTION.toString())) {
			policyType = PolicyType.HEADER_INJECTION;
		} else if (StringUtils.containsIgnoreCase(policyName, PolicyType.HEADER_REMOVAL.toString())) {
			policyType = PolicyType.HEADER_REMOVAL;
		} else if (StringUtils.containsIgnoreCase(policyName, PolicyType.JSON_THREAT_PROTECTION.toString())) {
			policyType = PolicyType.JSON_THREAT_PROTECTION;
		} else if (StringUtils.containsIgnoreCase(policyName, PolicyType.MESSAGE_LOGGING.toString())) {
			policyType = PolicyType.MESSAGE_LOGGING;
		} else if (StringUtils.containsIgnoreCase(policyName, PolicyType.RESPONSE_CACHING.toString())) {
			policyType = PolicyType.RESPONSE_CACHING;
		} else if (StringUtils.containsIgnoreCase(policyName, PolicyType.SPIKE_CONTROL.toString())) {
			policyType = PolicyType.SPIKE_CONTROL;
		} else if (StringUtils.containsIgnoreCase(policyName, PolicyType.XML_THREAT_PROTECTION.toString())) {
			policyType = PolicyType.XML_THREAT_PROTECTION;
		} else if (StringUtils.containsIgnoreCase(policyName, PolicyType.API_KEY.toString())) {
			policyType = PolicyType.API_KEY;
		}
		return policyType;
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

	public Policy policyCreator(PolicyType policyType, List<PolicyParameter> params) {
		
		System.out.println(policyType.toString());
		return PolicyFactory.buildPolicy(policyType, params);

	}

}
