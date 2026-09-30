package training.leetcode.algorithm.bfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Solution102 {
   /*
   Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
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

    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null) {
            return Collections.emptyList();
        }

        List<List<TreeNode>> levels = new ArrayList<>();
        List<TreeNode> level = new ArrayList<>();
        level.add(root);
        levels.add(level);

        do {
            List<TreeNode> nextLevel = new ArrayList<>();
            for (TreeNode node : level) {
                if (node.left != null) {
                    nextLevel.add(node.left);
                }
                if (node.right != null) {
                    nextLevel.add(node.right);
                }
            }
            if (!nextLevel.isEmpty()) {
                levels.add(nextLevel);
            }

            level = nextLevel;
        } while (!level.isEmpty());

        return levels.stream()
                .map(innerList -> innerList.stream() // Stream each inner List<TreeNode>
                        .map(node -> node.val)      // Map each TreeNode to its integer value
                        .collect(Collectors.toList())) // Collect the integers into a List<Integer>
                .collect(Collectors.toList());
    }
}
