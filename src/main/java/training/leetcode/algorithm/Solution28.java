package training.leetcode.algorithm;

public class Solution28 {
    /*
    Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

    Example 1:
    Input: haystack = "sadbutsad", needle = "sad"
    Output: 0
    Explanation: "sad" occurs at index 0 and 6.
    The first occurrence is at index 0, so we return 0.

    Example 2:
    Input: haystack = "leetcode", needle = "leeto"
    Output: -1
    Explanation: "leeto" did not occur in "leetcode", so we return -1.

    Constraints:
        1 <= haystack.length, needle.length <= 104
        haystack and needle consist of only lowercase English characters.
     */

    public int strStr(String haystack, String needle) {
        int m = haystack.length();
        int n = needle.length();
        boolean foundMatch = true;

        if(n > m) {
            return -1;
        }

        for(int i = 0; i <= m - n; i++) {
            foundMatch = true;
            for(int j = 0; j < n; j++) {
                if(haystack.charAt(i + j) != needle.charAt(j)) {
                    foundMatch = false;
                    break;
                }
            }
            if(foundMatch) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Solution28 solution = new Solution28();
        System.out.println(solution.strStr("а", "а"));
        System.out.println(solution.strStr("hello", "ll"));

    }
}
