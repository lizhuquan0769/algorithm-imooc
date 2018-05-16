package com.lizhuquan.algorithm.sort;

/**
 * 冒泡排序：O(n^2)
 */
public class BubbleSort implements Sort {
    @Override
    public int[] sort(int[] arr) {
        for(int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j + 1] < arr[j]) {
                    SortUtil.swap(arr, j, j + 1);
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            int[] arr = SortUtil.generaRandomArray(10000, 1, 100000);
            SortUtil.sortAndPrint(arr, new BubbleSort(), 1);
        }
    }
}
