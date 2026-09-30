package training.leetcode.algorithm.dynamicprogramming;

import java.util.Arrays;

public class Solution322 {
    /*
        You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
        Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
        You may assume that you have an infinite number of each kind of coin.

        Example 1:
        Input: coins = [1,2,5], amount = 11
        Output: 3
        Explanation: 11 = 5 + 5 + 1

        Example 2:
        Input: coins = [2], amount = 3
        Output: -1

        Example 3:
        Input: coins = [1], amount = 0
        Output: 0

        Approach

        Dynamic Programming (DP) Setup: We use a DP array where dp[i] represents the minimum number of coins needed
            to make the amount i.

        Initialization: Initialize dp[0] to 0 because no coins are needed to make amount 0. For all other amounts,
            initialize with a value larger than the maximum possible (e.g., amount + 1) to signify that they are initially unreachable.

        Iterate Through Coins and Amounts: For each coin, update the DP array for all amounts from the coin value up
             to the target amount. For each amount, check if using the current coin reduces the number of coins needed
             compared to the current value in dp[i].

        Result Extraction: After processing all coins, if dp[amount] remains greater than amount, it means the amount
            cannot be formed, so return -1. Otherwise, return dp[amount].

        Explanation

        Initialization: The dp array is initialized with amount + 1 to represent an initially unreachable state for all
            amounts except 0, which is set to 0.

        Coin Processing: For each coin denomination, we iterate through all amounts starting from the coin's value up to
            the target amount. For each amount i, we update dp[i] to be the minimum of its current value or dp[i - coin] + 1,
            which represents using one coin of the current denomination plus the coins needed for the remaining amount.

        Result Check: After processing all coins, if the value at dp[amount] is still greater than amount, it means the
             amount cannot be formed with the given coins, so we return -1. Otherwise, we return the value at dp[amount],
            which is the minimum number of coins needed.

        This approach efficiently computes the solution using dynamic programming, ensuring optimal performance by
            leveraging the properties of coin denominations and iterative updates to the DP array. The time complexity
            is O(amount * number of coins), and the space complexity is O(amount).
         */

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }
}
