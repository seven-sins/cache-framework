package com.lonecpp.sys.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lonecpp.object.sys.po.User;
import com.lonecpp.sys.mapper.UserMapper;
import com.lonecpp.sys.service.UserService;

/**
 * @author seven sins
 * @date 2017年12月30日 下午4:42:21
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;

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

}
