package com.hello.singleton;

/**
 * 单例-懒汉式
 * Lazy 初始化
 * 线程安全,效率较低
 * @author czhao1
 *
 */
public class SingleTon3 {
	private static SingleTon3 instance = null;
	
	private SingleTon3() {
	}

	public static synchronized SingleTon3 getInstance() {
		if (instance == null) {
			instance = new SingleTon3();
		}
		return instance;
	}
}
