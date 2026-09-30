package training.leetcode.algorithm.permutation;

import java.util.Arrays;

public class Solution31 {
    /*
      31. Next Permutation
        A permutation of an array of integers is an arrangement of its members into a sequence or linear order.
            For example, for arr = [1,2,3], the following are all the permutations of arr: [1,2,3], [1,3,2], [2, 1, 3],
             [2, 3, 1], [3,1,2], [3,2,1].

        The next permutation of an array of integers is the next lexicographically greater permutation of its integer.
         More formally, if all the permutations of the array are sorted in one container according to their lexicographical
         order, then the next permutation of that array is the permutation that follows it in the sorted container.
         If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).
            For example, the next permutation of arr = [1,2,3] is [1,3,2].
            Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
            While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.

        Given an array of integers nums, find the next permutation of nums.
        The replacement must be in place and use only constant extra memory.

        Example 1:
        Input: nums = [1,2,3]
        Output: [1,3,2]

        Example 2:
        Input: nums = [3,2,1]
        Output: [1,2,3]

        Example 3:
        Input: nums = [1,1,5]
        Output: [1,5,1]

        Explanation

        Finding the Pivot: We start from the second last element and move backwards to find the first element that is
        smaller than the element immediately after it. This identifies the pivot point where the sequence can be rearranged to form the next permutation.

        Finding the Successor: If a pivot is found, we search from the end of the array to find the first element that
        is larger than the pivot. This element is swapped with the pivot to ensure the next permutation is just larger than the current one.

        Reversing the Suffix: After swapping, the elements after the pivot are in descending order. Reversing this portion
         ensures they are in ascending order, giving the smallest possible sequence after the pivot, which is the next lexicographical permutation.
     */

    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;

        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }

        reverse(nums, i + 1);
    }

    private void reverse(int[] nums, int start) {
        int end = nums.length - 1;

        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];

        nums[i] = nums[j];
        nums[j] = temp;
    }



    public static void main(String[] args) {
        Solution31 solution = new Solution31();

        int[] nums = new int[] {1,2,3};
        solution.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[] {3,2,1};
        solution.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[] {1,1,5};
        solution.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}
