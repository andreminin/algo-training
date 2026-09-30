package training.leetcode.algorithm.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class Solution76 {
    /*
    Given two strings s and t of lengths m and n respectively, return the minimum window
    of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
    The testcases will be generated such that the answer is unique.
     */

    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";

        int[] freq = new int[128]; // ASCII, English words
        for (char c : t.toCharArray()) {
            freq[c]++;
        }

        int left = 0;
        int right = 0;
        int minLeft = 0;
        int minLength = Integer.MAX_VALUE;
        int unmatchCount = t.length();

        while (right < s.length()) {

            char rightChar = s.charAt(right++);
            if (freq[rightChar]-- > 0) {
                unmatchCount--;
            }

            // window is valid, shift left
            while (unmatchCount == 0) {

                if (right - left < minLength) {
                    minLength = right - left;
                    minLeft = left;
                }

                char leftChar = s.charAt(left++);
                if (freq[leftChar]++ == 0) {
                    unmatchCount++;
                }
            }
        }

        return minLength == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLength);
    }

    public String minWindowUsingMap(String s, String t) {

        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        int left = 0;
        int right = 0;
        int minLeft = 0;
        int minLength = Integer.MAX_VALUE;
        int unmatchCount = t.length();

        while (right < s.length()) {
            char rightChar = s.charAt(right);

            if (freqMap.containsKey(rightChar)) {
                freqMap.put(rightChar, freqMap.get(rightChar) - 1);
                if (freqMap.get(rightChar) >= 0) {
                    unmatchCount--;
                }
            }
            right++;

            while (unmatchCount == 0) {
                if (right - left < minLength) {
                    minLength = right - left;
                    minLeft = left;
                }

                char leftChar = s.charAt(left);

                if (freqMap.containsKey(leftChar)) {
                    freqMap.merge(leftChar, 1, Integer::sum);
                    if (freqMap.get(leftChar) > 0) {
                        unmatchCount++;
                    }
                }
                left++;
            }
        }

        return minLength == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLength);
    }
}
