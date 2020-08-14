package com.hello.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureDemo implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		System.out.println("Start executing call...");
		int sum = 0;
		for (int i = 0; i < 10; i++) {
			sum += i;
		}
		return Integer.valueOf(sum);
	}

	public static void doOtherThings() {
		System.out.println("异步执行：在线程执行submit时，打印该日志。");
	}

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(1);
		// 1.
		// Future<Integer> future = executor.submit(new FutureDemo());
		// 2.
		Future<Integer> futureI = executor.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				System.out.println("接口中要执行的内容。");
				return null;
			}
		});

		doOtherThings();

		try {
			Integer resultInteger = futureI.get();
			System.out.println("线程CallableDemo执行的结果是：" + resultInteger);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		executor.shutdown();
	}

}
