package com.lonecpp.core.config.ehcache.impl;

import java.io.Serializable;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.lonecpp.core.config.ehcache.CacheService;

/**
 * @author seven sins
 * @date 2018年1月6日 下午6:45:01
 */
@Service
public class CacheServiceImpl implements CacheService {

	static final String CACHE_NAME = "local";
	
	@Override
	@CachePut(value = CACHE_NAME, key = "'key_'+#key")
	public Object add(Serializable key, Object object) {
		return object;
	}

	/**
	 * 从本地缓存中自动获取, 如果获取失败返回null
	 */
	@Override
	@Cacheable(value = CACHE_NAME, key = "'key_'+#key")
	public Object get(Serializable key) {
		return null;
	}

}
