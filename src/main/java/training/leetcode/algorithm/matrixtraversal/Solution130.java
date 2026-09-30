package training.leetcode.algorithm.matrixtraversal;

import java.util.LinkedList;
import java.util.Queue;

public class Solution130 {
    /*
       You are given an m x n matrix board containing letters 'X' and 'O', capture regions that are surrounded:

        Connect: A cell is connected to adjacent cells horizontally or vertically.
        Region: To form a region connect every 'O' cell.
        Surround: The region is surrounded with 'X' cells if you can connect the region with 'X' cells and none of the region cells are on the edge of the board.

    To capture a surrounded region, replace all 'O's with 'X's in-place within the original board. You do not need to return anything.



    Example 1:

    Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]

    Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
     */

    class Solution {

        public void solve_BFS(char[][] board) {
            if (board == null || board.length == 0) return;
            int m = board.length;
            int n = board[0].length;

            Queue<int[]> queue = new LinkedList<>();

            //Identify O connected to borders and mark as not surrounded
            for (int j = 0; j < n; j++) {
                markNotSurrounded(board, 0, j, queue);
                markNotSurrounded(board, m - 1, j, queue);
            }

            for (int i = 0; i < m; i++) {
                markNotSurrounded(board, i, 0, queue);
                markNotSurrounded(board, i, n - 1, queue);
            }

            int[][] steps = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

            // expand not surrounded cells
            while (!queue.isEmpty()) {
                int[] cell = queue.poll();
                int i = cell[0];
                int j = cell[1];

                for (int[] step : steps) {
                    int ni = i + step[0];
                    int nj = j + step[1];
                    markNotSurrounded(board, ni, nj, queue);
                }
            }

            // Restore not surrounded cells and wipe other O as surrounded, replace by X
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 'S') {
                        board[i][j] = 'O';
                    } else if (board[i][j] == 'O') {
                        board[i][j] = 'X';
                    }
                }
            }
        }

        private void markNotSurrounded(char[][] board, int row, int col, Queue<int[]> queue) {
            if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] != 'O') {
                return;
            }
            board[row][col] = 'S';
            queue.offer(new int[]{row, col});
        }
    }


    public void solve_DFS(char[][] board) {
        if (board == null || board.length == 0) return;
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            dfs(board, i, 0, m, n);
            dfs(board, i, n - 1, m, n);
        }
        for (int j = 0; j < n; j++) {
            dfs(board, 0, j, m, n);
            dfs(board, m - 1, j, m, n);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = (board[i][j] == 'S') ? 'O' : 'X';
            }
        }
    }

    public void dfs(char[][] board, int i, int j, int m, int n) {
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != 'O') return;
        board[i][j] = 'S';
        dfs(board, i + 1, j, m, n);
        dfs(board, i - 1, j, m, n);
        dfs(board, i, j + 1, m, n);
        dfs(board, i, j - 1, m, n);
    }
}
