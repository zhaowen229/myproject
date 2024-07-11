package com.chz.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

import com.chz.sync.DefinedThread2;

/**
 * 加锁方式和类锁一样
 * 
 * @see DefinedThread2
 *
 */
public class MyReentrantLock2 {
	// 静态变量
	private static ReentrantLock lock = new ReentrantLock();

	public void test() {
		ReentrantLock lock = this.lock;
		try {
			lock.lock();
			System.out.println(Thread.currentThread().getName() + "获得锁");
		} catch (Exception e) {
		}
	}

	public static void main(String[] args) {
		new Thread(() -> new MyReentrantLock2().test()).start();
		new Thread(() -> new MyReentrantLock2().test()).start();
	}
}
