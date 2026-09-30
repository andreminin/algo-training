package training.leetcode.algorithm.dynamicprogramming;

public class Solution10 {
    /*
      Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:
        '.' Matches any single character.​​​​
        '*' Matches zero or more of the preceding element.
        The matching should cover the entire input string (not partial).

        Example 1:
        Input: s = "aa", p = "a"
        Output: false
        Explanation: "a" does not match the entire string "aa".

        Example 2:
        Input: s = "aa", p = "a*"
        Output: true
        Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".

        Example 3:
        Input: s = "ab", p = ".*"
        Output: true
        Explanation: ".*" means "zero or more (*) of any character (.)".

        Explanation

        Initialization: The dp table is initialized with dp[0][0] set to true because an empty string matches an empty pattern.

        Empty String Matching: The first loop handles cases where the pattern might match an empty string by checking if the pattern ends with * and adjusting the dp table accordingly.

        DP Table Filling: The nested loops iterate through each character of the string and pattern. For each character:

            If the pattern character matches the string character or is ., the current state is derived from the previous state.

            If the pattern character is *, it checks for zero occurrences (skipping the preceding character and *) or one or more occurrences (if the preceding character matches).

        Result: The value dp[m][n] indicates whether the entire string s matches the entire pattern p.
     */

    public boolean isMatch(String str, String pattern) {
        int m = str.length();
        int n = pattern.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        System.out.println("\t\tPattern match");

        System.out.println("\nInitial DP Matrix");
        System.out.print("\t");
        for(char ch : pattern.toCharArray()) {
            System.out.print(ch + "\t\t");
        }
        System.out.println();
        System.out.print("\t");
        for (int i = 1; i <= n; i++) {
            if (pattern.charAt(i - 1) == '*') {
                if (i >= 2) {
                    dp[0][i] = dp[0][i - 2];
                } else {
                    dp[0][i] = false; // Invalid pattern: '*' at start
                }
            }
            System.out.print( dp[0][i] + "\t");
        }

        System.out.println("\nProcessed DP Matrix");
        System.out.print("\t");
        for(char ch : pattern.toCharArray()) {
            System.out.print(ch + "\t\t");
        }
        System.out.println();

        for (int i = 1; i <= m; i++) {
            System.out.print(str.charAt(i-1) + "\t");

            for (int j = 1; j <= n; j++) {
                char s = str.charAt(i - 1);
                char p = pattern.charAt(j - 1);

                if (p == '.' || p == s) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p == '*') {
                    if (j > 1) {
                        // Zero occurrences of the preceding element
                        dp[i][j] = dp[i][j - 2];
                        char prevChar = pattern.charAt(j - 2);
                        if (prevChar == '.' || prevChar == s) {
                            // One or more occurrences of the preceding element
                            dp[i][j] = dp[i][j] || dp[i - 1][j];
                        }
                    } else {
                        dp[i][j] = false; // Invalid pattern: '*' at start
                    }
                } else {
                    dp[i][j] = false;
                }

                System.out.print( dp[i][j] + "\t");
            }

            System.out.println();
        }

        System.out.println("Matches: "+dp[m][n]);

        return dp[m][n];
    }

    public static void main(String[] args) {
        Solution10 solution = new Solution10();
        solution.isMatch("The long story", "The.*story");
    }
}
