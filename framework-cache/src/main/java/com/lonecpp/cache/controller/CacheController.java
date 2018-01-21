package com.lonecpp.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lonecpp.cache.po.User;
import com.lonecpp.core.base.BaseController;
import com.lonecpp.core.config.ehcache.CacheService;
import com.lonecpp.core.config.kafka.KafkaProducer;
import com.lonecpp.core.config.response.Response;

/**
 * 缓存控制器
 * @author seven sins
 * @date 2018年1月6日 下午6:56:31
 */
@RestController
public class CacheController extends BaseController {

	@Autowired
	CacheService cacheService;
	@Autowired
	KafkaProducer kafkaProducer;
	
	@GetMapping("/rest/sys/test")
	public Response<?> test() {
		kafkaProducer.send("test msg");
		
		return SUCCESS;
	}
	
	@GetMapping("/rest/sys/user/{id}")
	public Response<?> get(@PathVariable("id") Long id) {
		return new Response<>(200, cacheService.get(id));
	}
	
	@PostMapping("/rest/sys/user")
	public Response<?> create(@RequestBody User user) {
		cacheService.add(user.getId(), user);
		
		return SUCCESS;
	}
}
