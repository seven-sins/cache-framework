package com.lonecpp.thread.learning.two;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 队列使用
 * 1. 高性能无阻塞队列 ConcurrentLinkedQueue
 * @author seven sins
 * @date 2018年1月28日 下午1:34:26
 */
public class QueueDemo1 {

	
	public static void main(String[] args) {
		
		/**
		 * 1. 高性能无阻塞队列 ConcurrentLinkedQueue
		 */
		ConcurrentLinkedQueue<String> q = new ConcurrentLinkedQueue<>();
		/**
		 * add()和offer() 都是加入元素的方法 (在ConcurrentLinkedQueue中两者无任何区别)
		 */
		q.offer("a");
		q.offer("b");
		q.offer("c");
		q.offer("d");
		q.add("e");
		
		/**
		 * poll()和peek() 都是取头元素节点, 区别在于前者会删除元素, 后者不会
		 */
		System.out.println(q.poll());
		System.out.println(q.size());
		System.out.println(q.peek());
		System.out.println(q.size());
		
		
	}
}
