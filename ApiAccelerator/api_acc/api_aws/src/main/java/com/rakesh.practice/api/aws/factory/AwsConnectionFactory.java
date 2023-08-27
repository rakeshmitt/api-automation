package com.rakesh.practice.api.aws.factory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakesh.practice.api.aws.json.model.AwsApiError;
import com.rakesh.practice.api.commons.model.RemoteServiceResponse;

public class AwsConnectionFactory {

	private static final Logger logger = LogManager.getLogger(AwsConnectionFactory.class);
	
	/**
	 * Makes a http request to the specified endpoint
	 */
	public static RemoteServiceResponse invokeHttpRequest(URL endpointUrl, String httpMethod, Map<String, String> headers,
			String requestBody) {
		HttpURLConnection connection = createHttpConnection(endpointUrl, httpMethod, headers);
		try {
			if (requestBody != null) {
				DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
				wr.writeBytes(requestBody);
				wr.flush();
				wr.close();
			}
		} catch (Exception e) {
			throw new RuntimeException("Request failed. " + e.getMessage(), e);
		}
		return executeHttpRequest(connection);
	}

	public static RemoteServiceResponse executeHttpRequest(HttpURLConnection connection) {

		RemoteServiceResponse awsResponse;

		try {
			

			// Get Response
			InputStream is;
			String line;
			BufferedReader rd;
			try {
				is = connection.getInputStream();
			} catch (IOException e) {
				is = connection.getErrorStream();
			}
			rd = new BufferedReader(new InputStreamReader(is), 10000);
			line = rd.readLine();
			StringBuffer response = new StringBuffer();

			while (line != null) {
				response.append(line);
				response.append('\r');
				line = rd.readLine();
			}
			rd.close();
			ObjectMapper mapper = new ObjectMapper();
			/*
			 * mapper.enableDefaultTyping(
			 * ObjectMapper.DefaultTyping.NON_CONCRETE_AND_ARRAYS);
			 */
			logger.debug("Response "+response.toString());
			awsResponse = mapper.readValue(response.toString(), RemoteServiceResponse.class);
			awsResponse.setHttpStatus(HttpStatus.valueOf(connection.getResponseCode()));
			awsResponse.setHttpStatusCode(String.valueOf(connection.getResponseCode()));
//			awsResponse.setHttpResponseMessage(connection.getResponseMessage());
			awsResponse.setHttpResponse(response.toString());
			awsResponse.setJsonNode(mapper.readValue(response.toString(), JsonNode.class));
			
			if(awsResponse.getHttpStatus().isError()) {
				awsResponse.setApiErrorResponse(mapper.readValue(response.toString(), AwsApiError.class));
			}
			return awsResponse;
		} catch (Exception e) {
			throw new RuntimeException("Request failed. " + e.getMessage(), e);
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	public static HttpURLConnection createHttpConnection(URL endpointUrl, String httpMethod,
			Map<String, String> headers) {
		try {
			HttpURLConnection connection = (HttpURLConnection) endpointUrl.openConnection();

			if (httpMethod.equalsIgnoreCase("PATCH")) {
				connection.setRequestProperty("X-HTTP-Method-Override", "PATCH");
				connection.setRequestMethod("POST");
			} else {
				connection.setRequestMethod(httpMethod);
			}

			if (headers != null) {
				for (String headerKey : headers.keySet()) {
					connection.setRequestProperty(headerKey, headers.get(headerKey));
				}
			}

			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			return connection;
		} catch (Exception e) {
			throw new RuntimeException("Cannot create connection. " + e.getMessage(), e);
		}
	}

}
