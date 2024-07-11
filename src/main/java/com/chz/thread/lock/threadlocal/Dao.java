package com.chz.thread.lock.threadlocal;

public class Dao {
	public void insert() {
		// 获取连接
		System.out.println("Dao.insert() " + Thread.currentThread().getName() + " " + DBUtil.getConnection());

	}

	public void delete() {
		// 获取连接
		System.out.println("Dao.delete() " + Thread.currentThread().getName() + " " + DBUtil.getConnection());

	}

	public void update() {
		// 获取连接
		System.out.println("Dao.update() " + Thread.currentThread().getName() + " " + DBUtil.getConnection());

	}

	public void select() {
		// 获取连接
		System.out.println("Dao.select() " + Thread.currentThread().getName() + " " + DBUtil.getConnection());

	}
}
