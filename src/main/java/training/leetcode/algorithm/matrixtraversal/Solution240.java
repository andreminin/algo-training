package training.leetcode.algorithm.matrixtraversal;

public class Solution240 {
    /*
    Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:

    Integers in each row are sorted in ascending from left to right.
    Integers in each column are sorted in ascending from top to bottom.

    Explanation

    Initial Check: The code first checks if the matrix is null or empty to handle edge cases.
    Initial Position: The search starts from the top-right corner of the matrix (i.e., row = 0 and col = matrix[0].length - 1).
    Search Loop: The loop continues as long as the current position is within the matrix boundaries:

        If the current element matches the target, the function returns true.
        If the current element is greater than the target, the search moves left (decrementing col) to explore smaller values.
        If the current element is less than the target, the search moves down (incrementing row) to explore larger values.

    Result: If the loop exits without finding the target, the function returns false.
    This approach efficiently narrows down the search space by leveraging the sorted properties of the matrix, ensuring optimal performance.
     */

    public boolean searchMatrix(int[][] matrix, int target) {
        int row = 0;
        int rows = matrix.length;
        int col = matrix[0].length - 1;

        while (row < rows && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                col--;
            } else {
                row++;
            }
        }

        return false;
    }


}
