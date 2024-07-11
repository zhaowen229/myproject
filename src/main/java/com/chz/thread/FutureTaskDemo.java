package com.chz.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Future与FutureTask的区别：
 * 
 * 1.Future是一个接口，FutureTask是一个实现类；
 * 
 * 2.使用Future初始化一个异步任务结果一般需要搭配线程池的submit，且submit方法有返回值；
 * 而初始化一个FutureTask对象需要传入一个实现了Callable接口的类的对象，直接将FutureTask对象提交给线程池（使用），无返回值；
 * 
 * 3.Future + Callable获取结果需要Future对象的get，而FutureTask获取结果直接用FutureTask对象的get方法即可。
 *
 * @Future
 */
public class FutureTaskDemo {

	public static void doOtherThings() {
		System.out.println("异步执行：在线程执行submit时，打印该日志。");
	}

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(1);

		FutureTask<Integer> futureTask = new FutureTask<Integer>(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				int sum = 0;
				for (int i = 0; i < 10; i++) {
					sum += i;
				}
				return Integer.valueOf(sum);
			}
		});

		System.out.println(futureTask.isDone());
		executor.execute(futureTask);

		doOtherThings();

		try {
			System.out.println("result=" + futureTask.get());
			System.out.println(futureTask.isDone());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		executor.shutdown();
	}

}
