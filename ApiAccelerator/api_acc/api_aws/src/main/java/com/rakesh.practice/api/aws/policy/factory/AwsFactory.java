package com.rakesh.practice.api.aws.policy.factory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakesh.practice.api.aws.factory.AwsConnectionUtil;
import com.rakesh.practice.api.aws.json.model.AwsApiPolicy;
import com.rakesh.practice.api.aws.json.model.AwsApiStage;
import com.rakesh.practice.api.aws.json.model.AwsPatchOperation;
import com.rakesh.practice.api.aws.json.model.AwsQuota;
import com.rakesh.practice.api.aws.json.model.AwsResponseCache;
import com.rakesh.practice.api.aws.json.model.AwsThrottle;
import com.rakesh.practice.api.aws.json.model.AwsThrottlingModel;
import com.rakesh.practice.api.aws.json.model.AwsThrottlingPolicy;
import com.rakesh.practice.api.aws.json.model.AwsThrottlingVariables;
import com.rakesh.practice.api.aws.json.model.AwsVariables;
import com.rakesh.practice.api.aws.json.model.ConnectionEndPoint;
import com.rakesh.practice.api.commons.constant.Constants;
import com.rakesh.practice.api.commons.enums.APIState;
import com.rakesh.practice.api.commons.enums.PolicyType;
import com.rakesh.practice.api.commons.enums.ProductType;
import com.rakesh.practice.api.commons.exception.ApiException.PolicyApplicationException;
import com.rakesh.practice.api.commons.exception.BaseException;
import com.rakesh.practice.api.commons.factory.ApiProductFactory;
import com.rakesh.practice.api.commons.model.ApiResponse;
import com.rakesh.practice.api.commons.model.ApiServerResponse;
import com.rakesh.practice.api.commons.model.ApiDetail;
import com.rakesh.practice.api.commons.model.CanonicalAPIProxyModel;
import com.rakesh.practice.api.commons.model.Policy;
import com.rakesh.practice.api.commons.model.PolicyDetail;
import com.rakesh.practice.api.commons.model.Product;
import com.rakesh.practice.api.commons.model.RemoteServiceResponse;
import com.rakesh.practice.api.commons.policy.model.ApiKeyPolicy;
import com.rakesh.practice.api.commons.policy.model.ClientIdEnforcementPolicy;
import com.rakesh.practice.api.commons.policy.model.HeaderInjectionPolicy;
import com.rakesh.practice.api.commons.policy.model.QuotaPolicy;
import com.rakesh.practice.api.commons.policy.model.RateLimitPolicy;
import com.rakesh.practice.api.commons.policy.model.ResponseCachingPolicy;
import com.rakesh.practice.api.commons.policy.model.ThrottlingPolicy;
import com.rakesh.practice.api.commons.util.FileUtils;
import com.rakesh.practice.api.commons.util.ResponseUtil;

@Component("awsFactory")
public class AwsFactory extends ApiProductFactory {

	private static final Logger logger = LogManager.getLogger(AwsFactory.class);

	@Value("${aws.baseUrl}")
	private String awsBaseUrl;

	/*
	 * @Value("${aws.get.resources.url}") private String getResourceUrl;
	 */

	@Value("${aws.accessKey}")
	private String awsAccessKey;

	@Value("${aws.secretKey}")
	private String awsSecretKey;

	@Value("${aws.regionName}")
	private String regionName;

	@Value("${aws.serviceName}")
	private String serviceName;

	@Value("${aws.create.api.url}")
	private String createApiUrl;

	@Value("${aws.get.resources.url}")
	private String getResourceUrl;

	@Value("${aws.add.endpoint.url}")
	private String endPointUrl;

	@Value("${aws.clientId.enforcement.url}")
	private String clientIdEnforcementUrl;

	@Value("${aws.deployment.url}")
	private String deploymentUrl;

	@Value("${aws.usageplan.url}")
	private String usagePlanUrl;

