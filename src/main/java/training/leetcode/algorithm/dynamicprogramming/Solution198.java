package training.leetcode.algorithm.dynamicprogramming;

public class Solution198 {
        /*
            You are a professional robber planning to rob houses along a street. Each house has a certain amount of money
            stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems
            connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

            Given an integer array nums representing the amount of money of each house, return the maximum amount of money
            you can rob tonight without alerting the police.

            Example 1:

            Input: nums = [1,2,3,1]
            Output: 4
            Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
            Total amount you can rob = 1 + 3 = 4.

            Approach

            The key insight is that for each house, the robber has two choices: either rob the current house and
            add its value to the sum from two houses before, or skip the current house and take the sum from the previous
            house. This leads to the recurrence relation:

            currentMax=max⁡(previousMax,currentHouseValue+maxBeforePrevious)
            currentMax=max(previousMax,currentHouseValue+maxBeforePrevious)

            We use two variables to keep track of the maximum sums from the previous house and the house before that,
            updating them iteratively as we traverse through the array of house values.

            Explanation

            Initialization: We initialize two variables a and b to 0. These represent the maximum sums from two houses before and one house before the current house, respectively.
            Iteration: For each house in the array:
                Store the current value of b in a temporary variable temp.
                Update b to the maximum of its current value (skipping the current house) or the sum of a and the current house value (robbing the current house).
                Set a to the stored value of b (which is the previous value of b before the update).

            Result: After processing all houses, b will hold the maximum amount that can be robbed without alerting the police.
         */

    public int rob(int[] nums) {
        int twoHousesBefore = 0;
        int oneHouseBeforeCurrent = 0;

        for (int i = 0; i < nums.length; i++) {
            int temp = oneHouseBeforeCurrent;
            oneHouseBeforeCurrent = Math.max(oneHouseBeforeCurrent, twoHousesBefore + nums[i]);
            twoHousesBefore = temp;
        }

        return oneHouseBeforeCurrent;
    }
}
