package com.hello.proxy.statical;

import com.hello.proxy.UserImpl;

public class TestStaticalProxy {

	public static void main(String[] args) {
		LogStaticalProxy logStaticalProxy = new LogStaticalProxy(new UserImpl());
		logStaticalProxy.addUser();

	}

}
