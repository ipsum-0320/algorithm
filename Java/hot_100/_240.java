package Java.hot_100;

public class _240 {
    public boolean searchMatrix(int[][] matrix, int target) {
        // 从左下角开始找。
        int rowL = matrix.length, colL = matrix[0].length;
        int row = rowL - 1, col = 0;
        while (row >= 0 && col < colL) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                row--;
            } else {
                col++;
            }
        }
        return false;
    }
}
