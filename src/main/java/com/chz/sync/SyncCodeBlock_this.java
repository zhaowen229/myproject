package com.chz.sync;

class BlockDemo_this {
	/*
	 * public synchronized void demo1() {
	 * System.out.println(Thread.currentThread().getName()); }
	 * 
	 * public synchronized void demo2() {
	 * System.out.println(Thread.currentThread().getName()); }
	 */

	/**
	 * 上面两个方法和下面的只是写法不同 两个线程针对同一个对象,只要有一个线程获取到锁，另外一个线程只能阻塞等待，即使两个方法不相关
	 * 
	 * code中写的是while true 当然实际情况中 一个线程执行往同步方法中的code之后，其他线程可以得到该锁
	 */

	public void demo11() {
		// 同步代码块中 定义 对象锁 this
		synchronized (this) {
			while (true) {
				System.out.println("11:" + Thread.currentThread().getName());
			}
		}
	}

	public void demo22() {
		synchronized (this) {
			System.out.println("22:" + Thread.currentThread().getName());
		}
	}

}

public class SyncCodeBlock_this implements Runnable {
	public BlockDemo_this blockDemo;

	public SyncCodeBlock_this(BlockDemo_this blockDemo) {
		this.blockDemo = blockDemo;
	}

	public static void main(String[] args) {
		BlockDemo_this blockDemo = new BlockDemo_this();
		Thread t1 = new Thread(new SyncCodeBlock_this(blockDemo));
		Thread t2 = new Thread(new SyncCodeBlock_this(blockDemo));
		t1.start();
		t2.start();

	}

	@Override
	public void run() {
		blockDemo.demo11();
		blockDemo.demo22();
	}

}
