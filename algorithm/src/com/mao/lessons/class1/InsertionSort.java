package com.mao.lessons.class1;

public class InsertionSort {
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 0 - 0 是有序的
        // 需要0-i也是有序的
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void main(String... args) {
        int[] arr = new int[]{2, 2, 3, 4, 5, 3, 1};
        insertionSort(arr);
        for (int x : arr) {
            System.out.printf("%d\t", x);
        }
    }
}
