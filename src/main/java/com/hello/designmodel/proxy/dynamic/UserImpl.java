package com.hello.designmodel.proxy.dynamic;

public class UserImpl implements UserManager {

	@Override
	public void addUser() {
		System.out.println("add a user.");
	}

	@Override
	public void delUser() {
		System.out.println("delete a user");
	}

}
