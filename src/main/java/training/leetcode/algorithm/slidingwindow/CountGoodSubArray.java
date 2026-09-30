package training.leetcode.algorithm.slidingwindow;

import java.util.*;

class CountGoodSubArray {
    public long countGood(int[] nums, int k) {
        Map<Integer, Integer> freqCount = new HashMap<>(nums.length * 2);
        int pairs = 0;
        long count = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            pairs += freqCount.merge(nums[right], 1, Integer::sum) - 1;
            while (pairs >= k) {
                pairs -= freqCount.merge(nums[left++], -1, Integer::sum);
            }
            count += left;
        }
        return count;
    }
}
