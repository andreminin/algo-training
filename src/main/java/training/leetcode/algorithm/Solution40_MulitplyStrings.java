package training.leetcode.algorithm;

public class Solution40_MulitplyStrings {
    /*
      43. Multiply Strings

        Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
        Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.


        Example 1:
        Input: num1 = "2", num2 = "3"
        Output: "6"

        Example 2:

        Input: num1 = "123", num2 = "456"
        Output: "56088"

        Constraints:

        1 <= num1.length, num2.length <= 200
        num1 and num2 consist of digits only.
        Both num1 and num2 do not contain any leading zero, except the number 0 itself
     */

    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        int m = num1.length();
        int n = num2.length();
        int[] result = new int[m + n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int digit1 = num1.charAt(i) - '0';
                int digit2 = num2.charAt(j) - '0';
                int product = digit1 * digit2;

                // Position in result array
                int pos1 = i + j;
                int pos2 = i + j + 1;

                // Add and handle carry
                int sum = product + result[pos2];
                result[pos2] = sum % 10;
                result[pos1] += sum / 10;
            }
        }

        // Convert array to string
        StringBuilder sb = new StringBuilder();
        for (int num : result) {
            // Skip leading zeros
            if (!(sb.length() == 0 && num == 0)) {
                sb.append(num);
            }
        }

        return sb.isEmpty() ? "0" : sb.toString();
    }

    public static void main(String[] args) {
        Solution40_MulitplyStrings solution = new Solution40_MulitplyStrings();

        System.out.println(solution.multiply("123", "456"));
    }
}
