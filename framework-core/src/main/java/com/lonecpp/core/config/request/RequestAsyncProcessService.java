package com.lonecpp.core.config.request;

/**
 * 请求异步处理
 * @author seven sins
 * @date 2018年1月1日 下午7:04:16
 */
public interface RequestAsyncProcessService {

	/**
	 * 处理请求
	 * @param request
	 */
	void process(Request request);
}
