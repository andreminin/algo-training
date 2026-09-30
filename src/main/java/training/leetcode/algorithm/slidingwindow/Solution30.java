package training.leetcode.algorithm.slidingwindow;

import java.util.*;

public class Solution30 {
    /*
       30. Substring with Concatenation of All Words

        You are given a string s and an array of strings words. All the strings of words are of the same length.
        A concatenated string is a string that exactly contains all the strings of any permutation of words concatenated.
            For example, if words = ["ab","cd","ef"], then "abcdef", "abefcd", "cdabef", "cdefab", "efabcd", and "efcdab"
             are all concatenated strings. "acdbef" is not a concatenated string because it is not the concatenation of any permutation of words.
        Return an array of the starting indices of all the concatenated substrings in s. You can return the answer in any order.


        Example 1:
        Input: s = "barfoothefoobarman", words = ["foo","bar"]
        Output: [0,9]
        Explanation:
        The substring starting at 0 is "barfoo". It is the concatenation of ["bar","foo"] which is a permutation of words.
        The substring starting at 9 is "foobar". It is the concatenation of ["foo","bar"] which is a permutation of words.

        Example 2:
        Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
        Output: []
        Explanation:
        There is no concatenated substring.

        Example 3:
        Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
        Output: [6,9,12]
        Explanation:
        The substring starting at 6 is "foobarthe". It is the concatenation of ["foo","bar","the"].
        The substring starting at 9 is "barthefoo". It is the concatenation of ["bar","the","foo"].
        The substring starting at 12 is "thefoobar". It is the concatenation of ["the","foo","bar"].

     */

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }

        int wordLen = words[0].length();
        int totalWords = words.length;
        int totalLen = wordLen * totalWords;

        if (s.length() < totalLen) {
            return result;
        }

        Map<String, Integer> masterWordFreq = new HashMap<>();
        for (String word : words) {
            masterWordFreq.merge(word, 1, Integer::sum);
        }

        // check starting positions from 0 to wordLen-1 - this covers all possible alignments
        for (int i = 0; i < wordLen; i++) {
            int left = i;
            int count = 0;

            Map<String, Integer> currentCount = new HashMap<>();

            for (int j = i; j <= s.length() - wordLen; j += wordLen) {
                String word = s.substring(j, j + wordLen);

                if (masterWordFreq.containsKey(word)) {
                    currentCount.put(word, currentCount.getOrDefault(word, 0) + 1);
                    count++;

                    // If we have too many occurrences of this word, shrink window from left
                    while (currentCount.get(word) > masterWordFreq.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        currentCount.merge(leftWord, -1, Integer::sum);
                        count--;
                        left += wordLen;
                    }

                    // If we found all words, add to result and slide window
                    if (count == totalWords) {
                        result.add(left);

                        // Move left pointer to continue searching
                        String leftWord = s.substring(left, left + wordLen);
                        currentCount.merge(leftWord, -1, Integer::sum);
                        count--;
                        left += wordLen;
                    }
                } else {
                    // Current word not in dictionary, reset window
                    currentCount.clear();
                    count = 0;
                    left = j + wordLen;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Solution30 solution = new Solution30();

        System.out.println(solution.findSubstring("barfoothefoobarman", new String[] {"foo","bar"}));
        System.out.println(solution.findSubstring("barfoofoobarthefoobarman", new String[] {"bar","foo","the"}));
        System.out.println(solution.findSubstring("barfoofoobarthefoobarman", new String[] {"word","good","best","word"}));
    }
}
