package Java.hot_100;

public class _73 {

    public void setZeroes(int[][] matrix) {
        /**
         * [
         *   [1, 2, 3, 0],
         *   [4, 5, 6, 0],
         *   [7, 8, 9, 0]
         * ]
         * */
        int m = matrix.length, n = matrix[0].length;
        boolean[] row = new boolean[m], col = new boolean[n];
        // row 和 rol 创建出来默认是 false。
        // 先行后列。
        // row[i] 表示第 i 行有没有 0，col[i] 表示第 i 列有没有 0。
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || col[j])
                    matrix[i][j] = 0;
            }
        }
    }

    public void setZeroes_2(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        // 记录第一行，第一列有没有零。
        boolean is_row_0 = false, is_col_0 = false;
        // 遍历第一行。
        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == 0) {
                is_row_0 = true;
                break;
            }
        }
        // 遍历第一列。
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                is_col_0 = true;
                break;
            }
        }
        // 开始遍历剩下的 n - 1 行和 n - 1 列。
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 如果 matrix[i][j] == 0，那么 matrix[i][0] 和 matrix[0][j] 都要置为 0。
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        // 第一行是 0。
        if (is_row_0) {
            for (int i = 0; i < n; i++) {
                matrix[0][i] = 0;
            }
        }
        // 第一列是 0。
        if (is_col_0) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
