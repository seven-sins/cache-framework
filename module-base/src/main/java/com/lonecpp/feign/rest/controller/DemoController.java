package com.lonecpp.feign.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lonecpp.core.base.BaseController;
import com.lonecpp.core.config.response.Response;

@RestController
public class DemoController extends BaseController {

	@GetMapping("/rest/base/test")
	public Response<String> test(){
		
		return SUCCESS;
	}
}
