package training.leetcode;

public class Solution7 {
    /*
        Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.
        Assume the environment does not allow you to store 64-bit integers (signed or unsigned).

        Example 1:
        Input: x = 123
        Output: 321

        Example 2:
        Input: x = -123
        Output: -321

        Example 3:
        Input: x = 120
        Output: 21
     */

    public int reverse(int x) {
        boolean negative = x < 0;
        int result = 0;

        if(negative) {
            x = -x;
        }

        int maxMod = Integer.MAX_VALUE / 10;

        while (x > 0) {
            int val = x % 10;
            if(result > maxMod) {
                return 0;
            } else if(result == maxMod && ((negative && val > 8) || (!negative && val > 7))) {
                return 0;
            }
            result = result * 10 + val;
            x = x / 10;
        }

        return negative ? -result : result;
    }

    public static void main(String[] args) {
        Solution7 solution = new Solution7();

        System.out.println(solution.reverse(123));
        System.out.println(solution.reverse(-123));
        System.out.println(solution.reverse(120));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(solution.reverse(Integer.MAX_VALUE));
        System.out.println(solution.reverse(900000));
    }
}
