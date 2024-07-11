package com.chz.algorithm.firstbadversion;

public class Solution extends VersionControl {

	public static int firstBadVersion(int n) {
		int min = 1;
		int mid = 0;
		while (min < n) {
			mid = (min + n) / 2;
			if (isBadVersion(mid))
				n = mid;
			else
				min = mid + 1;
		}
		return min;
	}

	public static void main(String args[]) {
		System.out.println(firstBadVersion(5));
	}

}
