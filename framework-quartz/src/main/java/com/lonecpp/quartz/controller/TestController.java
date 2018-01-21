package com.lonecpp.quartz.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lonecpp.core.base.BaseController;
import com.lonecpp.quartz.job.TestJob;
import com.lonecpp.quartz.service.QuartzService;

/**
 * quartz测试
 * 
 * @author seven sins
 * @date 2018年1月10日 下午8:31:15
 */
@RestController
public class TestController extends BaseController {

	@Autowired
	QuartzService quartzService;
	
	@GetMapping("/rest/common/feign/test")
	public Object feignTest() {
		return 1;
	}
	
	@GetMapping("/rest/common/quartz/test")
	public Object test() {
		quartzService.remove("jobName", "jobGroupName", "triggerName", "triggerGroupName");
		//
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("jobName", "测试");
		params.put("count", 1);
		
		// 每隔5秒执行一次：*/5 * * * * ?
		// 每隔1分钟执行一次：0 */1 * * * ?
		// 每天23点执行一次：0 0 23 * * ?
		// 每天凌晨1点执行一次：0 0 1 * * ?
		// 每月1号凌晨1点执行一次：0 0 1 1 * ?
		// 每月最后一天23点执行一次：0 0 23 L * ?
		// 每周星期天凌晨1点实行一次：0 0 1 ? * L
		// 在26分、29分、33分执行一次：0 26,29,33 * * * ?
		// 每天的0点、13点、18点、21点都执行一次：0 0 0,13,18,21 * * ?
		quartzService.add("jobName", "jobGroupName", "triggerName", "triggerGroupName", TestJob.class, "0 */1 * * * ?", params);

		return 1;
	}
	
	@GetMapping("/rest/common/quartz/update")
	public Object update() {
		
		quartzService.modifyJobTime("jobName", "jobGroupName", "triggerName", "triggerGroupName", "0 */1 * * * ?");

		return 1;
	}
}
