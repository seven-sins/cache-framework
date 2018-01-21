package com.lonecpp.core.config.redis;

/**
 * @author seven sins
 * @date 2018年1月1日 下午4:13:34
 */
public interface RedisService {

	/**
	 * 添加
	 * 
	 * @param key
	 * @param value
	 */
	public void set(String key, String value);

	/**
	 * 添加
	 * 
	 * @param key
	 * @param value
	 */
	public void set(String key, Object value);

	/**
	 * 查询
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key);

	/**
	 * 删除
	 * 
	 * @param key
	 */
	public void delete(String key);
}
