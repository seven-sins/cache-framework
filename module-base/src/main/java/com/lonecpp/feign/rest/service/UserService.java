package com.lonecpp.feign.rest.service;

import java.io.Serializable;

import com.lonecpp.core.config.response.Response;
import com.lonecpp.object.sys.po.User;

/**
 * @author seven sins
 * @date 2018年1月21日 下午2:52:15
 */
public interface UserService {
	/**
	 * 用户列表查询(RPC)
	 * @param user
	 * @return
	 */
	public Response<User> find(User user);
	/**
	 * 用户查询(RPC)
	 * @param id
	 * @return
	 */
	public Response<User> get(Serializable id);
}
