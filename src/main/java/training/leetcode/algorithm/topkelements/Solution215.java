package training.leetcode.algorithm.topkelements;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution215 {
    /*
        Given an integer array nums and an integer k, return the kth largest element in the array.
        Note that it is the kth largest element in the sorted order, not the kth distinct element.
        Can you solve it without sorting?

        Example 1:
        Input: nums = [3,2,1,5,6,4], k = 2
        Output: 5

        Example 2:
        Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
        Output: 4
     */

    // Sorting is faster, but sorting is not allowed
    // Actually, sorting used internally in PriorityQueue, so we don't escape sorting completely
    public int findKthLargest_Prohibited(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length-k];
    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : nums) {
            if (minHeap.size() < k) {
                minHeap.offer(num);
            } else {
                if (num > minHeap.peek()) {
                    minHeap.poll();
                    minHeap.offer(num);
                }
            }
        }

        return minHeap.peek();
    }
}
