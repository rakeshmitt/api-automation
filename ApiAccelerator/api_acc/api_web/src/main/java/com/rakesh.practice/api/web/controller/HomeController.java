package com.rakesh.practice.api.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.rakesh.practice.api.commons.constant.Constants;
import com.rakesh.practice.api.commons.enums.APIState;
import com.rakesh.practice.api.commons.enums.ProductType;
import com.rakesh.practice.api.commons.exception.BaseException;
import com.rakesh.practice.api.commons.model.ApiResponse;
import com.rakesh.practice.api.commons.model.ApiServerResponse;
import com.rakesh.practice.api.commons.model.ApiDetail;
import com.rakesh.practice.api.commons.model.ApiError;
import com.rakesh.practice.api.commons.model.CanonicalAPIProxyModel;
import com.rakesh.practice.api.commons.model.Product;
import com.rakesh.practice.api.commons.model.RemoteServiceResponse;
import com.rakesh.practice.api.commons.parser.ApiDocumentParserV2;
import com.rakesh.practice.api.web.handler.RestErrorHandler;
import com.rakesh.practice.api.web.managers.ApiServiceApiManager;
import com.rakesh.practice.api.web.service.ProductVersionService;

@RestController
public class HomeController {
	
	private static final Logger logger = LogManager.getLogger(HomeController.class);

	@Value("${app.upload.dir:${user.home}}")
	private String UPLOADER_LOCATION;
	
	@Value("${api.aws.api.url}")
	private String apiAwsServiceUrl;
	
	@Value("${api.mulesoft.api.url}")
	private String apiMulesoftServiceUrl;
	
	@Autowired
	private ProductVersionService productVersionService;

	@Autowired
	private ApiDocumentParserV2 apiDocumentParser;
	
	@Autowired
	ApiServiceApiManager apiServiceMgr;

	@Autowired
	private Product product;
	
	@RequestMapping("/")
	public ModelAndView mainPage(ModelMap model) {
		logger.info("Main page is loaded");
		model.addAttribute("productNames", productVersionService.getProductNames());
		model.addAttribute("muleDeploymentEnvs", productVersionService.getMuleDeploymentEnvs());
		model.addAttribute("muleApiGatewayVersions", productVersionService.getMuleApiGatewayVersions());
		model.addAttribute("product", product);

		return new ModelAndView("index", model);
	}
		
