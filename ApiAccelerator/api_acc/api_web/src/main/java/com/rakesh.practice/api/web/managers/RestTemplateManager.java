package com.rakesh.practice.api.web.managers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateManager {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
