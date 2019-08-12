package com.hello.sync;

public class TestSync {
	private static TestSync testSync = new TestSync();
	private TestSync() {
	}
	
	public static TestSync getInstance() {
		if (testSync==null) {
			testSync = new TestSync();
		}
		return testSync;
		
	}

}

class Synchronized {
	public void method() {
		synchronized (this) {
			//execute code
		}
	}
	
	public void method2() {
		Object obj = new Object();
		synchronized (obj) {
			//execute code
		}
	}
	
	public void method3() {
		synchronized (Synchronized.class) {
			//execute code
		}
	}
}
