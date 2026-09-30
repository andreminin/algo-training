package training.leetcode.algorithm.overlappingintervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Solution56 {
    /*
    Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return
    an array of the non-overlapping intervals that cover all the intervals in the input.
     */


    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> merged = new ArrayList<>();
        int[] current;
        int[] last = intervals[0];
        merged.add(last);

        for (int i = 1; i < intervals.length; i++) {
            current = intervals[i];

            if (current[0] <= last[1]) {
                last[1] = Math.max(last[1], current[1]);
            } else {
                last = current;
                merged.add(current);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }


    public int[][] merge2(int[][] intervals) {
        int max = 0;
        for (int i = 0; i < intervals.length; i++) {
            max = Math.max(intervals[i][0], max);
        }

        int[] mp = new int[max + 1];
        for (int i = 0; i < intervals.length; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];
            mp[start] = Math.max(end + 1, mp[start]);
        }

        int r = 0;
        int have = -1;
        int intervalStart = -1;
        for (int i = 0; i < mp.length; i++) {
            if (mp[i] != 0) {
                if (intervalStart == -1) intervalStart = i;
                have = Math.max(mp[i] - 1, have);
            }
            if (have == i) {
                intervals[r++] = new int[] { intervalStart, have };
                have = -1;
                intervalStart = -1;
            }
        }

        if (intervalStart != -1) {
            intervals[r++] = new int[] { intervalStart, have };
        }
        if (intervals.length == r) {
            return intervals;
        }

        int[][] res = new int[r][];
        for (int i = 0; i < r; i++) {
            res[i] = intervals[i];
        }

        return res;
    }
}
