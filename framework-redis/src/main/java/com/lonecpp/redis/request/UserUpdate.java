package com.lonecpp.redis.request;

import java.io.Serializable;

import com.lonecpp.core.config.request.Request;
import com.lonecpp.redis.po.User;
import com.lonecpp.redis.service.UserService;

/**
 * 用户更新
 * 1. 删除缓存
 * 2. 更新数据库
 * @author seven sins
 * @date 2018年1月1日 下午6:04:49
 */
public class UserUpdate implements Request {
	
	User user;
	
	UserService userService;
	
	boolean forceRefresh;
	
	public UserUpdate(User user, UserService userService, boolean forceRefresh) {
		this.user = user;
		this.userService = userService;
		this.forceRefresh = forceRefresh;
	}
	
	@Override
	public void update() {
		// 删除缓存
		userService.deleteCache(user);
		// 更新数据库
		userService.update(user);
	}

	@Override
	public Serializable getId() {
		return user.getId();
	}

	/**
	 * 读请求1, 写请求2
	 */
	@Override
	public int getType() {
		return 2;
	}

	@Override
	public boolean isForceRefresh() {
		return forceRefresh;
	}

}
