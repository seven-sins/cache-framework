package com.lonecpp.feign.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * 接口测试模块
 * @author seven sins
 * @date 2018年1月21日 下午2:23:01
 */
@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients(basePackages = "com.lonecpp.feign.rpc.sys")
public class BaseApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BaseApplication.class, args);
	}
}