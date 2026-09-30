package training.leetcode.algorithm.matrixtraversal;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Solution733 {
    /*
      You are given an image represented by an m x n grid of integers image, where image[i][j] represents the pixel value of the image. You are also given three integers sr, sc, and color. Your task is to perform a flood fill on the image starting from the pixel image[sr][sc].

        To perform a flood fill:

    Begin with the starting pixel and change its color to color.
    Perform the same process for each pixel that is directly adjacent (pixels that share a side with the original pixel, either horizontally or vertically) and shares the same color as the starting pixel.
    Keep repeating this process by checking neighboring pixels of the updated pixels and modifying their color if it matches the original color of the starting pixel.
    The process stops when there are no more adjacent pixels of the original color to update.

        Return the modified image after performing the flood fill.
     */

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int startColor = image[sr][sc];
        if (startColor == color) {
            return image;
        }

        Deque<int[]> stack = new LinkedList<>();
        stack.push(new int[]{sr, sc});

        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            int r = current[0];
            int c = current[1];

            if (image[r][c] != startColor) {
                continue;
            }

            image[r][c] = color;

            pushIfValid(stack, r + 1, c, startColor, image);
            pushIfValid(stack, r - 1, c, startColor, image);
            pushIfValid(stack, r, c + 1, startColor, image);
            pushIfValid(stack, r, c - 1, startColor, image);
        }

        return image;
    }

    private void pushIfValid(Deque<int[]> stack, int r, int c, int startColor, int[][] image) {
        if (r >= 0 && r < image.length && c >= 0 && c < image[0].length && image[r][c] == startColor) {
            stack.push(new int[]{r, c});
        }
    }

    public int[][] floodFill_recursive(int[][] image, int sr, int sc, int color) {
        int startColor = image[sr][sc];

        if (startColor == color) {
            return image;
        }

        dfs(image, sr, sc, startColor, color);

        return image;
    }

    private void dfs(int[][] image, int r, int c, int startColor, int newColor) {
        if (r < 0 || r >= image.length || c < 0 || c >= image[0].length || image[r][c] != startColor) {
            return;
        }

        image[r][c] = newColor;
        dfs(image, r + 1, c, startColor, newColor);
        dfs(image, r - 1, c, startColor, newColor);
        dfs(image, r, c + 1, startColor, newColor);
        dfs(image, r, c - 1, startColor, newColor);
    }
}
