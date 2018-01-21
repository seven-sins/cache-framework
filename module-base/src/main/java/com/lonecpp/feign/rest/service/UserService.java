package com.lonecpp.feign.rest.service;

import java.io.Serializable;

import com.lonecpp.core.config.response.Response;
import com.lonecpp.object.sys.po.User;

/**
 * @author seven sins
 * @date 2018年1月21日 下午2:52:15
 */
public interface UserService {

	public Response<User> find(User user);
	
	public Response<User> get(Serializable id);
}
