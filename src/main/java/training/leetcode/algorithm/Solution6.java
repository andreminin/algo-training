package training.leetcode.algorithm;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Solution6 {
    /*
        The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want
        to display this pattern in a fixed font for better legibility)

        P   A   H   N
        A P L S I I G
        Y   I   R

        And then read line by line: "PAHNAPLSIIGYIR"
        Write the code that will take a string and make this conversion given a number of rows:
        string convert(string s, int numRows);


        Example 1:
        Input: s = "PAYPALISHIRING", numRows = 3
        Output: "PAHNAPLSIIGYIR"

        Example 2:

        Input: s = "PAYPALISHIRING", numRows = 4
        Output: "PINALSIGYAHRPI"
        Explanation:
        P     I    N
        A   L S  I G
        Y A   H R
        P     I

     */

    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        int sLength = s.length();
        StringBuilder result = new StringBuilder();

        int cycleLen = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < sLength; j += cycleLen) {
                result.append(s.charAt(j + i));

                if (i != 0 && i != numRows - 1) {
                    int diagonalIndex = j + cycleLen - i;

                    if (diagonalIndex < sLength) {
                        result.append(s.charAt(diagonalIndex));
                    }
                }
            }
        }
        return result.toString();
    }

    public String convert2(String s, int numRows) {
        if (numRows == 1 || numRows >= s.length()) {
            return s;
        }

        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int r = 0;
        int direction = -1;

        for (char c : s.toCharArray()) {
            rows[r].append(c);

            if (r == 0 || r == numRows - 1) {
                direction = -direction;
            }

            r += direction;
        }

        return Arrays.stream(rows).map(StringBuilder::toString).collect(Collectors.joining());
    }

    public static void main(String[] args) {
        Solution6 solution = new Solution6();

        System.out.println(solution.convert("PAYPALISHIRING", 3));
        System.out.println(solution.convert("PAYPALISHIRING", 4));
    }
}
