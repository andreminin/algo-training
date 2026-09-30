package training.leetcode.algorithm.prefixsum;

import java.util.HashMap;
import java.util.Map;

public class Solution560 {
    /*
     Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.

     A subarray is a contiguous non-empty sequence of elements within an array.
     */
    public int subarraySum(int[] nums, int k) {
        Map<Integer,Integer> sumToCount = new HashMap<>(nums.length + 1);
        int count = 0;
        int prefixSum = 0;
        sumToCount.put(0, 1);

        for(int num : nums) {
            prefixSum += num;

            count += sumToCount.getOrDefault( prefixSum - k, 0);
            sumToCount.put(prefixSum, sumToCount.getOrDefault( prefixSum, 0) + 1 );
        }

        return count;
    }
}
