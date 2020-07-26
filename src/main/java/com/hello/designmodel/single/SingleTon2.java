package com.hello.designmodel.single;

/**
 * 单例-懒汉式
 * Lazy 初始化
 * 线程不安全
 * @author czhao1
 *
 */
public class SingleTon2 {
	private static SingleTon2 instance = null;
	
	private SingleTon2() {
	}

	public static SingleTon2 getInstance() {
		if (instance == null) {
			instance = new SingleTon2();
		}
		return instance;
	}
}
