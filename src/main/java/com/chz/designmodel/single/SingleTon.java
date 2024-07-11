package com.chz.designmodel.single;

/**
 * 单例-饿汉式 在获取对象之前，单例对象已经创建完成。
 * 
 * 线程安全 容易产生垃圾对象
 * 
 * @author czhao1
 *
 */
public class SingleTon {
	private static SingleTon instance = new SingleTon();

	private SingleTon() {
	}

	public static SingleTon getInstance() {
		return instance;
	}

}
