package com.mao.strings;


// 请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为 We Are Happy.则经过替换之后的字符串为 We%20Are%20Happy。
public class SpaceReplace {
    public static String spaceReplace(String str) {
        StringBuilder result = new StringBuilder();
        for (char c : str.toCharArray()) {
            result.append(c == ' ' ? "%20" : String.valueOf(c));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(spaceReplace("Hello world"));
    }
}