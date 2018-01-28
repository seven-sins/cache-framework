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
		 * 		如指定初始化长度, 使用add()添加元素, 超出长度会抛异常
		 * 		LinkedBlockingQueue<String> q = new LinkedBlockingQueue<>(5);
		 * put(e) 阻塞
		 * add(e) 超出长度抛异常
		 * offer(e, time, unit) 指定时间内不能添加到队长超时返回false
		 * 
		 * poll()和peek() 都是取头元素节点, 区别在于前者会删除元素, 后者不会
		 * 
		 * 详细说明:
		 		add        增加一个元索                     	如果队列已满，则抛出一个IIIegaISlabEepeplian异常
				remove     移除并返回队列头部的元素    	如果队列为空，则抛出一个NoSuchElementException异常
				element    返回队列头部的元素             	如果队列为空，则抛出一个NoSuchElementException异常
				offer      添加一个元素并返回true    如果队列已满，则返回false
				poll       移除并返问队列头部的元素    	如果队列为空，则返回null
				peek       返回队列头部的元素             	如果队列为空，则返回null
				put        添加一个元素                      	如果队列满，则阻塞
				take       移除并返回队列头部的元素     如果队列为空，则阻塞
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
