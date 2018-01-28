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
		 * 
		 * 	add        增加一个元索                     	如果队列已满，则抛出一个IIIegaISlabEepeplian异常
			remove     移除并返回队列头部的元素    	如果队列为空，则抛出一个NoSuchElementException异常
			element    返回队列头部的元素             	如果队列为空，则抛出一个NoSuchElementException异常
			offer      添加一个元素并返回true    如果队列已满，则返回false
			poll       移除并返问队列头部的元素    	如果队列为空，则返回null
			peek       返回队列头部的元素             	如果队列为空，则返回null
			put        添加一个元素                      	如果队列满，则阻塞
			take       移除并返回队列头部的元素     如果队列为空，则阻塞
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
