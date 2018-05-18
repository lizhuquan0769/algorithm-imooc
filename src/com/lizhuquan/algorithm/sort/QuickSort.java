package com.lizhuquan.algorithm.sort;

/**
 * 快速排序：o(n*logn)
 */
public class QuickSort implements Sort {
    @Override
    public int[] sort(int[] arr) {
        // 快速排序方案1
//        quickSort(arr, 0, arr.length - 1);

        // 快速排序方案2
        quickSort2(arr, 0, arr.length - 1);

        // 快速排序方案3: 三路快速排序
        quickSort3(arr, 0, arr.length -  1);
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

        // 优化, 元素少的时候使用插入排序
//        if (right - left <= 15) {
//            insertionSort(arr, left, right);
//            return;
//        }

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

        // 优化, 随机选择标定元素来交换left元素
        SortUtil.swap(arr, left, left + (int)(Math.random() * (right - left)));
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

    private void quickSort2(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        // 优化, 元素少的时候使用插入排序
//        if (right - left <= 15) {
//            insertionSort(arr, left, right);
//            return;
//        }

        int p = partition2(arr, left, right);
        quickSort2(arr, left, p - 1);
        quickSort2(arr, p + 1, right);
    }

    /**
     * 面对大量重复源元素, 可以平衡两边元素长度
     * @param arr
     * @param l
     * @param r
     * @return
     */
    private int partition2(int[] arr, int l, int r) {
        // 优化, 随机选择标定元素来交换left元素
        SortUtil.swap(arr, l, l + (int)(Math.random() * (l - l)));
        int v = arr[l];

        // arr[l+1...i) <= v ; arr(j...r] >= v
        int i=l+1;
        int j=r;
        while (true) {
            while (i <= r && arr[i] < v) {
                i++;
            }
            while (j >= l+1 && arr[j] > v) {
                j--;
            }
            if (i > j) {
                break;
            }
            SortUtil.swap(arr, i, j);
            i++;
            j--;
        }
        SortUtil.swap(arr, l, j);
        return j;
    }

    /**
     * 三路快速排序, 维护三部分数据, 小于v, 等于v, 大于v
     * 应付场景: 大量重复元素的情况
     * @param arr
     * @param l
     * @param r
     */
    private void quickSort3(int arr[], int l, int r) {
        if (l >= r) {
            return;
        }

        // 优化, 元素少的时候使用插入排序
//        if (right - left <= 15) {
//            insertionSort(arr, left, right);
//            return;
//        }

        // partition
        // 优化, 随机选择标定元素来交换left元素
        SortUtil.swap(arr, l, l + (int)(Math.random() * (l - l)));
        int v = arr[l];

        int lt = l; // arr[l+1...lt] < v
        int gt = r + 1; // arr[gt...r] > v
        int i = l + 1; // arr[lt+1...i) == v
        while (i < gt) {
            if (arr[i] < v) {
                SortUtil.swap(arr, i,lt + 1);
                lt++;
                i++;
            } else if (arr[i] > v) {
                SortUtil.swap(arr, i, gt - 1);
                gt--;
            } else {
                i++;
            }
        }
        SortUtil.swap(arr, l, lt);

        quickSort3(arr, l, lt-1);
        quickSort3(arr, gt, r);
    }

    public static void main(String[] args) {
        int[] arrForMergeSort = SortUtil.generaRandomArray(100000, 1, 10000000);
        int[] arrForQuickSort = SortUtil.copyArray(arrForMergeSort, arrForMergeSort.length);
        SortUtil.sortAndPrint(arrForMergeSort, new MergeSort(), 1);
        SortUtil.sortAndPrint(arrForQuickSort, new QuickSort(), 1);

    }
}
