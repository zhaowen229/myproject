package com.hello.algorithm;

import java.util.Arrays;

import org.junit.Test;

public class SortAlgorithms {

//	稳定性的定义
//  假定在待排序的记录序列中，存在多个具有相同的关键字的记录，若经过排序，这些记录的相对次序保持不变，即在原序列中，ri=rj，且ri在rj之前，而在排序后的序列中，ri仍在rj之前，
//	则称这种排序算法是稳定的；否则称为不稳定的。
	@Test
	// 冒泡排序 稳定
	public void bubbleSort() {
		int arr[] = { 2, 5, 3, 6, 3, 10, 4 };
		int len = arr.length;
		for (int i = 0; i < len - 1; i++) {
			for (int j = 0; j < len - 1 - i; j++) {
				if (arr[j] > arr[j + 1]) { // 相邻元素两两对比
					int temp = arr[j + 1]; // 元素交换
					arr[j + 1] = arr[j];
					arr[j] = temp;
				}
			}
		}
		System.out.println(Arrays.toString(arr));
	}

	// 首先在未排序序列中找到最小元素，存放到排序序列的起始位置。
	// 再从剩余未排序元素中继续寻找最小元素，然后放到已排序序列的末尾。
	// 重复第二步，直到所有元素均排序完毕。
	@Test
	public void selectSort() {
		int arr[] = { 2, 5, 3, 6, 3, 10, 4 };
		int minIndex = 0;
		int temp = 0;
		for (int i = 0; i < arr.length - 1; i++) {
			minIndex = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[minIndex]) { // 寻找最小的数（升序） 如果是if (arr[j] > arr[minIndex])则是找到最大的数（降序）
					minIndex = j; // 将最小数的索引保存
				}
			}
			temp = arr[i];
			arr[i] = arr[minIndex];
			arr[minIndex] = temp;
		}
		System.out.println("选择排序：" + Arrays.toString(arr));
	}

	// 二分法查找 数组中指定的元素
	public static int binarySearch(int arr[], int target) {
		int min = 0;
		int mid = 0;
		int max = arr.length - 1;
		int value = 0;
		while (min <= max) {
			mid = min + (max - min) / 2;
			value = arr[mid];
			if (target < value)
				max = mid - 1;
			if (target > value)
				min = mid + 1;
			else
				return mid;
		}
		return -1;
	}

	public static int search(int[] nums, int target) {
		int pivot, left = 0, right = nums.length - 1;
		while (left <= right) {
			pivot = left + (right - left) / 2;
			if (nums[pivot] == target)
				return pivot;
			if (target < nums[pivot])
				right = pivot - 1;
			else
				left = pivot + 1;
		}
		return -1;
	}

	// 查找第一个相等的元素（二分法变种
	// https://www.cnblogs.com/luoxn28/p/5767571.html）
	static int findFirstEqual(int[] array, int key) {
		int left = 0;
		int right = array.length - 1;

		// 这里必须是 <=
		while (left <= right) {
			int mid = (left + right) / 2;
			if (array[mid] >= key) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		if (left < array.length && array[left] == key) {
			return left;
		}

		return -1;
	}

	public static void main(String[] args) {
		int arr[] = { 2, 5, 3, 6, 10, 4, 7, 20 };
		Arrays.sort(arr);
		System.out.println("after sort:" + Arrays.toString(arr));
		System.out.println("binarySearch result:" + binarySearch(arr, 4));
		System.out.println("search result:" + search(arr, 4));
	}

}
