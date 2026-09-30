package training.leetcode.algorithm.backtracking;

import java.util.Arrays;

public class Solution37 {
    /*
       37. Sudoku Solver

        Write a program to solve a Sudoku puzzle by filling the empty cells.
        A sudoku solution must satisfy all of the following rules:

            Each of the digits 1-9 must occur exactly once in each row.
            Each of the digits 1-9 must occur exactly once in each column.
            Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.

        The '.' character indicates empty cells.

        Approach

    Backtracking: The algorithm works by trying to place digits from '1' to '9' in each empty cell (marked by '.'),
     ensuring that each placement adheres to Sudoku rules.

    Validation Check: Before placing a digit, check if it is valid in the current row, column, and 3x3 sub-box.

    Recursion: For each valid placement, recursively attempt to solve the rest of the board. If the recursion
    leads to a solution, return true. Otherwise, backtrack by resetting the cell to '.' and try the next digit.

    Termination: The recursion terminates when all cells are filled, indicating a solution is found.

     worst case (O(9^(n*n))
     */

    public void solveSudoku(char[][] board) {
        solveBoard(board);
    }

    private boolean solveBoard(char[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == '.') {
                    for (char num = '1'; num <= '9'; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;
                            if (solveBoard(board)) {
                                return true;
                            }
                            // backtrack
                            board[row][col] = '.';
                        }
                    }
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isValid(char[][] board, int row, int col, char num) {
        int blockRow = (row / 3) * 3;
        int blockCol = (col / 3) * 3;

        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num
                || board[i][col] == num
                || board[blockRow + i / 3][blockCol + i % 3] == num)
            {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Solution37 solution = new Solution37();

        char[][] board = new char[][]
                {{'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};
        solution.solveSudoku(board);
        for(int i = 0; i < board.length; i++) {
            System.out.println(Arrays.toString(board[i]));
        }

    }

    int[] rows = new int[9];
    int[] cols = new int[9];
    int[] boxes = new int[9];
    char[][] board;
    int[][] empties;
    int emptyCount = 0;

    public void solveSudoku2(char[][] board) {
        this.board = board;
        empties = new int[81][2];
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char ch = board[r][c];
                if (ch == '.' || ch == '0') {
                    empties[emptyCount++] = new int[]{r, c};
                } else {
                    int d = ch - '0';
                    int mask = 1 << d;
                    rows[r] |= mask;
                    cols[c] |= mask;
                    boxes[boxIndex(r, c)] |= mask;
                }
            }
        }
        backtrack(0);
    }

    private boolean backtrack(int idx) {
        if (idx == emptyCount) return true;

        int bestIdx = -1;
        int bestOptions = 10;
        for (int k = idx; k < emptyCount; k++) {
            int r = empties[k][0], c = empties[k][1];
            int used = rows[r] | cols[c] | boxes[boxIndex(r, c)];
            int options = 9 - Integer.bitCount(used & 0x3FE);
            if (options < bestOptions) {
                bestOptions = options;
                bestIdx = k;
                if (options == 1) break;
            }
        }

        if (bestIdx != idx) {
            int[] tmp = empties[idx];
            empties[idx] = empties[bestIdx];
            empties[bestIdx] = tmp;
        }

        int r = empties[idx][0], c = empties[idx][1];
        int used = rows[r] | cols[c] | boxes[boxIndex(r, c)];
        for (int d = 1; d <= 9; d++) {
            int mask = 1 << d;
            if ((used & mask) == 0) {
                rows[r] |= mask;
                cols[c] |= mask;
                boxes[boxIndex(r, c)] |= mask;
                board[r][c] = (char) ('0' + d);

                if (backtrack(idx + 1)) return true;

                rows[r] &= ~mask;
                cols[c] &= ~mask;
                boxes[boxIndex(r, c)] &= ~mask;
                board[r][c] = '.';
            }
        }

        return false;
    }

    private int boxIndex(int r, int c) {
        return (r / 3) * 3 + (c / 3);
    }
}
