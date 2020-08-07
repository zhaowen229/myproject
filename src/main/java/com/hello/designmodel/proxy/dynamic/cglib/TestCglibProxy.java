package com.hello.designmodel.proxy.dynamic.cglib;

public class TestCglibProxy {

	public static void main(String[] args) {
		CglibIntercept cglibIntercept = new CglibIntercept();
		CglibHello hello = (CglibHello) cglibIntercept.newProxyInstance(CglibHello.class);// Class<?>
		hello.sayHello();
	}

}
