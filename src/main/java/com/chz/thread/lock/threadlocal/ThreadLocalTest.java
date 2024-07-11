package com.chz.thread.lock.threadlocal;

public class ThreadLocalTest implements Runnable {
	private Dao dao = new Dao();

	public static void main(String[] args) {
		ThreadLocalTest objLocalTest = new ThreadLocalTest();
		for (int i = 0; i < 2; i++) {
			new Thread(objLocalTest).start();
		}

//		// 新建一个ThreadLocal
//		ThreadLocal<String> local = new ThreadLocal<>();
//		// 新建一个随机数类
//		Random random = new Random();
//		// 使用java8的Stream新建5个线程
//		IntStream.range(0, 5).forEach(a -> new Thread(() -> {
//			// 为每一个线程设置相应的local值
//			local.set(a + "  " + random.nextInt(10));
//			System.out.println(Thread.currentThread().getName() + " local值分别是" + local.get());
//			System.out.println(local.get());
//			try {
//				TimeUnit.SECONDS.sleep(1);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}).start());
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		dao.insert();
		dao.update();
	}
}
