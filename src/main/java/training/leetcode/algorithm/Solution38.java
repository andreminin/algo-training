package training.leetcode.algorithm;

import java.util.ArrayList;
import java.util.List;

public class Solution38 {
    /*
      38. Count and Say

        The count-and-say sequence is a sequence of digit strings defined by the recursive formula:

            countAndSay(1) = "1"
            countAndSay(n) is the run-length encoding of countAndSay(n - 1).

        Run-length encoding (RLE) is a string compression method that works by replacing consecutive identical
        characters (repeated 2 or more times) with the concatenation of the character and the number marking the
        count of the characters (length of the run). For example, to compress the string "3322251" we replace "33"
         with "23", replace "222" with "32", replace "5" with "15" and replace "1" with "11". Thus the compressed
         string becomes "23321511".

        Given a positive integer n, return the nth element of the count-and-say sequence.

        Example 1:

        Input: n = 4
        Output: "1211"
        Explanation:
        countAndSay(1) = "1"
        countAndSay(2) = RLE of "1" = "11"
        countAndSay(3) = RLE of "11" = "21"
        countAndSay(4) = RLE of "21" = "1211"
     */

    public String countAndSay(int n) {
        String res = "1";
        for (int i = 1; i < n; i++) {
            res = buildNext(res);
        }
        return res;
    }

    private String buildNext(String s) {
        StringBuilder result = new StringBuilder();
        int count = 1;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                result.append(count).append(s.charAt(i - 1));
                count = 1;
            }
        }
        result.append(count).append(s.charAt(s.length() - 1));

        return result.toString();
    }

    public String countAndSay2(int n) {
        String str = "1";
        for(int i = 0; i < n-1; i++) {
            str = freqArrToStr(valueFreqArr(str));
        }

        return str;
    }

    private String freqArrToStr(int[][] freqArr) {
        StringBuilder result = new StringBuilder();
        for(int[] freq : freqArr) {
            result.append(freq[1]);
            result.append(freq[0]);
        }

        return result.toString();
    }

    private int[][] valueFreqArr(String str) {
        List<int[]>  valueFreqList = new ArrayList<>();

        char prev = ' ';
        int count = 0;
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(ch == prev) {
                count++;
            } else {
                if(prev != ' ') {
                    valueFreqList.add(new int[] {prev - '0', count});
                }
                prev = ch;
                count = 1;
            }
        }

        if(count > 0) {
            valueFreqList.add(new int[] {prev - '0', count});
        }

        return valueFreqList.toArray(new int[0][]);
    }



    public static  void main(String[] args) {
        Solution38 solution = new Solution38();

        System.out.println(solution.countAndSay(1));
        System.out.println(solution.countAndSay(4));
    }
}
