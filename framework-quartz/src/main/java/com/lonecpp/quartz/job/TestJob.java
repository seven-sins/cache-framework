package com.lonecpp.quartz.job;

import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;

/**
 * 测试
 * @author seven sins
 * @date 2018年1月10日 下午8:59:41
 * 
 * A. @DisallowConcurrentExecution
 * 禁止并发执行多个相同定义的JobDetail, 这个注解是加在Job类上, 但意思并不是不能同时执行多个Job, 
 * 而是不能并发执行同一个Job Definition(由JobDetail定义), 但是可以同时执行多个不同的JobDetail
 * 
 * 假定有一个Job类,叫做TestJob, 并在这个Job上加了这个注解, 然后在这个Job上定义了很多个JobDetail, 
 * 如JobDetail-01, JobDetail-02, 那么当scheduler启动时, 不会并发执行多个JobDetail-01或者JobDetail-02, 
 * 但可以同时执行JobDetail-01跟JobDetail-02
 * 设置@DisallowConcurrentExecution以后程序会等任务执行完毕以后再去执行, 不允许并发执行
 * 
 * B. @PersistJobDataAfterExecution
 * 这个注解是加在Job类上
 * 表示当正常执行完Job后, JobDataMap中的数据应该被改动, 以被下一次调用时用。
 * (1. 如不加这个注解, 修改后的JobDataMap不会被持久化)
 * (2. 使用@PersistJobDataAfterExecution时, 必须也加上@DisallowConcurrentExecution, 否则极易造成JobDataMap数据不同步)
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class TestJob implements Job {

	/**
	 * JobExecutionContext是Job运行的上下文，可以获得Trigger、Scheduler、JobDetail的信息
	 */
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// 同一个JobDetail实例，执行的多个Job实例，是共享同样的JobDataMap
		// 如果你在任务里修改了里面的值，会对其他Job实例（并发的或者后续的）造成影响。
		JobDataMap data = context.getJobDetail().getJobDataMap();
		int count = data.getInt("count");
		count ++;
		System.out.println("=============count: " + count);
		//
		data.put("count", count++);
		
		System.out.println("=============success=============" + new Date());
	}
}
