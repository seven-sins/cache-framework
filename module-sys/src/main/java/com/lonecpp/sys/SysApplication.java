package com.lonecpp.sys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.lonecpp.core.annotation.Database;

/**
 * 接口测试模块
 * @author seven sins
 * @date 2018年1月21日 下午2:23:01
 * 
 * 通过 @EnableEurekaClient 注解，为服务提供方赋予注册和发现服务的能力
 * 也可以使用org.springframework.cloud.client.discovery.@EnableDiscoveryClient注解
 */
@Database
@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.lonecpp.sys.mapper")
public class SysApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SysApplication.class, args);
	}
}
