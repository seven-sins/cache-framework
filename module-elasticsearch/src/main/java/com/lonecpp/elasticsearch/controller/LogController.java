package com.lonecpp.elasticsearch.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author seven sins
 * @date 2018年2月3日 下午3:19:26
 */
@RestController
public class LogController {
	
	static final Logger LOG = Logger.getLogger(LogController.class);
	
	@GetMapping("/log/test")
	public Object logTest() {
		LOG.info("{\"userName\":\"abc\"}");
		return 1;
	}
}
