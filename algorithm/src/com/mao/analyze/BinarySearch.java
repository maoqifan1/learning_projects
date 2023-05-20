package com.mao.analyze;


import java.util.Arrays;

@SuppressWarnings("ALL")
public class BinarySearch {
    private static final int NOT_FOUND = -1;

    static <AnyType extends Comparable<? super AnyType>>
    int binarySearch(AnyType[] a, AnyType x) {

        Arrays.sort(a);
        int low = 0, high = a.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (a[mid].compareTo(x) < 0) {
                low = mid + 1;
            } else if (a[mid].compareTo(x) > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return NOT_FOUND;

    }
}

class test {
    public static void main(String[] args) {
        Integer[] a = {10, 2, 9, 7, 9, 6, 20};
        Integer result = BinarySearch.binarySearch(a, 2);
        System.out.println(result);

    }
}
