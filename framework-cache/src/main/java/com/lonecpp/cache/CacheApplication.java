package com.lonecpp.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lonecpp.core.annotation.Database;
import com.lonecpp.core.annotation.Ehcache;
import com.lonecpp.core.annotation.KafkaProducer;
import com.lonecpp.core.annotation.RedisCluster;

/**
 * ehcache缓存测试、kafka集群测试
 * 
 * @author seven sins
 * @date 2018年1月6日 下午5:10:05
 */
@Database
@RedisCluster
@Ehcache
@KafkaProducer
@SpringBootApplication
@MapperScan("com.lonecpp.cache.mapper")
public class CacheApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CacheApplication.class, args);
	}
}
