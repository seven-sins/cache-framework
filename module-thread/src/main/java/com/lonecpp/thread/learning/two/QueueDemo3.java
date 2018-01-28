package com.lonecpp.thread.learning.two;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 队列使用
 * 1. LinkedBlockingQueue 基于链表的阻塞队列
 * @author seven sins
 * @date 2018年1月28日 下午1:34:26
 */
public class QueueDemo3 {

	
	public static void main(String[] args) throws InterruptedException {
		
		/**
		 * 1. LinkedBlockingQueue 基于链表的阻塞队列
		 * 		如指定初始化长度, 超出长度会抛异常
		 * 		LinkedBlockingQueue<String> q = new LinkedBlockingQueue<>(5);
		 */
		LinkedBlockingQueue<String> q = new LinkedBlockingQueue<>();
		q.add("a");
		q.put("b");
		q.add("c");
		q.add("d");
		q.add("e");
		q.add("f");
		System.out.println(q.size());
		
		for(Iterator<String> iterator = q.iterator(); iterator.hasNext();) {
			String string = iterator.next();
			
			System.out.println(string);
		}
	}
}
