package com.hello.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ExerciseHandler implements InvocationHandler {

	private Object target;

	public ExerciseHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		return method.invoke(target, args);
	}

}
