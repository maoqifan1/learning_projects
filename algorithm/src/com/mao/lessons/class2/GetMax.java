package com.mao.lessons.class2;

public class GetMax {

    public static int getMax(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    // arr[L..R]范围是求最大值
    public static int process(int[] arr, int L, int R) {
        if (L == R) { // base case ,当arr[L..R]范围上只有一个数时
            return arr[L];
        }
        int mid = L + ((R - L) >> 1);
//        int mid = (L +  R) >> 1;
        int leftMax = process(arr, L, mid);
        int rightMax = process(arr, mid + 1, R);
        return Math.max(leftMax, rightMax);
    }

    public static void main(String ...args){
        int max = getMax(new int[]{1,3,5,8});
        System
.out.println(max);
    }
}
