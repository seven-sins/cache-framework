package com.lonecpp.core.config.request;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 设置线程名称
 * @author seven sins
 * @date 2018年1月6日 下午5:30:00
 */
public class RequestProcessorThreadFactory implements ThreadFactory {

	AtomicInteger count = new AtomicInteger(0);

	@Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r);
		String theadName = RequestProcessorThread.class.getSimpleName() + count.addAndGet(1);
		thread.setName(theadName);

		return thread;
	}
}
