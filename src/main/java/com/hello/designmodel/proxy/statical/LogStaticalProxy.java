package com.hello.designmodel.proxy.statical;

import java.text.SimpleDateFormat;

import com.hello.designmodel.proxy.dynamic.UserManager;

public class LogStaticalProxy implements UserManager {
	private UserManager target;

	public LogStaticalProxy(UserManager target) {
		this.target = target;
	}

	@Override
	public void addUser() {
		System.out.println("static proxy 我想输出当前的系统时间："
				+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
		target.addUser();
	}

	@Override
	public void delUser() {
		System.out.println("static delete a user.");

	}

}
