package training.leetcode.algorithm.prefixsum;

import java.util.HashMap;
import java.util.Map;

public class Solution525 {

    /*
    Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.

        Example 1:

        Input: nums = [0,1]
        Output: 2
        Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.

        Example 2:

        Input: nums = [0,1,0]
        Output: 2
        Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.

        Example 3:

        Input: nums = [0,1,1,1,1,1,0,0,0]
        Output: 6
        Explanation: [1,1,1,0,0,0] is the longest contiguous subarray with equal number of 0 and 1.

        Constraints:

        1 <= nums.length <= 105
     */
    public int findMaxLengthWithMap(int[] nums) {

        Map<Integer,Integer> sumToPos = new HashMap<>();
        sumToPos.put(0, -1);

        int max = 0;
        int sum = 0;

        for(int i = 0; i < nums.length; i++) {
            sum += nums[i] == 0 ? -1 : 1;

            if(sumToPos.containsKey(sum)) {
                max = Math.max(max, i - sumToPos.get(sum));
            } else {
                sumToPos.put(sum, i);
            }
        }

        return max;
    }

    public int findMaxLength(int[] nums) {
        //sum range is from -n to n; lets use array instead of map
        int length = nums.length;

        int[] sumToPos = new int[length * 2 + 1];
        int outOfRange = length + 1;

        for(int i = 0; i < sumToPos.length; i++) {
            sumToPos[i] = outOfRange;
        }

        sumToPos[length] = -1;

        int max = 0;
        int sum = 0;
        int offset;

        for(int i = 0; i < length; i++) {
            sum += nums[i] == 0 ? -1 : 1;
            offset = length + sum;

            if(sumToPos[offset] != outOfRange) {
                max = Math.max(max, i - sumToPos[offset]);
            } else {
                sumToPos[offset] = i;
            }
        }

        return max;
    }
}
