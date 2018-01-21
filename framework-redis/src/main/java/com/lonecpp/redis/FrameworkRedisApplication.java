package com.lonecpp.redis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lonecpp.core.annotation.AsyncProcessor;
import com.lonecpp.core.annotation.Database;
import com.lonecpp.core.annotation.KafkaConsumer;
import com.lonecpp.core.annotation.RedisCluster;

/**
 * redis集群测试
 * 
 * @author seven sins
 * @date 2018年1月6日 下午5:13:09
 */
@Database
@RedisCluster
@AsyncProcessor
@KafkaConsumer
@SpringBootApplication
@MapperScan("com.lonecpp.redis.mapper")
public class FrameworkRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrameworkRedisApplication.class, args);
	}
}
