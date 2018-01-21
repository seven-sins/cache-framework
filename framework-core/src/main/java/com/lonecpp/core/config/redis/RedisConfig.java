package com.lonecpp.core.config.redis;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/**
 * redis cluster
 * @author seven sins
 * @date 2018年1月1日 下午3:53:02
 */
@Configuration
public class RedisConfig {

	/**
	 * 从配置文件中读取redis cluster nodes
	 */
	@Value("${spring.redis.cache.clusterNodes}")
	String clusterNodes;
	
	@Bean
	public JedisCluster jedisClusterFactory() {
		String[] nodesArray = clusterNodes.split(",");
		Set<HostAndPort> jedisClusterNodes = new HashSet<>();
		for(String node: nodesArray) {
			String[] item = node.split(":");
			jedisClusterNodes.add(new HostAndPort(item[0], Integer.valueOf(item[1].trim())));
		}
		JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes);
		
		return jedisCluster;
	}
	
}