	@Value("${aws.swagger.location:DEFAULT}")
	private String swaggerLocation;

	private ThrottlingPolicy throttlingPolicy = null;
	private ClientIdEnforcementPolicy clientIdEnforcementPolicy = null;
	private RateLimitPolicy rateLimitPolicy = null;
	private QuotaPolicy quotaPolicy = null;
	private ApiKeyPolicy apiKeyPolicy = null;
	private HeaderInjectionPolicy headerInjectionPolicy = null;
	private ResponseCachingPolicy responseCachingPolicy = null;

	private ObjectMapper mapper = null;

	private static final String STAGE_NAME = "DEV";

	
	/* (non-Javadoc)
	 * @see com.rakesh.practice.api.commons.factory.ApiProductFactory#createAndApplyPolicy(com.rakesh.practice.api.commons.model.CanonicalAPIProxyModel)
	 */
	@Override
	public List<ApiServerResponse> createAndApplyPolicy(CanonicalAPIProxyModel apiProxyModel) throws Exception {

		logger.info("API creation and Policy application for Amazon API Gateway started ...");

		PolicyDetail policyDetail = apiProxyModel.getPolicyDetail();
		Product product = apiProxyModel.getProduct();

		Map<PolicyType, Policy> policies = policyDetail.getPolicies();

		List<ApiServerResponse> serverResponseList = new ArrayList<ApiServerResponse>();

		for (ApiDetail apiDetail : apiProxyModel.getApiDetails()) {

			if (apiDetail.getProductType() == ProductType.AWS
					&& !StringUtils.isBlank(apiDetail.getSpecificationPath())) {

				ApiServerResponse serverResponse = new ApiServerResponse();
				List<ApiResponse> responseList = new ArrayList<ApiResponse>();

				serverResponse.setApiDetail(apiDetail);

				RemoteServiceResponse awsResponse = createApi(apiDetail, product);

				/*
				 * RemoteServiceResponse awsResponse = new RemoteServiceResponse();
				 * awsResponse.setApiId("hj38klw27f");
				 * awsResponse.setHttpStatus(HttpStatus.CREATED);
				 */

				responseList.add(ResponseUtil.getResponse(PolicyType.CREATE, awsResponse));

				if (null != awsResponse) {
					if (awsResponse.getHttpStatus().is2xxSuccessful() && null != awsResponse.getApiId()) {

						String apiId = awsResponse.getApiId();
						serverResponse.setApiState(APIState.SUCCESS);

						RemoteServiceResponse resourceFromApiResponse = getResourceFromApiId(apiId, product);

						// responseList.add(ResponseUtil.getResponse(PolicyType.FETCH,
						// resourceFromApiResponse));

						String resourceResponse = resourceFromApiResponse.getHttpResponse().toString();
						List<String> resourceMethodURLs = fetchResourceMethods(resourceResponse);
						for (String resourceMethodUrl : resourceMethodURLs) {
							RemoteServiceResponse addConnResponse = addConnectionEndpointToApi(resourceMethodUrl,
									product, apiDetail.getEndPoint());

							// responseList.add(ResponseUtil.getResponse(PolicyType.ADD, addConnResponse));

							if (addConnResponse.getHttpStatus().is2xxSuccessful()) {

								if (policies.containsKey(PolicyType.CLIENT_ID_ENFORCEMENT)) {
									clientIdEnforcementPolicy = (ClientIdEnforcementPolicy) policies
											.get(PolicyType.CLIENT_ID_ENFORCEMENT);
									if (clientIdEnforcementPolicy != null && StringUtils.equalsIgnoreCase(
											clientIdEnforcementPolicy.isEnabled(), Constants.ENABLED)) {
										awsResponse = applyClientEnforcementPolicy(apiId, resourceMethodUrl, product,
												clientIdEnforcementPolicy);
										responseList.add(ResponseUtil.getResponse(PolicyType.CLIENT_ID_ENFORCEMENT,
												awsResponse));
									}
								}

								if (policies.containsKey(PolicyType.API_KEY)) {
									apiKeyPolicy = (ApiKeyPolicy) policies.get(PolicyType.API_KEY);
									if (apiKeyPolicy != null && StringUtils
											.equalsIgnoreCase(apiKeyPolicy.getIsEnabled(), Constants.ENABLED)) {
										awsResponse = applyApiKeyPolicy(apiId, resourceMethodUrl, product,
												apiKeyPolicy);
										responseList.add(ResponseUtil.getResponse(PolicyType.API_KEY, awsResponse));
									}
								}

								if (policies.containsKey(PolicyType.HEADER_INJECTION)) {
									headerInjectionPolicy = (HeaderInjectionPolicy) policies
											.get(PolicyType.HEADER_INJECTION);
									if (headerInjectionPolicy != null) {
										awsResponse = applyHeaderInjectionPolicy(apiId, resourceMethodUrl, product,
												headerInjectionPolicy, apiDetail.getEndPoint());
										responseList.add(
												ResponseUtil.getResponse(PolicyType.HEADER_INJECTION, awsResponse));
									}
								}
							}
						}

						if (policies.containsKey(PolicyType.THROTTLING) || policies.containsKey(PolicyType.QUOTA)) {
							throttlingPolicy = (ThrottlingPolicy) policies.get(PolicyType.THROTTLING);
							quotaPolicy = (QuotaPolicy) policies.get(PolicyType.QUOTA);

							if (throttlingPolicy != null || quotaPolicy != null) {
								awsResponse = deployApiToEnv(apiId, product, STAGE_NAME);
								responseList.add(ResponseUtil.getResponse(PolicyType.DEPLOY, awsResponse));
								if (awsResponse.getHttpStatus().is2xxSuccessful()) {
									String apiName = apiDetail.getApiName();
									awsResponse = applyThrottlingQuotaPolicy(apiName, apiId, product, throttlingPolicy,
											quotaPolicy);
									responseList.add(ResponseUtil.getResponse(PolicyType.THROTTLING, awsResponse));
								}

							}

						}

						if (policies.containsKey(PolicyType.RESPONSE_CACHING)) {
							responseCachingPolicy = (ResponseCachingPolicy) policies.get(PolicyType.RESPONSE_CACHING);

							if (responseCachingPolicy != null) {
								awsResponse = applyResponseCachingPolicy(apiId, product, responseCachingPolicy);
								responseList.add(ResponseUtil.getResponse(PolicyType.RESPONSE_CACHING, awsResponse));
							}
						}
					} else {
						serverResponse.setApiState(APIState.FAILED);
						// throw new
						// ApiException.AuthorizationException(awsResponse.getJsonNode().get("message"));
					}
				}

				serverResponse.setApiResponse(responseList);
				serverResponseList.add(serverResponse);
			}

		}

		logger.info("API creation and Policy application for Amazon API Gateway completed ...");

		return serverResponseList;
	}

