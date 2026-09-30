package training.leetcode.algorithm.twopointers;

import java.util.*;

public class Solution15 {
    /*
        Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that
            i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
        Notice that the solution set must not contain duplicate triplets.

        Example 1:

        Input: nums = [-1,0,1,2,-1,-4]
        Output: [[-1,-1,2],[-1,0,1]]
        Explanation:
        nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
        nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
        nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
        The distinct triplets are [-1,0,1] and [-1,-1,2].
        Notice that the order of the output and the order of the triplets does not matter.

     */
    //Helper class for code clarity and maintenance
    static class Triplet {
        int a, b, c;

        public Triplet(int a, int b, int c) {
            // Store in sorted order to easily handle duplicates
            int[] arr = {a, b, c};
            Arrays.sort(arr);
            this.a = arr[0];
            this.b = arr[1];
            this.c = arr[2];
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Triplet triplet = (Triplet) obj;
            return a == triplet.a && b == triplet.b && c == triplet.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b, c);
        }

        public List<Integer> toList() {
            return Arrays.asList(a, b, c);
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Set<Triplet> resultSet = new HashSet<>();
        int n = nums.length;

        Arrays.sort(nums);

        for (int i = 0; i < n - 2; i++) {
            // First element in triplet scan
            // Skip duplicates for better performance
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = n - 1;
            int target = -nums[i];

            while (left < right) {
                int sum = nums[left] + nums[right];

                if (sum == target) {
                    resultSet.add(new Triplet(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        // Convert Triplet objects to List<Integer>
        List<List<Integer>> result = new ArrayList<>();
        for (Triplet triplet : resultSet) {
            result.add(triplet.toList());
        }

        return result;
    }
}
