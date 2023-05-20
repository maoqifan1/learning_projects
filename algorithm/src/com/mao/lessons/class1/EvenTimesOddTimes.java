package com.mao.lessons.class1;

public class EvenTimesOddTimes {
    public static int evenTimesOddTime1(int[] nums) {
        int xor = 0;
        for (int x : nums) {
            xor ^= x;
        }
        return xor;
    }

    // 数组里有两个出现了奇数次的元素
    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        for (int j : arr) {
            eor ^= j;
        }
        // eor = a^b
        // eor != 0
        // eor必然有一个位置上是1
        int rightOne = eor & (~eor + 1); // 提取出最右的1

        int onlyOne = 0; // eor'
        for (int cur : arr) {
            if ((cur & rightOne) == 0) {
                onlyOne ^= cur;
            }
        }
        System.out.println(onlyOne + " " + (eor ^ onlyOne));
    }

}
