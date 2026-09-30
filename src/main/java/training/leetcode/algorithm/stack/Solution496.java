package training.leetcode.algorithm.stack;

import java.util.*;

public class Solution496 {
    /*
        The next greater element of some element x in an array is the first greater element that is to the right
        of x in the same array.

        You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.

        For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater
         element of nums2[j] in nums2. If there is no next greater element, then the answer for this query is -1.

        Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.

        Example 1:

        Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
        Output: [-1,3,-1]
        Explanation: The next greater element for each value of nums1 is as follows:
        - 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
        - 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
        - 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.

     */

    //Not memory efficient solution using array, but should be faster than hashmap
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // Since values range from 0 to 10000, we can use an array of size 10001
        int[] nextGreaterArr = new int[10001];
        // minor optimization - num1 is subset of num2, no need to fillout by -1
        // Arrays.fill(nextGreaterArr, -1);

        int[] stack = new int[nums2.length];
        int top = -1;

        for (int i = nums2.length - 1; i >= 0; i--) {
            int currentNum = nums2[i];

            while (top >= 0 && stack[top] <= currentNum) {
                top--;
            }

            if (top >= 0) {
                nextGreaterArr[currentNum] = stack[top];
            } else {
                nextGreaterArr[currentNum] = -1;
            }

            stack[++top] = currentNum;
        }


        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = nextGreaterArr[nums1[i]];
        }

        return result;
    }

    // hashmap based concise solution, better for maintenance, no limitations like in array based
    public int[] nextGreaterElementConcise(int[] nums1, int[] nums2) {
        Deque<Integer> deque = new ArrayDeque<>();

        Map<Integer, Integer> nextGreater = new HashMap<>();

        for (int i = nums2.length - 1; i >= 0; i--) {
            int num = nums2[i];

            //keep next greater element in stack
            while (!deque.isEmpty() && deque.peek() <= num) {
                deque.pop();
            }

            if (deque.isEmpty()) {
                // no greater elements, map to  -1
                nextGreater.put(num, -1);
            } else {
                // map to next greate element
                nextGreater.put(num, deque.peek());
            }

            //store current element
            deque.push(num);
        }

        int[] result = new int[nums1.length];

        for (int i = 0; i < nums1.length; i++) {
            result[i] = nextGreater.get(nums1[i]);
        }

        return result;
    }
}
