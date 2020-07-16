package com.hello.algorithm.singlenum;

import java.util.HashSet;

public class SingleNum {

	public static void happyNum(int n) {

	}

	public static int singleNumber(int[] nums) {
		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < nums.length; i++) {
			Boolean result = set.add(nums[i]);
			if (!result)
				set.remove(nums[i]);
		}
		return (int) set.iterator().next();
	}

	public static void main(String args[]) {
		int nums[] = { 0, 2, 1, 1, 2 };
		int result = singleNumber(nums);
		System.out.println(result);

	}
}
