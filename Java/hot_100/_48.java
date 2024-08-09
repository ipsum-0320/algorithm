package Java.hot_100;

public class _48 {
    public void rotate(int[][] matrix) {
        // 可以直接找规律。
        /* [
            [1,2,3],
            [*,*,*],
            [*,*,*]
           ]
         * 变换后
           [
            [*,*,1],
            [*,*,2],
            [*,*,3]
           ]
         * 可以找到规律为：对于矩阵中第 i 行的第 j 个元素，在旋转后，它出现在第 j 行倒数第 i 列的位置。
         * */
        int[][] copyArr = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                copyArr[i][j] = matrix[i][j];
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                // 顺时针。
                matrix[j][matrix.length - i - 1] = copyArr[i][j];
                // 逆时针。
                // matrix[i][j] = copyArr[j][matrix.length - i - 1];
            }
        }
    }

    public void rotate_2(int[][] matrix) {
        // 先上下翻转。
        // 然后主对角线翻转。
        for (int i = 0; i < matrix.length / 2; i++) {
            for (int j = 0; j < matrix.length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[matrix.length - i - 1][j];
                matrix[matrix.length - i - 1][j] = temp;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}
