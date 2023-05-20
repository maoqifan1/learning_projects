package com.mao;

import java.util.Arrays;
import java.util.Scanner;

public class Array {

    static int findMax(int[] numArray, int k) {
        if (numArray.length <= 1) {
            return numArray[0];
        } else {
            Arrays.sort(numArray);
            return numArray[numArray.length - k];
        }
    }

    public static Integer[] arraySort(Integer[] numArray) throws Exception {
        if (numArray.length <= 1) {
            return numArray;
        } else {
            Integer pivot = numArray[0];
            Integer[] more = new Integer[numArray.length];
            Integer[] less = new Integer[numArray.length];
            for (int x : numArray) {
                if (x > pivot) {
                    Array.insert(more, x);
                } else {
                    Array.insert(less, x);
                }
            }
            Integer []resultArray = new Integer[more.length + less.length + 1];
            System.arraycopy(less,0,resultArray,0,less.length);
            return resultArray;
        }
    }

    @SuppressWarnings({"unchecked"})
    static void insert(Integer[] numArray, int element) throws Exception {
        int length = numArray.length;
        int destination[] = new int[length + 1];
        System.arraycopy(numArray, 0, destination, 0, length);
        destination[length - 1] = element;
        System.arraycopy(numArray, length, destination, length
                + 1, length);
    }
}
