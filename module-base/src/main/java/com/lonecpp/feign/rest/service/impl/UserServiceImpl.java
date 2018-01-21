package com.lonecpp.feign.rest.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lonecpp.core.config.response.Response;
import com.lonecpp.feign.rest.service.UserService;
import com.lonecpp.feign.rpc.sys.UserInterface;
import com.lonecpp.object.sys.po.User;

/**
 * @author seven sins
 * @date 2018年1月21日 下午2:53:17
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserInterface userInterface;
	
	@Override
	public Response<User> find(User user) {
		return userInterface.find(user);
	}

	@Override
	public Response<User> get(Serializable id) {
		return userInterface.get(Long.parseLong(String.valueOf(id)));
	}

}
