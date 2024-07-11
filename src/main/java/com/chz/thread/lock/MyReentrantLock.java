package com.chz.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

import com.chz.sync.DefinedThread;

/**
 * 这种加锁方式相当于同步的代码块中的对象锁
 * 
 * @see DefinedThread
 *
 */
public class MyReentrantLock {
	private final ReentrantLock lock;

	public MyReentrantLock() {
		lock = new ReentrantLock();
	}

	public void test() {
		final ReentrantLock lock = this.lock;
		try {
			lock.lock();
			System.out.println(Thread.currentThread().getName() + "获得锁");
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		// 针对同一个对象 如果 没有 lock.unlock(); 另外一个线程就不能获得锁
		MyReentrantLock objThread = new MyReentrantLock();
		for (int i = 0; i < 2; i++) {
			new Thread(() -> objThread.test()).start();
		}
	}

}
