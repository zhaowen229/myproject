package com.hello.designmodel.proxy.dynamic;

import com.hello.designmodel.proxy.UserImpl;
import com.hello.designmodel.proxy.UserManager;

public class TestDynamicProxy {

	public static void main(String[] args) {
		LogHandler logHandler = new LogHandler();
		UserManager userManager = (UserManager) logHandler.getProxyInstance(new UserImpl());
		userManager.addUser();
	}

}
