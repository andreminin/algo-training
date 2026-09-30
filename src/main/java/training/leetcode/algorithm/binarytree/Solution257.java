package training.leetcode.algorithm.binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solution257 {
    /*

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

    //Recursive solution
    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        dfs(root, "", result);
        return result;
    }

    private void dfs(TreeNode node, String path, List<String> result) {
        String currentPath = path + node.val;
        if (node.left == null && node.right == null) {
            result.add(currentPath);
            return;
        }
        if (node.left != null) {
            dfs(node.left, currentPath + "->", result);
        }
        if (node.right != null) {
            dfs(node.right, currentPath + "->", result);
        }
    }

    //Stack solution
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Deque<Pair<TreeNode, String>> stack = new ArrayDeque<>();
        stack.push(new Pair<>(root, Integer.toString(root.val)));

        while (!stack.isEmpty()) {
            Pair<TreeNode, String> pair = stack.poll();

            TreeNode node = pair.getKey();
            String currentPath = pair.getValue();

            if (node.left == null && node.right == null) {
                result.add(currentPath);
            }

            if (node.right != null) {
                stack.push(new Pair<>(node.right, currentPath + "->" + node.right.val));
            }

            if (node.left != null) {
                stack.push(new Pair<>(node.left, currentPath + "->" + node.left.val));
            }
        }

        return result;
    }

    static class Pair<K, V> {
        private final K key;
        private final V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }


}
