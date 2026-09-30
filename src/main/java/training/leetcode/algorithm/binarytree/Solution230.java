package training.leetcode.algorithm.binarytree;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution230 {
    /*
    Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
     */

    static class TreeNode {
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

    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.offerFirst(current);
                current = current.left;
            }
            current = stack.pollFirst();
            if(current != null) {
                k--;
                if (k == 0) {
                    return current.val;
                }
                current = current.right;
            }
        }

        return -1;
    }
}