	@RequestMapping(value = "/overview", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView overview(ModelMap model)
	{
		logger.info("Loading Overview of AAP Application");
	    
		model.addAttribute("productNames", productVersionService.getProductNames());
		model.addAttribute("product", new Product());
		model.addAttribute("showOverview", true);
		
		return new ModelAndView("index", model);
	}

	@PostMapping("/upload")
	@ResponseBody
	public String readPoliciesFromExcel(@RequestParam("file") MultipartFile policiesFile, ModelMap modelMap,
			HttpServletRequest request, @ModelAttribute("product") Product product, BindingResult errors)
			throws IOException {
		
		String fileName = policiesFile.getOriginalFilename();
		logger.info("HomeController started processing of uploaded file " + fileName);

		String apiMessage = "";
		File uploadLoc = null;
		File file = null;
		
		String fileExtension = fileName.substring(fileName.lastIndexOf(".")+1);

		if (policiesFile.isEmpty()) {
			logger.error("The uploaded file is empty.");
			apiMessage = "Please select a file to upload";
			modelMap.addAttribute("errors", "Please select a file to upload");
		} else if (!fileExtension.equals(Constants.XLS) && !fileExtension.equals(Constants.XLSX)) {
			logger.error("Uploaded file extension is "+ fileExtension + ". Kindly upload only the .xls or .xlsx files");
			modelMap.addAttribute("errors", "Please upload a file with .xls or .xlsx extension only");
			apiMessage = "Please upload a file with .xls or .xlsx extension only.";
		} else {
			try {
				uploadLoc = new File(UPLOADER_LOCATION);
				
				if (!uploadLoc.exists()) {
					uploadLoc.mkdirs();
				}
				
				file = new File(uploadLoc + "/" + fileName);
				
				/**
				 * Doing this to ensure that we always have the 
				 * latest file into account even the file is having 
				 * the same name.
				 */
				if(file != null && file.exists()) {
					file.delete();
				}


				String productName = request.getParameter("productName");
				ProductType productType = productVersionService.getProduct(productName);
				product.setProductType(productType);
				
				policiesFile.transferTo(file);
				
				CanonicalAPIProxyModel proxyModel = apiDocumentParser.processExcel(file, product);
				proxyModel.setProduct(product);
				
				ApiDetail apiDetail = proxyModel.getApiFromProductType(productVersionService.getProduct(productName));
				ObjectMapper mapper = new ObjectMapper();
				ObjectWriter writer = mapper.writer().withRootName("apiServerResponse");
				
				try {
					
					String apiResponses = apiServiceMgr.invokeRemoteService(determineServiceUrl(product.getProductType()), proxyModel);
	
					logger.debug(apiResponses);
					
					modelMap.addAttribute("success",
							apiDetail.getApiName()
									+ "--- API has been created with Policies applied successfully for "
									+ productName);
	
					apiMessage = apiResponses;
					logger.info(apiMessage);
				} catch (Exception e) {
					List<ApiServerResponse> apiResponses = new ArrayList<ApiServerResponse>();
					
					if (e instanceof BaseException) {
						apiResponses = buildErrorResponse(((BaseException) e).getRemoteServiceResponse(), apiDetail);
						
						logger.debug(writer.writeValueAsString(apiResponses));
						apiMessage = writer.writeValueAsString(apiResponses);
					}else {
						ApiError apiError = new RestErrorHandler(e);
						apiResponses = buildRestErrorResponse(e, apiError, apiDetail);
						apiMessage = writer.writeValueAsString(apiResponses);
					}
			
					modelMap.addAttribute("errors", apiMessage);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		modelMap.addAttribute("productNames", productVersionService.getProductNames());

		return apiMessage;
	}
	
	private List<ApiServerResponse> buildErrorResponse(RemoteServiceResponse remoteServiceResponse, ApiDetail apiDetail) {
		List<ApiServerResponse> apiResponses = new ArrayList<ApiServerResponse>();
		ApiServerResponse apiServerResponse = new ApiServerResponse();
		
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setApiError(remoteServiceResponse.getApiErrorResponse());
		apiResponse.setHttpStatus(remoteServiceResponse.getHttpStatus());
		
		apiServerResponse.setApiState(APIState.FAILED);
		apiServerResponse.setApiDetail(apiDetail);
		
		List<ApiResponse> apiResponseList = new ArrayList<ApiResponse>();
		apiResponseList.add(apiResponse);
		
		apiServerResponse.setApiResponse(apiResponseList);
		apiResponses.add(apiServerResponse);
		
		return apiResponses;
	}
	
	private List<ApiServerResponse> buildRestErrorResponse(Exception exception, ApiError apiError, ApiDetail apiDetail) {
		List<ApiServerResponse> apiResponses = new ArrayList<ApiServerResponse>();
		ApiServerResponse apiServerResponse = new ApiServerResponse();
		
		ApiResponse apiResponse = new ApiResponse();
		
		apiResponse.setApiError(apiError);
		if(exception instanceof HttpStatusCodeException) {
			HttpStatusCodeException hsce = (HttpStatusCodeException)exception;
			apiResponse.setHttpStatus(hsce.getStatusCode());
		}else {
			apiResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		apiServerResponse.setApiState(APIState.FAILED);
		apiServerResponse.setApiDetail(apiDetail);
		
		List<ApiResponse> apiResponseList = new ArrayList<ApiResponse>();
		apiResponseList.add(apiResponse);
		
		apiServerResponse.setApiResponse(apiResponseList);
		apiResponses.add(apiServerResponse);
		
		return apiResponses;
	}
	
	private String determineServiceUrl(ProductType productType) {
		String serviceUrl = null;
		if(productType.equals(ProductType.AWS)) {
			serviceUrl = apiAwsServiceUrl;
		}else if(productType.equals(ProductType.MULESOFT)) {
			serviceUrl = apiMulesoftServiceUrl;
		}
		
		return serviceUrl;
	}
}
