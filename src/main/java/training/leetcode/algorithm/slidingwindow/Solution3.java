package training.leetcode.algorithm.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class Solution3 {
    /*
    Given a string s, find the length of the longest without duplicate characters.
     */

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;

        int maxLength = 0;
        int left = 0;
        Map<Character, Integer> charIndexMap = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            if (charIndexMap.containsKey(currentChar) && charIndexMap.get(currentChar) >= left) {
                left = charIndexMap.get(currentChar) + 1;
            }

            charIndexMap.put(currentChar, right);

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
