package com.lonecpp.core.config.ehcache;

import java.io.Serializable;

/**
 * 缓存service
 * @author seven sins
 * @date 2018年1月6日 下午6:43:53
 */
public interface CacheService {
	
	/**
	 * 添加
	 * @param key
	 * @param object
	 * @return
	 */
	public Object add(Serializable key, Object object);
	
	/**
	 * 获取
	 * @param key
	 * @return
	 */
	public Object get(Serializable key);
}
