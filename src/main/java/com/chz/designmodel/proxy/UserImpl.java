package com.chz.designmodel.proxy;

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
