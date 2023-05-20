package com.mao.strings;

import java.util.Arrays;

//编写一个函数来查找字符串数组中的最长公共前缀。如果不存在公共前缀，返回空字符串 ""。
//输入: ["flower","flow","flight"]
// 输出: "fl"
public class LongestCommonPrefix {

    public static String longestCommonPrefix(String[] strs) {
        if (!checkStrs(strs)) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        Arrays.sort(strs);
        int min_len = Math.min(strs[0].length(), strs[strs.length - 1].length());
        return checkPrefix(strs[0].substring(0, min_len), strs[strs.length - 1].substring(0, min_len));
    }

    public static String checkPrefix(String s1, String s2) {
        // 第一个字符不等则返回空串
        if (s1.charAt(0) != s2.charAt(0)) {
            return "";
        }
        if (s1.length() == 1 && s2.length() == 1) {
            return s1.equals(s2) ? s1 : "";
        }

        return s1.substring(0, 1) + checkPrefix(s1.substring(1), s2.substring(1));
    }

    private static boolean checkStrs(String[] strs) {
        boolean flag = false;
        if (strs != null) {
            // 遍历strs检查元素值
            for (int i = 0; i < strs.length; i++) {
                if (strs[i] != null && strs[i].length() != 0) {
                    flag = true;
                } else {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }

    

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[] { "flower", "flow", "flight" }));
    }

}