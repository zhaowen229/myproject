package com.chz.thread.resume;

import java.text.SimpleDateFormat;

/**
 * 某公司面试题
 * 
 * 验证两种方法的运行时间
 * 
 * 1.for (int i = 0; i < 30; i++) { new ThreadExecuteSpendTime().run(); } 结果：60s
 * 
 * 2.for (int i = 0; i < 30; i++) { new ThreadExecuteSpendTime().start(); } 结果：很快2s
 *
 * @author zhaoc
 */
public class ThreadExecuteSpendTime extends Thread {

	@Override
	public void run() {
		try {
			System.out.println("线程等待时间:" + System.currentTimeMillis());
			Thread.sleep(2000);
			System.out.println("线程等待时间:" + System.currentTimeMillis());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void startMethod() {
		for (int i = 0; i < 30; i++) {
			new ThreadExecuteSpendTime().start();
		}
	}

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		System.out.println(sdf.format(System.currentTimeMillis()));
		// 1.
		for (int i = 0; i < 30; i++) {
			new ThreadExecuteSpendTime().run();
		}
		System.out.println(sdf.format(new java.util.Date(System.currentTimeMillis())));

		// 2.
		System.out.println("for循环执行时间:" + System.currentTimeMillis());
		startMethod();
		System.out.println("for循环执行时间:" + System.currentTimeMillis());

	}

}
