package com.hello.algorithm.sort;

import java.util.Arrays;

public class BubbleSort {

    public static void sort() {
        int[] arr = {3, 5, 2, 8, 1};
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - (i + 1); j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        int[] arr = {5, 3, 2, 8, 1};
        for (int i = 0; i < arr.length - 1; i++) {
            int minindex = i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[minindex] > arr[j]) {
                    minindex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minindex];
            arr[minindex] = temp;
        }
        System.out.println(Arrays.toString(arr));
    }
}
