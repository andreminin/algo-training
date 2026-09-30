package training.leetcode.algorithm.twopointers;

public class Solution5 {
    /*
    Given a string s, return the longest palindromic substring in s.

        Example 1:

        Input: s = "babad"
        Output: "bab"
        Explanation: "aba" is also a valid answer.

     */

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";

        int start = 0, end = 0;
        int length = s.length();

        for (int i = 0; i < length; i++) {
            int len = Math.max(expand(s, i, i),  expand(s, i, i + 1));

            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private int expand(String s, int left, int right) {
        int length = s.length();

        while (left >= 0 && right < length && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1;
    }

    public String longestPalindrome_dp(String s) {
        if (s == null || s.length() < 2) return s;

        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int maxLength = 1;
        int start = 0;

        // All single characters are palindromes
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        // Check for 2-character palindromes
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                start = i;
                maxLength = 2;
            }
        }

        // Check for palindromes of length 3 and more
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;

                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    if (len > maxLength) {
                        start = i;
                        maxLength = len;
                    }
                }
            }
        }

        return s.substring(start, start + maxLength);
    }

    public static void main(String[] args) {
        Solution5 solution = new Solution5();

        System.out.println(solution.longestPalindrome("babad"));

        System.out.println(solution.longestPalindrome("cbbd"));
    }
}
