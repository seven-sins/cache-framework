package com.lonecpp.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lonecpp.core.base.BaseController;
import com.lonecpp.core.config.request.RequestAsyncProcessService;
import com.lonecpp.core.config.response.Response;
import com.lonecpp.redis.po.User;
import com.lonecpp.redis.request.UserCacheRefresh;
import com.lonecpp.redis.request.UserUpdate;
import com.lonecpp.redis.service.UserService;

/**
 * @author seven sins
 * @date 2017年12月30日 下午4:44:21
 */
@RestController
public class UserController extends BaseController {

	@Autowired
	UserService userService;
	@Autowired
	RequestAsyncProcessService requestAsyncProcessService;
	
	@GetMapping("/rest/sys/user")
	public Response<User> find(User user) {
		return new Response<>(200, userService.find(user));
	}
	
	@GetMapping("/rest/sys/user/{id}")
	public Response<User> get(@PathVariable("id") Long id) {
		requestAsyncProcessService.process(new UserCacheRefresh(id, userService, false));
		// 将请求扔给service异步去处理后, 需要while(true)一会儿, 在此处等待
		// 去尝试等待前面有更新和同时缓存刷新的操作, 将最新的数据刷新到缓存中
		// TODO 此处代码后续需优化处理
		long startTime = System.currentTimeMillis();
		long endTime = 0L;
		long waitTime = 0L;
		// 等待超过200ms没有从缓存中获取到结果
		while(true) {
			if(waitTime > 200) {
				break;
			}
			// 尝试去redis中读取一次商品库存数据
			User user = userService.getByCache(id);
			if(user != null) {
				return new Response<>(200, user);
			} else {
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				endTime = System.currentTimeMillis();
				waitTime = endTime - startTime;
			}
		}
		// 直接从数据库获取数据
		User user = userService.get(id);
		if(user != null) {
			// 代码会运行到这里，只有三种情况：
			// 1、就是说，上一次也是读请求，数据刷入了redis，但是redis LRU算法给清理掉了，标志位还是false
			// 所以此时下一个读请求是从缓存中拿不到数据的，再放一个读Request进队列，让数据去刷新一下
			// 2、可能在200ms内，就是读请求在队列中一直积压着，没有等待到它执行（在实际生产环境中，基本是比较坑了）
			// 所以就直接查一次库，然后给队列里塞进去一个刷新缓存的请求
			// 3、数据库里本身就没有，缓存穿透，穿透redis，请求到达mysql库
			requestAsyncProcessService.process(new UserCacheRefresh(id, userService, true));
			
			return new Response<>(200, user);
		}
		
		return new Response<>(404, "未找到数据");
	}
	
	@PostMapping("/rest/sys/user")
	public Response<String> create(@RequestBody User user) {
		userService.insert(user);
		
		return SUCCESS;
	}
	
	@PutMapping("/rest/sys/user/{id}")
	public Response<String> update(@PathVariable("id") Long id, @RequestBody User user) {
		user.setId(id);
		requestAsyncProcessService.process(new UserUpdate(user, userService, false));
		
		return SUCCESS;
	}
}
