package com.rakesh.practice.api.web.handler;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class RestAuthenticationFailureHandler implements  AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		response.setStatus(HttpStatus.FORBIDDEN.value());
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("timestamp", new Date());
		data.put("status", HttpStatus.FORBIDDEN.value());
		data.put("message", "Access Denied");
		data.put("path", request.getRequestURL().toString());
		
//		OutputStream out = response.getOutputStream();
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.writeValue(out, data);
//		out.flush();
		
		request.getSession().setAttribute("errorMessage", "Invalid Username or Password..!");
		response.sendRedirect("/api/login?error=true");
	}

}
