package com.hello.algorithm;

import java.util.Iterator;
import java.util.TreeSet;

import org.junit.Test;

public class LongUpSubquence {

	/**
	 * LongUpSubquence
	 */
	public void test() {
		int nums[] = { 1, 3, 6, 7, 9, 4, 10, 5, 6 };
		TreeSet<Integer> set = new TreeSet<>();
		for (int i = 0; i < nums.length; i++) {
			Integer val = set.ceiling(nums[i]);
			if (val == null) {
				set.add(nums[i]);
			} else {
				set.remove(val);
				set.add(nums[i]);
			}

		}
		System.out.println("result==" + set.size());
	}

	@Test
	public void merge() {
		int nums1[] = { 1, 2, 3 };
		int nums2[] = { 2, 5, 6 };

		TreeSet<Integer> set = new TreeSet<>();
		int resArr[] = new int[nums1.length + nums2.length];

		for (int i = 0; i < nums1.length; i++) {
			set.add(nums1[i]);
		}
		for (int i = 0; i < nums2.length; i++) {
			set.add(nums2[i]);
		}
		Iterator<Integer> it = set.iterator();
		int i = 0;
		while (it.hasNext()) {
			resArr[i] = it.next();
			i++;
		}

		for (int j = 0; j < resArr.length; j++) {
			System.out.println(resArr[j]);
		}

	}

}
