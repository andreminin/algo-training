package training.leetcode.algorithm;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Solution20 {
    /*
      Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

    Open brackets must be closed by the same type of brackets.
    Open brackets must be closed in the correct order.
    Every close bracket has a corresponding open bracket of the same type.

    Example 1:

    Input: s = "()"

    Output: true

    Example 2:

    Input: s = "()[]{}"

    Output: true

     */

    private boolean match(char t, char s){
        return (t=='{' && s=='}') || (t=='(' && s==')') || (t=='[' && s==']');
    }
    public boolean isValid(String s) {
        int n = s.length();
        char[] stack = new char[n];
        int top = -1;
        for(char ch: s.toCharArray()){
            if(ch=='{' || ch=='[' || ch=='('){
                stack[++top] = ch;
            } else {
                if(top == -1 || !match(stack[top], ch)){
                    return false;
                }
                top--;
            }
        }
        return top == -1;
    }

    public boolean isValid2(String s) {
        if (s.length() % 2 != 0) return false;

        Deque<Character> stack = new ArrayDeque<>();

        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else {
                if (stack.isEmpty()) return false;

                char top = stack.pop();

                if ((ch == ')' && top != '(') ||
                        (ch == ']' && top != '[') ||
                        (ch == '}' && top != '{'))
                {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
