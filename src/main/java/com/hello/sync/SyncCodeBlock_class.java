package com.hello.sync;

class BlockDemo_class {

	public void demo() {
		synchronized (BlockDemo_obj.class) {
			while (true) {
				System.out.println(Thread.currentThread().getName());
			}
		}
	}

}

public class SyncCodeBlock_class implements Runnable {
	public BlockDemo_class blockDemo;

	public SyncCodeBlock_class(BlockDemo_class blockDemo) {
		this.blockDemo = blockDemo;
	}

	public static void main(String[] args) {

		// 类锁和 给静态的方法加synchronized 是一样的

		//两种情况的结果都是只能有一个线程获得锁
		
		// Condition1
//		BlockDemo_class obj1 = new BlockDemo_class();
//		Thread t1 = new Thread(new SyncCodeBlock_class(obj1));
//		Thread t2 = new Thread(new SyncCodeBlock_class(obj1));
//		t1.start();
//		t2.start();

		// Condition2
		BlockDemo_class obj1 = new BlockDemo_class();
		BlockDemo_class obj2 = new BlockDemo_class();
		Thread t1 = new Thread(new SyncCodeBlock_class(obj1));
		Thread t2 = new Thread(new SyncCodeBlock_class(obj2));
		t1.start();
		t2.start();
	}

	@Override
	public void run() {
		blockDemo.demo();
	}

}
