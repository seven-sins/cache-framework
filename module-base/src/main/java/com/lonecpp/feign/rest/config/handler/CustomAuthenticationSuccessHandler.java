package com.lonecpp.feign.rest.config.handler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler { // implements AuthenticationSuccessHandler
	static final Logger LOG = Logger.getLogger(CustomAuthenticationSuccessHandler.class);
	@Value("${auth.browser.loginType:json}")
	String loginType;
	@Autowired
	ObjectMapper objectMapper;
	@Autowired
	ClientDetailsService clientDetailsService;
	@Autowired
	AuthorizationServerTokenServices authorizationServerTokenServices;
	
	@SuppressWarnings("unchecked")
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {
		LOG.info("登录成功");
		
		String header = request.getHeader("Authorization");
		if(header == null || !header.startsWith("Basic ")) {
			throw new UnapprovedClientAuthenticationException("请求头无client信息");
		}
		
		String[] tokens = extractAndDecodeHeader(header, request);
		assert tokens.length == 2;
		
		String clientId = tokens[0];
		String clientSecret = tokens[1];
		
		ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
		
		if(clientDetails == null) {
			throw new UnapprovedClientAuthenticationException("clientId对应信息不存在");
		} else if(!StringUtils.equals(clientDetails.getClientSecret(), clientSecret)) {
			throw new UnapprovedClientAuthenticationException("clientSecret不匹配");
		}

		TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_MAP, clientId, clientDetails.getScope(), "custom");
		
		OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
		
		OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, auth);
		
		OAuth2AccessToken token = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
		
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(objectMapper.writeValueAsString(token));
	}

	private String[] extractAndDecodeHeader(String header, HttpServletRequest request) throws UnsupportedEncodingException {
		byte[] base64Token = header.substring(6).getBytes("UTF-8");
		byte[] decoded;
		try {
			decoded = Base64.decode(base64Token);
		}catch(IllegalArgumentException e) {
			throw new BadCredentialsException("无效的token");
		}
		
		String token = new String(decoded, "UTF-8");
		int index = token.indexOf(":");
		if(index == -1) {
			throw new BadCredentialsException("无效的token");
		}
		
		return new String[] { token.substring(0, index), token.substring(index + 1) };
	}
	
}
