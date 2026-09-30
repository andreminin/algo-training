package training.leetcode.algorithm.sudoku;

public class Solution35 {
    /*
       36. Valid Sudoku

        Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

            Each row must contain the digits 1-9 without repetition.
            Each column must contain the digits 1-9 without repetition.
            Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.

        Note:
            A Sudoku board (partially filled) could be valid but is not necessarily solvable.
            Only the filled cells need to be validated according to the mentioned rules.
     */

    public boolean isValidSudoku(char[][] board) {

        for (int i = 0; i < 9; i++) {
            //check row
            if (!isValid(board[i])) {
                return false;
            }
            char[] column = new char[9];
            for (int j = 0; j < 9; j++) {
                column[j] = board[j][i];
            }
            //check column
            if (!isValid(column)) {
                return false;
            }
        }

        //check subbox
        for (int cell = 0; cell < 9; cell++) {
            int row = cell / 3;
            int col = cell % 3;
            char[] subbox = new char[9];
            int offset = 0;
            for (int i = row * 3; i < row * 3 + 3; i++) {
                for (int j = col * 3; j < col * 3 + 3; j++) {
                    subbox[offset++] = board[i][j];
                }
            }
            //check subbox
            if (!isValid(subbox)) {
                return false;
            }
        }

        return true;
    }


    private boolean isValid(char[] digits) {
        int[] valFreq = new int[9];
        for (int digit : digits) {
            if (digit == '.') {
                continue;
            }
            int value = digit - '1';
            if (valFreq[value] > 0) {
                return false;
            }
            valFreq[value] = 1;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution35 solution = new Solution35();

        System.out.println(solution.isValidSudoku(new char[][]
                {{'.', '.', '4', '.', '.', '.', '6', '3', '.'}
                , {'.', '.', '.', '.', '.', '.', '.', '.', '.'}
                , {'5', '.', '.', '.', '.', '.', '.', '9', '.'}
                , {'.', '.', '.', '5', '6', '.', '.', '.', '.'}
                , {'4', '.', '3', '.', '.', '.', '.', '.', '1'}
                , {'.', '.', '.', '7', '.', '.', '.', '.', '.'}
                , {'.', '.', '.', '5', '.', '.', '.', '.', '.'}
                , {'.', '.', '.', '.', '.', '.', '.', '.', '.'}
                , {'.', '.', '.', '.', '.', '.', '.', '.', '.'}}));

        System.out.println(solution.isValidSudoku(new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'}
                , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        }));

        System.out.println(solution.isValidSudoku(new char[][]
                {{'8', '3', '.', '.', '7', '.', '.', '.', '.'}
                , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}}));

    }
}
