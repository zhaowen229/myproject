package com.hello.sync;

class Demo2 {

	/**
	 * 类锁
	 * 
	 * 下面的两种情况都只能有一个线程针对的对象能得到锁
	 */
	public synchronized static void demoSync() {
		while (true) {
			System.out.println(Thread.currentThread().getName());
		}
	}

}

public class DefinedThread2 implements Runnable {
	private Demo2 demo;

	private DefinedThread2(Demo2 demo) {
		this.demo = demo;
	}

	@Override
	public void run() {
		demo.demoSync();
	}

	public static void main(String[] args) {
		// 第一种
		// expect:多个线程针对同一个对象,只能有一个对象获取到锁
		// result:the same as expectation

//		Demo2 demo = new Demo2();
//		Thread t1 = new Thread(new DefinedThread2(demo));
//		Thread t2 = new Thread(new DefinedThread2(demo));
//		t1.start();
//		t2.start();

		// 第二种
		// expect:多个线程针对不同的对象,只能有一个对象获取到锁
		// result:the same as expectation

		Demo2 demo1 = new Demo2();
		Demo2 demo2 = new Demo2();

		Thread t1 = new Thread(new DefinedThread2(demo1));
		Thread t2 = new Thread(new DefinedThread2(demo2));
		t1.start();
		t2.start();

	}

}
