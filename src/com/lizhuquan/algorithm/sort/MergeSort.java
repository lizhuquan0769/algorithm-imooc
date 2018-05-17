package com.lizhuquan.algorithm.sort;

/**
 * 归并排序: o(n*logn)
 * Created by lizhuquan on 2018/5/17.
 */
public class MergeSort implements Sort {

    private InsertionSort insertionSort = new InsertionSort();

    @Override
    public int[] sort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
        return arr;
    }

    /**
     * 递归使用归并排序对arr[left...right]范围进行排序
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private void mergeSort(int[] arr, int left, int right) {
        // 归并排序代码
        if (left >= right) {
            return;
        }
        // 归并排序优化, 当left和right差距小的时候, 可以使用插入排序
//        if (right - left <= 15) {
//            insertionSort.sort(arr, left, right);
//        }

        int middle  = (left + right) / 2;
        mergeSort(arr, left, middle);
        mergeSort(arr, middle + 1, right);
        if (arr[middle] > arr[middle + 1]) {
            merge(arr, left, middle,  right);
        }

    }

    /**
     * 将arr[left...middle]和arr[middle+1...right]两部分进行归并
     * @param arr
     * @param left
     * @param middle
     * @param right
     */
    private void merge(int[] arr, int left, int middle, int right) {
        // copy aux array
        int[] aux = new int[right - left + 1];
        for (int i = left; i <= right; i++) {
            aux[i - left] = arr[i];
        }

        int i = left;
        int j = middle + 1;
        for (int k = left; k <= right; k++) {
            // i 和 j 超出边界的情况
            if (i > middle) {
                arr[k] = aux[j - left];
                j++;
            } else if (j > right) {
                arr[k] = aux[i - left];
                i++;
            } else if (aux[i - left] < aux[j - left]) {
                arr[k] = aux[i - left];
                i++;
            } else {
                arr[k] = aux[j - left];
                j++;
            }
        }
    };

    public static void main(String[] args) {
        int[] arrForMergetSort = SortUtil.generaRandomArray(50000, 1, 100000);
        int[] arrForInsertionSort = SortUtil.copyArray(arrForMergetSort, arrForMergetSort.length);

        SortUtil.sortAndPrint(arrForInsertionSort, new InsertionSort(), 1);
        SortUtil.sortAndPrint(arrForMergetSort, new MergeSort(), 1);
    }
}