	/**
	 * @param apiId
	 * @param product
	 * @param cachingPolicy
	 * @return
	 * @throws MalformedURLException
	 * @throws JsonProcessingException
	 * @throws PolicyApplicationException
	 */
	private RemoteServiceResponse applyResponseCachingPolicy(String apiId, Product product,
			ResponseCachingPolicy cachingPolicy)
			throws MalformedURLException, JsonProcessingException, PolicyApplicationException {

		logger.info("Response Caching Policy Application for Amazon API Gateway started...");
		mapper = new ObjectMapper();

		String depUrl = deploymentUrl + Constants.FORWARD_SLASH + apiId + Constants.FORWARD_SLASH + "deployments";

		URL endpointURL = new URL(depUrl);

		AwsResponseCache cache = new AwsResponseCache();
		cache.setStageName(STAGE_NAME);
		cache.setStageDescription(STAGE_NAME + " stage");
		cache.setDescription("First Deployment");
		cache.setCacheClusterEnabled("true");
		cache.setCacheClusterSize(cachingPolicy.getCacheSize());

		AwsVariables variable = new AwsVariables();
		variable.setSv1("opVar");

		cache.setVariables(variable);

		RemoteServiceResponse remoteServiceResponse = AwsConnectionUtil.postApiResources(getRegionName(product),
				serviceName, getAwsAccessKey(product), getAwsSecretKey(product), endpointURL, null,
				mapper.writeValueAsString(cache));

		return remoteServiceResponse;

	}

