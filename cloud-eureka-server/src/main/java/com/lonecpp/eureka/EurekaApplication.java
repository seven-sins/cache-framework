package com.lonecpp.eureka;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

/**
 * 注册中心
 * @author seven sins
 * @date 2018年1月18日 下午10:46:40
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(EurekaApplication.class, args);
	}
	
	@Bean
	public Filter webRequestLoggingFilter() {
	    return new CommonsRequestLoggingFilter();
	}
}
