package training.leetcode.algorithm.dynamicprogramming;

import java.util.List;

public class Solution120 {
    /*
        Given a triangle array, return the minimum path sum from top to bottom.

        For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on
        the current row, you may move to either index i or index i + 1 on the next row.

        Example 1:

        Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
        Output: 11
        Explanation: The triangle looks like:
           2
          3 4
         6 5 7
        4 1 8 3
        The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).

        Example 2:

        Input: triangle = [[-10]]
        Output: -10

     */

    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n + 1];
        List<Integer> row;

        for (int i = n - 1; i >= 0; i--) {
            row = triangle.get(i);
            for (int j = 0; j < row.size(); j++) {
                dp[j] = row.get(j) + Math.min(dp[j], dp[j + 1]);
            }
        }

        return dp[0];
    }

    public int minimumTotal3(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];

        List<Integer> lastRow = triangle.get(n - 1);
        for (int i = 0; i < n; i++) {
            dp[n - 1][i] = lastRow.get(i);
        }

        for (int i = n - 2; i >= 0; i--) {
            List<Integer> row = triangle.get(i);
            for (int j = 0; j <= i; j++) {
                dp[i][j] = row.get(j) + Math.min(dp[i + 1][j], dp[i + 1][j + 1]);
            }
        }

        return dp[0][0];
    }

    public int minimumTotal2(List<List<Integer>> triangle) {
        if(triangle.isEmpty()) {
            return 0;
        }
        if(triangle.size() == 1) {
            return triangle.get(0).get(0);
        }

        int number = triangle.get(0).get(0);

        return pathSum(1, 0, number, triangle);
    }

    private int pathSum(int level, int offset, int sum, List<List<Integer>> triangle) {
        if(level >= triangle.size()) {
            return sum;
        }

        List<Integer> number = triangle.get(level);

        return Math.min(pathSum(level+1, offset, sum + number.get(offset), triangle),
                pathSum(level+1, offset+1, sum + number.get(offset+1), triangle));
    }



    public static void main(String[] args) {
        Solution120 solution = new Solution120();

        System.out.println(solution.minimumTotal(List.of(List.of(2),List.of(3,4),List.of(6,5,7),List.of(4,1,8,3))));
        System.out.println(solution.minimumTotal(List.of(List.of(-11))));
    }
}