	/**
	 * @param apiId
	 * @param resourceMethodUrl
	 * @param product
	 * @param headerPolicy
	 * @param endPointUri
	 * @return
	 * @throws MalformedURLException
	 * @throws JsonProcessingException
	 * @throws PolicyApplicationException
	 */
	private RemoteServiceResponse applyHeaderInjectionPolicy(String apiId, String resourceMethodUrl, Product product,
			HeaderInjectionPolicy headerPolicy, String endPointUri)
			throws MalformedURLException, JsonProcessingException, PolicyApplicationException {

		logger.info("Header Injection Policy Application for Amazon API Gateway started...");

		URL endpointURL;
		endpointURL = new URL(awsBaseUrl + resourceMethodUrl);

		List<AwsPatchOperation> patchOperations = new ArrayList<AwsPatchOperation>();
		AwsPatchOperation patchOperation = null;

		// Adding Request Header
		if (!StringUtils.isEmpty(headerPolicy.getInboundHeader())) {

			String[] requestHeaders = headerPolicy.getInboundHeader().split(Constants.DELIM_SEMI_COLON);
			for (String requestHeader : requestHeaders) {

				String[] keys = requestHeader.split(Constants.DELIM_EQUAL_SIGN);
				for (String key : keys) {

					patchOperation = new AwsPatchOperation();
					patchOperation.setOp("add");
					patchOperation.setPath("/requestParameters/method.request.header." + key);
					patchOperation.setValue("true");

					patchOperations.add(patchOperation); // add one operation into list
					break;
				}
			}

		}

		AwsApiPolicy apiKeyPolicyData = new AwsApiPolicy();
		apiKeyPolicyData.setPatchOperations(patchOperations);
		mapper = new ObjectMapper();

		logger.info("Request Header URL " + endpointURL);

		RemoteServiceResponse remoteServiceResponse = AwsConnectionUtil.patchApiResources(getRegionName(product),
				serviceName, getAwsAccessKey(product), getAwsSecretKey(product), endpointURL,
				mapper.writeValueAsString(apiKeyPolicyData));

		applyIntegrationHeader(apiId, resourceMethodUrl, headerPolicy.getIntegrationHeader(), endPointUri, product);
		return remoteServiceResponse;

	}

