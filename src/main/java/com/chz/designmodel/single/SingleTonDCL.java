package com.chz.designmodel.single;

/**
 * 单例-懒加载 Lazy 初始化 双重校验保证线程安全和效率
 * 
 * 所谓双重检查加锁机制，指的是：
 * 不同步getInstance方法（防止多个线程阻塞，提高效率），进入方法过后，先检查实例是否存在，如果不存在才进入下面的同步块，这是第一重检查。
 * 进入同步块过后，再次检查实例是否存在，如果不存在，就在同步的情况下创建一个实例，这是第二重检查。
 * 
 * 双重检查加锁机制的实现会使用一个关键字volatile，
 * 
 * 目的：1.禁止指令重排 2.保证可见性
 *
 * 1.分配内存
 * 2.调用构造函数初始化对象
 * 3.返回内存地址的引用
 * 
 * @author czhao1
 *
 */
public class SingleTonDCL {
	private volatile static SingleTonDCL instance = null;

	private SingleTonDCL() {
	}

	private static SingleTonDCL geInstance() {
		if (instance == null) {
			synchronized (SingleTonDCL.class) {
				if (instance == null) {
					// 非原子操作
					instance = new SingleTonDCL();
				}
			}
		}
		return instance;
	}
}
