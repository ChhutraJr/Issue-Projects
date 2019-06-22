package com.example.issue.issueprojects.configuration.custom;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("customFailureHandler")
public class CustomFailureHandler implements AuthenticationFailureHandler {
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
	
		if(exception.getMessage().equals("User is disabled")){
			System.out.println(exception.getMessage());
			response.sendRedirect("/login?disable");
		}else{
			System.out.println("User has logged with incorrect username or password. MESSAGE===> "+ exception.getMessage());
			response.sendRedirect("/login?error");
		}
		
	}
	
}
