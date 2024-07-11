package com.chz.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Future 以及相关使用方法提供了异步执行任务的能力，但是对于结果的获取却是很不方便，只能通过阻塞或者轮询的方式得到任务的结果
 * 更好的方式 @see CompletionService
 * https://blog.csdn.net/qq_34562093/article/details/90209520
 */
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
		System.out.println("2异步执行：在线程执行submit时，打印该日志。");
	}

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(1);
		// 1.
		// Future<Integer> future = executor.submit(new FutureDemo());
		// 2.
		Future<Integer> futureI = executor.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				System.out.println("1接口中要执行的内容。");
				int sum = 0;
				for (int i = 0; i < 10; i++) {
					sum = sum +i;
				}
				Thread.sleep(5000);
				return sum;
			}
		});

		try {
			//get方法阻塞 直到任务执行完成
			System.out.println(futureI.get());
			Thread.sleep(2000);
			doOtherThings();
			System.out.println("3线程-1和2都执行结束.");
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		executor.shutdown();
	}

	public static void testGetMethod() throws Exception {
		ExecutorService service = Executors.newFixedThreadPool(1);

		long start = System.currentTimeMillis();

		// 任务1
		Future<Boolean> booleanTask = service.submit(new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				Thread.sleep(10000);
				return true;
			}
		});

		// 任务2
		Future<String> stringTask = service.submit(new Callable<String>() {

			@Override
			public String call() throws Exception {
				Thread.sleep(3000);
				return "Hello World";
			}
		});

		// 任务3
		Future<Integer> integerTask = service.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				Thread.sleep(2000);
				return new Random().nextInt(100);
			}
		});

		//轮询方式获取结果
		while (true) {
			if (booleanTask.isDone() && !booleanTask.isCancelled()) {
				Boolean result = booleanTask.get();
				System.err.println("任务1-10s： " + result);
				break;
			}
		}

		while (true) {
			if (stringTask.isDone() && !stringTask.isCancelled()) {
				String result = stringTask.get();
				System.err.println("任务2-3s： " + result);
				break;
			}
		}

		while (true) {
			if (integerTask.isDone() && !integerTask.isCancelled()) {
				Integer result = integerTask.get();
				System.err.println("任务3-2s：" + result);
				break;
			}
		}

		// 执行时间
		System.err.println("time: " + (System.currentTimeMillis() - start));

	}

}