	/**
	 * @param apiId
	 * @param resourceMethodUrl
	 * @param integrationHeader
	 * @param endPointUri
	 * @param product
	 * @return
	 * @throws MalformedURLException
	 * @throws JsonProcessingException
	 * @throws PolicyApplicationException
	 */
	private RemoteServiceResponse applyIntegrationHeader(String apiId, String resourceMethodUrl,
			String integrationHeader, String endPointUri, Product product)
			throws MalformedURLException, JsonProcessingException, PolicyApplicationException {

		logger.info("Integration Header Policy Application for Amazon API Gateway started for resource method url::: "
				+ resourceMethodUrl);

		URL endpointURL = new URL(awsBaseUrl + resourceMethodUrl + "/integration");

		ConnectionEndPoint endPoint = new ConnectionEndPoint();
		endPoint.setConnectionType("INTERNET");
		endPoint.setHttpMethod(resourceMethodUrl.substring(resourceMethodUrl.lastIndexOf("/") + 1)); // extract VERB
		Map<String, String> paramMap = new HashMap<String, String>();

		// Adding Request Header
		if (!StringUtils.isEmpty(integrationHeader)) {

			String[] requestHeaders = integrationHeader.split(Constants.DELIM_COMMA_SIGN);
			for (String requestHeader : requestHeaders) {

				String[] keys = requestHeader.split(Constants.DELIM_EQUAL_SIGN);
				for (String key : keys) {

					paramMap.put("integration.request.header." + keys[0].trim(),
							"method.request.header." + keys[1].trim());
					break;

				}
			}

		}

		endPoint.setRequestParameters(paramMap);
		endPoint.setUri(endPointUri);
		endPoint.setType("HTTP");
		mapper = new ObjectMapper();
		RemoteServiceResponse remoteServiceResponse = AwsConnectionUtil.putApiResources(getRegionName(product),
				serviceName, getAwsAccessKey(product), getAwsSecretKey(product), endpointURL,
				mapper.writeValueAsString(endPoint));


		if(remoteServiceResponse.getHttpStatus().is2xxSuccessful() && 
				StringUtils.isNotBlank(product.getAwsApiDeployDecision()) && 
				StringUtils.equalsIgnoreCase(product.getAwsApiDeployDecision(), 
						com.rakesh.practice.api.aws.constant.Constants.AWS_CREATE_AND_DEPLOY)) {
			applyIntegrationResponse(resourceMethodUrl, product);
		}
		
		return remoteServiceResponse;

	}

	/**
	 * @param apiId
	 * @param resourceMethodUrl
	 * @param product
	 * @param apiPolicy
	 * @return
	 * @throws MalformedURLException
	 * @throws JsonProcessingException
	 * @throws PolicyApplicationException
	 */
	private RemoteServiceResponse applyApiKeyPolicy(String apiId, String resourceMethodUrl, Product product,
			ApiKeyPolicy apiPolicy) throws MalformedURLException, JsonProcessingException, PolicyApplicationException {

		logger.info("API Key Policy Application for Amazon API Gateway started...");

		URL endpointURL;
		endpointURL = new URL(awsBaseUrl + resourceMethodUrl);

		List<AwsPatchOperation> patchOperations = new ArrayList<AwsPatchOperation>();

		AwsPatchOperation patchOperation = new AwsPatchOperation();
		patchOperation.setOp("replace");
		patchOperation.setPath("/apiKeyRequired");
		patchOperation.setValue("true");

		patchOperations.add(patchOperation); // add one operation into list

		AwsApiPolicy apiKeyPolicyData = new AwsApiPolicy();
		apiKeyPolicyData.setPatchOperations(patchOperations);

		logger.info("URL " + endpointURL);

		mapper = new ObjectMapper();
		RemoteServiceResponse remoteServiceResponse = AwsConnectionUtil.patchApiResources(getRegionName(product),
				serviceName, getAwsAccessKey(product), getAwsSecretKey(product), endpointURL,
				mapper.writeValueAsString(apiKeyPolicyData));

		return remoteServiceResponse;

	}

	/**
	 * @param apiId
	 * @param product
	 * @return
	 * @throws MalformedURLException
	 */
	public RemoteServiceResponse getResourceFromApiId(String apiId, Product product) throws MalformedURLException {

		logger.info("Fetching Resource From API Id");

		URL endpointURL;
		// String url = MessageFormat.format(env.getProperty("aws.get.resources.url"),
		// apiId);
		String url = MessageFormat.format(getResourceUrl, apiId);

		endpointURL = new URL(url);

		RemoteServiceResponse reourceStatus = AwsConnectionUtil.getApiResources(getRegionName(product), serviceName,
				getAwsAccessKey(product), getAwsSecretKey(product), endpointURL);

		logger.info("Creating Resource from ApiId " + apiId);

		return reourceStatus;
	}

