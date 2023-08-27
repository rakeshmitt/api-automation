package com.rakesh.practice.api.aws.factory;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rakesh.practice.api.aws.auth.AWS4SignerBase;
import com.rakesh.practice.api.aws.auth.AWS4SignerForAuthorizationHeader;
import com.rakesh.practice.api.commons.model.RemoteServiceResponse;
import com.rakesh.practice.api.commons.util.BinaryUtils;
import com.rakesh.practice.api.commons.util.HttpUtils;

public class AwsConnectionUtil {
	
	private static final Logger logger = LogManager.getLogger(AwsConnectionUtil.class);

	public static RemoteServiceResponse getApiResources(String regionName, String serviceName, String awsAccessKey,
			String awsSecretKey, URL endpointURL) {

		// the region-specific endpoint to the target object expressed in path style

		// for a simple GET, we have no body so supply the precomputed 'empty' hash
		Map<String, String> headers = new HashMap<String, String>();
		// headers.put("x-amz-content-sha256", AWS4SignerBase.EMPTY_BODY_SHA256);

		AWS4SignerForAuthorizationHeader signer = new AWS4SignerForAuthorizationHeader(endpointURL, "GET", serviceName,
				regionName);
		String authorization = signer.computeSignature(headers, null, // no query parameters
				AWS4SignerBase.EMPTY_BODY_SHA256, awsAccessKey, awsSecretKey);

		// place the computed signature into a formatted 'Authorization' header
		// and call S3
		headers.put("Authorization", authorization);
		logger.info("Fetching from  ::: " + endpointURL);

		return AwsConnectionFactory.invokeHttpRequest(endpointURL, "GET", headers, null);
		/*
		 * String response = awsResponse.getHttpResponse();
		 * System.out.println("--------- Response content ---------");
		 * System.out.println(response);
		 * System.out.println("------------------------------------");
		 */
	}

	public static RemoteServiceResponse postApiResources(String regionName, String serviceName, String awsAccessKey,
			String awsSecretKey, URL endpointURL, Map<String, String> queryParams, String data) {

		// precompute hash of the body content
		byte[] contentHash = AWS4SignerBase.hash(data);
		String contentHashString = BinaryUtils.toHex(contentHash);

		Map<String, String> headers = new HashMap<String, String>();

		headers.put("content-type", "application/x-amz-json-1.0");

		AWS4SignerForAuthorizationHeader signer = new AWS4SignerForAuthorizationHeader(endpointURL, "POST", serviceName,
				regionName);
		String authorization = signer.computeSignature(headers, queryParams, contentHashString, awsAccessKey,
				awsSecretKey);

		// place the computed signature into a formatted 'Authorization' header

		headers.put("Authorization", authorization);
		logger.info("POSTing to endpoint ::: " + endpointURL);
		logger.info("Request " + data);
		
		RemoteServiceResponse awsResponse = AwsConnectionFactory.invokeHttpRequest(endpointURL, "POST", headers, data);
		return awsResponse;
	}

	public static RemoteServiceResponse putApiResources(String regionName, String serviceName, String awsAccessKey,
			String awsSecretKey, URL endpointURL, String data) {

		/*
		 * String filename =
		 * "D:\\AAP\\Feasibility Docs\\AWS\\sampleSwagger_Metering.txt"; String
		 * swaggerInput = com.rakesh.practice.accelerator.api.util.FileUtils.readFile(filename);
		 */

		// precompute hash of the body content
		byte[] contentHash = AWS4SignerBase.hash(data);
		String contentHashString = BinaryUtils.toHex(contentHash);

		Map<String, String> headers = new HashMap<String, String>();

		headers.put("content-type", "application/json");

		Map<String, String> queryparams = new HashMap<String, String>();
		queryparams.put("mode", "import");
		queryparams.put("failonwarning", "true");
		queryparams.put("basepath", "ignore");

		AWS4SignerForAuthorizationHeader signer = new AWS4SignerForAuthorizationHeader(endpointURL, "PUT", serviceName,
				regionName);
		String authorization = signer.computeSignature(headers, null, contentHashString, awsAccessKey, awsSecretKey);

		// place the computed signature into a formatted 'Authorization' header

		headers.put("Authorization", authorization);
		
		logger.info("PUTing to endpoint ::: " + endpointURL);
		logger.info("Request " + data);
		
		RemoteServiceResponse awsResponse = AwsConnectionFactory.invokeHttpRequest(endpointURL, "PUT", headers, data);
		return awsResponse;
	}

	public static RemoteServiceResponse patchApiResources(String regionName, String serviceName, String awsAccessKey,
			String awsSecretKey, URL endpointURL, String data) {

		// precompute hash of the body content
		byte[] contentHash = AWS4SignerBase.hash(data);
		String contentHashString = BinaryUtils.toHex(contentHash);

		Map<String, String> headers = new HashMap<String, String>();

		headers.put("content-type", "application/json");

		Map<String, String> queryparams = new HashMap<String, String>();
		queryparams.put("mode", "import");
		queryparams.put("failonwarning", "true");
		queryparams.put("basepath", "ignore");

		AWS4SignerForAuthorizationHeader signer = new AWS4SignerForAuthorizationHeader(endpointURL, "PATCH",
				serviceName, regionName);
		String authorization = signer.computeSignature(headers, null, contentHashString, awsAccessKey, awsSecretKey);

		// place the computed signature into a formatted 'Authorization' header

		headers.put("Authorization", authorization);
		
		logger.info("PATCHing to endpoint ::: " + endpointURL);
		logger.info("Request " + data);
		
		RemoteServiceResponse awsResponse = AwsConnectionFactory.invokeHttpRequest(endpointURL, "PATCH", headers, data);
		return awsResponse;
	}

}
