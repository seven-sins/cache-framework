package com.lonecpp.sys.config.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomAuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler { // implements AuthenticationFailureHandler
	static final Logger LOG = Logger.getLogger(CustomAuthenticationFailHandler.class);
	@Value("${auth.browser.loginType:json}")
	String loginType;
	@Autowired
	ObjectMapper objectMapper;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex)
			throws IOException, ServletException {
		
		LOG.info("登录失败");
		if("json".equals(loginType)) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(ex));
		} else {
			super.onAuthenticationFailure(request, response, ex);
		}
	}

}
