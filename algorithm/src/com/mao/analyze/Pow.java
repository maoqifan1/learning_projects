package com.mao.analyze;

import java.math.BigInteger;
import java.util.LinkedList;

public class Pow {
    static BigInteger pow(BigInteger x, int n) {
        if (n == 0)
            return new BigInteger("1");
        if (n == 1)
            return x;
        if (isEven(n)) {
            return pow(x.multiply(x), n / 2);
        } else {
            return pow(x.multiply(x), n / 2).multiply(x);
        }
    }
    private static Boolean isEven(int n) {
        return n % 2 == 0;
    }

    public static void main(String[] args){
        System.out.println(Pow.pow(new BigInteger("200"),300000));
    }
}
