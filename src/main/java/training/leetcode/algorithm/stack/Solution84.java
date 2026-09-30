package training.leetcode.algorithm.stack;

import java.util.Arrays;

public class Solution84 {
    /*
      Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
      return the area of the largest rectangle in the histogram.

      Algorithm Flow:

    Initialization: Handle empty array case and initialize stack

    Main Loop: Process each bar position i from 0 to n (inclusive)

        For i = n, we use a virtual bar of height 0 to ensure all remaining bars get processed

        Stack Processing (while loop):

        While current bar is shorter than the bar at stack top, we've found the right boundary

        Pop the bar from stack and calculate the rectangle area with that bar as minimum height

        Width calculation:

            If stack is empty: rectangle extends from start (index 0) to current index i

            Otherwise: rectangle extends from stack[offset] + 1 to i - 1

        Push Operation: Always push current index to maintain increasing height order

        Key Insights:

            The stack maintains indices of bars in increasing height order

            When we encounter a shorter bar, it becomes the right boundary for all taller bars in stack

            The left boundary for each popped bar is the next bar in the stack (or start if stack empty)

            The virtual bar (height 0) at the end ensures all bars get processed
     */

    public static void main(String[] args) {
        Solution84 solution = new Solution84();

        int[] heights = new int[]{2, 2, 2, 2, 3};
        int square = solution.largestRectangleArea(heights);
        System.out.println("heights: " + Arrays.toString(heights) + ", max square: " + square);
        assert square == 10;

        heights = new int[]{2, 1, 5, 6, 2, 3};
        square = solution.largestRectangleArea(heights);
        System.out.println("heights: " + Arrays.toString(heights) + ", max square: " + square);
        assert square == 10;
    }

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;

        if (n == 0) return 0;

        // stores heights indices
        int[] stack = new int[n + 1];
        // stack pointer, -1 is empty
        int offset = -1;
        int maxArea = 0;

        // Process all bars from stack that are taller than current bar
        // This means we've found the right boundary for these bars
        for (int i = 0; i <= n; i++) {
            // 0 needed to process remaining heights
            int currentHeight = (i == n) ? 0 : heights[i];

            while (offset >= 0 && currentHeight < heights[stack[offset]]) {
                int height = heights[stack[offset--]];
                // If stack is empty after popping, the rectangle extends from start to current index
                // Otherwise, it extends from next bar in stack (left boundary) to current index (right boundary)
                int width = offset < 0 ? i : i - stack[offset] - 1;
                maxArea = Math.max(maxArea, height * width);
            }

            // Push current bar index to stack (maintain increasing order of heights)
            stack[++offset] = i;
        }

        return maxArea;
    }
}
