package com.mao.leetcode;

import org.omg.CORBA.INTERNAL;

@SuppressWarnings("ALL")
public class reverseNumber {
    static int reverse(int x) {
        char[] str = String.valueOf(x).toCharArray();
        char[] temp = new char[str.length];
        if (str[0] == '-') {
            int j = 1;
            temp[0] = str[0];
            for (int i = str.length - 1; i > 0; i--) {
                temp[j] = str[i];
                j++;
            }
        } else {
            int j = 0;
            for (int i = str.length - 1; i >= 0; i--) {
                temp[j] = str[i];
                j++;
            }
        }
        int num = 0;
        try {
            num = Integer.valueOf(String.valueOf(temp)).intValue();
        } catch (NumberFormatException e) {
            return 0;
        }
        // 判断整数是否溢出
        if (num > 0x7fffffff || num < 0x80000000) {
            return 0;
        }
        return num;
    }

    static int myAtoi(String str) {
        // 判断字符串是否非空
        if (str == null || str.trim().isEmpty()) {
            return 0;
        }
        char[] temp = str.trim().toCharArray();

        StringBuffer strBuffer = new StringBuffer();
        // 根据第一个字符是否为 + - 号判断遍历起点
        int i = 0;
        if ((temp[0] == '-' || temp[0] == '+') && temp.length > 1) {
            i = 1;
            if (temp[1] == '-' || temp[1] == '+' || temp[1] == ' ' ||
                    ((temp[i] >= 65 && temp[i] <= 89) || temp[i] >= 97 && temp[i] <= 121))
                return 0;
            strBuffer.append(temp[0]);
        }
        for (int j = i; j < temp.length; j++) {

            // 如果第一位不是数字则直接返回0
            if (j == 0 && !(48 <= temp[j] && temp[j] <= 57))
                return 0;

            if (temp[j] == '.' || ((temp[j] >= 65 && temp[j] <= 89) ||
                    temp[j] >= 97 && temp[j] <= 122) || temp[j] == ' ' || temp[j] == '-' || temp[j] == '+')
                break;
            // 判断字符是否为数字
            if (48 <= temp[j] && temp[j] <= 57) {
                strBuffer.append(temp[j]);
            }
        }
        System.out.println(strBuffer);
        //判断是否溢出
        try {
            return Integer.parseInt(String.valueOf(strBuffer));
        } catch (NumberFormatException e) {
            if (temp[0] == '-')
                return Integer.MIN_VALUE;
            return Integer.MAX_VALUE;
        }
    }

    public static boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        char[] xArr = String.valueOf(x).toCharArray();
        boolean flag = true;
        for (int i = 0; i < xArr.length; i++) {
            System.out.println(xArr[i] +":"+ xArr[xArr.length - 1 - i]);
            if (xArr[i] == xArr[xArr.length - 1 - i])
                flag = true;
            else
                flag = false;
        }
        return flag;

    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(121));
    }

}
