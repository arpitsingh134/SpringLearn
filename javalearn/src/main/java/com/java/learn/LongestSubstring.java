
package com.java.learn;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstring {
    public static String longestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int left = 0, right = 0, maxLen = 0;
        int start = 0;  // To store starting index of max substring

        while (right < s.length()) {
            char current = s.charAt(right);

            if (!set.contains(current)) {
                set.add(current);
                if (right - left + 1 > maxLen) {
                    maxLen = right - left + 1;
                    start = left;
                }
                right++;
            } else {
                set.remove(s.charAt(left));
                left++;
            }
        }

        return s.substring(start, start + maxLen);
    }

    public static String longestSubstringHashFunction(String s) {
        int[] hash = new int[256]; // default initialized to 0
        int left = 0, right = 0, maxLen = 0, start = 0;

        while (right < s.length()) {
            char current = s.charAt(right);
            hash[current]++;

            // If duplicate character found, shrink from left
            while (hash[current] > 1) {
                hash[s.charAt(left)]--;
                left++;
            }

            if (right - left + 1 > maxLen) {
                maxLen = right - left + 1;
                start = left;
            }

            right++;
        }

        return s.substring(start, start + maxLen);
    }

    public static void main(String[] args) {
        String str = "cadbzabcd";
        String s = longestSubstring(str);
        System.out.println("Longest unique substring: " + s + "\tand length LL :" + s.length());
    }
}