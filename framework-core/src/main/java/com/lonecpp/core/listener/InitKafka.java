package com.lonecpp.core.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 初始化kafka
 * 
 * @author seven sins
 * @date 2018年1月1日 下午4:55:35
 */
public class InitKafka implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("=============kafka初始化=============");
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {

	}

}