	/**
	 * @param apiDetail
	 * @param product
	 * @return
	 * @throws BaseException
	 * @throws JsonParseException
	 * @throws IOException
	 */
	private RemoteServiceResponse createApi(ApiDetail apiDetail, Product product)
			throws BaseException, JsonParseException, IOException {

		RemoteServiceResponse awsResponse = null;

		URL endpointURL = new URL(createApiUrl);
		String specificationPath = null;
		if (!StringUtils.isBlank(apiDetail.getSpecificationPath())) {
			logger.info("API Creation for Amazon API Gateway started...");
			specificationPath = apiDetail.getSpecificationPath();

			// swaggerLocation;
			String swaggerInput = FileUtils.readFile(specificationPath == null ? swaggerLocation : specificationPath);

			Map<String, String> queryparams = new HashMap<String, String>();
			queryparams.put("mode", "import");
			queryparams.put("failonwarning", "true");
			queryparams.put("basepath", "ignore");

			awsResponse = AwsConnectionUtil.postApiResources(getRegionName(product), serviceName,
					getAwsAccessKey(product), getAwsSecretKey(product), endpointURL, queryparams, swaggerInput);

			logger.info(
					"API Created Response " + awsResponse.getHttpResponse() + " " + awsResponse.getHttpStatusCode());

			mapper = new ObjectMapper();
			JsonFactory factory = mapper.getFactory();
			JsonParser jsonParser = factory.createParser(awsResponse.getHttpResponse().toString());

			JsonNode jsonNode = mapper.readTree(jsonParser);

			String apiId = jsonNode.path("id").asText();
			awsResponse.setApiId(apiId);

			logger.info("Generate API Id after creation of AWS API :::" + apiId);
		}

		return awsResponse;
	}

	/**
	 * @param apiname
	 * @param apiId
	 * @param product
	 * @param policy
	 * @return
	 * @throws JsonProcessingException
	 * @throws MalformedURLException
	 * @throws BaseException
	 */
	protected RemoteServiceResponse applyThrottlingQuotaPolicy(String apiname, String apiId, Product product,
			ThrottlingPolicy throttlingPolicy, QuotaPolicy quotaPolicy)
			throws JsonProcessingException, MalformedURLException, BaseException {

		URL endpointURL = new URL(usagePlanUrl);

		AwsThrottlingModel model = new AwsThrottlingModel();
		model.setDescription("ThrottlingPolicy Policy applied on API ");
		model.setName("ThrottlingPolicy Plan " + apiId + " " + STAGE_NAME);

		List<AwsApiStage> apiStages = new ArrayList<AwsApiStage>();
		AwsApiStage apiStage = new AwsApiStage();
		apiStage.setApiId(apiId);
		apiStage.setStage(STAGE_NAME);

		apiStages.add(apiStage);
		model.setApiStages(apiStages);

		if (throttlingPolicy != null && throttlingPolicy.isPolicyEnabled()) {
			logger.info("Throttling Policy Application for apiId -- " + apiId + " Amazon API Gateway started...");
			AwsThrottle awsThrottle = new AwsThrottle();
			awsThrottle.setBurstLimit(Long.valueOf(throttlingPolicy.getBurstLimit()));
			awsThrottle.setRateLimit(Long.valueOf(throttlingPolicy.getMaximumRequests()));
			model.setThrottle(awsThrottle);
		}

		if (quotaPolicy != null && quotaPolicy.isPolicyEnabled()) {
			logger.info("Quota Policy Application for apiId -- " + apiId + " Amazon API Gateway started...");
			AwsQuota quota = new AwsQuota();
			quota.setLimit(Integer.parseInt(quotaPolicy.getLimit()));
			quota.setPeriod(quotaPolicy.getPeriod());
			model.setQuota(quota);
		}

		model.setName("ThrottlingPolicy Plan_" + apiname + Constants.UNDERSCORE + apiId);

		mapper = new ObjectMapper();
		RemoteServiceResponse remoteServiceResponse = AwsConnectionUtil.postApiResources(getRegionName(product),
				serviceName, getAwsAccessKey(product), getAwsSecretKey(product), endpointURL, null,
				mapper.writeValueAsString(model));

		return remoteServiceResponse;

	}

