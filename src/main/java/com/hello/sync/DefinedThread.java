package com.hello.sync;

class Demo {
	
	public synchronized void demoSync() {
		while(true) {
			System.out.println(Thread.currentThread().getName());			
		}
	}

}

public class DefinedThread implements Runnable{
	private Demo demo;
	private DefinedThread(Demo demo) {
		this.demo=demo;
	}
	
	@Override
	public void run() {
		demo.demoSync();
	}
	
	public static void main(String[] args) {
		//Condition1
		//expect:多个线程针对同一个对象,只能有一个对象获取到锁(其中一个对象得到锁之后，在释放锁之前其他线程永远无法获得锁)
		//result:the same as expectation
		/*
		 * Demo demo = new Demo(); 
		 * Thread t1 = new Thread(new DefinedThread(demo));
		 * Thread t2 = new Thread(new DefinedThread(demo)); 
		 * t1.start(); 
		 * t2.start();
		 */
		
		//Condition2
		//expect:多个线程针对不同的对象,每个线程对应的对象都可以获取到锁
		//result:the same as expectation
		Demo demo1 = new Demo();
		Demo demo2 = new Demo();
		Thread t3 = new Thread(new DefinedThread(demo1));
		Thread t4 = new Thread(new DefinedThread(demo2));
		t3.start();
		t4.start();
	}
	
}
