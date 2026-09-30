package training.leetcode.algorithm;

import java.util.HashMap;
import java.util.Map;

public class Solution166 {
    /*
    Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
    If the fractional part is repeating, enclose the repeating part in parentheses.
    If multiple answers are possible, return any of them.
    It is guaranteed that the length of the answer string is less than 104 for all the given inputs.

    Example 1:
    Input: numerator = 1, denominator = 2
    Output: "0.5"

    Example 2:
    Input: numerator = 2, denominator = 1
    Output: "2"

    Example 3:
    Input: numerator = 4, denominator = 333
    Output: "0.(012)"

     */

    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";

        StringBuilder result = new StringBuilder();

        if ((numerator < 0) ^ (denominator < 0)) {
            result.append("-");
        }

        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);

        result.append(num / den);
        long remainder = num % den;

        if (remainder == 0) {
            return result.toString();
        }

        result.append(".");
        Map<Long, Integer> remPosMap = new HashMap<>();

        while (remainder != 0) {
            if (remPosMap.containsKey(remainder)) {
                return result.insert(remPosMap.get(remainder), "(").append(")").toString();
            } else {
                remPosMap.put(remainder, result.length());
            }

            remainder *= 10;
            result.append(remainder / den);
            remainder %= den;
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Solution166 solution = new Solution166();
        System.out.println(solution.fractionToDecimal(1, 2));

        System.out.println(solution.fractionToDecimal(10, 3));

        System.out.println(solution.fractionToDecimal(4, 333));
    }
}
