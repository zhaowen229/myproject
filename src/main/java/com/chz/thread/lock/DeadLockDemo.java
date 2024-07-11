package com.chz.thread.lock;

public class DeadLockDemo implements Runnable {
	private static Object obj1 = new Object();
	private static Object obj2 = new Object();
	private int flag;

	@Override
	public void run() {
		System.out.println("flag==" + flag);
		if (flag == 1) {
			synchronized (obj1) {
				System.out.println("线程" + Thread.currentThread().getName() + "获取锁obj1");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (obj2) {
					System.out.println("线程" + Thread.currentThread().getName() + "继续获取锁obj2");
				}
			}
		}

		if (flag == 2) {
			synchronized (obj2) {
				System.out.println("线程" + Thread.currentThread().getName() + "获取锁obj2");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (obj1) {
					System.out.println("线程" + Thread.currentThread().getName() + "继续获取锁obj1");
				}
			}
		}

	}

	public static void main(String[] args) {
		DeadLockDemo d1 = new DeadLockDemo();
		DeadLockDemo d2 = new DeadLockDemo();
		d1.flag = 1;
		d2.flag = 2;
		Thread t1 = new Thread(d1);
		Thread t2 = new Thread(d2);
		t1.start();
		t2.start();

	}

}
