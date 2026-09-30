package training.leetcode.algorithm;

public class Solution55_CanJump {
    /*
      55. Jump Game

    You are given an integer array nums. You are initially positioned at the array's first index, and each element
    in the array represents your maximum jump length at that position.
    Return true if you can reach the last index, or false otherwise.


    Example 1:
    Input: nums = [2,3,1,1,4]
    Output: true
    Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

    Example 2:
    Input: nums = [3,2,1,0,4]
    Output: false
    Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it
     impossible to reach the last index.

    Constraints:

        1 <= nums.length <= 104
        0 <= nums[i] <= 105

     */

    public boolean canJump(int[] nums) {
        int distance = 0;
        int m = nums.length;

        for (int i = 0; i < m; i++) {
            if (i > distance) {
                return false;
            }

            distance = Math.max(distance, i + nums[i]);

            if (distance >= m - 1) {
                return true;
            }
        }

        return false;
    }

    public boolean canJump2(int[] nums) {
        int lastPos = nums.length - 1;

        // Start from the second last element and move backwards
        for (int i = nums.length - 2; i >= 0; i--) {
            // If we can reach the last good position from current index
            if (i + nums[i] >= lastPos) {
                lastPos = i; // Update last good position
            }
        }

        return lastPos == 0;
    }

    public static void main(String[] args) {
        Solution55_CanJump solution = new Solution55_CanJump();

        System.out.println(solution.canJump(new int[] {2,5,0,0}));
        System.out.println(solution.canJump(new int[] {2,0}));
        System.out.println(solution.canJump(new int[] {2,3,1,1,4}));
        System.out.println(solution.canJump(new int[] {3,2,1,0,4}));
    }
}
