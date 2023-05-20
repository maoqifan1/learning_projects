package com.mao.lessons.class1;

public class Binary {
    // 二分查找
    public static int binarySearch(int[] arr, int ele) {
        int left, right, mid, midVal;
        left = 0;
        right = arr.length - 1;
        while (left <= right) {
            mid = (left + right) / 2;
            midVal = arr[mid];

            if (midVal < ele) {
                left = mid + 1;
            } else if (midVal > ele) {
                right = mid - 1;
            } else {
                // 找到了这个数所对应的索引，返回
                return mid;
            }
        }
        return -1; // 没找到，返回-1
    }

    // 找不大
    public static int binaryFind(int[] arr, int ele) {
        int left, right, mid = -1, midVal;
        left = 0;
        right = arr.length - 1;
        while (left <= right) {
            mid = (left + right) / 2;
            midVal = arr[mid];

            if (midVal < ele) {
                left = mid + 1;
            } else if (midVal > ele) {
                right = mid - 1;
            } else {
                return mid - 1;
            }
        }
        return -1;
    }

    public static void main(String... args) {
        int[] arr = new int[]{2, 2, 3, 4, 5};
        System.out.println(arr[binaryFind(arr, 4)]);
    }
}
