package com.lonecpp.feign.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lonecpp.core.config.response.Response;
import com.lonecpp.feign.rest.service.UserService;
import com.lonecpp.object.sys.po.User;

/**
 * @author seven sins
 * @date 2018年1月21日 下午2:50:46
 */
@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/rest/sys/user")
	public Response<User> find(User user) {
		return userService.find(user);
	}
	
	@GetMapping("/rest/sys/user/{id}")
	public Response<User> get(@PathVariable("id") Long id) {
		return userService.get(id);
	}
}
