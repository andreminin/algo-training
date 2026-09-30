package training.leetcode.algorithm.dynamicprogramming;

public class Solution70 {
    /*
        You are climbing a staircase. It takes n steps to reach the top.

        Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

        We can use dynamic programming to solve this problem.
        Let dp[i] represent the number of distinct ways to climb i steps.
        We know that:
        dp[0] = 1 (one way to stay at ground)
        dp[1] = 1 (only one step)
        For n>=2: dp[n] = dp[n-1] + dp[n-2] (because we can come from one step below or two steps below)

        However, note that the problem is equivalent to the Fibonacci sequence.

        We can avoid using an array for the entire n if we only need the last two values.

        Steps:
        if n <= 1, return 1.
        Otherwise, initialize:
        a = 1, b = 1
        for i from 2 to n:
        c = a + b
        a = b
        b = c
        return b

        Example for n=2:
        i=2: c = 1+1 = 2, then a becomes 1, b becomes 2 -> return 2.

        Example for n=3:
        i=2: c=2, a=1, b=2
        i=3: c=1+2=3, a=2, b=3 -> return 3.

        So we can code accordingly.

        Approach

        The key observation is that the number of ways to reach the n-th step is the sum of the ways to reach the
        (n-1)-th step (from which you take a single step) and the ways to reach the (n-2)-th step (from which you take
         two steps). This leads to the recurrence relation:

        dp[n]=dp[n−1]+dp[n−2]

        with base cases:

            There is 1 way to stay at ground level (0 steps).

            There is 1 way to climb 1 step.

        To optimize space, we use two variables to keep track of the previous two values instead of maintaining an entire array.

        Explanation

        Base Cases Handling: If n is 0 or 1, return 1 since there's only one way to climb 0 or 1 step.

        Initialization: Initialize two variables a and b to 1, representing the number of ways to climb 0 and 1 steps, respectively.

        Iterative Calculation: For each step from 2 to n, compute the number of ways to reach the current step by
         summing the values of the two previous steps. Update the variables a and b to hold the values for the next iteration.

        Result: After processing all steps up to n, b contains the number of distinct ways to climb n steps
     */

    public int climbStairs(int n) {
        if (n <= 1) {
            return 1;
        }
        int a = 1, b = 1;
        for (int i = 2; i <= n; i++) {
            int c = a + b;
            a = b;
            b = c;
        }
        return b;
    }
}