	/**
	 * @param apiId
	 * @param product
	 * @param stageName
	 * @return
	 * @throws JsonProcessingException
	 * @throws MalformedURLException
	 * @throws BaseException
	 */
	protected RemoteServiceResponse deployApiToEnv(String apiId, Product product, String stageName)
			throws JsonProcessingException, MalformedURLException, BaseException {

		logger.info("Deployment of APIs for apiId -- " + apiId + " -- on Amazon API Gateway started...");

		String depUrl = deploymentUrl + Constants.FORWARD_SLASH + apiId + Constants.FORWARD_SLASH + "deployments";

		URL endpointURL = new URL(depUrl);

		AwsThrottlingPolicy policy = new AwsThrottlingPolicy();

		AwsThrottlingVariables variable = new AwsThrottlingVariables();
		variable.setSv1("opVar");

		policy.setCacheClusterEnabled("false");
		policy.setStageName(stageName);
		policy.setStageDescription(STAGE_NAME + " stage");
		policy.setDescription("First deployment");
		policy.setCacheClusterEnabled("false");
		policy.setVariables(variable);

		mapper = new ObjectMapper();
		RemoteServiceResponse remoteServiceResponse = AwsConnectionUtil.postApiResources(getRegionName(product),
				serviceName, getAwsAccessKey(product), getAwsSecretKey(product), endpointURL, null,
				mapper.writeValueAsString(policy));

		return remoteServiceResponse;

	}

	/**
	 * @param id
	 * @param resourceMethodUrl
	 * @param product
	 * @param policy
	 * @return
	 * @throws MalformedURLException
	 * @throws JsonProcessingException
	 * @throws BaseException
	 */
	protected RemoteServiceResponse applyClientEnforcementPolicy(String id, String resourceMethodUrl, Product product,
			ClientIdEnforcementPolicy policy) throws MalformedURLException, JsonProcessingException, BaseException {

		logger.info("Client ID Enforcement Policy Application for Amazon API Gateway started...");

		URL endpointURL;
		endpointURL = new URL(awsBaseUrl + resourceMethodUrl);

		List<AwsPatchOperation> patchOperations = new ArrayList<AwsPatchOperation>();

		AwsPatchOperation patchOperation = new AwsPatchOperation();
		patchOperation.setOp("replace");
		patchOperation.setPath("/authorizationType");
		patchOperation.setValue("AWS_IAM");

		patchOperations.add(patchOperation); // add one operation into list

		AwsApiPolicy enforcementPolicy = new AwsApiPolicy();
		enforcementPolicy.setPatchOperations(patchOperations);

		logger.info("URL " + endpointURL);

		mapper = new ObjectMapper();
		RemoteServiceResponse remoteServiceResponse = AwsConnectionUtil.patchApiResources(getRegionName(product),
				serviceName, getAwsAccessKey(product), getAwsSecretKey(product), endpointURL,
				mapper.writeValueAsString(enforcementPolicy));

		return remoteServiceResponse;
	}

	
	/**
	 * @param resourceUrl
	 * @param product
	 * @param endPointUri
	 * @return
	 * @throws MalformedURLException
	 * @throws JsonProcessingException
	 * @throws BaseException
	 */
	protected RemoteServiceResponse addConnectionEndpointToApi(final String resourceUrl, final Product product,
			final String endPointUri) throws MalformedURLException, JsonProcessingException, BaseException {

		logger.info("Adding Connection Endpoint to Amazon API Gateway started...");

		URL endpointURL = new URL(awsBaseUrl + resourceUrl + "/integration");

		ConnectionEndPoint endPoint = new ConnectionEndPoint();
		endPoint.setConnectionType("INTERNET");
		endPoint.setHttpMethod(resourceUrl.substring(resourceUrl.lastIndexOf("/") + 1)); // extract VERB
		endPoint.setUri(endPointUri);
		endPoint.setType("HTTP");

		mapper = new ObjectMapper();

		RemoteServiceResponse remoteServiceResponse = AwsConnectionUtil.putApiResources(getRegionName(product),
				serviceName, getAwsAccessKey(product), getAwsSecretKey(product), endpointURL,
				mapper.writeValueAsString(endPoint));
		if(remoteServiceResponse.getHttpStatus().is2xxSuccessful()) {
			applyIntegrationResponse(resourceUrl, product);	
		}

		return remoteServiceResponse;
	}

