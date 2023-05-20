package com.maoqifan.customer;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class test {
    public static int binarySearch(int[] arr, int e) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = arr[mid];
            if (midVal < e) {
                low = mid + 1;
            } else if (midVal > e) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String... args) {

    }
}
