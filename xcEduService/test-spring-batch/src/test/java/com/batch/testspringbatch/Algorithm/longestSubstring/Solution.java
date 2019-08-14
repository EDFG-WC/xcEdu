package com.batch.testspringbatch.Algorithm.longestSubstring;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Solution {
    public static void main(String[] args) {
        String a = "abcabcbb";
        String b = "bbbbb";
        String c = "pwwkew";
        int i = lengthOfLongestSubstring("cdd");
        System.out.println(i);
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 1) {
            return 1;
        }
        char[] chars = s.toCharArray();
        //先把所有不重复字符串得到
        Set<String> set = new TreeSet<>();
        for (int i = 0; i < chars.length - 1; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(chars[i]);
            if (chars[i] == chars[i + 1]) {
                set.add(sb.toString());
                sb = new StringBuilder();
                continue;
            }
            for (int j = i + 1; j < chars.length - 1; j++) {
                if (chars[j] != chars[j + 1] && !sb.toString().contains(String.valueOf(chars[j]))) {
                    sb.append(chars[j]);
                } else {
                    set.add(sb.toString());
                    sb = new StringBuilder();
                    break;
                }
            }

            if (!sb.toString().contains(String.valueOf(chars[chars.length - 1]))) {
                sb.append(chars[chars.length - 1]);
                set.add(sb.toString());
            } else {
                set.add(sb.toString());
            }
        }
        if (set.size() != 0) {
            return String.valueOf(set.toArray()[set.size() - 1]).length();
        } else {
            return 0;
        }
    }
}