package com.lonecpp.core.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.lonecpp.core.config.request.RequestProcessorThreadPool;

/**
 * 初始化线程池
 * 
 * @author seven sins
 * @date 2018年1月1日 下午4:55:35
 */
public class InitThreadPool implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("=============系统初始化=============");
		// 初始化线程池
		RequestProcessorThreadPool.init();
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {

	}

}
