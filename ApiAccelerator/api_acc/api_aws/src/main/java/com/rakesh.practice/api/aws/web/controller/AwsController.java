package com.rakesh.practice.api.aws.web.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.rakesh.practice.api.aws.policy.factory.AwsFactory;
import com.rakesh.practice.api.commons.model.ApiServerResponse;
import com.rakesh.practice.api.commons.model.CanonicalAPIProxyModel;

@RestController
public class AwsController {

	private static final Logger logger = LogManager.getLogger(AwsController.class);

	@Autowired
	AwsFactory awsFactory;
	
	@PostMapping(path = "/aws/create", consumes = "application/json", produces = "application/json")
	public String createAWSAPIs(@RequestBody CanonicalAPIProxyModel canonicalModel) throws Exception {

		ObjectMapper mapper = new ObjectMapper();

		CanonicalAPIProxyModel model = mapper.readValue(mapper.writeValueAsString(canonicalModel), CanonicalAPIProxyModel.class);
		
		List<ApiServerResponse> apiResponses = awsFactory.createAndApplyPolicy(model);
		
		logger.info("AWS Response ::: ");
		ObjectWriter writer = mapper.writer().withRootName("apiServerResponse");
		logger.info(mapper.writeValueAsString(apiResponses));
		String result = writer.writeValueAsString(apiResponses);
		logger.debug(result);
		
		return result;
	}

}
