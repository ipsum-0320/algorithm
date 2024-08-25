package Java.hot_100;

public class _74 {

    public boolean searchMatrix(int[][] matrix, int target) {
        // 二分查找，转化为一维数组。
        int left = 0, right = matrix.length * matrix[0].length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            int rowL = mid / matrix[0].length;
            int colL = mid % matrix[0].length;

            if (matrix[rowL][colL] == target) return true;
            else if (matrix[rowL][colL] < target) left = mid;
            else right = mid;
        }

        int rowL = left / matrix[0].length;
        int colL = left % matrix[0].length;
        if (matrix[rowL][colL] == target) return true;
        rowL = right / matrix[0].length;
        colL = right % matrix[0].length;
        return matrix[rowL][colL] == target;
    }

}
