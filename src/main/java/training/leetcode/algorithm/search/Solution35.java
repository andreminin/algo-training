package training.leetcode.algorithm.search;

public class Solution35 {
    /*
      35. Search Insert Position
        Given a sorted array of distinct integers and a target value, return the index if the target is found. If not,
         return the index where it would be if it were inserted in order.
        You must write an algorithm with O(log n) runtime complexity.

        Example 1:
        Input: nums = [1,3,5,6], target = 5
        Output: 2

        Example 2:
        Input: nums = [1,3,5,6], target = 2
        Output: 1

        Example 3:
        Input: nums = [1,3,5,6], target = 7
        Output: 4
     */

    public int searchInsert(int[] nums, int target) {
        int m = nums.length;
        int left = 0;
        int right = m - 1;
        int pos = -1;

        if(nums[right] < target) {
            return m;
        }
        if(nums[left] > target) {
            return 0;
        }

        while(left <= right) {
            pos = (right + left) / 2;

            if(nums[pos] == target) {
                return pos;
            } else if(nums[pos] < target) {
                if(nums[pos + 1] >= target) {
                    return pos + 1;
                }
                left = pos;
            } else if(nums[pos] > target) {
                if(nums[pos-1] < target) {
                    return pos;
                }
                if(nums[pos-1] == target) {
                    return pos-1;
                }
                right = pos;
            }
        }

        throw new RuntimeException("Can't find position, last pos ="+pos);
    }

    public static void main(String[] args) {
        Solution35 solution = new Solution35();

        System.out.println(solution.searchInsert(new int[] {1, 3, 5}, 1));
        System.out.println(solution.searchInsert(new int[] {1}, 1));
        System.out.println(solution.searchInsert(new int[] {1, 3}, 3));
        System.out.println(solution.searchInsert(new int[] {1,3,5,6}, 5));
        System.out.println(solution.searchInsert(new int[] {1,3,5,6}, 2));
        System.out.println(solution.searchInsert(new int[] {1,3,5,6}, 7));
    }
}
