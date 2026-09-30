package training.leetcode.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Solution46 {
    /*
      Given an array nums of distinct integers, return all the possible . You can return the answer in any order.
     */

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        generatePermutations(nums, 0, result);

        return result;
    }

    private void generatePermutations(int[] nums, int index, List<List<Integer>> result) {
        if (index == nums.length) {
            List<Integer> current = new ArrayList<>(nums.length);
            for (int num : nums) {
                current.add(num);
            }
            result.add(current);

            return;
        }

        for (int i = index; i < nums.length; i++) {
            swap(nums, index, i);

            generatePermutations(nums, index + 1, result);

            swap(nums, index, i);
        }
    }

    private void swap(int[] nums, int i, int j) {
        if(i == j) return;

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        backtrack(result, new ArrayList<>(), nums, new boolean[nums.length]);

        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> permutation, int[] nums, boolean[] used) {
        if (permutation.size() == nums.length) {
            result.add(new ArrayList<>(permutation));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (used[i]) continue;

                used[i] = true;
                permutation.add(nums[i]);

                backtrack(result, permutation, nums, used);

                used[i] = false;
                permutation.remove(permutation.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Solution46 solution = new Solution46();

        int[] nums = new int[] {1, 2, 3};

        List<List<Integer>> result = solution.permute(nums);

        System.out.println(result);
    }
}
