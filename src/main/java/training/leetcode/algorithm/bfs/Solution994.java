package training.leetcode.algorithm.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class Solution994 {
    /*
      You are given an m x n grid where each cell can have one of three values:
        0 representing an empty cell,
        1 representing a fresh orange, or
        2 representing a rotten orange.

        Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
        Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
     */

    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();
        int freshCount = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    freshCount++;
                }
            }
        }

        if (freshCount == 0) {
            return 0;
        }

        int minutes = 0;
        int[][] steps = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!queue.isEmpty()) {
            boolean foundRotted = false;
            int rottenCount = queue.size();
            for(int i = 0; i < rottenCount; i++) {
                int[] rotten = queue.poll();
                int x = rotten[0];
                int y = rotten[1];

                for (int[] step : steps) {
                    int nx = x + step[0];
                    int ny = y + step[1];

                    if (nx < 0 || nx > m - 1 || ny < 0 || ny > n - 1) {
                        //Out of bounds
                        continue;
                    }

                    if (grid[nx][ny] == 1) {
                        grid[nx][ny] = 2;
                        freshCount--;
                        queue.offer(new int[]{nx, ny});
                        foundRotted = true;
                    }
                }
            }

            if (foundRotted) {
                minutes++;
            }
        }

        return freshCount == 0 ? minutes : -1;
    }
}
