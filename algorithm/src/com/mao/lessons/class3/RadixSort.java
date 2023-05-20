package com.mao.lessons.class3;

import java.util.Arrays;

public class RadixSort {

    public static void radixSort(int[] arr) {
        
        // 先找到最大的数
        int max = Integer.MIN_VALUE;
        for (int num : arr) {
            max = Math.max(num, max);
        }
        // 最大数的位数
        int maxDigit = 0;
        while (max != 0) {
            ++maxDigit;
            max = max / 10;
        }
        // 10进制
        int base = 10;
        // 新建桶, 0-9是10个数所以有10个桶
        int[][] buckets = new int[base][arr.length];

        // 有多少位就进行多少轮的排序
        for (int i = 0; i < maxDigit; i++) {
            int[] bucketsCount = new int[base];
            for (int num : arr) {
                // 获取位数上的值,这里要是不会写也可以用字符串
                int digit = (int) (num / Math.pow(base, i)) % 10;
                // 把它加到桶里
                buckets[digit][bucketsCount[digit]] = num;
                // 这一位的数的数量++，即记录每个桶中元素的数量，为了之后还能把它放回原数组
                bucketsCount[digit]++;
            }
            int index = 0;
            // 有几个桶循环几次
            for (int j = 0; j < bucketsCount.length; ++j) {
                // 一个桶里有多少个数
                int count = bucketsCount[j];
                // 如果桶里有数
                if (count != 0) {
                    // 取出放回原数组，这里其实已经排序了，因为桶本身是有序的
                    for (int k = 0; k < count; ++k) {
                        arr[index++] = buckets[j][k];
                    }
                }
            }
        }
    }

    public static void main(String... args) {
        int[] arr = { 170, 45, 75, 90, 802, 24, 2, 66 };
        RadixSort.radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}