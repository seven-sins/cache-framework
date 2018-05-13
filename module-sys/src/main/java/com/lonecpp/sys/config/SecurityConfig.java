package com.lonecpp.sys.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.lonecpp.sys.config.handler.CustomAuthenticationFailHandler;
import com.lonecpp.sys.config.handler.CustomAuthenticationSuccessHandler;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${auth.browser.loginPage}")
	String loginPage;
	@Autowired
	CustomAuthenticationSuccessHandler authenticationSuccessHandler;
	@Autowired
	CustomAuthenticationFailHandler authenticationFailHandler;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.formLogin()
			.loginPage("/authentication/require")
			.loginProcessingUrl("/auth/login") // 登录接口
			.successHandler(authenticationSuccessHandler)
			.failureHandler(authenticationFailHandler)
		//http.httpBasic()
			.and()
			.authorizeRequests()
			.antMatchers("/authentication/require", loginPage).permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.csrf().disable(); // 关闭跨站伪造
	}
}
