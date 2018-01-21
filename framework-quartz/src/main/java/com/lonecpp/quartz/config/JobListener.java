package com.lonecpp.quartz.config;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.SchedulerListener;
import org.quartz.Trigger;
import org.quartz.TriggerKey;

public class JobListener implements SchedulerListener {

	@Override
	public void jobScheduled(Trigger trigger) {

	}

	@Override
	public void jobUnscheduled(TriggerKey triggerKey) {

	}

	@Override
	public void triggerFinalized(Trigger trigger) {

	}

	@Override
	public void triggerPaused(TriggerKey triggerKey) {

	}

	@Override
	public void triggersPaused(String triggerGroup) {

	}

	@Override
	public void triggerResumed(TriggerKey triggerKey) {

	}

	@Override
	public void triggersResumed(String triggerGroup) {

	}

	@Override
	public void jobAdded(JobDetail jobDetail) {
		System.out.println("=============add");
	}

	@Override
	public void jobDeleted(JobKey jobKey) {
		System.out.println("=============remove");
	}

	@Override
	public void jobPaused(JobKey jobKey) {

	}

	@Override
	public void jobsPaused(String jobGroup) {

	}

	@Override
	public void jobResumed(JobKey jobKey) {

	}

	@Override
	public void jobsResumed(String jobGroup) {

	}

	@Override
	public void schedulerError(String msg, SchedulerException cause) {
		System.out.println("=============exception");
	}

	@Override
	public void schedulerInStandbyMode() {

	}

	@Override
	public void schedulerStarted() {
		System.out.println("=============start");
	}

	@Override
	public void schedulerStarting() {

	}

	@Override
	public void schedulerShutdown() {

	}

	@Override
	public void schedulerShuttingdown() {

	}

	@Override
	public void schedulingDataCleared() {

	}

}
