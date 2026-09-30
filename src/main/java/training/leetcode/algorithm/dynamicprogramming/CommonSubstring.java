package training.leetcode.algorithm.dynamicprogramming;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class CommonSubstring {

    public static int longestCommonSubsequence(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();

        int[][] dp = new int[m + 1][n + 1];

        System.out.println("\t\tLongest subsequence");
        System.out.print("\t");
        for(char ch : str2.toCharArray()) {
            System.out.print(ch + "\t");
        }
        System.out.println("");

        for (int i = 1; i <= m; i++) {
            System.out.print(str1.charAt(i-1) + "\t");

            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }

                System.out.print( dp[i][j] + "\t");
            }

            System.out.println();
        }

        // backtrack to get one LCS
        Deque<Character> queue = new ArrayDeque<>();
        int i = m;
        int j = n;

        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                queue.offerFirst(str1.charAt(i - 1));
                i -= 1;
                j -= 1;
            } else if (dp[i - 1][j] >=dp[i][j - 1]) {
                i -= 1;
            } else {
                j -= 1;
            }
        }

        StringBuilder chars = new StringBuilder();
        while (!queue.isEmpty()) {
            chars.append(queue.poll());
        }

        System.out.println("Longest subsequence: \"" + chars + "\"");

        return dp[m][n];
    }

    public static String findLongestCommonSubstring(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();

        int[][] dp = new int[m + 1][n + 1];

        int maxLength = 0;
        int endIndex = 0;

        System.out.println("\t\tLongest substring");
        System.out.print("\t");
        for(char ch : str2.toCharArray()) {
            System.out.print(ch + "\t");
        }
        System.out.println("");

        for (int i = 1; i <= m; i++) {
            System.out.print(str1.charAt(i-1) + "\t");

            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > maxLength) {
                        maxLength = dp[i][j];
                        endIndex = i;
                    }
                } else {
                    dp[i][j] = 0;
                }

                System.out.print( dp[i][j] + "\t");
            }

            System.out.println();
        }

        System.out.println();

        String substring;
        if (maxLength == 0) {
            substring = "";
        } else {
           substring = str1.substring(endIndex - maxLength, endIndex);
        }

        System.out.println("Longest substring: \"" + substring + "\"");
        System.out.println(substring.length());

        return substring;
    }

    // Space optimized version
    public static int longestCommonSubsequence2(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();

        String longer = m >= n ? str1 : str2;
        String shorter = m >= n ? str2 : str1;
        m = longer.length();
        n = shorter.length();

        int[] dp = new int[n + 1];
        int prev;

        System.out.println("\t\tLongest substring v2");
        System.out.print("\t");
        for(char ch : longer.toCharArray()) {
            System.out.print(ch + "\t");
        }
        System.out.println("");

        for (int i = 1; i <= m; i++) {
            prev = 0;
            for (int j = 1; j <= n; j++) {
                int temp = dp[j];
                if (longer.charAt(i - 1) == shorter.charAt(j - 1)) {
                    dp[j] = prev + 1;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
                prev = temp;
            }
        }

        return dp[n];
    }


    public static void main(String[] args) {
        longestCommonSubsequence("this is large story", "it was large bin");

        findLongestCommonSubstring("this is large story", "it was large bin");

        System.out.println("longestCommonSubsequence2: "+longestCommonSubsequence2("this is large story", "it was large bin"));
    }


}
