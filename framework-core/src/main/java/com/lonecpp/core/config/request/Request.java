package com.lonecpp.core.config.request;

import java.io.Serializable;

/**
 * 请求接口
 * @author seven sins
 * @date 2018年1月1日 下午5:17:22
 */
public interface Request {

	/**
	 * 更新
	 */
	void update();
	
	/**
	 * 获取Id
	 * @return
	 */
	Serializable getId();
	/**
	 * 1: 读请求, 2: 写请求
	 * @return
	 */
	int getType();
	
	/**
	 * 是否强制刷新缓存
	 * @return
	 */
	boolean isForceRefresh();
}
