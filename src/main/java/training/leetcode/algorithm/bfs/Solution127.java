package training.leetcode.algorithm.bfs;

import java.util.*;

public class Solution127 {
    /*
        A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of
         words beginWord -> s1 -> s2 -> ... -> sk such that:

        Every adjacent pair of words differs by a single letter.
        Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
        sk == endWord

        Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest
        transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
    */

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (beginWord.equals(endWord)) {
            return 1;
        }
        if (!wordSet.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int level = 1;

        while (!queue.isEmpty()) {
            level++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                char[] chars = current.toCharArray();

                for (int j = 0; j < chars.length; j++) {
                    char original = chars[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == original) continue;
                        chars[j] = c;
                        String newWord = new String(chars);
                        if (newWord.equals(endWord)) {
                            return level;
                        }
                        if (wordSet.contains(newWord)) {
                            queue.offer(newWord);
                            wordSet.remove(newWord);
                        }
                    }
                    chars[j] = original;
                }
            }
        }
        return 0;
    }

    public int ladderLength_2_Slow_N2L_complexity(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return 0;
        }
        if (beginWord.equals(endWord)) {
            return 1;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int level = 1;

        while (!queue.isEmpty()) {
            level++;
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String current = queue.poll();

                for(String word : new HashSet<>(wordSet)) {
                    if (isOneDifferent(current, word)) {
                        if (word.equals(endWord)) {
                            return level;
                        }
                        queue.offer(word);
                        wordSet.remove(word);
                    }
                }
            }
        }
        return 0;
    }

    //assume words have same length
    public boolean isOneDifferent(String word, String test) {
        int diffs = 0;
        int length = word.length();

        for (int i = 0; i < length; i++) {
            if (word.charAt(i) != test.charAt(i)) {
                diffs++;
            }

            if (diffs > 1) {
                return false;
            }
        }

        return diffs == 1;
    }
}