	/**
	 * @param resourceUrl
	 * @param product
	 * @return
	 * @throws MalformedURLException
	 * @throws JsonProcessingException
	 */
	private RemoteServiceResponse applyIntegrationResponse(final String resourceUrl, final Product product) 
			throws MalformedURLException, JsonProcessingException {

		URL endpointURL = new URL(awsBaseUrl + resourceUrl + "/integration");
		final URL integrationResponseUrl = new URL(endpointURL + "/responses" + "/200");

		AwsFactory.logger.info("Putting Integration Response to Connection Endpoint");

		final String integrationRequest = "{\"statusCode\" : \"200\" }";
		mapper = new ObjectMapper();
		final Object request = mapper.readValue(integrationRequest, Object.class);

		AwsFactory.logger.debug("integrationResponseUrl  " + integrationResponseUrl);

		RemoteServiceResponse apiIntegrationResponse = AwsConnectionUtil.putApiResources(getRegionName(product),
				serviceName, getAwsAccessKey(product), getAwsSecretKey(product), integrationResponseUrl,
				mapper.writeValueAsString(request));

		return apiIntegrationResponse;
	}

	/**
	 * @param resourcePayload
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	protected List<String> fetchResourceMethods(String resourcePayload)
			throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		List<String> resourceUrls = new ArrayList<String>();
		logger.info("Fetching Resource Method from resource Payload");
		JsonNode root = objectMapper.readTree(resourcePayload);

		Iterator<JsonNode> nodeItr = root.path("_embedded").path("item").iterator();
		while (nodeItr.hasNext()) {
			Iterator<JsonNode> itemItr = nodeItr.next().iterator();
			while (itemItr.hasNext()) {

				JsonNode jsonNode = itemItr.next();

				if (jsonNode.isContainerNode()) {
					Iterator<Entry<String, JsonNode>> jsonField = jsonNode.fields();
					while (jsonField.hasNext()) {
						Entry<String, JsonNode> entry = jsonField.next();

						if (entry.getKey() == "resource:methods") {
							if (entry.getValue().isArray()) {
								Iterator<JsonNode> hrefNode = entry.getValue().iterator();
								while (hrefNode.hasNext()) {
									JsonNode nodes = hrefNode.next();
									resourceUrls.add(nodes.get("href").toString().replace("\"", ""));
								}
							}
						}

					}

				}

			}
		}

		return resourceUrls;

	}

	/**
	 * @param product
	 * @return the awsAccessKey
	 */
	private String getAwsAccessKey(Product product) {
		if (StringUtils.isBlank(product.getAwsAccessKey())) {
			return awsAccessKey;
		} else {
			return product.getAwsAccessKey();
		}
	}

	/**
	 * @return the awsSecretKey
	 */
	private String getAwsSecretKey(Product product) {
		if (StringUtils.isBlank(product.getAwsSecretKey())) {
			return awsAccessKey;
		} else {
			return product.getAwsSecretKey();
		}
	}

	/**
	 * @return the regionName
	 */
	private String getRegionName(Product product) {
		if (StringUtils.isBlank(product.getAwsRegionName())) {
			return regionName;
		} else {
			return product.getAwsRegionName();
		}
	}

}
