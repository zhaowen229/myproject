package com.chz.thread.lock.threadlocal;

import java.util.Arrays;

import org.junit.Test;

public class MyTest {
	public transient String nameString;
	int value = 5;

	public static void bubbleSort() {
		int arr[] = { 6, 5, 3, 10, 4 };
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

	@Test
	public void test() {

		int[] arr = { 2, 1, 3, 7, 4 };
		int minIndex = 0;
		int temp = 0;
		int count = 0;
		// 总共要经过 N-1 轮比较
		for (int i = 0; i < arr.length - 1; i++) {
			minIndex = i;
			// 每轮需要比较的次数 N-i
			count++;
			System.out.println("i==" + i);
			for (int j = i + 1; j < arr.length; j++) {
				if (count == 2) {
					System.out.println("j==" + j);
				}
				if (arr[j] < arr[minIndex]) { // 寻找最小的数（升序）
					minIndex = j; // 将最小数的索引保存
				}
			}
			temp = arr[i];
			arr[i] = arr[minIndex];
			arr[minIndex] = temp;
		}
		System.out.println("选择排序：" + Arrays.toString(arr));
	}

	public static void main(String args[]) {
//		bubbleSort();

	}

}
