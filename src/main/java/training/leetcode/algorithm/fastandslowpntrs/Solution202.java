package training.leetcode.algorithm.fastandslowpntrs;

public class Solution202 {
    /*
        Write an algorithm to determine if a number n is happy.

        A happy number is a number defined by the following process:

        Starting with any positive integer, replace the number by the sum of the squares of its digits.
        Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
        Those numbers for which this process ends in 1 are happy.

        Return true if n is a happy number, and false if not.



     */

    private static final int[] SQUARES = new int[10];
    static {
        for (int i = 0; i < 10; i++) {
            SQUARES[i] = i * i;
        }
    }

    public boolean isHappy(int n) {
        //Floyd cycle check
        int slow = n;
        int fast = next(n);

        while (fast != 1 && slow != fast) {
            slow = next(slow);
            fast = next(next(fast));
        }
        return fast == 1;
    }

    private int next(int n) {
        int totalSum = 0;

        while (n > 0) {
            int digit = n % 10;
            totalSum += SQUARES[digit];
            n /= 10;
        }
        return totalSum;
    }
}
