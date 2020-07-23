package com.hello.sync;

class BlockDemo_obj {
	private BlockDemo_obj obj1 = new BlockDemo_obj();
	private BlockDemo_obj obj2 = new BlockDemo_obj();

	public void demo11() {
		// 对象锁
		synchronized (obj1) {
			while (true) {
				System.out.println(Thread.currentThread().getName());
			}
		}
	}

	public void demo22() {
		synchronized (obj2) {
			System.out.println(Thread.currentThread().getName());
		}
	}

}

public class SyncCodeBlock_obj implements Runnable {
	public BlockDemo_obj blockDemo;

	public SyncCodeBlock_obj(BlockDemo_obj blockDemo) {
		this.blockDemo = blockDemo;
	}

	public static void main(String[] args) {

		// demo1方法中的同步代码块锁住的是obj1对象实例，demo2方法中的同步代码块锁住的是obj2对象实例。
		// thread1执行obj1，thread2执行obj2，由于两个方法抢占的是不同的对象实例锁，也就是说两个线程均能获取到锁执行各自的方法
		BlockDemo_obj obj1 = new BlockDemo_obj();
		BlockDemo_obj obj2 = new BlockDemo_obj();
		Thread t1 = new Thread(new SyncCodeBlock_obj(obj1));
		Thread t2 = new Thread(new SyncCodeBlock_obj(obj2));
		t1.start();
		t2.start();
	}

	@Override
	public void run() {
		blockDemo.demo11();
		blockDemo.demo22();
	}

}
