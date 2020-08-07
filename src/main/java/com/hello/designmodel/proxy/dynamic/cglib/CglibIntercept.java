package com.hello.designmodel.proxy.dynamic.cglib;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class CglibIntercept implements MethodInterceptor {
	/**
	 * CGLIB 增强类对象，代理类对象是由 Enhancer 类创建的， Enhancer 是 CGLIB 的字节码增强器，可以很方便的对类进行拓展
	 */
	private Enhancer enhancer = new Enhancer();

	/**
	 * 使用动态代理创建一个代理对象
	 * 
	 * @param c
	 * @return
	 */
	public Object newProxyInstance(Class<?> c) {
		/**
		 * 设置产生的代理对象的父类,增强类型
		 */
		enhancer.setSuperclass(c);
		/**
		 * 定义代理逻辑对象为当前对象，要求当前对象实现 MethodInterceptor 接口
		 */
		enhancer.setCallback(this);
		/**
		 * 使用默认无参数的构造函数创建目标对象,这是一个前提,被代理的类要提供无参构造方法
		 */
		return enhancer.create();
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		System.out.println("print output.");
		Object object = methodProxy.invokeSuper(obj, args);
		return object;
	}

}
