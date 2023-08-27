package com.rakesh.practice.api.web.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.rakesh.practice.api.web.model.User;

@Configuration
@ConfigurationProperties(prefix="api.web")
@PropertySource("user.properties")
public class UserConfig {
	
	private List<User> users;

	/**
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	

}
