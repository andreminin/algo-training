package training.leetcode.algorithm.binarytree;

public class Solution124 {
    /*
        A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them.
         A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.

        The path sum of a path is the sum of the node's values in the path.

        Given the root of a binary tree, return the maximum path sum of any non-empty path.

        Explanation

        Initialization: The maxSum variable is initialized to the smallest possible integer value to ensure any valid
        path sum will be larger.

        Recursive Traversal: The maxGain function recursively computes the maximum gain from each node. For each node, it
        calculates the gains from the left and right subtrees, ignoring any negative gains.

        Path Calculation: The potential path sum through the current node is calculated as the sum of the node's value and
        the gains from both subtrees. This value is used to update the global maxSum if it is larger than the current maximum.

        Return Value: The function returns the maximum gain that can be achieved from the current node, which is the node's
        value plus the larger of the gains from its left or right subtree. This value is used by the parent nodes to compute their own gains.

        This approach efficiently computes the maximum path sum by ensuring each node is processed only once, leading
        to an optimal time complexity of O(N), where N is the number of nodes in the tree. The space complexity is O(H) due to the
        recursion stack, where H is the height of the tree.
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

    private int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    private int maxGain(TreeNode node) {
        if (node == null) return 0;

        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        int pathsSum = node.val + leftGain + rightGain;
        maxSum = Math.max(maxSum, pathsSum);

        return node.val + Math.max(leftGain, rightGain);
    }
}
