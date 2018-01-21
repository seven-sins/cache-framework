package com.lonecpp.quartz.service;

import java.util.Map;

import org.quartz.Job;

/**
 * @author seven sins
 * @date 2018年1月10日 下午9:16:30
 */
public interface QuartzService {

	/**
	 * 添加一个定时任务
     * @param jobName          任务名
     * @param jobGroupName     任务组名
     * @param triggerName      触发器名
     * @param triggerGroupName 触发器组名
     * @param jobClass         任务
     * @param cron             时间设置，参考quartz说明文档
     * @param parameterData    参数
     * @Description: 添加一个定时任务
     */
	public void add(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class<? extends Job> jobClass,
			String cron, Map<String, Object> parameterData);
	
	/**
	 * 修改一个任务的触发时间
     * @param jobName
     * @param jobGroupName
     * @param triggerName      触发器名
     * @param triggerGroupName 触发器组名
     * @param cron             时间设置，参考quartz说明文档
     * @Description: 修改一个任务的触发时间
     */
	public void modifyJobTime(String jobName, String jobGroupName, String triggerName, String triggerGroupName,
			String cron);
	
	/**
	 * 删除一个定时任务
     * @param jobName
     * @param jobGroupName
     * @param triggerName
     * @param triggerGroupName
     * @Description: 移除一个任务
     */
	public void remove(String jobName, String jobGroupName, String triggerName, String triggerGroupName);
	
	/**
	 * 启动所有定时任务
     * @Description:启动所有定时任务
     */
	public void start(); 
	
	/**
	 * 关闭所有定时任务
     * @Description:关闭所有定时任务
     */
	public void close();
	
}
