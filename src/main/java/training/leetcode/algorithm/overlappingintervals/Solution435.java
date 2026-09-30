package training.leetcode.algorithm.overlappingintervals;

import java.util.Arrays;
import java.util.Comparator;

public class Solution435 {
    /*
        Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals
        you need to remove to make the rest of the intervals non-overlapping.

        Note that intervals which only touch at a point are non-overlapping. For example, [1, 2] and [2, 3] are non-overlapping.
     */

    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));

        int count = 1;
        int end = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= end) {
                count++;
                end = intervals[i][1];
            }
        }

        return intervals.length - count;
    }
}
