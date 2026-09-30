package training.leetcode.algorithm.search;

import java.util.Arrays;

public class Solution34 {
    /*
      34. Find First and Last Position of Element in Sorted Array

        Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
        If target is not found in the array, return [-1, -1].
        You must write an algorithm with O(log n) runtime complexity.

        Example 1:
        Input: nums = [5,7,7,8,8,10], target = 8
        Output: [3,4]

        Example 2:
        Input: nums = [5,7,7,8,8,10], target = 6
        Output: [-1,-1]

        Example 3:
        Input: nums = [], target = 0
        Output: [-1,-1]

     */

    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        int m = nums.length;
        if (m == 1) {
            return nums[0] == target ? new int[]{0, 0} : new int[]{-1, -1};
        }
        if(nums[0] > target || nums[m-1] < target) {
            return new int[]{-1, -1};
        }

        int left1 = 0;
        int right1 = m - 1;
        int left2 = 0;
        int right2 = m - 1;
        int pos;
        int start = -1;
        int end = -1;

        if(nums[0] == target) {
            start = 0;
            right1 = -1;
        }
        if(nums[m-1] == target) {
            end = m-1;
            left2 = m;
        }

        while (left1 <= right1 || left2 <= right2) {
            if (left1 <= right1) {
                pos = (right1 + left1) / 2;

                if (nums[pos] == target) {
                    if(nums[pos - 1] < target) {
                        start = pos;
                        left1 = right1 + 1;
                    } else {
                        right1 = pos-1;
                    }
                } else if (nums[pos] < target) {
                    if (nums[pos + 1] == target) {
                        start = pos + 1;
                        left1 = right1 + 1;
                    } else if (nums[pos + 1] > target) {
                        left1 = right1 + 1;
                    } else {
                        left1 = pos;
                    }
                } else {
                    right1 = pos;
                }
            }

            if (left2 <= right2) {
                pos = (right2 + left2) / 2;

                if (nums[pos] == target) {
                    if(nums[pos + 1] > target) {
                        end = pos;
                        left2 = right2 + 1;
                    } else {
                        left2 = pos + 1;
                    }
                } else if (nums[pos] > target) {
                    if (nums[pos - 1] == target) {
                        end = pos - 1;
                        left2 = right2 + 1;
                    } else if (nums[pos - 1] < target) {
                        left2 = right2 + 1;
                    } else {
                        right2 = pos;
                    }
                } else {
                    left2 = pos +1;
                }
            }
        }

        return new int[] {start, end};
    }

    public static void main(String[] args) {
        Solution34 solution = new Solution34();

        System.out.println(Arrays.toString(solution.searchRange(new int[] {1,2,3}, 2)));
        System.out.println(Arrays.toString(solution.searchRange(new int[] {1,5}, 4)));
        System.out.println(Arrays.toString(solution.searchRange(new int[] {2,2}, 1)));
        System.out.println(Arrays.toString(solution.searchRange(new int[] {5,7,7,8,8,10}, 8)));
        System.out.println(Arrays.toString(solution.searchRange(new int[] {5,7,7,8,8,10}, 6)));
        System.out.println(Arrays.toString(solution.searchRange(new int[] {5,7,7,8,8,8}, 8)));
        System.out.println(Arrays.toString(solution.searchRange(new int[] {7,7,7,8,8,8}, 7)));
    }
}
