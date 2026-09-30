package training.leetcode.algorithm;

import java.util.Arrays;

public class Solution14 {
    /*
        Write a function to find the longest common prefix string amongst an array of strings.
        If there is no common prefix, return an empty string "".

        Example 1:

        Input: strs = ["flower","flow","flight"]
        Output: "fl"
     */

    //Vertical scan
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        if (strs.length == 1) return strs[0];

        for(String str : strs) {
            if(str.length() == 0) {
                return "";
            }
        }

        for (int i = 0; i < strs[0].length(); i++) {
            char currentChar = strs[0].charAt(i);

            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != currentChar) {
                    return strs[0].substring(0, i);
                }
            }
        }

        return strs[0];
    }

    class TrieNode {
        TrieNode[] children;
        boolean isEnd;
        int count; // Number of words that pass through this node

        public TrieNode() {
            children = new TrieNode[26];
            isEnd = false;
            count = 0;
        }
    }

    public String longestCommonPrefix_sorted(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        // Sort the array
        Arrays.sort(strs);

        // Compare first and last string after sorting
        String first = strs[0];
        String last = strs[strs.length - 1];

        int i = 0;
        while (i < first.length() && i < last.length() && first.charAt(i) == last.charAt(i)) {
            i++;
        }

        return first.substring(0, i);
    }

    public String longestCommonPrefix_Trie(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        TrieNode root = new TrieNode();

        // Build the trie
        for (String word : strs) {
            if (word.isEmpty()) return "";
            TrieNode current = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (current.children[index] == null) {
                    current.children[index] = new TrieNode();
                }
                current = current.children[index];
                current.count++;
            }
            current.isEnd = true;
        }

        // Find the longest common prefix
        StringBuilder prefix = new StringBuilder();
        TrieNode current = root;

        while (true) {
            // Check if current node has exactly one child and all words pass through it
            TrieNode next = null;
            int childCount = 0;

            for (int i = 0; i < 26; i++) {
                if (current.children[i] != null && current.children[i].count == strs.length) {
                    next = current.children[i];
                    childCount++;
                    prefix.append((char)('a' + i));
                }
            }

            if (childCount != 1) break;
            current = next;
        }

        return prefix.toString();
    }

    public String longestCommonPrefix_divide_conquer(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        return divideAndConquer(strs, 0, strs.length - 1);
    }

    private String divideAndConquer(String[] strs, int left, int right) {
        if (left == right) {
            return strs[left];
        }

        int mid = (left + right) / 2;
        String leftLCP = divideAndConquer(strs, left, mid);
        String rightLCP = divideAndConquer(strs, mid + 1, right);

        return commonPrefix(leftLCP, rightLCP);
    }

    private String commonPrefix(String left, String right) {
        int minLength = Math.min(left.length(), right.length());
        for (int i = 0; i < minLength; i++) {
            if (left.charAt(i) != right.charAt(i)) {
                return left.substring(0, i);
            }
        }
        return left.substring(0, minLength);
    }



    public String longestCommonPrefix_horizontal(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        String prefix = strs[0];

        for (int i = 1; i < strs.length; i++) {
            // Reduce the prefix until it matches the current string
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }

        return prefix;
    }
}
