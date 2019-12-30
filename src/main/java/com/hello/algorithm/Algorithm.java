package com.hello.algorithm;

import java.util.ArrayList;
import java.util.List;

public class Algorithm {
	
	static int fibonacci(int n) {
		if (n<=1) {
//			System.out.println(n);
			return n;
		}
		return fibonacci(n-1) + fibonacci(n-2);
	}
	
	public static void main(String args[]) {
		int result =0;
		List<Integer> fiList = new ArrayList<>();
		for (int i = 1; i <= 4; i++) {
			 result = getNthValue(i);			
			 fiList.add(result);
		}
		System.out.println(fiList);
	}
	
	private static int getNthValue(int n) {
		int NResult = fibonacci(n);
		return NResult;
	}

}
