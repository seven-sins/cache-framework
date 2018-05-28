package com.lonecpp.sys.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lonecpp.core.base.BaseController;
import com.lonecpp.core.config.response.Response;
import com.lonecpp.sys.config.CustomUserDetailService;

@RestController
public class AuthController extends BaseController {
	static final Logger LOG = Logger.getLogger(AuthController.class);
	@Autowired
	CustomUserDetailService userDetailService;
	@Value("${auth.browser.loginPage}")
	String loginPage;
	
	RequestCache requestCache = new HttpSessionRequestCache();
	
	RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@RequestMapping("/authentication/require")
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public Response<?> requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// /assets/login.html
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if(savedRequest != null) {
			String target = savedRequest.getRedirectUrl();
			LOG.info("跳转请求: " + target);
			if(StringUtils.endsWithIgnoreCase(target, ".html")) {
				redirectStrategy.sendRedirect(request, response, loginPage);
			}
		}
		
		return new Response<>("未登录");
	}
}
