package com.rakesh.practice.api.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.rakesh.practice.api.web.config.UserConfig;

/**
 * @author Ashish Tiwari
 *
 *         This is the main Spring Boot class which initializes the AAP
 *         application.
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.rakesh.practice.api" })
@PropertySources({ @PropertySource("aws.properties"), @PropertySource("commons.properties"),
		@PropertySource("mulesoft.properties") })
@EnableConfigurationProperties(UserConfig.class)
public class ApiWebApplication {

	public static void main(String[] args) {
		// System.setProperty("server.servlet.context-path", "/api");
		ApplicationContext appCtxt = SpringApplication.run(ApiWebApplication.class, args);
	}

}
