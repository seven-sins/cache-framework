package com.lonecpp.feign.rpc.sys;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lonecpp.core.config.feign.FeignConfiguration;
import com.lonecpp.core.config.response.Response;
import com.lonecpp.object.sys.po.User;

/**
 * @author seven sins
 * @date 2018年1月21日 下午2:46:30
 */
@FeignClient(name = "module-sys", configuration = FeignConfiguration.class)
public interface UserInterface {
	/**
	 * 用户列表查询RPC
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/rest/sys/user", method = RequestMethod.GET)
	public Response<User> find(User user);
	/**
	 * 用户查询RPC
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/rest/sys/user/{id}", method = RequestMethod.GET)
	public Response<User> get(@PathVariable("id") Long id);
}
