package com.lizhuquan.algorithm.sort;

import java.util.Random;

/**
 * Created by lizhuquan on 2018/5/16.
 */
public class SortUtil {

    public static int[] generaRandomArray(int genNums, int rangeL, int rangeR) {
        int[] arr = new int[genNums];

        Random random = new Random();
        int diff = rangeR - rangeL;
        for (int i = 0; i < genNums; i++) {
            arr[i] = rangeL + random.nextInt(diff);
        }

        return arr;
    }

    public static void printArr(int[] arr) {
        for (int e: arr) {
            System.out.print(e);
            System.out.print(" ");
        }
        System.out.println();
    }

    public static void swap(int[] arr, int index1, int index2) {
        int value1 = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = value1;
    }

    public static void validSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int val1 = arr[i];
            int val2 = arr[i + 1];
            if (val1 > val2) {
                throw new RuntimeException(String.format("排序失败, %s->%s, %s->%s", i, val1, i + 1, val2));
            }
        }
    }

    public static void sortAndPrint(int[] arr, Sort sort, int circle) {
        for (int i = 0; i < circle; i++) {
            long b = System.currentTimeMillis();
            arr = sort.sort(arr);
            long e = System.currentTimeMillis();
            validSort(arr);
            printArr(arr);
            System.out.println(sort.getClass().getSimpleName() + "->" + (e - b) + " ms");
            System.out.println();
        }
    }

    public static int[] copyArray(int[] arr, int length) {
        int[] dest = new int[length];
        System.arraycopy(arr, 0, dest, 0, length);
        return dest;
    }

    public static void main(String[] args) {
        validSort(new int[]{1,3,2});
    }
}
