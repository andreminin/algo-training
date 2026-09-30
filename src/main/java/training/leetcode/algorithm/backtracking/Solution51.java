package training.leetcode.algorithm.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution51 {
    /*
        The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
        Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
        Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.

        Explanation

        Approach

    Backtracking: We use a recursive backtracking approach to place queens column by column. For each column, we try placing a queen in every row that does not conflict with existing queens.

    Conflict Checking: To efficiently check for conflicts, we maintain three boolean arrays:

        rowUsed: Tracks which rows are already occupied.

        diag1: Tracks diagonals where the sum of row and column indices is constant (slope -1).

        diag2: Tracks diagonals where the difference between row and column indices is constant (slope 1),
         adjusted to avoid negative indices.

    Base Case: When all queens are placed (i.e., all columns are processed), we convert the current board state into the
     required output format and add it to the solutions list.

    Recursive Case: For each column, try every row. If placing a queen in a row doesn't cause a conflict, mark
     the row and diagonals as used, then recursively process the next column. After returning from recursion,
      backtrack by unmarking the row and diagonals.

        This approach efficiently explores all possible valid configurations using backtracking and ensures constraints are met
        using auxiliary arrays for conflict checks. The time complexity is O(N!), which is optimal for this problem due to the
        nature of backtracking. The space complexity is O(N) for the recursion stack and auxiliary arrays.
     */


    public List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<>();
        int[] queenColumns = new int[n];
        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }

        boolean[] rowUsed = new boolean[n];
        boolean[] constSumDiag = new boolean[2 * n - 1];
        boolean[] constDifDiag = new boolean[2 * n - 1];

        backtrack(0, queenColumns, rowUsed, constSumDiag, constDifDiag, n, solutions, board);

        return solutions;
    }

    private void backtrack(int col, int[] queenColumns, boolean[] rowUsed, boolean[] constSumDiag, boolean[] constDifDiag, int n,
                           List<List<String>> solutions, char[][] board) {
        if (col == n) {
            List<String> result = new ArrayList<>();
            for (char[] r : board) {
                result.add(new String(r));
            }
            solutions.add(result);
            return;
        }

        int sumDiagIndex;
        int difDiagIndex;

        for (int row = 0; row < n; row++) {
            sumDiagIndex = row + col;
            difDiagIndex = row - col + n - 1;

            if (rowUsed[row] || constSumDiag[sumDiagIndex] || constDifDiag[difDiagIndex]) {
                continue;
            }

            queenColumns[col] = row;
            rowUsed[row] = true;
            constSumDiag[sumDiagIndex] = true;
            constDifDiag[difDiagIndex] = true;
            board[col][row]= 'Q';

            backtrack(col + 1, queenColumns, rowUsed, constSumDiag, constDifDiag, n, solutions, board);

            board[col][row] = '.';
            rowUsed[row] = false;
            constSumDiag[sumDiagIndex] = false;
            constDifDiag[difDiagIndex] = false;
        }
    }


}
