package com.lonecpp.core.config.redis.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lonecpp.core.config.redis.RedisService;

import redis.clients.jedis.JedisCluster;

/**
 * @author seven sins
 * @date 2018年1月1日 下午4:06:44
 */
@Service
public class RedisServiceImpl implements RedisService {

	@Autowired
	JedisCluster jedisCluster;
	
	@Override
	public void set(String key, String value) {
		jedisCluster.set(key, value);
	}

	@Override
	public String get(String key) {
		return jedisCluster.get(key);
	}

	@Override
	public void delete(String key) {
		jedisCluster.del(key);
	}

	@Override
	public void set(String key, Object value) {
		jedisCluster.set(key, JSONObject.toJSONString(value));
	}

}
