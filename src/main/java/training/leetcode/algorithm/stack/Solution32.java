package training.leetcode.algorithm.stack;

import java.util.Stack;

public class Solution32 {
    /*
        Given a string containing just the characters '(' and ')', return the length of the longest valid (well-formed) parentheses

        Example 1:
        Input: s = "(()"
        Output: 2
        Explanation: The longest valid parentheses substring is "()".

        Example 2:
        Input: s = ")()())"
        Output: 4
        Explanation: The longest valid parentheses substring is "()()".

        Example 3:
        Input: s = ""
        Output: 0
     */

    public int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }

        int maxLength = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxLength = Math.max(maxLength, i - stack.peek());
                }
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        Solution32 solution = new Solution32();

        System.out.println(solution.longestValidParentheses(""));
        System.out.println(solution.longestValidParentheses("(()"));
        System.out.println(solution.longestValidParentheses(")()())"));
    }
}
