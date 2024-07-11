package com.chz.algorithm;

public class IsSubsequence {

	/**
	 * 双指针
	 */
	public static boolean isSubsequence(String s, String t) {
		char[] arrs = s.toCharArray();
		char[] arrt = t.toCharArray();
		int p = 0;
		int q = 0;
		while (p < arrs.length && q < arrt.length) {
			if (arrs[p] == arrt[q]) {
				p++;
				q++;
			} else {
				q++;
			}
		}
		return p == arrs.length;
	}

	/**
	 * 逐个查找
	 */
	public boolean isSubsequence2(String s, String t) {
		int index = -1;
		for (char val : s.toCharArray()) {
			index = t.indexOf(val, index + 1);
			if (index == -1)
				return false;
		}
		return true;
	}
}
