package com.rakesh.practice.api.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.rakesh.practice.api.web.config.UserConfig;
import com.rakesh.practice.api.web.model.User;

@Component
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserConfig userConfig;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = findUserByUsername(username);
		UserBuilder userBuilder = null;
		
		if(user != null) {
			userBuilder = org.springframework.security.core.userdetails.User.withUsername(username);
			userBuilder.password(user.getPassword());
			userBuilder.roles(user.getRoles());
		}else {
			throw new UsernameNotFoundException("User Not Found");
		}
		
		return userBuilder.build();
	}

	private User findUserByUsername(String username) {
		for(User user: userConfig.getUsers()) {
			if(user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}

}
