package training.leetcode.algorithm.binarytree.binarysearch;

public class Solution153 {
    /*
     Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example,
      the array nums = [0,1,2,4,5,6,7] might become:

    [4,5,6,7,0,1,2] if it was rotated 4 times.
    [0,1,2,4,5,6,7] if it was rotated 7 times.

    Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].

    Given the sorted rotated array nums of unique elements, return the minimum element of this array.

    You must write an algorithm that runs in O(log n) time.


    Approach
    Binary Search Setup: Initialize two pointers, left and right, to the start and end of the array.
    Binary Search Execution:
        While left is less than right, calculate the middle index mid.
        Compare the element at mid with the element at right:
            If nums[mid] < nums[right], the minimum element must be in the left half, including mid. So, set right = mid.
            Otherwise, the minimum element must be in the right half, excluding mid. So, set left = mid + 1.
    Termination: When the loop ends, left will be pointing to the minimum element.
     */

    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return nums[left];
    }
}
