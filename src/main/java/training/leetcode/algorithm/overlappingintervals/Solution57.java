package training.leetcode.algorithm.overlappingintervals;

import java.util.ArrayList;
import java.util.List;

public class Solution57 {
    /*
    You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start
     and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval
     newInterval = [start, end] that represents the start and end of another interval.

    Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still
    does not have any overlapping intervals (merge overlapping intervals if necessary).

    Return intervals after the insertion.

    Note that you don't need to modify intervals in-place. You can make a new array and return it.
     */

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);

        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }


    public int[][] insert2(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        if (n == 0) {
            int[][] ans = new int[1][2];
            ans[0][0] = newInterval[0];
            ans[0][1] = newInterval[1];
            return ans;
        }
        int left = binSL(intervals, newInterval),
                right = binSR(intervals, newInterval);
        List<int[]> ans = new ArrayList<>();
        for (int i = 0; i < left; ++i)
            ans.add(intervals[i]);
        if (left >= 0) {
            if (intervals[left][1] < newInterval[0])
                ans.add(intervals[left]);
            else
                newInterval[0] = intervals[left][0];
        }
        ans.add(newInterval);
        if (right < n) {
            if (newInterval[1] < intervals[right][0])
                ans.add(intervals[right]);
            else
                newInterval[1] = intervals[right][1];
        }
        for (int i = right + 1; i < n; ++i) {
            ans.add(intervals[i]);
        }
        return ans.toArray(new int[ans.size()][]);
    }

    public int binSR (int[][] arr, int[] slot) {
        int start = 0, end = arr.length - 1, mid = 0;
        int ans = end+1;
        while (start <= end) {
            mid = start + (end-start)/2;
            if (arr[mid][1] >= slot[1]) {
                ans = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return ans;
    }

    public int binSL (int[][] arr, int[] slot) {
        int start = 0, end = arr.length - 1, mid = 0;
        int ans = -1;
        while (start <= end) {
            mid = start + (end-start)/2;
            if (arr[mid][0] <= slot[0]) {
                ans = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return ans;
    }
}
