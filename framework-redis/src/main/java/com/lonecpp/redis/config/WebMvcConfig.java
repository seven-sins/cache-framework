package com.lonecpp.redis.config;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.lonecpp.core.listener.InitThreadPool;

/**
 * @author seven sins
 * @date 2018年1月1日 下午4:50:33
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Bean
	public ServletListenerRegistrationBean<InitThreadPool> servletListenerRegistrationBean() {
		ServletListenerRegistrationBean<InitThreadPool> servletListenerRegistrationBean = new ServletListenerRegistrationBean<>();
		servletListenerRegistrationBean.setListener(new InitThreadPool());
		
		return servletListenerRegistrationBean;
	}
	
	//	@Bean
	//	public ServletListenerRegistrationBean<InitKafka> kafkaServletListenerRegistrationBean() {
	//		ServletListenerRegistrationBean<InitKafka> servletListenerRegistrationBean = new ServletListenerRegistrationBean<>();
	//		servletListenerRegistrationBean.setListener(new InitKafka());
	//		
	//		return servletListenerRegistrationBean;
	//	}
}
