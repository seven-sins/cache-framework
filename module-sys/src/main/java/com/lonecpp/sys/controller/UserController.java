package com.lonecpp.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lonecpp.core.base.BaseController;
import com.lonecpp.core.config.response.Response;
import com.lonecpp.object.sys.po.User;
import com.lonecpp.sys.service.UserService;

/**
 * @author seven sins
 * @date 2017年12月30日 下午4:44:21
 */
@RestController
public class UserController extends BaseController {

	@Autowired
	UserService userService;
	
	@GetMapping("/rest/sys/user")
	public Response<User> find(User user) {
		return new Response<>(200, userService.find(user));
	}
	
	@GetMapping("/rest/sys/user/{id}")
	public Response<User> get(@PathVariable("id") Long id) {
		return new Response<>(200, userService.get(id));
	}
	
	@PostMapping("/rest/sys/user")
	public Response<String> create(@RequestBody User user) {
		userService.insert(user);
		
		return SUCCESS;
	}
	
	@PutMapping("/rest/sys/user/{id}")
	public Response<String> update(@PathVariable("id") Long id, @RequestBody User user) {
		user.setId(id);
		userService.update(user);
		
		return SUCCESS;
	}
}
