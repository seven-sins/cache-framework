package com.lonecpp.thread.learning.two;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 队列使用
 * 1. ArrayBlockingQueue 基于数组的阻塞队列
 * @author seven sins
 * @date 2018年1月28日 下午1:34:26
 */
public class QueueDemo2 {

	
	public static void main(String[] args) throws InterruptedException {
		
		/**
		 * 1. ArrayBlockingQueue 基于数组的阻塞队列
		 */
		ArrayBlockingQueue<String> array = new ArrayBlockingQueue<>(5);
		array.add("a");
		array.put("b");
		array.add("c");
		array.add("d");
		array.add("e");
		
		/**
		 * 2秒后如果再不能添加进队列, 返回false
		 */
		System.out.println(array.offer("a", 2, TimeUnit.SECONDS));
	}
}
