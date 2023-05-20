package com.mao.lessons.class2;

import java.util.Arrays;

public class QuickSort {
    public static void quickSort(int arr[], int left, int right) {
        if (left < right) {
            // 获得枢轴位置
            int pivot = partition(arr, left, right);
            // 快速排序左边
            quickSort(arr, left, pivot - 1);
            // 快速排序右边
            quickSort(arr, pivot + 1, right);
        }
    }

    public static int partition(int arr[], int left, int right) {
        int pivot = arr[left]; // 枢轴
        int i = left + 1;
        int j = right;

        while (true) {
            while (i <= j && arr[i] < pivot)
                ++i; // 找，直到i<=j或者arr[i]的值小于枢轴值
            while (i <= j && arr[j] > pivot)
                --j; // 找，直到i<=j或着arr[j]的值大于枢轴值

            // 上面的条件不满足了，先看看是不是i>j了
            if (i >= j) {
                break;
            }
            // 不是，那么当arr[i]的值小于枢轴值或者arr[j]的值大于枢轴值
            // 一个是去左边，一个是去右边
            swap(arr, i, j);
            ++i;
            --j;
        }
        // 一次划分结束，把j和枢轴的位置换一下（应为j就是新的枢轴位置）
        // 为什么呢，应为j右边都是大于枢轴的，j左边都是小于枢轴的，那么现在枢轴就应该在j这个位置
        // 应为pivot值在left这个位置，所以left和j换
        swap(arr, j, left);
        // 换完了，返回这次的枢轴值
        // 以这个枢轴值为界，进行下次快速排序
        return j;
    }

    /**
     * 荷兰国旗问题
     * 
     * @param arr
     * @param left
     * @param right
     * @param numidx 要作为中心点的索引
     */
    public static void partitionFlag(int[] arr, int left, int right, int numidx) {
        int pivot = arr[numidx];
        int i = left;
        int j = right;

        while (true) {
            while (i <= j && arr[i] < pivot)
                ++i;
            while (i <= j && arr[j] > pivot)
                --j;
            if (i >= j) {
                break;
            }
            swap(arr, i, j);
            ++i;
            --j;
        }
        swap(arr, numidx, right);
    }

    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[j] ^ arr[i];
    }

    public static void main(String[] args) {
        int[] arr = { 3, 5, 1, 6, 4, 8, 2, 9, 7 };
        // QuickSort.quickSort(arr, 0, arr.length - 1);
        QuickSort.partitionFlag(arr, 0, arr.length - 1, 4);
        System.out.println(Arrays.toString(arr)); // 输出 [1, 2, 3, 4, 5, 6, 7, 8, 9]
    }
}
