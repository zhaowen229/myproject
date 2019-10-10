package com.hello.singleton;

/**
 * 单例-饿汉式
 * 线程安全
 * 容易产生垃圾对象
 * @author czhao1
 *
 */
public class SingleTon {
	private static SingleTon instance = new SingleTon();
	
	private SingleTon(){
	}
	
	public static SingleTon getInstance() {
		return instance;
	}

}
