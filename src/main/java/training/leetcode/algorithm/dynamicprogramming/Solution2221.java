package training.leetcode.algorithm.dynamicprogramming;

public class Solution2221 {
    /*
        You are given a 0-indexed integer array nums, where nums[i] is a digit between 0 and 9 (inclusive).

        The triangular sum of nums is the value of the only element present in nums after the following process terminates:

        Let nums comprise of n elements. If n == 1, end the process. Otherwise, create a new 0-indexed integer array newNums of length n - 1.
        For each index i, where 0 <= i < n - 1, assign the value of newNums[i] as (nums[i] + nums[i+1]) % 10, where % denotes modulo operator.
        Replace the array nums with newNums.
        Repeat the entire process starting from step 1.

        Return the triangular sum of nums.
     */

    public int triangularSum(int[] nums) {
        int m = nums.length;

        if(m == 1) {
            return nums[0];
        }

        int[] dp = new int[m];
        int length = m;
        for(int i = 0; i < m; i++) {
            dp[i] = nums[i];
        }

        while(length > 1) {
            for(int i = 1; i < length; i++) {
                dp[i-1] = (dp[i-1] + dp[i])%10;
            }
            length--;
        }

        return dp[0];
    }
}
