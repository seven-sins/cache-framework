package com.lonecpp.quartz.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * quartz配置
 * 
 * @author seven sins
 * @date 2018年1月10日 下午8:16:29
 */
@Configuration
public class QuartzConfig {
	
	@Autowired
	DataSource dataSource;

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		factory.setDataSource(dataSource);
		factory.setQuartzProperties(quartzProperties());
		return factory;
	}

	@Bean
	public Properties quartzProperties() throws IOException {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
		propertiesFactoryBean.afterPropertiesSet();
		return propertiesFactoryBean.getObject();
	}

	/**
	 * quartz初始化监听器
	 * 
	 * @return
	 */
	@Bean
	public QuartzInitializerListener executorListener() {
		try {
			scheduler().getListenerManager().addSchedulerListener(new JobListener());
		} catch (SchedulerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new QuartzInitializerListener();
	}

	/**
	 * 通过SchedulerFactoryBean获取Scheduler的实例
	 * 
	 * @return
	 * @throws IOException
	 */
	@Bean
	public Scheduler scheduler() throws IOException {
		return schedulerFactoryBean().getScheduler();
	}
}
