package com.hello.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class TestException {
	private static Logger logger = LogManager.getLogger(TestException.class);
	private static TestException obj = new TestException();
	
	@SuppressWarnings("finally")
	public String testFinally() {
		File file = new File("");
		try {
			InputStream in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Still log info.");
		} finally {
			return "always get this value.";
		}
	}

	@SuppressWarnings("finally")
	public String testNewException() {
		File file = new File("");
		try {
			InputStream in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			throw new Exception();
//			System.out.println("stop log info.");
		} finally {
			return "Still execute as finally";
		}
	}

	public String test() throws Exception {
		File file = new File("");
		try {
			InputStream in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			throw new Exception();
		}
		return "Do not execute";
	}
	
	public static void main(String[] args) throws Exception{
		String result1 = obj.testFinally();
		System.out.println("testFinally:" + result1);

		String result2 = obj.testNewException();
		System.out.println("testNewException:" + result2);
		
		String result3 = obj.test();
		System.out.println("test:" + result3);
	}

}
