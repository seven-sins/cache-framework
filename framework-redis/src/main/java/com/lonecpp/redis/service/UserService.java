package com.lonecpp.redis.service;

import java.io.Serializable;

import com.lonecpp.core.base.BaseService;
import com.lonecpp.redis.po.User;

/**
 * @author seven sins
 * @date 2018年1月6日 下午5:13:00
 */
public interface UserService extends BaseService<User> {
	
	/**
	 * 删除缓存
	 * @param user
	 */
	public void deleteCache(User user);
	
	/**
	 * 更新缓存
	 * @param user
	 */
	public void updateCache(User user);
	
	/**
	 * 从缓存中获取数据
	 * @param id
	 * @return
	 */
	public User getByCache(Serializable id);
}
