package training.leetcode.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Solution17 {
    /*
         Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number
         could represent. Return the answer in any order.

        A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
     */


    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();

        if (digits == null || digits.length() == 0) {
            return result;
        }

        for (char c : digits.toCharArray()) {
            if (c < '2' || c > '9') {
                return new ArrayList<>();
            }
        }

        String[] phoneMap = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        result.add("");

        for (char digitChar : digits.toCharArray()) {
            int digit = digitChar - '0';
            List<String> temp = new ArrayList<>();

            for (String combination : result) {
                for (char letter : phoneMap[digit].toCharArray()) {
                    temp.add(combination + letter);
                }
            }

            result = temp;
        }

        return result;
    }

    private static final String[] KEYPAD = {
            "",
            "",
            "abc",
            "def",
            "ghi",
            "jkl",
            "mno",
            "pqrs",
            "tuv",
            "wxyz"
    };

    public List<String> letterCombinations2(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }

        backtrack(result, new StringBuilder(), digits, 0);
        return result;
    }

    private void backtrack(List<String> result, StringBuilder current, String digits, int index) {
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        String letters = KEYPAD[digits.charAt(index) - '0'];
        for (char c : letters.toCharArray()) {
            current.append(c);
            backtrack(result, current, digits, index + 1);
            current.deleteCharAt(current.length() - 1); // backtrack
        }
    }

    public static void main(String[] args) {
        Solution17 solution = new Solution17();
        System.out.println(solution.letterCombinations("2"));

        System.out.println(solution.letterCombinations("23"));

        System.out.println(solution.letterCombinations("2234"));
    }
}
