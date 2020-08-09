package com.hello.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class MyAtomicInteger {
	private static volatile int value = 0;
	private static AtomicInteger atomicValue = new AtomicInteger();
	static int i = 0;

	public static void main(String[] args) {
		System.out.println("result:" + getAndAddInt());
	}

	public static final int getAndAddInt() {
		int var5;
		do {
			var5 = 5;// 内存中存储的值
			System.out.println("内存中存储的值:" + var5);
		} while (!compareAndSwapInt());

		return var5;
	}

	public static boolean compareAndSwapInt() {
		i++;
		if (i < 5) {
			return false;
		}
		return true;
	}

	public static int getValueCommon() {
		Thread[] threads = new Thread[5];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(() -> {
				for (int j = 0; j < 10; j++) {
					System.out.println(value++);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			threads[i].start();
		}
		return value;
	}

	public static int testAtomicOperation() {
		Thread[] threads = new Thread[5];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(() -> {
				for (int j = 0; j < 10; j++) {
					System.out.println(atomicValue.incrementAndGet());
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			threads[i].start();
		}
		return atomicValue.get();
	}

}
