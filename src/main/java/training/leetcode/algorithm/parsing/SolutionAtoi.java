package training.leetcode.algorithm.parsing;

public class SolutionAtoi {
    public int myAtoi(String s) {
        if (s == null) return 0;

        int n = s.length();
        int index = 0;
        int sign = 1;
        int total = 0;

        while (index < n && s.charAt(index) == ' ') {
            index++;
        }

        if (index == n) return 0;

        if (s.charAt(index) == '+' || s.charAt(index) == '-') {
            sign = s.charAt(index) == '-' ? -1 : 1;
            index++;
        }

        while (index < n && s.charAt(index) == '0') {
            index++;
        }

        int maxValMod = Integer.MAX_VALUE / 10;

        while (index < n) {
            char c = s.charAt(index);

            if (c < '0' || c > '9') break;

            int value = c - '0';

            if (total > maxValMod || (total == maxValMod && value > 7)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            total = total * 10 + value;

            index++;
        }

        return sign * total;
    }

    public static void main(String[] args) {
        SolutionAtoi solution = new SolutionAtoi();

      //  int result = solution.myAtoi("-2147483647");

     //   System.out.println(result);

        System.out.println(solution.myAtoi("-21474836482"));
    }
}
