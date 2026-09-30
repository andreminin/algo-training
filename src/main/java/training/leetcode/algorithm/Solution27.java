package training.leetcode.algorithm;

import java.util.Arrays;

public class Solution27 {
    /*
      Given an integer array nums and an integer val, remove all occurrences of val in nums in-place. The order of the
      elements may be changed. Then return the number of elements in nums which are not equal to val.

        Consider the number of elements in nums which are not equal to val be k, to get accepted, you need to do the following things:

        Change the array nums such that the first k elements of nums contain the elements which are not equal to val.
        The remaining elements of nums are not important as well as the size of nums.
        Return k.
     */

    public int removeElement(int[] nums, int val) {
        int count = 0;
        int offset = nums.length - 1;

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == val) {
                while (offset > i) {
                    if(nums[offset] != val) {
                        nums[i] = nums[offset];
                        nums[offset] = val;
                        count++;
                        offset--;
                        break;
                    } else {
                        offset--;
                    }
                }
            } else {
                count++;
            }

            if(offset == i) {
                return count;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Solution27 solution = new Solution27();

        int[] nums = new int[] {3,2,2,3};
        System.out.println(solution.removeElement(nums, 3) + ", nums: "+ Arrays.toString(nums));

        nums = new int[] {0,1,2,2,3,0,4,2};
        System.out.println(solution.removeElement(nums, 2) + ", nums: "+ Arrays.toString(nums));

        nums = new int[] {1};
        System.out.println(solution.removeElement(nums, 1) + ", nums: "+ Arrays.toString(nums));
    }
}
