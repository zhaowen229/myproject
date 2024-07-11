package com.chz.designmodel.proxy.dynamic;

import com.chz.designmodel.proxy.UserImpl;
import com.chz.designmodel.proxy.UserManager;

public class TestDynamicProxy {

	public static void main(String[] args) {
		LogHandler logHandler = new LogHandler();
		UserManager userManager = (UserManager) logHandler.getProxyInstance(new UserImpl());
		userManager.addUser();
	}

}
