package com.lonecpp.thread.learning.two;

/**
 * @author seven sins
 * @date 2018年1月28日 下午12:41:57
 * 
 * 静态内部类实现单例
 * 单例在多线程中最广泛的解决方案, 也是最安全的解决方案
 */
public class InnerSingleton {

	private static class Singleton{
		private static Singleton single = new Singleton();
	}
	
	public static Singleton getInstance() {
		return Singleton.single;
	}
}
