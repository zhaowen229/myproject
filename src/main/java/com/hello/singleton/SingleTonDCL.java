package com.hello.singleton;

/**
 * 单例-懒加载
 * Lazy 初始化
 * 双重校验保证线程安全和效率
 * 
 * 所谓双重检查加锁机制，指的是：
 * 并不是每次进入getInstance方法都需要同步，而是先不同步，进入方法过后，先检查实例是否存在，如果不存在才进入下面的同步块，这是第一重检查。
 * 进入同步块过后，再次检查实例是否存在，如果不存在，就在同步的情况下创建一个实例，这是第二重检查。这样一来，就只需要同步一次了，从而减少了多次在同步情况下进行判断所浪费的时间
 * 
 * 双重检查加锁机制的实现会使用一个关键字volatile，它的意思是：被volatile修饰的变量的值，将不会被本地线程缓存，所有对该变量的读写都是直接操作共享内存,从而确保多个线程能正确的处理该变量。
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
					instance = new SingleTonDCL();
				}
			}
		}
		return instance;
	}
}
