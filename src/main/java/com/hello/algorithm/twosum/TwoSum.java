package com.hello.algorithm.twosum;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。 示例: 给定 nums = [2, 7, 11, 15], target
 * = 9 因为 nums[0] + nums[1] = 2 + 7 = 9 所以返回 [0, 1] 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 
 * @author zhaowen
 *
 */
public class TwoSum {

	public static int[] twoSum(int[] nums, int target) throws Exception {
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] == target - nums[j]) {
					return new int[] { i, j };
				}
			}
		}
		throw new Exception("no such nums");
	}

	/**
	 * ps:mapNums.get(complement) != i 保证当数组中有两个相同的数时，能返回正确的下标
	 * 
	 * @param nums
	 * @param target
	 * @return
	 * @throws Exception
	 * 
	 * 
	 */
	public static int[] twoSumWithHashTable(int[] nums, int target) throws Exception {
		Map<Integer, Integer> mapNums = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			mapNums.put(nums[i], i);
		}
		for (int i = 0; i < nums.length; i++) {
			int complement = target - nums[i];
			if (mapNums.containsKey(complement) && mapNums.get(complement) != i) {
				return new int[] { i, mapNums.get(complement) };
			}
		}
		throw new Exception("no such num.");
	}

	public static void main(String[] args) throws Exception {
		int nums[] = { 2, 7, 11 };
		// int arr[] = twoSum(nums, 13);
		int arr[] = twoSumWithHashTable(nums, 13);
		for (int i : arr) {
			System.out.println(i);
		}
	}

}
