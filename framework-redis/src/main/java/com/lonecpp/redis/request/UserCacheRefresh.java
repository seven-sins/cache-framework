package com.lonecpp.redis.request;

import java.io.Serializable;

import com.lonecpp.core.config.request.Request;
import com.lonecpp.redis.po.User;
import com.lonecpp.redis.service.UserService;

/**
 * 用户缓存刷新
 * @author seven sins
 * @date 2018年1月1日 下午6:04:49
 */
public class UserCacheRefresh implements Request {
	
	UserService userService;
	
	Serializable id;
	
	boolean forceRefresh;
	
	public UserCacheRefresh(Serializable id, UserService userService, boolean forceRefresh) {
		this.id = id;
		this.userService = userService;
		this.forceRefresh = forceRefresh;
	}
	
	@Override
	public void update() {
		// 查询数据
		User user = userService.get(id);
		if(user != null) {
			// 更新缓存
			userService.updateCache(user);
		}
	}

	@Override
	public Serializable getId() {
		return id;
	}

	/**
	 * 读请求1, 写请求2
	 */
	@Override
	public int getType() {
		return 1;
	}

	@Override
	public boolean isForceRefresh() {
		return forceRefresh;
	}

}
