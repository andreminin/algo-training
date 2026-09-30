package training.leetcode.algorithm.prefixsum;

import java.util.Arrays;

public class Solution611 {
    /*
        Given an integer array nums, return the number of triplets chosen from the array that can make triangles if
        we take them as side lengths of a triangle.

        Example 1:
        Input: nums = [2,2,3,4]
        Output: 3
        Explanation: Valid combinations are:
        2,3,4 (using the first 2)
        2,3,4 (using the second 2)
        2,2,3

        Example 2:
        Input: nums = [4,2,3,4]
        Output: 4
     */


    public int triangleNumber2(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }

        Arrays.sort(nums);
        int count = 0;
        int n = nums.length;

        for (int i = n - 1; i >= 2; i--) {
            int left = 0;
            int right = i - 1;

            while (left < right) {
                if (nums[left] + nums[right] > nums[i]) {
                    count += (right - left);
                    right--;
                } else {
                    left++;
                }
            }
        }

        return count;
    }

    /*
        Uses mathematical combinations to count valid triangles efficiently

        Handles duplicate numbers naturally through frequency counting

        Good time complexity: O(MAX + n) where MAX is the maximum element
     */

    public int triangleNumber(int[] nums) {
        int MAX = 1000;

        int[] freq = new int[MAX + 1];
        int[] prefixSum = new int[MAX + 1];

        for (int x : nums) {
            if (x > 0) freq[x]++;
        }

        for (int i = 1; i <= MAX; i++) {
            prefixSum[i] = prefixSum[i - 1] + freq[i];
        }

        int count = 0;

        // All three sides equal
        for (int i = 1; i <= MAX; i++) {
            if (freq[i] >= 3) {
                count += freq[i] * (freq[i] - 1) * (freq[i] - 2) / 6;
            }
        }

        // Two sides equal
        for (int i = 1; i <= MAX; i++) {
            if (freq[i] >= 2) {
                // Third side must be < 2*i and ≠ i
                int maxThird = Math.min(MAX, 2 * i - 1);
                int validThird = prefixSum[maxThird] - freq[i];
                count += freq[i] * (freq[i] - 1) / 2 * validThird;
            }
        }

        // All sides distinct
        for (int i = 1; i <= MAX; i++) {
            if (freq[i] == 0) continue;
            for (int j = i + 1; j <= MAX; j++) {
                if (freq[j] == 0) continue;

                // Third side must be > j and < i + j
                int minK = j + 1;
                int maxK = Math.min(MAX, i + j - 1);
                if (minK > maxK) continue;

                int validK = prefixSum[maxK] - prefixSum[minK - 1];
                count += freq[i] * freq[j] * validK;
            }
        }

        return count;
    }

}
