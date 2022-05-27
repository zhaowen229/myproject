package com.hello.designmodel.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LogHandler implements InvocationHandler{
	private Object targetObject;

	public Object getProxyInstance(Object targetObject) {
		this.targetObject = targetObject;
		return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(), targetObject.getClass().getInterfaces(),
				this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		before();
		Object result =  method.invoke(this.targetObject, args);
		after();
		return result;
	}

	private void before(){
		System.out.println("jdk dynamic begin.");
	}

	private void after(){
		System.out.println("jdk dynamic end.");
	}
}
