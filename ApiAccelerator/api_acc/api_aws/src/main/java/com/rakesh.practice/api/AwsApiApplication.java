package com.rakesh.practice.api;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.rakesh.practice.api.aws.policy.factory.AwsFactory;

@SpringBootApplication
@ComponentScan(basePackages = {"com.rakesh.practice.api" })
@EnableConfigurationProperties
public class AwsApiApplication {
	
	public static void main(String[] args) throws Exception{
		try {
			
			System.setProperty("spring.devtools.restart.enabled", "false");
			SpringApplication appCtxt = new SpringApplication(AwsApiApplication.class);
			appCtxt.run(args);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
