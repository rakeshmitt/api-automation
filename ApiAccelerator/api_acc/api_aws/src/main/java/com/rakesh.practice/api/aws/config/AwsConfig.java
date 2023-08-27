package com.rakesh.practice.api.aws.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:aws.properties")
@ConfigurationProperties

public class AwsConfig {

	/*@Value("${aws.server.port}")
	private String serverPort;

	*//**
	 * @return the serverPort
	 *//*
	public String getServerPort() {
		return serverPort;
	}
	
*/	
	
}
