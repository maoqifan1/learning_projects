package com.mao.leetcode;


import com.sun.tools.javac.util.List;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FindString {
    private String fileText;

    // 读取文件
    public void readFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            this.fileText = reader.lines().reduce("", (a, b) -> a + " " + b);
            System.out.println(this.fileText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 分割文件中的单词
    private String[] splitTextBySpace() {
        return this.fileText.split(" ");
    }

    // 统计某个单词出现次数
    public int countNumberOfString(String arg) {
        int count = 0;
        for (int i = 0; i < fileText.length(); i++) {
            for (int j = 0; j < arg.length(); j++) {
                if (arg.charAt(j) == this.fileText.charAt(i)) {
                    if (j == arg.length() - 1) {
                        count++;
                    } else {
                        i++;
                    }
                } else {
                    break;
                }
            }
        }
        return count;
    }

    // 统计文章中某几个最常用的单词
    public String getCommonWords(int topNum) {
        StringBuilder resWords = new StringBuilder(); // 存储常用单词的字符串
        String[] words = this.splitTextBySpace(); // 文章中的所有单词
        Map<String, Integer> resMap = new HashMap<>(); // 存放结果的集合
        for (String word : words) {
            if (word.length() > 1)
                resMap.put(word, countNumberOfString(word)); // 存放单词及其响应的数量
        }
        Set<Map.Entry<String, Integer>> resSet = resMap.entrySet(); // 获得可迭代集合
        Iterator iterator = resSet.iterator(); // 获得迭代器
        while (iterator.hasNext()) {
            @SuppressWarnings("unchecked")
            Map.Entry<String, Iterator> wordInfo = (Map.Entry<String, Iterator>) iterator.next();
            System.out.println(wordInfo.getKey() + "  " + wordInfo.getValue());
        }

        return "";
    }

    public static void main(String[] args) {
        FindString findString = new FindString();
        findString.readFile("/Users/maoqifan/Documents/1.txt");
        findString.getCommonWords(2);
    }
}
