package training.leetcode.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Solution78 {
    /*
        Given an integer array nums of unique elements, return all possible (the power set).

        The solution set must not contain duplicate subsets. Return the solution in any order.
     */

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        generateSubsets(nums, 0, new ArrayList<>(), result);

        return result;
    }

    private void generateSubsets(int[] nums, int offset, List<Integer> temp, List<List<Integer>> result) {
        result.add(new ArrayList<>(temp));

        for (int i = offset; i < nums.length; i++) {
            temp.add(nums[i]);

            generateSubsets(nums, i + 1, temp, result);

            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {
        Solution78 solution = new Solution78();

        int[] nums = new int[] {1, 2, 3};

        List<List<Integer>> result = solution.subsets(nums);

        System.out.println(result);
    }
}
