package training.leetcode.algorithm.bfs;

public class Solution45_CanJump2 {
    /*
        You are given a 0-indexed array of integers nums of length n. You are initially positioned at index 0.

        Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at
         index i, you can jump to any index (i + j) where:

            0 <= j <= nums[i] and
            i + j < n

        Return the minimum number of jumps to reach index n - 1. The test cases are generated such that you can reach index n - 1.

        Example 1:
        Input: nums = [2,3,1,1,4]
        Output: 2
        Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.

        Example 2:
        Input: nums = [2,3,0,1,4]
        Output: 2

        Constraints:
            1 <= nums.length <= 104
            0 <= nums[i] <= 1000
            It's guaranteed that you can reach nums[n - 1].
     */

    public int jump(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;

        int jumps = 0;
        int currentMax = 0;  // Current level's maximum reach
        int nextMax = 0;     // Next level's maximum reach

        for (int i = 0; i < n; i++) {
            // Update the maximum reach for next level
            nextMax = Math.max(nextMax, i + nums[i]);

            // If we can reach the end from current position
            if (nextMax >= n - 1) {
                return jumps + 1;
            }

            // When we finish current level, move to next level
            if (i == currentMax) {
                jumps++;
                currentMax = nextMax;
            }
        }

        return jumps;
    }

    public int jump2(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;

        int jumps = 0;
        int currentEnd = 0;  // Farthest index reachable with current jumps
        int farthest = 0;    // Farthest index we can reach overall

        for (int i = 0; i < n - 1; i++) {
            // Update the farthest we can reach from current position
            farthest = Math.max(farthest, i + nums[i]);

            // If we've reached the end of current jump range
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;

                // If we can reach or exceed the last index
                if (currentEnd >= n - 1) {
                    break;
                }
            }
        }

        return jumps;
    }
}
