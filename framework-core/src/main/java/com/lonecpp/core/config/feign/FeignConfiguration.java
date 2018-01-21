package com.lonecpp.core.config.feign;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

/**
 * feign自定义配置
 * @author seven sins
 * @date 2018年1月21日 下午3:08:56
 */
@Configuration
public class FeignConfiguration {

	//	public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
	//		return new BasicAuthRequestInterceptor("user", "password");
	//	}
	
	//	@Bean
	//	public Contract feignContract() {
	//		return new feign.Contract.Default();
	//	}
	
	@Bean
	public Logger.Level feignLoggerLevel(){
		return Logger.Level.FULL;
	}
}
