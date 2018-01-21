package com.lonecpp.redis.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lonecpp.core.config.redis.RedisService;
import com.lonecpp.redis.mapper.UserMapper;
import com.lonecpp.redis.po.User;
import com.lonecpp.redis.service.UserService;

/**
 * @author seven sins
 * @date 2017年12月30日 下午4:42:21
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	@Autowired
	RedisService redisService;

	@Override
	public List<User> find(User entity) {
		return userMapper.find(entity);
	}

	@Override
	public int count(User entity) {
		return userMapper.count(entity);
	}

	@Override
	public User get(Serializable id) {
		return userMapper.get(id);
	}

	@Override
	public void insert(User entity) {
		userMapper.insert(entity);
	}

	@Override
	public void update(User entity) {
		userMapper.update(entity);
	}

	@Override
	public void deleteById(Serializable id) {
		userMapper.deleteById(id);
	}

	@Override
	public void delete(Serializable[] ids) {
		userMapper.delete(ids);
	}

	@Override
	public void deleteCache(User user) {
		redisService.delete(String.valueOf(user.getId()));
	}

	@Override
	public void updateCache(User user) {
		// 更新缓存
		redisService.set(user.getId().toString(), user);
	}

	@Override
	public User getByCache(Serializable id) {
		// 从缓存中获取
		String str = redisService.get(String.valueOf(id));
		if(str != null && !"".equals(str)) {
			return JSONObject.parseObject(str, User.class);
		}
		return null;
	}

}
