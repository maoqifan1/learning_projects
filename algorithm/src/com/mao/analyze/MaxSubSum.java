package com.mao.analyze;

public class MaxSubSum {

    private static int maxSubSum1(int[] a) {
        int maxSum = 0;

        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                int thisSum = 0;

                for (int k = 0; k <= j; k++)
                    thisSum += a[k];

                if (thisSum > maxSum) {
                    maxSum = thisSum;
                }
            }
        }
        return maxSum;
    }

    private static int maxSubSum2(int[] a) {
        int maxSum = 0;

        for (int i = 0; i < a.length; i++) {
            int thisSum = 0;

            for (int j = i; j < a.length; j++) {
                thisSum += a[j];

                if (thisSum > maxSum) {
                    maxSum = thisSum;
                }
            }
        }
        return maxSum;
    }

    private static int maxSubSum4(int[] a) {
        int maxSum = 0, thisSum = 0;

        for(int i=0;i<a.length;i++){
            thisSum+=a[i];

            if(thisSum>maxSum){
                maxSum = thisSum;
            }else if(thisSum<0){
                thisSum = 0;
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, -5, -4, 10};
        int maxSum = maxSubSum2(array);
        System.out.println(maxSum);
    }
}
