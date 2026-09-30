package training.leetcode.algorithm.backtracking;

import java.util.*;
import java.util.stream.Collectors;

public class Solution40 {
    /*
      40. Combination Sum II

        Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations
         in candidates where the candidate numbers sum to target.

        Each number in candidates may only be used once in the combination.
        Note: The solution set must not contain duplicate combinations.


        Example 1:
        Input: candidates = [10,1,2,7,6,1,5], target = 8
        Output:  [
                    [1,1,6],
                    [1,2,5],
                    [1,7],
                    [2,6]
                ]

        Example 2:
        Input: candidates = [2,5,2,1,2], target = 5
        Output:
            [
                [1,2,2],
                [5]
            ]
     */

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);

        backtrack(candidates, target, 0, new ArrayList<>(), result);

        return result;
    }

    private void backtrack(int[] candidates, int remaining, int start, List<Integer> current, List<List<Integer>> result) {
        if (remaining == 0) {
            result.add(new ArrayList<>(current));

            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }

            if (candidates[i] > remaining) {
                break;
            }

            current.add(candidates[i]);

            backtrack(candidates, remaining - candidates[i], i + 1, current, result);

            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        Solution40 solution = new Solution40();

        System.out.println(solution.combinationSum2(new int[] {2,3,6,7}, 7));
        System.out.println(solution.combinationSum2(new int[] {2,3,5}, 8));
        System.out.println(solution.combinationSum2(new int[] {2}, 1));
        System.out.println(solution.combinationSum2(new int[] {1}, 1));
        System.out.println(solution.combinationSum2(new int[] {10,1,2,7,6,1,5}, 8));
    }
}
