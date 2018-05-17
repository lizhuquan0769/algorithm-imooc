package com.lizhuquan.algorithm.sort;

/**
 * 快速排序：o(n*logn)
 */
public class QuickSort implements Sort {
    @Override
    public int[] sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
        return arr;
    }

    /**
     * 对arr[left...right]部分进行快速排序
     * @param arr
     * @param left
     * @param right
     */
    private void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int p = partition(arr, left, right);
        quickSort(arr, left, p - 1);
        quickSort(arr, p + 1, right);
    }

    /**
     * 对arr[left...right]部分进行partition操作
     * 返回p， 使得arr[left...p-1] < arr[p] ; arr[p+1...right] > arr[p]
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private int partition(int[] arr, int left, int right) {
        int v = arr[left];
        // 假设arr[left+1...j] < v ; arr[j+1...i) > v
        // 最终arr[left...j-1] < v ; arr[j+1...right] > v, j即时定位点
        int j = left;
        for (int i = left + 1; i <= right; i++) {
            if (arr[i] < v) {
                SortUtil.swap(arr, j+1, i);
                j++;
            }
        }
        SortUtil.swap(arr, left, j);
        return j;
    }

    public static void main(String[] args) {
        int[] arrForMergeSort = SortUtil.generaRandomArray(100000, 1, 10000000);
        int[] arrForQuickSort = SortUtil.copyArray(arrForMergeSort, arrForMergeSort.length);
        SortUtil.sortAndPrint(arrForMergeSort, new MergeSort(), 1);
        SortUtil.sortAndPrint(arrForQuickSort, new QuickSort(), 1);

    }
}
