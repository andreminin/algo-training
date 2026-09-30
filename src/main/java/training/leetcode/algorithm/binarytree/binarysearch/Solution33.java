package training.leetcode.algorithm.binarytree.binarysearch;

public class Solution33 {
    /*
    There is an integer array nums sorted in ascending order (with distinct values).

    Prior to being passed to your function, nums is possibly left rotated at an unknown index k (1 <= k < nums.length)
     such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
     For example, [0,1,2,4,5,6,7] might be left rotated by 3 indices and become [4,5,6,7,0,1,2].

    Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums,
    or -1 if it is not in nums.

    You must write an algorithm with O(log n) runtime complexity.

     Explanation

    Initialization: We initialize low and high pointers to the start and end of the array, respectively.

    Binary Search Loop: We enter a loop that continues as long as low is less than or equal to high.

    Mid Calculation: We compute the mid index to split the current search interval.

    Target Check: If the element at the mid index matches the target, we return the mid index immediately.

    Left Half Check: If the left half (from low to mid) is sorted, we check if the target lies within this range.
    If it does, we adjust high to mid - 1 to search the left half; otherwise, we adjust low to mid + 1 to search the right half.

    Right Half Check: If the left half is not sorted, the right half must be sorted. We then check if the target lies
    within the right half. If it does, we adjust low to mid + 1 to search the right half; otherwise, we adjust high to mid - 1 to search the left half.

    Result: If the target is not found after the loop, we return -1.

     */

    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[low] <= nums[mid]) {
                if (target >= nums[low] && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }
}
