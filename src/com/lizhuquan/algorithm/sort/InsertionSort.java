package com.lizhuquan.algorithm.sort;

/**
 * 插入排序: O(n^2), 适合几乎有序的数组， 接近O(n)
 */
public class InsertionSort implements Sort {

    @Override
    public int[] sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    SortUtil.swap(arr, j, j - 1);
                } else {
                    break;
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = SortUtil.generaRandomArray(10000, 10000, 1000000);
        int[] arrForSelection = SortUtil.copyArray(arr, arr.length);
        int[] arrForInsertion = SortUtil.copyArray(arr, arr.length);
        SortUtil.sortAndPrint(arrForSelection, new SelectionSort(), 1);
        SortUtil.sortAndPrint(arrForInsertion, new InsertionSort(), 1);
        System.out.println(arrForSelection.length);
        System.out.println(arrForInsertion.length);
    }
}
