package com.hello.thread.lock.threadlocal;

import com.hello.test.MyTest;

public class DBUtil {
	private static ThreadLocal<MyTest> local = new ThreadLocal<MyTest>();

	public static MyTest getConnection() {

		MyTest result = local.get();
		if (result == null) {
			MyTest objMyTest = new MyTest();
			local.set(objMyTest);
			return objMyTest;

		}
		return result;
	}
}
