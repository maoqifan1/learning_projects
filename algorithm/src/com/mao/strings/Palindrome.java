package com.mao.strings;



public class Palindrome {
    // 4.1. 最长回文串LeetCode:
    // 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。在构造过程中，请注意区分大小写。比如"Aa"不能当做一个回文字符串。注
    // 意:假设字符串的长度不会超过 1010。
    // 回文串：“回文串”是一个正读和反读都一样的字符串，比如“level”或者“noon”等等就是回文串
    // 输入:
    // "abccccdd"
    // 输出:
    // 7
    // 解释:
    // 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
    public static int longPalindrome(String str) {
        int[] map = new int[128];
        int count = 0;
        // 统计每个字符的出现数量
        for (char s : str.toCharArray()) {
            if (map[s] != 0) {
                map[s] = 0; // 成对出现
                count++;
            } else {
                map[s]++;
            }
        }
        return isEmpty(map) ? count * 2 : count * 2 + 1;
    }

    public static boolean isEmpty(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0)
                return false;
        }
        return true;
    }

    // 4.2. 验证回文串
    // LeetCode: 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。 说明：本题中，我们将空字符串定义为有效的回文串。
    public static boolean isPalindrome(String str) {
        str = str.replaceAll(" ", "").replaceAll("\\p{Punct}", "").toLowerCase();
        int len = str.length();
        for (int i = 0; i < len / 2; ++i) {
            // 对应位置比较
            if (str.charAt(i) == str.charAt(len - i - 1)) {
                continue;
            } else { // 不相等，break
                return false;
            }
        }
        return true;
    }

    // 4.3. 最长回文子串
    // Leetcode: LeetCode: 最长回文子串 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
    // 示例 1：

    // 输入: "babad"
    // 输出: "bab"
    // 注意: "aba"也是一个有效答案。
    // 示例 2：

    // 输入: "cbbd"
    // 输出: "bb"

    private int index, len;
    public String longestPalindrome(String s) {
      if (s.length() < 2)
        return s;
      for (int i = 0; i < s.length() - 1; i++) {
        // 一种是以字符串s中索引i的字符为中心，另一种是以s中索引i和i+1的字符组成的字符串为中心
        // 中心字符出现一次，即字符串s中索引i和i+1的字符相同
        PalindromeHelper(s, i, i);
        // 中心字符出现两次，即字符串s中索引i和i+1的字符不同
        PalindromeHelper(s, i, i + 1);
      }
      return s.substring(index, index + len);
    }
  
    public void PalindromeHelper(String s, int l, int r) {
      while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
        l--;
        r++;
      }
      if (len < r - l - 1) {
        index = l + 1;
        len = r - l - 1;
      }
    }

    public static void main(String... args) {
        Palindrome p = new Palindrome();
        System.out.println(p.longestPalindrome("babad"));
    }
}
