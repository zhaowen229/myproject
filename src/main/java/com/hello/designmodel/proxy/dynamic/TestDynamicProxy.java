package com.hello.designmodel.proxy.dynamic;

public class TestDynamicProxy {

	public static void main(String[] args) {
		LogHandler logHandler = new LogHandler();
		UserManager userManager = (UserManager) logHandler.getProxyInstance(new UserImpl());
		userManager.addUser();
	}

}