package com.hello.designmodel.proxy.statical;

import com.hello.designmodel.proxy.UserImpl;

public class TestStaticalProxy {

	public static void main(String[] args) {
		LogStaticalProxy logStaticalProxy = new LogStaticalProxy(new UserImpl());
		logStaticalProxy.addUser();

	}

}
