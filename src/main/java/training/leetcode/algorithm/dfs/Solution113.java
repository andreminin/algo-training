package training.leetcode.algorithm.dfs;

import java.util.ArrayList;
import java.util.List;

public class Solution113 {
    /*
      Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where the sum of the node
       values in the path equals targetSum. Each path should be returned as a list of the node values, not node references.

        A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.
     */

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> paths = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        dfs(root, targetSum, 0, path, paths);

        return paths;
    }

    private void dfs(TreeNode node, int targetSum, int currentSum, List<Integer> path, List<List<Integer>> paths) {
        if (node == null) {
            return;
        }

        currentSum += node.val;
        path.add(node.val);

        if (node.left == null && node.right == null) {
            if (currentSum == targetSum) {
                paths.add(new ArrayList<>(path));
            }
        } else {
            dfs(node.left, targetSum, currentSum, path, paths);
            dfs(node.right, targetSum, currentSum, path, paths);
        }

        path.remove(path.size() - 1);
    }
}
