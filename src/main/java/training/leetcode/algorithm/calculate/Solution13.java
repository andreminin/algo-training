package training.leetcode.algorithm.calculate;

import java.util.HashMap;
import java.util.Map;

public class Solution13 {
    /*
       Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

        Symbol       Value
        I             1
        V             5
        X             10
        L             50
        C             100
        D             500
        M             1000

        For example, 2 is written as II in Roman numeral, just two ones added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.

        Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

            I can be placed before V (5) and X (10) to make 4 and 9.
            X can be placed before L (50) and C (100) to make 40 and 90.
            C can be placed before D (500) and M (1000) to make 400 and 900.

        Given a roman numeral, convert it to an integer.
     */

    public int romanToInt(String s) {
        Map<Character, Integer> values = new HashMap<>();
        values.put('I', 1);
        values.put('V', 5);
        values.put('X', 10);
        values.put('L', 50);
        values.put('C', 100);
        values.put('D', 500);
        values.put('M', 1000);

        int sum = 0;
        int length = s.length();
        int currVal;
        int nextVal;

        for (int i = 0; i < length - 1; i++) {
            currVal = values.get(s.charAt(i));
            nextVal = values.get(s.charAt(i + 1));

            if (currVal < nextVal) {
                sum -= currVal;
            } else {
                sum += currVal;
            }
        }

        sum += values.get(s.charAt(length - 1));

        return sum;
    }

    public int romanToInt2(String s) {
        int sum=0;
        int prev=0;
        for(char c:s.toCharArray())
        {
            switch(c)
            {
                case 'I': sum+=1;prev=1;break;
                case 'V': sum+=(prev==1)?3:5;prev=5;break;
                case 'X': sum+=(prev==1)?8:10;prev=10;break;
                case 'L': sum+=(prev==10)?30:50;prev=50;break;
                case 'C': sum+=(prev==10)?80:100;prev=100;break;
                case 'D': sum+=(prev==100)?300:500;prev=500;break;
                case 'M': sum+=(prev==100)?800:1000;prev=1000;break;
            }
        }
        return sum;
    }
}
